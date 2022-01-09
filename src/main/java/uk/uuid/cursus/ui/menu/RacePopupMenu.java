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
package uk.uuid.cursus.ui.menu;

import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

import uk.uuid.cursus.ui.actions.DeleteRaceAction;
import uk.uuid.cursus.ui.actions.EditRaceAction;
import uk.uuid.cursus.ui.actions.MoveRaceDownAction;
import uk.uuid.cursus.ui.actions.MoveRaceUpAction;
import uk.uuid.cursus.ui.actions.NewRaceAction;
import uk.uuid.cursus.ui.component.DatabaseWindow;
import uk.uuid.cursus.db.data.Race;

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