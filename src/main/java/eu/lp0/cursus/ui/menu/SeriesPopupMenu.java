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

import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.ui.actions.DeleteSeriesAction;
import eu.lp0.cursus.ui.actions.EditSeriesAction;
import eu.lp0.cursus.ui.actions.NewEventAction;
import eu.lp0.cursus.ui.actions.NewSeriesAction;
import eu.lp0.cursus.ui.component.DatabaseWindow;

public class SeriesPopupMenu extends JPopupMenu {
	public SeriesPopupMenu(DatabaseWindow win, Series series) {
		add(new NewEventAction(win, series));
		add(new EditSeriesAction(win, series));
		add(new DeleteSeriesAction(win, series));
		add(new JSeparator());
		add(new NewSeriesAction(win));
	}
}