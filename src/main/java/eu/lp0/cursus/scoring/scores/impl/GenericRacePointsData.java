/*
	cursus - Race series management program
	Copyright 2012-2013  Simon Arlott

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

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.data.RaceLapsData;
import eu.lp0.cursus.scoring.data.ScoredData;
import eu.lp0.cursus.scoring.scores.base.AbstractRacePointsData;

public class GenericRacePointsData<T extends ScoredData & RaceLapsData> extends AbstractRacePointsData<T> {
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
	private final FleetMethod raceFleetMethod;
	protected final Supplier<Map<Race, Integer>> lazyRaceFleetSize = Suppliers.memoize(new Supplier<Map<Race, Integer>>() {
		@Override
		public Map<Race, Integer> get() {
			return calculateFleetSizes(raceFleetMethod);
		}
	});
	private final FleetMethod nonAttendeeFleetMethod;
	protected final Supplier<Map<Race, Integer>> lazyNonAttendeeFleetSize = Suppliers.memoize(new Supplier<Map<Race, Integer>>() {
		@Override
		public Map<Race, Integer> get() {
			return calculateFleetSizes(nonAttendeeFleetMethod);
		}
	});

	/**
	 * Methods of calculating the fleet size for each race.
	 */
	public enum FleetMethod {
		/**
		 * The scored pilots who attended the race.
		 * <p>
		 * Simplest option but results in a wide disparity of scores for pilots who complete no laps when some races have significantly fewer pilots.
		 */
		RACE,

		/**
		 * The scored pilots who attended the event (or any of its races).
		 * <p>
		 * Combines the fleet size of all races that occurred in the event, keeping the event's races isolated from other events.
		 */
		EVENT,

		/**
		 * The scored pilots who attended any event or race.
		 * <p>
		 * Combines the total fleet of all races in the series, which may be much larger than any single event.
		 */
		SERIES,

		/**
		 * The scored pilots who attended any scored race.
		 * <p>
		 * Combines the fleet size of all races that are being scored, but ignores attendance of the events involved. Unusual because it can make the fleet size
		 * different between the race scores and the series scores.
		 */
		RACES_SCORED,

		/**
		 * The scored pilots who attended any scored event (or any of their races).
		 * <p>
		 * Combines the fleet size of all races for all events that are being scored, including attendance of the events involved. Unusual because it can make
		 * the fleet size different between the event scores and the series scores.
		 */
		EVENTS_SCORED,

		/**
		 * The scored pilots regardless of whether or not they attended the event or race.
		 */
		PILOTS;
	}

	public GenericRacePointsData(T scores, FleetMethod fleetMethod) {
		this(scores, fleetMethod, fleetMethod);
	}

	public GenericRacePointsData(T scores, FleetMethod raceFleetMethod, FleetMethod nonAttendeeFleetMethod) {
		super(scores);
		this.raceFleetMethod = raceFleetMethod;
		this.nonAttendeeFleetMethod = nonAttendeeFleetMethod;
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
		if (!pilot.getEvents().contains(pilot) && Sets.intersection(lazyEventRaces.get().get(race.getEvent()), pilot.getRaces().keySet()).isEmpty()) {
			return getNonAttendeeFleetSize(race) + 1;
		} else {
			return getFleetSize(race) + 1;
		}
	}

	@Override
	public final int getFleetSize(Race race) {
		return lazyRaceFleetSize.get().get(race);
	}

	protected int getNonAttendeeFleetSize(Race race) {
		return lazyNonAttendeeFleetSize.get().get(race);
	}

	protected Map<Race, Integer> calculateFleetSizes(FleetMethod fleetMethod) {
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

					pilots.addAll(event.getAttendees());

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
				pilots.addAll(event.getAttendees());

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

		case RACES_SCORED: {
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

		case EVENTS_SCORED: {
			Set<Pilot> pilots = new HashSet<Pilot>(scores.getPilots().size() * 2);
			for (Event event : scores.getEvents()) {
				pilots.addAll(event.getAttendees());

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

		case PILOTS:
			for (Race race : scores.getRaces()) {
				fleetSizes.put(race, scores.getFleet().size());
			}
			break;
		}
		return fleetSizes;
	}
}