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

import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.util.IntegerSequence;

public abstract class AbstractRaceDiscardsData<T extends ScoredData & RacePointsData> implements RaceDiscardsData {
	protected final T scores;
	protected final int discards;
	protected final Supplier<Table<Pilot, Integer, Race>> lazyDiscardedRaces = Suppliers.memoize(new Supplier<Table<Pilot, Integer, Race>>() {
		@Override
		public Table<Pilot, Integer, Race> get() {
			// The first column is just nulls, as Tables must have at least one column
			Table<Pilot, Integer, Race> discardedRaces = ArrayTable.create(scores.getPilots(), new IntegerSequence(0, discards));
			for (Pilot pilot : scores.getPilots()) {
				discardedRaces.row(pilot).putAll(calculateDiscardedRaces(pilot));
			}
			return discardedRaces;
		}
	});
	protected final Supplier<Table<Pilot, Integer, Integer>> lazyRaceDiscards = Suppliers.memoize(new Supplier<Table<Pilot, Integer, Integer>>() {
		@Override
		public Table<Pilot, Integer, Integer> get() {
			// The first column is just nulls, as Tables must have at least one column
			Table<Pilot, Integer, Integer> pilotDiscards = ArrayTable.create(scores.getPilots(), new IntegerSequence(0, discards));
			Table<Pilot, Race, Integer> racePoints = scores.getRacePoints();
			Table<Pilot, Integer, Race> discardedRaces = lazyDiscardedRaces.get();
			for (Pilot pilot : scores.getPilots()) {
				for (int discard = 1; discard <= discards; discard++) {
					pilotDiscards.put(pilot, discard, racePoints.get(pilot, discardedRaces.get(pilot, discard)));
				}
			}
			return pilotDiscards;
		}
	});

	public AbstractRaceDiscardsData(T scores, int discards) {
		Preconditions.checkArgument(discards >= 0);

		this.scores = scores;
		this.discards = discards;
	}

	@Override
	public final Table<Pilot, Integer, Integer> getRaceDiscards() {
		return lazyRaceDiscards.get();
	}

	@Override
	public final Map<Integer, Integer> getRaceDiscards(Pilot pilot) {
		return lazyRaceDiscards.get().row(pilot);
	}

	@Override
	public final Map<Pilot, Integer> getRaceDiscards(int discard) {
		Preconditions.checkArgument(discards > 0);
		Preconditions.checkElementIndex(discard, discards);
		return lazyRaceDiscards.get().column(discard);
	}

	@Override
	public final int getRaceDiscard(Pilot pilot, int discard) {
		Preconditions.checkArgument(discards > 0);
		Preconditions.checkElementIndex(discard, discards);
		return lazyRaceDiscards.get().get(pilot, discard);
	}

	@Override
	public final Table<Pilot, Integer, Race> getDiscardedRaces() {
		return lazyDiscardedRaces.get();
	}

	@Override
	public final Map<Integer, Race> getDiscardedRaces(Pilot pilot) {
		return lazyDiscardedRaces.get().row(pilot);
	}

	protected abstract Map<Integer, Race> calculateDiscardedRaces(Pilot pilot);
}