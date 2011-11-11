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

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

public class GenericOverallPointsData<T extends ScoredData & RacePointsData & RaceDiscardsData & OverallPenaltiesData> extends AbstractOverallPointsData<T> {
	public GenericOverallPointsData(T scores) {
		super(scores);
	}

	@Override
	public int getOverallPoints(Pilot pilot) {
		int points = 0;

		// Add race points
		for (Integer racePoints : scores.getRacePoints(pilot).values()) {
			points += racePoints;
		}

		// Remove discarded races
		for (Integer raceDiscard : scores.getRaceDiscards(pilot).values()) {
			points -= raceDiscard;
		}

		// Add penalties
		points += scores.getOverallPenalties(pilot);
		if (points < 0) {
			points = 0;
		}

		return points;
	}

	@Override
	public int getOverallFleetSize() {
		Set<Pilot> pilots = new HashSet<Pilot>();
		for (Race race : scores.getRaces()) {
			pilots.addAll(race.getAttendees().keySet());
		}
		return pilots.size();
	}
}