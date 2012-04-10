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

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

public class GenericOverallPenaltiesData<T extends ScoredData & RacePenaltiesData> extends AbstractOverallPenaltiesData<T> {
	private final int eventNonAttendancePenalty;

	public GenericOverallPenaltiesData(T scores, int eventNonAttendancePenalty) {
		super(scores);

		this.eventNonAttendancePenalty = eventNonAttendancePenalty;
	}

	@Override
	protected int calculateOverallPenalties(Pilot pilot) {
		int penalties = 0;

		for (Integer racePenalties : scores.getRacePenalties(pilot).values()) {
			penalties += racePenalties;
		}

		Set<Event> events = new HashSet<Event>();
		for (Race race : scores.getRaces()) {
			events.add(race.getEvent());
		}
		for (Event event : events) {
			if (Sets.intersection(Sets.newHashSet(event.getRaces()), pilot.getRaces().keySet()).isEmpty()) {
				penalties += eventNonAttendancePenalty;
			}
		}

		return penalties;
	}
}