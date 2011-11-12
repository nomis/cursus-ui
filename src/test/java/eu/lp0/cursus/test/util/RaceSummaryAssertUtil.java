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
package eu.lp0.cursus.test.util;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.Scores;

public class RaceSummaryAssertUtil {
	private final Map<Pilot, Integer> actualRacePoints;
	private final List<Pilot> actualRaceOrder;

	private final int expectedPilots;
	private final Set<Pilot> expectedRaceOrder = new LinkedHashSet<Pilot>();
	private boolean done = false;

	public RaceSummaryAssertUtil(Scores scores, Race race) {
		expectedPilots = scores.getPilots().size();
		actualRacePoints = scores.getRacePoints(race);
		actualRaceOrder = scores.getRaceOrder(race);
	}

	public void assertPilot(Pilot pilot, int expectedPoints) {
		Assert.assertFalse(done);

		Assert.assertTrue("Pilot " + pilot.getName() + " does not exist in scores", actualRacePoints.containsKey(pilot)); //$NON-NLS-1$//$NON-NLS-2$
		Assert.assertEquals("Race points mismatch for " + pilot.getName(), expectedPoints, (int)actualRacePoints.get(pilot)); //$NON-NLS-1$
		Assert.assertTrue("Pilot " + pilot.getName() + " already specified", expectedRaceOrder.add(pilot)); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void assertDone() {
		Assert.assertFalse(done);

		Assert.assertEquals(expectedPilots, expectedRaceOrder.size());
		Assert.assertEquals(expectedPilots, actualRaceOrder.size());
		Assert.assertArrayEquals(expectedRaceOrder.toArray(), actualRaceOrder.toArray());
		done = true;
	}
}