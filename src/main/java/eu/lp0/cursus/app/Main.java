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

import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import eu.lp0.cursus.db.Database;
import eu.lp0.cursus.db.InvalidDatabaseException;
import eu.lp0.cursus.db.MemoryDatabase;
import eu.lp0.cursus.ui.MainWindow;

public class Main implements Runnable {
	private final MainWindow win;
	private Database db = null;

	public static void main(String[] args) throws InterruptedException, InvocationTargetException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		SwingUtilities.invokeAndWait(new Main(args));
	}

	public Main(String[] args) {
		win = new MainWindow(this, args);
	}

	@Override
	public void run() {
		win.setLocationRelativeTo(null);
		win.setVisible(true);
		win.setExtendedState(win.getExtendedState() | Frame.MAXIMIZED_BOTH);
	}

	public synchronized boolean isOpen() {
		return db != null;
	}

	public synchronized Database getDatabase() {
		return db;
	}

	public synchronized boolean open() throws SQLException, InvalidDatabaseException {
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