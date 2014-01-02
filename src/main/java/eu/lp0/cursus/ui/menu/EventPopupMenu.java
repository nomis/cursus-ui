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

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.ui.actions.DeleteEventAction;
import eu.lp0.cursus.ui.actions.EditEventAction;
import eu.lp0.cursus.ui.actions.MoveEventDownAction;
import eu.lp0.cursus.ui.actions.MoveEventUpAction;
import eu.lp0.cursus.ui.actions.NewEventAction;
import eu.lp0.cursus.ui.actions.NewRaceAction;
import eu.lp0.cursus.ui.component.DatabaseWindow;

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