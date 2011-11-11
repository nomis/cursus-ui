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

			Scores scores = scorer.scoreSeries(series);
			Assert.assertEquals(SERIES_FLEET_AT_EVENT1, scores.getOverallFleetSize());

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
	public void checkEvent1() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);

			Scores scores = scorer.scoreEvent(event1);
			Assert.assertEquals(EVENT1_FLEET, scores.getOverallFleetSize());

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
			raceAssertUtil.assertPilot(sco087, 4, 1, 8, 7);
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

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}