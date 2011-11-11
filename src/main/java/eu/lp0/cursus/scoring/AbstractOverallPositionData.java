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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import eu.lp0.cursus.db.data.Pilot;

public abstract class AbstractOverallPositionData<T extends ScoredData> implements OverallPositionData {
	protected final T scores;

	public AbstractOverallPositionData(T scores) {
		this.scores = scores;
	}

	@Override
	public Map<Pilot, Integer> getOverallPositions() {
		Map<Pilot, Integer> racePositions = new HashMap<Pilot, Integer>();
		for (Entry<Integer, Pilot> entry : getOverallPositionsWithOrder().entries()) {
			racePositions.put(entry.getValue(), entry.getKey());
		}
		return Collections.unmodifiableMap(racePositions);
	}

	@Override
	public int getOverallPosition(Pilot pilot) {
		Multimap<Pilot, Integer> inverted = HashMultimap.create();
		Multimaps.invertFrom(getOverallPositionsWithOrder(), inverted);
		return inverted.get(pilot).iterator().next();
	}

	@Override
	public List<Pilot> getOverallOrder() {
		return Collections.unmodifiableList(getOverallPositionsWithOrder().values());
	}

	@Override
	public abstract LinkedListMultimap<Integer, Pilot> getOverallPositionsWithOrder();
}