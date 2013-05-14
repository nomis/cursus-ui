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
 * Scores at the end of event 2 (19/01/2013 to 20/01/2013)
 */
public class Series2012Event2Scores extends Series2012Event1Scores {
	@Override
	@Before
	public void createData() throws Exception {
		super.createData();
		createNonEvent1Data();
		createNonEvent2Data();
		createEvent1Races();
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

			Scores scores = scorer.scoreRaces(races, getSeriesResultsPilots(series, event2), getSeriesResultsEvents(series, event2),
					Predicates.in(getSeriesResultsPilots(series, event2)));
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
		Race race4 = raceDAO.find(event1, RACE4_NAME);
		Race race5 = raceDAO.find(event1, RACE5_NAME);
		Race race6 = raceDAO.find(event1, RACE6_NAME);
		Event event2 = eventDAO.find(series, EVENT2_NAME);
		Race race7 = raceDAO.find(event2, RACE7_NAME);
		Race race8 = raceDAO.find(event2, RACE8_NAME);
		Race race9 = raceDAO.find(event2, RACE9_NAME);
		Race race10 = raceDAO.find(event2, RACE10_NAME);
		Race race11 = raceDAO.find(event2, RACE11_NAME);
		Race race12 = raceDAO.find(event2, RACE12_NAME);

		Assert.assertEquals(SERIES_FLEET_AT_EVENT2, scores.getPilots().size());
		Assert.assertEquals(RACE1_PILOTS, scores.getFleetSize(race1));
		Assert.assertEquals(RACE2_PILOTS, scores.getFleetSize(race2));
		Assert.assertEquals(RACE3_PILOTS, scores.getFleetSize(race3));
		Assert.assertEquals(RACE4_PILOTS, scores.getFleetSize(race4));
		Assert.assertEquals(RACE5_PILOTS, scores.getFleetSize(race5));
		Assert.assertEquals(RACE6_PILOTS, scores.getFleetSize(race6));
		Assert.assertEquals(RACE7_PILOTS, scores.getFleetSize(race7));
		Assert.assertEquals(RACE8_PILOTS, scores.getFleetSize(race8));
		Assert.assertEquals(RACE9_PILOTS, scores.getFleetSize(race9));
		Assert.assertEquals(RACE10_PILOTS, scores.getFleetSize(race10));
		Assert.assertEquals(RACE11_PILOTS, scores.getFleetSize(race11));
		Assert.assertEquals(RACE12_PILOTS, scores.getFleetSize(race12));

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
		race1AssertUtil.assertPilot(sco087, 0, 0, false, 23, 17);
		race1AssertUtil.assertPilot(sco100, 0, 0, false, 23, 17);
		race1AssertUtil.assertPilot(sco156, 0, 0, false, 23, 17);
		race1AssertUtil.assertPilot(sco320, 0, 0, false, 23, 17);
		race1AssertUtil.assertPilot(sco467, 0, 0, false, 23, 17);
		race1AssertUtil.assertPilot(sco561, 0, 0, false, 23, 17);
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
		race2AssertUtil.assertPilot(sco087, 0, 0, false, 23, 17);
		race2AssertUtil.assertPilot(sco100, 0, 0, false, 23, 17);
		race2AssertUtil.assertPilot(sco156, 0, 0, false, 23, 17);
		race2AssertUtil.assertPilot(sco320, 0, 0, false, 23, 17);
		race2AssertUtil.assertPilot(sco467, 0, 0, false, 23, 17);
		race2AssertUtil.assertPilot(sco561, 0, 0, false, 23, 17);
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
		race3AssertUtil.assertPilot(sco087, 0, 0, false, 23, 17);
		race3AssertUtil.assertPilot(sco100, 0, 0, false, 23, 17);
		race3AssertUtil.assertPilot(sco156, 0, 0, false, 23, 17);
		race3AssertUtil.assertPilot(sco320, 0, 0, false, 23, 17);
		race3AssertUtil.assertPilot(sco467, 0, 0, false, 23, 17);
		race3AssertUtil.assertPilot(sco561, 0, 0, false, 23, 17);
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
		race4AssertUtil.assertPilot(sco087, 0, 0, false, 23, 17);
		race4AssertUtil.assertPilot(sco100, 0, 0, false, 23, 17);
		race4AssertUtil.assertPilot(sco156, 0, 0, false, 23, 17);
		race4AssertUtil.assertPilot(sco320, 0, 0, false, 23, 17);
		race4AssertUtil.assertPilot(sco467, 0, 0, false, 23, 17);
		race4AssertUtil.assertPilot(sco561, 0, 0, false, 23, 17);
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
		race5AssertUtil.assertPilot(sco087, 0, 0, false, 23, 17);
		race5AssertUtil.assertPilot(sco100, 0, 0, false, 23, 17);
		race5AssertUtil.assertPilot(sco156, 0, 0, false, 23, 17);
		race5AssertUtil.assertPilot(sco320, 0, 0, false, 23, 17);
		race5AssertUtil.assertPilot(sco467, 0, 0, false, 23, 17);
		race5AssertUtil.assertPilot(sco561, 0, 0, false, 23, 17);
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
		race6AssertUtil.assertPilot(sco087, 0, 0, false, 23, 17);
		race6AssertUtil.assertPilot(sco100, 0, 0, false, 23, 17);
		race6AssertUtil.assertPilot(sco156, 0, 0, false, 23, 17);
		race6AssertUtil.assertPilot(sco320, 0, 0, false, 23, 17);
		race6AssertUtil.assertPilot(sco467, 0, 0, false, 23, 17);
		race6AssertUtil.assertPilot(sco561, 0, 0, false, 23, 17);
		race6AssertUtil.assertDone(0);

		RaceAssertUtil race7AssertUtil = new RaceAssertUtil(scores, race7);
		race7AssertUtil.assertPilot(sco200, 6, 0, false, 0, 1);
		race7AssertUtil.assertPilot(sco179, 6, 0, false, 2, 2);
		race7AssertUtil.assertPilot(sco808, 6, 0, false, 3, 3);
		race7AssertUtil.assertPilot(sco116, 6, 0, false, 4, 4);
		race7AssertUtil.assertPilot(sco666, 5, 0, false, 5, 5);
		race7AssertUtil.assertPilot(sco159, 5, 0, false, 6, 6);
		race7AssertUtil.assertPilot(sco100, 4, 0, false, 7, 7);
		race7AssertUtil.assertPilot(sco018, 4, 0, false, 8, 8);
		race7AssertUtil.assertPilot(sco081, 3, 0, false, 9, 9);
		race7AssertUtil.assertPilot(sco087, 3, 0, false, 10, 10);
		race7AssertUtil.assertPilot(sco315, 3, 0, false, 11, 11);
		race7AssertUtil.assertPilot(sco060, 2, 0, false, 12, 12);
		race7AssertUtil.assertPilot(sco076, 0, 0, false, 17, 13);
		race7AssertUtil.assertPilot(sco153, 0, 0, false, 17, 13);
		race7AssertUtil.assertPilot(sco156, 0, 0, false, 17, 13);
		race7AssertUtil.assertPilot(sco528, 0, 0, false, 17, 13);
		race7AssertUtil.assertPilot(sco117, 0, 0, false, 23, 17);
		race7AssertUtil.assertPilot(sco158, 0, 0, false, 23, 17);
		race7AssertUtil.assertPilot(sco320, 0, 0, false, 23, 17);
		race7AssertUtil.assertPilot(sco467, 0, 0, false, 23, 17);
		race7AssertUtil.assertPilot(sco561, 0, 0, false, 23, 17);
		race7AssertUtil.assertPilot(sco777, 0, 0, false, 23, 17);
		race7AssertUtil.assertDone(0);

		RaceAssertUtil race8AssertUtil = new RaceAssertUtil(scores, race8);
		race8AssertUtil.assertPilot(sco200, 6, 0, false, 0, 1);
		race8AssertUtil.assertPilot(sco179, 6, 0, false, 2, 2);
		race8AssertUtil.assertPilot(sco808, 6, 1, false, 3, 3);
		race8AssertUtil.assertPilot(sco116, 6, 0, false, 4, 4);
		race8AssertUtil.assertPilot(sco666, 5, 0, false, 5, 5);
		race8AssertUtil.assertPilot(sco159, 5, 0, false, 6, 6);
		race8AssertUtil.assertPilot(sco100, 5, 0, false, 7, 7);
		race8AssertUtil.assertPilot(sco081, 5, 0, false, 8, 8);
		race8AssertUtil.assertPilot(sco018, 5, 0, false, 9, 9);
		race8AssertUtil.assertPilot(sco528, 4, 0, false, 10, 10);
		race8AssertUtil.assertPilot(sco315, 4, 0, false, 11, 11);
		race8AssertUtil.assertPilot(sco087, 3, 0, false, 12, 12);
		race8AssertUtil.assertPilot(sco060, 2, 0, false, 13, 13);
		race8AssertUtil.assertPilot(sco076, 0, 0, false, 17, 14);
		race8AssertUtil.assertPilot(sco153, 0, 0, false, 17, 14);
		race8AssertUtil.assertPilot(sco156, 0, 0, false, 17, 14);
		race8AssertUtil.assertPilot(sco117, 0, 0, false, 23, 17);
		race8AssertUtil.assertPilot(sco158, 0, 0, false, 23, 17);
		race8AssertUtil.assertPilot(sco320, 0, 0, false, 23, 17);
		race8AssertUtil.assertPilot(sco467, 0, 0, false, 23, 17);
		race8AssertUtil.assertPilot(sco561, 0, 0, false, 23, 17);
		race8AssertUtil.assertPilot(sco777, 0, 0, false, 23, 17);
		race8AssertUtil.assertDone(0);

		RaceAssertUtil race9AssertUtil = new RaceAssertUtil(scores, race9);
		race9AssertUtil.assertPilot(sco200, 6, 0, false, 0, 1);
		race9AssertUtil.assertPilot(sco179, 6, 0, false, 2, 2);
		race9AssertUtil.assertPilot(sco116, 6, 0, false, 3, 3);
		race9AssertUtil.assertPilot(sco808, 5, 0, false, 4, 4);
		race9AssertUtil.assertPilot(sco081, 5, 0, false, 5, 5);
		race9AssertUtil.assertPilot(sco528, 5, 0, false, 6, 6);
		race9AssertUtil.assertPilot(sco100, 5, 0, false, 7, 7);
		race9AssertUtil.assertPilot(sco159, 4, 0, false, 8, 8);
		race9AssertUtil.assertPilot(sco666, 4, 0, false, 9, 9);
		race9AssertUtil.assertPilot(sco018, 4, 0, false, 10, 10);
		race9AssertUtil.assertPilot(sco087, 4, 0, false, 11, 11);
		race9AssertUtil.assertPilot(sco315, 4, 0, false, 12, 12);
		race9AssertUtil.assertPilot(sco060, 1, 0, false, 13, 13);
		race9AssertUtil.assertPilot(sco076, 0, 0, false, 17, 14);
		race9AssertUtil.assertPilot(sco153, 0, 0, false, 17, 14);
		race9AssertUtil.assertPilot(sco156, 0, 0, false, 17, 14);
		race9AssertUtil.assertPilot(sco117, 0, 0, false, 23, 17);
		race9AssertUtil.assertPilot(sco158, 0, 0, false, 23, 17);
		race9AssertUtil.assertPilot(sco320, 0, 0, false, 23, 17);
		race9AssertUtil.assertPilot(sco467, 0, 0, false, 23, 17);
		race9AssertUtil.assertPilot(sco561, 0, 0, false, 23, 17);
		race9AssertUtil.assertPilot(sco777, 0, 0, false, 23, 17);
		race9AssertUtil.assertDone(0);

		RaceAssertUtil race10AssertUtil = new RaceAssertUtil(scores, race10);
		race10AssertUtil.assertPilot(sco179, 9, 0, false, 0, 1);
		race10AssertUtil.assertPilot(sco200, 9, 1, false, 2, 2);
		race10AssertUtil.assertPilot(sco116, 9, 0, false, 3, 3);
		race10AssertUtil.assertPilot(sco808, 9, 0, false, 4, 4);
		race10AssertUtil.assertPilot(sco528, 8, 0, false, 5, 5);
		race10AssertUtil.assertPilot(sco159, 7, 0, false, 6, 6);
		race10AssertUtil.assertPilot(sco666, 7, 0, false, 7, 7);
		race10AssertUtil.assertPilot(sco087, 6, 0, false, 8, 8);
		race10AssertUtil.assertPilot(sco018, 6, 0, false, 9, 9);
		race10AssertUtil.assertPilot(sco315, 5, 0, false, 10, 10);
		race10AssertUtil.assertPilot(sco100, 4, 0, false, 11, 11);
		race10AssertUtil.assertPilot(sco060, 1, 0, false, 12, 12);
		race10AssertUtil.assertPilot(sco076, 0, 0, false, 17, 13);
		race10AssertUtil.assertPilot(sco081, 0, 0, false, 17, 13);
		race10AssertUtil.assertPilot(sco153, 0, 0, false, 17, 13);
		race10AssertUtil.assertPilot(sco156, 0, 0, false, 17, 13);
		race10AssertUtil.assertPilot(sco117, 0, 0, false, 23, 17);
		race10AssertUtil.assertPilot(sco158, 0, 0, false, 23, 17);
		race10AssertUtil.assertPilot(sco320, 0, 0, false, 23, 17);
		race10AssertUtil.assertPilot(sco467, 0, 0, false, 23, 17);
		race10AssertUtil.assertPilot(sco561, 0, 0, false, 23, 17);
		race10AssertUtil.assertPilot(sco777, 0, 0, false, 23, 17);
		race10AssertUtil.assertDone(0);

		RaceAssertUtil race11AssertUtil = new RaceAssertUtil(scores, race11);
		race11AssertUtil.assertPilot(sco200, 9, 0, false, 0, 1);
		race11AssertUtil.assertPilot(sco179, 9, 0, false, 2, 2);
		race11AssertUtil.assertPilot(sco808, 9, 0, false, 3, 3);
		race11AssertUtil.assertPilot(sco116, 9, 0, false, 4, 4);
		race11AssertUtil.assertPilot(sco528, 8, 0, false, 5, 5);
		race11AssertUtil.assertPilot(sco666, 8, 0, false, 6, 6);
		race11AssertUtil.assertPilot(sco159, 7, 0, false, 7, 7);
		race11AssertUtil.assertPilot(sco081, 7, 0, false, 8, 8);
		race11AssertUtil.assertPilot(sco087, 6, 0, false, 9, 9);
		race11AssertUtil.assertPilot(sco100, 6, 0, false, 10, 10);
		race11AssertUtil.assertPilot(sco060, 5, 0, false, 11, 11);
		race11AssertUtil.assertPilot(sco315, 5, 0, false, 12, 12);
		race11AssertUtil.assertPilot(sco018, 0, 0, false, 17, 13);
		race11AssertUtil.assertPilot(sco076, 0, 0, false, 17, 13);
		race11AssertUtil.assertPilot(sco153, 0, 0, false, 17, 13);
		race11AssertUtil.assertPilot(sco156, 0, 0, false, 17, 13);
		race11AssertUtil.assertPilot(sco117, 0, 0, false, 23, 17);
		race11AssertUtil.assertPilot(sco158, 0, 0, false, 23, 17);
		race11AssertUtil.assertPilot(sco320, 0, 0, false, 23, 17);
		race11AssertUtil.assertPilot(sco467, 0, 0, false, 23, 17);
		race11AssertUtil.assertPilot(sco561, 0, 0, false, 23, 17);
		race11AssertUtil.assertPilot(sco777, 0, 0, false, 23, 17);
		race11AssertUtil.assertDone(0);

		RaceAssertUtil race12AssertUtil = new RaceAssertUtil(scores, race12);
		race12AssertUtil.assertPilot(sco200, 9, 0, false, 0, 1);
		race12AssertUtil.assertPilot(sco808, 9, 0, false, 2, 2);
		race12AssertUtil.assertPilot(sco179, 9, 0, false, 3, 3);
		race12AssertUtil.assertPilot(sco528, 9, 0, false, 4, 4);
		race12AssertUtil.assertPilot(sco116, 9, 0, false, 5, 5);
		race12AssertUtil.assertPilot(sco666, 8, 1, false, 6, 6);
		race12AssertUtil.assertPilot(sco159, 8, 0, false, 7, 7);
		race12AssertUtil.assertPilot(sco060, 6, 0, false, 8, 8);
		race12AssertUtil.assertPilot(sco087, 4, 0, false, 9, 9);
		race12AssertUtil.assertPilot(sco018, 4, 0, false, 10, 10);
		race12AssertUtil.assertPilot(sco076, 0, 0, false, 17, 11);
		race12AssertUtil.assertPilot(sco081, 0, 0, false, 17, 11);
		race12AssertUtil.assertPilot(sco100, 0, 0, false, 17, 11);
		race12AssertUtil.assertPilot(sco153, 0, 0, false, 17, 11);
		race12AssertUtil.assertPilot(sco156, 0, 0, false, 17, 11);
		race12AssertUtil.assertPilot(sco315, 0, 0, false, 17, 11);
		race12AssertUtil.assertPilot(sco117, 0, 0, false, 23, 17);
		race12AssertUtil.assertPilot(sco158, 0, 0, false, 23, 17);
		race12AssertUtil.assertPilot(sco320, 0, 0, false, 23, 17);
		race12AssertUtil.assertPilot(sco467, 0, 0, false, 23, 17);
		race12AssertUtil.assertPilot(sco561, 0, 0, false, 23, 17);
		race12AssertUtil.assertPilot(sco777, 0, 0, false, 23, 17);
		race12AssertUtil.assertDone(0);

		OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
		overallAssertUtil.assertPilot(sco200, 1, 5, 1, 3, 2, 2);
		overallAssertUtil.assertPilot(sco179, 0, 15, 2, 8, 7, 5);
		overallAssertUtil.assertPilot(sco808, 2, 23, 3, 8, 6, 6);
		overallAssertUtil.assertPilot(sco116, 0, 29, 4, 6, 5, 5);
		overallAssertUtil.assertPilot(sco528, 0, 42, 5, 17, 10, 10);
		overallAssertUtil.assertPilot(sco159, 1, 43, 6, 8, 7, 7);
		overallAssertUtil.assertPilot(sco666, 1, 54, 7, 17, 17, 9);
		overallAssertUtil.assertPilot(sco060, 0, 83, 8, 13, 13, 12);
		overallAssertUtil.assertPilot(sco081, 0, 93, 9, 17, 17, 17);
		overallAssertUtil.assertPilot(sco315, 0, 107, 10, 17, 17, 17);
		overallAssertUtil.assertPilot(sco018, 0, 108, 11, 17, 17, 17);
		overallAssertUtil.assertPilot(sco117, 0, 122, 12, 23, 23, 23);
		overallAssertUtil.assertPilot(sco100, 0, 128, 13, 23, 23, 23);
		overallAssertUtil.assertPilot(sco087, 0, 128, 14, 23, 23, 23);
		overallAssertUtil.assertPilot(sco076, 0, 133, 15, 17, 17, 17);
		overallAssertUtil.assertPilot(sco158, 0, 150, 16, 23, 23, 23);
		overallAssertUtil.assertPilot(sco777, 0, 150, 16, 23, 23, 23);
		overallAssertUtil.assertPilot(sco153, 0, 153, 18, 17, 17, 17);
		overallAssertUtil.assertPilot(sco156, 0, 171, 19, 23, 23, 23);
		overallAssertUtil.assertPilot(sco320, 0, 207, 20, 23, 23, 23);
		overallAssertUtil.assertPilot(sco467, 0, 207, 20, 23, 23, 23);
		overallAssertUtil.assertPilot(sco561, 0, 207, 20, 23, 23, 23);
		overallAssertUtil.assertOrder();
	}

