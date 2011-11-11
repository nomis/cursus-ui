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

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

public class GenericRacePointsData<T extends ScoredData & RaceLapsData & RacePenaltiesData> extends AbstractRacePointsData<T> {
	public GenericRacePointsData(T scores) {
		super(scores);
	}

	@Override
	public Map<Pilot, Integer> getRacePoints(Race race) {
		Map<Pilot, Integer> racePoints = new HashMap<Pilot, Integer>();
		List<Pilot> lapOrder = scores.getLapOrder(race);

		// Score everyone who completed a lap
		int points = 0;
		for (Pilot pilot : lapOrder) {
			racePoints.put(pilot, points + scores.getRacePenalties(pilot, race));
			points += (points == 0) ? 2 : 1;
		}

		// Score everyone else
		for (Pilot pilot : scores.getPilots()) {
			if (!racePoints.containsKey(pilot)) {
				// It is possible to have penalties but no laps
				racePoints.put(pilot, getPointsForNoLaps(pilot, race) + scores.getRacePenalties(pilot, race));
			}
		}

		return Collections.unmodifiableMap(racePoints);
	}

	protected int getPointsForNoLaps(Pilot pilot, Race race) {
		return race.getAttendees().size() + 1;
	}
}