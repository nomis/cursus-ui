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
 * Scores at the end of event 5 (11/05/2013 to 12/05/2013)
 */
public class Series2012Event5Scores extends Series2012Event4Scores {
	@Override
	@Before
	public void createData() throws Exception {
		super.createData();
		createEvent5Races();
	}

	@Override
	@Test
	public void checkSeries() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event5 = eventDAO.find(series, EVENT5_NAME);
			Scores scores = scorer.scoreSeries(series, getSeriesResultsPilots(series, event5), Predicates.in(getSeriesResultsPilots(series, event5)));
			checkSeriesAtEvent5(scores);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkSeriesAtEvent5() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Event event4 = eventDAO.find(series, EVENT4_NAME);
			Event event5 = eventDAO.find(series, EVENT5_NAME);

			List<Race> races = new ArrayList<Race>();
			races.addAll(event1.getRaces());
			races.addAll(event2.getRaces());
			races.addAll(event3.getRaces());
			races.addAll(event4.getRaces());
			races.addAll(event5.getRaces());

			Scores scores = scorer.scoreRaces(races, getSeriesResultsPilots(series, event5), getSeriesResultsEvents(series, event5),
					Predicates.in(getSeriesResultsPilots(series, event5)));
			checkSeriesAtEvent5(scores);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	private void checkSeriesAtEvent5(Scores scores) throws Exception {
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
		Event event4 = eventDAO.find(series, EVENT4_NAME);
		Race race16 = raceDAO.find(event4, RACE16_NAME);
		Race race17 = raceDAO.find(event4, RACE17_NAME);
		Event event5 = eventDAO.find(series, EVENT5_NAME);
		Race race18 = raceDAO.find(event5, RACE18_NAME);
		Race race19 = raceDAO.find(event5, RACE19_NAME);
		Race race20 = raceDAO.find(event5, RACE20_NAME);
		Race race21 = raceDAO.find(event5, RACE21_NAME);
		Race race22 = raceDAO.find(event5, RACE22_NAME);
		Race race23 = raceDAO.find(event5, RACE23_NAME);

		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getPilots().size());
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
		Assert.assertEquals(RACE16_PILOTS, scores.getFleetSize(race16));
		Assert.assertEquals(RACE17_PILOTS, scores.getFleetSize(race17));
		Assert.assertEquals(RACE18_PILOTS, scores.getFleetSize(race18));
		Assert.assertEquals(RACE19_PILOTS, scores.getFleetSize(race19));
		Assert.assertEquals(RACE20_PILOTS, scores.getFleetSize(race20));
		Assert.assertEquals(RACE21_PILOTS, scores.getFleetSize(race21));
		Assert.assertEquals(RACE22_PILOTS, scores.getFleetSize(race22));
		Assert.assertEquals(RACE23_PILOTS, scores.getFleetSize(race23));

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

		RaceAssertUtil race16AssertUtil = new RaceAssertUtil(scores, race16);
		race16AssertUtil.assertPilot(sco116, 6, 0, false, 0, 1);
		race16AssertUtil.assertPilot(sco159, 5, 0, false, 2, 2);
		race16AssertUtil.assertPilot(sco200, 5, 0, false, 3, 3);
		race16AssertUtil.assertPilot(sco179, 5, 0, false, 4, 4);
		race16AssertUtil.assertPilot(sco808, 5, 0, false, 5, 5);
		race16AssertUtil.assertPilot(sco528, 4, 0, false, 6, 6);
		race16AssertUtil.assertPilot(sco018, 4, 0, false, 7, 7);
		race16AssertUtil.assertPilot(sco315, 4, 0, false, 8, 8);
		race16AssertUtil.assertPilot(sco060, 3, 0, false, 9, 9);
		race16AssertUtil.assertPilot(sco117, 3, 0, false, 10, 10);
		race16AssertUtil.assertPilot(sco777, 3, 0, false, 11, 11);
		race16AssertUtil.assertPilot(sco076, 2, 0, false, 12, 12);
		race16AssertUtil.assertPilot(sco156, 2, 0, false, 13, 13);
		race16AssertUtil.assertPilot(sco153, 2, 0, false, 14, 14);
		race16AssertUtil.assertPilot(sco081, 0, 0, false, 17, 15);
		race16AssertUtil.assertPilot(sco561, 0, 0, false, 17, 15);
		race16AssertUtil.assertPilot(sco087, 0, 0, false, 23, 17);
		race16AssertUtil.assertPilot(sco100, 0, 0, false, 23, 17);
		race16AssertUtil.assertPilot(sco158, 0, 0, false, 23, 17);
		race16AssertUtil.assertPilot(sco320, 0, 0, false, 23, 17);
		race16AssertUtil.assertPilot(sco467, 0, 0, false, 23, 17);
		race16AssertUtil.assertPilot(sco666, 0, 0, false, 23, 17);
		race16AssertUtil.assertDone(0);

		RaceAssertUtil race17AssertUtil = new RaceAssertUtil(scores, race17);
		race17AssertUtil.assertPilot(sco159, 4, 0, false, 0, 1);
		race17AssertUtil.assertPilot(sco200, 4, 0, false, 2, 2);
		race17AssertUtil.assertPilot(sco179, 4, 0, false, 3, 3);
		race17AssertUtil.assertPilot(sco116, 4, 0, false, 4, 4);
		race17AssertUtil.assertPilot(sco808, 3, 0, false, 5, 5);
		race17AssertUtil.assertPilot(sco528, 3, 0, false, 6, 6);
		race17AssertUtil.assertPilot(sco081, 3, 0, false, 7, 7);
		race17AssertUtil.assertPilot(sco060, 3, 0, false, 8, 8);
		race17AssertUtil.assertPilot(sco117, 2, 0, false, 9, 9);
		race17AssertUtil.assertPilot(sco018, 2, 0, false, 10, 10);
		race17AssertUtil.assertPilot(sco156, 2, 0, false, 11, 11);
		race17AssertUtil.assertPilot(sco315, 2, 0, false, 12, 12);
		race17AssertUtil.assertPilot(sco777, 1, 0, false, 13, 13);
		race17AssertUtil.assertPilot(sco076, 0, 0, false, 17, 14);
		race17AssertUtil.assertPilot(sco153, 0, 0, false, 17, 14);
		race17AssertUtil.assertPilot(sco561, 0, 0, false, 17, 14);
		race17AssertUtil.assertPilot(sco087, 0, 0, false, 23, 17);
		race17AssertUtil.assertPilot(sco100, 0, 0, false, 23, 17);
		race17AssertUtil.assertPilot(sco158, 0, 0, false, 23, 17);
		race17AssertUtil.assertPilot(sco320, 0, 0, false, 23, 17);
		race17AssertUtil.assertPilot(sco467, 0, 0, false, 23, 17);
		race17AssertUtil.assertPilot(sco666, 0, 0, false, 23, 17);
		race17AssertUtil.assertDone(0);

