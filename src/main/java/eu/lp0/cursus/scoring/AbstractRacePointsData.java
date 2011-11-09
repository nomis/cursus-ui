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

public abstract class AbstractRacePointsData implements RacePointsData {
	protected final Scores scores;

	public AbstractRacePointsData(Scores scores) {
		this.scores = scores;
	}

	@Override
	public Table<Pilot, Race, Integer> getRacePoints() {
		Table<Pilot, Race, Integer> racePoints = ArrayTable.create(scores.getPilots(), scores.getRaces());
		for (Pilot pilot : scores.getPilots()) {
			racePoints.row(pilot).putAll(getRacePoints(pilot));
		}
		return racePoints;
	}

	@Override
	public Map<Race, Integer> getRacePoints(Pilot pilot) {
		Map<Race, Integer> racePoints = new HashMap<Race, Integer>();
		for (Race race : scores.getRaces()) {
			racePoints.put(race, getRacePoints(pilot, race));
		}
		return Collections.unmodifiableMap(racePoints);
	}

	@Override
	public Map<Pilot, Integer> getRacePoints(Race race) {
		Map<Pilot, Integer> racePoints = new HashMap<Pilot, Integer>();
		for (Pilot pilot : scores.getPilots()) {
			racePoints.put(pilot, getRacePoints(pilot, race));
		}
		return Collections.unmodifiableMap(racePoints);
	}

	@Override
	public abstract Integer getRacePoints(Pilot pilot, Race race);
}