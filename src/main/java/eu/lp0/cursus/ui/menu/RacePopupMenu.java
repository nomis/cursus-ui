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
package eu.lp0.cursus.ui.menu;

import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.ui.actions.DeleteRaceAction;
import eu.lp0.cursus.ui.actions.EditRaceAction;
import eu.lp0.cursus.ui.actions.MoveRaceDownAction;
import eu.lp0.cursus.ui.actions.MoveRaceUpAction;
import eu.lp0.cursus.ui.actions.NewRaceAction;
import eu.lp0.cursus.ui.component.DatabaseWindow;

public class RacePopupMenu extends JPopupMenu {
	public RacePopupMenu(DatabaseWindow win, Race race) {
		add(new EditRaceAction(win, race));
		add(new DeleteRaceAction(win, race));
		add(new JSeparator());
		add(new MoveRaceUpAction(win, race));
		add(new MoveRaceDownAction(win, race));
		add(new JSeparator());
		add(new NewRaceAction(win, race.getEvent()));
	}
}