		RaceAssertUtil race18AssertUtil = new RaceAssertUtil(scores, race18);
		race18AssertUtil.assertPilot(sco200, 7, 0, false, 0, 1);
		race18AssertUtil.assertPilot(sco116, 7, 0, false, 2, 2);
		race18AssertUtil.assertPilot(sco666, 7, 0, false, 3, 3);
		race18AssertUtil.assertPilot(sco808, 7, 0, false, 4, 4);
		race18AssertUtil.assertPilot(sco159, 6, 0, false, 5, 5);
		race18AssertUtil.assertPilot(sco528, 6, 0, false, 6, 6);
		race18AssertUtil.assertPilot(sco018, 6, 0, false, 7, 7);
		race18AssertUtil.assertPilot(sco076, 5, 0, false, 8, 8);
		race18AssertUtil.assertPilot(sco179, 5, 0, false, 9, 9);
		race18AssertUtil.assertPilot(sco060, 4, 0, false, 10, 10);
		race18AssertUtil.assertPilot(sco117, 4, 0, false, 11, 11);
		race18AssertUtil.assertPilot(sco100, 4, 0, false, 12, 12);
		race18AssertUtil.assertPilot(sco315, 4, 0, false, 13, 13);
		race18AssertUtil.assertPilot(sco156, 3, 0, false, 14, 14);
		race18AssertUtil.assertPilot(sco153, 2, 0, false, 15, 15);
		race18AssertUtil.assertPilot(sco561, 0, 0, false, 18, 16);
		race18AssertUtil.assertPilot(sco777, 0, 0, false, 18, 16);
		race18AssertUtil.assertPilot(sco081, 0, 0, false, 23, 18);
		race18AssertUtil.assertPilot(sco087, 0, 0, false, 23, 18);
		race18AssertUtil.assertPilot(sco158, 0, 0, false, 23, 18);
		race18AssertUtil.assertPilot(sco320, 0, 0, false, 23, 18);
		race18AssertUtil.assertPilot(sco467, 0, 0, false, 23, 18);
		race18AssertUtil.assertDone(0);

		RaceAssertUtil race19AssertUtil = new RaceAssertUtil(scores, race19);
		race19AssertUtil.assertPilot(sco116, 7, 0, false, 0, 1);
		race19AssertUtil.assertPilot(sco808, 7, 0, false, 2, 2);
		race19AssertUtil.assertPilot(sco528, 7, 0, false, 3, 3);
		race19AssertUtil.assertPilot(sco666, 7, 0, false, 4, 4);
		race19AssertUtil.assertPilot(sco159, 7, 0, false, 5, 5);
		race19AssertUtil.assertPilot(sco018, 6, 0, false, 6, 6);
		race19AssertUtil.assertPilot(sco076, 6, 0, false, 7, 7);
		race19AssertUtil.assertPilot(sco100, 5, 0, false, 8, 8);
		race19AssertUtil.assertPilot(sco156, 5, 0, false, 9, 9);
		race19AssertUtil.assertPilot(sco060, 4, 0, false, 10, 10);
		race19AssertUtil.assertPilot(sco200, 3, 0, false, 11, 11);
		race19AssertUtil.assertPilot(sco179, 3, 0, false, 12, 12);
		race19AssertUtil.assertPilot(sco315, 3, 0, false, 13, 13);
		race19AssertUtil.assertPilot(sco153, 3, 0, false, 14, 14);
		race19AssertUtil.assertPilot(sco117, 2, 0, false, 15, 15);
		race19AssertUtil.assertPilot(sco561, 0, 0, false, 18, 16);
		race19AssertUtil.assertPilot(sco777, 0, 0, false, 18, 16);
		race19AssertUtil.assertPilot(sco081, 0, 0, false, 23, 18);
		race19AssertUtil.assertPilot(sco087, 0, 0, false, 23, 18);
		race19AssertUtil.assertPilot(sco158, 0, 0, false, 23, 18);
		race19AssertUtil.assertPilot(sco320, 0, 0, false, 23, 18);
		race19AssertUtil.assertPilot(sco467, 0, 0, false, 23, 18);
		race19AssertUtil.assertDone(0);

