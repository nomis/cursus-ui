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

import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Ordering;
import com.google.common.collect.Table;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

public class PilotRacePlacingComparator implements Comparator<Pilot> {
	private final Table<Pilot, Race, Integer> racePoints;

	public PilotRacePlacingComparator(Table<Pilot, Race, Integer> racePoints) {
		this.racePoints = racePoints;
	}

	private List<Integer> getRacePlacing(Pilot pilot) {
		return Ordering.natural().sortedCopy(racePoints.row(pilot).values());
	}

	@Override
	public int compare(Pilot o1, Pilot o2) {
		return Ordering.<Integer> natural().lexicographical().compare(getRacePlacing(o1), getRacePlacing(o2));
	}
}
