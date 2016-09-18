/*
	cursus - Race series management program
	Copyright 2011-2012, 2014  Simon Arlott

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
package uk.uuid.lp0.cursus.ui.menu;

import java.awt.Frame;

import javax.swing.JMenuBar;

import uk.uuid.lp0.cursus.i18n.LanguageManager;
import uk.uuid.lp0.cursus.ui.DatabaseManager;

public class MainMenu extends JMenuBar {
	private final FileMenu mnuFile;

	public MainMenu(Frame win, DatabaseManager dbMgr) {
		add(mnuFile = new FileMenu(win, dbMgr));
		add(new LangMenu());
		add(new HelpMenu(win));
		LanguageManager.register(this, true);
	}

	public void enableOpen(boolean enabled) {
		mnuFile.enableOpen(enabled);
	}

	public void sync(boolean open, boolean saved) {
		mnuFile.sync(open, saved);
	}
}