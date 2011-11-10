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
import java.util.Map;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

public abstract class AbstractRacePositionData<T extends ScoredData> implements RacePositionData {
	protected final T scores;

	public AbstractRacePositionData(T scores) {
		this.scores = scores;
	}

	@Override
	public Table<Pilot, Race, Integer> getRacePosition() {
		Table<Pilot, Race, Integer> racePosition = ArrayTable.create(scores.getPilots(), scores.getRaces());
		for (Pilot pilot : scores.getPilots()) {
			racePosition.row(pilot).putAll(getRacePosition(pilot));
		}
		return racePosition;
	}

	@Override
	public Map<Race, Integer> getRacePosition(Pilot pilot) {
		Map<Race, Integer> racePosition = new HashMap<Race, Integer>();
		for (Race race : scores.getRaces()) {
			racePosition.put(race, getRacePosition(pilot, race));
		}
		return Collections.unmodifiableMap(racePosition);
	}

	@Override
	public Map<Pilot, Integer> getRacePosition(Race race) {
		Map<Pilot, Integer> racePosition = new HashMap<Pilot, Integer>();
		for (Pilot pilot : scores.getPilots()) {
			racePosition.put(pilot, getRacePosition(pilot, race));
		}
		return Collections.unmodifiableMap(racePosition);
	}

	@Override
	public abstract Integer getRacePosition(Pilot pilot, Race race);
}