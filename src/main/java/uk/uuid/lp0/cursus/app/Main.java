/*
	cursus - Race series management program
	Copyright 2011-2014  Simon Arlott

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU Affero General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU Affero General Public License for more details.

	You should have received a copy of the GNU Affero General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uk.uuid.lp0.cursus.app;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.InsetsUIResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.uuid.lp0.cursus.i18n.Messages;
import uk.uuid.lp0.cursus.ui.Constants;
import uk.uuid.lp0.cursus.ui.MainWindow;
import uk.uuid.lp0.cursus.util.Background;

import com.google.common.annotations.VisibleForTesting;

import uk.uuid.cursus.db.Database;
import uk.uuid.cursus.db.DatabaseSession;
import uk.uuid.cursus.db.InvalidDatabaseException;
import uk.uuid.cursus.db.MemoryDatabase;
import uk.uuid.cursus.db.dao.SeriesDAO;
import uk.uuid.cursus.db.data.Event;
import uk.uuid.cursus.db.data.Race;
import uk.uuid.cursus.db.data.Series;

public class Main implements Runnable {
	public static final String UNTITLED_SERIES = "series.untitled"; //$NON-NLS-1$
	public static final String UNTITLED_EVENT = "event.untitled"; //$NON-NLS-1$
	public static final String UNTITLED_RACE = "race.untitled"; //$NON-NLS-1$

	private static final Logger log = LoggerFactory.getLogger(Main.class);
	private static final SeriesDAO seriesDAO = new SeriesDAO();
	private static final AtomicLong UNTITLED = new AtomicLong();
	private final String[] args;
	private MainWindow win = null; // TODO use EventBus
	private Database db = null;

	public static void main(String[] args) {
		Background.execute(new Main(args));
	}

	public Main(String[] args) {
		this.args = args;
	}

	public String[] getArgs() {
		return args;
	}

	@Override
	public void run() {
		log.info(Constants.APP_DESC);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			log.debug("Unable to select system look and feel", e); //$NON-NLS-1$
		}

		try {
			UIManager.setLookAndFeel(new com.jgoodies.looks.plastic.Plastic3DLookAndFeel());
		} catch (Exception e) {
			log.debug("Unable to select JGoodies look and feel", e); //$NON-NLS-1$
		}

		UIManager.put("SplitPane.continuousLayout", true); //$NON-NLS-1$
		UIManager.put("TabbedPane.tabInsets", new InsetsUIResource(5, 15, 5, 15)); //$NON-NLS-1$

		win = new MainWindow(this);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				win.display();
			}
		});
	}

	public synchronized boolean isOpen() {
		return db != null;
	}

	public synchronized Database getDatabase() {
		return db;
	}

	@VisibleForTesting
	public MainWindow getWindow() {
		return win;
	}

	@VisibleForTesting
	public void setWindow(MainWindow win) {
		this.win = win;
	}

	public synchronized boolean newDatabase() {
		assert (Background.isExecutorThread());

		close();
		if (!isOpen()) {
			boolean ok = true;

			try {
				db = createEmptyDatabase();
			} catch (SQLException e) {
				// TODO log
				ok = false;
			} catch (InvalidDatabaseException e) {
				// TODO log
				ok = false;
			}

			if (ok) {
				win.databaseOpened();
			} else {
				JOptionPane.showMessageDialog(win, Messages.getString("err.unable-to-create-new-db"), Constants.APP_NAME, JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
			}
			return ok;
		}
		return false;
	}

	public synchronized boolean openDatabase(Database newDB) {
		assert (Background.isExecutorThread());

		// TODO preserve current selection
		close(true);
		if (!isOpen()) {
			this.db = newDB;
			win.databaseOpened();
			return true;
		}
		return false;
	}

	protected Database createEmptyDatabase() throws InvalidDatabaseException, SQLException {
		Database mem = new MemoryDatabase(Messages.getString("db.untitled", UNTITLED.incrementAndGet())); //$NON-NLS-1$

		mem.startSession();
		try {
			DatabaseSession.begin();

			Series series = new Series(Messages.getString(UNTITLED_SERIES));
			Event event = new Event(series, Messages.getString(UNTITLED_EVENT));
			series.getEvents().add(event);
			Race race = new Race(event, Messages.getString(UNTITLED_RACE));
			event.getRaces().add(race);
			seriesDAO.persist(series);

			DatabaseSession.commit();
		} finally {
			mem.endSession();
		}

		return mem;
	}

	public synchronized boolean savedAs(Database newDB) throws InvalidDatabaseException, SQLException {
		assert (Background.isExecutorThread());

		// TODO preserve current selection
		close(true);
		if (!isOpen()) {
			this.db = newDB;
			win.databaseOpened();
			return true;
		}
		return false;
	}

	public boolean close() {
		return close(false);
	}

	public synchronized boolean close(boolean force) {
		assert (Background.isExecutorThread());

		if (db != null) {
			if (db.close(force)) {
				db = null;
				win.databaseClosed();
			}
		}
		return !isOpen();
	}
}