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
package uk.uuid.cursus.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import uk.uuid.cursus.ui.DatabaseManager;

public class FileSaveAsAction extends AbstractTranslatedAction {
	private final DatabaseManager dbMgr;

	public FileSaveAsAction(DatabaseManager dbMgr) {
		super("menu.file.save-as", true); //$NON-NLS-1$
		this.dbMgr = dbMgr;

		putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		putValue(Action.ACTION_COMMAND_KEY, DatabaseManager.Commands.SAVE_AS.toString());
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		dbMgr.actionPerformed(ae);
	}
}