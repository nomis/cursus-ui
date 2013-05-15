/*
	cursus - Race series management program
	Copyright 2012-2013  Simon Arlott

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
import eu.lp0.cursus.scoring.data.Scores;
import eu.lp0.cursus.test.util.OverallAssertUtil;
import eu.lp0.cursus.test.util.RaceAssertUtil;

/**
 * Scores at the end of event 1 (17/11/2012 to 18/11/2012)
 */
public class Series2012Event1Scores extends Series2012NonEvent2Scores {
	@Override
	@Before
	public void createData() throws Exception {
		super.createData();
		createEvent1Races();
	}

	@Test
	public void checkSeries() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Scores scores = scorer.scoreSeries(series, getSeriesResultsPilots(series, event1), Predicates.in(getSeriesResultsPilots(series, event1)));
			checkSeriesAtEvent1(scores);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkSeriesAtEvent1() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);

			List<Race> races = new ArrayList<Race>();
			races.addAll(event1.getRaces());

			Scores scores = scorer.scoreRaces(races, getSeriesResultsPilots(series, event1), getSeriesResultsEvents(series, event1),
					Predicates.in(getSeriesResultsPilots(series, event1)));
			checkSeriesAtEvent1(scores);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	private void checkSeriesAtEvent1(Scores scores) throws Exception {
		Series series = seriesDAO.find(SERIES_NAME);
		Event event1 = eventDAO.find(series, EVENT1_NAME);
		Race race1 = raceDAO.find(event1, RACE1_NAME);
		Race race2 = raceDAO.find(event1, RACE2_NAME);
		Race race3 = raceDAO.find(event1, RACE3_NAME);
		Race race4 = raceDAO.find(event1, RACE4_NAME);
		Race race5 = raceDAO.find(event1, RACE5_NAME);
		Race race6 = raceDAO.find(event1, RACE6_NAME);

		Assert.assertEquals(SERIES_FLEET_AT_EVENT1, scores.getPilots().size());
		Assert.assertEquals(RACE1_PILOTS, scores.getFleetSize(race1));
		Assert.assertEquals(RACE2_PILOTS, scores.getFleetSize(race2));
		Assert.assertEquals(RACE3_PILOTS, scores.getFleetSize(race3));
		Assert.assertEquals(RACE4_PILOTS, scores.getFleetSize(race4));
		Assert.assertEquals(RACE5_PILOTS, scores.getFleetSize(race5));
		Assert.assertEquals(RACE6_PILOTS, scores.getFleetSize(race6));

		RaceAssertUtil race1AssertUtil = new RaceAssertUtil(scores, race1);
		race1AssertUtil.assertPilot(sco200, 5, 0, false, 0, 1);
		race1AssertUtil.assertPilot(sco808, 5, 1, false, 2, 2);
		race1AssertUtil.assertPilot(sco159, 5, 0, false, 3, 3);
		race1AssertUtil.assertPilot(sco179, 5, 0, false, 4, 4);
		race1AssertUtil.assertPilot(sco116, 4, 0, false, 5, 5);
		race1AssertUtil.assertPilot(sco081, 4, 0, false, 6, 6);
		race1AssertUtil.assertPilot(sco076, 4, 0, false, 7, 7);
		race1AssertUtil.assertPilot(sco117, 3, 0, false, 8, 8);
		race1AssertUtil.assertPilot(sco777, 3, 0, false, 9, 9);
		race1AssertUtil.assertPilot(sco528, 2, 0, false, 10, 10);
		race1AssertUtil.assertPilot(sco158, 2, 0, false, 11, 11);
		race1AssertUtil.assertPilot(sco060, 1, 0, false, 12, 12);
		race1AssertUtil.assertPilot(sco315, 1, 0, false, 13, 13);
		race1AssertUtil.assertPilot(sco018, 0, 0, false, 17, 14);
		race1AssertUtil.assertPilot(sco153, 0, 0, false, 17, 14);
		race1AssertUtil.assertPilot(sco666, 0, 0, false, 17, 14);
		race1AssertUtil.assertPilot(sco100, 0, 0, false, 22, 17);
		race1AssertUtil.assertPilot(sco156, 0, 0, false, 22, 17);
		race1AssertUtil.assertPilot(sco320, 0, 0, false, 22, 17);
		race1AssertUtil.assertPilot(sco467, 0, 0, false, 22, 17);
		race1AssertUtil.assertPilot(sco561, 0, 0, false, 22, 17);
		race1AssertUtil.assertDone(0);

		RaceAssertUtil race2AssertUtil = new RaceAssertUtil(scores, race2);
		race2AssertUtil.assertPilot(sco808, 7, 0, false, 0, 1);
		race2AssertUtil.assertPilot(sco116, 7, 0, false, 2, 2);
		race2AssertUtil.assertPilot(sco200, 7, 0, false, 3, 3);
		race2AssertUtil.assertPilot(sco528, 6, 0, false, 4, 4);
		race2AssertUtil.assertPilot(sco159, 6, 0, false, 5, 5);
		race2AssertUtil.assertPilot(sco081, 6, 0, false, 6, 6);
		race2AssertUtil.assertPilot(sco076, 5, 0, false, 7, 7);
		race2AssertUtil.assertPilot(sco179, 4, 0, false, 8, 8);
		race2AssertUtil.assertPilot(sco060, 4, 0, false, 9, 9);
		race2AssertUtil.assertPilot(sco117, 4, 0, false, 10, 10);
		race2AssertUtil.assertPilot(sco018, 1, 0, false, 11, 11);
		race2AssertUtil.assertPilot(sco153, 0, 0, false, 17, 12);
		race2AssertUtil.assertPilot(sco158, 0, 0, false, 17, 12);
		race2AssertUtil.assertPilot(sco315, 0, 0, false, 17, 12);
		race2AssertUtil.assertPilot(sco666, 0, 0, false, 17, 12);
		race2AssertUtil.assertPilot(sco777, 0, 0, false, 17, 12);
		race2AssertUtil.assertPilot(sco100, 0, 0, false, 22, 17);
		race2AssertUtil.assertPilot(sco156, 0, 0, false, 22, 17);
		race2AssertUtil.assertPilot(sco320, 0, 0, false, 22, 17);
		race2AssertUtil.assertPilot(sco467, 0, 0, false, 22, 17);
		race2AssertUtil.assertPilot(sco561, 0, 0, false, 22, 17);
		race2AssertUtil.assertDone(0);

		RaceAssertUtil race3AssertUtil = new RaceAssertUtil(scores, race3);
		race3AssertUtil.assertPilot(sco179, 5, 0, false, 0, 1);
		race3AssertUtil.assertPilot(sco200, 5, 0, false, 2, 2);
		race3AssertUtil.assertPilot(sco116, 5, 0, false, 3, 3);
		race3AssertUtil.assertPilot(sco159, 5, 0, false, 4, 4);
		race3AssertUtil.assertPilot(sco528, 5, 0, false, 5, 5);
		race3AssertUtil.assertPilot(sco808, 4, 0, false, 6, 6);
		race3AssertUtil.assertPilot(sco117, 3, 0, false, 7, 7);
		race3AssertUtil.assertPilot(sco666, 3, 0, false, 8, 8);
		race3AssertUtil.assertPilot(sco060, 3, 0, false, 9, 9);
		race3AssertUtil.assertPilot(sco777, 2, 0, false, 10, 10);
		race3AssertUtil.assertPilot(sco315, 1, 0, false, 11, 11);
		race3AssertUtil.assertPilot(sco018, 0, 0, false, 17, 12);
		race3AssertUtil.assertPilot(sco076, 0, 0, false, 17, 12);
		race3AssertUtil.assertPilot(sco081, 0, 0, false, 17, 12);
		race3AssertUtil.assertPilot(sco153, 0, 0, false, 17, 12);
		race3AssertUtil.assertPilot(sco158, 0, 0, false, 17, 12);
		race3AssertUtil.assertPilot(sco100, 0, 0, false, 22, 17);
		race3AssertUtil.assertPilot(sco156, 0, 0, false, 22, 17);
		race3AssertUtil.assertPilot(sco320, 0, 0, false, 22, 17);
		race3AssertUtil.assertPilot(sco467, 0, 0, false, 22, 17);
		race3AssertUtil.assertPilot(sco561, 0, 0, false, 22, 17);
		race3AssertUtil.assertDone(0);

		RaceAssertUtil race4AssertUtil = new RaceAssertUtil(scores, race4);
		race4AssertUtil.assertPilot(sco179, 6, 0, false, 0, 1);
		race4AssertUtil.assertPilot(sco200, 6, 0, false, 2, 2);
		race4AssertUtil.assertPilot(sco159, 5, 1, false, 3, 3);
		race4AssertUtil.assertPilot(sco116, 5, 0, false, 4, 4);
		race4AssertUtil.assertPilot(sco666, 5, 0, false, 5, 5);
		race4AssertUtil.assertPilot(sco060, 3, 0, false, 6, 6);
		race4AssertUtil.assertPilot(sco528, 3, 0, false, 7, 7);
		race4AssertUtil.assertPilot(sco808, 2, 0, false, 8, 8);
		race4AssertUtil.assertPilot(sco117, 2, 0, false, 9, 9);
		race4AssertUtil.assertPilot(sco315, 1, 0, false, 10, 10);
		race4AssertUtil.assertPilot(sco777, 1, 0, false, 11, 11);
		race4AssertUtil.assertPilot(sco018, 0, 0, false, 17, 12);
		race4AssertUtil.assertPilot(sco076, 0, 0, false, 17, 12);
		race4AssertUtil.assertPilot(sco081, 0, 0, false, 17, 12);
		race4AssertUtil.assertPilot(sco153, 0, 0, false, 17, 12);
		race4AssertUtil.assertPilot(sco158, 0, 0, false, 17, 12);
		race4AssertUtil.assertPilot(sco100, 0, 0, false, 22, 17);
		race4AssertUtil.assertPilot(sco156, 0, 0, false, 22, 17);
		race4AssertUtil.assertPilot(sco320, 0, 0, false, 22, 17);
		race4AssertUtil.assertPilot(sco467, 0, 0, false, 22, 17);
		race4AssertUtil.assertPilot(sco561, 0, 0, false, 22, 17);
		race4AssertUtil.assertDone(0);

		RaceAssertUtil race5AssertUtil = new RaceAssertUtil(scores, race5);
		race5AssertUtil.assertPilot(sco200, 7, 0, false, 0, 1);
		race5AssertUtil.assertPilot(sco116, 7, 0, false, 2, 2);
		race5AssertUtil.assertPilot(sco528, 6, 0, false, 3, 3);
		race5AssertUtil.assertPilot(sco666, 5, 0, false, 4, 4);
		race5AssertUtil.assertPilot(sco159, 5, 0, false, 5, 5);
		race5AssertUtil.assertPilot(sco808, 5, 0, false, 6, 6);
		race5AssertUtil.assertPilot(sco179, 4, 0, false, 7, 7);
		race5AssertUtil.assertPilot(sco060, 4, 0, false, 8, 8);
		race5AssertUtil.assertPilot(sco117, 4, 0, false, 9, 9);
		race5AssertUtil.assertPilot(sco158, 1, 0, false, 10, 10);
		race5AssertUtil.assertPilot(sco018, 0, 0, false, 17, 11);
		race5AssertUtil.assertPilot(sco076, 0, 0, false, 17, 11);
		race5AssertUtil.assertPilot(sco081, 0, 0, false, 17, 11);
		race5AssertUtil.assertPilot(sco153, 0, 0, false, 17, 11);
		race5AssertUtil.assertPilot(sco315, 0, 0, false, 17, 11);
		race5AssertUtil.assertPilot(sco777, 0, 0, false, 17, 11);
		race5AssertUtil.assertPilot(sco100, 0, 0, false, 22, 17);
		race5AssertUtil.assertPilot(sco156, 0, 0, false, 22, 17);
		race5AssertUtil.assertPilot(sco320, 0, 0, false, 22, 17);
		race5AssertUtil.assertPilot(sco467, 0, 0, false, 22, 17);
		race5AssertUtil.assertPilot(sco561, 0, 0, false, 22, 17);
		race5AssertUtil.assertDone(0);

		RaceAssertUtil race6AssertUtil = new RaceAssertUtil(scores, race6);
		race6AssertUtil.assertPilot(sco808, 7, 0, false, 0, 1);
		race6AssertUtil.assertPilot(sco200, 7, 0, false, 2, 2);
		race6AssertUtil.assertPilot(sco528, 7, 0, false, 3, 3);
		race6AssertUtil.assertPilot(sco159, 7, 0, false, 4, 4);
		race6AssertUtil.assertPilot(sco179, 7, 0, false, 5, 5);
		race6AssertUtil.assertPilot(sco116, 6, 0, false, 6, 6);
		race6AssertUtil.assertPilot(sco666, 6, 0, false, 7, 7);
		race6AssertUtil.assertPilot(sco060, 3, 0, false, 8, 8);
		race6AssertUtil.assertPilot(sco158, 2, 0, false, 9, 9);
		race6AssertUtil.assertPilot(sco117, 1, 0, false, 10, 10);
		race6AssertUtil.assertPilot(sco018, 0, 0, false, 17, 11);
		race6AssertUtil.assertPilot(sco076, 0, 0, false, 17, 11);
		race6AssertUtil.assertPilot(sco081, 0, 0, false, 17, 11);
		race6AssertUtil.assertPilot(sco153, 0, 0, false, 17, 11);
		race6AssertUtil.assertPilot(sco315, 0, 0, false, 17, 11);
		race6AssertUtil.assertPilot(sco777, 0, 0, false, 17, 11);
		race6AssertUtil.assertPilot(sco100, 0, 0, false, 22, 17);
		race6AssertUtil.assertPilot(sco156, 0, 0, false, 22, 17);
		race6AssertUtil.assertPilot(sco320, 0, 0, false, 22, 17);
		race6AssertUtil.assertPilot(sco467, 0, 0, false, 22, 17);
		race6AssertUtil.assertPilot(sco561, 0, 0, false, 22, 17);
		race6AssertUtil.assertDone(0);

		OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
		overallAssertUtil.assertPilot(sco200, 0, 6, 1, 3);
		overallAssertUtil.assertPilot(sco808, 1, 15, 2, 8);
		overallAssertUtil.assertPilot(sco179, 0, 16, 3, 8);
		overallAssertUtil.assertPilot(sco116, 0, 16, 4, 6);
		overallAssertUtil.assertPilot(sco159, 1, 20, 5, 5);
		overallAssertUtil.assertPilot(sco528, 0, 22, 6, 10);
		overallAssertUtil.assertPilot(sco060, 0, 40, 7, 12);
		overallAssertUtil.assertPilot(sco666, 0, 41, 8, 17);
		overallAssertUtil.assertPilot(sco117, 0, 43, 9, 10);
		overallAssertUtil.assertPilot(sco081, 0, 63, 10, 17);
		overallAssertUtil.assertPilot(sco158, 0, 64, 11, 17);
		overallAssertUtil.assertPilot(sco777, 0, 64, 11, 17);
		overallAssertUtil.assertPilot(sco076, 0, 65, 13, 17);
		overallAssertUtil.assertPilot(sco315, 0, 68, 14, 17);
		overallAssertUtil.assertPilot(sco018, 0, 79, 15, 17);
		overallAssertUtil.assertPilot(sco153, 0, 85, 16, 17);
		overallAssertUtil.assertPilot(sco100, 0, 110, 17, 22);
		overallAssertUtil.assertPilot(sco156, 0, 110, 17, 22);
		overallAssertUtil.assertPilot(sco320, 0, 110, 17, 22);
		overallAssertUtil.assertPilot(sco467, 0, 110, 17, 22);
		overallAssertUtil.assertPilot(sco561, 0, 110, 17, 22);
		overallAssertUtil.assertOrder();
	}

	@Test
	public final void checkEvent1() throws Exception {
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

			Scores scores = scorer.scoreEvent(event1, Predicates.in(getEventResultsPilots(series, event1)));
			Assert.assertEquals(EVENT1_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race1));
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race2));
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race3));
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race4));
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race5));
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race6));

			RaceAssertUtil race1AssertUtil = new RaceAssertUtil(scores, race1);
			race1AssertUtil.assertPilot(sco200, 5, 0, false, 0, 1);
			race1AssertUtil.assertPilot(ir001, 5, 0, false, 2, 2);
			race1AssertUtil.assertPilot(sco808, 5, 1, false, 3, 3);
			race1AssertUtil.assertPilot(sco159, 5, 0, false, 4, 4);
			race1AssertUtil.assertPilot(sco179, 5, 0, false, 5, 5);
			race1AssertUtil.assertPilot(sco116, 4, 0, false, 6, 6);
			race1AssertUtil.assertPilot(ir085, 4, 0, false, 7, 7);
			race1AssertUtil.assertPilot(sco081, 4, 0, false, 8, 8);
			race1AssertUtil.assertPilot(sco076, 4, 0, false, 9, 9);
			race1AssertUtil.assertPilot(ir075, 3, 0, false, 10, 10);
			race1AssertUtil.assertPilot(sco117, 3, 0, false, 11, 11);
			race1AssertUtil.assertPilot(sco777, 3, 0, false, 12, 12);
			race1AssertUtil.assertPilot(sco528, 2, 0, false, 13, 13);
			race1AssertUtil.assertPilot(ir181, 2, 0, false, 14, 14);
			race1AssertUtil.assertPilot(sco158, 2, 0, false, 15, 15);
			race1AssertUtil.assertPilot(ir053, 1, 0, false, 16, 16);
			race1AssertUtil.assertPilot(sco060, 1, 0, false, 17, 17);
			race1AssertUtil.assertPilot(sco315, 1, 0, false, 18, 18);
			race1AssertUtil.assertPilot(sco018, 0, 0, false, 22, 19);
			race1AssertUtil.assertPilot(sco153, 0, 0, false, 22, 19);
			race1AssertUtil.assertPilot(sco666, 0, 0, false, 22, 19);
			race1AssertUtil.assertDone(0);

			RaceAssertUtil race2AssertUtil = new RaceAssertUtil(scores, race2);
			race2AssertUtil.assertPilot(sco808, 7, 0, false, 0, 1);
			race2AssertUtil.assertPilot(sco116, 7, 0, false, 2, 2);
			race2AssertUtil.assertPilot(sco200, 7, 0, false, 3, 3);
			race2AssertUtil.assertPilot(ir075, 6, 0, false, 4, 4);
			race2AssertUtil.assertPilot(sco528, 6, 0, false, 5, 5);
			race2AssertUtil.assertPilot(sco159, 6, 0, false, 6, 6);
			race2AssertUtil.assertPilot(sco081, 6, 0, false, 7, 7);
			race2AssertUtil.assertPilot(sco076, 5, 0, false, 8, 8);
			race2AssertUtil.assertPilot(ir001, 4, 0, false, 9, 9);
			race2AssertUtil.assertPilot(sco179, 4, 0, false, 10, 10);
			race2AssertUtil.assertPilot(sco060, 4, 0, false, 11, 11);
			race2AssertUtil.assertPilot(sco117, 4, 0, false, 12, 12);
			race2AssertUtil.assertPilot(ir181, 2, 0, false, 13, 13);
			race2AssertUtil.assertPilot(sco018, 1, 0, false, 14, 14);
			race2AssertUtil.assertPilot(ir053, 0, 0, false, 22, 15);
			race2AssertUtil.assertPilot(ir085, 0, 0, false, 22, 15);
			race2AssertUtil.assertPilot(sco153, 0, 0, false, 22, 15);
			race2AssertUtil.assertPilot(sco158, 0, 0, false, 22, 15);
			race2AssertUtil.assertPilot(sco315, 0, 0, false, 22, 15);
			race2AssertUtil.assertPilot(sco666, 0, 0, false, 22, 15);
			race2AssertUtil.assertPilot(sco777, 0, 0, false, 22, 15);
			race2AssertUtil.assertDone(0);

			RaceAssertUtil race3AssertUtil = new RaceAssertUtil(scores, race3);
			race3AssertUtil.assertPilot(sco179, 5, 0, false, 0, 1);
			race3AssertUtil.assertPilot(ir181, 5, 0, false, 2, 2);
			race3AssertUtil.assertPilot(sco200, 5, 0, false, 3, 3);
			race3AssertUtil.assertPilot(ir001, 5, 0, false, 4, 4);
			race3AssertUtil.assertPilot(sco116, 5, 0, false, 5, 5);
			race3AssertUtil.assertPilot(sco159, 5, 0, false, 6, 6);
			race3AssertUtil.assertPilot(sco528, 5, 0, false, 7, 7);
			race3AssertUtil.assertPilot(sco808, 4, 0, false, 8, 8);
			race3AssertUtil.assertPilot(sco117, 3, 0, false, 9, 9);
			race3AssertUtil.assertPilot(sco666, 3, 0, false, 10, 10);
			race3AssertUtil.assertPilot(sco060, 3, 0, false, 11, 11);
			race3AssertUtil.assertPilot(sco777, 2, 0, false, 12, 12);
			race3AssertUtil.assertPilot(ir085, 1, 0, false, 13, 13);
			race3AssertUtil.assertPilot(ir053, 1, 0, false, 14, 14);
			race3AssertUtil.assertPilot(sco315, 1, 0, false, 15, 15);
			race3AssertUtil.assertPilot(ir075, 0, 0, false, 22, 16);
			race3AssertUtil.assertPilot(sco018, 0, 0, false, 22, 16);
			race3AssertUtil.assertPilot(sco076, 0, 0, false, 22, 16);
			race3AssertUtil.assertPilot(sco081, 0, 0, false, 22, 16);
			race3AssertUtil.assertPilot(sco153, 0, 0, false, 22, 16);
			race3AssertUtil.assertPilot(sco158, 0, 0, false, 22, 16);
			race3AssertUtil.assertDone(0);

			RaceAssertUtil race4AssertUtil = new RaceAssertUtil(scores, race4);
			race4AssertUtil.assertPilot(sco179, 6, 0, false, 0, 1);
			race4AssertUtil.assertPilot(sco200, 6, 0, false, 2, 2);
			race4AssertUtil.assertPilot(ir181, 6, 1, false, 3, 3);
			race4AssertUtil.assertPilot(sco159, 5, 1, false, 4, 4);
			race4AssertUtil.assertPilot(sco116, 5, 0, false, 5, 5);
			race4AssertUtil.assertPilot(sco666, 5, 0, false, 6, 6);
			race4AssertUtil.assertPilot(ir075, 4, 0, false, 7, 7);
			race4AssertUtil.assertPilot(sco060, 3, 0, false, 8, 8);
			race4AssertUtil.assertPilot(sco528, 3, 0, false, 9, 9);
			race4AssertUtil.assertPilot(sco808, 2, 0, false, 10, 10);
			race4AssertUtil.assertPilot(sco117, 2, 0, false, 11, 11);
			race4AssertUtil.assertPilot(ir001, 1, 0, false, 12, 12);
			race4AssertUtil.assertPilot(sco315, 1, 0, false, 13, 13);
			race4AssertUtil.assertPilot(sco777, 1, 0, false, 14, 14);
			race4AssertUtil.assertPilot(ir053, 0, 0, false, 22, 15);
			race4AssertUtil.assertPilot(ir085, 0, 0, false, 22, 15);
			race4AssertUtil.assertPilot(sco018, 0, 0, false, 22, 15);
			race4AssertUtil.assertPilot(sco076, 0, 0, false, 22, 15);
			race4AssertUtil.assertPilot(sco081, 0, 0, false, 22, 15);
			race4AssertUtil.assertPilot(sco153, 0, 0, false, 22, 15);
			race4AssertUtil.assertPilot(sco158, 0, 0, false, 22, 15);
			race4AssertUtil.assertDone(0);

			RaceAssertUtil race5AssertUtil = new RaceAssertUtil(scores, race5);
			race5AssertUtil.assertPilot(sco200, 7, 0, false, 0, 1);
			race5AssertUtil.assertPilot(ir181, 7, 0, false, 2, 2);
			race5AssertUtil.assertPilot(sco116, 7, 0, false, 3, 3);
			race5AssertUtil.assertPilot(sco528, 6, 0, false, 4, 4);
			race5AssertUtil.assertPilot(sco666, 5, 0, false, 5, 5);
			race5AssertUtil.assertPilot(sco159, 5, 0, false, 6, 6);
			race5AssertUtil.assertPilot(sco808, 5, 0, false, 7, 7);
			race5AssertUtil.assertPilot(sco179, 4, 0, false, 8, 8);
			race5AssertUtil.assertPilot(sco060, 4, 0, false, 9, 9);
			race5AssertUtil.assertPilot(sco117, 4, 0, false, 10, 10);
			race5AssertUtil.assertPilot(sco158, 1, 0, false, 11, 11);
			race5AssertUtil.assertPilot(ir001, 0, 0, false, 22, 12);
			race5AssertUtil.assertPilot(ir053, 0, 0, false, 22, 12);
			race5AssertUtil.assertPilot(ir075, 0, 0, false, 22, 12);
			race5AssertUtil.assertPilot(ir085, 0, 0, false, 22, 12);
			race5AssertUtil.assertPilot(sco018, 0, 0, false, 22, 12);
			race5AssertUtil.assertPilot(sco076, 0, 0, false, 22, 12);
			race5AssertUtil.assertPilot(sco081, 0, 0, false, 22, 12);
			race5AssertUtil.assertPilot(sco153, 0, 0, false, 22, 12);
			race5AssertUtil.assertPilot(sco315, 0, 0, false, 22, 12);
			race5AssertUtil.assertPilot(sco777, 0, 0, false, 22, 12);
			race5AssertUtil.assertDone(0);

			RaceAssertUtil race6AssertUtil = new RaceAssertUtil(scores, race6);
			race6AssertUtil.assertPilot(sco808, 7, 0, false, 0, 1);
			race6AssertUtil.assertPilot(sco200, 7, 0, false, 2, 2);
			race6AssertUtil.assertPilot(sco528, 7, 0, false, 3, 3);
			race6AssertUtil.assertPilot(sco159, 7, 0, false, 4, 4);
			race6AssertUtil.assertPilot(sco179, 7, 0, false, 5, 5);
			race6AssertUtil.assertPilot(sco116, 6, 0, false, 6, 6);
			race6AssertUtil.assertPilot(sco666, 6, 0, false, 7, 7);
			race6AssertUtil.assertPilot(sco060, 3, 0, false, 8, 8);
			race6AssertUtil.assertPilot(ir181, 3, 0, false, 9, 9);
			race6AssertUtil.assertPilot(sco158, 2, 0, false, 10, 10);
			race6AssertUtil.assertPilot(sco117, 1, 0, false, 11, 11);
			race6AssertUtil.assertPilot(ir001, 0, 0, false, 22, 12);
			race6AssertUtil.assertPilot(ir053, 0, 0, false, 22, 12);
			race6AssertUtil.assertPilot(ir075, 0, 0, false, 22, 12);
			race6AssertUtil.assertPilot(ir085, 0, 0, false, 22, 12);
			race6AssertUtil.assertPilot(sco018, 0, 0, false, 22, 12);
			race6AssertUtil.assertPilot(sco076, 0, 0, false, 22, 12);
			race6AssertUtil.assertPilot(sco081, 0, 0, false, 22, 12);
			race6AssertUtil.assertPilot(sco153, 0, 0, false, 22, 12);
			race6AssertUtil.assertPilot(sco315, 0, 0, false, 22, 12);
			race6AssertUtil.assertPilot(sco777, 0, 0, false, 22, 12);
			race6AssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 7, 1, 3);
			overallAssertUtil.assertPilot(sco179, 0, 18, 2, 10);
			overallAssertUtil.assertPilot(sco808, 1, 19, 3, 10);
			overallAssertUtil.assertPilot(sco116, 0, 21, 4, 6);
			overallAssertUtil.assertPilot(sco159, 1, 25, 5, 6);
			overallAssertUtil.assertPilot(sco528, 0, 28, 6, 13);
			overallAssertUtil.assertPilot(ir181, 1, 30, 7, 14);
			overallAssertUtil.assertPilot(sco060, 0, 47, 8, 17);
			overallAssertUtil.assertPilot(ir001, 0, 49, 9, 22);
			overallAssertUtil.assertPilot(sco666, 0, 50, 10, 22);
			overallAssertUtil.assertPilot(sco117, 0, 52, 11, 12);
			overallAssertUtil.assertPilot(ir075, 0, 65, 12, 22);
			overallAssertUtil.assertPilot(sco158, 0, 80, 13, 22);
			overallAssertUtil.assertPilot(sco081, 0, 81, 14, 22);
			overallAssertUtil.assertPilot(sco777, 0, 82, 15, 22);
			overallAssertUtil.assertPilot(sco076, 0, 83, 16, 22);
			overallAssertUtil.assertPilot(ir085, 0, 86, 17, 22);
			overallAssertUtil.assertPilot(sco315, 0, 90, 18, 22);
			overallAssertUtil.assertPilot(ir053, 0, 96, 19, 22);
			overallAssertUtil.assertPilot(sco018, 0, 102, 20, 22);
			overallAssertUtil.assertPilot(sco153, 0, 110, 21, 22);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace1() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Race race1 = raceDAO.find(event1, RACE1_NAME);

			Scores scores = scorer.scoreRace(race1, Predicates.in(getEventResultsPilots(series, event1)));
			Assert.assertEquals(EVENT1_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race1));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race1);
			raceAssertUtil.assertPilot(sco200, 5, 0, false, 0, 1);
			raceAssertUtil.assertPilot(ir001, 5, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco808, 5, 1, false, 3, 3);
			raceAssertUtil.assertPilot(sco159, 5, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco179, 5, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco116, 4, 0, false, 6, 6);
			raceAssertUtil.assertPilot(ir085, 4, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco081, 4, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco076, 4, 0, false, 9, 9);
			raceAssertUtil.assertPilot(ir075, 3, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco117, 3, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco777, 3, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco528, 2, 0, false, 13, 13);
			raceAssertUtil.assertPilot(ir181, 2, 0, false, 14, 14);
			raceAssertUtil.assertPilot(sco158, 2, 0, false, 15, 15);
			raceAssertUtil.assertPilot(ir053, 1, 0, false, 16, 16);
			raceAssertUtil.assertPilot(sco060, 1, 0, false, 17, 17);
			raceAssertUtil.assertPilot(sco315, 1, 0, false, 18, 18);
			raceAssertUtil.assertPilot(sco018, 0, 0, false, 22, 19);
			raceAssertUtil.assertPilot(sco153, 0, 0, false, 22, 19);
			raceAssertUtil.assertPilot(sco666, 0, 0, false, 22, 19);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(ir001, 0, 2, 2);
			overallAssertUtil.assertPilot(sco808, 1, 4, 3);
			overallAssertUtil.assertPilot(sco159, 0, 4, 4);
			overallAssertUtil.assertPilot(sco179, 0, 5, 5);
			overallAssertUtil.assertPilot(sco116, 0, 6, 6);
			overallAssertUtil.assertPilot(ir085, 0, 7, 7);
			overallAssertUtil.assertPilot(sco081, 0, 8, 8);
			overallAssertUtil.assertPilot(sco076, 0, 9, 9);
			overallAssertUtil.assertPilot(ir075, 0, 10, 10);
			overallAssertUtil.assertPilot(sco117, 0, 11, 11);
			overallAssertUtil.assertPilot(sco777, 0, 12, 12);
			overallAssertUtil.assertPilot(sco528, 0, 13, 13);
			overallAssertUtil.assertPilot(ir181, 0, 14, 14);
			overallAssertUtil.assertPilot(sco158, 0, 15, 15);
			overallAssertUtil.assertPilot(ir053, 0, 16, 16);
			overallAssertUtil.assertPilot(sco060, 0, 17, 17);
			overallAssertUtil.assertPilot(sco315, 0, 18, 18);
			overallAssertUtil.assertPilot(sco018, 0, 22, 19);
			overallAssertUtil.assertPilot(sco153, 0, 22, 19);
			overallAssertUtil.assertPilot(sco666, 0, 22, 19);
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
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Race race2 = raceDAO.find(event1, RACE2_NAME);

			Scores scores = scorer.scoreRace(race2, Predicates.in(getEventResultsPilots(series, event1)));
			Assert.assertEquals(EVENT1_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race2));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race2);
			raceAssertUtil.assertPilot(sco808, 7, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco116, 7, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco200, 7, 0, false, 3, 3);
			raceAssertUtil.assertPilot(ir075, 6, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco528, 6, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco159, 6, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco081, 6, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco076, 5, 0, false, 8, 8);
			raceAssertUtil.assertPilot(ir001, 4, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco179, 4, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco060, 4, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco117, 4, 0, false, 12, 12);
			raceAssertUtil.assertPilot(ir181, 2, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco018, 1, 0, false, 14, 14);
			raceAssertUtil.assertPilot(ir053, 0, 0, false, 22, 15);
			raceAssertUtil.assertPilot(ir085, 0, 0, false, 22, 15);
			raceAssertUtil.assertPilot(sco153, 0, 0, false, 22, 15);
			raceAssertUtil.assertPilot(sco158, 0, 0, false, 22, 15);
			raceAssertUtil.assertPilot(sco315, 0, 0, false, 22, 15);
			raceAssertUtil.assertPilot(sco666, 0, 0, false, 22, 15);
			raceAssertUtil.assertPilot(sco777, 0, 0, false, 22, 15);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco808, 0, 0, 1);
			overallAssertUtil.assertPilot(sco116, 0, 2, 2);
			overallAssertUtil.assertPilot(sco200, 0, 3, 3);
			overallAssertUtil.assertPilot(ir075, 0, 4, 4);
			overallAssertUtil.assertPilot(sco528, 0, 5, 5);
			overallAssertUtil.assertPilot(sco159, 0, 6, 6);
			overallAssertUtil.assertPilot(sco081, 0, 7, 7);
			overallAssertUtil.assertPilot(sco076, 0, 8, 8);
			overallAssertUtil.assertPilot(ir001, 0, 9, 9);
			overallAssertUtil.assertPilot(sco179, 0, 10, 10);
			overallAssertUtil.assertPilot(sco060, 0, 11, 11);
			overallAssertUtil.assertPilot(sco117, 0, 12, 12);
			overallAssertUtil.assertPilot(ir181, 0, 13, 13);
			overallAssertUtil.assertPilot(sco018, 0, 14, 14);
			overallAssertUtil.assertPilot(ir053, 0, 22, 15);
			overallAssertUtil.assertPilot(ir085, 0, 22, 15);
			overallAssertUtil.assertPilot(sco153, 0, 22, 15);
			overallAssertUtil.assertPilot(sco158, 0, 22, 15);
			overallAssertUtil.assertPilot(sco315, 0, 22, 15);
			overallAssertUtil.assertPilot(sco666, 0, 22, 15);
			overallAssertUtil.assertPilot(sco777, 0, 22, 15);
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
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Race race3 = raceDAO.find(event1, RACE3_NAME);

			Scores scores = scorer.scoreRace(race3, Predicates.in(getEventResultsPilots(series, event1)));
			Assert.assertEquals(EVENT1_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race3));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race3);
			raceAssertUtil.assertPilot(sco179, 5, 0, false, 0, 1);
			raceAssertUtil.assertPilot(ir181, 5, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco200, 5, 0, false, 3, 3);
			raceAssertUtil.assertPilot(ir001, 5, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco116, 5, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco159, 5, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco528, 5, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco808, 4, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco117, 3, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco666, 3, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco060, 3, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco777, 2, 0, false, 12, 12);
			raceAssertUtil.assertPilot(ir085, 1, 0, false, 13, 13);
			raceAssertUtil.assertPilot(ir053, 1, 0, false, 14, 14);
			raceAssertUtil.assertPilot(sco315, 1, 0, false, 15, 15);
			raceAssertUtil.assertPilot(ir075, 0, 0, false, 22, 16);
			raceAssertUtil.assertPilot(sco018, 0, 0, false, 22, 16);
			raceAssertUtil.assertPilot(sco076, 0, 0, false, 22, 16);
			raceAssertUtil.assertPilot(sco081, 0, 0, false, 22, 16);
			raceAssertUtil.assertPilot(sco153, 0, 0, false, 22, 16);
			raceAssertUtil.assertPilot(sco158, 0, 0, false, 22, 16);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco179, 0, 0, 1);
			overallAssertUtil.assertPilot(ir181, 0, 2, 2);
			overallAssertUtil.assertPilot(sco200, 0, 3, 3);
			overallAssertUtil.assertPilot(ir001, 0, 4, 4);
			overallAssertUtil.assertPilot(sco116, 0, 5, 5);
			overallAssertUtil.assertPilot(sco159, 0, 6, 6);
			overallAssertUtil.assertPilot(sco528, 0, 7, 7);
			overallAssertUtil.assertPilot(sco808, 0, 8, 8);
			overallAssertUtil.assertPilot(sco117, 0, 9, 9);
			overallAssertUtil.assertPilot(sco666, 0, 10, 10);
			overallAssertUtil.assertPilot(sco060, 0, 11, 11);
			overallAssertUtil.assertPilot(sco777, 0, 12, 12);
			overallAssertUtil.assertPilot(ir085, 0, 13, 13);
			overallAssertUtil.assertPilot(ir053, 0, 14, 14);
			overallAssertUtil.assertPilot(sco315, 0, 15, 15);
			overallAssertUtil.assertPilot(ir075, 0, 22, 16);
			overallAssertUtil.assertPilot(sco018, 0, 22, 16);
			overallAssertUtil.assertPilot(sco076, 0, 22, 16);
			overallAssertUtil.assertPilot(sco081, 0, 22, 16);
			overallAssertUtil.assertPilot(sco153, 0, 22, 16);
			overallAssertUtil.assertPilot(sco158, 0, 22, 16);
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
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Race race4 = raceDAO.find(event1, RACE4_NAME);

			Scores scores = scorer.scoreRace(race4, Predicates.in(getEventResultsPilots(series, event1)));
			Assert.assertEquals(EVENT1_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race4));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race4);
			raceAssertUtil.assertPilot(sco179, 6, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco200, 6, 0, false, 2, 2);
			raceAssertUtil.assertPilot(ir181, 6, 1, false, 3, 3);
			raceAssertUtil.assertPilot(sco159, 5, 1, false, 4, 4);
			raceAssertUtil.assertPilot(sco116, 5, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco666, 5, 0, false, 6, 6);
			raceAssertUtil.assertPilot(ir075, 4, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco060, 3, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco528, 3, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco808, 2, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco117, 2, 0, false, 11, 11);
			raceAssertUtil.assertPilot(ir001, 1, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco315, 1, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco777, 1, 0, false, 14, 14);
			raceAssertUtil.assertPilot(ir053, 0, 0, false, 22, 15);
			raceAssertUtil.assertPilot(ir085, 0, 0, false, 22, 15);
			raceAssertUtil.assertPilot(sco018, 0, 0, false, 22, 15);
			raceAssertUtil.assertPilot(sco076, 0, 0, false, 22, 15);
			raceAssertUtil.assertPilot(sco081, 0, 0, false, 22, 15);
			raceAssertUtil.assertPilot(sco153, 0, 0, false, 22, 15);
			raceAssertUtil.assertPilot(sco158, 0, 0, false, 22, 15);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco179, 0, 0, 1);
			overallAssertUtil.assertPilot(sco200, 0, 2, 2);
			overallAssertUtil.assertPilot(ir181, 1, 4, 3);
			overallAssertUtil.assertPilot(sco159, 1, 5, 4);
			overallAssertUtil.assertPilot(sco116, 0, 5, 5);
			overallAssertUtil.assertPilot(sco666, 0, 6, 6);
			overallAssertUtil.assertPilot(ir075, 0, 7, 7);
			overallAssertUtil.assertPilot(sco060, 0, 8, 8);
			overallAssertUtil.assertPilot(sco528, 0, 9, 9);
			overallAssertUtil.assertPilot(sco808, 0, 10, 10);
			overallAssertUtil.assertPilot(sco117, 0, 11, 11);
			overallAssertUtil.assertPilot(ir001, 0, 12, 12);
			overallAssertUtil.assertPilot(sco315, 0, 13, 13);
			overallAssertUtil.assertPilot(sco777, 0, 14, 14);
			overallAssertUtil.assertPilot(ir053, 0, 22, 15);
			overallAssertUtil.assertPilot(ir085, 0, 22, 15);
			overallAssertUtil.assertPilot(sco018, 0, 22, 15);
			overallAssertUtil.assertPilot(sco076, 0, 22, 15);
			overallAssertUtil.assertPilot(sco081, 0, 22, 15);
			overallAssertUtil.assertPilot(sco153, 0, 22, 15);
			overallAssertUtil.assertPilot(sco158, 0, 22, 15);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace5() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Race race5 = raceDAO.find(event1, RACE5_NAME);

			Scores scores = scorer.scoreRace(race5, Predicates.in(getEventResultsPilots(series, event1)));
			Assert.assertEquals(EVENT1_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race5));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race5);
			raceAssertUtil.assertPilot(sco200, 7, 0, false, 0, 1);
			raceAssertUtil.assertPilot(ir181, 7, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco116, 7, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco528, 6, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco666, 5, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco159, 5, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco808, 5, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco179, 4, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco060, 4, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco117, 4, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco158, 1, 0, false, 11, 11);
			raceAssertUtil.assertPilot(ir001, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(ir053, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(ir075, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(ir085, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(sco018, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(sco076, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(sco081, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(sco153, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(sco315, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(sco777, 0, 0, false, 22, 12);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(ir181, 0, 2, 2);
			overallAssertUtil.assertPilot(sco116, 0, 3, 3);
			overallAssertUtil.assertPilot(sco528, 0, 4, 4);
			overallAssertUtil.assertPilot(sco666, 0, 5, 5);
			overallAssertUtil.assertPilot(sco159, 0, 6, 6);
			overallAssertUtil.assertPilot(sco808, 0, 7, 7);
			overallAssertUtil.assertPilot(sco179, 0, 8, 8);
			overallAssertUtil.assertPilot(sco060, 0, 9, 9);
			overallAssertUtil.assertPilot(sco117, 0, 10, 10);
			overallAssertUtil.assertPilot(sco158, 0, 11, 11);
			overallAssertUtil.assertPilot(ir001, 0, 22, 12);
			overallAssertUtil.assertPilot(ir053, 0, 22, 12);
			overallAssertUtil.assertPilot(ir075, 0, 22, 12);
			overallAssertUtil.assertPilot(ir085, 0, 22, 12);
			overallAssertUtil.assertPilot(sco018, 0, 22, 12);
			overallAssertUtil.assertPilot(sco076, 0, 22, 12);
			overallAssertUtil.assertPilot(sco081, 0, 22, 12);
			overallAssertUtil.assertPilot(sco153, 0, 22, 12);
			overallAssertUtil.assertPilot(sco315, 0, 22, 12);
			overallAssertUtil.assertPilot(sco777, 0, 22, 12);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace6() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Race race6 = raceDAO.find(event1, RACE6_NAME);

			Scores scores = scorer.scoreRace(race6, Predicates.in(getEventResultsPilots(series, event1)));
			Assert.assertEquals(EVENT1_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race6));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race6);
			raceAssertUtil.assertPilot(sco808, 7, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco200, 7, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco528, 7, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco159, 7, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco179, 7, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco116, 6, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco666, 6, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco060, 3, 0, false, 8, 8);
			raceAssertUtil.assertPilot(ir181, 3, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco158, 2, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco117, 1, 0, false, 11, 11);
			raceAssertUtil.assertPilot(ir001, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(ir053, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(ir075, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(ir085, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(sco018, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(sco076, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(sco081, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(sco153, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(sco315, 0, 0, false, 22, 12);
			raceAssertUtil.assertPilot(sco777, 0, 0, false, 22, 12);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco808, 0, 0, 1);
			overallAssertUtil.assertPilot(sco200, 0, 2, 2);
			overallAssertUtil.assertPilot(sco528, 0, 3, 3);
			overallAssertUtil.assertPilot(sco159, 0, 4, 4);
			overallAssertUtil.assertPilot(sco179, 0, 5, 5);
			overallAssertUtil.assertPilot(sco116, 0, 6, 6);
			overallAssertUtil.assertPilot(sco666, 0, 7, 7);
			overallAssertUtil.assertPilot(sco060, 0, 8, 8);
			overallAssertUtil.assertPilot(ir181, 0, 9, 9);
			overallAssertUtil.assertPilot(sco158, 0, 10, 10);
			overallAssertUtil.assertPilot(sco117, 0, 11, 11);
			overallAssertUtil.assertPilot(ir001, 0, 22, 12);
			overallAssertUtil.assertPilot(ir053, 0, 22, 12);
			overallAssertUtil.assertPilot(ir075, 0, 22, 12);
			overallAssertUtil.assertPilot(ir085, 0, 22, 12);
			overallAssertUtil.assertPilot(sco018, 0, 22, 12);
			overallAssertUtil.assertPilot(sco076, 0, 22, 12);
			overallAssertUtil.assertPilot(sco081, 0, 22, 12);
			overallAssertUtil.assertPilot(sco153, 0, 22, 12);
			overallAssertUtil.assertPilot(sco315, 0, 22, 12);
			overallAssertUtil.assertPilot(sco777, 0, 22, 12);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}