/*
	cursus - Race series management program
	Copyright 2011, 2014  Simon Arlott

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
package uk.uuid.cursus.ui.component;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class DatabaseTextField extends JTextField {
	private static final String UNDO = "UNDO"; //$NON-NLS-1$
	private static final String REDO = "REDO"; //$NON-NLS-1$

	public DatabaseTextField(int maxLength) {
		this("", maxLength, 0); //$NON-NLS-1$
	}

	public DatabaseTextField(String text, int maxLength) {
		this(text, maxLength, 0);
	}

	public DatabaseTextField(String text, int maxLength, int columns) {
		super(new RestrictedLengthPlainDocument(maxLength), text, columns);

		final UndoManager undo = new UndoManager();
		getDocument().addUndoableEditListener(undo);
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), UNDO);
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), REDO);
		getActionMap().put(UNDO, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					undo.undo();
				} catch (CannotUndoException e) {
				}
			}
		});
		getActionMap().put(REDO, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					undo.redo();
				} catch (CannotRedoException e) {
				}
			}
		});
	}
}