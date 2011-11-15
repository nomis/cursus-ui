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

import eu.lp0.cursus.scoring.AbstractScoresFactory;
import eu.lp0.cursus.scoring.AveragingRacePointsData;
import eu.lp0.cursus.scoring.GenericDiscardCalculator;
import eu.lp0.cursus.scoring.GenericOverallPenaltiesData;
import eu.lp0.cursus.scoring.GenericOverallPointsData;
import eu.lp0.cursus.scoring.GenericOverallPositionData;
import eu.lp0.cursus.scoring.GenericRaceDiscardsData;
import eu.lp0.cursus.scoring.GenericRaceLapsData;
import eu.lp0.cursus.scoring.GenericRacePenaltiesData;
import eu.lp0.cursus.scoring.GenericRacePointsData;
import eu.lp0.cursus.scoring.GenericRacePositionsData;
import eu.lp0.cursus.scoring.OverallPenaltiesData;
import eu.lp0.cursus.scoring.OverallPointsData;
import eu.lp0.cursus.scoring.OverallPositionData;
import eu.lp0.cursus.scoring.PilotRacePlacingComparator;
import eu.lp0.cursus.scoring.RaceDiscardsData;
import eu.lp0.cursus.scoring.RaceLapsData;
import eu.lp0.cursus.scoring.RacePenaltiesData;
import eu.lp0.cursus.scoring.RacePointsData;
import eu.lp0.cursus.scoring.RacePositionsData;
import eu.lp0.cursus.scoring.Scores;

public class ScoresFactory2010 extends AbstractScoresFactory {
	@Override
	public RaceLapsData newRaceLapsData(Scores scores) {
		return new GenericRaceLapsData<Scores>(scores, false, true);
	}

	@Override
	public RacePenaltiesData newRacePenaltiesData(Scores scores) {
		return new GenericRacePenaltiesData<Scores>(scores);
	}

	@Override
	public RacePointsData newRacePointsData(Scores scores) {
		return new AveragingRacePointsData<Scores>(scores, GenericRacePointsData.FleetMethod.RACE, AveragingRacePointsData.AveragingMethod.AFTER_DISCARDS,
				AveragingRacePointsData.Rounding.ROUND_HALF_UP);
	}

	@Override
	public RacePositionsData newRacePositionsData(Scores scores) {
		return new GenericRacePositionsData<Scores>(scores, GenericRacePositionsData.EqualPositioning.WITHOUT_LAPS);
	}

	@Override
	public RaceDiscardsData newRaceDiscardsData(Scores scores) {
		return new GenericRaceDiscardsData<Scores>(scores, new GenericDiscardCalculator(SPKAConstants.RACES_PER_DISCARD));
	}

	@Override
	public OverallPenaltiesData newOverallPenaltiesData(Scores scores) {
		return new GenericOverallPenaltiesData<Scores>(scores);
	}

	@Override
	public OverallPointsData newOverallPointsData(Scores scores) {
		return new GenericOverallPointsData<Scores>(scores);
	}

	@Override
	public OverallPositionData newOverallPositionData(Scores scores) {
		return new GenericOverallPositionData<Scores>(scores, GenericOverallPositionData.EqualPositioning.IF_REQUIRED,
				PilotRacePlacingComparator.PlacingMethod.EXCLUDING_DISCARDS);
	}
}