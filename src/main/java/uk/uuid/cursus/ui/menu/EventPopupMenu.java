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

import uk.uuid.cursus.ui.actions.DeleteEventAction;
import uk.uuid.cursus.ui.actions.EditEventAction;
import uk.uuid.cursus.ui.actions.MoveEventDownAction;
import uk.uuid.cursus.ui.actions.MoveEventUpAction;
import uk.uuid.cursus.ui.actions.NewEventAction;
import uk.uuid.cursus.ui.actions.NewRaceAction;
import uk.uuid.cursus.ui.component.DatabaseWindow;
import uk.uuid.cursus.db.data.Event;

public class EventPopupMenu extends JPopupMenu {
	public EventPopupMenu(final DatabaseWindow win, final Event event) {
		add(new NewRaceAction(win, event));
		add(new EditEventAction(win, event));
		add(new DeleteEventAction(win, event));
		add(new JSeparator());
		add(new MoveEventUpAction(win, event));
		add(new MoveEventDownAction(win, event));
		add(new JSeparator());
		add(new NewEventAction(win, event.getSeries()));
	}
}