/*
	cursus - Race series management program
	Copyright 2011-2012  Simon Arlott

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.lp0.cursus.app;

import java.sql.SQLException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.InsetsUIResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.annotations.VisibleForTesting;

import eu.lp0.cursus.db.Database;
import eu.lp0.cursus.db.InvalidDatabaseException;
import eu.lp0.cursus.db.MemoryDatabase;
import eu.lp0.cursus.ui.MainWindow;
import eu.lp0.cursus.util.Background;
import eu.lp0.cursus.util.Constants;

public class Main implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(Main.class);
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

	public synchronized boolean open() throws InvalidDatabaseException, SQLException {
		assert (Background.isExecutorThread());

		close();
		if (!isOpen()) {
			db = createEmptyDatabase();
			win.databaseOpened();
			return true;
		}
		return false;
	}

	protected Database createEmptyDatabase() throws InvalidDatabaseException, SQLException {
		return new MemoryDatabase();
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