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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

public class GenericRacePointsData<T extends ScoredData & RaceLapsData> extends AbstractRacePointsData<T> {
	public GenericRacePointsData(T scores) {
		super(scores);
	}

	@Override
	protected Map<Pilot, Integer> calculateRacePoints(Race race) {
		Map<Pilot, Integer> racePoints = new HashMap<Pilot, Integer>();
		List<Pilot> lapOrder = scores.getLapOrder(race);

		// Score everyone who completed a lap
		int points = 0;
		for (Pilot pilot : lapOrder) {
			racePoints.put(pilot, points);
			points += (points == 0) ? 2 : 1;
		}

		// Score everyone else
		for (Pilot pilot : scores.getPilots()) {
			if (!racePoints.containsKey(pilot)) {
				racePoints.put(pilot, getPointsForNoLaps(pilot, race));
			}
		}

		return racePoints;
	}

	@Override
	protected boolean calculateSimulatedRacePoints(Pilot pilot, Race race) {
		return false;
	}

	protected int getPointsForNoLaps(Pilot pilot, Race race) {
		return getFleetSize(race) + 1;
	}

	public int getFleetSize(Race race) {
		return race.getAttendees().size();
	}
}