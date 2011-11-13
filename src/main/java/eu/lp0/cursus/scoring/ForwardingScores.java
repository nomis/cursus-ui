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

public abstract class ForwardingScores extends AbstractForwardingScores {
	protected abstract Scores delegate();

	@Override
	protected ScoredData delegateScoredData() {
		return delegate();
	}

	@Override
	protected ScoresFactorySubset delegateScoresFactory() {
		return delegate();
	}

	@Override
	protected RaceLapsData delegateRaceLapsData() {
		return delegate();
	}

	@Override
	protected RacePointsData delegateRacePointsData() {
		return delegate();
	}

	@Override
	protected RacePenaltiesData delegateRacePenaltiesData() {
		return delegate();
	}

	@Override
	protected RacePositionsData delegateRacePositionsData() {
		return delegate();
	}

	@Override
	protected RaceDiscardsData delegateRaceDiscardsData() {
		return delegate();
	}

	@Override
	protected OverallPenaltiesData delegateOverallPenaltiesData() {
		return delegate();
	}

	@Override
	protected OverallPointsData delegateOverallPointsData() {
		return delegate();
	}

	@Override
	protected OverallPositionData delegateOverallPositionData() {
		return delegate();
	}
}