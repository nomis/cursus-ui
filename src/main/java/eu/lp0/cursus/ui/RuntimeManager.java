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
package eu.lp0.cursus.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingUtilities;

import eu.lp0.cursus.db.InvalidDatabaseException;
import eu.lp0.cursus.util.Background;
import eu.lp0.cursus.util.Messages;

public class RuntimeManager {
	private final MainWindow win;
	private final DatabaseManager dbMgr;

	public RuntimeManager(MainWindow win, DatabaseManager dbMgr, final String[] args) {
		this.win = win;
		this.dbMgr = dbMgr;

		win.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent we) {
				Background.execute(new Runnable() {
					@Override
					public void run() {
						try {
							startup(args);
						} catch (InvalidDatabaseException e) {
							// TODO handle uncaught exceptions
							throw new RuntimeException(e);
						}
					}
				});
			}

			@Override
			public void windowClosing(WindowEvent we) {
				Background.execute(new Runnable() {
					@Override
					public void run() {
						shutdown();
					}
				});
			}
		});
	}

	private void startup(String[] args) throws InvalidDatabaseException {
		assert (Background.isExecutorThread());

		try {
			if (args.length == 0) {
				dbMgr.newDatabase();
			} else if (args.length == 1) {
				// TODO open file
			} else {
				// TODO error message
			}
		} finally {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					win.getMenu().enableOpen(true);
				}
			});
		}
	}

	private void shutdown() {
		if (dbMgr.trySaveDatabase(Messages.getString("menu.file.exit"))) { //$NON-NLS-1$
			try {
				win.dispose();
			} finally {
				Background.shutdownNow();
			}
		}
	}
}