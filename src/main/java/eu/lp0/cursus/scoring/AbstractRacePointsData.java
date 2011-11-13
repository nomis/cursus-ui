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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Predicates;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

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
	protected final Supplier<Map<Race, Collection<Pilot>>> lazySimulatedRacePointsByRace = Suppliers.memoize(new Supplier<Map<Race, Collection<Pilot>>>() {
		@Override
		public Map<Race, Collection<Pilot>> get() {
			Table<Pilot, Race, Boolean> simulatedRacePoints = lazySimulatedRacePoints.get();
			Map<Race, Collection<Pilot>> simulatedRacePointsByRace = new HashMap<Race, Collection<Pilot>>(scores.getRaces().size() * 2);
			for (Race race : scores.getRaces()) {
				simulatedRacePointsByRace.put(race, Maps.filterValues(simulatedRacePoints.column(race), Predicates.equalTo(true)).keySet());
			}
			return simulatedRacePointsByRace;
		}
	});
	protected final Supplier<Map<Pilot, Collection<Race>>> lazySimulatedRacePointsByPilot = Suppliers.memoize(new Supplier<Map<Pilot, Collection<Race>>>() {
		@Override
		public Map<Pilot, Collection<Race>> get() {
			Table<Pilot, Race, Boolean> simulatedRacePoints = lazySimulatedRacePoints.get();
			Map<Pilot, Collection<Race>> simulatedRacePointsByPilot = new HashMap<Pilot, Collection<Race>>(scores.getPilots().size() * 2);
			for (Pilot pilot : scores.getPilots()) {
				simulatedRacePointsByPilot.put(pilot, Maps.filterValues(simulatedRacePoints.row(pilot), Predicates.equalTo(true)).keySet());
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
	public final Map<Race, Collection<Pilot>> getSimulatedRacePoints() {
		return lazySimulatedRacePointsByRace.get();
	}

	@Override
	public final Map<Pilot, Collection<Race>> getSimulatedPilotPoints() {
		return lazySimulatedRacePointsByPilot.get();
	}

	@Override
	public final boolean hasSimulatedRacePoints(Pilot pilot, Race race) {
		return lazySimulatedRacePoints.get().get(pilot, race);
	}

	@Override
	public final Collection<Race> getSimulatedRacePoints(Pilot pilot) {
		return lazySimulatedRacePointsByPilot.get().get(pilot);
	}

	@Override
	public final Collection<Pilot> getSimulatedRacePoints(Race race) {
		return lazySimulatedRacePointsByRace.get().get(race);
	}

	protected abstract Map<Pilot, Integer> calculateRacePoints(Race race);

	protected abstract boolean calculateSimulatedRacePoints(Pilot pilot, Race race);
}