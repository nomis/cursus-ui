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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

import eu.lp0.cursus.db.data.Penalty;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceAttendee;
import eu.lp0.cursus.db.data.RaceEvent;

public class GenericRaceLapsData<T extends ScoredData & RacePenaltiesData> extends AbstractRaceLapsData<T> {
	private static final int EXPECTED_MAXIMUM_LAPS = 32;
	private final boolean scoreBeforeStart;
	private final boolean scoreAfterFinish;

	public GenericRaceLapsData(T scores, boolean scoreBeforeStart, boolean scoreAfterFinish) {
		super(scores);

		this.scoreBeforeStart = scoreBeforeStart;
		this.scoreAfterFinish = scoreAfterFinish;
	}

	@Override
	protected List<Pilot> calculateRaceLapsInOrder(Race race, Map<Pilot, Integer> laps) {
		ListMultimap<Integer, Pilot> raceOrder = ArrayListMultimap.create(EXPECTED_MAXIMUM_LAPS, scores.getPilots().size());

		for (Pilot pilot : scores.getPilots()) {
			laps.put(pilot, 0);
		}

		for (Pilot pilot : extractRaceLaps(race)) {
			int lapCount = laps.get(pilot);

			raceOrder.remove(lapCount, pilot);
			laps.put(pilot, ++lapCount);
			raceOrder.put(lapCount, pilot);
		}

		// Save pilot order
		List<Pilot> origPilotOrder = getPilotOrder(raceOrder);
		SortedSet<Pilot> noLaps = new TreeSet<Pilot>(new PilotRaceNumberComparator());
		Set<Integer> changed = new HashSet<Integer>();

		// It is intentional that pilots can end up having 0 laps but be considered to have completed the race
		for (RaceAttendee attendee : Maps.filterKeys(race.getAttendees(), Predicates.in(scores.getPilots())).values()) {
			for (Penalty penalty : Iterables.concat(Ordering.natural().sortedCopy(attendee.getPenalties()),
					scores.getSimulatedRacePenalties(attendee.getPilot(), race))) {
				if (penalty.getType() == Penalty.Type.LAPS && penalty.getValue() != 0) {
					Pilot pilot = attendee.getPilot();
					int lapCount = laps.get(pilot);

					raceOrder.remove(lapCount, pilot);
					changed.add(lapCount);

					lapCount += penalty.getValue();
					if (lapCount <= 0) {
						lapCount = 0;
						noLaps.add(pilot);
					}
					laps.put(pilot, lapCount);

					raceOrder.put(lapCount, pilot);
					changed.add(lapCount);
				}
			}
		}

		// Apply original pilot order
		if (!changed.isEmpty()) {
			origPilotOrder.addAll(noLaps);

			for (Integer lapCount : changed) {
				raceOrder.replaceValues(lapCount, Ordering.explicit(origPilotOrder).sortedCopy(raceOrder.get(lapCount)));
			}

			return getPilotOrder(raceOrder);
		} else {
			return origPilotOrder;
		}
	}

	protected List<Pilot> getPilotOrder(ListMultimap<Integer, Pilot> raceOrder) {
		List<Pilot> pilotOrder = new ArrayList<Pilot>(scores.getPilots().size());
		for (Integer lap : Ordering.natural().reverse().sortedCopy(raceOrder.keySet())) {
			pilotOrder.addAll(raceOrder.get(lap));
		}
		return pilotOrder;
	}

	protected Iterable<Pilot> extractRaceLaps(Race race) {
		// Convert a list of race events into a list of valid pilot laps
		return Iterables.filter(Iterables.transform(Iterables.unmodifiableIterable(race.getEvents()), new Function<RaceEvent, Pilot>() {
			boolean scoring = scoreBeforeStart;

			@Override
			public Pilot apply(RaceEvent event) {
				switch (event.getType()) {
				case START:
					scoring = true;
					break;

				case LAP:
					if (scoring) {
						return event.getPilot();
					}
					break;

				case INVALID_LAP:
					break;

				case FINISH:
					scoring = scoreAfterFinish;
					break;
				}

				return null;
			}

			// If they aren't marked as attending the race as a pilot, they don't get scored.
		}), Predicates.in(filteredPilots(race)));
	}

	private Set<Pilot> filteredPilots(Race race) {
		ImmutableSet.Builder<Pilot> pilots = ImmutableSet.builder();
		for (RaceAttendee attendee : Maps.filterKeys(race.getAttendees(), Predicates.in(scores.getFleet())).values()) {
			if (attendee.getType() == RaceAttendee.Type.PILOT) {
				pilots.add(attendee.getPilot());
			}
		}
		return pilots.build();
	}
}