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
import java.util.HashSet;
import java.util.Map;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Table;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

public abstract class AbstractRacePointsData<T extends ScoredData> implements RacePointsData {
	protected final T scores;

	public AbstractRacePointsData(T scores) {
		this.scores = scores;
	}

	@Override
	public Table<Pilot, Race, Integer> getRacePoints() {
		Table<Pilot, Race, Integer> racePoints = ArrayTable.create(scores.getPilots(), scores.getRaces());
		for (Race race : scores.getRaces()) {
			racePoints.column(race).putAll(getRacePoints(race));
		}
		return racePoints;
	}

	@Override
	public int getRacePoints(Pilot pilot, Race race) {
		return getRacePoints(race).get(pilot);
	}

	@Override
	public Map<Race, Integer> getRacePoints(Pilot pilot) {
		Map<Race, Integer> racePoints = new HashMap<Race, Integer>();
		for (Race race : scores.getRaces()) {
			racePoints.put(race, getRacePoints(pilot, race));
		}
		return racePoints;
	}

	@Override
	public Map<Race, Collection<Pilot>> getSimulatedRacePoints() {
		SetMultimap<Pilot, Race> simulatedRacePoints = HashMultimap.create();
		for (Pilot pilot : scores.getPilots()) {
			simulatedRacePoints.putAll(pilot, getSimulatedRacePoints(pilot));
		}

		Multimap<Race, Pilot> invSimulatedRacePoints = HashMultimap.create();
		Multimaps.invertFrom(simulatedRacePoints, invSimulatedRacePoints);
		return invSimulatedRacePoints.asMap();
	}

	@Override
	public Collection<Race> getSimulatedRacePoints(Pilot pilot) {
		Collection<Race> simulatedRacePoints = new HashSet<Race>();
		for (Race race : scores.getRaces()) {
			if (hasSimulatedRacePoints(pilot, race)) {
				simulatedRacePoints.add(race);
			}
		}
		return simulatedRacePoints;
	}

	@Override
	public Collection<Pilot> getSimulatedRacePoints(Race race) {
		Collection<Pilot> simulatedRacePoints = new HashSet<Pilot>();
		for (Pilot pilot : scores.getPilots()) {
			if (hasSimulatedRacePoints(pilot, race)) {
				simulatedRacePoints.add(pilot);
			}
		}
		return simulatedRacePoints;
	}

	@Override
	public abstract Map<Pilot, Integer> getRacePoints(Race race);

	@Override
	public abstract boolean hasSimulatedRacePoints(Pilot pilot, Race race);
}