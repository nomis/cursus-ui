/*
	cursus - Race series management program
	Copyright 2013  Simon Arlott

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
package org.spka.cursus.test.series_2012;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Series;

/**
 * Scores at the end of non-event 2 (16/02/2013 to 17/02/2013)
 */
public class Series2012NonEvent2Scores extends Series2012NonEvent1Scores {
	@Override
	@Before
	public void createData() throws Exception {
		super.createData();
		createNonEvent1Data();
		createNonEvent2Data();
	}

	@Test
	public final void checkNonEvent2() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event nonEvent2 = eventDAO.find(series, NON_EVENT2_NAME);
			Assert.assertEquals(0, nonEvent2.getRaces().size());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}
