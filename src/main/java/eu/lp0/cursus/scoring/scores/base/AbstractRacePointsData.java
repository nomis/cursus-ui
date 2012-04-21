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
import java.util.Map;
import java.util.Set;

import com.google.common.base.Predicates;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.data.RacePointsData;
import eu.lp0.cursus.scoring.data.ScoredData;

public abstract class AbstractRacePointsData<T extends ScoredData> implements RacePointsData {
	protected final T scores;
	protected final Supplier<Table<Pilot, Race, Integer>> lazyRacePoints = Suppliers.memoize(new Supplier<Table<Pilot, Race, Integer>>() {
		@Override
		public Table<Pilot, Race, Integer> get() {
			Table<Pilot, Race, Integer> racePoints = ArrayTable.create(scores.getPilots(), scores.getRaces());
			for (Race race : scores.getRaces()) {
				racePoints.column(race).putAll(calculateRacePoints(race));
			}
			return racePoints;
		}
	});
	protected final Supplier<Table<Pilot, Race, Boolean>> lazySimulatedRacePoints = Suppliers.memoize(new Supplier<Table<Pilot, Race, Boolean>>() {
		@Override
		public Table<Pilot, Race, Boolean> get() {
			Table<Pilot, Race, Boolean> simulatedRacePoints = ArrayTable.create(scores.getPilots(), scores.getRaces());
			for (Pilot pilot : scores.getPilots()) {
				for (Race race : scores.getRaces()) {
					simulatedRacePoints.row(pilot).put(race, calculateSimulatedRacePoints(pilot, race));
				}
			}
			return simulatedRacePoints;
		}
	});
	protected final Supplier<Map<Race, Set<Pilot>>> lazySimulatedRacePointsByRace = Suppliers.memoize(new Supplier<Map<Race, Set<Pilot>>>() {
		@Override
		public Map<Race, Set<Pilot>> get() {
			Table<Pilot, Race, Boolean> simulatedRacePoints = lazySimulatedRacePoints.get();
			Map<Race, Set<Pilot>> simulatedRacePointsByRace = new HashMap<Race, Set<Pilot>>(scores.getRaces().size() * 2);
			for (Race race : scores.getRaces()) {
				simulatedRacePointsByRace.put(race, ImmutableSet.copyOf(Maps.filterValues(simulatedRacePoints.column(race), Predicates.equalTo(true)).keySet()));
			}
			return simulatedRacePointsByRace;
		}
	});
	protected final Supplier<Map<Pilot, Set<Race>>> lazySimulatedRacePointsByPilot = Suppliers.memoize(new Supplier<Map<Pilot, Set<Race>>>() {
		@Override
		public Map<Pilot, Set<Race>> get() {
			Table<Pilot, Race, Boolean> simulatedRacePoints = lazySimulatedRacePoints.get();
			Map<Pilot, Set<Race>> simulatedRacePointsByPilot = new HashMap<Pilot, Set<Race>>(scores.getPilots().size() * 2);
			for (Pilot pilot : scores.getPilots()) {
				simulatedRacePointsByPilot.put(pilot, ImmutableSet.copyOf(Maps.filterValues(simulatedRacePoints.row(pilot), Predicates.equalTo(true)).keySet()));
			}
			return simulatedRacePointsByPilot;
		}
	});

	public AbstractRacePointsData(T scores) {
		this.scores = scores;
	}

	@Override
	public final Table<Pilot, Race, Integer> getRacePoints() {
		return lazyRacePoints.get();
	}

	@Override
	public final int getRacePoints(Pilot pilot, Race race) {
		return lazyRacePoints.get().get(pilot, race);
	}

	@Override
	public final Map<Race, Integer> getRacePoints(Pilot pilot) {
		return lazyRacePoints.get().row(pilot);
	}

	@Override
	public final Map<Pilot, Integer> getRacePoints(Race race) {
		return lazyRacePoints.get().column(race);
	}

	@Override
	public final Map<Race, ? extends Set<Pilot>> getSimulatedRacePoints() {
		return lazySimulatedRacePointsByRace.get();
	}

	@Override
	public final Map<Pilot, ? extends Set<Race>> getSimulatedPilotPoints() {
		return lazySimulatedRacePointsByPilot.get();
	}

	@Override
	public final boolean hasSimulatedRacePoints(Pilot pilot, Race race) {
		return lazySimulatedRacePoints.get().get(pilot, race);
	}

	@Override
	public final Set<Race> getSimulatedRacePoints(Pilot pilot) {
		return lazySimulatedRacePointsByPilot.get().get(pilot);
	}

	@Override
	public final Set<Pilot> getSimulatedRacePoints(Race race) {
		return lazySimulatedRacePointsByRace.get().get(race);
	}

	protected abstract Map<Pilot, Integer> calculateRacePoints(Race race);

	protected abstract boolean calculateSimulatedRacePoints(Pilot pilot, Race race);
}