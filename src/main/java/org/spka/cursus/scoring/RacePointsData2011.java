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
package org.spka.cursus.scoring;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.GenericRacePointsData;
import eu.lp0.cursus.scoring.RaceLapsData;
import eu.lp0.cursus.scoring.RacePenaltiesData;
import eu.lp0.cursus.scoring.ScoredData;

public class RacePointsData2011<T extends ScoredData & RaceLapsData & RacePenaltiesData> extends GenericRacePointsData<T> {
	public RacePointsData2011(T scores) {
		super(scores);
	}

	@Override
	protected int getPointsForNoLaps(Pilot pilot, Race race) {
		int fleet = race.getEvent().getSeries().getPilots().size();
		if (race.getAttendees().containsKey(pilot)) {
			return fleet + 1;
		} else {
			return fleet + 2;
		}
	}
}