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

import eu.lp0.cursus.scoring.GenericOverallPointsData;
import eu.lp0.cursus.scoring.OverallPenaltiesData;
import eu.lp0.cursus.scoring.RaceDiscardsData;
import eu.lp0.cursus.scoring.RacePointsData;
import eu.lp0.cursus.scoring.ScoredData;

public class OverallPointsData2011<T extends ScoredData & RacePointsData & RaceDiscardsData & OverallPenaltiesData> extends GenericOverallPointsData<T> {
	public OverallPointsData2011(T scores) {
		super(scores);
	}

	@Override
	protected int calculateOverallFleetSize() {
		assert (!scores.getRaces().isEmpty());
		return Scorer2011.calculateFleetSize(scores.getRaces().get(0).getEvent().getSeries());
	}
}