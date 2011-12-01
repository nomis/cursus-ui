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
package eu.lp0.cursus.ui.menu;

import java.awt.Frame;

import javax.swing.Action;
import javax.swing.SwingUtilities;

import eu.lp0.cursus.ui.DatabaseManager;
import eu.lp0.cursus.ui.actions.ExitAction;
import eu.lp0.cursus.ui.actions.FileCloseAction;
import eu.lp0.cursus.ui.actions.FileOpenAction;
import eu.lp0.cursus.ui.actions.FileSaveAction;
import eu.lp0.cursus.ui.actions.FileSaveAsAction;
import eu.lp0.cursus.ui.actions.NewFileAction;
import eu.lp0.cursus.ui.component.TranslatedJMenu;

public class FileMenu extends TranslatedJMenu {
	private final Action mnuFileNew;
	private final Action mnuFileOpen;
	private final Action mnuFileSave;
	private final Action mnuFileSaveAs;
	private final Action mnuFileClose;

	public FileMenu(Frame win, DatabaseManager dbMgr) {
		super("menu.file", true); //$NON-NLS-1$

		add(mnuFileNew = new NewFileAction(dbMgr));
		mnuFileNew.setEnabled(false);

		add(mnuFileOpen = new FileOpenAction(dbMgr));
		mnuFileOpen.setEnabled(false);

		add(mnuFileSave = new FileSaveAction(dbMgr));
		mnuFileSave.setEnabled(false);

		mnuFileSaveAs = new FileSaveAsAction(dbMgr);
		add(mnuFileSaveAs);
		mnuFileSaveAs.setEnabled(false);

		add(mnuFileClose = new FileCloseAction(dbMgr));
		mnuFileClose.setEnabled(false);

		addSeparator();
		add(new ExitAction(win));
	}

	public void enableOpen(boolean enabled) {
		assert (SwingUtilities.isEventDispatchThread());

		mnuFileNew.setEnabled(enabled);
		mnuFileOpen.setEnabled(enabled);
	}

	public void sync(boolean open) {
		assert (SwingUtilities.isEventDispatchThread());

		enableOpen(true);
		mnuFileSave.setEnabled(open);
		mnuFileSaveAs.setEnabled(open);
		mnuFileClose.setEnabled(open);
	}
}