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
package uk.uuid.lp0.cursus.util;

import java.awt.Component;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.uuid.lp0.cursus.i18n.Messages;
import uk.uuid.lp0.cursus.ui.Constants;
import uk.uuid.cursus.db.DatabaseVersion;
import uk.uuid.cursus.db.DatabaseVersionException;
import uk.uuid.cursus.db.InvalidDatabaseException;
import uk.uuid.cursus.db.InvalidFilenameException;
import uk.uuid.cursus.db.TooManyCursusRowsException;
import uk.uuid.cursus.util.CursusException;

public class DatabaseError {
	private static final Logger log = LoggerFactory.getLogger(DatabaseError.class);

	public static void unableToSave(Component c, String context, String message) {
		JOptionPane.showMessageDialog(c, message, Constants.APP_NAME + Constants.EN_DASH + context, JOptionPane.ERROR_MESSAGE);
	}

	public static void errorSaving(Component c, String context, Throwable t) {
		JOptionPane.showMessageDialog(c, Messages.getString("db.error-saving") + ":\n\n" + createMessage(t), Constants.APP_NAME + Constants.EN_DASH + context, //$NON-NLS-1$ //$NON-NLS-2$
				JOptionPane.ERROR_MESSAGE);
	}

	public static void errorFileSave(Component c, String context, File file, Throwable t) {
		String message;
		if (t instanceof CursusException) {
			message = translateMessage(t);
		} else {
			message = createMessage(t);
		}

		JOptionPane.showMessageDialog(c,
				Messages.getString("err.saving-db", file.getAbsoluteFile()) + ":\n\n" + message, Constants.APP_NAME + Constants.EN_DASH + context, //$NON-NLS-1$ //$NON-NLS-2$ 
				JOptionPane.ERROR_MESSAGE);
	}

	private static String createMessage(Throwable t) {
		LinkedList<String> lines = new LinkedList<String>();

		Throwable first = t;
		do {
			if (t == first || t.getCause() == null || t.getCause() == t) {
				lines.addFirst(""); //$NON-NLS-1$
				lines.addFirst(translateMessage(t));
				lines.addFirst(t.getClass().getName() + ":"); //$NON-NLS-1$
			}

			t = t.getCause();
		} while (t != null && t.getCause() != t);

		StringBuilder sb = new StringBuilder();
		for (String line : lines) {
			sb.append(line).append("\n"); //$NON-NLS-1$
		}
		return sb.toString();
	}

	private static String translateMessage(Throwable t) {
		if (t instanceof CursusException) {
			if (t instanceof InvalidDatabaseException) {
				if (t instanceof DatabaseVersionException) {
					return Messages.getString("db.version-not-supported", DatabaseVersion.parseLong(((DatabaseVersionException)t).getCursus().getVersion())); //$NON-NLS-1$
				} else if (t instanceof TooManyCursusRowsException) {
					return Messages.getString("db.too-many-database-identifier-rows", ((TooManyCursusRowsException)t).getRows()); //$NON-NLS-1$
				} else if (t instanceof InvalidFilenameException) {
					if (t instanceof InvalidFilenameException.Semicolon) {
						return Messages.getString("db.filename-invalid.semicolon", ((InvalidFilenameException)t).getName()); //$NON-NLS-1$
					} else if (t instanceof InvalidFilenameException.Suffix) {
						return Messages.getString("db.filename-invalid.suffix", ((InvalidFilenameException)t).getName(), //$NON-NLS-1$
								((InvalidFilenameException.Suffix)t).getSuffix());
					}
				}
			}
			log.warn("Untranslated exception: " + t); //$NON-NLS-1$
		}
		return t.getLocalizedMessage();
	}
}