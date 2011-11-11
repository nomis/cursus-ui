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

import java.util.Comparator;
import java.util.SortedSet;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Ordering;
import com.google.common.collect.TreeMultimap;

import eu.lp0.cursus.db.data.Pilot;

public class GenericOverallPositionData<T extends ScoredData & RacePointsData & OverallPointsData> extends AbstractOverallPositionData<T> {
	private final EqualPositioning equalPositioning;

	public enum EqualPositioning {
		ALWAYS, IF_REQUIRED;
	}

	public GenericOverallPositionData(T scores, EqualPositioning equalPositioning) {
		super(scores);

		this.equalPositioning = equalPositioning;
	}

	@Override
	public LinkedListMultimap<Integer, Pilot> getOverallPositions() {
		// Invert race points with ordered lists of pilots
		Comparator<Pilot> racePlacings = new PilotRacePlacingComparator(scores.getRacePoints());
		TreeMultimap<Integer, Pilot> invOverallPoints = TreeMultimap.create(Ordering.natural(), racePlacings);
		Multimaps.invertFrom(Multimaps.forMap(scores.getOverallPoints()), invOverallPoints);

		// Calculate overall positions
		LinkedListMultimap<Integer, Pilot> overallPositions = LinkedListMultimap.create();
		int position = 1;

		for (Integer points : invOverallPoints.keys()) {
			SortedSet<Pilot> pilots = invOverallPoints.get(points);

			switch (equalPositioning) {
			case ALWAYS:
				// Always put pilots with the same points in the same position
				overallPositions.putAll(position, pilots);
				position += pilots.size();
				break;

			case IF_REQUIRED:
				// Try to put pilots with the same points in separate positions
				Pilot prevPilot = null;
				for (Pilot pilot : pilots) {
					// If this pilot does not compare equally with the previous pilot, use the next position
					if (prevPilot != null && racePlacings.compare(prevPilot, pilot) != 0) {
						position++;
					}
					overallPositions.put(position, pilot);
					prevPilot = pilot;
				}
				// Update the position after the last pilot added
				if (prevPilot != null) {
					position++;
				}
				break;
			}
		}

		return overallPositions;
	}
}