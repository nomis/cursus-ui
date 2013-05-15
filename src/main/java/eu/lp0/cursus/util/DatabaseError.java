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
package eu.lp0.cursus.util;

import java.awt.Component;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import eu.lp0.cursus.i18n.Messages;

public class DatabaseError {
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
			message = t.getMessage();
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
				lines.addFirst(t.getLocalizedMessage());
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
}