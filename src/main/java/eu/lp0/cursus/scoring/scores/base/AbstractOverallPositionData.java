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
package eu.lp0.cursus.scoring.scores.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.LinkedListMultimap;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.scoring.data.OverallPositionData;
import eu.lp0.cursus.scoring.data.ScoredData;

public abstract class AbstractOverallPositionData<T extends ScoredData> implements OverallPositionData {
	protected final T scores;
	protected final Supplier<LinkedListMultimap<Integer, Pilot>> lazyOverallPositionsWithOrder = Suppliers
			.memoize(new Supplier<LinkedListMultimap<Integer, Pilot>>() {
				@Override
				public LinkedListMultimap<Integer, Pilot> get() {
					return calculateOverallPositionsWithOrder();
				}
			});
	protected final Supplier<Map<Pilot, Integer>> lazyOverallPositions = Suppliers.memoize(new Supplier<Map<Pilot, Integer>>() {
		@Override
		public Map<Pilot, Integer> get() {
			Map<Pilot, Integer> racePositions = new HashMap<Pilot, Integer>(scores.getPilots().size() * 2);
			for (Entry<Integer, Pilot> entry : lazyOverallPositionsWithOrder.get().entries()) {
				racePositions.put(entry.getValue(), entry.getKey());
			}
			return racePositions;
		}
	});

	public AbstractOverallPositionData(T scores) {
		this.scores = scores;
	}

	@Override
	public final Map<Pilot, Integer> getOverallPositions() {
		return lazyOverallPositions.get();
	}

	@Override
	public final LinkedListMultimap<Integer, Pilot> getOverallPositionsWithOrder() {
		return lazyOverallPositionsWithOrder.get();
	}

	@Override
	public final int getOverallPosition(Pilot pilot) {
		return lazyOverallPositions.get().get(pilot);
	}

	@Override
	public final List<Pilot> getOverallOrder() {
		return lazyOverallPositionsWithOrder.get().values();
	}

	protected abstract LinkedListMultimap<Integer, Pilot> calculateOverallPositionsWithOrder();
}