		RaceAssertUtil race20AssertUtil = new RaceAssertUtil(scores, race20);
		race20AssertUtil.assertPilot(sco200, 5, 0, false, 0, 1);
		race20AssertUtil.assertPilot(sco116, 5, 0, false, 2, 2);
		race20AssertUtil.assertPilot(sco808, 5, 0, false, 3, 3);
		race20AssertUtil.assertPilot(sco528, 4, 0, false, 4, 4);
		race20AssertUtil.assertPilot(sco179, 4, 0, false, 5, 5);
		race20AssertUtil.assertPilot(sco159, 4, 0, false, 6, 6);
		race20AssertUtil.assertPilot(sco018, 4, 0, false, 7, 7);
		race20AssertUtil.assertPilot(sco100, 3, 0, false, 8, 8);
		race20AssertUtil.assertPilot(sco076, 3, 0, false, 9, 9);
		race20AssertUtil.assertPilot(sco666, 3, 0, false, 10, 10);
		race20AssertUtil.assertPilot(sco117, 3, 0, false, 11, 11);
		race20AssertUtil.assertPilot(sco060, 2, 0, false, 12, 12);
		race20AssertUtil.assertPilot(sco315, 1, 0, false, 13, 13);
		race20AssertUtil.assertPilot(sco156, 1, 0, false, 14, 14);
		race20AssertUtil.assertPilot(sco153, 1, 0, false, 15, 15);
		race20AssertUtil.assertPilot(sco561, 0, 0, false, 18, 16);
		race20AssertUtil.assertPilot(sco777, 0, 0, false, 18, 16);
		race20AssertUtil.assertPilot(sco081, 0, 0, false, 23, 18);
		race20AssertUtil.assertPilot(sco087, 0, 0, false, 23, 18);
		race20AssertUtil.assertPilot(sco158, 0, 0, false, 23, 18);
		race20AssertUtil.assertPilot(sco320, 0, 0, false, 23, 18);
		race20AssertUtil.assertPilot(sco467, 0, 0, false, 23, 18);
		race20AssertUtil.assertDone(0);

		RaceAssertUtil race21AssertUtil = new RaceAssertUtil(scores, race21);
		race21AssertUtil.assertPilot(sco200, 6, 0, false, 0, 1);
		race21AssertUtil.assertPilot(sco179, 6, 1, false, 2, 2);
		race21AssertUtil.assertPilot(sco116, 6, 0, false, 3, 3);
		race21AssertUtil.assertPilot(sco159, 6, 0, false, 4, 4);
		race21AssertUtil.assertPilot(sco528, 5, 0, false, 5, 5);
		race21AssertUtil.assertPilot(sco808, 4, 0, false, 6, 6);
		race21AssertUtil.assertPilot(sco018, 4, 0, false, 7, 7);
		race21AssertUtil.assertPilot(sco666, 4, 0, false, 8, 8);
		race21AssertUtil.assertPilot(sco076, 4, 0, false, 9, 9);
		race21AssertUtil.assertPilot(sco117, 4, 0, false, 10, 10);
		race21AssertUtil.assertPilot(sco156, 3, 0, false, 11, 11);
		race21AssertUtil.assertPilot(sco060, 3, 0, false, 12, 12);
		race21AssertUtil.assertPilot(sco315, 2, 0, false, 13, 13);
		race21AssertUtil.assertPilot(sco153, 2, 0, false, 14, 14);
		race21AssertUtil.assertPilot(sco777, 1, 0, false, 15, 15);
		race21AssertUtil.assertPilot(sco100, 0, 0, false, 18, 16);
		race21AssertUtil.assertPilot(sco561, 0, 0, false, 18, 16);
		race21AssertUtil.assertPilot(sco081, 0, 0, false, 23, 18);
		race21AssertUtil.assertPilot(sco087, 0, 0, false, 23, 18);
		race21AssertUtil.assertPilot(sco158, 0, 0, false, 23, 18);
		race21AssertUtil.assertPilot(sco320, 0, 0, false, 23, 18);
		race21AssertUtil.assertPilot(sco467, 0, 0, false, 23, 18);
		race21AssertUtil.assertDone(0);

		RaceAssertUtil race22AssertUtil = new RaceAssertUtil(scores, race22);
		race22AssertUtil.assertPilot(sco179, 9, 0, false, 0, 1);
		race22AssertUtil.assertPilot(sco116, 9, 0, false, 2, 2);
		race22AssertUtil.assertPilot(sco808, 9, 0, false, 3, 3);
		race22AssertUtil.assertPilot(sco159, 9, 0, false, 4, 4);
		race22AssertUtil.assertPilot(sco060, 6, 0, false, 5, 5);
		race22AssertUtil.assertPilot(sco076, 6, 0, false, 6, 6);
		race22AssertUtil.assertPilot(sco777, 5, 0, false, 7, 7);
		race22AssertUtil.assertPilot(sco153, 4, 0, false, 8, 8);
		race22AssertUtil.assertPilot(sco666, 3, 0, false, 9, 9);
		race22AssertUtil.assertPilot(sco200, 3, 0, false, 10, 10);
		race22AssertUtil.assertPilot(sco315, 2, 0, false, 11, 11);
		race22AssertUtil.assertPilot(sco117, 1, 0, false, 12, 12);
		race22AssertUtil.assertPilot(sco018, 0, 0, false, 18, 13);
		race22AssertUtil.assertPilot(sco100, 0, 0, false, 18, 13);
		race22AssertUtil.assertPilot(sco156, 0, 0, false, 18, 13);
		race22AssertUtil.assertPilot(sco528, 0, 0, false, 18, 13);
		race22AssertUtil.assertPilot(sco561, 0, 0, false, 18, 13);
		race22AssertUtil.assertPilot(sco081, 0, 0, false, 23, 18);
		race22AssertUtil.assertPilot(sco087, 0, 0, false, 23, 18);
		race22AssertUtil.assertPilot(sco158, 0, 0, false, 23, 18);
		race22AssertUtil.assertPilot(sco320, 0, 0, false, 23, 18);
		race22AssertUtil.assertPilot(sco467, 0, 0, false, 23, 18);
		race22AssertUtil.assertDone(0);

