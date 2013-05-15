/*
	cursus - Race series management program
	Copyright 2011-2012  Simon Arlott

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
package org.fisly.cursus.test.europe_2011;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Gender;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.scoring.data.Scores;
import eu.lp0.cursus.scoring.scorer.FleetFilter;
import eu.lp0.cursus.test.util.OverallAssertUtil;

/**
 * Scores for the Class 8 championship (06/06/2011 to 09/09/2011)
 */
public class Series2011Class8Scores extends AbstractSeries2011 {
	@Before
	public void createData() throws Exception {
		createEvent1Races();
	}

	@Test
	public void checkSeries() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Race race1 = raceDAO.find(event1, RACE1_NAME);
			Race race2 = raceDAO.find(event1, RACE2_NAME);
			Race race3 = raceDAO.find(event1, RACE3_NAME);
			Race race4 = raceDAO.find(event1, RACE4_NAME);
			Race race5 = raceDAO.find(event1, RACE5_NAME);
			Race race6 = raceDAO.find(event1, RACE6_NAME);
			Assert.assertEquals(SERIES_PILOTS, series.getPilots().size());
			Scores mScores = scorer.scoreSeries(series, FleetFilter.from(Gender.MALE));
			Scores fScores = scorer.scoreSeries(series, FleetFilter.from(Gender.FEMALE));

			Assert.assertEquals(SERIES_M_FLEET, mScores.getPilots().size());
			Assert.assertEquals(SERIES_M_FLEET, mScores.getFleet().size());
			Assert.assertEquals(EVENT1_M_FLEET, mScores.getFleetSize(race1));
			Assert.assertEquals(EVENT1_M_FLEET, mScores.getFleetSize(race2));
			Assert.assertEquals(EVENT1_M_FLEET, mScores.getFleetSize(race3));
			Assert.assertEquals(EVENT1_M_FLEET, mScores.getFleetSize(race4));
			Assert.assertEquals(EVENT1_M_FLEET, mScores.getFleetSize(race5));
			Assert.assertEquals(EVENT1_M_FLEET, mScores.getFleetSize(race6));

