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

import com.google.common.collect.Iterables;
import com.google.common.collect.Table;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.scoring.Scores;

public class OverallAssertUtil {
	private final Table<Pilot, Integer, Integer> actualRaceDiscards;
	private final Map<Pilot, Integer> actualOverallPenalties;
	private final Map<Pilot, Integer> actualOverallPoints;
	private final Map<Pilot, Integer> actualOverallPositions;
	private final List<Pilot> actualOverallOrder;

	private final int expectedPilots;
	private final Set<Pilot> expectedOverallOrder = new LinkedHashSet<Pilot>();
	private boolean done = false;

	public OverallAssertUtil(Scores scores) {
		expectedPilots = scores.getPilots().size();
		actualRaceDiscards = scores.getRaceDiscards();
		actualOverallPenalties = scores.getOverallPenalties();
		actualOverallPoints = scores.getOverallPoints();
		actualOverallPositions = scores.getOverallPositions();
		actualOverallOrder = scores.getOverallOrder();
	}

	public void assertPilot(Pilot pilot, int expectedPenalties, int expectedPoints, int expectedPosition, Integer... expectedDiscards) {
		Assert.assertFalse(done);

		if (expectedDiscards == null) {
			expectedDiscards = new Integer[0];
		}
		Assert.assertArrayEquals(
				"Overall discards mismatch for " + pilot, expectedDiscards, Iterables.toArray(Iterables.skip(actualRaceDiscards.row(pilot).values(), 1), Integer.class)); //$NON-NLS-1$
		Assert.assertEquals("Overall penalties mismatch for " + pilot, expectedPenalties, (int)actualOverallPenalties.get(pilot)); //$NON-NLS-1$
		Assert.assertEquals("Overall points mismatch for " + pilot, expectedPoints, (int)actualOverallPoints.get(pilot)); //$NON-NLS-1$
		Assert.assertEquals("Overall position mismatch for " + pilot, expectedPosition, (int)actualOverallPositions.get(pilot)); //$NON-NLS-1$
		Assert.assertTrue("Pilot " + pilot + " already specified", expectedOverallOrder.add(pilot)); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void assertOrder() {
		Assert.assertFalse(done);

		Assert.assertEquals(expectedPilots, expectedOverallOrder.size());
		Assert.assertEquals(expectedPilots, actualOverallOrder.size());
		Assert.assertArrayEquals(expectedOverallOrder.toArray(), actualOverallOrder.toArray());
		done = true;
	}
}