		RaceAssertUtil race23AssertUtil = new RaceAssertUtil(scores, race23);
		race23AssertUtil.assertPilot(sco179, 7, 0, false, 0, 1);
		race23AssertUtil.assertPilot(sco808, 7, 0, false, 2, 2);
		race23AssertUtil.assertPilot(sco116, 7, 0, false, 3, 3);
		race23AssertUtil.assertPilot(sco200, 7, 0, false, 4, 4);
		race23AssertUtil.assertPilot(sco666, 5, 0, false, 5, 5);
		race23AssertUtil.assertPilot(sco159, 5, 0, false, 6, 6);
		race23AssertUtil.assertPilot(sco076, 5, 0, false, 7, 7);
		race23AssertUtil.assertPilot(sco018, 5, 0, false, 8, 8);
		race23AssertUtil.assertPilot(sco060, 3, 0, false, 9, 9);
		race23AssertUtil.assertPilot(sco777, 3, 0, false, 10, 10);
		race23AssertUtil.assertPilot(sco156, 3, 0, false, 11, 11);
		race23AssertUtil.assertPilot(sco153, 3, 1, false, 12, 12);
		race23AssertUtil.assertPilot(sco315, 2, 0, false, 13, 13);
		race23AssertUtil.assertPilot(sco100, 0, 0, false, 18, 14);
		race23AssertUtil.assertPilot(sco117, 0, 0, false, 18, 14);
		race23AssertUtil.assertPilot(sco528, 0, 0, false, 18, 14);
		race23AssertUtil.assertPilot(sco561, 0, 0, false, 18, 14);
		race23AssertUtil.assertPilot(sco081, 0, 0, false, 23, 18);
		race23AssertUtil.assertPilot(sco087, 0, 0, false, 23, 18);
		race23AssertUtil.assertPilot(sco158, 0, 0, false, 23, 18);
		race23AssertUtil.assertPilot(sco320, 0, 0, false, 23, 18);
		race23AssertUtil.assertPilot(sco467, 0, 0, false, 23, 18);
		race23AssertUtil.assertDone(0);

		OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
		overallAssertUtil.assertPilot(sco200, 1, 19, 1, 11, 10, 6, 4, 3);
		overallAssertUtil.assertPilot(sco179, 1, 38, 2, 12, 9, 8, 7, 5);
		overallAssertUtil.assertPilot(sco116, 0, 46, 3, 6, 5, 5, 5, 4);
		overallAssertUtil.assertPilot(sco808, 3, 48, 4, 8, 7, 6, 6, 6);
		overallAssertUtil.assertPilot(sco159, 2, 76, 5, 8, 7, 7, 6, 6);
		overallAssertUtil.assertPilot(sco528, 0, 86, 6, 18, 18, 17, 10, 10);
		overallAssertUtil.assertPilot(sco666, 1, 117, 7, 23, 23, 17, 17, 10);
		overallAssertUtil.assertPilot(sco060, 0, 160, 8, 13, 13, 12, 12, 12);
		overallAssertUtil.assertPilot(sco018, 0, 189, 9, 19, 19, 18, 17, 17);
		overallAssertUtil.assertPilot(sco315, 0, 193, 10, 17, 17, 17, 17, 13);
		overallAssertUtil.assertPilot(sco117, 0, 211, 11, 23, 23, 23, 23, 23);
		overallAssertUtil.assertPilot(sco076, 0, 225, 12, 19, 19, 19, 17, 17);
		overallAssertUtil.assertPilot(sco081, 0, 248, 13, 23, 23, 23, 23, 23);
		overallAssertUtil.assertPilot(sco777, 0, 248, 14, 23, 23, 23, 23, 23);
		overallAssertUtil.assertPilot(sco153, 1, 267, 15, 19, 17, 17, 17, 17);
		overallAssertUtil.assertPilot(sco100, 0, 279, 16, 23, 23, 23, 23, 23);
		overallAssertUtil.assertPilot(sco156, 0, 283, 17, 23, 23, 23, 23, 23);
		overallAssertUtil.assertPilot(sco087, 0, 335, 18, 23, 23, 23, 23, 23);
		overallAssertUtil.assertPilot(sco561, 0, 354, 19, 23, 23, 23, 23, 23);
		overallAssertUtil.assertPilot(sco158, 0, 357, 20, 23, 23, 23, 23, 23);
		overallAssertUtil.assertPilot(sco320, 0, 395, 21, 23, 23, 23, 23, 23);
		overallAssertUtil.assertPilot(sco467, 0, 414, 22, 23, 23, 23, 23, 23);
		overallAssertUtil.assertOrder();
	}

	@Test
	public final void checkEvent5() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event5 = eventDAO.find(series, EVENT5_NAME);
			Race race18 = raceDAO.find(event5, RACE18_NAME);
			Race race19 = raceDAO.find(event5, RACE19_NAME);
			Race race20 = raceDAO.find(event5, RACE20_NAME);
			Race race21 = raceDAO.find(event5, RACE21_NAME);
			Race race22 = raceDAO.find(event5, RACE22_NAME);
			Race race23 = raceDAO.find(event5, RACE23_NAME);

			Scores scores = scorer.scoreEvent(event5, Predicates.in(getEventResultsPilots(series, event5)));
			Assert.assertEquals(EVENT5_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race18));
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race19));
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race20));
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race21));
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race22));
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race23));

			RaceAssertUtil race18AssertUtil = new RaceAssertUtil(scores, race18);
			race18AssertUtil.assertPilot(ir181, 7, 0, false, 0, 1);
			race18AssertUtil.assertPilot(sco200, 7, 0, false, 2, 2);
			race18AssertUtil.assertPilot(sco116, 7, 0, false, 3, 3);
			race18AssertUtil.assertPilot(sco666, 7, 0, false, 4, 4);
			race18AssertUtil.assertPilot(sco808, 7, 0, false, 5, 5);
			race18AssertUtil.assertPilot(sco159, 6, 0, false, 6, 6);
			race18AssertUtil.assertPilot(sco528, 6, 0, false, 7, 7);
			race18AssertUtil.assertPilot(sco018, 6, 0, false, 8, 8);
			race18AssertUtil.assertPilot(sco076, 5, 0, false, 9, 9);
			race18AssertUtil.assertPilot(sco179, 5, 0, false, 10, 10);
			race18AssertUtil.assertPilot(sco060, 4, 0, false, 11, 11);
			race18AssertUtil.assertPilot(sco117, 4, 0, false, 12, 12);
			race18AssertUtil.assertPilot(sco100, 4, 0, false, 13, 13);
			race18AssertUtil.assertPilot(sco315, 4, 0, false, 14, 14);
			race18AssertUtil.assertPilot(sco156, 3, 0, false, 15, 15);
			race18AssertUtil.assertPilot(sco153, 2, 0, false, 16, 16);
			race18AssertUtil.assertPilot(sco561, 0, 0, false, 19, 17);
			race18AssertUtil.assertPilot(sco777, 0, 0, false, 19, 17);
			race18AssertUtil.assertDone(0);

			RaceAssertUtil race19AssertUtil = new RaceAssertUtil(scores, race19);
			race19AssertUtil.assertPilot(ir181, 7, 0, false, 0, 1);
			race19AssertUtil.assertPilot(sco116, 7, 0, false, 2, 2);
			race19AssertUtil.assertPilot(sco808, 7, 0, false, 3, 3);
			race19AssertUtil.assertPilot(sco528, 7, 0, false, 4, 4);
			race19AssertUtil.assertPilot(sco666, 7, 0, false, 5, 5);
			race19AssertUtil.assertPilot(sco159, 7, 0, false, 6, 6);
			race19AssertUtil.assertPilot(sco018, 6, 0, false, 7, 7);
			race19AssertUtil.assertPilot(sco076, 6, 0, false, 8, 8);
			race19AssertUtil.assertPilot(sco100, 5, 0, false, 9, 9);
			race19AssertUtil.assertPilot(sco156, 5, 0, false, 10, 10);
			race19AssertUtil.assertPilot(sco060, 4, 0, false, 11, 11);
			race19AssertUtil.assertPilot(sco200, 3, 0, false, 12, 12);
			race19AssertUtil.assertPilot(sco179, 3, 0, false, 13, 13);
			race19AssertUtil.assertPilot(sco315, 3, 0, false, 14, 14);
			race19AssertUtil.assertPilot(sco153, 3, 0, false, 15, 15);
			race19AssertUtil.assertPilot(sco117, 2, 0, false, 16, 16);
			race19AssertUtil.assertPilot(sco561, 0, 0, false, 19, 17);
			race19AssertUtil.assertPilot(sco777, 0, 0, false, 19, 17);
			race19AssertUtil.assertDone(0);

			RaceAssertUtil race20AssertUtil = new RaceAssertUtil(scores, race20);
			race20AssertUtil.assertPilot(ir181, 5, 0, false, 0, 1);
			race20AssertUtil.assertPilot(sco200, 5, 0, false, 2, 2);
			race20AssertUtil.assertPilot(sco116, 5, 0, false, 3, 3);
			race20AssertUtil.assertPilot(sco808, 5, 0, false, 4, 4);
			race20AssertUtil.assertPilot(sco528, 4, 0, false, 5, 5);
			race20AssertUtil.assertPilot(sco179, 4, 0, false, 6, 6);
			race20AssertUtil.assertPilot(sco159, 4, 0, false, 7, 7);
			race20AssertUtil.assertPilot(sco018, 4, 0, false, 8, 8);
			race20AssertUtil.assertPilot(sco100, 3, 0, false, 9, 9);
			race20AssertUtil.assertPilot(sco076, 3, 0, false, 10, 10);
			race20AssertUtil.assertPilot(sco666, 3, 0, false, 11, 11);
			race20AssertUtil.assertPilot(sco117, 3, 0, false, 12, 12);
			race20AssertUtil.assertPilot(sco060, 2, 0, false, 13, 13);
			race20AssertUtil.assertPilot(sco315, 1, 0, false, 14, 14);
			race20AssertUtil.assertPilot(sco156, 1, 0, false, 15, 15);
			race20AssertUtil.assertPilot(sco153, 1, 0, false, 16, 16);
			race20AssertUtil.assertPilot(sco561, 0, 0, false, 19, 17);
			race20AssertUtil.assertPilot(sco777, 0, 0, false, 19, 17);
			race20AssertUtil.assertDone(0);

			RaceAssertUtil race21AssertUtil = new RaceAssertUtil(scores, race21);
			race21AssertUtil.assertPilot(sco200, 6, 0, false, 0, 1);
			race21AssertUtil.assertPilot(sco179, 6, 1, false, 2, 2);
			race21AssertUtil.assertPilot(sco116, 6, 0, false, 3, 3);
			race21AssertUtil.assertPilot(sco159, 6, 0, false, 4, 4);
			race21AssertUtil.assertPilot(sco528, 5, 0, false, 5, 5);
			race21AssertUtil.assertPilot(ir181, 4, 0, false, 6, 6);
			race21AssertUtil.assertPilot(sco808, 4, 0, false, 7, 7);
			race21AssertUtil.assertPilot(sco018, 4, 0, false, 8, 8);
			race21AssertUtil.assertPilot(sco666, 4, 0, false, 9, 9);
			race21AssertUtil.assertPilot(sco076, 4, 0, false, 10, 10);
			race21AssertUtil.assertPilot(sco117, 4, 0, false, 11, 11);
			race21AssertUtil.assertPilot(sco156, 3, 0, false, 12, 12);
			race21AssertUtil.assertPilot(sco060, 3, 0, false, 13, 13);
			race21AssertUtil.assertPilot(sco315, 2, 0, false, 14, 14);
			race21AssertUtil.assertPilot(sco153, 2, 0, false, 15, 15);
			race21AssertUtil.assertPilot(sco777, 1, 0, false, 16, 16);
			race21AssertUtil.assertPilot(sco100, 0, 0, false, 19, 17);
			race21AssertUtil.assertPilot(sco561, 0, 0, false, 19, 17);
			race21AssertUtil.assertDone(0);

			RaceAssertUtil race22AssertUtil = new RaceAssertUtil(scores, race22);
			race22AssertUtil.assertPilot(sco179, 9, 0, false, 0, 1);
			race22AssertUtil.assertPilot(sco116, 9, 0, false, 2, 2);
			race22AssertUtil.assertPilot(sco808, 9, 0, false, 3, 3);
			race22AssertUtil.assertPilot(sco159, 9, 0, false, 4, 4);
			race22AssertUtil.assertPilot(sco060, 6, 0, false, 5, 5);
			race22AssertUtil.assertPilot(sco076, 6, 0, false, 6, 6);
			race22AssertUtil.assertPilot(ir181, 5, 0, false, 7, 7);
			race22AssertUtil.assertPilot(sco777, 5, 0, false, 8, 8);
			race22AssertUtil.assertPilot(sco153, 4, 0, false, 9, 9);
			race22AssertUtil.assertPilot(sco666, 3, 0, false, 10, 10);
			race22AssertUtil.assertPilot(sco200, 3, 0, false, 11, 11);
			race22AssertUtil.assertPilot(sco315, 2, 0, false, 12, 12);
			race22AssertUtil.assertPilot(sco117, 1, 0, false, 13, 13);
			race22AssertUtil.assertPilot(sco018, 0, 0, false, 19, 14);
			race22AssertUtil.assertPilot(sco100, 0, 0, false, 19, 14);
			race22AssertUtil.assertPilot(sco156, 0, 0, false, 19, 14);
			race22AssertUtil.assertPilot(sco528, 0, 0, false, 19, 14);
			race22AssertUtil.assertPilot(sco561, 0, 0, false, 19, 14);
			race22AssertUtil.assertDone(0);

			RaceAssertUtil race23AssertUtil = new RaceAssertUtil(scores, race23);
			race23AssertUtil.assertPilot(ir181, 7, 0, false, 0, 1);
			race23AssertUtil.assertPilot(sco179, 7, 0, false, 2, 2);
			race23AssertUtil.assertPilot(sco808, 7, 0, false, 3, 3);
			race23AssertUtil.assertPilot(sco116, 7, 0, false, 4, 4);
			race23AssertUtil.assertPilot(sco200, 7, 0, false, 5, 5);
			race23AssertUtil.assertPilot(sco666, 5, 0, false, 6, 6);
			race23AssertUtil.assertPilot(sco159, 5, 0, false, 7, 7);
			race23AssertUtil.assertPilot(sco076, 5, 0, false, 8, 8);
			race23AssertUtil.assertPilot(sco018, 5, 0, false, 9, 9);
			race23AssertUtil.assertPilot(sco060, 3, 0, false, 10, 10);
			race23AssertUtil.assertPilot(sco777, 3, 0, false, 11, 11);
			race23AssertUtil.assertPilot(sco156, 3, 0, false, 12, 12);
			race23AssertUtil.assertPilot(sco153, 3, 1, false, 13, 13);
			race23AssertUtil.assertPilot(sco315, 2, 0, false, 14, 14);
			race23AssertUtil.assertPilot(sco100, 0, 0, false, 19, 15);
			race23AssertUtil.assertPilot(sco117, 0, 0, false, 19, 15);
			race23AssertUtil.assertPilot(sco528, 0, 0, false, 19, 15);
			race23AssertUtil.assertPilot(sco561, 0, 0, false, 19, 15);
			race23AssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(ir181, 0, 6, 1, 7);
			overallAssertUtil.assertPilot(sco116, 0, 13, 2, 4);
			overallAssertUtil.assertPilot(sco808, 0, 18, 3, 7);
			overallAssertUtil.assertPilot(sco200, 0, 20, 4, 12);
			overallAssertUtil.assertPilot(sco179, 1, 21, 5, 13);
			overallAssertUtil.assertPilot(sco159, 0, 27, 6, 7);
			overallAssertUtil.assertPilot(sco666, 0, 34, 7, 11);
			overallAssertUtil.assertPilot(sco528, 0, 40, 8, 19);
			overallAssertUtil.assertPilot(sco018, 0, 40, 9, 19);
			overallAssertUtil.assertPilot(sco076, 0, 41, 10, 10);
			overallAssertUtil.assertPilot(sco060, 0, 50, 11, 13);
			overallAssertUtil.assertPilot(sco156, 0, 64, 12, 19);
			overallAssertUtil.assertPilot(sco117, 0, 64, 13, 19);
			overallAssertUtil.assertPilot(sco315, 0, 68, 14, 14);
			overallAssertUtil.assertPilot(sco100, 0, 69, 15, 19);
			overallAssertUtil.assertPilot(sco153, 1, 69, 16, 16);
			overallAssertUtil.assertPilot(sco777, 0, 73, 17, 19);
			overallAssertUtil.assertPilot(sco561, 0, 95, 18, 19);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace18() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event5 = eventDAO.find(series, EVENT5_NAME);
			Race race18 = raceDAO.find(event5, RACE18_NAME);

			Scores scores = scorer.scoreRace(race18, Predicates.in(getEventResultsPilots(series, event5)));
			Assert.assertEquals(EVENT5_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race18));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race18);
			raceAssertUtil.assertPilot(ir181, 7, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco200, 7, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco116, 7, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco666, 7, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco808, 7, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco159, 6, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco528, 6, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco018, 6, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco076, 5, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco179, 5, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco060, 4, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco117, 4, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco100, 4, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco315, 4, 0, false, 14, 14);
			raceAssertUtil.assertPilot(sco156, 3, 0, false, 15, 15);
			raceAssertUtil.assertPilot(sco153, 2, 0, false, 16, 16);
			raceAssertUtil.assertPilot(sco561, 0, 0, false, 19, 17);
			raceAssertUtil.assertPilot(sco777, 0, 0, false, 19, 17);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(ir181, 0, 0, 1);
			overallAssertUtil.assertPilot(sco200, 0, 2, 2);
			overallAssertUtil.assertPilot(sco116, 0, 3, 3);
			overallAssertUtil.assertPilot(sco666, 0, 4, 4);
			overallAssertUtil.assertPilot(sco808, 0, 5, 5);
			overallAssertUtil.assertPilot(sco159, 0, 6, 6);
			overallAssertUtil.assertPilot(sco528, 0, 7, 7);
			overallAssertUtil.assertPilot(sco018, 0, 8, 8);
			overallAssertUtil.assertPilot(sco076, 0, 9, 9);
			overallAssertUtil.assertPilot(sco179, 0, 10, 10);
			overallAssertUtil.assertPilot(sco060, 0, 11, 11);
			overallAssertUtil.assertPilot(sco117, 0, 12, 12);
			overallAssertUtil.assertPilot(sco100, 0, 13, 13);
			overallAssertUtil.assertPilot(sco315, 0, 14, 14);
			overallAssertUtil.assertPilot(sco156, 0, 15, 15);
			overallAssertUtil.assertPilot(sco153, 0, 16, 16);
			overallAssertUtil.assertPilot(sco561, 0, 19, 17);
			overallAssertUtil.assertPilot(sco777, 0, 19, 17);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace19() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event5 = eventDAO.find(series, EVENT5_NAME);
			Race race19 = raceDAO.find(event5, RACE19_NAME);

			Scores scores = scorer.scoreRace(race19, Predicates.in(getEventResultsPilots(series, event5)));
			Assert.assertEquals(EVENT5_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race19));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race19);
			raceAssertUtil.assertPilot(ir181, 7, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco116, 7, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco808, 7, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco528, 7, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco666, 7, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco159, 7, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco018, 6, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco076, 6, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco100, 5, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco156, 5, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco060, 4, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco200, 3, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco179, 3, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco315, 3, 0, false, 14, 14);
			raceAssertUtil.assertPilot(sco153, 3, 0, false, 15, 15);
			raceAssertUtil.assertPilot(sco117, 2, 0, false, 16, 16);
			raceAssertUtil.assertPilot(sco561, 0, 0, false, 19, 17);
			raceAssertUtil.assertPilot(sco777, 0, 0, false, 19, 17);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(ir181, 0, 0, 1);
			overallAssertUtil.assertPilot(sco116, 0, 2, 2);
			overallAssertUtil.assertPilot(sco808, 0, 3, 3);
			overallAssertUtil.assertPilot(sco528, 0, 4, 4);
			overallAssertUtil.assertPilot(sco666, 0, 5, 5);
			overallAssertUtil.assertPilot(sco159, 0, 6, 6);
			overallAssertUtil.assertPilot(sco018, 0, 7, 7);
			overallAssertUtil.assertPilot(sco076, 0, 8, 8);
			overallAssertUtil.assertPilot(sco100, 0, 9, 9);
			overallAssertUtil.assertPilot(sco156, 0, 10, 10);
			overallAssertUtil.assertPilot(sco060, 0, 11, 11);
			overallAssertUtil.assertPilot(sco200, 0, 12, 12);
			overallAssertUtil.assertPilot(sco179, 0, 13, 13);
			overallAssertUtil.assertPilot(sco315, 0, 14, 14);
			overallAssertUtil.assertPilot(sco153, 0, 15, 15);
			overallAssertUtil.assertPilot(sco117, 0, 16, 16);
			overallAssertUtil.assertPilot(sco561, 0, 19, 17);
			overallAssertUtil.assertPilot(sco777, 0, 19, 17);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace20() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event5 = eventDAO.find(series, EVENT5_NAME);
			Race race20 = raceDAO.find(event5, RACE20_NAME);

			Scores scores = scorer.scoreRace(race20, Predicates.in(getEventResultsPilots(series, event5)));
			Assert.assertEquals(EVENT5_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race20));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race20);
			raceAssertUtil.assertPilot(ir181, 5, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco200, 5, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco116, 5, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco808, 5, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco528, 4, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco179, 4, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco159, 4, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco018, 4, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco100, 3, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco076, 3, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco666, 3, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco117, 3, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco060, 2, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco315, 1, 0, false, 14, 14);
			raceAssertUtil.assertPilot(sco156, 1, 0, false, 15, 15);
			raceAssertUtil.assertPilot(sco153, 1, 0, false, 16, 16);
			raceAssertUtil.assertPilot(sco561, 0, 0, false, 19, 17);
			raceAssertUtil.assertPilot(sco777, 0, 0, false, 19, 17);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(ir181, 0, 0, 1);
			overallAssertUtil.assertPilot(sco200, 0, 2, 2);
			overallAssertUtil.assertPilot(sco116, 0, 3, 3);
			overallAssertUtil.assertPilot(sco808, 0, 4, 4);
			overallAssertUtil.assertPilot(sco528, 0, 5, 5);
			overallAssertUtil.assertPilot(sco179, 0, 6, 6);
			overallAssertUtil.assertPilot(sco159, 0, 7, 7);
			overallAssertUtil.assertPilot(sco018, 0, 8, 8);
			overallAssertUtil.assertPilot(sco100, 0, 9, 9);
			overallAssertUtil.assertPilot(sco076, 0, 10, 10);
			overallAssertUtil.assertPilot(sco666, 0, 11, 11);
			overallAssertUtil.assertPilot(sco117, 0, 12, 12);
			overallAssertUtil.assertPilot(sco060, 0, 13, 13);
			overallAssertUtil.assertPilot(sco315, 0, 14, 14);
			overallAssertUtil.assertPilot(sco156, 0, 15, 15);
			overallAssertUtil.assertPilot(sco153, 0, 16, 16);
			overallAssertUtil.assertPilot(sco561, 0, 19, 17);
			overallAssertUtil.assertPilot(sco777, 0, 19, 17);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace21() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event5 = eventDAO.find(series, EVENT5_NAME);
			Race race21 = raceDAO.find(event5, RACE21_NAME);

			Scores scores = scorer.scoreRace(race21, Predicates.in(getEventResultsPilots(series, event5)));
			Assert.assertEquals(EVENT5_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race21));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race21);
			raceAssertUtil.assertPilot(sco200, 6, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco179, 6, 1, false, 2, 2);
			raceAssertUtil.assertPilot(sco116, 6, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco159, 6, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco528, 5, 0, false, 5, 5);
			raceAssertUtil.assertPilot(ir181, 4, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco808, 4, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco018, 4, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco666, 4, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco076, 4, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco117, 4, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco156, 3, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco060, 3, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco315, 2, 0, false, 14, 14);
			raceAssertUtil.assertPilot(sco153, 2, 0, false, 15, 15);
			raceAssertUtil.assertPilot(sco777, 1, 0, false, 16, 16);
			raceAssertUtil.assertPilot(sco100, 0, 0, false, 19, 17);
			raceAssertUtil.assertPilot(sco561, 0, 0, false, 19, 17);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(sco179, 1, 3, 2);
			overallAssertUtil.assertPilot(sco116, 0, 3, 3);
			overallAssertUtil.assertPilot(sco159, 0, 4, 4);
			overallAssertUtil.assertPilot(sco528, 0, 5, 5);
			overallAssertUtil.assertPilot(ir181, 0, 6, 6);
			overallAssertUtil.assertPilot(sco808, 0, 7, 7);
			overallAssertUtil.assertPilot(sco018, 0, 8, 8);
			overallAssertUtil.assertPilot(sco666, 0, 9, 9);
			overallAssertUtil.assertPilot(sco076, 0, 10, 10);
			overallAssertUtil.assertPilot(sco117, 0, 11, 11);
			overallAssertUtil.assertPilot(sco156, 0, 12, 12);
			overallAssertUtil.assertPilot(sco060, 0, 13, 13);
			overallAssertUtil.assertPilot(sco315, 0, 14, 14);
			overallAssertUtil.assertPilot(sco153, 0, 15, 15);
			overallAssertUtil.assertPilot(sco777, 0, 16, 16);
			overallAssertUtil.assertPilot(sco100, 0, 19, 17);
			overallAssertUtil.assertPilot(sco561, 0, 19, 17);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace22() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event5 = eventDAO.find(series, EVENT5_NAME);
			Race race22 = raceDAO.find(event5, RACE22_NAME);

			Scores scores = scorer.scoreRace(race22, Predicates.in(getEventResultsPilots(series, event5)));
			Assert.assertEquals(EVENT5_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race22));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race22);
			raceAssertUtil.assertPilot(sco179, 9, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco116, 9, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco808, 9, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco159, 9, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco060, 6, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco076, 6, 0, false, 6, 6);
			raceAssertUtil.assertPilot(ir181, 5, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco777, 5, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco153, 4, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco666, 3, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco200, 3, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco315, 2, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco117, 1, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco018, 0, 0, false, 19, 14);
			raceAssertUtil.assertPilot(sco100, 0, 0, false, 19, 14);
			raceAssertUtil.assertPilot(sco156, 0, 0, false, 19, 14);
			raceAssertUtil.assertPilot(sco528, 0, 0, false, 19, 14);
			raceAssertUtil.assertPilot(sco561, 0, 0, false, 19, 14);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco179, 0, 0, 1);
			overallAssertUtil.assertPilot(sco116, 0, 2, 2);
			overallAssertUtil.assertPilot(sco808, 0, 3, 3);
			overallAssertUtil.assertPilot(sco159, 0, 4, 4);
			overallAssertUtil.assertPilot(sco060, 0, 5, 5);
			overallAssertUtil.assertPilot(sco076, 0, 6, 6);
			overallAssertUtil.assertPilot(ir181, 0, 7, 7);
			overallAssertUtil.assertPilot(sco777, 0, 8, 8);
			overallAssertUtil.assertPilot(sco153, 0, 9, 9);
			overallAssertUtil.assertPilot(sco666, 0, 10, 10);
			overallAssertUtil.assertPilot(sco200, 0, 11, 11);
			overallAssertUtil.assertPilot(sco315, 0, 12, 12);
			overallAssertUtil.assertPilot(sco117, 0, 13, 13);
			overallAssertUtil.assertPilot(sco018, 0, 19, 14);
			overallAssertUtil.assertPilot(sco100, 0, 19, 14);
			overallAssertUtil.assertPilot(sco156, 0, 19, 14);
			overallAssertUtil.assertPilot(sco528, 0, 19, 14);
			overallAssertUtil.assertPilot(sco561, 0, 19, 14);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace23() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event5 = eventDAO.find(series, EVENT5_NAME);
			Race race23 = raceDAO.find(event5, RACE23_NAME);

			Scores scores = scorer.scoreRace(race23, Predicates.in(getEventResultsPilots(series, event5)));
			Assert.assertEquals(EVENT5_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race23));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race23);
			raceAssertUtil.assertPilot(ir181, 7, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco179, 7, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco808, 7, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco116, 7, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco200, 7, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco666, 5, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco159, 5, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco076, 5, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco018, 5, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco060, 3, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco777, 3, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco156, 3, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco153, 3, 1, false, 13, 13);
			raceAssertUtil.assertPilot(sco315, 2, 0, false, 14, 14);
			raceAssertUtil.assertPilot(sco100, 0, 0, false, 19, 15);
			raceAssertUtil.assertPilot(sco117, 0, 0, false, 19, 15);
			raceAssertUtil.assertPilot(sco528, 0, 0, false, 19, 15);
			raceAssertUtil.assertPilot(sco561, 0, 0, false, 19, 15);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(ir181, 0, 0, 1);
			overallAssertUtil.assertPilot(sco179, 0, 2, 2);
			overallAssertUtil.assertPilot(sco808, 0, 3, 3);
			overallAssertUtil.assertPilot(sco116, 0, 4, 4);
			overallAssertUtil.assertPilot(sco200, 0, 5, 5);
			overallAssertUtil.assertPilot(sco666, 0, 6, 6);
			overallAssertUtil.assertPilot(sco159, 0, 7, 7);
			overallAssertUtil.assertPilot(sco076, 0, 8, 8);
			overallAssertUtil.assertPilot(sco018, 0, 9, 9);
			overallAssertUtil.assertPilot(sco060, 0, 10, 10);
			overallAssertUtil.assertPilot(sco777, 0, 11, 11);
			overallAssertUtil.assertPilot(sco156, 0, 12, 12);
			overallAssertUtil.assertPilot(sco153, 1, 14, 13);
			overallAssertUtil.assertPilot(sco315, 0, 14, 14);
			overallAssertUtil.assertPilot(sco100, 0, 19, 15);
			overallAssertUtil.assertPilot(sco117, 0, 19, 15);
			overallAssertUtil.assertPilot(sco528, 0, 19, 15);
			overallAssertUtil.assertPilot(sco561, 0, 19, 15);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}