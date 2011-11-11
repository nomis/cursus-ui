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

import eu.lp0.cursus.scoring.GenericDiscardCalculator;
import eu.lp0.cursus.scoring.GenericOverallPositionData;
import eu.lp0.cursus.scoring.GenericRaceDiscardsData;
import eu.lp0.cursus.scoring.GenericRaceLapsData;
import eu.lp0.cursus.scoring.GenericRacePositionsData;
import eu.lp0.cursus.scoring.GenericScoresFactory;
import eu.lp0.cursus.scoring.OverallPositionData;
import eu.lp0.cursus.scoring.RaceDiscardsData;
import eu.lp0.cursus.scoring.RaceLapsData;
import eu.lp0.cursus.scoring.RacePositionsData;
import eu.lp0.cursus.scoring.Scores;

public class ScoresFactory2006 extends GenericScoresFactory {
	@Override
	public RaceLapsData newRaceLapsData(Scores scores) {
		return new GenericRaceLapsData<Scores>(scores, false, true);
	}

	@Override
	public RaceDiscardsData newRaceDiscardsData(Scores scores) {
		return new GenericRaceDiscardsData<Scores>(scores, new GenericDiscardCalculator(SPKAConstants.RACES_PER_DISCARD));
	}

	@Override
	public RacePositionsData newRacePositionsData(Scores scores) {
		return new GenericRacePositionsData<Scores>(scores, GenericRacePositionsData.EqualPositioning.ALWAYS);
	}

	@Override
	public OverallPositionData newOverallPositionData(Scores scores) {
		return new GenericOverallPositionData<Scores>(scores, GenericOverallPositionData.EqualPositioning.IF_REQUIRED);
	}
}