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

import java.util.List;
import java.util.Map;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;

import eu.lp0.cursus.db.data.Penalty;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

public abstract class AbstractRacePenaltiesData<T extends ScoredData> implements RacePenaltiesData {
	protected final T scores;
	protected final Supplier<Table<Pilot, Race, Integer>> lazyRacePenalties = Suppliers.memoize(new Supplier<Table<Pilot, Race, Integer>>() {
		@Override
		public Table<Pilot, Race, Integer> get() {
			Table<Pilot, Race, Integer> racePenalties = ArrayTable.create(scores.getPilots(), scores.getRaces());
			for (Race race : scores.getRaces()) {
				for (Pilot pilot : scores.getPilots()) {
					racePenalties.put(pilot, race, calculateRacePenalties(pilot, race));
				}
			}
			return racePenalties;
		}
	});
	protected final Supplier<Table<Pilot, Race, List<Penalty>>> lazySimulatedRacePenalties = Suppliers
			.memoize(new Supplier<Table<Pilot, Race, List<Penalty>>>() {
				@Override
				public Table<Pilot, Race, List<Penalty>> get() {
					Table<Pilot, Race, List<Penalty>> simulatedRacePenalties = ArrayTable.create(scores.getPilots(), scores.getRaces());
					for (Race race : scores.getRaces()) {
						for (Pilot pilot : scores.getPilots()) {
							simulatedRacePenalties.put(pilot, race, calculateSimulatedRacePenalties(pilot, race));
						}
					}
					return simulatedRacePenalties;
				}
			});

	public AbstractRacePenaltiesData(T scores) {
		this.scores = scores;
	}

	@Override
	public final Table<Pilot, Race, Integer> getRacePenalties() {
		return lazyRacePenalties.get();
	}

	@Override
	public final Map<Race, Integer> getRacePenalties(Pilot pilot) {
		return lazyRacePenalties.get().row(pilot);
	}

	@Override
	public final Map<Pilot, Integer> getRacePenalties(Race race) {
		return lazyRacePenalties.get().column(race);
	}

	@Override
	public final int getRacePenalties(Pilot pilot, Race race) {
		return lazyRacePenalties.get().get(pilot, race);
	}

	public Table<Pilot, Race, List<Penalty>> getSimulatedRacePenalties() {
		return lazySimulatedRacePenalties.get();
	}

	public Map<Race, List<Penalty>> getSimulatedRacePenalties(Pilot pilot) {
		return lazySimulatedRacePenalties.get().row(pilot);
	}

	public Map<Pilot, List<Penalty>> getSimulatedRacePenalties(Race race) {
		return lazySimulatedRacePenalties.get().column(race);
	}

	public List<Penalty> getSimulatedRacePenalties(Pilot pilot, Race race) {
		return lazySimulatedRacePenalties.get().get(pilot, race);
	}

	protected abstract int calculateRacePenalties(Pilot pilot, Race race);

	protected abstract List<Penalty> calculateSimulatedRacePenalties(Pilot pilot, Race race);
}