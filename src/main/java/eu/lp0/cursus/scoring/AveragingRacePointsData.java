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

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Predicates;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Table;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceAttendee;

public class AveragingRacePointsData<T extends Scores> extends GenericRacePointsData<T> {
	private final ScoresBeforeAveraging scoresBeforeAveraging = new ScoresBeforeAveraging();
	private final AveragingMethod method;
	private final Rounding rounding;
	protected final Supplier<Table<Pilot, Race, Integer>> lazyRacePointsBeforeAveraging = Suppliers.memoize(new Supplier<Table<Pilot, Race, Integer>>() {
		@Override
		public Table<Pilot, Race, Integer> get() {
			Table<Pilot, Race, Integer> racePoints = ArrayTable.create(scores.getPilots(), scores.getRaces());
			for (Race race : scores.getRaces()) {
				racePoints.column(race).putAll(AveragingRacePointsData.super.calculateRacePoints(race));

				for (Pilot pilot : scores.getSimulatedRacePoints(race)) {
					if (!getOtherRacesForPilot(pilot, race, false).isEmpty()) {
						// Null the score so that it can't be discarded
						racePoints.column(race).put(pilot, null);
					}
				}
			}
			return racePoints;
		}
	});

	public enum AveragingMethod {
		BEFORE_DISCARDS, AFTER_DISCARDS, SET_NULL;
	}

	public enum Rounding {
		/** @see BigDecimal#ROUND_DOWN */
		ROUND_DOWN (BigDecimal.ROUND_DOWN),

		/** @see BigDecimal#ROUND_HALF_DOWN */
		ROUND_HALF_DOWN (BigDecimal.ROUND_HALF_DOWN),

		/** @see BigDecimal#ROUND_HALF_EVEN */
		ROUND_HALF_EVEN (BigDecimal.ROUND_HALF_EVEN),

		/** @see BigDecimal#ROUND_HALF_UP */
		ROUND_HALF_UP (BigDecimal.ROUND_HALF_UP),

		/** @see BigDecimal#ROUND_UP */
		ROUND_UP (BigDecimal.ROUND_UP);

		private int value;

		private Rounding(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public AveragingRacePointsData(T scores, FleetMethod fleetMethod, AveragingMethod averagingMethod, Rounding rounding) {
		super(scores, fleetMethod);

		this.method = averagingMethod;
		this.rounding = rounding;
	}

	@Override
	protected boolean calculateSimulatedRacePoints(Pilot pilot, Race race) {
		// Return true even if the points aren't actually simulated but would
		// be simulated if there were more races to calculate an average from
		RaceAttendee attendee = pilot.getRaces().get(race);
		return attendee != null && attendee.getType().isMandatory();
	}

	// For each pilot with simulated races, this is called twice:
	// 1. When calculating points before averaging (!checkDiscards)
	// 2. When calculating averaged points (checkDiscards)
	//
	// An optimal implementation for pilots with simulated points
	// in many races would calculate for all of the pilot's races
	// together - but this is an very unusual scenario and relatively
	// few simulated points need to be calculated for each race
	private Set<Race> getOtherRacesForPilot(Pilot pilot, Race race, boolean checkDiscards) {
		Set<Race> otherRaces = new HashSet<Race>(scores.getRaces().size() * 2);

		// Find other races where the pilot is not attending in a mandatory position
		for (Race otherRace : Iterables.filter(scores.getRaces(), Predicates.not(Predicates.equalTo(race)))) {
			if (!scores.hasSimulatedRacePoints(pilot, otherRace)) {
				otherRaces.add(otherRace);
			}
		}

		// If averaging should occur after discards, remove discards... unless that removes all races
		if (checkDiscards && !otherRaces.isEmpty() && method == AveragingMethod.AFTER_DISCARDS) {
			Set<Race> discardedRaces = scoresBeforeAveraging.getDiscardedRaces(pilot);
			if (!discardedRaces.containsAll(otherRaces)) {
				otherRaces.removeAll(discardedRaces);
			}
		}

		return otherRaces;
	}

	@Override
	protected Map<Pilot, Integer> calculateRacePoints(Race race) {
		Table<Pilot, Race, Integer> racePoints = ArrayTable.create(scoresBeforeAveraging.getRacePoints());

		if (method != AveragingMethod.SET_NULL) {
			for (Pilot pilot : scores.getPilots()) {
				// Calculate an average score using the other races
				if (racePoints.row(pilot).get(race) == null) {
					Set<Race> otherRaces = getOtherRacesForPilot(pilot, race, true);

					// Add the scores from the other races
					int points = 0;
					for (Race otherRace : otherRaces) {
						points += racePoints.row(pilot).get(otherRace);
					}

					// Calculate and apply the average
					points = BigDecimal.valueOf(points).divide(BigDecimal.valueOf(otherRaces.size()), rounding.getValue()).intValue();
					racePoints.row(pilot).put(race, points);
				}
			}
		} else {
			// Do nothing, the scores for those pilots will be null
		}

		return racePoints.column(race);
	}

	public class ScoresBeforeAveraging extends ForwardingScores {
		private final Supplier<RacePointsData> racePointsData = Suppliers.memoize(new Supplier<RacePointsData>() {
			@Override
			public RacePointsData get() {
				return new RacePointsData() {
					@Override
					public Table<Pilot, Race, Integer> getRacePoints() {
						return lazyRacePointsBeforeAveraging.get();
					}

					@Override
					public int getRacePoints(Pilot pilot, Race race) {
						return lazyRacePointsBeforeAveraging.get().get(pilot, race);
					}

					@Override
					public Map<Race, Integer> getRacePoints(Pilot pilot) {
						return lazyRacePointsBeforeAveraging.get().row(pilot);
					}

					@Override
					public Map<Pilot, Integer> getRacePoints(Race race) {
						return lazyRacePointsBeforeAveraging.get().column(race);
					}

					@Override
					public Map<Race, ? extends Set<Pilot>> getSimulatedRacePoints() {
						return AveragingRacePointsData.this.getSimulatedRacePoints();
					}

					@Override
					public Map<Pilot, ? extends Set<Race>> getSimulatedPilotPoints() {
						return AveragingRacePointsData.this.getSimulatedPilotPoints();
					}

					@Override
					public boolean hasSimulatedRacePoints(Pilot pilot, Race race) {
						return AveragingRacePointsData.this.hasSimulatedRacePoints(pilot, race);
					}

					@Override
					public Set<Race> getSimulatedRacePoints(Pilot pilot) {
						return AveragingRacePointsData.this.getSimulatedRacePoints(pilot);
					}

					@Override
					public Set<Pilot> getSimulatedRacePoints(Race race) {
						return AveragingRacePointsData.this.getSimulatedRacePoints(race);
					}

					@Override
					public int getFleetSize(Race race) {
						return AveragingRacePointsData.this.getFleetSize(race);
					}
				};
			}
		});

		@Override
		protected Scores delegate() {
			return scores;
		}

		/**
		 * Provide an implementation of {@code RacePointsData} that returns the points before averaging but uses {@code this} instance to do so (this is
		 * important as {@code AveragingRacePointsData} could be sub-classed)
		 */
		@Override
		protected RacePointsData delegateRacePointsData() {
			return racePointsData.get();
		}
	}
}