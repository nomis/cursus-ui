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
package eu.lp0.cursus.test.db.data;

import junit.framework.Assert;

import org.junit.Test;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;

public class RaceOrderTest {
	@Test
	public void eventOrdering() {
		Series series = new Series("Series 1"); //$NON-NLS-1$
		Event event = new Event(series, "Event"); //$NON-NLS-1$
		series.getEvents().add(event);
		Race race1 = new Race(event, "Race 1"); //$NON-NLS-1$
		Race race2 = new Race(event, "Race 2"); //$NON-NLS-1$
		Race race3 = new Race(event, "Race 3"); //$NON-NLS-1$
		event.getRaces().add(race3);
		event.getRaces().add(race2);
		event.getRaces().add(race1);

		Assert.assertTrue(race3.compareTo(race2) < 0);
		Assert.assertTrue(race2.compareTo(race1) < 0);
		Assert.assertEquals(0, race1.compareTo(race1));
		Assert.assertEquals(0, race2.compareTo(race2));
		Assert.assertEquals(0, race3.compareTo(race3));
		Assert.assertTrue(race1.compareTo(race2) > 0);
		Assert.assertTrue(race2.compareTo(race3) > 0);
	}

	@Test
	public void seriesOrdering1() {
		Series series = new Series("Series 1"); //$NON-NLS-1$
		Event event1 = new Event(series, "Event 1"); //$NON-NLS-1$
		Event event2 = new Event(series, "Event 2"); //$NON-NLS-1$
		Event event3 = new Event(series, "Event 3"); //$NON-NLS-1$
		series.getEvents().add(event3);
		series.getEvents().add(event2);
		series.getEvents().add(event1);

		Race race1 = new Race(event3, "Race 1"); //$NON-NLS-1$
		Race race2 = new Race(event2, "Race 2"); //$NON-NLS-1$
		Race race3 = new Race(event1, "Race 3"); //$NON-NLS-1$
		event1.getRaces().add(race3);
		event2.getRaces().add(race2);
		event3.getRaces().add(race1);

		Assert.assertTrue(race2.compareTo(race3) < 0);
		Assert.assertTrue(race1.compareTo(race2) < 0);
		Assert.assertEquals(0, race1.compareTo(race1));
		Assert.assertEquals(0, race2.compareTo(race2));
		Assert.assertEquals(0, race3.compareTo(race3));
		Assert.assertTrue(race2.compareTo(race1) > 0);
		Assert.assertTrue(race3.compareTo(race2) > 0);
	}

	@Test
	public void seriesOrdering2() {
		Series series = new Series("Series 1"); //$NON-NLS-1$
		Event event1 = new Event(series, "Event 1"); //$NON-NLS-1$
		Event event2 = new Event(series, "Event 2"); //$NON-NLS-1$
		Event event3 = new Event(series, "Event 3"); //$NON-NLS-1$
		series.getEvents().add(event3);
		series.getEvents().add(event2);
		series.getEvents().add(event1);

		Race race1 = new Race(event1, "Race 1"); //$NON-NLS-1$
		Race race2 = new Race(event2, "Race 2"); //$NON-NLS-1$
		Race race3 = new Race(event3, "Race 3"); //$NON-NLS-1$
		event1.getRaces().add(race1);
		event2.getRaces().add(race2);
		event3.getRaces().add(race3);

		Assert.assertTrue(race2.compareTo(race1) < 0);
		Assert.assertTrue(race3.compareTo(race2) < 0);
		Assert.assertEquals(0, race1.compareTo(race1));
		Assert.assertEquals(0, race2.compareTo(race2));
		Assert.assertEquals(0, race3.compareTo(race3));
		Assert.assertTrue(race1.compareTo(race2) > 0);
		Assert.assertTrue(race2.compareTo(race3) > 0);
	}
}