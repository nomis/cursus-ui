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
package org.spka.cursus.test.series_2011;

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
 * Scores at the end of event 2 (29/01/2012)
 */
public class Series2011Event2Scores extends Series2011Event1Scores {
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
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Scores scores = scorer.scoreSeries(series, getSeriesResultsPilots(series, event2), Predicates.in(getSeriesResultsPilots(series, event2)));
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

			Scores scores = scorer.scoreRaces(races, getSeriesResultsPilots(series, event2), Predicates.in(getSeriesResultsPilots(series, event2)));
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
		Event event2 = eventDAO.find(series, EVENT2_NAME);
		Race race2 = raceDAO.find(event2, RACE2_NAME);
		Race race3 = raceDAO.find(event2, RACE3_NAME);
		Race race4 = raceDAO.find(event2, RACE4_NAME);

		Assert.assertEquals(SERIES_FLEET_AT_EVENT2, scores.getPilots().size());
		Assert.assertEquals(SERIES_FLEET_AT_EVENT2, scores.getFleetSize(race1));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT2, scores.getFleetSize(race2));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT2, scores.getFleetSize(race3));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT2, scores.getFleetSize(race4));

		RaceAssertUtil race1AssertUtil = new RaceAssertUtil(scores, race1);
		race1AssertUtil.assertPilot(sco068, 5, 0, false, 0, 1);
		race1AssertUtil.assertPilot(sco200, 5, 0, false, 2, 2);
		race1AssertUtil.assertPilot(sco179, 5, 0, false, 3, 3);
		race1AssertUtil.assertPilot(sco808, 5, 0, false, 4, 4);
		race1AssertUtil.assertPilot(sco116, 5, 0, false, 5, 5);
		race1AssertUtil.assertPilot(sco081, 5, 0, false, 6, 6);
		race1AssertUtil.assertPilot(sco136, 4, 0, false, 7, 7);
		race1AssertUtil.assertPilot(sco248, 4, 0, false, 8, 8);
		race1AssertUtil.assertPilot(sco528, 4, 0, false, 9, 9);
		race1AssertUtil.assertPilot(sco467, 4, 0, false, 10, 10);
		race1AssertUtil.assertPilot(sco159, 4, 0, false, 11, 11);
		race1AssertUtil.assertPilot(sco019, 3, 0, false, 12, 12);
		race1AssertUtil.assertPilot(sco018, 3, 0, false, 13, 13);
		race1AssertUtil.assertPilot(sco198, 3, 0, false, 14, 14);
		race1AssertUtil.assertPilot(sco087, 3, 0, false, 15, 15);
		race1AssertUtil.assertPilot(sco197, 2, 0, false, 16, 16);
		race1AssertUtil.assertPilot(sco158, 2, 0, false, 17, 17);
		race1AssertUtil.assertPilot(sco320, 1, 0, false, 18, 18);
		race1AssertUtil.assertPilot(sco249, 1, 0, false, 19, 19);
		race1AssertUtil.assertPilot(sco060, 0, 0, false, 23, 20);
		race1AssertUtil.assertPilot(sco153, 0, 0, false, 23, 20);
		race1AssertUtil.assertPilot(sco156, 0, 0, false, 23, 20);
		race1AssertUtil.assertDone(0);

		RaceAssertUtil race2AssertUtil = new RaceAssertUtil(scores, race2);
		race2AssertUtil.assertPilot(sco200, 6, 0, false, 0, 1);
		race2AssertUtil.assertPilot(sco808, 6, 1, false, 2, 2);
		race2AssertUtil.assertPilot(sco068, 5, 0, false, 3, 3);
		race2AssertUtil.assertPilot(sco179, 5, 0, false, 4, 4);
		race2AssertUtil.assertPilot(sco081, 5, 0, false, 5, 5);
		race2AssertUtil.assertPilot(sco528, 4, 0, false, 6, 6);
		race2AssertUtil.assertPilot(sco159, 4, 0, false, 7, 7);
		race2AssertUtil.assertPilot(sco249, 4, 0, false, 8, 8);
		race2AssertUtil.assertPilot(sco116, 3, 0, false, 9, 9);
		race2AssertUtil.assertPilot(sco248, 3, 0, false, 10, 10);
		race2AssertUtil.assertPilot(sco467, 3, 0, false, 11, 11);
		race2AssertUtil.assertPilot(sco087, 3, 0, false, 12, 12);
		race2AssertUtil.assertPilot(sco019, 1, 1, false, 13, 13); // 2, 1, 13, 13
		race2AssertUtil.assertPilot(sco153, 1, 0, false, 14, 14); // 2, 0, 14, 14
		race2AssertUtil.assertPilot(sco198, 0, 0, true, 15, 15);
		race2AssertUtil.assertPilot(sco197, 0, 0, true, 20, 16);
		race2AssertUtil.assertPilot(sco060, 0, 0, false, 23, 17);
		race2AssertUtil.assertPilot(sco136, 0, 0, false, 23, 17);
		race2AssertUtil.assertPilot(sco156, 0, 0, false, 23, 17);
		race2AssertUtil.assertPilot(sco320, 0, 0, false, 23, 17);
		race2AssertUtil.assertPilot(sco018, 0, 0, false, 24, 21);
		race2AssertUtil.assertPilot(sco158, 0, 0, false, 24, 21);
		race2AssertUtil.assertDone(2);

		RaceAssertUtil race3AssertUtil = new RaceAssertUtil(scores, race3);
		race3AssertUtil.assertPilot(sco200, 7, 0, false, 0, 1);
		race3AssertUtil.assertPilot(sco081, 6, 0, false, 2, 2);
		race3AssertUtil.assertPilot(sco808, 0, 0, true, 2, 3);
		race3AssertUtil.assertPilot(sco068, 6, 0, false, 3, 4);
		race3AssertUtil.assertPilot(sco179, 0, 0, true, 3, 5);
		race3AssertUtil.assertPilot(sco159, 5, 0, false, 4, 6);
		race3AssertUtil.assertPilot(sco116, 5, 0, false, 5, 7);
		race3AssertUtil.assertPilot(sco528, 5, 0, false, 6, 8);
		race3AssertUtil.assertPilot(sco249, 5, 0, false, 7, 9);
		race3AssertUtil.assertPilot(sco136, 5, 0, false, 8, 10);
		race3AssertUtil.assertPilot(sco248, 4, 0, false, 9, 11);
		race3AssertUtil.assertPilot(sco087, 4, 0, false, 10, 12);
		race3AssertUtil.assertPilot(sco467, 4, 0, false, 11, 13);
		race3AssertUtil.assertPilot(sco060, 3, 0, false, 12, 14);
		race3AssertUtil.assertPilot(sco019, 3, 0, false, 13, 15);
		race3AssertUtil.assertPilot(sco153, 2, 0, false, 14, 16);
		race3AssertUtil.assertPilot(sco198, 1, 0, false, 15, 17);
		race3AssertUtil.assertPilot(sco156, 0, 0, false, 23, 18);
		race3AssertUtil.assertPilot(sco197, 0, 0, false, 23, 18);
		race3AssertUtil.assertPilot(sco320, 0, 0, false, 23, 18);
		race3AssertUtil.assertPilot(sco018, 0, 0, false, 24, 21);
		race3AssertUtil.assertPilot(sco158, 0, 0, false, 24, 21);
		race3AssertUtil.assertDone(2);

		RaceAssertUtil race4AssertUtil = new RaceAssertUtil(scores, race4);
		race4AssertUtil.assertPilot(sco200, 7, 0, false, 0, 1);
		race4AssertUtil.assertPilot(sco808, 7, 0, false, 2, 2);
		race4AssertUtil.assertPilot(sco179, 6, 0, false, 3, 3);
		race4AssertUtil.assertPilot(sco068, 6, 0, false, 4, 4);
		race4AssertUtil.assertPilot(sco159, 6, 0, false, 5, 5);
		race4AssertUtil.assertPilot(sco116, 5, 0, false, 6, 6);
		race4AssertUtil.assertPilot(sco528, 5, 0, false, 7, 7);
		race4AssertUtil.assertPilot(sco249, 4, 0, false, 8, 8);
		race4AssertUtil.assertPilot(sco136, 0, 0, true, 8, 9);
		race4AssertUtil.assertPilot(sco248, 4, 0, false, 9, 10);
		race4AssertUtil.assertPilot(sco087, 4, 0, false, 10, 11);
		race4AssertUtil.assertPilot(sco081, 2, 0, false, 11, 12);
		race4AssertUtil.assertPilot(sco467, 2, 0, false, 12, 13);
		race4AssertUtil.assertPilot(sco153, 2, 0, false, 13, 14);
		race4AssertUtil.assertPilot(sco060, 0, 0, true, 18, 15);
		race4AssertUtil.assertPilot(sco019, 0, 0, false, 23, 16);
		race4AssertUtil.assertPilot(sco156, 0, 0, false, 23, 16);
		race4AssertUtil.assertPilot(sco197, 0, 0, false, 23, 16);
		race4AssertUtil.assertPilot(sco198, 0, 0, false, 23, 16);
		race4AssertUtil.assertPilot(sco320, 0, 0, false, 23, 16);
		race4AssertUtil.assertPilot(sco018, 0, 0, false, 24, 21);
		race4AssertUtil.assertPilot(sco158, 0, 0, false, 24, 21);
		race4AssertUtil.assertDone(2);

		OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
		overallAssertUtil.assertPilot(sco200, 0, 0, 1, 2);
		overallAssertUtil.assertPilot(sco068, 0, 6, 2, 4);
		overallAssertUtil.assertPilot(sco808, 1, 7, 3, 4);
		overallAssertUtil.assertPilot(sco179, 0, 9, 4, 4);
		overallAssertUtil.assertPilot(sco081, 0, 13, 5, 11);
		overallAssertUtil.assertPilot(sco159, 0, 16, 6, 11);
		overallAssertUtil.assertPilot(sco116, 0, 16, 7, 9);
		overallAssertUtil.assertPilot(sco528, 0, 19, 8, 9);
		overallAssertUtil.assertPilot(sco136, 0, 23, 9, 23);
		overallAssertUtil.assertPilot(sco249, 0, 23, 9, 19);
		overallAssertUtil.assertPilot(sco248, 0, 26, 11, 10);
		overallAssertUtil.assertPilot(sco087, 0, 32, 12, 15);
		overallAssertUtil.assertPilot(sco467, 0, 32, 13, 12);
		overallAssertUtil.assertPilot(sco019, 1, 39, 14, 23);
		overallAssertUtil.assertPilot(sco153, 0, 41, 15, 23);
		overallAssertUtil.assertPilot(sco198, 0, 44, 16, 23);
		overallAssertUtil.assertPilot(sco060, 0, 53, 17, 23);
		overallAssertUtil.assertPilot(sco197, 0, 59, 18, 23);
		overallAssertUtil.assertPilot(sco018, 0, 61, 19, 24);
		overallAssertUtil.assertPilot(sco320, 0, 64, 20, 23);
		overallAssertUtil.assertPilot(sco158, 0, 65, 21, 24);
		overallAssertUtil.assertPilot(sco156, 0, 69, 22, 23);
		overallAssertUtil.assertOrder();

		debugPrintScores(scores);
	}

	@Test
	public final void checkEvent2() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race2 = raceDAO.find(event2, RACE2_NAME);
			Race race3 = raceDAO.find(event2, RACE3_NAME);
			Race race4 = raceDAO.find(event2, RACE4_NAME);

			Scores scores = scorer.scoreEvent(event2, Predicates.in(getEventResultsPilots(series, event2)));
			Assert.assertEquals(EVENT2_PILOTS, scores.getPilots().size());
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race2));
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race3));
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race4));

			RaceAssertUtil race2AssertUtil = new RaceAssertUtil(scores, race2);
			race2AssertUtil.assertPilot(sco200, 6, 0, false, 0, 1);
			race2AssertUtil.assertPilot(b1045, 6, 0, false, 2, 2); // 5, 0, 3, 3
			race2AssertUtil.assertPilot(sco808, 6, 1, false, 3, 3); // 6, 1, 2, 2
			race2AssertUtil.assertPilot(sco068, 5, 0, false, 4, 4);
			race2AssertUtil.assertPilot(sco179, 5, 0, false, 5, 5);
			race2AssertUtil.assertPilot(sco081, 5, 0, false, 6, 6);
			race2AssertUtil.assertPilot(sco528, 4, 0, false, 7, 7);
			race2AssertUtil.assertPilot(sco159, 4, 0, false, 8, 8);
			race2AssertUtil.assertPilot(sco249, 4, 0, false, 9, 9);
			race2AssertUtil.assertPilot(sco116, 3, 0, false, 10, 10);
			race2AssertUtil.assertPilot(sco248, 3, 0, false, 11, 11);
			race2AssertUtil.assertPilot(sco467, 3, 0, false, 12, 12);
			race2AssertUtil.assertPilot(sco087, 3, 0, false, 13, 13);
			race2AssertUtil.assertPilot(sco019, 1, 1, false, 14, 14); // 2, 1, 14, 14
			race2AssertUtil.assertPilot(sco153, 1, 0, false, 15, 15); // 2, 0, 15, 15
			race2AssertUtil.assertPilot(sco198, 0, 0, true, 20, 16);
			race2AssertUtil.assertPilot(sco060, 0, 0, false, 24, 17);
			race2AssertUtil.assertPilot(sco136, 0, 0, false, 24, 17);
			race2AssertUtil.assertPilot(sco156, 0, 0, false, 24, 17);
			race2AssertUtil.assertPilot(sco197, 0, 0, true, 24, 17);
			race2AssertUtil.assertPilot(sco320, 0, 0, false, 24, 17);
			race2AssertUtil.assertDone(2);

			RaceAssertUtil race3AssertUtil = new RaceAssertUtil(scores, race3);
			race3AssertUtil.assertPilot(sco200, 7, 0, false, 0, 1);
			race3AssertUtil.assertPilot(b1045, 7, 0, false, 2, 2);
			race3AssertUtil.assertPilot(sco081, 6, 0, false, 3, 3);
			race3AssertUtil.assertPilot(sco808, 0, 0, true, 3, 4);
			race3AssertUtil.assertPilot(sco068, 6, 0, false, 4, 5);
			race3AssertUtil.assertPilot(sco159, 5, 0, false, 5, 6);
			race3AssertUtil.assertPilot(sco179, 0, 0, true, 5, 7);
			race3AssertUtil.assertPilot(sco116, 5, 0, false, 6, 8);
			race3AssertUtil.assertPilot(sco528, 5, 0, false, 7, 9);
			race3AssertUtil.assertPilot(sco249, 5, 0, false, 8, 10);
			race3AssertUtil.assertPilot(sco136, 5, 0, false, 9, 11);
			race3AssertUtil.assertPilot(sco248, 4, 0, false, 10, 12);
			race3AssertUtil.assertPilot(sco087, 4, 0, false, 11, 13);
			race3AssertUtil.assertPilot(sco467, 4, 0, false, 12, 14);
			race3AssertUtil.assertPilot(sco060, 3, 0, false, 13, 15);
			race3AssertUtil.assertPilot(sco019, 3, 0, false, 14, 16);
			race3AssertUtil.assertPilot(sco153, 2, 0, false, 15, 17);
			race3AssertUtil.assertPilot(sco198, 1, 0, false, 16, 18);
			race3AssertUtil.assertPilot(sco156, 0, 0, false, 24, 19);
			race3AssertUtil.assertPilot(sco197, 0, 0, false, 24, 19);
			race3AssertUtil.assertPilot(sco320, 0, 0, false, 24, 19);
			race3AssertUtil.assertDone(2);

			RaceAssertUtil race4AssertUtil = new RaceAssertUtil(scores, race4);
			race4AssertUtil.assertPilot(sco200, 7, 0, false, 0, 1);
			race4AssertUtil.assertPilot(b1045, 7, 0, false, 2, 2);
			race4AssertUtil.assertPilot(sco808, 7, 0, false, 3, 3);
			race4AssertUtil.assertPilot(sco179, 6, 0, false, 4, 4);
			race4AssertUtil.assertPilot(sco068, 6, 0, false, 5, 5);
			race4AssertUtil.assertPilot(sco159, 6, 0, false, 6, 6);
			race4AssertUtil.assertPilot(sco116, 5, 0, false, 7, 7);
			race4AssertUtil.assertPilot(sco528, 5, 0, false, 8, 8);
			race4AssertUtil.assertPilot(sco249, 4, 0, false, 9, 9);
			race4AssertUtil.assertPilot(sco248, 4, 0, false, 10, 10);
			race4AssertUtil.assertPilot(sco087, 4, 0, false, 11, 11);
			race4AssertUtil.assertPilot(sco081, 2, 0, false, 12, 12);
			race4AssertUtil.assertPilot(sco467, 2, 0, false, 13, 13);
			race4AssertUtil.assertPilot(sco153, 2, 0, false, 14, 14);
			race4AssertUtil.assertPilot(sco136, 0, 0, true, 17, 15);
			race4AssertUtil.assertPilot(sco060, 0, 0, true, 19, 16);
			race4AssertUtil.assertPilot(sco019, 0, 0, false, 24, 17);
			race4AssertUtil.assertPilot(sco156, 0, 0, false, 24, 17);
			race4AssertUtil.assertPilot(sco197, 0, 0, false, 24, 17);
			race4AssertUtil.assertPilot(sco198, 0, 0, false, 24, 17);
			race4AssertUtil.assertPilot(sco320, 0, 0, false, 24, 17);
			race4AssertUtil.assertDone(2);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(b1045, 0, 6, 2); // 0, 7, 2
			overallAssertUtil.assertPilot(sco808, 1, 10, 3); // 1, 9, 3
			overallAssertUtil.assertPilot(sco068, 0, 13, 4);
			overallAssertUtil.assertPilot(sco179, 0, 14, 5);
			overallAssertUtil.assertPilot(sco159, 0, 19, 6);
			overallAssertUtil.assertPilot(sco081, 0, 21, 7);
			overallAssertUtil.assertPilot(sco528, 0, 22, 8);
			overallAssertUtil.assertPilot(sco116, 0, 23, 9);
			overallAssertUtil.assertPilot(sco249, 0, 26, 10);
			overallAssertUtil.assertPilot(sco248, 0, 31, 11);
			overallAssertUtil.assertPilot(sco087, 0, 35, 12);
			overallAssertUtil.assertPilot(sco467, 0, 37, 13);
			overallAssertUtil.assertPilot(sco153, 0, 44, 14);
			overallAssertUtil.assertPilot(sco136, 0, 50, 15);
			overallAssertUtil.assertPilot(sco019, 1, 53, 16);
			overallAssertUtil.assertPilot(sco060, 0, 56, 17);
			overallAssertUtil.assertPilot(sco198, 0, 60, 18);
			overallAssertUtil.assertPilot(sco156, 0, 72, 19);
			overallAssertUtil.assertPilot(sco197, 0, 72, 19);
			overallAssertUtil.assertPilot(sco320, 0, 72, 19);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace2() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race2 = raceDAO.find(event2, RACE2_NAME);

			Scores scores = scorer.scoreRace(race2, Predicates.in(getEventResultsPilots(series, event2)));
			Assert.assertEquals(EVENT2_PILOTS, scores.getPilots().size());
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race2));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race2);
			raceAssertUtil.assertPilot(sco200, 6, 0, false, 0, 1);
			raceAssertUtil.assertPilot(b1045, 6, 0, false, 2, 2); // 5, 0, 3, 3
			raceAssertUtil.assertPilot(sco808, 6, 1, false, 3, 3); // 6, 1, 2, 2
			raceAssertUtil.assertPilot(sco068, 5, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco179, 5, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco081, 5, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco528, 4, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco159, 4, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco249, 4, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco116, 3, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco248, 3, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco467, 3, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco087, 3, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco019, 1, 1, false, 14, 14); // 2, 1, 14, 14
			raceAssertUtil.assertPilot(sco153, 1, 0, false, 15, 15); // 2, 0, 15, 15
			raceAssertUtil.assertPilot(sco060, 0, 0, false, 24, 16);
			raceAssertUtil.assertPilot(sco136, 0, 0, false, 24, 16);
			raceAssertUtil.assertPilot(sco156, 0, 0, false, 24, 16);
			raceAssertUtil.assertPilot(sco197, 0, 0, true, 24, 16);
			raceAssertUtil.assertPilot(sco198, 0, 0, true, 24, 16);
			raceAssertUtil.assertPilot(sco320, 0, 0, false, 24, 16);
			raceAssertUtil.assertDone(2);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(b1045, 0, 2, 2); // 0, 3, 3
			overallAssertUtil.assertPilot(sco808, 1, 4, 3); // 1, 3, 2
			overallAssertUtil.assertPilot(sco068, 0, 4, 4);
			overallAssertUtil.assertPilot(sco179, 0, 5, 5);
			overallAssertUtil.assertPilot(sco081, 0, 6, 6);
			overallAssertUtil.assertPilot(sco528, 0, 7, 7);
			overallAssertUtil.assertPilot(sco159, 0, 8, 8);
			overallAssertUtil.assertPilot(sco249, 0, 9, 9);
			overallAssertUtil.assertPilot(sco116, 0, 10, 10);
			overallAssertUtil.assertPilot(sco248, 0, 11, 11);
			overallAssertUtil.assertPilot(sco467, 0, 12, 12);
			overallAssertUtil.assertPilot(sco087, 0, 13, 13);
			overallAssertUtil.assertPilot(sco019, 1, 15, 14);
			overallAssertUtil.assertPilot(sco153, 0, 15, 15);
			overallAssertUtil.assertPilot(sco060, 0, 24, 16);
			overallAssertUtil.assertPilot(sco136, 0, 24, 16);
			overallAssertUtil.assertPilot(sco156, 0, 24, 16);
			overallAssertUtil.assertPilot(sco197, 0, 24, 16);
			overallAssertUtil.assertPilot(sco198, 0, 24, 16);
			overallAssertUtil.assertPilot(sco320, 0, 24, 16);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace3() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race3 = raceDAO.find(event2, RACE3_NAME);

			Scores scores = scorer.scoreRace(race3, Predicates.in(getEventResultsPilots(series, event2)));
			Assert.assertEquals(EVENT2_PILOTS, scores.getPilots().size());
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race3));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race3);
			raceAssertUtil.assertPilot(sco200, 7, 0, false, 0, 1);
			raceAssertUtil.assertPilot(b1045, 7, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco081, 6, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco068, 6, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco159, 5, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco116, 5, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco528, 5, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco249, 5, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco136, 5, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco248, 4, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco087, 4, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco467, 4, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco060, 3, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco019, 3, 0, false, 14, 14);
			raceAssertUtil.assertPilot(sco153, 2, 0, false, 15, 15);
			raceAssertUtil.assertPilot(sco198, 1, 0, false, 16, 16);
			raceAssertUtil.assertPilot(sco156, 0, 0, false, 24, 17);
			raceAssertUtil.assertPilot(sco179, 0, 0, true, 24, 17);
			raceAssertUtil.assertPilot(sco197, 0, 0, false, 24, 17);
			raceAssertUtil.assertPilot(sco320, 0, 0, false, 24, 17);
			raceAssertUtil.assertPilot(sco808, 0, 0, true, 24, 17);
			raceAssertUtil.assertDone(2);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(b1045, 0, 2, 2);
			overallAssertUtil.assertPilot(sco081, 0, 3, 3);
			overallAssertUtil.assertPilot(sco068, 0, 4, 4);
			overallAssertUtil.assertPilot(sco159, 0, 5, 5);
			overallAssertUtil.assertPilot(sco116, 0, 6, 6);
			overallAssertUtil.assertPilot(sco528, 0, 7, 7);
			overallAssertUtil.assertPilot(sco249, 0, 8, 8);
			overallAssertUtil.assertPilot(sco136, 0, 9, 9);
			overallAssertUtil.assertPilot(sco248, 0, 10, 10);
			overallAssertUtil.assertPilot(sco087, 0, 11, 11);
			overallAssertUtil.assertPilot(sco467, 0, 12, 12);
			overallAssertUtil.assertPilot(sco060, 0, 13, 13);
			overallAssertUtil.assertPilot(sco019, 0, 14, 14);
			overallAssertUtil.assertPilot(sco153, 0, 15, 15);
			overallAssertUtil.assertPilot(sco198, 0, 16, 16);
			overallAssertUtil.assertPilot(sco156, 0, 24, 17);
			overallAssertUtil.assertPilot(sco179, 0, 24, 17);
			overallAssertUtil.assertPilot(sco197, 0, 24, 17);
			overallAssertUtil.assertPilot(sco320, 0, 24, 17);
			overallAssertUtil.assertPilot(sco808, 0, 24, 17);
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
			Assert.assertEquals(EVENT2_PILOTS, scores.getPilots().size());
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race4));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race4);
			raceAssertUtil.assertPilot(sco200, 7, 0, false, 0, 1);
			raceAssertUtil.assertPilot(b1045, 7, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco808, 7, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco179, 6, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco068, 6, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco159, 6, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco116, 5, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco528, 5, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco249, 4, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco248, 4, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco087, 4, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco081, 2, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco467, 2, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco153, 2, 0, false, 14, 14);
			raceAssertUtil.assertPilot(sco019, 0, 0, false, 24, 15);
			raceAssertUtil.assertPilot(sco060, 0, 0, true, 24, 15);
			raceAssertUtil.assertPilot(sco136, 0, 0, true, 24, 15);
			raceAssertUtil.assertPilot(sco156, 0, 0, false, 24, 15);
			raceAssertUtil.assertPilot(sco197, 0, 0, false, 24, 15);
			raceAssertUtil.assertPilot(sco198, 0, 0, false, 24, 15);
			raceAssertUtil.assertPilot(sco320, 0, 0, false, 24, 15);
			raceAssertUtil.assertDone(2);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(b1045, 0, 2, 2);
			overallAssertUtil.assertPilot(sco808, 0, 3, 3);
			overallAssertUtil.assertPilot(sco179, 0, 4, 4);
			overallAssertUtil.assertPilot(sco068, 0, 5, 5);
			overallAssertUtil.assertPilot(sco159, 0, 6, 6);
			overallAssertUtil.assertPilot(sco116, 0, 7, 7);
			overallAssertUtil.assertPilot(sco528, 0, 8, 8);
			overallAssertUtil.assertPilot(sco249, 0, 9, 9);
			overallAssertUtil.assertPilot(sco248, 0, 10, 10);
			overallAssertUtil.assertPilot(sco087, 0, 11, 11);
			overallAssertUtil.assertPilot(sco081, 0, 12, 12);
			overallAssertUtil.assertPilot(sco467, 0, 13, 13);
			overallAssertUtil.assertPilot(sco153, 0, 14, 14);
			overallAssertUtil.assertPilot(sco019, 0, 24, 15);
			overallAssertUtil.assertPilot(sco060, 0, 24, 15);
			overallAssertUtil.assertPilot(sco136, 0, 24, 15);
			overallAssertUtil.assertPilot(sco156, 0, 24, 15);
			overallAssertUtil.assertPilot(sco197, 0, 24, 15);
			overallAssertUtil.assertPilot(sco198, 0, 24, 15);
			overallAssertUtil.assertPilot(sco320, 0, 24, 15);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}