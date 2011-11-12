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
package org.spka.cursus.test.series_2011;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.scoring.Scores;
import eu.lp0.cursus.test.util.OverallAssertUtil;
import eu.lp0.cursus.test.util.RaceAssertUtil;

/**
 * Scores at the end of event 1 (29/10/2011 to 30/10/2011)
 */
public class Series2011Event1Scores extends AbstractSeries2011 {
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

			Scores scores = scorer.scoreSeries(series);
			Assert.assertEquals(SERIES_FLEET_AT_EVENT1, scores.getOverallFleetSize());
			Assert.assertEquals(SERIES_FLEET_AT_EVENT1, scores.getFleetSize(race1));

			RaceAssertUtil race1AssertUtil = new RaceAssertUtil(scores, race1);
			race1AssertUtil.assertPilot(sco068, 5, 0, 0, 1);
			race1AssertUtil.assertPilot(sco200, 5, 0, 2, 2);
			race1AssertUtil.assertPilot(sco179, 5, 0, 3, 3);
			race1AssertUtil.assertPilot(sco808, 5, 0, 4, 4);
			race1AssertUtil.assertPilot(sco116, 5, 0, 5, 5);
			race1AssertUtil.assertPilot(sco081, 5, 0, 6, 6);
			race1AssertUtil.assertPilot(sco136, 4, 0, 7, 7);
			race1AssertUtil.assertPilot(sco248, 4, 0, 8, 8);
			race1AssertUtil.assertPilot(sco528, 4, 0, 9, 9);
			race1AssertUtil.assertPilot(sco467, 4, 0, 10, 10);
			race1AssertUtil.assertPilot(sco159, 4, 0, 11, 11);
			race1AssertUtil.assertPilot(sco019, 3, 0, 12, 12);
			race1AssertUtil.assertPilot(sco018, 3, 0, 13, 13);
			race1AssertUtil.assertPilot(sco198, 3, 0, 14, 14);
			race1AssertUtil.assertPilot(sco087, 3, 0, 15, 15);
			race1AssertUtil.assertPilot(sco197, 2, 0, 16, 16);
			race1AssertUtil.assertPilot(sco158, 2, 0, 17, 17);
			race1AssertUtil.assertPilot(sco320, 1, 0, 18, 18);
			race1AssertUtil.assertPilot(sco249, 1, 0, 19, 19);
			race1AssertUtil.assertPilot(sco060, 0, 0, 23, 20);
			race1AssertUtil.assertPilot(sco153, 0, 0, 23, 20);
			race1AssertUtil.assertPilot(sco156, 0, 0, 23, 20);
			race1AssertUtil.assertOrder();

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco068, 0, 0, 1);
			overallAssertUtil.assertPilot(sco200, 0, 2, 2);
			overallAssertUtil.assertPilot(sco179, 0, 3, 3);
			overallAssertUtil.assertPilot(sco808, 0, 4, 4);
			overallAssertUtil.assertPilot(sco116, 0, 5, 5);
			overallAssertUtil.assertPilot(sco081, 0, 6, 6);
			overallAssertUtil.assertPilot(sco136, 0, 7, 7);
			overallAssertUtil.assertPilot(sco248, 0, 8, 8);
			overallAssertUtil.assertPilot(sco528, 0, 9, 9);
			overallAssertUtil.assertPilot(sco467, 0, 10, 10);
			overallAssertUtil.assertPilot(sco159, 0, 11, 11);
			overallAssertUtil.assertPilot(sco019, 0, 12, 12);
			overallAssertUtil.assertPilot(sco018, 0, 13, 13);
			overallAssertUtil.assertPilot(sco198, 0, 14, 14);
			overallAssertUtil.assertPilot(sco087, 0, 15, 15);
			overallAssertUtil.assertPilot(sco197, 0, 16, 16);
			overallAssertUtil.assertPilot(sco158, 0, 17, 17);
			overallAssertUtil.assertPilot(sco320, 0, 18, 18);
			overallAssertUtil.assertPilot(sco249, 0, 19, 19);
			overallAssertUtil.assertPilot(sco060, 0, 23, 20);
			overallAssertUtil.assertPilot(sco153, 0, 23, 20);
			overallAssertUtil.assertPilot(sco156, 0, 23, 20);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkEvent1() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Race race1 = raceDAO.find(event1, RACE1_NAME);

			Scores scores = scorer.scoreEvent(event1);
			Assert.assertEquals(EVENT1_FLEET, scores.getOverallFleetSize());
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race1));

			RaceAssertUtil race1AssertUtil = new RaceAssertUtil(scores, race1);
			race1AssertUtil.assertPilot(sco068, 5, 0, 0, 1);
			race1AssertUtil.assertPilot(sco200, 5, 0, 2, 2);
			race1AssertUtil.assertPilot(sco179, 5, 0, 3, 3);
			race1AssertUtil.assertPilot(sco808, 5, 0, 4, 4);
			race1AssertUtil.assertPilot(sco116, 5, 0, 5, 5);
			race1AssertUtil.assertPilot(sco081, 5, 0, 6, 6);
			race1AssertUtil.assertPilot(sco136, 4, 0, 7, 7);
			race1AssertUtil.assertPilot(sco248, 4, 0, 8, 8);
			race1AssertUtil.assertPilot(sco528, 4, 0, 9, 9);
			race1AssertUtil.assertPilot(sco467, 4, 0, 10, 10);
			race1AssertUtil.assertPilot(sco159, 4, 0, 11, 11);
			race1AssertUtil.assertPilot(sco019, 3, 0, 12, 12);
			race1AssertUtil.assertPilot(sco018, 3, 0, 13, 13);
			race1AssertUtil.assertPilot(sco198, 3, 0, 14, 14);
			race1AssertUtil.assertPilot(sco087, 3, 0, 15, 15);
			race1AssertUtil.assertPilot(sco197, 2, 0, 16, 16);
			race1AssertUtil.assertPilot(sco158, 2, 0, 17, 17);
			race1AssertUtil.assertPilot(sco320, 1, 0, 18, 18);
			race1AssertUtil.assertPilot(sco249, 1, 0, 19, 19);
			race1AssertUtil.assertPilot(sco060, 0, 0, 23, 20);
			race1AssertUtil.assertPilot(sco153, 0, 0, 23, 20);
			race1AssertUtil.assertPilot(sco156, 0, 0, 23, 20);
			race1AssertUtil.assertOrder();

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco068, 0, 0, 1);
			overallAssertUtil.assertPilot(sco200, 0, 2, 2);
			overallAssertUtil.assertPilot(sco179, 0, 3, 3);
			overallAssertUtil.assertPilot(sco808, 0, 4, 4);
			overallAssertUtil.assertPilot(sco116, 0, 5, 5);
			overallAssertUtil.assertPilot(sco081, 0, 6, 6);
			overallAssertUtil.assertPilot(sco136, 0, 7, 7);
			overallAssertUtil.assertPilot(sco248, 0, 8, 8);
			overallAssertUtil.assertPilot(sco528, 0, 9, 9);
			overallAssertUtil.assertPilot(sco467, 0, 10, 10);
			overallAssertUtil.assertPilot(sco159, 0, 11, 11);
			overallAssertUtil.assertPilot(sco019, 0, 12, 12);
			overallAssertUtil.assertPilot(sco018, 0, 13, 13);
			overallAssertUtil.assertPilot(sco198, 0, 14, 14);
			overallAssertUtil.assertPilot(sco087, 0, 15, 15);
			overallAssertUtil.assertPilot(sco197, 0, 16, 16);
			overallAssertUtil.assertPilot(sco158, 0, 17, 17);
			overallAssertUtil.assertPilot(sco320, 0, 18, 18);
			overallAssertUtil.assertPilot(sco249, 0, 19, 19);
			overallAssertUtil.assertPilot(sco060, 0, 23, 20);
			overallAssertUtil.assertPilot(sco153, 0, 23, 20);
			overallAssertUtil.assertPilot(sco156, 0, 23, 20);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace1() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Race race1 = raceDAO.find(event1, RACE1_NAME);

			Scores scores = scorer.scoreRace(race1);
			Assert.assertEquals(EVENT1_FLEET, scores.getOverallFleetSize());
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race1));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race1);
			raceAssertUtil.assertPilot(sco068, 5, 0, 0, 1);
			raceAssertUtil.assertPilot(sco200, 5, 0, 2, 2);
			raceAssertUtil.assertPilot(sco179, 5, 0, 3, 3);
			raceAssertUtil.assertPilot(sco808, 5, 0, 4, 4);
			raceAssertUtil.assertPilot(sco116, 5, 0, 5, 5);
			raceAssertUtil.assertPilot(sco081, 5, 0, 6, 6);
			raceAssertUtil.assertPilot(sco136, 4, 0, 7, 7);
			raceAssertUtil.assertPilot(sco248, 4, 0, 8, 8);
			raceAssertUtil.assertPilot(sco528, 4, 0, 9, 9);
			raceAssertUtil.assertPilot(sco467, 4, 0, 10, 10);
			raceAssertUtil.assertPilot(sco159, 4, 0, 11, 11);
			raceAssertUtil.assertPilot(sco019, 3, 0, 12, 12);
			raceAssertUtil.assertPilot(sco018, 3, 0, 13, 13);
			raceAssertUtil.assertPilot(sco198, 3, 0, 14, 14);
			raceAssertUtil.assertPilot(sco087, 3, 0, 15, 15);
			raceAssertUtil.assertPilot(sco197, 2, 0, 16, 16);
			raceAssertUtil.assertPilot(sco158, 2, 0, 17, 17);
			raceAssertUtil.assertPilot(sco320, 1, 0, 18, 18);
			raceAssertUtil.assertPilot(sco249, 1, 0, 19, 19);
			raceAssertUtil.assertPilot(sco060, 0, 0, 23, 20);
			raceAssertUtil.assertPilot(sco153, 0, 0, 23, 20);
			raceAssertUtil.assertPilot(sco156, 0, 0, 23, 20);
			raceAssertUtil.assertOrder();

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco068, 0, 0, 1);
			overallAssertUtil.assertPilot(sco200, 0, 2, 2);
			overallAssertUtil.assertPilot(sco179, 0, 3, 3);
			overallAssertUtil.assertPilot(sco808, 0, 4, 4);
			overallAssertUtil.assertPilot(sco116, 0, 5, 5);
			overallAssertUtil.assertPilot(sco081, 0, 6, 6);
			overallAssertUtil.assertPilot(sco136, 0, 7, 7);
			overallAssertUtil.assertPilot(sco248, 0, 8, 8);
			overallAssertUtil.assertPilot(sco528, 0, 9, 9);
			overallAssertUtil.assertPilot(sco467, 0, 10, 10);
			overallAssertUtil.assertPilot(sco159, 0, 11, 11);
			overallAssertUtil.assertPilot(sco019, 0, 12, 12);
			overallAssertUtil.assertPilot(sco018, 0, 13, 13);
			overallAssertUtil.assertPilot(sco198, 0, 14, 14);
			overallAssertUtil.assertPilot(sco087, 0, 15, 15);
			overallAssertUtil.assertPilot(sco197, 0, 16, 16);
			overallAssertUtil.assertPilot(sco158, 0, 17, 17);
			overallAssertUtil.assertPilot(sco320, 0, 18, 18);
			overallAssertUtil.assertPilot(sco249, 0, 19, 19);
			overallAssertUtil.assertPilot(sco060, 0, 23, 20);
			overallAssertUtil.assertPilot(sco153, 0, 23, 20);
			overallAssertUtil.assertPilot(sco156, 0, 23, 20);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}