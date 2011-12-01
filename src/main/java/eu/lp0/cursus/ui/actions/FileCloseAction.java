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
package eu.lp0.cursus.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import eu.lp0.cursus.ui.DatabaseManager;

public class FileCloseAction extends AbstractTranslatedAction {
	private final DatabaseManager dbMgr;

	public FileCloseAction(DatabaseManager dbMgr) {
		super("menu.file.close", true); //$NON-NLS-1$
		this.dbMgr = dbMgr;

		putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
		putValue(Action.ACTION_COMMAND_KEY, DatabaseManager.Commands.CLOSE.toString());
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		dbMgr.actionPerformed(ae);
	}
}