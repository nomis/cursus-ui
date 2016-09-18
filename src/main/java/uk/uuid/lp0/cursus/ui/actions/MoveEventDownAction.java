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

import java.util.List;

import uk.uuid.lp0.cursus.ui.component.DatabaseWindow;
import uk.uuid.cursus.db.dao.EventDAO;
import uk.uuid.cursus.db.data.Event;

public class MoveEventDownAction extends AbstractMoveAction<Event> {
	private static final EventDAO eventDAO = new EventDAO();

	public MoveEventDownAction(DatabaseWindow win, Event event) {
		super("menu.event.move-down", false, win, event); //$NON-NLS-1$
	}

	@Override
	protected boolean doMove(Event item) {
		Event event = eventDAO.get(item);
		List<Event> events = event.getSeries().getEvents();
		int pos = event.getSeriesOrder();
		if (pos < events.size() - 1) {
			// Can move event later in the series
			events.set(pos, events.get(pos + 1));
			events.set(pos + 1, event);
			return true;
		} else {
			// Can't move this event later in the series
			return false;
		}
	}

	@Override
	protected void doRefresh(DatabaseWindow win) {
		win.refreshRaceList();
	}
}