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
 * Scores at the end of event 4 (18/06/2011 to 19/06/2011)
 */
public class Series2010Event4Scores extends Series2010Event3Scores {
	@Override
	@Before
	public void createData() throws Exception {
		super.createData();
		createEvent4Races();
	}

	@Override
	@Test
	public void checkSeries() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Scores scores = scorer.scoreSeries(series, Predicates.in(getResultsPilots(series)));
			checkSeriesAtEvent4(scores);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkSeriesAtEvent4() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Event event4 = eventDAO.find(series, EVENT4_NAME);

			List<Race> races = new ArrayList<Race>();
			races.addAll(event1.getRaces());
			races.addAll(event2.getRaces());
			races.addAll(event3.getRaces());
			races.addAll(event4.getRaces());

			Scores scores = scorer.scoreRaces(races, getResultsPilots(series), Predicates.in(getResultsPilots(series)));
			checkSeriesAtEvent4(scores);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	private void checkSeriesAtEvent4(Scores scores) throws Exception {
		Series series = seriesDAO.find(SERIES_NAME);
		Event event1 = eventDAO.find(series, EVENT1_NAME);
		Race race1 = raceDAO.find(event1, RACE1_NAME);
		Race race2 = raceDAO.find(event1, RACE2_NAME);
		Race race3 = raceDAO.find(event1, RACE3_NAME);
		Event event2 = eventDAO.find(series, EVENT2_NAME);
		Race race4 = raceDAO.find(event2, RACE4_NAME);
		Event event3 = eventDAO.find(series, EVENT3_NAME);
		Race race5 = raceDAO.find(event3, RACE5_NAME);
		Event event4 = eventDAO.find(series, EVENT4_NAME);
		Race race6 = raceDAO.find(event4, RACE6_NAME);
		Race race7 = raceDAO.find(event4, RACE7_NAME);
		Race race8 = raceDAO.find(event4, RACE8_NAME);

		Assert.assertEquals(SERIES_FLEET, scores.getPilots().size());
		Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race1));
		Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race2));
		Assert.assertEquals(EVENT1_FLEET, scores.getFleetSize(race3));
		Assert.assertEquals(EVENT2_FLEET, scores.getFleetSize(race4));
		Assert.assertEquals(EVENT3_FLEET, scores.getFleetSize(race5));
		Assert.assertEquals(EVENT4_FLEET, scores.getFleetSize(race6));
		Assert.assertEquals(EVENT4_FLEET, scores.getFleetSize(race7));
		Assert.assertEquals(EVENT4_FLEET, scores.getFleetSize(race8));

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
		race2AssertUtil.assertPilot(sco159, 0, 0, true, 3, 5);
		race2AssertUtil.assertPilot(sco179, 4, 0, false, 4, 6);
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
		race3AssertUtil.assertPilot(sco179, 0, 0, true, 2, 3);
		race3AssertUtil.assertPilot(sco159, 6, 0, false, 3, 4);
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
		race3AssertUtil.assertPilot(sco808, 1, 0, false, 14, 15);
		race3AssertUtil.assertPilot(sco197, 0, 0, true, 15, 16);
		race3AssertUtil.assertPilot(sco136, 0, 0, false, 18, 17);
		race3AssertUtil.assertPilot(sco156, 0, 0, false, 18, 17);
		race3AssertUtil.assertPilot(sco158, 0, 0, false, 18, 17);
		race3AssertUtil.assertDone(2);

		RaceAssertUtil race4AssertUtil = new RaceAssertUtil(scores, race4);
		race4AssertUtil.assertPilot(sco068, 6, 0, false, 0, 1);
		race4AssertUtil.assertPilot(sco200, 6, 0, false, 2, 2);
		race4AssertUtil.assertPilot(sco179, 6, 0, false, 3, 3);
		race4AssertUtil.assertPilot(sco159, 5, 0, false, 4, 4);
		race4AssertUtil.assertPilot(sco808, 5, 0, false, 5, 5);
		race4AssertUtil.assertPilot(sco116, 5, 0, false, 6, 6);
		race4AssertUtil.assertPilot(sco087, 4, 0, false, 7, 7);
		race4AssertUtil.assertPilot(sco019, 0, 0, true, 7, 8);
		race4AssertUtil.assertPilot(sco081, 1, 0, false, 8, 9);
		race4AssertUtil.assertPilot(sco060, 0, 0, true, 10, 10);
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

		RaceAssertUtil race5AssertUtil = new RaceAssertUtil(scores, race5);
		race5AssertUtil.assertPilot(sco200, 6, 0, false, 0, 1);
		race5AssertUtil.assertPilot(sco159, 6, 0, false, 2, 2);
		race5AssertUtil.assertPilot(sco179, 0, 0, true, 2, 3);
		race5AssertUtil.assertPilot(sco068, 6, 0, false, 3, 4);
		race5AssertUtil.assertPilot(sco248, 5, 0, false, 4, 5);
		race5AssertUtil.assertPilot(sco081, 0, 0, true, 4, 6);
		race5AssertUtil.assertPilot(sco019, 4, 0, false, 5, 7);
		race5AssertUtil.assertPilot(sco018, 4, 0, false, 6, 8);
		race5AssertUtil.assertPilot(sco158, 3, 0, false, 7, 9);
		race5AssertUtil.assertPilot(sco060, 3, 0, false, 8, 10);
		race5AssertUtil.assertPilot(sco087, 3, 0, false, 9, 11);
		race5AssertUtil.assertPilot(sco116, 3, 0, false, 10, 12);
		race5AssertUtil.assertPilot(sco249, 3, 0, false, 11, 13);
		race5AssertUtil.assertPilot(sco042, 0, 0, false, 16, 14);
		race5AssertUtil.assertPilot(sco136, 0, 0, false, 16, 14);
		race5AssertUtil.assertPilot(sco153, 0, 0, false, 16, 14);
		race5AssertUtil.assertPilot(sco156, 0, 0, false, 16, 14);
		race5AssertUtil.assertPilot(sco197, 0, 0, false, 16, 14);
		race5AssertUtil.assertPilot(sco808, 0, 0, false, 16, 14);
		race5AssertUtil.assertDone(2);

		RaceAssertUtil race6AssertUtil = new RaceAssertUtil(scores, race6);
		race6AssertUtil.assertPilot(sco068, 6, 0, false, 0, 1);
		race6AssertUtil.assertPilot(sco179, 6, 0, false, 2, 2);
		race6AssertUtil.assertPilot(sco808, 6, 0, false, 3, 3);
		race6AssertUtil.assertPilot(sco159, 6, 0, false, 4, 4);
		race6AssertUtil.assertPilot(sco200, 6, 0, false, 5, 5);
		race6AssertUtil.assertPilot(sco081, 6, 0, false, 6, 6);
		race6AssertUtil.assertPilot(sco116, 6, 0, false, 7, 7);
		race6AssertUtil.assertPilot(sco087, 6, 0, false, 8, 8);
		race6AssertUtil.assertPilot(sco136, 6, 0, false, 9, 9);
		race6AssertUtil.assertPilot(sco248, 6, 0, false, 10, 10);
		race6AssertUtil.assertPilot(sco249, 5, 0, false, 11, 11);
		race6AssertUtil.assertPilot(sco019, 4, 0, false, 12, 12);
		race6AssertUtil.assertPilot(sco018, 2, 0, false, 13, 13);
		race6AssertUtil.assertPilot(sco042, 0, 0, false, 16, 14);
		race6AssertUtil.assertPilot(sco060, 0, 0, false, 16, 14);
		race6AssertUtil.assertPilot(sco153, 0, 0, false, 16, 14);
		race6AssertUtil.assertPilot(sco156, 0, 0, false, 16, 14);
		race6AssertUtil.assertPilot(sco158, 0, 0, false, 16, 14);
		race6AssertUtil.assertPilot(sco197, 0, 0, false, 16, 14);
		race6AssertUtil.assertDone(0);

		RaceAssertUtil race7AssertUtil = new RaceAssertUtil(scores, race7);
		race7AssertUtil.assertPilot(sco200, 4, 0, false, 0, 1);
		race7AssertUtil.assertPilot(sco808, 4, 0, false, 2, 2);
		race7AssertUtil.assertPilot(sco159, 4, 0, false, 3, 3);
		race7AssertUtil.assertPilot(sco018, 4, 0, false, 4, 4);
		race7AssertUtil.assertPilot(sco087, 4, 0, false, 5, 5);
		race7AssertUtil.assertPilot(sco136, 4, 0, false, 6, 6);
		race7AssertUtil.assertPilot(sco068, 4, 0, false, 7, 7);
		race7AssertUtil.assertPilot(sco248, 4, 0, false, 8, 8);
		race7AssertUtil.assertPilot(sco081, 4, 0, false, 9, 9);
		race7AssertUtil.assertPilot(sco116, 4, 0, false, 10, 10);
		race7AssertUtil.assertPilot(sco249, 4, 0, false, 11, 11);
		race7AssertUtil.assertPilot(sco179, 3, 0, false, 12, 12);
		race7AssertUtil.assertPilot(sco019, 3, 0, false, 13, 13);
		race7AssertUtil.assertPilot(sco153, 2, 0, false, 14, 14);
		race7AssertUtil.assertPilot(sco156, 1, 0, false, 15, 15);
		race7AssertUtil.assertPilot(sco042, 0, 0, false, 16, 16);
		race7AssertUtil.assertPilot(sco060, 0, 0, false, 16, 16);
		race7AssertUtil.assertPilot(sco158, 0, 0, false, 16, 16);
		race7AssertUtil.assertPilot(sco197, 0, 0, false, 16, 16);
		race7AssertUtil.assertDone(0);

		RaceAssertUtil race8AssertUtil = new RaceAssertUtil(scores, race8);
		race8AssertUtil.assertPilot(sco179, 8, 0, false, 0, 1);
		race8AssertUtil.assertPilot(sco081, 8, 0, false, 2, 2);
		race8AssertUtil.assertPilot(sco248, 8, 0, false, 3, 3);
		race8AssertUtil.assertPilot(sco808, 7, 0, false, 4, 4);
		race8AssertUtil.assertPilot(sco249, 6, 0, false, 5, 5);
		race8AssertUtil.assertPilot(sco068, 5, 0, false, 6, 6);
		race8AssertUtil.assertPilot(sco159, 4, 0, false, 7, 7);
		race8AssertUtil.assertPilot(sco087, 4, 0, false, 8, 8);
		race8AssertUtil.assertPilot(sco153, 3, 0, false, 9, 9);
		race8AssertUtil.assertPilot(sco116, 2, 0, false, 10, 10);
		race8AssertUtil.assertPilot(sco018, 0, 0, false, 16, 11);
		race8AssertUtil.assertPilot(sco019, 0, 0, false, 16, 11);
		race8AssertUtil.assertPilot(sco042, 0, 0, false, 16, 11);
		race8AssertUtil.assertPilot(sco060, 0, 0, false, 16, 11);
		race8AssertUtil.assertPilot(sco136, 0, 0, false, 16, 11);
		race8AssertUtil.assertPilot(sco156, 0, 0, false, 16, 11);
		race8AssertUtil.assertPilot(sco158, 0, 0, false, 16, 11);
		race8AssertUtil.assertPilot(sco197, 0, 0, false, 16, 11);
		race8AssertUtil.assertPilot(sco200, 0, 0, false, 16, 11);
		race8AssertUtil.assertDone(0);

		OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
		overallAssertUtil.assertPilot(sco200, 0, 2, 1, 16, 5);
		overallAssertUtil.assertPilot(sco179, 0, 11, 2, 12, 4);
		overallAssertUtil.assertPilot(sco068, 0, 16, 3, 7, 7);
		overallAssertUtil.assertPilot(sco808, 0, 17, 4, 16, 14);
		overallAssertUtil.assertPilot(sco159, 0, 19, 5, 7, 4);
		overallAssertUtil.assertPilot(sco081, 0, 25, 6, 9, 8);
		overallAssertUtil.assertPilot(sco248, 0, 36, 7, 14, 10);
		overallAssertUtil.assertPilot(sco019, 0, 40, 8, 16, 13);
		overallAssertUtil.assertPilot(sco116, 0, 42, 9, 10, 10);
		overallAssertUtil.assertPilot(sco087, 1, 43, 10, 18, 9);
		overallAssertUtil.assertPilot(sco249, 0, 53, 11, 14, 11);
		overallAssertUtil.assertPilot(sco018, 0, 55, 12, 16, 14);
		overallAssertUtil.assertPilot(sco060, 0, 61, 13, 16, 16);
		overallAssertUtil.assertPilot(sco042, 0, 77, 14, 16, 16);
		overallAssertUtil.assertPilot(sco136, 0, 79, 15, 18, 18);
		overallAssertUtil.assertPilot(sco153, 0, 79, 16, 18, 16);
		overallAssertUtil.assertPilot(sco158, 0, 87, 17, 18, 18);
		overallAssertUtil.assertPilot(sco197, 0, 89, 18, 18, 16);
		overallAssertUtil.assertPilot(sco156, 0, 95, 19, 18, 18);
		overallAssertUtil.assertOrder();
	}

	@Test
	public final void checkEvent4() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event4 = eventDAO.find(series, EVENT4_NAME);
			Race race6 = raceDAO.find(event4, RACE6_NAME);
			Race race7 = raceDAO.find(event4, RACE7_NAME);
			Race race8 = raceDAO.find(event4, RACE8_NAME);

			Scores scores = scorer.scoreEvent(event4, Predicates.in(getResultsPilots(series, event4)));
			Assert.assertEquals(EVENT4_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT4_FLEET, scores.getFleetSize(race6));
			Assert.assertEquals(EVENT4_FLEET, scores.getFleetSize(race7));
			Assert.assertEquals(EVENT4_FLEET, scores.getFleetSize(race8));

			RaceAssertUtil race6AssertUtil = new RaceAssertUtil(scores, race6);
			race6AssertUtil.assertPilot(sco068, 6, 0, false, 0, 1);
			race6AssertUtil.assertPilot(sco179, 6, 0, false, 2, 2);
			race6AssertUtil.assertPilot(sco808, 6, 0, false, 3, 3);
			race6AssertUtil.assertPilot(sco159, 6, 0, false, 4, 4);
			race6AssertUtil.assertPilot(sco200, 6, 0, false, 5, 5);
			race6AssertUtil.assertPilot(sco081, 6, 0, false, 6, 6);
			race6AssertUtil.assertPilot(sco116, 6, 0, false, 7, 7);
			race6AssertUtil.assertPilot(sco087, 6, 0, false, 8, 8);
			race6AssertUtil.assertPilot(sco136, 6, 0, false, 9, 9);
			race6AssertUtil.assertPilot(sco248, 6, 0, false, 10, 10);
			race6AssertUtil.assertPilot(sco249, 5, 0, false, 11, 11);
			race6AssertUtil.assertPilot(sco019, 4, 0, false, 12, 12);
			race6AssertUtil.assertPilot(sco018, 2, 0, false, 13, 13);
			race6AssertUtil.assertPilot(sco153, 0, 0, false, 16, 14);
			race6AssertUtil.assertPilot(sco156, 0, 0, false, 16, 14);
			race6AssertUtil.assertDone(0);

			RaceAssertUtil race7AssertUtil = new RaceAssertUtil(scores, race7);
			race7AssertUtil.assertPilot(sco200, 4, 0, false, 0, 1);
			race7AssertUtil.assertPilot(sco808, 4, 0, false, 2, 2);
			race7AssertUtil.assertPilot(sco159, 4, 0, false, 3, 3);
			race7AssertUtil.assertPilot(sco018, 4, 0, false, 4, 4);
			race7AssertUtil.assertPilot(sco087, 4, 0, false, 5, 5);
			race7AssertUtil.assertPilot(sco136, 4, 0, false, 6, 6);
			race7AssertUtil.assertPilot(sco068, 4, 0, false, 7, 7);
			race7AssertUtil.assertPilot(sco248, 4, 0, false, 8, 8);
			race7AssertUtil.assertPilot(sco081, 4, 0, false, 9, 9);
			race7AssertUtil.assertPilot(sco116, 4, 0, false, 10, 10);
			race7AssertUtil.assertPilot(sco249, 4, 0, false, 11, 11);
			race7AssertUtil.assertPilot(sco179, 3, 0, false, 12, 12);
			race7AssertUtil.assertPilot(sco019, 3, 0, false, 13, 13);
			race7AssertUtil.assertPilot(sco153, 2, 0, false, 14, 14);
			race7AssertUtil.assertPilot(sco156, 1, 0, false, 15, 15);
			race7AssertUtil.assertDone(0);

			RaceAssertUtil race8AssertUtil = new RaceAssertUtil(scores, race8);
			race8AssertUtil.assertPilot(sco179, 8, 0, false, 0, 1);
			race8AssertUtil.assertPilot(sco081, 8, 0, false, 2, 2);
			race8AssertUtil.assertPilot(sco248, 8, 0, false, 3, 3);
			race8AssertUtil.assertPilot(sco808, 7, 0, false, 4, 4);
			race8AssertUtil.assertPilot(sco249, 6, 0, false, 5, 5);
			race8AssertUtil.assertPilot(sco068, 5, 0, false, 6, 6);
			race8AssertUtil.assertPilot(sco159, 4, 0, false, 7, 7);
			race8AssertUtil.assertPilot(sco087, 4, 0, false, 8, 8);
			race8AssertUtil.assertPilot(sco153, 3, 0, false, 9, 9);
			race8AssertUtil.assertPilot(sco116, 2, 0, false, 10, 10);
			race8AssertUtil.assertPilot(sco018, 0, 0, false, 16, 11);
			race8AssertUtil.assertPilot(sco019, 0, 0, false, 16, 11);
			race8AssertUtil.assertPilot(sco136, 0, 0, false, 16, 11);
			race8AssertUtil.assertPilot(sco156, 0, 0, false, 16, 11);
			race8AssertUtil.assertPilot(sco200, 0, 0, false, 16, 11);
			race8AssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco808, 0, 9, 1);
			overallAssertUtil.assertPilot(sco068, 0, 13, 2);
			overallAssertUtil.assertPilot(sco179, 0, 14, 3);
			overallAssertUtil.assertPilot(sco159, 0, 14, 4);
			overallAssertUtil.assertPilot(sco081, 0, 17, 5);
			overallAssertUtil.assertPilot(sco200, 0, 21, 6);
			overallAssertUtil.assertPilot(sco248, 0, 21, 7);
			overallAssertUtil.assertPilot(sco087, 0, 21, 8);
			overallAssertUtil.assertPilot(sco249, 0, 27, 9);
			overallAssertUtil.assertPilot(sco116, 0, 27, 10);
			overallAssertUtil.assertPilot(sco136, 0, 31, 11);
			overallAssertUtil.assertPilot(sco018, 0, 33, 12);
			overallAssertUtil.assertPilot(sco153, 0, 39, 13);
			overallAssertUtil.assertPilot(sco019, 0, 41, 14);
			overallAssertUtil.assertPilot(sco156, 0, 47, 15);
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
			Event event4 = eventDAO.find(series, EVENT4_NAME);
			Race race6 = raceDAO.find(event4, RACE6_NAME);

			Scores scores = scorer.scoreRace(race6, Predicates.in(getResultsPilots(series, event4)));
			Assert.assertEquals(EVENT4_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT4_FLEET, scores.getFleetSize(race6));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race6);
			raceAssertUtil.assertPilot(sco068, 6, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco179, 6, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco808, 6, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco159, 6, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco200, 6, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco081, 6, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco116, 6, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco087, 6, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco136, 6, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco248, 6, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco249, 5, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco019, 4, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco018, 2, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco153, 0, 0, false, 16, 14);
			raceAssertUtil.assertPilot(sco156, 0, 0, false, 16, 14);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco068, 0, 0, 1);
			overallAssertUtil.assertPilot(sco179, 0, 2, 2);
			overallAssertUtil.assertPilot(sco808, 0, 3, 3);
			overallAssertUtil.assertPilot(sco159, 0, 4, 4);
			overallAssertUtil.assertPilot(sco200, 0, 5, 5);
			overallAssertUtil.assertPilot(sco081, 0, 6, 6);
			overallAssertUtil.assertPilot(sco116, 0, 7, 7);
			overallAssertUtil.assertPilot(sco087, 0, 8, 8);
			overallAssertUtil.assertPilot(sco136, 0, 9, 9);
			overallAssertUtil.assertPilot(sco248, 0, 10, 10);
			overallAssertUtil.assertPilot(sco249, 0, 11, 11);
			overallAssertUtil.assertPilot(sco019, 0, 12, 12);
			overallAssertUtil.assertPilot(sco018, 0, 13, 13);
			overallAssertUtil.assertPilot(sco153, 0, 16, 14);
			overallAssertUtil.assertPilot(sco156, 0, 16, 14);
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
			Event event4 = eventDAO.find(series, EVENT4_NAME);
			Race race7 = raceDAO.find(event4, RACE7_NAME);

			Scores scores = scorer.scoreRace(race7, Predicates.in(getResultsPilots(series, event4)));
			Assert.assertEquals(EVENT4_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT4_FLEET, scores.getFleetSize(race7));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race7);
			raceAssertUtil.assertPilot(sco200, 4, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco808, 4, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco159, 4, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco018, 4, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco087, 4, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco136, 4, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco068, 4, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco248, 4, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco081, 4, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco116, 4, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco249, 4, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco179, 3, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco019, 3, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco153, 2, 0, false, 14, 14);
			raceAssertUtil.assertPilot(sco156, 1, 0, false, 15, 15);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(sco808, 0, 2, 2);
			overallAssertUtil.assertPilot(sco159, 0, 3, 3);
			overallAssertUtil.assertPilot(sco018, 0, 4, 4);
			overallAssertUtil.assertPilot(sco087, 0, 5, 5);
			overallAssertUtil.assertPilot(sco136, 0, 6, 6);
			overallAssertUtil.assertPilot(sco068, 0, 7, 7);
			overallAssertUtil.assertPilot(sco248, 0, 8, 8);
			overallAssertUtil.assertPilot(sco081, 0, 9, 9);
			overallAssertUtil.assertPilot(sco116, 0, 10, 10);
			overallAssertUtil.assertPilot(sco249, 0, 11, 11);
			overallAssertUtil.assertPilot(sco179, 0, 12, 12);
			overallAssertUtil.assertPilot(sco019, 0, 13, 13);
			overallAssertUtil.assertPilot(sco153, 0, 14, 14);
			overallAssertUtil.assertPilot(sco156, 0, 15, 15);
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
			Event event4 = eventDAO.find(series, EVENT4_NAME);
			Race race8 = raceDAO.find(event4, RACE8_NAME);

			Scores scores = scorer.scoreRace(race8, Predicates.in(getResultsPilots(series, event4)));
			Assert.assertEquals(EVENT4_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT4_FLEET, scores.getFleetSize(race8));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race8);
			raceAssertUtil.assertPilot(sco179, 8, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco081, 8, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco248, 8, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco808, 7, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco249, 6, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco068, 5, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco159, 4, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco087, 4, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco153, 3, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco116, 2, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco018, 0, 0, false, 16, 11);
			raceAssertUtil.assertPilot(sco019, 0, 0, false, 16, 11);
			raceAssertUtil.assertPilot(sco136, 0, 0, false, 16, 11);
			raceAssertUtil.assertPilot(sco156, 0, 0, false, 16, 11);
			raceAssertUtil.assertPilot(sco200, 0, 0, false, 16, 11);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco179, 0, 0, 1);
			overallAssertUtil.assertPilot(sco081, 0, 2, 2);
			overallAssertUtil.assertPilot(sco248, 0, 3, 3);
			overallAssertUtil.assertPilot(sco808, 0, 4, 4);
			overallAssertUtil.assertPilot(sco249, 0, 5, 5);
			overallAssertUtil.assertPilot(sco068, 0, 6, 6);
			overallAssertUtil.assertPilot(sco159, 0, 7, 7);
			overallAssertUtil.assertPilot(sco087, 0, 8, 8);
			overallAssertUtil.assertPilot(sco153, 0, 9, 9);
			overallAssertUtil.assertPilot(sco116, 0, 10, 10);
			overallAssertUtil.assertPilot(sco018, 0, 16, 11);
			overallAssertUtil.assertPilot(sco019, 0, 16, 11);
			overallAssertUtil.assertPilot(sco136, 0, 16, 11);
			overallAssertUtil.assertPilot(sco156, 0, 16, 11);
			overallAssertUtil.assertPilot(sco200, 0, 16, 11);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}