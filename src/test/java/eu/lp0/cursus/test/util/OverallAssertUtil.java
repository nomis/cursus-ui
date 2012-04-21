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
package eu.lp0.cursus.test.util;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Assert;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.scoring.data.Scores;

public class OverallAssertUtil {
	private final Scores scores;

	private final Set<Pilot> expectedOverallOrder = new LinkedHashSet<Pilot>();
	private boolean done = false;

	public OverallAssertUtil(Scores scores) {
		this.scores = scores;
	}

	public void assertPilot(Pilot pilot, int expectedPenalties, int expectedPoints, int expectedPosition, Integer... expectedDiscards) {
		Assert.assertFalse(done);

		if (expectedDiscards == null) {
			expectedDiscards = new Integer[0];
		}

		Assert.assertTrue("Pilot " + pilot.getName() + " does not exist in scores", scores.getPilots().contains(pilot)); //$NON-NLS-1$//$NON-NLS-2$
		Assert.assertArrayEquals("Overall discards mismatch for " + pilot, expectedDiscards, scores.getRaceDiscards(pilot).toArray()); //$NON-NLS-1$
		Assert.assertEquals("Overall penalties mismatch for " + pilot, expectedPenalties, scores.getOverallPenalties(pilot)); //$NON-NLS-1$
		Assert.assertEquals("Overall points mismatch for " + pilot, expectedPoints, scores.getOverallPoints(pilot)); //$NON-NLS-1$
		Assert.assertEquals("Overall position mismatch for " + pilot, expectedPosition, scores.getOverallPosition(pilot)); //$NON-NLS-1$
		Assert.assertTrue("Pilot " + pilot + " already specified", expectedOverallOrder.add(pilot)); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void assertOrder() {
		Assert.assertFalse(done);

		Assert.assertEquals(scores.getPilots().size(), expectedOverallOrder.size());
		Assert.assertEquals(scores.getPilots().size(), scores.getOverallOrder().size());
		Assert.assertArrayEquals(expectedOverallOrder.toArray(), scores.getOverallOrder().toArray());
		done = true;
	}
}