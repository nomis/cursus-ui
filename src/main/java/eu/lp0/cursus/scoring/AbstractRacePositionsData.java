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
import eu.lp0.cursus.db.data.Race;

public abstract class AbstractRacePositionsData<T extends ScoredData> implements RacePositionsData {
	protected final T scores;

	public AbstractRacePositionsData(T scores) {
		this.scores = scores;
	}

	@Override
	public Map<Race, Map<Pilot, Integer>> getRacePositions() {
		Map<Race, Map<Pilot, Integer>> racePositions = new HashMap<Race, Map<Pilot, Integer>>();
		for (Race race : scores.getRaces()) {
			racePositions.put(race, getRacePositions(race));
		}
		return Collections.unmodifiableMap(racePositions);
	}

	@Override
	public Map<Pilot, Integer> getRacePositions(Race race) {
		Map<Pilot, Integer> racePositions = new HashMap<Pilot, Integer>();
		for (Entry<Integer, Pilot> entry : getRacePositionsWithOrder(race).entries()) {
			racePositions.put(entry.getValue(), entry.getKey());
		}
		return Collections.unmodifiableMap(racePositions);
	}

	@Override
	public Map<Race, LinkedListMultimap<Integer, Pilot>> getRacePositionsWithOrder() {
		Map<Race, LinkedListMultimap<Integer, Pilot>> racePositions = new HashMap<Race, LinkedListMultimap<Integer, Pilot>>();
		for (Race race : scores.getRaces()) {
			racePositions.put(race, getRacePositionsWithOrder(race));
		}
		return Collections.unmodifiableMap(racePositions);
	}

	@Override
	public int getRacePosition(Pilot pilot, Race race) {
		Multimap<Pilot, Integer> inverted = HashMultimap.create();
		Multimaps.invertFrom(getRacePositionsWithOrder(race), inverted);
		return inverted.get(pilot).iterator().next();
	}

	@Override
	public Map<Race, List<Pilot>> getRaceOrders() {
		Map<Race, List<Pilot>> raceOrders = new HashMap<Race, List<Pilot>>();
		for (Race race : scores.getRaces()) {
			raceOrders.put(race, getRaceOrder(race));
		}
		return Collections.unmodifiableMap(raceOrders);
	}

	@Override
	public List<Pilot> getRaceOrder(Race race) {
		return Collections.unmodifiableList(getRacePositionsWithOrder(race).values());
	}

	@Override
	public abstract LinkedListMultimap<Integer, Pilot> getRacePositionsWithOrder(Race race);
}