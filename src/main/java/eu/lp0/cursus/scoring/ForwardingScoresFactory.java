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


public abstract class ForwardingScoresFactory implements ScoresFactory {
	protected abstract ScoresFactory delegate();

	@Override
	public RaceLapsData newRaceLapsData(Scores scores) {
		return delegate().newRaceLapsData(scores);
	}

	@Override
	public RacePenaltiesData newRacePenaltiesData(Scores scores) {
		return delegate().newRacePenaltiesData(scores);
	}

	@Override
	public RacePointsData newRacePointsData(Scores scores) {
		return delegate().newRacePointsData(scores);
	}

	@Override
	public RacePositionsData newRacePositionsData(Scores scores) {
		return delegate().newRacePositionsData(scores);
	}

	@Override
	public RaceDiscardsData newRaceDiscardsData(Scores scores) {
		return delegate().newRaceDiscardsData(scores);
	}

	@Override
	public OverallPenaltiesData newOverallPenaltiesData(Scores scores) {
		return delegate().newOverallPenaltiesData(scores);
	}

	@Override
	public OverallPointsData newOverallPointsData(Scores scores) {
		return delegate().newOverallPointsData(scores);
	}

	@Override
	public OverallPositionData newOverallPositionData(Scores scores) {
		return delegate().newOverallPositionData(scores);
	}
}