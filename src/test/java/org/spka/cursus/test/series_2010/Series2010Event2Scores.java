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
package org.spka.cursus.test.series_2010;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Predicates;

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
			Scores scores = scorer.scoreSeries(series, Predicates.in(getSeriesResultsPilots(series)));
			checkSeriesAtEvent2(scores);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkSeriesAtEvent2() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);

			List<Race> races = new ArrayList<Race>();
			races.addAll(event1.getRaces());
			races.addAll(event2.getRaces());

			Scores scores = scorer.scoreRaces(races, getSeriesResultsPilots(series), Predicates.in(getSeriesResultsPilots(series)));
			checkSeriesAtEvent2(scores);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	private void checkSeriesAtEvent2(Scores scores) throws Exception {
		Series series = seriesDAO.find(SERIES_NAME);
		Event event1 = eventDAO.find(series, EVENT1_NAME);
		Race race1 = raceDAO.find(event1, RACE1_NAME);
		Race race2 = raceDAO.find(event1, RACE2_NAME);
		Race race3 = raceDAO.find(event1, RACE3_NAME);
		Event event2 = eventDAO.find(series, EVENT2_NAME);
		Race race4 = raceDAO.find(event2, RACE4_NAME);

		Assert.assertEquals(SERIES_FLEET, scores.getPilots().size());
		Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race1));
		Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race2));
		Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race3));
		Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race4));

		RaceAssertUtil race1AssertUtil = new RaceAssertUtil(scores, race1);
		race1AssertUtil.assertPilot(sco200, 5, 0, false, 0, 1);
		race1AssertUtil.assertPilot(sco179, 5, 0, false, 2, 2);
		race1AssertUtil.assertPilot(sco019, 5, 0, false, 3, 3);
		race1AssertUtil.assertPilot(sco808, 0, 0, true, 3, 4);
		race1AssertUtil.assertPilot(sco159, 4, 0, false, 4, 5);
		race1AssertUtil.assertPilot(sco081, 4, 0, false, 5, 6);
		race1AssertUtil.assertPilot(sco116, 3, 0, false, 6, 7);
		race1AssertUtil.assertPilot(sco068, 2, 0, false, 7, 8);
		race1AssertUtil.assertPilot(sco087, 0, 0, true, 7, 9);
		race1AssertUtil.assertPilot(sco248, 2, 0, false, 8, 10);
		race1AssertUtil.assertPilot(sco249, 2, 0, false, 9, 11);
		race1AssertUtil.assertPilot(sco042, 2, 0, false, 10, 12);
		race1AssertUtil.assertPilot(sco018, 2, 0, false, 11, 13);
		race1AssertUtil.assertPilot(sco060, 1, 0, false, 12, 14);
		race1AssertUtil.assertPilot(sco136, 0, 0, false, 18, 15);
		race1AssertUtil.assertPilot(sco153, 0, 0, false, 18, 15);
		race1AssertUtil.assertPilot(sco156, 0, 0, false, 18, 15);
		race1AssertUtil.assertPilot(sco158, 0, 0, false, 18, 15);
		race1AssertUtil.assertPilot(sco197, 0, 0, false, 18, 15);
		race1AssertUtil.assertDone(2);

		RaceAssertUtil race2AssertUtil = new RaceAssertUtil(scores, race2);
		race2AssertUtil.assertPilot(sco808, 5, 0, false, 0, 1);
		race2AssertUtil.assertPilot(sco200, 0, 0, true, 0, 2);
		race2AssertUtil.assertPilot(sco019, 5, 0, false, 2, 3);
		race2AssertUtil.assertPilot(sco068, 4, 0, false, 3, 4);
		race2AssertUtil.assertPilot(sco179, 4, 0, false, 4, 5);
		race2AssertUtil.assertPilot(sco159, 0, 0, true, 4, 6);
		race2AssertUtil.assertPilot(sco116, 4, 0, false, 5, 7);
		race2AssertUtil.assertPilot(sco081, 3, 0, false, 6, 8);
		race2AssertUtil.assertPilot(sco248, 3, 0, false, 7, 9);
		race2AssertUtil.assertPilot(sco249, 3, 0, false, 8, 10);
		race2AssertUtil.assertPilot(sco018, 3, 0, false, 9, 11);
		race2AssertUtil.assertPilot(sco060, 2, 0, false, 10, 12);
		race2AssertUtil.assertPilot(sco042, 2, 0, false, 11, 13);
		race2AssertUtil.assertPilot(sco197, 2, 0, false, 12, 14);
		race2AssertUtil.assertPilot(sco153, 2, 0, false, 13, 15);
		race2AssertUtil.assertPilot(sco087, 0, 0, false, 18, 16);
		race2AssertUtil.assertPilot(sco136, 0, 0, false, 18, 16);
		race2AssertUtil.assertPilot(sco156, 0, 0, false, 18, 16);
		race2AssertUtil.assertPilot(sco158, 0, 0, false, 18, 16);
		race2AssertUtil.assertDone(2);

		RaceAssertUtil race3AssertUtil = new RaceAssertUtil(scores, race3);
		race3AssertUtil.assertPilot(sco200, 7, 0, false, 0, 1);
		race3AssertUtil.assertPilot(sco081, 7, 0, false, 2, 2);
		race3AssertUtil.assertPilot(sco159, 6, 0, false, 3, 3);
		race3AssertUtil.assertPilot(sco179, 0, 0, true, 3, 4);
		race3AssertUtil.assertPilot(sco068, 6, 0, false, 4, 5);
		race3AssertUtil.assertPilot(sco060, 5, 0, false, 5, 6);
		race3AssertUtil.assertPilot(sco248, 4, 0, false, 6, 7);
		race3AssertUtil.assertPilot(sco087, 4, 1, false, 7, 8);
		race3AssertUtil.assertPilot(sco116, 3, 0, false, 8, 9);
		race3AssertUtil.assertPilot(sco249, 3, 0, false, 9, 10);
		race3AssertUtil.assertPilot(sco042, 3, 0, false, 10, 11);
		race3AssertUtil.assertPilot(sco019, 2, 0, false, 11, 12);
		race3AssertUtil.assertPilot(sco018, 1, 0, false, 12, 13);
		race3AssertUtil.assertPilot(sco153, 1, 0, false, 13, 14);
		race3AssertUtil.assertPilot(sco197, 0, 0, true, 13, 15);
		race3AssertUtil.assertPilot(sco808, 1, 0, false, 14, 16);
		race3AssertUtil.assertPilot(sco136, 0, 0, false, 18, 17);
		race3AssertUtil.assertPilot(sco156, 0, 0, false, 18, 17);
		race3AssertUtil.assertPilot(sco158, 0, 0, false, 18, 17);
		race3AssertUtil.assertDone(2);

		RaceAssertUtil race4AssertUtil = new RaceAssertUtil(scores, race4);
		race4AssertUtil.assertPilot(sco068, 6, 0, false, 0, 1);
		race4AssertUtil.assertPilot(sco200, 6, 0, false, 2, 2);
		race4AssertUtil.assertPilot(sco179, 6, 0, false, 3, 3);
		race4AssertUtil.assertPilot(sco019, 0, 0, true, 3, 4);
		race4AssertUtil.assertPilot(sco159, 5, 0, false, 4, 5);
		race4AssertUtil.assertPilot(sco808, 5, 0, false, 5, 6);
		race4AssertUtil.assertPilot(sco116, 5, 0, false, 6, 7);
		race4AssertUtil.assertPilot(sco087, 4, 0, false, 7, 8);
		race4AssertUtil.assertPilot(sco081, 1, 0, false, 8, 9);
		race4AssertUtil.assertPilot(sco060, 0, 0, true, 8, 10);
		race4AssertUtil.assertPilot(sco018, 0, 0, false, 14, 11);
		race4AssertUtil.assertPilot(sco042, 0, 0, false, 14, 11);
		race4AssertUtil.assertPilot(sco136, 0, 0, false, 14, 11);
		race4AssertUtil.assertPilot(sco153, 0, 0, false, 14, 11);
		race4AssertUtil.assertPilot(sco156, 0, 0, false, 14, 11);
		race4AssertUtil.assertPilot(sco158, 0, 0, false, 14, 11);
		race4AssertUtil.assertPilot(sco197, 0, 0, false, 14, 11);
		race4AssertUtil.assertPilot(sco248, 0, 0, false, 14, 11);
		race4AssertUtil.assertPilot(sco249, 0, 0, false, 14, 11);
		race4AssertUtil.assertDone(2);

		OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
		overallAssertUtil.assertPilot(sco200, 0, 0, 1, 2);
		overallAssertUtil.assertPilot(sco068, 0, 7, 2, 7);
		overallAssertUtil.assertPilot(sco808, 0, 8, 3, 14);
		overallAssertUtil.assertPilot(sco019, 0, 8, 4, 11);
		overallAssertUtil.assertPilot(sco179, 0, 8, 4, 4);
		overallAssertUtil.assertPilot(sco159, 0, 11, 6, 4);
		overallAssertUtil.assertPilot(sco081, 0, 13, 7, 8);
		overallAssertUtil.assertPilot(sco116, 0, 17, 8, 8);
		overallAssertUtil.assertPilot(sco248, 0, 21, 9, 14);
		overallAssertUtil.assertPilot(sco087, 1, 22, 10, 18);
		overallAssertUtil.assertPilot(sco060, 0, 23, 11, 12);
		overallAssertUtil.assertPilot(sco249, 0, 26, 12, 14);
		overallAssertUtil.assertPilot(sco042, 0, 31, 13, 14);
		overallAssertUtil.assertPilot(sco018, 0, 32, 14, 14);
		overallAssertUtil.assertPilot(sco197, 0, 39, 15, 18);
		overallAssertUtil.assertPilot(sco153, 0, 40, 16, 18);
		overallAssertUtil.assertPilot(sco136, 0, 50, 17, 18);
		overallAssertUtil.assertPilot(sco156, 0, 50, 17, 18);
		overallAssertUtil.assertPilot(sco158, 0, 50, 17, 18);
		overallAssertUtil.assertOrder();
	}

	@Test
	public final void checkEvent2() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race4 = raceDAO.find(event2, RACE4_NAME);

			Scores scores = scorer.scoreEvent(event2, Predicates.in(getEventResultsPilots(series, event2)));
			Assert.assertEquals(EVENT2_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race4));

			RaceAssertUtil race4AssertUtil = new RaceAssertUtil(scores, race4);
			race4AssertUtil.assertPilot(sco068, 6, 0, false, 0, 1);
			race4AssertUtil.assertPilot(sco200, 6, 0, false, 2, 2);
			race4AssertUtil.assertPilot(sco179, 6, 0, false, 3, 3);
			race4AssertUtil.assertPilot(sco159, 5, 0, false, 4, 4);
			race4AssertUtil.assertPilot(sco808, 5, 0, false, 5, 5);
			race4AssertUtil.assertPilot(sco116, 5, 0, false, 6, 6);
			race4AssertUtil.assertPilot(sco087, 4, 0, false, 7, 7);
			race4AssertUtil.assertPilot(sco081, 1, 0, false, 8, 8);
			race4AssertUtil.assertPilot(sco019, 0, 0, true, 14, 9);
			race4AssertUtil.assertPilot(sco060, 0, 0, true, 14, 9);
			race4AssertUtil.assertPilot(sco153, 0, 0, false, 14, 9);
			race4AssertUtil.assertPilot(sco156, 0, 0, false, 14, 9);
			race4AssertUtil.assertPilot(sco197, 0, 0, false, 14, 9);
			race4AssertUtil.assertDone(2);

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
	public final void checkRace4() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race4 = raceDAO.find(event2, RACE4_NAME);

			Scores scores = scorer.scoreRace(race4, Predicates.in(getEventResultsPilots(series, event2)));
			Assert.assertEquals(EVENT2_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race4));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race4);
			raceAssertUtil.assertPilot(sco068, 6, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco200, 6, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco179, 6, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco159, 5, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco808, 5, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco116, 5, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco087, 4, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco081, 1, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco019, 0, 0, true, 14, 9);
			raceAssertUtil.assertPilot(sco060, 0, 0, true, 14, 9);
			raceAssertUtil.assertPilot(sco153, 0, 0, false, 14, 9);
			raceAssertUtil.assertPilot(sco156, 0, 0, false, 14, 9);
			raceAssertUtil.assertPilot(sco197, 0, 0, false, 14, 9);
			raceAssertUtil.assertDone(2);

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