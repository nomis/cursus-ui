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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.Scores;

public class RaceSummaryAssertUtil {
	private final Map<Pilot, Integer> actualRacePoints;

	private final Set<Pilot> expectedPilots = new HashSet<Pilot>();
	private final int expectedPilotCount;
	private boolean done = false;

	public RaceSummaryAssertUtil(Scores scores, Race race) {
		expectedPilotCount = scores.getPilots().size();
		actualRacePoints = scores.getRacePoints(race);
	}

	public void assertPilot(Pilot pilot, int expectedPoints) {
		Assert.assertFalse(done);

		Assert.assertEquals("Race points mismatch for " + pilot.getName(), expectedPoints, (int)actualRacePoints.get(pilot)); //$NON-NLS-1$
		Assert.assertTrue("Pilot " + pilot.getName() + " already specified", expectedPilots.add(pilot)); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void assertDone() {
		Assert.assertFalse(done);

		Assert.assertEquals(expectedPilotCount, expectedPilots.size());
		done = true;
	}
}