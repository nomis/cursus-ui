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
package eu.lp0.cursus.ui.tab;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTree;

import eu.lp0.cursus.ui.MainWindow;

public class PenaltiesManager extends RaceTreeManager {
	public PenaltiesManager(MainWindow win, JTabbedPane mainTabs, JPanel penaltiesTab, JTree penaltiesRaceList) {
		super(win, mainTabs, penaltiesTab, penaltiesRaceList);
	}

	@Override
	protected void tabSelected() {
		super.tabSelected();
	}

	@Override
	protected void databaseClosed() {
		super.databaseClosed();
	}
}