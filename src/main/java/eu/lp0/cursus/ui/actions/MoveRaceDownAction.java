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

import java.util.List;

import eu.lp0.cursus.db.dao.RaceDAO;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.ui.component.DatabaseWindow;

public class MoveRaceDownAction extends AbstractMoveAction<Race> {
	private static final RaceDAO raceDAO = new RaceDAO();

	public MoveRaceDownAction(DatabaseWindow win, Race race) {
		super("menu.race.move-down", false, win, race); //$NON-NLS-1$
	}

	@Override
	protected boolean doMove(Race item) {
		Race race = raceDAO.get(item);
		Event event = race.getEvent();
		List<Race> races = event.getRaces();
		int posRace = race.getEventOrder();
		if (posRace < races.size() - 1) {
			// Can move race later in the event
			races.set(posRace, races.get(posRace + 1));
			races.set(posRace + 1, race);
			return true;
		} else {
			// Can't move this race later in the event
			List<Event> events = event.getSeries().getEvents();
			int posEvent = event.getSeriesOrder();
			if (posEvent < events.size() - 1) {
				// Can move the race to a later event
				Event newEvent = event.getSeries().getEvents().get(posEvent + 1);
				event.getRaces().remove(race);
				race.setEvent(newEvent);
				newEvent.getRaces().add(0, race);
				return true;
			} else {
				// Can't move the race to a later event
				return false;
			}
		}
	}

	@Override
	protected void doRefresh(DatabaseWindow win) {
		win.refreshRaceList();
	}
}