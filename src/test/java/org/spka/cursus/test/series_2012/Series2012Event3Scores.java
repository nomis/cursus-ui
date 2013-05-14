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
 * Scores at the end of event 3 (16/03/2013 to 17/03/2013)
 */
public class Series2012Event3Scores extends Series2012Event2Scores {
	@Override
	@Before
	public void createData() throws Exception {
		super.createData();
		createNonEvent1Data();
		createNonEvent2Data();
		createEvent1Races();
		createEvent2Races();
		createEvent3Races();
	}

	@Override
	@Test
	public void checkSeries() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Scores scores = scorer.scoreSeries(series, getSeriesResultsPilots(series, event3), Predicates.in(getSeriesResultsPilots(series, event3)));
			checkSeriesAtEvent3(scores);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkSeriesAtEvent3() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);

			List<Race> races = new ArrayList<Race>();
			races.addAll(event1.getRaces());
			races.addAll(event2.getRaces());
			races.addAll(event3.getRaces());

			Scores scores = scorer.scoreRaces(races, getSeriesResultsPilots(series, event3), getSeriesResultsEvents(series, event3),
					Predicates.in(getSeriesResultsPilots(series, event3)));
			checkSeriesAtEvent3(scores);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	private void checkSeriesAtEvent3(Scores scores) throws Exception {
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
		Event event3 = eventDAO.find(series, EVENT3_NAME);
		Race race13 = raceDAO.find(event3, RACE13_NAME);
		Race race14 = raceDAO.find(event3, RACE14_NAME);
		Race race15 = raceDAO.find(event3, RACE15_NAME);

		Assert.assertEquals(SERIES_FLEET_AT_EVENT3, scores.getPilots().size());
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
		Assert.assertEquals(RACE13_PILOTS, scores.getFleetSize(race13));
		Assert.assertEquals(RACE14_PILOTS, scores.getFleetSize(race14));
		Assert.assertEquals(RACE15_PILOTS, scores.getFleetSize(race15));

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

		RaceAssertUtil race13AssertUtil = new RaceAssertUtil(scores, race13);
		race13AssertUtil.assertPilot(sco179, 9, 0, false, 0, 1);
		race13AssertUtil.assertPilot(sco528, 8, 0, false, 2, 2);
		race13AssertUtil.assertPilot(sco116, 8, 0, false, 3, 3);
		race13AssertUtil.assertPilot(sco159, 8, 0, false, 4, 4);
		race13AssertUtil.assertPilot(sco315, 7, 0, false, 5, 5);
		race13AssertUtil.assertPilot(sco200, 7, 0, false, 6, 6);
		race13AssertUtil.assertPilot(sco808, 6, 1, false, 7, 7);
		race13AssertUtil.assertPilot(sco666, 5, 0, false, 8, 8);
		race13AssertUtil.assertPilot(sco060, 4, 0, false, 9, 9);
		race13AssertUtil.assertPilot(sco153, 4, 0, false, 10, 10);
		race13AssertUtil.assertPilot(sco117, 3, 0, false, 11, 11);
		race13AssertUtil.assertPilot(sco320, 2, 0, false, 12, 12);
		race13AssertUtil.assertPilot(sco777, 1, 0, false, 13, 13);
		race13AssertUtil.assertPilot(sco018, 0, 0, false, 19, 14);
		race13AssertUtil.assertPilot(sco076, 0, 0, false, 19, 14);
		race13AssertUtil.assertPilot(sco081, 0, 0, false, 19, 14);
		race13AssertUtil.assertPilot(sco156, 0, 0, false, 19, 14);
		race13AssertUtil.assertPilot(sco561, 0, 0, false, 19, 14);
		race13AssertUtil.assertPilot(sco087, 0, 0, false, 23, 19);
		race13AssertUtil.assertPilot(sco100, 0, 0, false, 23, 19);
		race13AssertUtil.assertPilot(sco158, 0, 0, false, 23, 19);
		race13AssertUtil.assertPilot(sco467, 0, 0, false, 23, 19);
		race13AssertUtil.assertDone(0);

		RaceAssertUtil race14AssertUtil = new RaceAssertUtil(scores, race14);
		race14AssertUtil.assertPilot(sco808, 9, 0, false, 0, 1);
		race14AssertUtil.assertPilot(sco116, 9, 0, false, 2, 2);
		race14AssertUtil.assertPilot(sco200, 9, 0, false, 3, 3);
		race14AssertUtil.assertPilot(sco179, 9, 0, false, 4, 4);
		race14AssertUtil.assertPilot(sco159, 8, 0, false, 5, 5);
		race14AssertUtil.assertPilot(sco528, 7, 0, false, 6, 6);
		race14AssertUtil.assertPilot(sco315, 7, 0, false, 7, 7);
		race14AssertUtil.assertPilot(sco060, 6, 0, false, 8, 8);
		race14AssertUtil.assertPilot(sco117, 6, 0, false, 9, 9);
		race14AssertUtil.assertPilot(sco666, 5, 0, false, 10, 10);
		race14AssertUtil.assertPilot(sco777, 4, 0, false, 11, 11);
		race14AssertUtil.assertPilot(sco018, 4, 0, false, 12, 12);
		race14AssertUtil.assertPilot(sco561, 2, 0, false, 13, 13);
		race14AssertUtil.assertPilot(sco076, 0, 0, false, 19, 14);
		race14AssertUtil.assertPilot(sco081, 0, 0, false, 19, 14);
		race14AssertUtil.assertPilot(sco153, 0, 0, false, 19, 14);
		race14AssertUtil.assertPilot(sco156, 0, 0, false, 19, 14);
		race14AssertUtil.assertPilot(sco320, 0, 0, false, 19, 14);
		race14AssertUtil.assertPilot(sco087, 0, 0, false, 23, 19);
		race14AssertUtil.assertPilot(sco100, 0, 0, false, 23, 19);
		race14AssertUtil.assertPilot(sco158, 0, 0, false, 23, 19);
		race14AssertUtil.assertPilot(sco467, 0, 0, false, 23, 19);
		race14AssertUtil.assertDone(0);

		RaceAssertUtil race15AssertUtil = new RaceAssertUtil(scores, race15);
		race15AssertUtil.assertPilot(sco808, 8, 0, false, 0, 1);
		race15AssertUtil.assertPilot(sco200, 8, 0, false, 2, 2);
		race15AssertUtil.assertPilot(sco159, 8, 1, false, 3, 3);
		race15AssertUtil.assertPilot(sco179, 7, 0, false, 4, 4);
		race15AssertUtil.assertPilot(sco116, 7, 0, false, 5, 5);
		race15AssertUtil.assertPilot(sco528, 6, 0, false, 6, 6);
		race15AssertUtil.assertPilot(sco666, 5, 0, false, 7, 7);
		race15AssertUtil.assertPilot(sco315, 4, 0, false, 8, 8);
		race15AssertUtil.assertPilot(sco060, 3, 0, false, 9, 9);
		race15AssertUtil.assertPilot(sco777, 2, 0, false, 10, 10);
		race15AssertUtil.assertPilot(sco153, 1, 0, false, 11, 11);
		race15AssertUtil.assertPilot(sco018, 0, 0, false, 19, 12);
		race15AssertUtil.assertPilot(sco076, 0, 0, false, 19, 12);
		race15AssertUtil.assertPilot(sco081, 0, 0, false, 19, 12);
		race15AssertUtil.assertPilot(sco117, 0, 0, false, 19, 12);
		race15AssertUtil.assertPilot(sco156, 0, 0, false, 19, 12);
		race15AssertUtil.assertPilot(sco320, 0, 0, false, 19, 12);
		race15AssertUtil.assertPilot(sco561, 0, 0, false, 19, 12);
		race15AssertUtil.assertPilot(sco087, 0, 0, false, 23, 19);
		race15AssertUtil.assertPilot(sco100, 0, 0, false, 23, 19);
		race15AssertUtil.assertPilot(sco158, 0, 0, false, 23, 19);
		race15AssertUtil.assertPilot(sco467, 0, 0, false, 23, 19);
		race15AssertUtil.assertDone(0);

		OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
		overallAssertUtil.assertPilot(sco200, 1, 11, 1, 6, 3, 3);
		overallAssertUtil.assertPilot(sco179, 0, 23, 2, 8, 7, 5);
		overallAssertUtil.assertPilot(sco808, 3, 30, 3, 8, 7, 6);
		overallAssertUtil.assertPilot(sco116, 0, 39, 4, 6, 5, 5);
		overallAssertUtil.assertPilot(sco528, 0, 56, 5, 17, 10, 10);
		overallAssertUtil.assertPilot(sco159, 2, 56, 6, 8, 7, 7);
		overallAssertUtil.assertPilot(sco666, 1, 78, 7, 17, 17, 10);
		overallAssertUtil.assertPilot(sco060, 0, 109, 8, 13, 13, 12);
		overallAssertUtil.assertPilot(sco315, 0, 127, 9, 17, 17, 17);
		overallAssertUtil.assertPilot(sco081, 0, 144, 10, 19, 19, 19);
		overallAssertUtil.assertPilot(sco018, 0, 154, 11, 19, 19, 17);
		overallAssertUtil.assertPilot(sco117, 0, 161, 12, 23, 23, 23);
		overallAssertUtil.assertPilot(sco076, 0, 184, 13, 19, 19, 19);
		overallAssertUtil.assertPilot(sco777, 0, 184, 14, 23, 23, 23);
		overallAssertUtil.assertPilot(sco153, 0, 191, 15, 19, 17, 17);
		overallAssertUtil.assertPilot(sco100, 0, 197, 16, 23, 23, 23);
		overallAssertUtil.assertPilot(sco087, 0, 197, 17, 23, 23, 23);
		overallAssertUtil.assertPilot(sco158, 0, 219, 18, 23, 23, 23);
		overallAssertUtil.assertPilot(sco156, 0, 228, 19, 23, 23, 23);
		overallAssertUtil.assertPilot(sco320, 0, 257, 20, 23, 23, 23);
		overallAssertUtil.assertPilot(sco561, 0, 258, 21, 23, 23, 23);
		overallAssertUtil.assertPilot(sco467, 0, 276, 22, 23, 23, 23);
		overallAssertUtil.assertOrder();
	}

	@Test
	public final void checkEvent3() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Race race13 = raceDAO.find(event3, RACE13_NAME);
			Race race14 = raceDAO.find(event3, RACE14_NAME);
			Race race15 = raceDAO.find(event3, RACE15_NAME);

			Scores scores = scorer.scoreEvent(event3, Predicates.in(getEventResultsPilots(series, event3)));
			Assert.assertEquals(EVENT3_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT3_FLEET, scores.getFleetSize(race13));
			Assert.assertEquals(EVENT3_FLEET, scores.getFleetSize(race14));
			Assert.assertEquals(EVENT3_FLEET, scores.getFleetSize(race15));

			RaceAssertUtil race13AssertUtil = new RaceAssertUtil(scores, race13);
			race13AssertUtil.assertPilot(sco179, 9, 0, false, 0, 1);
			race13AssertUtil.assertPilot(ir181, 9, 0, false, 2, 2);
			race13AssertUtil.assertPilot(sco528, 8, 0, false, 3, 3);
			race13AssertUtil.assertPilot(sco116, 8, 0, false, 4, 4);
			race13AssertUtil.assertPilot(sco159, 8, 0, false, 5, 5);
			race13AssertUtil.assertPilot(sco315, 7, 0, false, 6, 6);
			race13AssertUtil.assertPilot(sco200, 7, 0, false, 7, 7);
			race13AssertUtil.assertPilot(sco808, 6, 1, false, 8, 8);
			race13AssertUtil.assertPilot(sco666, 5, 0, false, 9, 9);
			race13AssertUtil.assertPilot(sco060, 4, 0, false, 10, 10);
			race13AssertUtil.assertPilot(sco153, 4, 0, false, 11, 11);
			race13AssertUtil.assertPilot(sco117, 3, 0, false, 12, 12);
			race13AssertUtil.assertPilot(sco320, 2, 0, false, 13, 13);
			race13AssertUtil.assertPilot(sco777, 1, 0, false, 14, 14);
			race13AssertUtil.assertPilot(sco018, 0, 0, false, 20, 15);
			race13AssertUtil.assertPilot(sco076, 0, 0, false, 20, 15);
			race13AssertUtil.assertPilot(sco081, 0, 0, false, 20, 15);
			race13AssertUtil.assertPilot(sco156, 0, 0, false, 20, 15);
			race13AssertUtil.assertPilot(sco561, 0, 0, false, 20, 15);
			race13AssertUtil.assertDone(0);

			RaceAssertUtil race14AssertUtil = new RaceAssertUtil(scores, race14);
			race14AssertUtil.assertPilot(ir181, 9, 0, false, 0, 1);
			race14AssertUtil.assertPilot(sco808, 9, 0, false, 2, 2);
			race14AssertUtil.assertPilot(sco116, 9, 0, false, 3, 3);
			race14AssertUtil.assertPilot(sco200, 9, 0, false, 4, 4);
			race14AssertUtil.assertPilot(sco179, 9, 0, false, 5, 5);
			race14AssertUtil.assertPilot(sco159, 8, 0, false, 6, 6);
			race14AssertUtil.assertPilot(sco528, 7, 0, false, 7, 7);
			race14AssertUtil.assertPilot(sco315, 7, 0, false, 8, 8);
			race14AssertUtil.assertPilot(sco060, 6, 0, false, 9, 9);
			race14AssertUtil.assertPilot(sco117, 6, 0, false, 10, 10);
			race14AssertUtil.assertPilot(sco666, 5, 0, false, 11, 11);
			race14AssertUtil.assertPilot(sco777, 4, 0, false, 12, 12);
			race14AssertUtil.assertPilot(sco018, 4, 0, false, 13, 13);
			race14AssertUtil.assertPilot(sco561, 2, 0, false, 14, 14);
			race14AssertUtil.assertPilot(sco076, 0, 0, false, 20, 15);
			race14AssertUtil.assertPilot(sco081, 0, 0, false, 20, 15);
			race14AssertUtil.assertPilot(sco153, 0, 0, false, 20, 15);
			race14AssertUtil.assertPilot(sco156, 0, 0, false, 20, 15);
			race14AssertUtil.assertPilot(sco320, 0, 0, false, 20, 15);
			race14AssertUtil.assertDone(0);

			RaceAssertUtil race15AssertUtil = new RaceAssertUtil(scores, race15);
			race15AssertUtil.assertPilot(ir181, 8, 0, false, 0, 1);
			race15AssertUtil.assertPilot(sco808, 8, 0, false, 2, 2);
			race15AssertUtil.assertPilot(sco200, 8, 0, false, 3, 3);
			race15AssertUtil.assertPilot(sco159, 8, 1, false, 4, 4);
			race15AssertUtil.assertPilot(sco179, 7, 0, false, 5, 5);
			race15AssertUtil.assertPilot(sco116, 7, 0, false, 6, 6);
			race15AssertUtil.assertPilot(sco528, 6, 0, false, 7, 7);
			race15AssertUtil.assertPilot(sco666, 5, 0, false, 8, 8);
			race15AssertUtil.assertPilot(sco315, 4, 0, false, 9, 9);
			race15AssertUtil.assertPilot(sco060, 3, 0, false, 10, 10);
			race15AssertUtil.assertPilot(sco777, 2, 0, false, 11, 11);
			race15AssertUtil.assertPilot(sco153, 1, 0, false, 12, 12);
			race15AssertUtil.assertPilot(sco018, 0, 0, false, 20, 13);
			race15AssertUtil.assertPilot(sco076, 0, 0, false, 20, 13);
			race15AssertUtil.assertPilot(sco081, 0, 0, false, 20, 13);
			race15AssertUtil.assertPilot(sco117, 0, 0, false, 20, 13);
			race15AssertUtil.assertPilot(sco156, 0, 0, false, 20, 13);
			race15AssertUtil.assertPilot(sco320, 0, 0, false, 20, 13);
			race15AssertUtil.assertPilot(sco561, 0, 0, false, 20, 13);
			race15AssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(ir181, 0, 2, 1);
			overallAssertUtil.assertPilot(sco179, 0, 10, 2);
			overallAssertUtil.assertPilot(sco808, 1, 13, 3);
			overallAssertUtil.assertPilot(sco116, 0, 13, 4);
			overallAssertUtil.assertPilot(sco200, 0, 14, 5);
			overallAssertUtil.assertPilot(sco159, 1, 16, 6);
			overallAssertUtil.assertPilot(sco528, 0, 17, 7);
			overallAssertUtil.assertPilot(sco315, 0, 23, 8);
			overallAssertUtil.assertPilot(sco666, 0, 28, 9);
			overallAssertUtil.assertPilot(sco060, 0, 29, 10);
			overallAssertUtil.assertPilot(sco777, 0, 37, 11);
			overallAssertUtil.assertPilot(sco117, 0, 42, 12);
			overallAssertUtil.assertPilot(sco153, 0, 43, 13);
			overallAssertUtil.assertPilot(sco018, 0, 53, 14);
			overallAssertUtil.assertPilot(sco320, 0, 53, 14);
			overallAssertUtil.assertPilot(sco561, 0, 54, 16);
			overallAssertUtil.assertPilot(sco076, 0, 60, 17);
			overallAssertUtil.assertPilot(sco081, 0, 60, 17);
			overallAssertUtil.assertPilot(sco156, 0, 60, 17);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace13() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Race race13 = raceDAO.find(event3, RACE13_NAME);

			Scores scores = scorer.scoreRace(race13, Predicates.in(getEventResultsPilots(series, event3)));
			Assert.assertEquals(EVENT3_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT3_FLEET, scores.getFleetSize(race13));

			RaceAssertUtil race13AssertUtil = new RaceAssertUtil(scores, race13);
			race13AssertUtil.assertPilot(sco179, 9, 0, false, 0, 1);
			race13AssertUtil.assertPilot(ir181, 9, 0, false, 2, 2);
			race13AssertUtil.assertPilot(sco528, 8, 0, false, 3, 3);
			race13AssertUtil.assertPilot(sco116, 8, 0, false, 4, 4);
			race13AssertUtil.assertPilot(sco159, 8, 0, false, 5, 5);
			race13AssertUtil.assertPilot(sco315, 7, 0, false, 6, 6);
			race13AssertUtil.assertPilot(sco200, 7, 0, false, 7, 7);
			race13AssertUtil.assertPilot(sco808, 6, 1, false, 8, 8);
			race13AssertUtil.assertPilot(sco666, 5, 0, false, 9, 9);
			race13AssertUtil.assertPilot(sco060, 4, 0, false, 10, 10);
			race13AssertUtil.assertPilot(sco153, 4, 0, false, 11, 11);
			race13AssertUtil.assertPilot(sco117, 3, 0, false, 12, 12);
			race13AssertUtil.assertPilot(sco320, 2, 0, false, 13, 13);
			race13AssertUtil.assertPilot(sco777, 1, 0, false, 14, 14);
			race13AssertUtil.assertPilot(sco018, 0, 0, false, 20, 15);
			race13AssertUtil.assertPilot(sco076, 0, 0, false, 20, 15);
			race13AssertUtil.assertPilot(sco081, 0, 0, false, 20, 15);
			race13AssertUtil.assertPilot(sco156, 0, 0, false, 20, 15);
			race13AssertUtil.assertPilot(sco561, 0, 0, false, 20, 15);
			race13AssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco179, 0, 0, 1);
			overallAssertUtil.assertPilot(ir181, 0, 2, 2);
			overallAssertUtil.assertPilot(sco528, 0, 3, 3);
			overallAssertUtil.assertPilot(sco116, 0, 4, 4);
			overallAssertUtil.assertPilot(sco159, 0, 5, 5);
			overallAssertUtil.assertPilot(sco315, 0, 6, 6);
			overallAssertUtil.assertPilot(sco200, 0, 7, 7);
			overallAssertUtil.assertPilot(sco808, 1, 9, 8);
			overallAssertUtil.assertPilot(sco666, 0, 9, 9);
			overallAssertUtil.assertPilot(sco060, 0, 10, 10);
			overallAssertUtil.assertPilot(sco153, 0, 11, 11);
			overallAssertUtil.assertPilot(sco117, 0, 12, 12);
			overallAssertUtil.assertPilot(sco320, 0, 13, 13);
			overallAssertUtil.assertPilot(sco777, 0, 14, 14);
			overallAssertUtil.assertPilot(sco018, 0, 20, 15);
			overallAssertUtil.assertPilot(sco076, 0, 20, 15);
			overallAssertUtil.assertPilot(sco081, 0, 20, 15);
			overallAssertUtil.assertPilot(sco156, 0, 20, 15);
			overallAssertUtil.assertPilot(sco561, 0, 20, 15);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace14() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Race race14 = raceDAO.find(event3, RACE14_NAME);

			Scores scores = scorer.scoreRace(race14, Predicates.in(getEventResultsPilots(series, event3)));
			Assert.assertEquals(EVENT3_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT3_FLEET, scores.getFleetSize(race14));

			RaceAssertUtil race14AssertUtil = new RaceAssertUtil(scores, race14);
			race14AssertUtil.assertPilot(ir181, 9, 0, false, 0, 1);
			race14AssertUtil.assertPilot(sco808, 9, 0, false, 2, 2);
			race14AssertUtil.assertPilot(sco116, 9, 0, false, 3, 3);
			race14AssertUtil.assertPilot(sco200, 9, 0, false, 4, 4);
			race14AssertUtil.assertPilot(sco179, 9, 0, false, 5, 5);
			race14AssertUtil.assertPilot(sco159, 8, 0, false, 6, 6);
			race14AssertUtil.assertPilot(sco528, 7, 0, false, 7, 7);
			race14AssertUtil.assertPilot(sco315, 7, 0, false, 8, 8);
			race14AssertUtil.assertPilot(sco060, 6, 0, false, 9, 9);
			race14AssertUtil.assertPilot(sco117, 6, 0, false, 10, 10);
			race14AssertUtil.assertPilot(sco666, 5, 0, false, 11, 11);
			race14AssertUtil.assertPilot(sco777, 4, 0, false, 12, 12);
			race14AssertUtil.assertPilot(sco018, 4, 0, false, 13, 13);
			race14AssertUtil.assertPilot(sco561, 2, 0, false, 14, 14);
			race14AssertUtil.assertPilot(sco076, 0, 0, false, 20, 15);
			race14AssertUtil.assertPilot(sco081, 0, 0, false, 20, 15);
			race14AssertUtil.assertPilot(sco153, 0, 0, false, 20, 15);
			race14AssertUtil.assertPilot(sco156, 0, 0, false, 20, 15);
			race14AssertUtil.assertPilot(sco320, 0, 0, false, 20, 15);
			race14AssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(ir181, 0, 0, 1);
			overallAssertUtil.assertPilot(sco808, 0, 2, 2);
			overallAssertUtil.assertPilot(sco116, 0, 3, 3);
			overallAssertUtil.assertPilot(sco200, 0, 4, 4);
			overallAssertUtil.assertPilot(sco179, 0, 5, 5);
			overallAssertUtil.assertPilot(sco159, 0, 6, 6);
			overallAssertUtil.assertPilot(sco528, 0, 7, 7);
			overallAssertUtil.assertPilot(sco315, 0, 8, 8);
			overallAssertUtil.assertPilot(sco060, 0, 9, 9);
			overallAssertUtil.assertPilot(sco117, 0, 10, 10);
			overallAssertUtil.assertPilot(sco666, 0, 11, 11);
			overallAssertUtil.assertPilot(sco777, 0, 12, 12);
			overallAssertUtil.assertPilot(sco018, 0, 13, 13);
			overallAssertUtil.assertPilot(sco561, 0, 14, 14);
			overallAssertUtil.assertPilot(sco076, 0, 20, 15);
			overallAssertUtil.assertPilot(sco081, 0, 20, 15);
			overallAssertUtil.assertPilot(sco153, 0, 20, 15);
			overallAssertUtil.assertPilot(sco156, 0, 20, 15);
			overallAssertUtil.assertPilot(sco320, 0, 20, 15);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace15() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Race race15 = raceDAO.find(event3, RACE15_NAME);

			Scores scores = scorer.scoreRace(race15, Predicates.in(getEventResultsPilots(series, event3)));
			Assert.assertEquals(EVENT3_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT3_FLEET, scores.getFleetSize(race15));

			RaceAssertUtil race15AssertUtil = new RaceAssertUtil(scores, race15);
			race15AssertUtil.assertPilot(ir181, 8, 0, false, 0, 1);
			race15AssertUtil.assertPilot(sco808, 8, 0, false, 2, 2);
			race15AssertUtil.assertPilot(sco200, 8, 0, false, 3, 3);
			race15AssertUtil.assertPilot(sco159, 8, 1, false, 4, 4);
			race15AssertUtil.assertPilot(sco179, 7, 0, false, 5, 5);
			race15AssertUtil.assertPilot(sco116, 7, 0, false, 6, 6);
			race15AssertUtil.assertPilot(sco528, 6, 0, false, 7, 7);
			race15AssertUtil.assertPilot(sco666, 5, 0, false, 8, 8);
			race15AssertUtil.assertPilot(sco315, 4, 0, false, 9, 9);
			race15AssertUtil.assertPilot(sco060, 3, 0, false, 10, 10);
			race15AssertUtil.assertPilot(sco777, 2, 0, false, 11, 11);
			race15AssertUtil.assertPilot(sco153, 1, 0, false, 12, 12);
			race15AssertUtil.assertPilot(sco018, 0, 0, false, 20, 13);
			race15AssertUtil.assertPilot(sco076, 0, 0, false, 20, 13);
			race15AssertUtil.assertPilot(sco081, 0, 0, false, 20, 13);
			race15AssertUtil.assertPilot(sco117, 0, 0, false, 20, 13);
			race15AssertUtil.assertPilot(sco156, 0, 0, false, 20, 13);
			race15AssertUtil.assertPilot(sco320, 0, 0, false, 20, 13);
			race15AssertUtil.assertPilot(sco561, 0, 0, false, 20, 13);
			race15AssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(ir181, 0, 0, 1);
			overallAssertUtil.assertPilot(sco808, 0, 2, 2);
			overallAssertUtil.assertPilot(sco200, 0, 3, 3);
			overallAssertUtil.assertPilot(sco159, 1, 5, 4);
			overallAssertUtil.assertPilot(sco179, 0, 5, 5);
			overallAssertUtil.assertPilot(sco116, 0, 6, 6);
			overallAssertUtil.assertPilot(sco528, 0, 7, 7);
			overallAssertUtil.assertPilot(sco666, 0, 8, 8);
			overallAssertUtil.assertPilot(sco315, 0, 9, 9);
			overallAssertUtil.assertPilot(sco060, 0, 10, 10);
			overallAssertUtil.assertPilot(sco777, 0, 11, 11);
			overallAssertUtil.assertPilot(sco153, 0, 12, 12);
			overallAssertUtil.assertPilot(sco018, 0, 20, 13);
			overallAssertUtil.assertPilot(sco076, 0, 20, 13);
			overallAssertUtil.assertPilot(sco081, 0, 20, 13);
			overallAssertUtil.assertPilot(sco117, 0, 20, 13);
			overallAssertUtil.assertPilot(sco156, 0, 20, 13);
			overallAssertUtil.assertPilot(sco320, 0, 20, 13);
			overallAssertUtil.assertPilot(sco561, 0, 20, 13);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}
