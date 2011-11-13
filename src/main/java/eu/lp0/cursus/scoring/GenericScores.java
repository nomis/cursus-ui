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

import java.util.List;
import java.util.Set;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

public class GenericScores extends AbstractScores {
	protected final RaceLapsData raceLapsData;
	protected final RacePointsData racePointsData;
	protected final RacePenaltiesData racePenaltiesData;
	protected final RacePositionsData racePositionsData;
	protected final RaceDiscardsData raceDiscardsData;

	protected final OverallPenaltiesData overallPenaltiesData;
	protected final OverallPointsData overallPointsData;
	protected final OverallPositionData overallPositionData;

	public GenericScores(Set<Pilot> pilots, List<Race> races, ScoresFactory scoresFactory) {
		super(pilots, races, scoresFactory);

		raceLapsData = scoresFactory.newRaceLapsData(this);
		racePointsData = scoresFactory.newRacePointsData(this);
		racePenaltiesData = scoresFactory.newRacePenaltiesData(this);
		racePositionsData = scoresFactory.newRacePositionsData(this);
		raceDiscardsData = scoresFactory.newRaceDiscardsData(this);

		overallPenaltiesData = scoresFactory.newOverallPenaltiesData(this);
		overallPointsData = scoresFactory.newOverallPointsData(this);
		overallPositionData = scoresFactory.newOverallPositionData(this);
	}

	// ForwardingScores
	@Override
	protected RaceLapsData delegateRaceLapsData() {
		return raceLapsData;
	}

	@Override
	protected RacePointsData delegateRacePointsData() {
		return racePointsData;
	}

	@Override
	protected RacePenaltiesData delegateRacePenaltiesData() {
		return racePenaltiesData;
	}

	@Override
	protected RacePositionsData delegateRacePositionsData() {
		return racePositionsData;
	}

	@Override
	protected RaceDiscardsData delegateRaceDiscardsData() {
		return raceDiscardsData;
	}

	@Override
	protected OverallPenaltiesData delegateOverallPenaltiesData() {
		return overallPenaltiesData;
	}

	@Override
	protected OverallPointsData delegateOverallPointsData() {
		return overallPointsData;
	}

	@Override
	protected OverallPositionData delegateOverallPositionData() {
		return overallPositionData;
	}
}