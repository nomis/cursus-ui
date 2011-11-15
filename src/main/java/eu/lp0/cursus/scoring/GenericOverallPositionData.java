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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;

import com.google.common.collect.Iterators;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Ordering;
import com.google.common.collect.PeekingIterator;
import com.google.common.collect.TreeMultimap;

import eu.lp0.cursus.db.data.Pilot;

public class GenericOverallPositionData<T extends ScoredData & RacePointsData & RaceDiscardsData & OverallPointsData> extends AbstractOverallPositionData<T> {
	private final EqualPositioning equalPositioning;
	private final PilotRacePlacingComparator.PlacingMethod placingMethod;

	public enum EqualPositioning {
		ALWAYS, IF_REQUIRED;
	}

	public GenericOverallPositionData(T scores, EqualPositioning equalPositioning, PilotRacePlacingComparator.PlacingMethod placingMethod) {
		super(scores);

		this.equalPositioning = equalPositioning;
		this.placingMethod = placingMethod;
	}

	@Override
	protected LinkedListMultimap<Integer, Pilot> calculateOverallPositionsWithOrder() {
		// Invert race points with ordered lists of pilots
		Comparator<Pilot> racePlacings = new PilotRacePlacingComparator<T>(scores, placingMethod);
		Comparator<Pilot> fallbackOrdering = new PilotRaceNumberComparator();
		TreeMultimap<Integer, Pilot> invOverallPoints = TreeMultimap.create(Ordering.natural(), Ordering.from(racePlacings).compound(fallbackOrdering));
		Multimaps.invertFrom(Multimaps.forMap(scores.getOverallPoints()), invOverallPoints);

		// Calculate overall positions
		LinkedListMultimap<Integer, Pilot> overallPositions = LinkedListMultimap.create();
		List<Pilot> collectedPilots = new ArrayList<Pilot>(scores.getPilots().size());
		int position = 1;

		for (Integer points : invOverallPoints.keySet()) {
			SortedSet<Pilot> pilots = invOverallPoints.get(points);

			switch (equalPositioning) {
			case ALWAYS:
				// Always put pilots with the same points in the same position
				overallPositions.putAll(position, pilots);
				position += pilots.size();
				break;

			case IF_REQUIRED:
				// Try to put pilots with the same points in separate positions
				PeekingIterator<Pilot> it = Iterators.peekingIterator(pilots.iterator());
				while (it.hasNext()) {
					Pilot pilot = it.next();
					collectedPilots.add(pilot);

					// If this pilot compares equally with the next pilot, add them too
					while (it.hasNext() && racePlacings.compare(it.peek(), pilot) == 0) {
						collectedPilots.add(it.next());
					}

					// Sort them by an arbitrary order
					Collections.sort(collectedPilots, fallbackOrdering);

					// Add them all to this position
					overallPositions.putAll(position, collectedPilots);
					position += collectedPilots.size();

					collectedPilots.clear();
				}
				break;
			}
		}

		return overallPositions;
	}
}