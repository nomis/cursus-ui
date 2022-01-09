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
package uk.uuid.cursus.ui.event;

import uk.uuid.cursus.ui.common.CommonDetailDialog;
import uk.uuid.cursus.ui.component.DatabaseWindow;
import uk.uuid.cursus.db.dao.EventDAO;
import uk.uuid.cursus.db.dao.SeriesDAO;
import uk.uuid.cursus.db.data.Event;

public class EventDetailDialog extends CommonDetailDialog<Event> {
	private static final SeriesDAO seriesDAO = new SeriesDAO();
	private static final EventDAO eventDAO = new EventDAO();

	public EventDetailDialog(DatabaseWindow win, String title, Event event, boolean isUpdate) {
		super(win, title, eventDAO, event, isUpdate);
	}

	@Override
	protected void prePersist(Event event) {
		event.setSeries(seriesDAO.get(event.getSeries()));
		event.getSeries().getEvents().add(event);
	}

	@Override
	protected void postSave() {
		win.refreshRaceList();
	}
}