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
import java.util.Collection;
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
	private final Method method;
	private final Rounding rounding;

	public enum Method {
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

	public AveragingRacePointsData(T scores, Method method, Rounding rounding) {
		super(scores);

		this.method = method;
		this.rounding = rounding;
	}

	protected Table<Pilot, Race, Integer> getRacePointsBeforeAveraging() {
		Table<Pilot, Race, Integer> racePoints = ArrayTable.create(scores.getPilots(), scores.getRaces());
		for (Race race : scores.getRaces()) {
			racePoints.column(race).putAll(super.getRacePoints(race));

			for (Pilot pilot : scores.getPilots()) {
				if (isPilotMandatoryAttendee(pilot, race)) {
					if (!getOtherRacesForPilot(pilot, race, false).isEmpty()) {
						// Null the score so that it can't be discarded
						racePoints.column(race).put(pilot, null);
					}
				}
			}
		}
		return racePoints;
	}

	private boolean isPilotMandatoryAttendee(Pilot pilot, Race race) {
		RaceAttendee attendee = race.getAttendees().get(pilot);
		return attendee != null && attendee.getType().isMandatory();
	}

	private Set<Race> getOtherRacesForPilot(Pilot pilot, Race race, boolean checkDiscards) {
		Set<Race> otherRaces = new HashSet<Race>();

		// Find other races where the pilot is not attending in a mandatory position
		for (Race otherRace : Iterables.filter(scores.getRaces(), Predicates.not(Predicates.equalTo(race)))) {
			if (!isPilotMandatoryAttendee(pilot, otherRace)) {
				otherRaces.add(otherRace);
			}
		}

		// If averaging should occur after discards, remove discards... unless that removes all races
		if (checkDiscards && !otherRaces.isEmpty() && method == Method.AFTER_DISCARDS) {
			Collection<Race> discardedRaces = scoresBeforeAveraging.getDiscardedRaces(pilot).values();
			if (!discardedRaces.containsAll(otherRaces)) {
				otherRaces.removeAll(discardedRaces);
			}
		}

		return otherRaces;
	}

	@Override
	public Map<Pilot, Integer> getRacePoints(Race race) {
		Table<Pilot, Race, Integer> racePoints = ArrayTable.create(scoresBeforeAveraging.getRacePoints());

		if (method != Method.SET_NULL) {
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
		private final Supplier<RaceDiscardsData> raceDiscardsData = Suppliers.memoize(new Supplier<RaceDiscardsData>() {
			@Override
			public RaceDiscardsData get() {
				return delegate().newRaceDiscardsData(ScoresBeforeAveraging.this);
			}
		});

		private final Supplier<RacePointsData> racePointsData = Suppliers.memoize(new Supplier<RacePointsData>() {
			@Override
			public RacePointsData get() {
				return new RacePointsData() {
					@Override
					public Table<Pilot, Race, Integer> getRacePoints() {
						return AveragingRacePointsData.this.getRacePointsBeforeAveraging();
					}

					@Override
					public int getRacePoints(Pilot pilot, Race race) {
						return AveragingRacePointsData.this.getRacePointsBeforeAveraging().get(pilot, race);
					}

					@Override
					public Map<Race, Integer> getRacePoints(Pilot pilot) {
						return AveragingRacePointsData.this.getRacePointsBeforeAveraging().row(pilot);
					}

					@Override
					public Map<Pilot, Integer> getRacePoints(Race race) {
						return AveragingRacePointsData.this.getRacePointsBeforeAveraging().column(race);
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
		 * Create a new {@code RaceDiscardsData}, but intercept access to {@code RacePointsData}
		 */
		@Override
		protected RaceDiscardsData delegateRaceDiscardsData() {
			return raceDiscardsData.get();
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