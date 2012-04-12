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
import eu.lp0.cursus.db.data.Series;

public class EventOrderTest {
	@Test
	public void seriesOrdering() {
		Series series = new Series("Series 1"); //$NON-NLS-1$
		Event event1 = new Event(series, "Event 1"); //$NON-NLS-1$
		Event event2 = new Event(series, "Event 2"); //$NON-NLS-1$
		Event event3 = new Event(series, "Event 3"); //$NON-NLS-1$
		series.getEvents().add(event3);
		series.getEvents().add(event2);
		series.getEvents().add(event1);

		Assert.assertTrue(event3.compareTo(event2) < 0);
		Assert.assertTrue(event2.compareTo(event1) < 0);
		Assert.assertEquals(0, event1.compareTo(event1));
		Assert.assertEquals(0, event2.compareTo(event2));
		Assert.assertEquals(0, event3.compareTo(event3));
		Assert.assertTrue(event1.compareTo(event2) > 0);
		Assert.assertTrue(event2.compareTo(event3) > 0);
	}
}