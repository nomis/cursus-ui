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

public class RaceAssertUtil {
	private final Map<Pilot, Integer> actualRaceLaps;
	private final Map<Pilot, Integer> actualRacePenalties;
	private final Map<Pilot, Integer> actualRacePoints;
	private final Map<Pilot, Integer> actualRacePositions;
	private final List<Pilot> actualRaceOrder;

	private final Set<Pilot> expectedRaceOrder = new LinkedHashSet<Pilot>();
	private boolean done = false;

	public RaceAssertUtil(Scores scores, Race race) {
		actualRaceLaps = scores.getLaps(race);
		actualRacePenalties = scores.getRacePenalties(race);
		actualRacePoints = scores.getRacePoints(race);
		actualRacePositions = scores.getRacePositions(race);
		actualRaceOrder = scores.getRaceOrder(race);
	}

	public void assertPilot(Pilot pilot, int expectedLaps, int expectedPenalties, int expectedPoints, int expectedPosition) {
		Assert.assertFalse(done);

		Assert.assertEquals("Race laps mismatch for " + pilot.getName(), expectedLaps, (int)actualRaceLaps.get(pilot)); //$NON-NLS-1$
		Assert.assertEquals("Race penalties mismatch for " + pilot.getName(), expectedPenalties, (int)actualRacePenalties.get(pilot)); //$NON-NLS-1$
		Assert.assertEquals("Race points mismatch for " + pilot.getName(), expectedPoints, (int)actualRacePoints.get(pilot)); //$NON-NLS-1$
		Assert.assertEquals("Race position mismatch for " + pilot.getName(), expectedPosition, (int)actualRacePositions.get(pilot)); //$NON-NLS-1$
		Assert.assertTrue("Pilot " + pilot.getName() + " already specified", expectedRaceOrder.add(pilot)); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void assertOrder() {
		Assert.assertFalse(done);

		Assert.assertArrayEquals(expectedRaceOrder.toArray(), actualRaceOrder.toArray());
		done = true;
	}
}