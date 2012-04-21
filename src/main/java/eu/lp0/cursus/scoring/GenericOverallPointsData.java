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
package eu.lp0.cursus.scoring;

import java.util.Map.Entry;

import com.google.common.base.Predicates;
import com.google.common.collect.Maps;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

public class GenericOverallPointsData<T extends ScoredData & RacePointsData & RaceDiscardsData & OverallPenaltiesData> extends AbstractOverallPointsData<T> {
	public GenericOverallPointsData(T scores) {
		super(scores);
	}

	@Override
	protected int calculateOverallPoints(Pilot pilot) {
		int points = 0;

		// Add race points but not discards
		for (Entry<Race, Integer> racePoints : Maps.filterKeys(scores.getRacePoints(pilot), Predicates.not(Predicates.in(scores.getDiscardedRaces(pilot))))
				.entrySet()) {
			points += racePoints.getValue();
		}

		// Add all penalties (this includes race penalties)
		points += scores.getOverallPenalties(pilot);
		if (points < 0) {
			points = 0;
		}

		return points;
	}
}