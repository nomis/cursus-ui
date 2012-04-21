/*
	cursus - Race series management program
	Copyright 2012  Simon Arlott

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


/**
 * Scores needs to extend {@code ScoresFactorySubset} so that we can get a new instance of {@code RaceDiscardsData} from within {@code AveragingRacePointsData}
 * and run the race discards calculation on the pre-averaged data (avoiding infinite recursion)
 * 
 * We don't extend {@code ScoresFactory} because creating a new {@code Scores} that uses a {@code ForwardingScoresFactory} is impossible (the method doesn't
 * supply a factory to be intercepted)
 */
public interface Scores extends ScoredData, ScoresFactorySubset, RaceLapsData, RacePointsData, RacePenaltiesData, RacePositionsData, RaceDiscardsData,
		OverallPenaltiesData, OverallPointsData, OverallPositionData {
}