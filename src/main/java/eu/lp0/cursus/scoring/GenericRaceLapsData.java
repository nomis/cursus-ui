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

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceAttendee;
import eu.lp0.cursus.db.data.RaceEvent;

public class GenericRaceLapsData<T extends ScoredData> extends AbstractRaceLapsData<T> {
	private final boolean scoreBeforeStart;
	private final boolean scoreAfterFinish;

	public GenericRaceLapsData(T scores, boolean scoreBeforeStart, boolean scoreAfterFinish) {
		super(scores);

		this.scoreBeforeStart = scoreBeforeStart;
		this.scoreAfterFinish = scoreAfterFinish;

		for (Race race : scores.getRaces()) {
			loadLaps(race);
		}
	}

	private Set<Pilot> filteredPilots(Race race) {
		ImmutableSet.Builder<Pilot> pilots = ImmutableSet.builder();
		for (RaceAttendee attendee : race.getAttendees().values()) {
			if (scores.getPilots().contains(attendee.getPilot()) && attendee.getType() == RaceAttendee.Type.PILOT) {
				pilots.add(attendee.getPilot());
			}
		}
		return pilots.build();
	}

	private void loadLaps(Race race) {
		Set<Pilot> pilots = filteredPilots(race);
		boolean beforeStart = true;
		boolean afterFinish = false;

		for (RaceEvent event : race.getEvents()) {
			switch (event.getType()) {
			case START:
				beforeStart = false;
				break;

			case LAP:
				if ((!beforeStart || scoreBeforeStart) && (!afterFinish || scoreAfterFinish)) {
					// If they aren't marked as attending the race as a pilot, they don't get scored.
					if (pilots.contains(event.getPilot())) {
						completeRaceLap(race, event.getPilot());
					}
				}
				break;

			case FINISH:
				afterFinish = true;
				break;
			}
		}
	}
}