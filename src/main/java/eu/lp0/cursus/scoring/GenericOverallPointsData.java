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
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.base.Predicates;
import com.google.common.collect.Sets;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

public class GenericOverallPointsData<T extends ScoredData & RacePointsData & RaceDiscardsData & OverallPenaltiesData> extends AbstractOverallPointsData<T> {
	public enum OverallFleetMethod {
		FILTERED, UNFILTERED;
	}

	public GenericOverallPointsData(T scores) {
		super(scores);
	}

	@Override
	protected int calculateOverallPoints(Pilot pilot) {
		int points = 0;

		Set<Race> discardedRaces = scores.getDiscardedRaces(pilot);

		// Add race points but not discards
		for (Entry<Race, Integer> racePoints : scores.getRacePoints(pilot).entrySet()) {
			if (!discardedRaces.contains(racePoints.getKey())) {
				points += racePoints.getValue();
			}
		}

		// Add all penalties (this includes race penalties)
		points += scores.getOverallPenalties(pilot);
		if (points < 0) {
			points = 0;
		}

		return points;
	}

	@Override
	protected int calculateOverallFleetSize() {
		Set<Pilot> pilots = new HashSet<Pilot>(scores.getPilots().size() * 2);
		for (Race race : scores.getRaces()) {
			pilots.addAll(Sets.filter(race.getAttendees().keySet(), Predicates.in(scores.getPilots())));
		}
		return pilots.size();
	}
}