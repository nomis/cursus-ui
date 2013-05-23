/*
	cursus - Race series management program
	Copyright 2011-2013  Simon Arlott

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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.google.common.base.Throwables;

import eu.lp0.cursus.db.Database;
import eu.lp0.cursus.db.DatabaseVersionException;
import eu.lp0.cursus.db.FileDatabase;
import eu.lp0.cursus.db.InvalidDatabaseException;
import eu.lp0.cursus.i18n.Messages;
import eu.lp0.cursus.ui.util.ProgressMonitorWrapper;
import eu.lp0.cursus.util.Background;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.util.CursusException;
import eu.lp0.cursus.util.DatabaseError;

public class DatabaseManager implements ActionListener {
	private final MainWindow win;

	DatabaseManager(MainWindow win) {
		this.win = win;
	}

	public enum Commands {
		NEW, OPEN, SAVE, SAVE_AS, CLOSE;
	}

	@Override
	public void actionPerformed(final ActionEvent ae) {
		Background.execute(new Runnable() {
			@Override
			public void run() {
				try {
					switch (Commands.valueOf(ae.getActionCommand())) {
					case NEW:
						newDatabase();
						break;
					case OPEN:
						openDatabase();
						break;
					case SAVE:
						saveDatabase();
						break;
					case SAVE_AS:
						saveAsDatabase();
						break;
					case CLOSE:
						closeDatabase();
						break;
					}
				} catch (InvalidDatabaseException e) {
					// TODO handle uncaught exceptions
					throw Throwables.propagate(e);
				}
			}
		});
	}

	/**
	 * Try and save the database if one is open
	 * 
	 * @param action
	 *            text name of the action being performed
	 * @return true if the database was saved or discarded
	 */
	boolean trySaveDatabase(String action) {
		assert (Background.isExecutorThread());

		if (win.isOpen() && !win.getDatabase().isSaved()) {
			switch (JOptionPane.showConfirmDialog(win, Messages.getString("warn.current-db-not-saved", win.getDatabase().getName()), //$NON-NLS-1$
					Constants.APP_NAME + Constants.EN_DASH + action, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE)) {
			case JOptionPane.YES_OPTION:
				if (saveDatabase("menu.file.save", false)) { //$NON-NLS-1$
					return win.getMain().close(true);
				}
				return false;
			case JOptionPane.NO_OPTION:
				win.getDatabase().close(true);
				break;
			case JOptionPane.CANCEL_OPTION:
			case JOptionPane.CLOSED_OPTION:
			default:
				return false;
			}
		}
		return true;
	}

	void newDatabase() throws InvalidDatabaseException {
		assert (Background.isExecutorThread());

		if (trySaveDatabase(Messages.getString("menu.file.new"))) { //$NON-NLS-1$
			win.getMain().close(true);

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					win.getMenu().enableOpen(false);
				}
			});

			boolean ok = true;
			try {
				ok = win.getMain().open();
			} catch (SQLException e) {
				// TODO log
				ok = false;
			} catch (DatabaseVersionException e) {
				// TODO log
				ok = false;
			}
			if (!ok) {
				JOptionPane.showMessageDialog(win, Messages.getString("err.unable-to-create-new-db"), Constants.APP_NAME, JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						win.getMenu().enableOpen(true);
					}
				});
			}
		}
	}

	void openDatabase() {
		assert (Background.isExecutorThread());

		// TODO open database
		JOptionPane.showMessageDialog(win, Messages.getString("err.feat-not-impl"), //$NON-NLS-1$
				Constants.APP_NAME + Constants.EN_DASH + Messages.getString("menu.file.open"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
	}

	void saveDatabase() {
		assert (Background.isExecutorThread());

		saveDatabase("menu.file.save", true); //$NON-NLS-1$
	}

	void saveAsDatabase() {
		assert (Background.isExecutorThread());

		saveDatabase("menu.file.save-as", true); //$NON-NLS-1$
	}

	private boolean saveDatabase(String action, boolean open) {
		JFileChooser chooser = new JFileChooser();

		chooser.setFileFilter(FileDatabase.FILE_FILTER);
		chooser.setName(Messages.getString(action));
		switch (chooser.showSaveDialog(win)) {
		case JFileChooser.APPROVE_OPTION:
			break;

		case JFileChooser.CANCEL_OPTION:
		case JFileChooser.ERROR_OPTION:
		default:
			return false;
		}

		File file = FileDatabase.reformatForSave(chooser.getSelectedFile());

		if (file.exists()) {
			switch (JOptionPane.showConfirmDialog(win, Messages.getString("warn.file-exists", file.getAbsolutePath()), //$NON-NLS-1$
					Constants.APP_NAME + Constants.EN_DASH + action, JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE)) {
			case JOptionPane.OK_OPTION:
				break;
			case JOptionPane.NO_OPTION:
			case JOptionPane.CLOSED_OPTION:
			default:
				return false;
			}
		}

		try {
			Database db = win.getDatabase().saveAs(new ProgressMonitorWrapper(win) {
				@Override
				protected String buildMessage(String message) {
					return Messages.getString("db.saving-file", message); //$NON-NLS-1$
				}
			}, file);
			if (open) {
				win.getMain().savedAs(db);
			}
			return true;
		} catch (InterruptedException e) {
			DatabaseError.errorFileSave(win, chooser.getName(), file, new CursusException(Messages.getString("err.operation-canceled"), e)); //$NON-NLS-1$
		} catch (Exception e) {
			DatabaseError.errorFileSave(win, chooser.getName(), file, e);
		}
		return false;
	}

	void closeDatabase() {
		assert (Background.isExecutorThread());

		if (trySaveDatabase(Messages.getString("menu.file.close"))) { //$NON-NLS-1$
			if (!win.getMain().close(true)) {
				JOptionPane.showMessageDialog(win, Messages.getString("err.unable-to-close-db"), Constants.APP_NAME, JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
			}
		}
	}
}