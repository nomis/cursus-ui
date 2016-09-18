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
package uk.uuid.lp0.cursus.ui.actions;

import java.awt.Dialog;
import java.awt.event.ActionEvent;

import javax.swing.Action;

import uk.uuid.lp0.cursus.util.SwingHacks;

public class CancelDialogAction extends AbstractTranslatedAction {
	private final Dialog dialog;

	public CancelDialogAction(Dialog dialog) {
		super("dialog.cancel", false); //$NON-NLS-1$
		this.dialog = dialog;

		putValue(Action.SMALL_ICON, SwingHacks.getCancelIcon());
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		dialog.dispose();
	}
}