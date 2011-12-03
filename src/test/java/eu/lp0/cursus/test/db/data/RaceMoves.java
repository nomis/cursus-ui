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
package eu.lp0.cursus.test.db.data;

import org.junit.Assert;
import org.junit.Test;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.test.db.AbstractDatabaseTest;

public class RaceMoves extends AbstractDatabaseTest {
	@Test
	public void raceSwap() {
		// Save data
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = new Series("Test 1"); //$NON-NLS-1$
			Event event = new Event(series, "Test 1"); //$NON-NLS-1$
			series.getEvents().add(event);
			Race race1 = new Race(event, "Test 1"); //$NON-NLS-1$
			event.getRaces().add(race1);
			Race race2 = new Race(event, "Test 2"); //$NON-NLS-1$
			event.getRaces().add(race2);
			seriesDAO.persist(series);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}

		// Check data
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find("Test 1"); //$NON-NLS-1$
			Event event = eventDAO.find(series, "Test 1"); //$NON-NLS-1$
			Race race1 = raceDAO.find(event, "Test 1"); //$NON-NLS-1$
			Race race2 = raceDAO.find(event, "Test 2"); //$NON-NLS-1$
			Assert.assertArrayEquals(new Object[] { race1, race2 }, event.getRaces().toArray());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}

		// Modify data
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find("Test 1"); //$NON-NLS-1$
			Event event = eventDAO.find(series, "Test 1"); //$NON-NLS-1$
			Race race1 = event.getRaces().get(0);
			Race race2 = event.getRaces().get(1);
			event.getRaces().set(0, race2);
			event.getRaces().set(1, race1);
			eventDAO.persist(event);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}

		// Check data
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find("Test 1"); //$NON-NLS-1$
			Event event = eventDAO.find(series, "Test 1"); //$NON-NLS-1$
			Race race1 = raceDAO.find(event, "Test 1"); //$NON-NLS-1$
			Race race2 = raceDAO.find(event, "Test 2"); //$NON-NLS-1$
			Assert.assertArrayEquals(new Object[] { race2, race1 }, event.getRaces().toArray());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}