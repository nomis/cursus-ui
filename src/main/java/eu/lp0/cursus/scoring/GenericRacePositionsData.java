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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.google.common.collect.TreeMultimap;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

public class GenericRacePositionsData<T extends ScoredData & RaceLapsData & RacePointsData> extends AbstractRacePositionsData<T> {
	private final EqualPositioning equalPositioning;

	public enum EqualPositioning {
		ALWAYS, WITHOUT_LAPS, NEVER;
	}

	public GenericRacePositionsData(T scores, EqualPositioning equalPositioning) {
		super(scores);

		this.equalPositioning = equalPositioning;
	}

	@Override
	public LinkedListMultimap<Integer, Pilot> getRacePositionsWithOrder(Race race) {
		// Create a lap order list containing all pilots
		List<Pilot> lapOrder = new ArrayList<Pilot>(scores.getLapOrder(race));
		Set<Pilot> pilotsWithLaps = new HashSet<Pilot>(lapOrder);
		SortedSet<Pilot> pilotsWithoutLaps = new TreeSet<Pilot>(new PilotRaceNumberComparator());
		pilotsWithoutLaps.addAll(Sets.difference(new HashSet<Pilot>(scores.getPilots()), pilotsWithLaps));
		lapOrder.addAll(pilotsWithoutLaps);

		// Invert race points with ordered lists of pilots
		TreeMultimap<Integer, Pilot> invRacePoints = TreeMultimap.create(Ordering.natural(), Ordering.explicit(lapOrder));
		Multimaps.invertFrom(Multimaps.forMap(scores.getRacePoints(race)), invRacePoints);

		// Calculate race positions
		LinkedListMultimap<Integer, Pilot> racePositions = LinkedListMultimap.create();
		int position = 1;

		for (Integer points : invRacePoints.keys()) {
			SortedSet<Pilot> pilots = invRacePoints.get(points);

			switch (equalPositioning) {
			case ALWAYS:
				// Always put pilots with the same points in the same position
				racePositions.putAll(position, pilots);
				position += pilots.size();
				break;

			case WITHOUT_LAPS:
				// Add everyone with laps (by removing pilots without laps from the set) in separate positions
				for (Pilot pilot : Sets.difference(pilots, pilotsWithoutLaps)) {
					racePositions.put(position, pilot);
					position++;
				}

				// Add everyone without laps (by removing pilots with laps from the set) in the same position
				racePositions.putAll(position, Sets.difference(pilots, pilotsWithLaps));
				position += pilots.size();
				break;

			case NEVER:
				// Always put pilots with the same points in separate positions
				for (Pilot pilot : pilots) {
					racePositions.put(position, pilot);
					position++;
				}
				break;
			}
		}

		return racePositions;
	}
}