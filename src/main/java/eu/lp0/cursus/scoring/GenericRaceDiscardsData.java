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
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.common.base.Predicates;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

public class GenericRaceDiscardsData<T extends ScoredData & RacePointsData> extends AbstractRaceDiscardsData<T> {
	public GenericRaceDiscardsData(T scores) {
		super(scores, 0);
	}

	public GenericRaceDiscardsData(T scores, DiscardCalculator discardCalculator) {
		super(scores, discardCalculator.getDiscardsFor(scores.getRaces()));
	}

	@Override
	protected List<Race> calculateDiscardedRaces(Pilot pilot) {
		List<Race> pilotDiscards = new ArrayList<Race>(discards);

		if (discards > 0) {
			final Map<Race, Integer> racePoints = scores.getRacePoints(pilot);
			SortedSet<Race> pilotRaces = new TreeSet<Race>(new Comparator<Race>() {
				@Override
				public int compare(Race o1, Race o2) {
					return ComparisonChain.start().compare(racePoints.get(o2), racePoints.get(o1)).compare(o1, o2).result();
				}
			});

			// Use all races where the score is not null
			pilotRaces.addAll(Maps.filterValues(racePoints, Predicates.notNull()).keySet());

			// Discard the highest scoring races
			Iterator<Race> it = Iterables.limit(pilotRaces, 2).iterator();
			for (int i = 1; i <= discards; i++) {
				pilotDiscards.add(it.hasNext() ? it.next() : null);
			}
		}

		return pilotDiscards;
	}
}