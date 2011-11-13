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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Table;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

public abstract class AbstractRacePositionsData<T extends ScoredData> implements RacePositionsData {
	protected final T scores;
	protected final Supplier<Map<Race, LinkedListMultimap<Integer, Pilot>>> lazyRacePositionsWithOrder = Suppliers
			.memoize(new Supplier<Map<Race, LinkedListMultimap<Integer, Pilot>>>() {
				@Override
				public Map<Race, LinkedListMultimap<Integer, Pilot>> get() {
					Map<Race, LinkedListMultimap<Integer, Pilot>> raceOrders = new HashMap<Race, LinkedListMultimap<Integer, Pilot>>(
							scores.getRaces().size() * 2);
					for (Race race : scores.getRaces()) {
						raceOrders.put(race, calculateRacePositionsWithOrder(race));
					}
					return raceOrders;
				}
			});
	protected final Supplier<Table<Race, Pilot, Integer>> lazyRacePositions = Suppliers.memoize(new Supplier<Table<Race, Pilot, Integer>>() {
		@Override
		public Table<Race, Pilot, Integer> get() {
			Table<Race, Pilot, Integer> racePositions = ArrayTable.create(scores.getRaces(), scores.getPilots());
			Map<Race, LinkedListMultimap<Integer, Pilot>> racePositionsWithOrder = lazyRacePositionsWithOrder.get();
			for (Map.Entry<Race, LinkedListMultimap<Integer, Pilot>> raceEntry : racePositionsWithOrder.entrySet()) {
				for (Map.Entry<Integer, Pilot> positionEntry : raceEntry.getValue().entries()) {
					racePositions.put(raceEntry.getKey(), positionEntry.getValue(), positionEntry.getKey());
				}
			}
			return racePositions;
		}
	});
	protected final Supplier<Map<Race, List<Pilot>>> lazyRaceOrders = Suppliers.memoize(new Supplier<Map<Race, List<Pilot>>>() {
		@Override
		public Map<Race, List<Pilot>> get() {
			Map<Race, List<Pilot>> raceOrders = new HashMap<Race, List<Pilot>>(scores.getRaces().size() * 2);
			Map<Race, LinkedListMultimap<Integer, Pilot>> racePositionsWithOrder = lazyRacePositionsWithOrder.get();
			for (Map.Entry<Race, LinkedListMultimap<Integer, Pilot>> raceEntry : racePositionsWithOrder.entrySet()) {
				raceOrders.put(raceEntry.getKey(), raceEntry.getValue().values());
			}
			return raceOrders;
		}
	});

	public AbstractRacePositionsData(T scores) {
		this.scores = scores;
	}

	@Override
	public final Table<Race, Pilot, Integer> getRacePositions() {
		return lazyRacePositions.get();
	}

	@Override
	public final Map<Pilot, Integer> getRacePositions(Race race) {
		return lazyRacePositions.get().row(race);
	}

	@Override
	public final Map<Race, LinkedListMultimap<Integer, Pilot>> getRacePositionsWithOrder() {
		return lazyRacePositionsWithOrder.get();
	}

	@Override
	public final LinkedListMultimap<Integer, Pilot> getRacePositionsWithOrder(Race race) {
		return lazyRacePositionsWithOrder.get().get(race);
	}

	@Override
	public final int getRacePosition(Pilot pilot, Race race) {
		return lazyRacePositions.get().get(race, pilot);
	}

	@Override
	public final Map<Race, List<Pilot>> getRaceOrders() {
		return lazyRaceOrders.get();
	}

	@Override
	public final List<Pilot> getRaceOrder(Race race) {
		return lazyRaceOrders.get().get(race);
	}

	protected abstract LinkedListMultimap<Integer, Pilot> calculateRacePositionsWithOrder(Race race);
}