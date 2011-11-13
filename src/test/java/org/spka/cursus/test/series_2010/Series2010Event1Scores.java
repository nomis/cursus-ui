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
package org.spka.cursus.test.series_2010;

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
 * Scores at the end of event 1 (16/10/2010 to 17/10/2010)
 */
public class Series2010Event1Scores extends AbstractSeries2010 {
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

			Scores scores = scorer.scoreSeries(series);
			Assert.assertEquals(SERIES_FLEET_AT_EVENT1, scores.getOverallFleetSize());
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race1));
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race2));
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race3));

			RaceAssertUtil race1AssertUtil = new RaceAssertUtil(scores, race1);
			race1AssertUtil.assertPilot(sco200, 5, 0, 0, 1);
			race1AssertUtil.assertPilot(sco179, 5, 0, 2, 2);
			race1AssertUtil.assertPilot(sco019, 5, 0, 3, 3);
			race1AssertUtil.assertPilot(sco159, 4, 0, 4, 4);
			race1AssertUtil.assertPilot(sco081, 4, 0, 5, 5);
			race1AssertUtil.assertPilot(sco116, 3, 0, 6, 6);
			race1AssertUtil.assertPilot(sco068, 2, 0, 7, 7);
			race1AssertUtil.assertPilot(sco808, 0, 0, 7, 8);
			race1AssertUtil.assertPilot(sco248, 2, 0, 8, 9);
			race1AssertUtil.assertPilot(sco249, 2, 0, 9, 10);
			race1AssertUtil.assertPilot(sco042, 2, 0, 10, 11);
			race1AssertUtil.assertPilot(sco018, 2, 0, 11, 12);
			race1AssertUtil.assertPilot(sco060, 1, 0, 12, 13);
			race1AssertUtil.assertPilot(sco087, 0, 0, 13, 14);
			race1AssertUtil.assertPilot(sco136, 0, 0, 18, 15);
			race1AssertUtil.assertPilot(sco153, 0, 0, 18, 15);
			race1AssertUtil.assertPilot(sco156, 0, 0, 18, 15);
			race1AssertUtil.assertPilot(sco158, 0, 0, 18, 15);
			race1AssertUtil.assertPilot(sco197, 0, 0, 18, 15);
			race1AssertUtil.assertOrder();

			RaceAssertUtil race2AssertUtil = new RaceAssertUtil(scores, race2);
			race2AssertUtil.assertPilot(sco808, 5, 0, 0, 1);
			race2AssertUtil.assertPilot(sco200, 0, 0, 0, 2);
			race2AssertUtil.assertPilot(sco019, 5, 0, 2, 3);
			race2AssertUtil.assertPilot(sco068, 4, 0, 3, 4);
			race2AssertUtil.assertPilot(sco179, 4, 0, 4, 5);
			race2AssertUtil.assertPilot(sco159, 0, 0, 4, 6);
			race2AssertUtil.assertPilot(sco116, 4, 0, 5, 7);
			race2AssertUtil.assertPilot(sco081, 3, 0, 6, 8);
			race2AssertUtil.assertPilot(sco248, 3, 0, 7, 9);
			race2AssertUtil.assertPilot(sco249, 3, 0, 8, 10);
			race2AssertUtil.assertPilot(sco018, 3, 0, 9, 11);
			race2AssertUtil.assertPilot(sco060, 2, 0, 10, 12);
			race2AssertUtil.assertPilot(sco042, 2, 0, 11, 13);
			race2AssertUtil.assertPilot(sco197, 2, 0, 12, 14);
			race2AssertUtil.assertPilot(sco153, 2, 0, 13, 15);
			race2AssertUtil.assertPilot(sco087, 0, 0, 18, 16);
			race2AssertUtil.assertPilot(sco136, 0, 0, 18, 16);
			race2AssertUtil.assertPilot(sco156, 0, 0, 18, 16);
			race2AssertUtil.assertPilot(sco158, 0, 0, 18, 16);
			race2AssertUtil.assertOrder();

			RaceAssertUtil race3AssertUtil = new RaceAssertUtil(scores, race3);
			race3AssertUtil.assertPilot(sco200, 7, 0, 0, 1);
			race3AssertUtil.assertPilot(sco081, 7, 0, 2, 2);
			race3AssertUtil.assertPilot(sco159, 6, 0, 3, 3);
			race3AssertUtil.assertPilot(sco179, 0, 0, 3, 4);
			race3AssertUtil.assertPilot(sco068, 6, 0, 4, 5);
			race3AssertUtil.assertPilot(sco060, 5, 0, 5, 6);
			race3AssertUtil.assertPilot(sco248, 4, 0, 6, 7);
			race3AssertUtil.assertPilot(sco087, 4, 1, 7, 8);
			race3AssertUtil.assertPilot(sco116, 3, 0, 8, 9);
			race3AssertUtil.assertPilot(sco249, 3, 0, 9, 10);
			race3AssertUtil.assertPilot(sco042, 3, 0, 10, 11);
			race3AssertUtil.assertPilot(sco019, 2, 0, 11, 12);
			race3AssertUtil.assertPilot(sco018, 1, 0, 12, 13);
			race3AssertUtil.assertPilot(sco153, 1, 0, 13, 14);
			race3AssertUtil.assertPilot(sco808, 1, 0, 14, 15);
			race3AssertUtil.assertPilot(sco197, 0, 0, 15, 16);
			race3AssertUtil.assertPilot(sco136, 0, 0, 18, 17);
			race3AssertUtil.assertPilot(sco156, 0, 0, 18, 17);
			race3AssertUtil.assertPilot(sco158, 0, 0, 18, 17);
			race3AssertUtil.assertOrder();

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(sco179, 0, 9, 2);
			overallAssertUtil.assertPilot(sco159, 0, 11, 3);
			overallAssertUtil.assertPilot(sco081, 0, 13, 4);
			overallAssertUtil.assertPilot(sco068, 0, 14, 5);
			overallAssertUtil.assertPilot(sco019, 0, 16, 6);
			overallAssertUtil.assertPilot(sco116, 0, 19, 7);
			overallAssertUtil.assertPilot(sco808, 0, 21, 8);
			overallAssertUtil.assertPilot(sco248, 0, 21, 9);
			overallAssertUtil.assertPilot(sco249, 0, 26, 10);
			overallAssertUtil.assertPilot(sco060, 0, 27, 11);
			overallAssertUtil.assertPilot(sco042, 0, 31, 12);
			overallAssertUtil.assertPilot(sco018, 0, 32, 13);
			overallAssertUtil.assertPilot(sco087, 1, 39, 14);
			overallAssertUtil.assertPilot(sco153, 0, 44, 15);
			overallAssertUtil.assertPilot(sco197, 0, 45, 16);
			overallAssertUtil.assertPilot(sco136, 0, 54, 17);
			overallAssertUtil.assertPilot(sco156, 0, 54, 17);
			overallAssertUtil.assertPilot(sco158, 0, 54, 17);
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
			Race race2 = raceDAO.find(event1, RACE2_NAME);
			Race race3 = raceDAO.find(event1, RACE3_NAME);

			Scores scores = scorer.scoreEvent(event1);
			Assert.assertEquals(EVENT1_FLEET, scores.getOverallFleetSize());
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race1));

			RaceAssertUtil race1AssertUtil = new RaceAssertUtil(scores, race1);
			race1AssertUtil.assertPilot(sco200, 5, 0, 0, 1);
			race1AssertUtil.assertPilot(sco179, 5, 0, 2, 2);
			race1AssertUtil.assertPilot(sco019, 5, 0, 3, 3);
			race1AssertUtil.assertPilot(sco159, 4, 0, 4, 4);
			race1AssertUtil.assertPilot(sco081, 4, 0, 5, 5);
			race1AssertUtil.assertPilot(sco116, 3, 0, 6, 6);
			race1AssertUtil.assertPilot(sco068, 2, 0, 7, 7);
			race1AssertUtil.assertPilot(sco808, 0, 0, 7, 8);
			race1AssertUtil.assertPilot(sco248, 2, 0, 8, 9);
			race1AssertUtil.assertPilot(sco249, 2, 0, 9, 10);
			race1AssertUtil.assertPilot(sco042, 2, 0, 10, 11);
			race1AssertUtil.assertPilot(sco018, 2, 0, 11, 12);
			race1AssertUtil.assertPilot(sco060, 1, 0, 12, 13);
			race1AssertUtil.assertPilot(sco087, 0, 0, 13, 14);
			race1AssertUtil.assertPilot(sco153, 0, 0, 18, 15);
			race1AssertUtil.assertPilot(sco156, 0, 0, 18, 15);
			race1AssertUtil.assertPilot(sco197, 0, 0, 18, 15);
			race1AssertUtil.assertOrder();

			RaceAssertUtil race2AssertUtil = new RaceAssertUtil(scores, race2);
			race2AssertUtil.assertPilot(sco808, 5, 0, 0, 1);
			race2AssertUtil.assertPilot(sco200, 0, 0, 0, 2);
			race2AssertUtil.assertPilot(sco019, 5, 0, 2, 3);
			race2AssertUtil.assertPilot(sco068, 4, 0, 3, 4);
			race2AssertUtil.assertPilot(sco179, 4, 0, 4, 5);
			race2AssertUtil.assertPilot(sco159, 0, 0, 4, 6);
			race2AssertUtil.assertPilot(sco116, 4, 0, 5, 7);
			race2AssertUtil.assertPilot(sco081, 3, 0, 6, 8);
			race2AssertUtil.assertPilot(sco248, 3, 0, 7, 9);
			race2AssertUtil.assertPilot(sco249, 3, 0, 8, 10);
			race2AssertUtil.assertPilot(sco018, 3, 0, 9, 11);
			race2AssertUtil.assertPilot(sco060, 2, 0, 10, 12);
			race2AssertUtil.assertPilot(sco042, 2, 0, 11, 13);
			race2AssertUtil.assertPilot(sco197, 2, 0, 12, 14);
			race2AssertUtil.assertPilot(sco153, 2, 0, 13, 15);
			race2AssertUtil.assertPilot(sco087, 0, 0, 18, 16);
			race2AssertUtil.assertPilot(sco156, 0, 0, 18, 16);
			race2AssertUtil.assertOrder();

			RaceAssertUtil race3AssertUtil = new RaceAssertUtil(scores, race3);
			race3AssertUtil.assertPilot(sco200, 7, 0, 0, 1);
			race3AssertUtil.assertPilot(sco081, 7, 0, 2, 2);
			race3AssertUtil.assertPilot(sco159, 6, 0, 3, 3);
			race3AssertUtil.assertPilot(sco179, 0, 0, 3, 4);
			race3AssertUtil.assertPilot(sco068, 6, 0, 4, 5);
			race3AssertUtil.assertPilot(sco060, 5, 0, 5, 6);
			race3AssertUtil.assertPilot(sco248, 4, 0, 6, 7);
			race3AssertUtil.assertPilot(sco087, 4, 1, 7, 8);
			race3AssertUtil.assertPilot(sco116, 3, 0, 8, 9);
			race3AssertUtil.assertPilot(sco249, 3, 0, 9, 10);
			race3AssertUtil.assertPilot(sco042, 3, 0, 10, 11);
			race3AssertUtil.assertPilot(sco019, 2, 0, 11, 12);
			race3AssertUtil.assertPilot(sco018, 1, 0, 12, 13);
			race3AssertUtil.assertPilot(sco153, 1, 0, 13, 14);
			race3AssertUtil.assertPilot(sco808, 1, 0, 14, 15);
			race3AssertUtil.assertPilot(sco197, 0, 0, 15, 16);
			race3AssertUtil.assertPilot(sco156, 0, 0, 18, 17);
			race3AssertUtil.assertOrder();

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(sco179, 0, 9, 2);
			overallAssertUtil.assertPilot(sco159, 0, 11, 3);
			overallAssertUtil.assertPilot(sco081, 0, 13, 4);
			overallAssertUtil.assertPilot(sco068, 0, 14, 5);
			overallAssertUtil.assertPilot(sco019, 0, 16, 6);
			overallAssertUtil.assertPilot(sco116, 0, 19, 7);
			overallAssertUtil.assertPilot(sco808, 0, 21, 8);
			overallAssertUtil.assertPilot(sco248, 0, 21, 9);
			overallAssertUtil.assertPilot(sco249, 0, 26, 10);
			overallAssertUtil.assertPilot(sco060, 0, 27, 11);
			overallAssertUtil.assertPilot(sco042, 0, 31, 12);
			overallAssertUtil.assertPilot(sco018, 0, 32, 13);
			overallAssertUtil.assertPilot(sco087, 1, 39, 14);
			overallAssertUtil.assertPilot(sco153, 0, 44, 15);
			overallAssertUtil.assertPilot(sco197, 0, 45, 16);
			overallAssertUtil.assertPilot(sco156, 0, 54, 17);
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
			raceAssertUtil.assertPilot(sco200, 5, 0, 0, 1);
			raceAssertUtil.assertPilot(sco179, 5, 0, 2, 2);
			raceAssertUtil.assertPilot(sco019, 5, 0, 3, 3);
			raceAssertUtil.assertPilot(sco159, 4, 0, 4, 4);
			raceAssertUtil.assertPilot(sco081, 4, 0, 5, 5);
			raceAssertUtil.assertPilot(sco116, 3, 0, 6, 6);
			raceAssertUtil.assertPilot(sco068, 2, 0, 7, 7);
			raceAssertUtil.assertPilot(sco248, 2, 0, 8, 8);
			raceAssertUtil.assertPilot(sco249, 2, 0, 9, 9);
			raceAssertUtil.assertPilot(sco042, 2, 0, 10, 10);
			raceAssertUtil.assertPilot(sco018, 2, 0, 11, 11);
			raceAssertUtil.assertPilot(sco060, 1, 0, 12, 12);
			raceAssertUtil.assertPilot(sco087, 0, 0, 18, 13);
			raceAssertUtil.assertPilot(sco153, 0, 0, 18, 13);
			raceAssertUtil.assertPilot(sco156, 0, 0, 18, 13);
			raceAssertUtil.assertPilot(sco197, 0, 0, 18, 13);
			raceAssertUtil.assertPilot(sco808, 0, 0, 18, 13);
			raceAssertUtil.assertOrder();

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(sco179, 0, 2, 2);
			overallAssertUtil.assertPilot(sco019, 0, 3, 3);
			overallAssertUtil.assertPilot(sco159, 0, 4, 4);
			overallAssertUtil.assertPilot(sco081, 0, 5, 5);
			overallAssertUtil.assertPilot(sco116, 0, 6, 6);
			overallAssertUtil.assertPilot(sco068, 0, 7, 7);
			overallAssertUtil.assertPilot(sco248, 0, 8, 8);
			overallAssertUtil.assertPilot(sco249, 0, 9, 9);
			overallAssertUtil.assertPilot(sco042, 0, 10, 10);
			overallAssertUtil.assertPilot(sco018, 0, 11, 11);
			overallAssertUtil.assertPilot(sco060, 0, 12, 12);
			overallAssertUtil.assertPilot(sco087, 0, 18, 13);
			overallAssertUtil.assertPilot(sco153, 0, 18, 13);
			overallAssertUtil.assertPilot(sco156, 0, 18, 13);
			overallAssertUtil.assertPilot(sco197, 0, 18, 13);
			overallAssertUtil.assertPilot(sco808, 0, 18, 13);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace2() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Race race2 = raceDAO.find(event1, RACE2_NAME);

			Scores scores = scorer.scoreRace(race2);
			Assert.assertEquals(EVENT1_FLEET, scores.getOverallFleetSize());
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race2));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race2);
			raceAssertUtil.assertPilot(sco808, 5, 0, 0, 1);
			raceAssertUtil.assertPilot(sco019, 5, 0, 2, 2);
			raceAssertUtil.assertPilot(sco068, 4, 0, 3, 3);
			raceAssertUtil.assertPilot(sco179, 4, 0, 4, 4);
			raceAssertUtil.assertPilot(sco116, 4, 0, 5, 5);
			raceAssertUtil.assertPilot(sco081, 3, 0, 6, 6);
			raceAssertUtil.assertPilot(sco248, 3, 0, 7, 7);
			raceAssertUtil.assertPilot(sco249, 3, 0, 8, 8);
			raceAssertUtil.assertPilot(sco018, 3, 0, 9, 9);
			raceAssertUtil.assertPilot(sco060, 2, 0, 10, 10);
			raceAssertUtil.assertPilot(sco042, 2, 0, 11, 11);
			raceAssertUtil.assertPilot(sco197, 2, 0, 12, 12);
			raceAssertUtil.assertPilot(sco153, 2, 0, 13, 13);
			raceAssertUtil.assertPilot(sco087, 0, 0, 18, 14);
			raceAssertUtil.assertPilot(sco156, 0, 0, 18, 14);
			raceAssertUtil.assertPilot(sco159, 0, 0, 18, 14);
			raceAssertUtil.assertPilot(sco200, 0, 0, 18, 14);
			raceAssertUtil.assertOrder();

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco808, 0, 0, 1);
			overallAssertUtil.assertPilot(sco019, 0, 2, 2);
			overallAssertUtil.assertPilot(sco068, 0, 3, 3);
			overallAssertUtil.assertPilot(sco179, 0, 4, 4);
			overallAssertUtil.assertPilot(sco116, 0, 5, 5);
			overallAssertUtil.assertPilot(sco081, 0, 6, 6);
			overallAssertUtil.assertPilot(sco248, 0, 7, 7);
			overallAssertUtil.assertPilot(sco249, 0, 8, 8);
			overallAssertUtil.assertPilot(sco018, 0, 9, 9);
			overallAssertUtil.assertPilot(sco060, 0, 10, 10);
			overallAssertUtil.assertPilot(sco042, 0, 11, 11);
			overallAssertUtil.assertPilot(sco197, 0, 12, 12);
			overallAssertUtil.assertPilot(sco153, 0, 13, 13);
			overallAssertUtil.assertPilot(sco087, 0, 18, 14);
			overallAssertUtil.assertPilot(sco156, 0, 18, 14);
			overallAssertUtil.assertPilot(sco159, 0, 18, 14);
			overallAssertUtil.assertPilot(sco200, 0, 18, 14);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace3() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Race race3 = raceDAO.find(event1, RACE3_NAME);

			Scores scores = scorer.scoreRace(race3);
			Assert.assertEquals(EVENT1_FLEET, scores.getOverallFleetSize());
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race3));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race3);
			raceAssertUtil.assertPilot(sco200, 7, 0, 0, 1);
			raceAssertUtil.assertPilot(sco081, 7, 0, 2, 2);
			raceAssertUtil.assertPilot(sco159, 6, 0, 3, 3);
			raceAssertUtil.assertPilot(sco068, 6, 0, 4, 4);
			raceAssertUtil.assertPilot(sco060, 5, 0, 5, 5);
			raceAssertUtil.assertPilot(sco248, 4, 0, 6, 6);
			raceAssertUtil.assertPilot(sco087, 4, 1, 7, 7);
			raceAssertUtil.assertPilot(sco116, 3, 0, 8, 8);
			raceAssertUtil.assertPilot(sco249, 3, 0, 9, 9);
			raceAssertUtil.assertPilot(sco042, 3, 0, 10, 10);
			raceAssertUtil.assertPilot(sco019, 2, 0, 11, 11);
			raceAssertUtil.assertPilot(sco018, 1, 0, 12, 12);
			raceAssertUtil.assertPilot(sco153, 1, 0, 13, 13);
			raceAssertUtil.assertPilot(sco808, 1, 0, 14, 14);
			raceAssertUtil.assertPilot(sco156, 0, 0, 18, 15);
			raceAssertUtil.assertPilot(sco179, 0, 0, 18, 15);
			raceAssertUtil.assertPilot(sco197, 0, 0, 18, 15);
			raceAssertUtil.assertOrder();

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(sco081, 0, 2, 2);
			overallAssertUtil.assertPilot(sco159, 0, 3, 3);
			overallAssertUtil.assertPilot(sco068, 0, 4, 4);
			overallAssertUtil.assertPilot(sco060, 0, 5, 5);
			overallAssertUtil.assertPilot(sco248, 0, 6, 6);
			overallAssertUtil.assertPilot(sco087, 1, 8, 7);
			overallAssertUtil.assertPilot(sco116, 0, 8, 8);
			overallAssertUtil.assertPilot(sco249, 0, 9, 9);
			overallAssertUtil.assertPilot(sco042, 0, 10, 10);
			overallAssertUtil.assertPilot(sco019, 0, 11, 11);
			overallAssertUtil.assertPilot(sco018, 0, 12, 12);
			overallAssertUtil.assertPilot(sco153, 0, 13, 13);
			overallAssertUtil.assertPilot(sco808, 0, 14, 14);
			overallAssertUtil.assertPilot(sco156, 0, 18, 15);
			overallAssertUtil.assertPilot(sco179, 0, 18, 15);
			overallAssertUtil.assertPilot(sco197, 0, 18, 15);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}