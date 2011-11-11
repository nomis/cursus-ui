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
package eu.lp0.cursus.scoring;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Penalty;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.PilotAtEvent;
import eu.lp0.cursus.db.data.Race;

public class GenericOverallPenaltiesData<T extends ScoredData & RacePenaltiesData> extends AbstractOverallPenaltiesData<T> {
	public GenericOverallPenaltiesData(T scores) {
		super(scores);
	}

	@Override
	public int getOverallPenalties(Pilot pilot) {
		int penalties = 0;

		// Add race penalties
		for (Integer racePenalties : scores.getRacePenalties(pilot).values()) {
			penalties += racePenalties;
		}

		// Get event/series penalties
		List<Penalty> overallPenalties = new ArrayList<Penalty>();
		Set<Event> events = new HashSet<Event>();
		for (Race race : scores.getRaces()) {
			events.add(race.getEvent());
		}
		Iterator<Event> it = events.iterator();
		while (it.hasNext()) {
			Event event = it.next();
			// Only add event penalties if all the races from this event are included
			if (scores.getRaces().containsAll(event.getRaces())) {
				PilotAtEvent pilotAtEvent = event.getPilots().get(pilot);
				if (pilotAtEvent != null) {
					overallPenalties.addAll(pilotAtEvent.getPenalties());
				}
			} else {
				it.remove();
			}
		}
		// Only add series penalties if all the events from this series are included
		if (events.containsAll(pilot.getSeries().getEvents())) {
			overallPenalties.addAll(pilot.getSeriesPenalties());
		}

		// Calculate overall penalties
		int autoPenalties = 0;
		for (Penalty penalty : overallPenalties) {
			switch (penalty.getType()) {
			case AUTOMATIC:
				if (penalty.getValue() >= 0) {
					// Add multiple automatic penalties
					for (int i = 0; i < penalty.getValue(); i++) {
						penalties += ++autoPenalties;
					}
				} else {
					// Remove multiple automatic penalties
					for (int i = penalty.getValue(); i > 0; i--) {
						if (autoPenalties > 0) {
							penalties -= autoPenalties--;
						}
					}
				}
				break;

			case FIXED:
				// Fixed penalty added/removed
				penalties += penalty.getValue();
				break;
			}
		}

		return penalties;
	}
}