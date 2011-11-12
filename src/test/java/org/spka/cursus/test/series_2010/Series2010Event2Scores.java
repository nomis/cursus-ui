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
import eu.lp0.cursus.test.util.RaceSummaryAssertUtil;

/**
 * Scores at the end of event 2 (27/11/2010)
 */
public class Series2010Event2Scores extends Series2010Event1Scores {
	@Override
	@Before
	public void createData() throws Exception {
		super.createData();
		createEvent2Races();
	}

	@Override
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
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race4 = raceDAO.find(event2, RACE4_NAME);

			Scores scores = scorer.scoreSeries(series);
			Assert.assertEquals(SERIES_FLEET_AT_EVENT2, scores.getOverallFleetSize());
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race1));
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race2));
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race3));
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race4));

			RaceSummaryAssertUtil race1SummaryAssertUtil = new RaceSummaryAssertUtil(scores, race1);
			race1SummaryAssertUtil.assertPilot(sco200, 0);
			race1SummaryAssertUtil.assertPilot(sco179, 2);
			race1SummaryAssertUtil.assertPilot(sco019, 3);
			race1SummaryAssertUtil.assertPilot(sco808, 3);
			race1SummaryAssertUtil.assertPilot(sco159, 4);
			race1SummaryAssertUtil.assertPilot(sco081, 5);
			race1SummaryAssertUtil.assertPilot(sco116, 6);
			race1SummaryAssertUtil.assertPilot(sco068, 7);
			race1SummaryAssertUtil.assertPilot(sco087, 7);
			race1SummaryAssertUtil.assertPilot(sco248, 8);
			race1SummaryAssertUtil.assertPilot(sco249, 9);
			race1SummaryAssertUtil.assertPilot(sco042, 10);
			race1SummaryAssertUtil.assertPilot(sco018, 11);
			race1SummaryAssertUtil.assertPilot(sco060, 12);
			race1SummaryAssertUtil.assertPilot(sco136, 18);
			race1SummaryAssertUtil.assertPilot(sco153, 18);
			race1SummaryAssertUtil.assertPilot(sco156, 18);
			race1SummaryAssertUtil.assertPilot(sco158, 18);
			race1SummaryAssertUtil.assertPilot(sco197, 18);
			race1SummaryAssertUtil.assertDone();

			RaceSummaryAssertUtil race2SummaryAssertUtil = new RaceSummaryAssertUtil(scores, race2);
			race2SummaryAssertUtil.assertPilot(sco808, 0);
			race2SummaryAssertUtil.assertPilot(sco200, 0);
			race2SummaryAssertUtil.assertPilot(sco019, 2);
			race2SummaryAssertUtil.assertPilot(sco068, 3);
			race2SummaryAssertUtil.assertPilot(sco179, 4);
			race2SummaryAssertUtil.assertPilot(sco159, 4);
			race2SummaryAssertUtil.assertPilot(sco116, 5);
			race2SummaryAssertUtil.assertPilot(sco081, 6);
			race2SummaryAssertUtil.assertPilot(sco248, 7);
			race2SummaryAssertUtil.assertPilot(sco249, 8);
			race2SummaryAssertUtil.assertPilot(sco018, 9);
			race2SummaryAssertUtil.assertPilot(sco060, 10);
			race2SummaryAssertUtil.assertPilot(sco042, 11);
			race2SummaryAssertUtil.assertPilot(sco197, 12);
			race2SummaryAssertUtil.assertPilot(sco153, 13);
			race2SummaryAssertUtil.assertPilot(sco087, 18);
			race2SummaryAssertUtil.assertPilot(sco136, 18);
			race2SummaryAssertUtil.assertPilot(sco156, 18);
			race2SummaryAssertUtil.assertPilot(sco158, 18);
			race2SummaryAssertUtil.assertDone();

			RaceSummaryAssertUtil race3SummaryAssertUtil = new RaceSummaryAssertUtil(scores, race3);
			race3SummaryAssertUtil.assertPilot(sco200, 0);
			race3SummaryAssertUtil.assertPilot(sco081, 2);
			race3SummaryAssertUtil.assertPilot(sco159, 3);
			race3SummaryAssertUtil.assertPilot(sco179, 3);
			race3SummaryAssertUtil.assertPilot(sco068, 4);
			race3SummaryAssertUtil.assertPilot(sco060, 5);
			race3SummaryAssertUtil.assertPilot(sco248, 6);
			race3SummaryAssertUtil.assertPilot(sco087, 7);
			race3SummaryAssertUtil.assertPilot(sco116, 8);
			race3SummaryAssertUtil.assertPilot(sco249, 9);
			race3SummaryAssertUtil.assertPilot(sco042, 10);
			race3SummaryAssertUtil.assertPilot(sco019, 11);
			race3SummaryAssertUtil.assertPilot(sco018, 12);
			race3SummaryAssertUtil.assertPilot(sco153, 13);
			race3SummaryAssertUtil.assertPilot(sco197, 13);
			race3SummaryAssertUtil.assertPilot(sco808, 14);
			race3SummaryAssertUtil.assertPilot(sco136, 18);
			race3SummaryAssertUtil.assertPilot(sco156, 18);
			race3SummaryAssertUtil.assertPilot(sco158, 18);
			race3SummaryAssertUtil.assertDone();

			RaceSummaryAssertUtil race4SummaryAssertUtil = new RaceSummaryAssertUtil(scores, race4);
			race4SummaryAssertUtil.assertPilot(sco068, 0);
			race4SummaryAssertUtil.assertPilot(sco200, 2);
			race4SummaryAssertUtil.assertPilot(sco179, 3);
			race4SummaryAssertUtil.assertPilot(sco019, 3);
			race4SummaryAssertUtil.assertPilot(sco159, 4);
			race4SummaryAssertUtil.assertPilot(sco808, 5);
			race4SummaryAssertUtil.assertPilot(sco116, 6);
			race4SummaryAssertUtil.assertPilot(sco087, 7);
			race4SummaryAssertUtil.assertPilot(sco081, 8);
			race4SummaryAssertUtil.assertPilot(sco060, 8);
			race4SummaryAssertUtil.assertPilot(sco018, 14);
			race4SummaryAssertUtil.assertPilot(sco042, 14);
			race4SummaryAssertUtil.assertPilot(sco136, 14);
			race4SummaryAssertUtil.assertPilot(sco153, 14);
			race4SummaryAssertUtil.assertPilot(sco156, 14);
			race4SummaryAssertUtil.assertPilot(sco158, 14);
			race4SummaryAssertUtil.assertPilot(sco197, 14);
			race4SummaryAssertUtil.assertPilot(sco248, 14);
			race4SummaryAssertUtil.assertPilot(sco249, 14);
			race4SummaryAssertUtil.assertDone();

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			Assert.fail("TODO"); //$NON-NLS-1$
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkEvent2() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race4 = raceDAO.find(event2, RACE4_NAME);

			Scores scores = scorer.scoreEvent(event2);
			Assert.assertEquals(EVENT2_FLEET, scores.getOverallFleetSize());
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race4));

			RaceSummaryAssertUtil race4SummaryAssertUtil = new RaceSummaryAssertUtil(scores, race4);
			race4SummaryAssertUtil.assertPilot(sco068, 0);
			race4SummaryAssertUtil.assertPilot(sco200, 2);
			race4SummaryAssertUtil.assertPilot(sco179, 3);
			race4SummaryAssertUtil.assertPilot(sco159, 4);
			race4SummaryAssertUtil.assertPilot(sco808, 5);
			race4SummaryAssertUtil.assertPilot(sco116, 6);
			race4SummaryAssertUtil.assertPilot(sco087, 7);
			race4SummaryAssertUtil.assertPilot(sco081, 8);
			race4SummaryAssertUtil.assertPilot(sco019, 14);
			race4SummaryAssertUtil.assertPilot(sco060, 14);
			race4SummaryAssertUtil.assertPilot(sco153, 14);
			race4SummaryAssertUtil.assertPilot(sco156, 14);
			race4SummaryAssertUtil.assertPilot(sco197, 14);
			race4SummaryAssertUtil.assertDone();

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco068, 0, 0, 1);
			overallAssertUtil.assertPilot(sco200, 0, 2, 2);
			overallAssertUtil.assertPilot(sco179, 0, 3, 3);
			overallAssertUtil.assertPilot(sco159, 0, 4, 4);
			overallAssertUtil.assertPilot(sco808, 0, 5, 5);
			overallAssertUtil.assertPilot(sco116, 0, 6, 6);
			overallAssertUtil.assertPilot(sco087, 0, 7, 7);
			overallAssertUtil.assertPilot(sco081, 0, 8, 8);
			overallAssertUtil.assertPilot(sco019, 0, 14, 9);
			overallAssertUtil.assertPilot(sco060, 0, 14, 9);
			overallAssertUtil.assertPilot(sco153, 0, 14, 9);
			overallAssertUtil.assertPilot(sco156, 0, 14, 9);
			overallAssertUtil.assertPilot(sco197, 0, 14, 9);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace4() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race4 = raceDAO.find(event2, RACE4_NAME);

			Scores scores = scorer.scoreRace(race4);
			Assert.assertEquals(EVENT2_FLEET, scores.getOverallFleetSize());
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race4));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race4);
			raceAssertUtil.assertPilot(sco068, 6, 0, 0, 1);
			raceAssertUtil.assertPilot(sco200, 6, 0, 2, 2);
			raceAssertUtil.assertPilot(sco179, 6, 0, 3, 3);
			raceAssertUtil.assertPilot(sco159, 5, 0, 4, 4);
			raceAssertUtil.assertPilot(sco808, 5, 0, 5, 5);
			raceAssertUtil.assertPilot(sco116, 5, 0, 6, 6);
			raceAssertUtil.assertPilot(sco087, 4, 0, 7, 7);
			raceAssertUtil.assertPilot(sco081, 1, 0, 8, 8);
			raceAssertUtil.assertPilot(sco019, 0, 0, 14, 9);
			raceAssertUtil.assertPilot(sco060, 0, 0, 14, 9);
			raceAssertUtil.assertPilot(sco153, 0, 0, 14, 9);
			raceAssertUtil.assertPilot(sco156, 0, 0, 14, 9);
			raceAssertUtil.assertPilot(sco197, 0, 0, 14, 9);
			raceAssertUtil.assertOrder();

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco068, 0, 0, 1);
			overallAssertUtil.assertPilot(sco200, 0, 2, 2);
			overallAssertUtil.assertPilot(sco179, 0, 3, 3);
			overallAssertUtil.assertPilot(sco159, 0, 4, 4);
			overallAssertUtil.assertPilot(sco808, 0, 5, 5);
			overallAssertUtil.assertPilot(sco116, 0, 6, 6);
			overallAssertUtil.assertPilot(sco087, 0, 7, 7);
			overallAssertUtil.assertPilot(sco081, 0, 8, 8);
			overallAssertUtil.assertPilot(sco019, 0, 14, 9);
			overallAssertUtil.assertPilot(sco060, 0, 14, 9);
			overallAssertUtil.assertPilot(sco153, 0, 14, 9);
			overallAssertUtil.assertPilot(sco156, 0, 14, 9);
			overallAssertUtil.assertPilot(sco197, 0, 14, 9);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}