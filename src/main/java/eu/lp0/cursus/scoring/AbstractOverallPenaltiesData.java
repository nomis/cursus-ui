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
import java.util.Map;

import eu.lp0.cursus.db.data.Pilot;

public abstract class AbstractOverallPenaltiesData<T extends ScoredData> implements OverallPenaltiesData {
	protected final T scores;

	public AbstractOverallPenaltiesData(T scores) {
		this.scores = scores;
	}

	@Override
	public Map<Pilot, Integer> getOverallPenalties() {
		Map<Pilot, Integer> overallPenalties = new HashMap<Pilot, Integer>();
		for (Pilot pilot : scores.getPilots()) {
			overallPenalties.put(pilot, getOverallPenalties(pilot));
		}
		return overallPenalties;
	}

	@Override
	public abstract int getOverallPenalties(Pilot pilot);
}