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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.scoring.Scores;
import eu.lp0.cursus.test.db.AbstractDatabaseTest;
import eu.lp0.cursus.xml.ExportScores;

public class AbstractSeries extends AbstractDatabaseTest {
	protected final String SERIES_COUNTRY = "Scotland"; //$NON-NLS-1$

	/**
	 * Get all the pilots in a Scotland series
	 */
	protected Set<Pilot> getSeriesResultsPilots(Series series) throws Exception {
		return Sets.filter(series.getPilots(), new Predicate<Pilot>() {
			@Override
			public boolean apply(Pilot pilot) {
				return pilot.getCountry().equals(SERIES_COUNTRY);
			}
		});
	}

	/**
	 * Get all the pilots at a Scotland event (including non-Scotland pilots)
	 * (but only up to and including the specified event)
	 */
	protected Set<Pilot> getEventResultsPilots(Series series, final Event event) {
		return Sets.filter(series.getPilots(), new Predicate<Pilot>() {
			@Override
			public boolean apply(Pilot pilot) {
				for (Race race : event.getRaces()) {
					if (race.getAttendees().containsKey(pilot)) {
						return true;
					}
				}

				for (Race race : pilot.getRaces().keySet()) {
					if (race.getEvent().compareTo(event) <= 0) {
						return pilot.getCountry().equals(SERIES_COUNTRY);
					}
				}

				return false;
			}
		});
	}

	/**
	 * Get all the pilots in a Scotland series (only up to and including the specified event)
	 */
	protected Set<Pilot> getSeriesResultsPilots(Series series, final Event event) {
		return Sets.filter(series.getPilots(), new Predicate<Pilot>() {
			@Override
			public boolean apply(Pilot pilot) {
				for (Race race : pilot.getRaces().keySet()) {
					if (race.getEvent().compareTo(event) <= 0) {
						return pilot.getCountry().equals(SERIES_COUNTRY);
					}
				}

				return false;
			}
		});
	}

	protected void debugPrintScores(Scores scores) {
		List<Event> events = new ArrayList<Event>();
		for (Race race : scores.getRaces()) {
			if (!events.contains(race.getEvent())) {
				events.add(race.getEvent());
			}
		}

		System.out.print("Pos\tPilot"); //$NON-NLS-1$
		int i = 1;
		for (Race race : scores.getRaces()) {
			System.out.print("\tE" + (events.indexOf(race.getEvent()) + 1)); //$NON-NLS-1$
			System.out.print("/R" + i); //$NON-NLS-1$
			i++;
		}
		if (scores.getRaces().size() == 1) {
			System.out.print("\tLaps"); //$NON-NLS-1$
		}
		System.out.print("\tPen"); //$NON-NLS-1$
		for (i = 1; i <= scores.getDiscardCount(); i++) {
			System.out.print("\tDisc"); //$NON-NLS-1$
		}
		System.out.println("\tTotal"); //$NON-NLS-1$
		for (Pilot pilot : scores.getOverallOrder()) {
			System.out.print(scores.getOverallPosition(pilot) + ":\t" + pilot.getName()); //$NON-NLS-1$
			for (Race race : scores.getRaces()) {
				System.out.print("\t" + scores.getRacePoints(pilot, race)); //$NON-NLS-1$
				if (scores.getSimulatedRacePoints(pilot).contains(race)) {
					System.out.print("*"); //$NON-NLS-1$
				}
			}
			if (scores.getRaces().size() == 1) {
				System.out.print("\t" + scores.getLaps(pilot, scores.getRaces().get(0))); //$NON-NLS-1$
			}
			System.out.print("\t" + scores.getOverallPenalties(pilot)); //$NON-NLS-1$ 
			for (i = 1; i <= scores.getDiscardCount(); i++) {
				System.out.print("\t" + scores.getRaceDiscard(pilot, i)); //$NON-NLS-1$ 
			}
			System.out.println("\t" + scores.getOverallPoints(pilot)); //$NON-NLS-1$ 
		}

		System.out.println();
		System.out.println(new ExportScores(scores).toString());
	}
}