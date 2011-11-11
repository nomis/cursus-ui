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

			Scores scores = scorer.scoreSeries(series);
			Assert.assertEquals(SERIES_FLEET_AT_EVENT2, scores.getOverallFleetSize());

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

			Scores scores = scorer.scoreEvent(event2);
			Assert.assertEquals(EVENT2_FLEET, scores.getOverallFleetSize());

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

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}