			OverallAssertUtil mOverallAssertUtil = new OverallAssertUtil(mScores);
			mOverallAssertUtil.assertPilot(F09, 1, 3, 1, 18);
			mOverallAssertUtil.assertPilot(H66, 1, 14, 2, 5);
			mOverallAssertUtil.assertPilot(G22, 1, 15, 3, 28);
			mOverallAssertUtil.assertPilot(G25, 0, 15, 4, 4);
			mOverallAssertUtil.assertPilot(G23, 1, 29, 5, 9);
			mOverallAssertUtil.assertPilot(F06, 0, 41, 6, 17);
			mOverallAssertUtil.assertPilot(H61, 1, 43, 7, 18);
			mOverallAssertUtil.assertPilot(B81, 0, 44, 8, 25);
			mOverallAssertUtil.assertPilot(F10, 0, 53, 9, 37);
			mOverallAssertUtil.assertPilot(G26, 0, 57, 10, 24);
			mOverallAssertUtil.assertPilot(G28, 0, 61, 11, 54);
			mOverallAssertUtil.assertPilot(G27, 0, 65, 12, 34);
			mOverallAssertUtil.assertPilot(F07, 1, 65, 13, 16);
			mOverallAssertUtil.assertPilot(F08, 0, 68, 14, 33);
			mOverallAssertUtil.assertPilot(K41, 1, 69, 15, 47);
			mOverallAssertUtil.assertPilot(H65, 0, 69, 16, 28);
			mOverallAssertUtil.assertPilot(H69, 0, 82, 17, 55);
			mOverallAssertUtil.assertPilot(G30, 0, 83, 18, 35);
			mOverallAssertUtil.assertPilot(G21, 0, 83, 19, 51);
			mOverallAssertUtil.assertPilot(H64, 0, 86, 20, 24);
			mOverallAssertUtil.assertPilot(F05, 0, 89, 21, 24);
			mOverallAssertUtil.assertPilot(K42, 0, 91, 22, 23);
			mOverallAssertUtil.assertPilot(G24, 0, 105, 23, 58);
			mOverallAssertUtil.assertPilot(H70, 0, 105, 24, 39);
			mOverallAssertUtil.assertPilot(H62, 0, 115, 25, 45);
			mOverallAssertUtil.assertPilot(F03, 0, 119, 26, 36);
			mOverallAssertUtil.assertPilot(K43, 0, 122, 27, 49);
			mOverallAssertUtil.assertPilot(G29, 0, 124, 28, 59);
			mOverallAssertUtil.assertPilot(F01, 0, 134, 29, 48);
			mOverallAssertUtil.assertPilot(SUI152, 0, 140, 30, 47);
			mOverallAssertUtil.assertPilot(K47, 0, 147, 31, 48);
			mOverallAssertUtil.assertPilot(B82, 0, 147, 32, 44);
			mOverallAssertUtil.assertPilot(K44, 0, 147, 33, 41);
			mOverallAssertUtil.assertPilot(H63, 0, 148, 34, 40);
			mOverallAssertUtil.assertPilot(K45, 0, 157, 35, 42);
			mOverallAssertUtil.assertPilot(IR91, 1, 158, 36, 48);
			mOverallAssertUtil.assertPilot(K46, 1, 160, 37, 50);
			mOverallAssertUtil.assertPilot(SCO104, 0, 171, 38, 46);
			mOverallAssertUtil.assertPilot(F04, 0, 173, 39, 43);
			mOverallAssertUtil.assertPilot(K48, 0, 177, 40, 44);
			mOverallAssertUtil.assertPilot(H67, 0, 178, 41, 43);
			mOverallAssertUtil.assertPilot(K49, 0, 184, 42, 53);
			mOverallAssertUtil.assertPilot(CZ133, 0, 188, 43, 47);
			mOverallAssertUtil.assertPilot(K50, 0, 189, 44, 52);
			mOverallAssertUtil.assertPilot(F02, 1, 191, 45, 49);
			mOverallAssertUtil.assertPilot(CZ135, 0, 210, 46, 59);
			mOverallAssertUtil.assertPilot(SCO105, 0, 215, 47, 59);
			mOverallAssertUtil.assertPilot(IR92, 1, 227, 48, 57);
			mOverallAssertUtil.assertPilot(CZ132, 0, 227, 49, 54);
			mOverallAssertUtil.assertPilot(SCO102, 3, 232, 50, 52);
			mOverallAssertUtil.assertPilot(SCO103, 0, 239, 51, 58);
			// The PKA scored fleet (61) instead of fleet+1 (62) for pilots who didn't complete a lap
			mOverallAssertUtil.assertPilot(SCO106, 1, 239, 52, 62); // 239, 61
			mOverallAssertUtil.assertPilot(CZ131, 0, 254, 53, 56);
			mOverallAssertUtil.assertPilot(IR93, 0, 255, 54, 56);
			mOverallAssertUtil.assertPilot(SCO110, 0, 262, 55, 62); // 262, 61
			mOverallAssertUtil.assertPilot(SCO108, 1, 265, 56, 57);
			mOverallAssertUtil.assertPilot(CZ134, 1, 272, 57, 62); // 271, 61
			mOverallAssertUtil.assertPilot(SCO107, 0, 276, 58, 62); // 276, 61
			mOverallAssertUtil.assertPilot(SCO109, 3, 287, 59, 62); // 287, 61
			mOverallAssertUtil.assertPilot(SCO101, 0, 287, 60, 62); // 287, 61
			mOverallAssertUtil.assertPilot(E111, 0, 306, 61, 62); // 303, 61
			mOverallAssertUtil.assertOrder();

			Assert.assertEquals(SERIES_F_FLEET, fScores.getPilots().size());
			Assert.assertEquals(SERIES_F_FLEET, fScores.getFleet().size());
			Assert.assertEquals(EVENT1_F_FLEET, fScores.getFleetSize(race1));
			Assert.assertEquals(EVENT1_F_FLEET, fScores.getFleetSize(race2));
			Assert.assertEquals(EVENT1_F_FLEET, fScores.getFleetSize(race3));
			Assert.assertEquals(EVENT1_F_FLEET, fScores.getFleetSize(race4));
			Assert.assertEquals(EVENT1_F_FLEET, fScores.getFleetSize(race5));
			Assert.assertEquals(EVENT1_F_FLEET, fScores.getFleetSize(race6));

			OverallAssertUtil fOverallAssertUtil = new OverallAssertUtil(fScores);
			fOverallAssertUtil.assertPilot(G32, 1, 1, 1, 2);
			fOverallAssertUtil.assertPilot(G31, 0, 10, 2, 3);
			fOverallAssertUtil.assertPilot(F12, 0, 12, 3, 5);
			fOverallAssertUtil.assertPilot(F13, 0, 20, 4, 7);
			fOverallAssertUtil.assertPilot(K52, 0, 25, 5, 13);
			fOverallAssertUtil.assertPilot(SCO113, 0, 32, 6, 8);
			fOverallAssertUtil.assertPilot(G35, 0, 35, 7, 13);
			fOverallAssertUtil.assertPilot(G34, 0, 37, 8, 10);
			fOverallAssertUtil.assertPilot(G36, 0, 38, 9, 10);
			fOverallAssertUtil.assertPilot(F11, 0, 46, 10, 11);
			fOverallAssertUtil.assertPilot(G33, 0, 48, 11, 11);
			fOverallAssertUtil.assertPilot(K53, 0, 62, 12, 13);
			fOverallAssertUtil.assertPilot(K51, 0, 65, 13, 13);
			fOverallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}