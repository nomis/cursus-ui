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

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

import eu.lp0.cursus.db.data.Pilot;

public class PilotRaceNumberComparator implements Comparator<Pilot> {
	@Override
	public int compare(Pilot o1, Pilot o2) {
		return ComparisonChain.start().compare(o1.getRaceNumber(), o2.getRaceNumber(), Ordering.natural().nullsLast()).compare(o1.getName(), o2.getName())
				.compare(o1.getId(), o2.getId()).result();
	}
}