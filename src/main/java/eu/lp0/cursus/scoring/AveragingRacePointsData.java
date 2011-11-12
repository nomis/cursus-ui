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
import java.util.Map;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceAttendee;

public class AveragingRacePointsData<T extends ScoredData & RaceLapsData & RacePenaltiesData> extends GenericRacePointsData<T> {
	private final Method method;
	private final Rounding rounding;

	public enum Method {
		BEFORE_DISCARDS, AFTER_DISCARDS, DISABLED;
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
		}
		return racePoints;
	}

	@Override
	public Map<Pilot, Integer> getRacePoints(Race race) {
		// The great thing about this call is that it includes the current race without requesting it explicitly from the superclass
		Table<Pilot, Race, Integer> racePoints = getRacePointsBeforeAveraging();

		// Average the scores of anyone attending as mandatory race master/scorer/marshal/etc.
		switch (method) {
		case BEFORE_DISCARDS:
			for (Map.Entry<Pilot, RaceAttendee> attendee : race.getAttendees().entrySet()) {
				if (attendee.getValue().getType().isMandatory() && scores.getPilots().contains(attendee.getKey())) {
					// This pilot is attending as a mandatory position in this race
					int totalPoints = 0;
					int totalRaces = 0;

					// Add their score from the other races
					for (Map.Entry<Race, Integer> otherRace : racePoints.row(attendee.getKey()).entrySet()) {
						if (otherRace.getKey() != race) {
							RaceAttendee otherAttendee = otherRace.getKey().getAttendees().get(attendee.getKey());
							if (otherAttendee == null || !otherAttendee.getType().isMandatory()) {
								// This pilot is not attending as a mandatory position in the other race
								totalPoints += otherRace.getValue();
								totalRaces++;
							}
						}
					}

					// Calculate and apply the average if they have some other races
					if (totalRaces > 0) {
						int averagePoints = BigDecimal.valueOf(totalPoints).divide(BigDecimal.valueOf(totalRaces), rounding.getValue()).intValue();
						racePoints.column(race).put(attendee.getKey(), averagePoints);
					}
				}
			}
			break;

		case AFTER_DISCARDS:
			// TODO
			break;

		case DISABLED:
			// Do nothing
			break;
		}

		return racePoints.column(race);
	}
}