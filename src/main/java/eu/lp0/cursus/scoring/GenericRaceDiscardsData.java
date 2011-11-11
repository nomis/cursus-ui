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

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

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
	public Map<Integer, Race> getDiscardedRaces(final Pilot pilot) {
		Map<Integer, Race> pilotDiscards = new TreeMap<Integer, Race>();

		if (discards > 0) {
			final Map<Race, Integer> racePoints = scores.getRacePoints(pilot);
			SortedSet<Race> pilotRaces = new TreeSet<Race>(new Comparator<Race>() {
				@Override
				public int compare(Race o1, Race o2) {
					return racePoints.get(o2).compareTo(racePoints.get(o1));
				}
			});

			// Use all races where the score is not null
			for (Map.Entry<Race, Integer> entry : racePoints.entrySet()) {
				if (entry.getValue() != null) {
					pilotRaces.add(entry.getKey());
				}
			}

			// Discard the highest scoring races
			Iterator<Race> it = pilotRaces.iterator();
			for (int discard = 1; discard <= discards; discard++) {
				if (it.hasNext()) {
					pilotDiscards.put(discard, null);
				} else {
					pilotDiscards.put(discard, it.next());
				}
			}
		}

		return Collections.unmodifiableMap(pilotDiscards);
	}
}