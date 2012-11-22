/*
	cursus - Race series management program
	Copyright 2012  Simon Arlott

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
package eu.lp0.cursus.scoring.scores.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Penalty;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.data.RacePenaltiesData;
import eu.lp0.cursus.scoring.data.ScoredData;
import eu.lp0.cursus.scoring.scores.base.AbstractOverallPenaltiesData;

public class GenericOverallPenaltiesData<T extends ScoredData & RacePenaltiesData> extends AbstractOverallPenaltiesData<T> {
	protected final Supplier<Map<Event, Set<Race>>> lazyEventRaces = Suppliers.memoize(new Supplier<Map<Event, Set<Race>>>() {
		@Override
		public Map<Event, Set<Race>> get() {
			Map<Event, Set<Race>> eventRaces = new LinkedHashMap<Event, Set<Race>>(scores.getEvents().size() * 2);
			for (Event event : Ordering.natural().sortedCopy(scores.getEvents())) {
				eventRaces.put(event, ImmutableSet.copyOf(event.getRaces()));
			}
			return eventRaces;
		}
	});
	private final int eventNonAttendancePenalty;
	private final int eventNonAttendanceDiscards;

	public GenericOverallPenaltiesData(T scores, int eventNonAttendancePenalty, DiscardCalculator eventNonAttendanceDiscardCalculator) {
		super(scores);

		this.eventNonAttendancePenalty = eventNonAttendancePenalty;
		this.eventNonAttendanceDiscards = eventNonAttendanceDiscardCalculator.getDiscardsFor(scores.getRaces());
	}

	@Override
	protected int calculateOverallPenalties(Pilot pilot) {
		int penalties = 0;

		for (Integer racePenalties : scores.getRacePenalties(pilot).values()) {
			penalties += racePenalties;
		}

		for (Penalty penalty : getSimulatedOverallPenalties(pilot)) {
			switch (penalty.getType()) {
			case FIXED:
			case EVENT_NON_ATTENDANCE:
				// Fixed penalty added/removed
				penalties += penalty.getValue();
				break;

			case AUTOMATIC:
				// This can only be applied in RacePenaltiesData
			case LAPS:
				// This can only be applied in RaceLapsData
				Preconditions.checkArgument(false, "Invalid overall penalty type: " + penalty.getType()); //$NON-NLS-1$
				break;
			}
		}

		return penalties;
	}

	@Override
	protected List<Penalty> calculateSimulatedOverallPenalties(Pilot pilot) {
		List<Penalty> penalties = new ArrayList<Penalty>(1);

		if (eventNonAttendancePenalty != 0) {
			for (Event event : lazyEventRaces.get().keySet()) {
				if (!event.getAttendees().contains(pilot) && Sets.intersection(lazyEventRaces.get().get(event), pilot.getRaces().keySet()).isEmpty()) {
					penalties.add(new Penalty(Penalty.Type.EVENT_NON_ATTENDANCE, eventNonAttendancePenalty, event.getName()));
				}
			}
		}

		for (int i = 0; i < eventNonAttendanceDiscards && i < penalties.size(); i++) {
			penalties.get(penalties.size() - (i + 1)).setValue(0);
		}

		return penalties;
	}
}