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
package org.spka.cursus.test;

import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.test.db.AbstractDatabaseTest;

public class AbstractSeries extends AbstractDatabaseTest {
	protected final String SERIES_COUNTRY = "Scotland"; //$NON-NLS-1$

	protected Set<Pilot> getResultsPilots(Series series) throws Exception {
		return Sets.filter(series.getPilots(), new Predicate<Pilot>() {
			@Override
			public boolean apply(Pilot pilot) {
				return pilot.getCountry().equals(SERIES_COUNTRY);
			}
		});
	}

	protected Set<Pilot> getResultsPilots(Series series, final Event event) {
		return Sets.filter(series.getPilots(), new Predicate<Pilot>() {
			@Override
			public boolean apply(Pilot pilot) {
				for (Race race : event.getRaces()) {
					if (race.getAttendees().containsKey(pilot)) {
						return true;
					}
				}

				return pilot.getCountry().equals(SERIES_COUNTRY);
			}
		});
	}
}