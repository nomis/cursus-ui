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

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

import eu.lp0.cursus.db.data.Penalty;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.scoring.data.OverallPenaltiesData;
import eu.lp0.cursus.scoring.data.ScoredData;

public abstract class AbstractOverallPenaltiesData<T extends ScoredData> implements OverallPenaltiesData {
	protected final T scores;
	protected final Supplier<Map<Pilot, Integer>> lazyOverallPenalties = Suppliers.memoize(new Supplier<Map<Pilot, Integer>>() {
		@Override
		public Map<Pilot, Integer> get() {
			Map<Pilot, Integer> overallPenalties = new HashMap<Pilot, Integer>(scores.getPilots().size() * 2);
			for (Pilot pilot : scores.getPilots()) {
				overallPenalties.put(pilot, calculateOverallPenalties(pilot));
			}
			return overallPenalties;
		}
	});
	protected final Supplier<Map<Pilot, List<Penalty>>> lazySimulatedOverallPenalties = Suppliers.memoize(new Supplier<Map<Pilot, List<Penalty>>>() {
		@Override
		public Map<Pilot, List<Penalty>> get() {
			Map<Pilot, List<Penalty>> simulatedOverallPenalties = new HashMap<Pilot, List<Penalty>>(scores.getPilots().size() * 2);
			for (Pilot pilot : scores.getPilots()) {
				simulatedOverallPenalties.put(pilot, calculateSimulatedOverallPenalties(pilot));
			}
			return simulatedOverallPenalties;
		}
	});

	public AbstractOverallPenaltiesData(T scores) {
		this.scores = scores;
	}

	@Override
	public final Map<Pilot, Integer> getOverallPenalties() {
		return lazyOverallPenalties.get();
	}

	@Override
	public final int getOverallPenalties(Pilot pilot) {
		return lazyOverallPenalties.get().get(pilot);
	}

	@Override
	public Map<Pilot, ? extends List<Penalty>> getSimulatedOverallPenalties() {
		return lazySimulatedOverallPenalties.get();
	}

	@Override
	public List<Penalty> getSimulatedOverallPenalties(Pilot pilot) {
		return lazySimulatedOverallPenalties.get().get(pilot);
	}

	protected abstract int calculateOverallPenalties(Pilot pilot);

	protected abstract List<Penalty> calculateSimulatedOverallPenalties(Pilot pilot);
}