/*
	cursus - Race series management program
	Copyright 2011  Simon Arlott

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

import eu.lp0.cursus.db.Database;
import eu.lp0.cursus.db.DatabaseVersionException;
import eu.lp0.cursus.db.MemoryDatabase;
import eu.lp0.cursus.ui.MainWindow;

public class Main implements Runnable {
	private final MainWindow win = new MainWindow(this);
	private Database db = null;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		SwingUtilities.invokeLater(new Main());
	}

	@Override
	public void run() {
		win.setLocationRelativeTo(null);
		// TODO start maximised
		win.setVisible(true);

		// FIXME why are the window components not already rendered here?

		try {
			open();
		} catch (SQLException e) {
			// TODO handle unhandled exceptions
			throw new RuntimeException(e);
		} catch (DatabaseVersionException e) {
			// TODO handle unhandled exceptions
			throw new RuntimeException(e);
		}
	}

	public synchronized boolean isOpen() {
		return db != null;
	}

	public synchronized Database getDatabase() {
		return db;
	}

	public synchronized boolean open() throws SQLException, DatabaseVersionException {
		close();
		if (!isOpen()) {
			db = new MemoryDatabase();
			win.databaseOpened();
			return true;
		}
		return false;
	}

	public boolean close() {
		return close(false);
	}

	public synchronized boolean close(boolean force) {
		if (db != null) {
			if (db.close(force)) {
				db = null;
				win.databaseClosed();
			}
		}
		return !isOpen();
	}
}