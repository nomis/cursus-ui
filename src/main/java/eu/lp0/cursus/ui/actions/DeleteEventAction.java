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
package eu.lp0.cursus.ui.actions;

import eu.lp0.cursus.db.dao.EventDAO;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.ui.component.DatabaseWindow;

public class DeleteEventAction extends AbstractDeleteAction<Event> {
	private static final EventDAO eventDAO = new EventDAO();

	public DeleteEventAction(DatabaseWindow win, Event event) {
		super("menu.event.delete", false, win, event); //$NON-NLS-1$
	}

	@Override
	protected void doDelete(Event item) {
		Event event = eventDAO.get(item);
		event.getSeries().getEvents().remove(event);
		eventDAO.remove(event);
	}

	@Override
	protected void doRefresh(DatabaseWindow win) {
		win.refreshRaceList();
	}
}