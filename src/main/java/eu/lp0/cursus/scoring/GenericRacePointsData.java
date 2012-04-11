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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.Sets;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

public class GenericRacePointsData<T extends ScoredData & RaceLapsData> extends AbstractRacePointsData<T> {
	private final FleetMethod fleetMethod;
	protected final Supplier<Map<Race, Integer>> lazyRaceFleetSize = Suppliers.memoize(new Supplier<Map<Race, Integer>>() {
		@Override
		public Map<Race, Integer> get() {
			return calculateFleetSizes();
		}
	});

	public enum FleetMethod {
		RACE, EVENT, SERIES, SCORED, PILOTS;
	}

	public GenericRacePointsData(T scores, FleetMethod fleetMethod) {
		super(scores);
		this.fleetMethod = fleetMethod;
	}

	@Override
	protected Map<Pilot, Integer> calculateRacePoints(Race race) {
		Map<Pilot, Integer> racePoints = new HashMap<Pilot, Integer>(scores.getPilots().size() * 2);
		List<Pilot> lapOrder = scores.getLapOrder(race);

		// Score everyone who completed a lap
		int points = 0;
		for (Pilot pilot : lapOrder) {
			racePoints.put(pilot, points);
			points += (points == 0) ? 2 : 1;
		}

		// Score everyone else
		for (Pilot pilot : scores.getPilots()) {
			if (!racePoints.containsKey(pilot)) {
				racePoints.put(pilot, getPointsForNoLaps(pilot, race));
			}
		}

		return racePoints;
	}

	@Override
	protected boolean calculateSimulatedRacePoints(Pilot pilot, Race race) {
		return false;
	}

	protected int getPointsForNoLaps(Pilot pilot, Race race) {
		return getFleetSize(race) + 1;
	}

	@Override
	public final int getFleetSize(Race race) {
		return lazyRaceFleetSize.get().get(race);
	}

	protected Map<Race, Integer> calculateFleetSizes() {
		Map<Race, Integer> fleetSizes = new HashMap<Race, Integer>();
		switch (fleetMethod) {
		case RACE:
			for (Race race : scores.getRaces()) {
				fleetSizes.put(race, Sets.intersection(scores.getFleet(), race.getAttendees().keySet()).size());
			}
			break;

		case EVENT: {
			Map<Event, Integer> eventFleetSizes = new HashMap<Event, Integer>();
			for (Race race : scores.getRaces()) {
				Event event = race.getEvent();

				if (!eventFleetSizes.containsKey(event)) {
					Set<Pilot> pilots = new HashSet<Pilot>(scores.getSeries().getPilots().size() * 2);
					for (Race race2 : event.getRaces()) {
						pilots.addAll(race2.getAttendees().keySet());
					}
					eventFleetSizes.put(event, Sets.intersection(scores.getFleet(), pilots).size());
				}

				fleetSizes.put(race, eventFleetSizes.get(event));
			}

			break;
		}

		case SERIES: {
			Set<Pilot> pilots = new HashSet<Pilot>(scores.getSeries().getPilots().size() * 2);
			for (Event event : scores.getSeries().getEvents()) {
				for (Race race : event.getRaces()) {
					pilots.addAll(race.getAttendees().keySet());
				}
			}

			int fleetSize = Sets.intersection(scores.getFleet(), pilots).size();
			for (Race race : scores.getRaces()) {
				fleetSizes.put(race, fleetSize);
			}

			break;
		}

		case SCORED: {
			Set<Pilot> pilots = new HashSet<Pilot>(scores.getPilots().size() * 2);
			for (Race race : scores.getRaces()) {
				pilots.addAll(race.getAttendees().keySet());
			}

			int fleetSize = Sets.intersection(scores.getFleet(), pilots).size();
			for (Race race : scores.getRaces()) {
				fleetSizes.put(race, fleetSize);
			}

			break;
		}

		case PILOTS:
			for (Race race : scores.getRaces()) {
				fleetSizes.put(race, scores.getFleet().size());
			}
			break;
		}
		return fleetSizes;
	}
}