	@Test
	public final void checkEvent2() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race7 = raceDAO.find(event2, RACE7_NAME);
			Race race8 = raceDAO.find(event2, RACE8_NAME);
			Race race9 = raceDAO.find(event2, RACE9_NAME);
			Race race10 = raceDAO.find(event2, RACE10_NAME);
			Race race11 = raceDAO.find(event2, RACE11_NAME);
			Race race12 = raceDAO.find(event2, RACE12_NAME);

			Scores scores = scorer.scoreEvent(event2, Predicates.in(getEventResultsPilots(series, event2)));
			Assert.assertEquals(EVENT2_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race7));
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race8));
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race9));
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race10));
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race11));
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race12));

			RaceAssertUtil race7AssertUtil = new RaceAssertUtil(scores, race7);
			race7AssertUtil.assertPilot(sco200, 6, 0, false, 0, 1);
			race7AssertUtil.assertPilot(ir181, 6, 0, false, 2, 2);
			race7AssertUtil.assertPilot(sco179, 6, 0, false, 3, 3);
			race7AssertUtil.assertPilot(sco808, 6, 0, false, 4, 4);
			race7AssertUtil.assertPilot(sco116, 6, 0, false, 5, 5);
			race7AssertUtil.assertPilot(sco666, 5, 0, false, 6, 6);
			race7AssertUtil.assertPilot(sco159, 5, 0, false, 7, 7);
			race7AssertUtil.assertPilot(sco100, 4, 0, false, 8, 8);
			race7AssertUtil.assertPilot(sco018, 4, 0, false, 9, 9);
			race7AssertUtil.assertPilot(sco081, 3, 0, false, 10, 10);
			race7AssertUtil.assertPilot(sco087, 3, 0, false, 11, 11);
			race7AssertUtil.assertPilot(sco315, 3, 0, false, 12, 12);
			race7AssertUtil.assertPilot(sco060, 2, 0, false, 13, 13);
			race7AssertUtil.assertPilot(sco076, 0, 0, false, 18, 14);
			race7AssertUtil.assertPilot(sco153, 0, 0, false, 18, 14);
			race7AssertUtil.assertPilot(sco156, 0, 0, false, 18, 14);
			race7AssertUtil.assertPilot(sco528, 0, 0, false, 18, 14);
			race7AssertUtil.assertDone(0);

			RaceAssertUtil race8AssertUtil = new RaceAssertUtil(scores, race8);
			race8AssertUtil.assertPilot(sco200, 6, 0, false, 0, 1);
			race8AssertUtil.assertPilot(sco179, 6, 0, false, 2, 2);
			race8AssertUtil.assertPilot(sco808, 6, 1, false, 3, 3);
			race8AssertUtil.assertPilot(sco116, 6, 0, false, 4, 4);
			race8AssertUtil.assertPilot(sco666, 5, 0, false, 5, 5);
			race8AssertUtil.assertPilot(sco159, 5, 0, false, 6, 6);
			race8AssertUtil.assertPilot(sco100, 5, 0, false, 7, 7);
			race8AssertUtil.assertPilot(ir181, 5, 0, false, 8, 8);
			race8AssertUtil.assertPilot(sco081, 5, 0, false, 9, 9);
			race8AssertUtil.assertPilot(sco018, 5, 0, false, 10, 10);
			race8AssertUtil.assertPilot(sco528, 4, 0, false, 11, 11);
			race8AssertUtil.assertPilot(sco315, 4, 0, false, 12, 12);
			race8AssertUtil.assertPilot(sco087, 3, 0, false, 13, 13);
			race8AssertUtil.assertPilot(sco060, 2, 0, false, 14, 14);
			race8AssertUtil.assertPilot(sco076, 0, 0, false, 18, 15);
			race8AssertUtil.assertPilot(sco153, 0, 0, false, 18, 15);
			race8AssertUtil.assertPilot(sco156, 0, 0, false, 18, 15);
			race8AssertUtil.assertDone(0);

			RaceAssertUtil race9AssertUtil = new RaceAssertUtil(scores, race9);
			race9AssertUtil.assertPilot(sco200, 6, 0, false, 0, 1);
			race9AssertUtil.assertPilot(ir181, 6, 0, false, 2, 2);
			race9AssertUtil.assertPilot(sco179, 6, 0, false, 3, 3);
			race9AssertUtil.assertPilot(sco116, 6, 0, false, 4, 4);
			race9AssertUtil.assertPilot(sco808, 5, 0, false, 5, 5);
			race9AssertUtil.assertPilot(sco081, 5, 0, false, 6, 6);
			race9AssertUtil.assertPilot(sco528, 5, 0, false, 7, 7);
			race9AssertUtil.assertPilot(sco100, 5, 0, false, 8, 8);
			race9AssertUtil.assertPilot(sco159, 4, 0, false, 9, 9);
			race9AssertUtil.assertPilot(sco666, 4, 0, false, 10, 10);
			race9AssertUtil.assertPilot(sco018, 4, 0, false, 11, 11);
			race9AssertUtil.assertPilot(sco087, 4, 0, false, 12, 12);
			race9AssertUtil.assertPilot(sco315, 4, 0, false, 13, 13);
			race9AssertUtil.assertPilot(sco060, 1, 0, false, 14, 14);
			race9AssertUtil.assertPilot(sco076, 0, 0, false, 18, 15);
			race9AssertUtil.assertPilot(sco153, 0, 0, false, 18, 15);
			race9AssertUtil.assertPilot(sco156, 0, 0, false, 18, 15);
			race9AssertUtil.assertDone(0);

			RaceAssertUtil race10AssertUtil = new RaceAssertUtil(scores, race10);
			race10AssertUtil.assertPilot(ir181, 9, 0, false, 0, 1);
			race10AssertUtil.assertPilot(sco179, 9, 0, false, 2, 2);
			race10AssertUtil.assertPilot(sco200, 9, 1, false, 3, 3);
			race10AssertUtil.assertPilot(sco116, 9, 0, false, 4, 4);
			race10AssertUtil.assertPilot(sco808, 9, 0, false, 5, 5);
			race10AssertUtil.assertPilot(sco528, 8, 0, false, 6, 6);
			race10AssertUtil.assertPilot(sco159, 7, 0, false, 7, 7);
			race10AssertUtil.assertPilot(sco666, 7, 0, false, 8, 8);
			race10AssertUtil.assertPilot(sco087, 6, 0, false, 9, 9);
			race10AssertUtil.assertPilot(sco018, 6, 0, false, 10, 10);
			race10AssertUtil.assertPilot(sco315, 5, 0, false, 11, 11);
			race10AssertUtil.assertPilot(sco100, 4, 0, false, 12, 12);
			race10AssertUtil.assertPilot(sco060, 1, 0, false, 13, 13);
			race10AssertUtil.assertPilot(sco076, 0, 0, false, 18, 14);
			race10AssertUtil.assertPilot(sco081, 0, 0, false, 18, 14);
			race10AssertUtil.assertPilot(sco153, 0, 0, false, 18, 14);
			race10AssertUtil.assertPilot(sco156, 0, 0, false, 18, 14);
			race10AssertUtil.assertDone(0);

			RaceAssertUtil race11AssertUtil = new RaceAssertUtil(scores, race11);
			race11AssertUtil.assertPilot(sco200, 9, 0, false, 0, 1);
			race11AssertUtil.assertPilot(sco179, 9, 0, false, 2, 2);
			race11AssertUtil.assertPilot(ir181, 9, 0, false, 3, 3);
			race11AssertUtil.assertPilot(sco808, 9, 0, false, 4, 4);
			race11AssertUtil.assertPilot(sco116, 9, 0, false, 5, 5);
			race11AssertUtil.assertPilot(sco528, 8, 0, false, 6, 6);
			race11AssertUtil.assertPilot(sco666, 8, 0, false, 7, 7);
			race11AssertUtil.assertPilot(sco159, 7, 0, false, 8, 8);
			race11AssertUtil.assertPilot(sco081, 7, 0, false, 9, 9);
			race11AssertUtil.assertPilot(sco087, 6, 0, false, 10, 10);
			race11AssertUtil.assertPilot(sco100, 6, 0, false, 11, 11);
			race11AssertUtil.assertPilot(sco060, 5, 0, false, 12, 12);
			race11AssertUtil.assertPilot(sco315, 5, 0, false, 13, 13);
			race11AssertUtil.assertPilot(sco018, 0, 0, false, 18, 14);
			race11AssertUtil.assertPilot(sco076, 0, 0, false, 18, 14);
			race11AssertUtil.assertPilot(sco153, 0, 0, false, 18, 14);
			race11AssertUtil.assertPilot(sco156, 0, 0, false, 18, 14);
			race11AssertUtil.assertDone(0);

			RaceAssertUtil race12AssertUtil = new RaceAssertUtil(scores, race12);
			race12AssertUtil.assertPilot(sco200, 9, 0, false, 0, 1);
			race12AssertUtil.assertPilot(ir181, 9, 0, false, 2, 2);
			race12AssertUtil.assertPilot(sco808, 9, 0, false, 3, 3);
			race12AssertUtil.assertPilot(sco179, 9, 0, false, 4, 4);
			race12AssertUtil.assertPilot(sco528, 9, 0, false, 5, 5);
			race12AssertUtil.assertPilot(sco116, 9, 0, false, 6, 6);
			race12AssertUtil.assertPilot(sco666, 8, 1, false, 7, 7);
			race12AssertUtil.assertPilot(sco159, 8, 0, false, 8, 8);
			race12AssertUtil.assertPilot(sco060, 6, 0, false, 9, 9);
			race12AssertUtil.assertPilot(sco087, 4, 0, false, 10, 10);
			race12AssertUtil.assertPilot(sco018, 4, 0, false, 11, 11);
			race12AssertUtil.assertPilot(sco076, 0, 0, false, 18, 12);
			race12AssertUtil.assertPilot(sco081, 0, 0, false, 18, 12);
			race12AssertUtil.assertPilot(sco100, 0, 0, false, 18, 12);
			race12AssertUtil.assertPilot(sco153, 0, 0, false, 18, 12);
			race12AssertUtil.assertPilot(sco156, 0, 0, false, 18, 12);
			race12AssertUtil.assertPilot(sco315, 0, 0, false, 18, 12);
			race12AssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 1, 1, 1, 3);
			overallAssertUtil.assertPilot(ir181, 0, 9, 2, 8);
			overallAssertUtil.assertPilot(sco179, 0, 12, 3, 4);
			overallAssertUtil.assertPilot(sco808, 1, 20, 4, 5);
			overallAssertUtil.assertPilot(sco116, 0, 22, 5, 6);
			overallAssertUtil.assertPilot(sco666, 1, 34, 6, 10);
			overallAssertUtil.assertPilot(sco528, 0, 35, 7, 18);
			overallAssertUtil.assertPilot(sco159, 0, 36, 8, 9);
			overallAssertUtil.assertPilot(sco100, 0, 46, 9, 18);
			overallAssertUtil.assertPilot(sco018, 0, 51, 10, 18);
			overallAssertUtil.assertPilot(sco081, 0, 52, 11, 18);
			overallAssertUtil.assertPilot(sco087, 0, 52, 12, 13);
			overallAssertUtil.assertPilot(sco060, 0, 61, 13, 14);
			overallAssertUtil.assertPilot(sco315, 0, 61, 14, 18);
			overallAssertUtil.assertPilot(sco076, 0, 90, 15, 18);
			overallAssertUtil.assertPilot(sco153, 0, 90, 15, 18);
			overallAssertUtil.assertPilot(sco156, 0, 90, 15, 18);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace7() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race7 = raceDAO.find(event2, RACE7_NAME);

			Scores scores = scorer.scoreRace(race7, Predicates.in(getEventResultsPilots(series, event2)));
			Assert.assertEquals(EVENT2_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race7));

			RaceAssertUtil race7AssertUtil = new RaceAssertUtil(scores, race7);
			race7AssertUtil.assertPilot(sco200, 6, 0, false, 0, 1);
			race7AssertUtil.assertPilot(ir181, 6, 0, false, 2, 2);
			race7AssertUtil.assertPilot(sco179, 6, 0, false, 3, 3);
			race7AssertUtil.assertPilot(sco808, 6, 0, false, 4, 4);
			race7AssertUtil.assertPilot(sco116, 6, 0, false, 5, 5);
			race7AssertUtil.assertPilot(sco666, 5, 0, false, 6, 6);
			race7AssertUtil.assertPilot(sco159, 5, 0, false, 7, 7);
			race7AssertUtil.assertPilot(sco100, 4, 0, false, 8, 8);
			race7AssertUtil.assertPilot(sco018, 4, 0, false, 9, 9);
			race7AssertUtil.assertPilot(sco081, 3, 0, false, 10, 10);
			race7AssertUtil.assertPilot(sco087, 3, 0, false, 11, 11);
			race7AssertUtil.assertPilot(sco315, 3, 0, false, 12, 12);
			race7AssertUtil.assertPilot(sco060, 2, 0, false, 13, 13);
			race7AssertUtil.assertPilot(sco076, 0, 0, false, 18, 14);
			race7AssertUtil.assertPilot(sco153, 0, 0, false, 18, 14);
			race7AssertUtil.assertPilot(sco156, 0, 0, false, 18, 14);
			race7AssertUtil.assertPilot(sco528, 0, 0, false, 18, 14);
			race7AssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(ir181, 0, 2, 2);
			overallAssertUtil.assertPilot(sco179, 0, 3, 3);
			overallAssertUtil.assertPilot(sco808, 0, 4, 4);
			overallAssertUtil.assertPilot(sco116, 0, 5, 5);
			overallAssertUtil.assertPilot(sco666, 0, 6, 6);
			overallAssertUtil.assertPilot(sco159, 0, 7, 7);
			overallAssertUtil.assertPilot(sco100, 0, 8, 8);
			overallAssertUtil.assertPilot(sco018, 0, 9, 9);
			overallAssertUtil.assertPilot(sco081, 0, 10, 10);
			overallAssertUtil.assertPilot(sco087, 0, 11, 11);
			overallAssertUtil.assertPilot(sco315, 0, 12, 12);
			overallAssertUtil.assertPilot(sco060, 0, 13, 13);
			overallAssertUtil.assertPilot(sco076, 0, 18, 14);
			overallAssertUtil.assertPilot(sco153, 0, 18, 14);
			overallAssertUtil.assertPilot(sco156, 0, 18, 14);
			overallAssertUtil.assertPilot(sco528, 0, 18, 14);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace8() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race8 = raceDAO.find(event2, RACE8_NAME);

			Scores scores = scorer.scoreRace(race8, Predicates.in(getEventResultsPilots(series, event2)));
			Assert.assertEquals(EVENT2_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race8));

			RaceAssertUtil race8AssertUtil = new RaceAssertUtil(scores, race8);
			race8AssertUtil.assertPilot(sco200, 6, 0, false, 0, 1);
			race8AssertUtil.assertPilot(sco179, 6, 0, false, 2, 2);
			race8AssertUtil.assertPilot(sco808, 6, 1, false, 3, 3);
			race8AssertUtil.assertPilot(sco116, 6, 0, false, 4, 4);
			race8AssertUtil.assertPilot(sco666, 5, 0, false, 5, 5);
			race8AssertUtil.assertPilot(sco159, 5, 0, false, 6, 6);
			race8AssertUtil.assertPilot(sco100, 5, 0, false, 7, 7);
			race8AssertUtil.assertPilot(ir181, 5, 0, false, 8, 8);
			race8AssertUtil.assertPilot(sco081, 5, 0, false, 9, 9);
			race8AssertUtil.assertPilot(sco018, 5, 0, false, 10, 10);
			race8AssertUtil.assertPilot(sco528, 4, 0, false, 11, 11);
			race8AssertUtil.assertPilot(sco315, 4, 0, false, 12, 12);
			race8AssertUtil.assertPilot(sco087, 3, 0, false, 13, 13);
			race8AssertUtil.assertPilot(sco060, 2, 0, false, 14, 14);
			race8AssertUtil.assertPilot(sco076, 0, 0, false, 18, 15);
			race8AssertUtil.assertPilot(sco153, 0, 0, false, 18, 15);
			race8AssertUtil.assertPilot(sco156, 0, 0, false, 18, 15);
			race8AssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(sco179, 0, 2, 2);
			overallAssertUtil.assertPilot(sco808, 1, 4, 3);
			overallAssertUtil.assertPilot(sco116, 0, 4, 4);
			overallAssertUtil.assertPilot(sco666, 0, 5, 5);
			overallAssertUtil.assertPilot(sco159, 0, 6, 6);
			overallAssertUtil.assertPilot(sco100, 0, 7, 7);
			overallAssertUtil.assertPilot(ir181, 0, 8, 8);
			overallAssertUtil.assertPilot(sco081, 0, 9, 9);
			overallAssertUtil.assertPilot(sco018, 0, 10, 10);
			overallAssertUtil.assertPilot(sco528, 0, 11, 11);
			overallAssertUtil.assertPilot(sco315, 0, 12, 12);
			overallAssertUtil.assertPilot(sco087, 0, 13, 13);
			overallAssertUtil.assertPilot(sco060, 0, 14, 14);
			overallAssertUtil.assertPilot(sco076, 0, 18, 15);
			overallAssertUtil.assertPilot(sco153, 0, 18, 15);
			overallAssertUtil.assertPilot(sco156, 0, 18, 15);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace9() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race9 = raceDAO.find(event2, RACE9_NAME);

			Scores scores = scorer.scoreRace(race9, Predicates.in(getEventResultsPilots(series, event2)));
			Assert.assertEquals(EVENT2_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race9));

			RaceAssertUtil race9AssertUtil = new RaceAssertUtil(scores, race9);
			race9AssertUtil.assertPilot(sco200, 6, 0, false, 0, 1);
			race9AssertUtil.assertPilot(ir181, 6, 0, false, 2, 2);
			race9AssertUtil.assertPilot(sco179, 6, 0, false, 3, 3);
			race9AssertUtil.assertPilot(sco116, 6, 0, false, 4, 4);
			race9AssertUtil.assertPilot(sco808, 5, 0, false, 5, 5);
			race9AssertUtil.assertPilot(sco081, 5, 0, false, 6, 6);
			race9AssertUtil.assertPilot(sco528, 5, 0, false, 7, 7);
			race9AssertUtil.assertPilot(sco100, 5, 0, false, 8, 8);
			race9AssertUtil.assertPilot(sco159, 4, 0, false, 9, 9);
			race9AssertUtil.assertPilot(sco666, 4, 0, false, 10, 10);
			race9AssertUtil.assertPilot(sco018, 4, 0, false, 11, 11);
			race9AssertUtil.assertPilot(sco087, 4, 0, false, 12, 12);
			race9AssertUtil.assertPilot(sco315, 4, 0, false, 13, 13);
			race9AssertUtil.assertPilot(sco060, 1, 0, false, 14, 14);
			race9AssertUtil.assertPilot(sco076, 0, 0, false, 18, 15);
			race9AssertUtil.assertPilot(sco153, 0, 0, false, 18, 15);
			race9AssertUtil.assertPilot(sco156, 0, 0, false, 18, 15);
			race9AssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(ir181, 0, 2, 2);
			overallAssertUtil.assertPilot(sco179, 0, 3, 3);
			overallAssertUtil.assertPilot(sco116, 0, 4, 4);
			overallAssertUtil.assertPilot(sco808, 0, 5, 5);
			overallAssertUtil.assertPilot(sco081, 0, 6, 6);
			overallAssertUtil.assertPilot(sco528, 0, 7, 7);
			overallAssertUtil.assertPilot(sco100, 0, 8, 8);
			overallAssertUtil.assertPilot(sco159, 0, 9, 9);
			overallAssertUtil.assertPilot(sco666, 0, 10, 10);
			overallAssertUtil.assertPilot(sco018, 0, 11, 11);
			overallAssertUtil.assertPilot(sco087, 0, 12, 12);
			overallAssertUtil.assertPilot(sco315, 0, 13, 13);
			overallAssertUtil.assertPilot(sco060, 0, 14, 14);
			overallAssertUtil.assertPilot(sco076, 0, 18, 15);
			overallAssertUtil.assertPilot(sco153, 0, 18, 15);
			overallAssertUtil.assertPilot(sco156, 0, 18, 15);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace10() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race10 = raceDAO.find(event2, RACE10_NAME);

			Scores scores = scorer.scoreRace(race10, Predicates.in(getEventResultsPilots(series, event2)));
			Assert.assertEquals(EVENT2_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race10));

			RaceAssertUtil race10AssertUtil = new RaceAssertUtil(scores, race10);
			race10AssertUtil.assertPilot(ir181, 9, 0, false, 0, 1);
			race10AssertUtil.assertPilot(sco179, 9, 0, false, 2, 2);
			race10AssertUtil.assertPilot(sco200, 9, 1, false, 3, 3);
			race10AssertUtil.assertPilot(sco116, 9, 0, false, 4, 4);
			race10AssertUtil.assertPilot(sco808, 9, 0, false, 5, 5);
			race10AssertUtil.assertPilot(sco528, 8, 0, false, 6, 6);
			race10AssertUtil.assertPilot(sco159, 7, 0, false, 7, 7);
			race10AssertUtil.assertPilot(sco666, 7, 0, false, 8, 8);
			race10AssertUtil.assertPilot(sco087, 6, 0, false, 9, 9);
			race10AssertUtil.assertPilot(sco018, 6, 0, false, 10, 10);
			race10AssertUtil.assertPilot(sco315, 5, 0, false, 11, 11);
			race10AssertUtil.assertPilot(sco100, 4, 0, false, 12, 12);
			race10AssertUtil.assertPilot(sco060, 1, 0, false, 13, 13);
			race10AssertUtil.assertPilot(sco076, 0, 0, false, 18, 14);
			race10AssertUtil.assertPilot(sco081, 0, 0, false, 18, 14);
			race10AssertUtil.assertPilot(sco153, 0, 0, false, 18, 14);
			race10AssertUtil.assertPilot(sco156, 0, 0, false, 18, 14);
			race10AssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(ir181, 0, 0, 1);
			overallAssertUtil.assertPilot(sco179, 0, 2, 2);
			overallAssertUtil.assertPilot(sco200, 1, 4, 3);
			overallAssertUtil.assertPilot(sco116, 0, 4, 4);
			overallAssertUtil.assertPilot(sco808, 0, 5, 5);
			overallAssertUtil.assertPilot(sco528, 0, 6, 6);
			overallAssertUtil.assertPilot(sco159, 0, 7, 7);
			overallAssertUtil.assertPilot(sco666, 0, 8, 8);
			overallAssertUtil.assertPilot(sco087, 0, 9, 9);
			overallAssertUtil.assertPilot(sco018, 0, 10, 10);
			overallAssertUtil.assertPilot(sco315, 0, 11, 11);
			overallAssertUtil.assertPilot(sco100, 0, 12, 12);
			overallAssertUtil.assertPilot(sco060, 0, 13, 13);
			overallAssertUtil.assertPilot(sco076, 0, 18, 14);
			overallAssertUtil.assertPilot(sco081, 0, 18, 14);
			overallAssertUtil.assertPilot(sco153, 0, 18, 14);
			overallAssertUtil.assertPilot(sco156, 0, 18, 14);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace11() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race11 = raceDAO.find(event2, RACE11_NAME);

			Scores scores = scorer.scoreRace(race11, Predicates.in(getEventResultsPilots(series, event2)));
			Assert.assertEquals(EVENT2_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race11));

			RaceAssertUtil race11AssertUtil = new RaceAssertUtil(scores, race11);
			race11AssertUtil.assertPilot(sco200, 9, 0, false, 0, 1);
			race11AssertUtil.assertPilot(sco179, 9, 0, false, 2, 2);
			race11AssertUtil.assertPilot(ir181, 9, 0, false, 3, 3);
			race11AssertUtil.assertPilot(sco808, 9, 0, false, 4, 4);
			race11AssertUtil.assertPilot(sco116, 9, 0, false, 5, 5);
			race11AssertUtil.assertPilot(sco528, 8, 0, false, 6, 6);
			race11AssertUtil.assertPilot(sco666, 8, 0, false, 7, 7);
			race11AssertUtil.assertPilot(sco159, 7, 0, false, 8, 8);
			race11AssertUtil.assertPilot(sco081, 7, 0, false, 9, 9);
			race11AssertUtil.assertPilot(sco087, 6, 0, false, 10, 10);
			race11AssertUtil.assertPilot(sco100, 6, 0, false, 11, 11);
			race11AssertUtil.assertPilot(sco060, 5, 0, false, 12, 12);
			race11AssertUtil.assertPilot(sco315, 5, 0, false, 13, 13);
			race11AssertUtil.assertPilot(sco018, 0, 0, false, 18, 14);
			race11AssertUtil.assertPilot(sco076, 0, 0, false, 18, 14);
			race11AssertUtil.assertPilot(sco153, 0, 0, false, 18, 14);
			race11AssertUtil.assertPilot(sco156, 0, 0, false, 18, 14);
			race11AssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(sco179, 0, 2, 2);
			overallAssertUtil.assertPilot(ir181, 0, 3, 3);
			overallAssertUtil.assertPilot(sco808, 0, 4, 4);
			overallAssertUtil.assertPilot(sco116, 0, 5, 5);
			overallAssertUtil.assertPilot(sco528, 0, 6, 6);
			overallAssertUtil.assertPilot(sco666, 0, 7, 7);
			overallAssertUtil.assertPilot(sco159, 0, 8, 8);
			overallAssertUtil.assertPilot(sco081, 0, 9, 9);
			overallAssertUtil.assertPilot(sco087, 0, 10, 10);
			overallAssertUtil.assertPilot(sco100, 0, 11, 11);
			overallAssertUtil.assertPilot(sco060, 0, 12, 12);
			overallAssertUtil.assertPilot(sco315, 0, 13, 13);
			overallAssertUtil.assertPilot(sco018, 0, 18, 14);
			overallAssertUtil.assertPilot(sco076, 0, 18, 14);
			overallAssertUtil.assertPilot(sco153, 0, 18, 14);
			overallAssertUtil.assertPilot(sco156, 0, 18, 14);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace12() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race12 = raceDAO.find(event2, RACE12_NAME);

			Scores scores = scorer.scoreRace(race12, Predicates.in(getEventResultsPilots(series, event2)));
			Assert.assertEquals(EVENT2_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race12));

			RaceAssertUtil race12AssertUtil = new RaceAssertUtil(scores, race12);
			race12AssertUtil.assertPilot(sco200, 9, 0, false, 0, 1);
			race12AssertUtil.assertPilot(ir181, 9, 0, false, 2, 2);
			race12AssertUtil.assertPilot(sco808, 9, 0, false, 3, 3);
			race12AssertUtil.assertPilot(sco179, 9, 0, false, 4, 4);
			race12AssertUtil.assertPilot(sco528, 9, 0, false, 5, 5);
			race12AssertUtil.assertPilot(sco116, 9, 0, false, 6, 6);
			race12AssertUtil.assertPilot(sco666, 8, 1, false, 7, 7);
			race12AssertUtil.assertPilot(sco159, 8, 0, false, 8, 8);
			race12AssertUtil.assertPilot(sco060, 6, 0, false, 9, 9);
			race12AssertUtil.assertPilot(sco087, 4, 0, false, 10, 10);
			race12AssertUtil.assertPilot(sco018, 4, 0, false, 11, 11);
			race12AssertUtil.assertPilot(sco076, 0, 0, false, 18, 12);
			race12AssertUtil.assertPilot(sco081, 0, 0, false, 18, 12);
			race12AssertUtil.assertPilot(sco100, 0, 0, false, 18, 12);
			race12AssertUtil.assertPilot(sco153, 0, 0, false, 18, 12);
			race12AssertUtil.assertPilot(sco156, 0, 0, false, 18, 12);
			race12AssertUtil.assertPilot(sco315, 0, 0, false, 18, 12);
			race12AssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(ir181, 0, 2, 2);
			overallAssertUtil.assertPilot(sco808, 0, 3, 3);
			overallAssertUtil.assertPilot(sco179, 0, 4, 4);
			overallAssertUtil.assertPilot(sco528, 0, 5, 5);
			overallAssertUtil.assertPilot(sco116, 0, 6, 6);
			overallAssertUtil.assertPilot(sco666, 1, 8, 7);
			overallAssertUtil.assertPilot(sco159, 0, 8, 8);
			overallAssertUtil.assertPilot(sco060, 0, 9, 9);
			overallAssertUtil.assertPilot(sco087, 0, 10, 10);
			overallAssertUtil.assertPilot(sco018, 0, 11, 11);
			overallAssertUtil.assertPilot(sco076, 0, 18, 12);
			overallAssertUtil.assertPilot(sco081, 0, 18, 12);
			overallAssertUtil.assertPilot(sco100, 0, 18, 12);
			overallAssertUtil.assertPilot(sco153, 0, 18, 12);
			overallAssertUtil.assertPilot(sco156, 0, 18, 12);
			overallAssertUtil.assertPilot(sco315, 0, 18, 12);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}
