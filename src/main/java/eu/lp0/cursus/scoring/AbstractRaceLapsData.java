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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.common.base.Predicates;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Table;

import eu.lp0.cursus.db.data.Penalty;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceAttendee;

public abstract class AbstractRaceLapsData<T extends ScoredData> implements RaceLapsData {
	private static final int EXPECTED_MAXIMUM_LAPS = 32;
	protected final T scores;
	private volatile boolean initialised;
	private Table<Race, Pilot, Integer> raceLaps;
	private Map<Race, List<Pilot>> raceLapOrder;

	public AbstractRaceLapsData(T scores) {
		this.scores = scores;
	}

	private void lazyInitialisation() {
		if (!initialised) {
			synchronized (this) {
				if (!initialised) {
					calculateRaceLaps();
					initialised = true;
				}
			}
		}
	}

	private void calculateRaceLaps() {
		raceLaps = ArrayTable.create(scores.getRaces(), scores.getPilots());
		raceLapOrder = new HashMap<Race, List<Pilot>>(scores.getRaces().size() * 2);

		for (Race race : scores.getRaces()) {
			Map<Pilot, Integer> laps = raceLaps.row(race);
			ListMultimap<Integer, Pilot> raceOrder = ArrayListMultimap.create(EXPECTED_MAXIMUM_LAPS, scores.getPilots().size());

			for (Pilot pilot : scores.getPilots()) {
				laps.put(pilot, 0);
			}

			for (Pilot pilot : calculateRaceLaps(race)) {
				int lapCount = raceLaps.get(race, pilot);

				raceOrder.remove(lapCount, pilot);
				raceLaps.put(race, pilot, ++lapCount);
				raceOrder.put(lapCount, pilot);
			}

			// Save pilot order
			List<Pilot> origPilotOrder = getPilotOrder(raceOrder);
			SortedSet<Pilot> noLaps = new TreeSet<Pilot>(new PilotRaceNumberComparator());
			Set<Integer> changed = new HashSet<Integer>();

			// It is intentional that pilots can end up having 0 laps but be considered to have completed the race
			for (RaceAttendee attendee : Maps.filterKeys(race.getAttendees(), Predicates.in(scores.getPilots())).values()) {
				for (Penalty penalty : attendee.getPenalties()) {
					if (penalty.getType() == Penalty.Type.LAPS && penalty.getValue() != 0) {
						Pilot pilot = attendee.getPilot();
						int lapCount = raceLaps.get(race, pilot);

						raceOrder.remove(lapCount, pilot);
						changed.add(lapCount);

						lapCount += penalty.getValue();
						if (lapCount <= 0) {
							lapCount = 0;
							noLaps.add(pilot);
						}
						raceLaps.put(race, pilot, lapCount);

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

				raceLapOrder.put(race, getPilotOrder(raceOrder));
			} else {
				raceLapOrder.put(race, origPilotOrder);
			}
		}
	}

	private List<Pilot> getPilotOrder(ListMultimap<Integer, Pilot> raceOrder) {
		List<Integer> reverseLaps = Ordering.natural().reverse().sortedCopy(raceOrder.keySet());
		List<Pilot> pilotOrder = new ArrayList<Pilot>(scores.getPilots().size());
		for (Integer lap : reverseLaps) {
			pilotOrder.addAll(raceOrder.get(lap));
		}
		return pilotOrder;
	}

	@Override
	public final int getLaps(Pilot pilot, Race race) {
		lazyInitialisation();
		return raceLaps.get(race, pilot);
	}

	@Override
	public final Map<Race, Integer> getLaps(Pilot pilot) {
		lazyInitialisation();
		return raceLaps.column(pilot);
	}

	@Override
	public final Map<Pilot, Integer> getLaps(Race race) {
		lazyInitialisation();
		return raceLaps.row(race);
	}

	@Override
	public final List<Pilot> getLapOrder(Race race) {
		lazyInitialisation();
		return raceLapOrder.get(race);
	}

	protected abstract Iterable<Pilot> calculateRaceLaps(Race race);
}