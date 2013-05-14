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
import eu.lp0.cursus.scoring.data.Scores;
import eu.lp0.cursus.test.util.OverallAssertUtil;
import eu.lp0.cursus.test.util.RaceAssertUtil;

/**
 * Scores at the end of event 5 (26/05/2012 to 27/05/2012)
 */
public class Series2011Event5Scores extends Series2011Event4Scores {
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
		Event event2 = eventDAO.find(series, EVENT2_NAME);
		Race race2 = raceDAO.find(event2, RACE2_NAME);
		Race race3 = raceDAO.find(event2, RACE3_NAME);
		Race race4 = raceDAO.find(event2, RACE4_NAME);
		Event event3 = eventDAO.find(series, EVENT3_NAME);
		Race race5 = raceDAO.find(event3, RACE5_NAME);
		Race race6 = raceDAO.find(event3, RACE6_NAME);
		Race race7 = raceDAO.find(event3, RACE7_NAME);
		Race race8 = raceDAO.find(event3, RACE8_NAME);
		Race race9 = raceDAO.find(event3, RACE9_NAME);
		Event event4 = eventDAO.find(series, EVENT4_NAME);
		Race race10 = raceDAO.find(event4, RACE10_NAME);
		Race race11 = raceDAO.find(event4, RACE11_NAME);
		Race race12 = raceDAO.find(event4, RACE12_NAME);
		Race race13 = raceDAO.find(event4, RACE13_NAME);
		Race race14 = raceDAO.find(event4, RACE14_NAME);
		Race race15 = raceDAO.find(event4, RACE15_NAME);
		Event event5 = eventDAO.find(series, EVENT5_NAME);
		Race race16 = raceDAO.find(event5, RACE16_NAME);
		Race race17 = raceDAO.find(event5, RACE17_NAME);
		Race race18 = raceDAO.find(event5, RACE18_NAME);
		Race race19 = raceDAO.find(event5, RACE19_NAME);
		Race race20 = raceDAO.find(event5, RACE20_NAME);

		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getPilots().size());
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race1));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race2));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race3));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race4));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race5));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race6));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race7));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race8));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race9));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race10));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race11));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race12));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race13));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race14));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race15));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race16));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race17));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race18));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race19));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT5, scores.getFleetSize(race20));

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
		race1AssertUtil.assertPilot(sco040, 0, 0, false, 26, 20);
		race1AssertUtil.assertPilot(sco060, 0, 0, false, 26, 20);
		race1AssertUtil.assertPilot(sco153, 0, 0, false, 26, 20);
		race1AssertUtil.assertPilot(sco156, 0, 0, false, 26, 20);
		race1AssertUtil.assertPilot(sco315, 0, 0, false, 26, 20);
		race1AssertUtil.assertPilot(sco529, 0, 0, false, 26, 20);
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
		race2AssertUtil.assertPilot(sco019, 1, 1, false, 13, 13);
		race2AssertUtil.assertPilot(sco153, 1, 0, false, 14, 14);
		race2AssertUtil.assertPilot(sco198, 0, 0, true, 24, 15);
		race2AssertUtil.assertPilot(sco197, 0, 0, true, 25, 16);
		race2AssertUtil.assertPilot(sco018, 0, 0, false, 26, 17);
		race2AssertUtil.assertPilot(sco040, 0, 0, false, 26, 17);
		race2AssertUtil.assertPilot(sco060, 0, 0, false, 26, 17);
		race2AssertUtil.assertPilot(sco136, 0, 0, false, 26, 17);
		race2AssertUtil.assertPilot(sco156, 0, 0, false, 26, 17);
		race2AssertUtil.assertPilot(sco158, 0, 0, false, 26, 17);
		race2AssertUtil.assertPilot(sco315, 0, 0, false, 26, 17);
		race2AssertUtil.assertPilot(sco320, 0, 0, false, 26, 17);
		race2AssertUtil.assertPilot(sco529, 0, 0, false, 26, 17);
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
		race3AssertUtil.assertPilot(sco018, 0, 0, false, 26, 18);
		race3AssertUtil.assertPilot(sco040, 0, 0, false, 26, 18);
		race3AssertUtil.assertPilot(sco156, 0, 0, false, 26, 18);
		race3AssertUtil.assertPilot(sco158, 0, 0, false, 26, 18);
		race3AssertUtil.assertPilot(sco197, 0, 0, false, 26, 18);
		race3AssertUtil.assertPilot(sco315, 0, 0, false, 26, 18);
		race3AssertUtil.assertPilot(sco320, 0, 0, false, 26, 18);
		race3AssertUtil.assertPilot(sco529, 0, 0, false, 26, 18);
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
		race4AssertUtil.assertPilot(sco248, 4, 0, false, 9, 9);
		race4AssertUtil.assertPilot(sco087, 4, 0, false, 10, 10);
		race4AssertUtil.assertPilot(sco081, 2, 0, false, 11, 11);
		race4AssertUtil.assertPilot(sco060, 0, 0, true, 11, 12);
		race4AssertUtil.assertPilot(sco467, 2, 0, false, 12, 13);
		race4AssertUtil.assertPilot(sco153, 2, 0, false, 13, 14);
		race4AssertUtil.assertPilot(sco136, 0, 0, true, 23, 15);
		race4AssertUtil.assertPilot(sco018, 0, 0, false, 26, 16);
		race4AssertUtil.assertPilot(sco019, 0, 0, false, 26, 16);
		race4AssertUtil.assertPilot(sco040, 0, 0, false, 26, 16);
		race4AssertUtil.assertPilot(sco156, 0, 0, false, 26, 16);
		race4AssertUtil.assertPilot(sco158, 0, 0, false, 26, 16);
		race4AssertUtil.assertPilot(sco197, 0, 0, false, 26, 16);
		race4AssertUtil.assertPilot(sco198, 0, 0, false, 26, 16);
		race4AssertUtil.assertPilot(sco315, 0, 0, false, 26, 16);
		race4AssertUtil.assertPilot(sco320, 0, 0, false, 26, 16);
		race4AssertUtil.assertPilot(sco529, 0, 0, false, 26, 16);
		race4AssertUtil.assertDone(2);

		RaceAssertUtil race5AssertUtil = new RaceAssertUtil(scores, race5);
		race5AssertUtil.assertPilot(sco179, 6, 0, false, 0, 1);
		race5AssertUtil.assertPilot(sco068, 5, 0, false, 2, 2);
		race5AssertUtil.assertPilot(sco116, 5, 0, false, 3, 3);
		race5AssertUtil.assertPilot(sco019, 5, 0, false, 4, 4);
		race5AssertUtil.assertPilot(sco808, 5, 0, false, 5, 5);
		race5AssertUtil.assertPilot(sco528, 5, 0, false, 6, 6);
		race5AssertUtil.assertPilot(sco249, 4, 0, false, 7, 7);
		race5AssertUtil.assertPilot(sco248, 0, 0, true, 7, 8);
		race5AssertUtil.assertPilot(sco087, 4, 0, false, 8, 9);
		race5AssertUtil.assertPilot(sco159, 4, 0, false, 9, 10);
		race5AssertUtil.assertPilot(sco081, 3, 0, false, 10, 11);
		race5AssertUtil.assertPilot(sco467, 3, 0, false, 11, 12);
		race5AssertUtil.assertPilot(sco153, 3, 0, false, 12, 13);
		race5AssertUtil.assertPilot(sco060, 1, 0, false, 13, 14);
		race5AssertUtil.assertPilot(sco040, 1, 0, false, 14, 15);
		race5AssertUtil.assertPilot(sco018, 0, 0, true, 17, 16);
		race5AssertUtil.assertPilot(sco136, 0, 0, false, 26, 17);
		race5AssertUtil.assertPilot(sco156, 0, 0, false, 26, 17);
		race5AssertUtil.assertPilot(sco158, 0, 0, false, 26, 17);
		race5AssertUtil.assertPilot(sco197, 0, 0, false, 26, 17);
		race5AssertUtil.assertPilot(sco198, 0, 0, false, 26, 17);
		race5AssertUtil.assertPilot(sco200, 0, 0, false, 26, 17);
		race5AssertUtil.assertPilot(sco315, 0, 0, false, 26, 17);
		race5AssertUtil.assertPilot(sco320, 0, 0, false, 26, 17);
		race5AssertUtil.assertPilot(sco529, 0, 0, false, 26, 17);
		race5AssertUtil.assertDone(2);

		RaceAssertUtil race6AssertUtil = new RaceAssertUtil(scores, race6);
		race6AssertUtil.assertPilot(sco808, 10, 0, false, 0, 1);
		race6AssertUtil.assertPilot(sco179, 10, 0, false, 2, 2);
		race6AssertUtil.assertPilot(sco159, 9, 0, false, 3, 3);
		race6AssertUtil.assertPilot(sco019, 9, 0, false, 4, 4);
		race6AssertUtil.assertPilot(sco249, 9, 0, false, 5, 5);
		race6AssertUtil.assertPilot(sco081, 0, 0, true, 5, 6);
		race6AssertUtil.assertPilot(sco116, 0, 0, true, 5, 6);
		race6AssertUtil.assertPilot(sco528, 8, 0, false, 6, 8);
		race6AssertUtil.assertPilot(sco087, 8, 0, false, 7, 9);
		race6AssertUtil.assertPilot(sco060, 7, 0, false, 8, 10);
		race6AssertUtil.assertPilot(sco467, 7, 0, false, 9, 11);
		race6AssertUtil.assertPilot(sco248, 6, 0, false, 10, 12);
		race6AssertUtil.assertPilot(sco018, 6, 0, false, 11, 13);
		race6AssertUtil.assertPilot(sco320, 5, 0, false, 12, 14);
		race6AssertUtil.assertPilot(sco068, 3, 0, false, 13, 15);
		race6AssertUtil.assertPilot(sco153, 3, 0, false, 14, 16);
		race6AssertUtil.assertPilot(sco040, 2, 0, false, 15, 17);
		race6AssertUtil.assertPilot(sco136, 0, 0, false, 26, 18);
		race6AssertUtil.assertPilot(sco156, 0, 0, false, 26, 18);
		race6AssertUtil.assertPilot(sco158, 0, 0, false, 26, 18);
		race6AssertUtil.assertPilot(sco197, 0, 0, false, 26, 18);
		race6AssertUtil.assertPilot(sco198, 0, 0, false, 26, 18);
		race6AssertUtil.assertPilot(sco200, 0, 0, false, 26, 18);
		race6AssertUtil.assertPilot(sco315, 0, 0, false, 26, 18);
		race6AssertUtil.assertPilot(sco529, 0, 0, false, 26, 18);
		race6AssertUtil.assertDone(2);

		RaceAssertUtil race7AssertUtil = new RaceAssertUtil(scores, race7);
		race7AssertUtil.assertPilot(sco249, 4, 0, false, 0, 1);
		race7AssertUtil.assertPilot(sco068, 4, 0, false, 2, 2);
		race7AssertUtil.assertPilot(sco248, 4, 0, false, 3, 3);
		race7AssertUtil.assertPilot(sco808, 4, 0, false, 4, 4);
		race7AssertUtil.assertPilot(sco467, 4, 0, false, 5, 5);
		race7AssertUtil.assertPilot(sco159, 0, 0, true, 5, 6);
		race7AssertUtil.assertPilot(sco528, 3, 0, false, 6, 7);
		race7AssertUtil.assertPilot(sco179, 3, 0, false, 7, 8);
		race7AssertUtil.assertPilot(sco087, 2, 0, false, 8, 9);
		race7AssertUtil.assertPilot(sco081, 2, 0, false, 9, 10);
		race7AssertUtil.assertPilot(sco320, 1, 0, false, 10, 11);
		race7AssertUtil.assertPilot(sco060, 1, 0, false, 11, 12);
		race7AssertUtil.assertPilot(sco116, 1, 0, false, 12, 13);
		race7AssertUtil.assertPilot(sco153, 1, 0, false, 13, 14);
		race7AssertUtil.assertPilot(sco019, 0, 0, true, 20, 15);
		race7AssertUtil.assertPilot(sco018, 0, 0, false, 26, 16);
		race7AssertUtil.assertPilot(sco040, 0, 0, false, 26, 16);
		race7AssertUtil.assertPilot(sco136, 0, 0, false, 26, 16);
		race7AssertUtil.assertPilot(sco156, 0, 0, false, 26, 16);
		race7AssertUtil.assertPilot(sco158, 0, 0, false, 26, 16);
		race7AssertUtil.assertPilot(sco197, 0, 0, false, 26, 16);
		race7AssertUtil.assertPilot(sco198, 0, 0, false, 26, 16);
		race7AssertUtil.assertPilot(sco200, 0, 0, false, 26, 16);
		race7AssertUtil.assertPilot(sco315, 0, 0, false, 26, 16);
		race7AssertUtil.assertPilot(sco529, 0, 0, false, 26, 16);
		race7AssertUtil.assertDone(2);

		RaceAssertUtil race8AssertUtil = new RaceAssertUtil(scores, race8);
		race8AssertUtil.assertPilot(sco068, 7, 0, false, 0, 1);
		race8AssertUtil.assertPilot(sco200, 7, 0, false, 2, 2);
		race8AssertUtil.assertPilot(sco808, 6, 0, false, 3, 3);
		race8AssertUtil.assertPilot(sco159, 6, 0, false, 4, 4);
		race8AssertUtil.assertPilot(sco249, 6, 0, false, 5, 5);
		race8AssertUtil.assertPilot(sco528, 6, 0, false, 6, 6);
		race8AssertUtil.assertPilot(sco248, 6, 0, false, 7, 7);
		race8AssertUtil.assertPilot(sco179, 5, 0, false, 8, 8);
		race8AssertUtil.assertPilot(sco116, 5, 0, false, 9, 9);
		race8AssertUtil.assertPilot(sco087, 0, 0, true, 9, 10);
		race8AssertUtil.assertPilot(sco467, 5, 0, false, 10, 11);
		race8AssertUtil.assertPilot(sco320, 4, 0, false, 11, 12);
		race8AssertUtil.assertPilot(sco040, 4, 0, false, 12, 13);
		race8AssertUtil.assertPilot(sco156, 3, 0, false, 13, 14);
		race8AssertUtil.assertPilot(sco153, 0, 0, true, 14, 15);
		race8AssertUtil.assertPilot(sco018, 0, 0, false, 26, 16);
		race8AssertUtil.assertPilot(sco019, 0, 0, false, 26, 16);
		race8AssertUtil.assertPilot(sco060, 0, 0, false, 26, 16);
		race8AssertUtil.assertPilot(sco081, 0, 0, false, 26, 16);
		race8AssertUtil.assertPilot(sco136, 0, 0, false, 26, 16);
		race8AssertUtil.assertPilot(sco158, 0, 0, false, 26, 16);
		race8AssertUtil.assertPilot(sco197, 0, 0, false, 26, 16);
		race8AssertUtil.assertPilot(sco198, 0, 0, false, 26, 16);
		race8AssertUtil.assertPilot(sco315, 0, 0, false, 26, 16);
		race8AssertUtil.assertPilot(sco529, 0, 0, false, 26, 16);
		race8AssertUtil.assertDone(2);

		RaceAssertUtil race9AssertUtil = new RaceAssertUtil(scores, race9);
		race9AssertUtil.assertPilot(sco200, 5, 0, false, 0, 1);
		race9AssertUtil.assertPilot(sco528, 5, 0, false, 2, 2);
		race9AssertUtil.assertPilot(sco068, 0, 0, true, 2, 3);
		race9AssertUtil.assertPilot(sco808, 5, 0, false, 3, 4);
		race9AssertUtil.assertPilot(sco248, 5, 0, false, 4, 5);
		race9AssertUtil.assertPilot(sco116, 4, 0, false, 5, 6);
		race9AssertUtil.assertPilot(sco179, 4, 0, false, 6, 7);
		race9AssertUtil.assertPilot(sco159, 3, 0, false, 7, 8);
		race9AssertUtil.assertPilot(sco320, 3, 0, false, 8, 9);
		race9AssertUtil.assertPilot(sco249, 0, 0, true, 8, 10);
		race9AssertUtil.assertPilot(sco467, 3, 0, false, 9, 11);
		race9AssertUtil.assertPilot(sco153, 2, 0, false, 10, 12);
		race9AssertUtil.assertPilot(sco087, 1, 0, false, 11, 13);
		race9AssertUtil.assertPilot(sco018, 0, 0, false, 26, 14);
		race9AssertUtil.assertPilot(sco019, 0, 0, false, 26, 14);
		race9AssertUtil.assertPilot(sco040, 0, 0, false, 26, 14);
		race9AssertUtil.assertPilot(sco060, 0, 0, false, 26, 14);
		race9AssertUtil.assertPilot(sco081, 0, 0, false, 26, 14);
		race9AssertUtil.assertPilot(sco136, 0, 0, false, 26, 14);
		race9AssertUtil.assertPilot(sco156, 0, 0, false, 26, 14);
		race9AssertUtil.assertPilot(sco158, 0, 0, false, 26, 14);
		race9AssertUtil.assertPilot(sco197, 0, 0, false, 26, 14);
		race9AssertUtil.assertPilot(sco198, 0, 0, false, 26, 14);
		race9AssertUtil.assertPilot(sco315, 0, 0, false, 26, 14);
		race9AssertUtil.assertPilot(sco529, 0, 0, false, 26, 14);
		race9AssertUtil.assertDone(2);

		RaceAssertUtil race10AssertUtil = new RaceAssertUtil(scores, race10);
		race10AssertUtil.assertPilot(sco068, 7, 0, false, 0, 1);
		race10AssertUtil.assertPilot(sco200, 0, 0, true, 0, 2);
		race10AssertUtil.assertPilot(sco081, 7, 0, false, 2, 3);
		race10AssertUtil.assertPilot(sco808, 7, 0, false, 3, 4);
		race10AssertUtil.assertPilot(sco159, 7, 0, false, 4, 5);
		race10AssertUtil.assertPilot(sco179, 7, 0, false, 5, 6);
		race10AssertUtil.assertPilot(sco116, 6, 0, false, 6, 7);
		race10AssertUtil.assertPilot(sco248, 6, 0, false, 7, 8);
		race10AssertUtil.assertPilot(sco528, 6, 0, false, 8, 9);
		race10AssertUtil.assertPilot(sco087, 6, 0, false, 9, 10);
		race10AssertUtil.assertPilot(sco467, 5, 0, false, 10, 11);
		race10AssertUtil.assertPilot(sco320, 5, 0, false, 11, 12);
		race10AssertUtil.assertPilot(sco060, 5, 0, false, 12, 13);
		race10AssertUtil.assertPilot(sco018, 5, 0, false, 13, 14);
		race10AssertUtil.assertPilot(sco315, 5, 0, false, 14, 15);
		race10AssertUtil.assertPilot(sco249, 4, 0, false, 15, 16);
		race10AssertUtil.assertPilot(sco529, 4, 0, false, 16, 17);
		race10AssertUtil.assertPilot(sco153, 4, 0, false, 17, 18);
		race10AssertUtil.assertPilot(sco156, 2, 0, false, 18, 19);
		race10AssertUtil.assertPilot(sco158, 0, 0, true, 21, 20);
		race10AssertUtil.assertPilot(sco019, 0, 0, false, 26, 21);
		race10AssertUtil.assertPilot(sco040, 0, 0, false, 26, 21);
		race10AssertUtil.assertPilot(sco136, 0, 0, false, 26, 21);
		race10AssertUtil.assertPilot(sco197, 0, 0, false, 26, 21);
		race10AssertUtil.assertPilot(sco198, 0, 0, false, 26, 21);
		race10AssertUtil.assertDone(2);

		RaceAssertUtil race11AssertUtil = new RaceAssertUtil(scores, race11);
		race11AssertUtil.assertPilot(sco200, 8, 0, false, 0, 1);
		race11AssertUtil.assertPilot(sco179, 8, 0, false, 2, 2);
		race11AssertUtil.assertPilot(sco068, 0, 0, true, 2, 3);
		race11AssertUtil.assertPilot(sco248, 7, 0, false, 3, 4);
		race11AssertUtil.assertPilot(sco116, 6, 0, false, 4, 5);
		race11AssertUtil.assertPilot(sco528, 6, 0, false, 5, 6);
		race11AssertUtil.assertPilot(sco081, 0, 0, true, 5, 7);
		race11AssertUtil.assertPilot(sco159, 6, 0, false, 6, 8);
		race11AssertUtil.assertPilot(sco060, 6, 0, false, 7, 9);
		race11AssertUtil.assertPilot(sco087, 5, 0, false, 8, 10);
		race11AssertUtil.assertPilot(sco467, 5, 0, false, 9, 11);
		race11AssertUtil.assertPilot(sco018, 5, 0, false, 10, 12);
		race11AssertUtil.assertPilot(sco315, 5, 0, false, 11, 13);
		race11AssertUtil.assertPilot(sco320, 5, 0, false, 12, 14);
		race11AssertUtil.assertPilot(sco158, 4, 0, false, 13, 15);
		race11AssertUtil.assertPilot(sco529, 4, 0, false, 14, 16);
		race11AssertUtil.assertPilot(sco153, 4, 0, false, 15, 17);
		race11AssertUtil.assertPilot(sco156, 3, 0, false, 16, 18);
		race11AssertUtil.assertPilot(sco019, 0, 0, false, 26, 19);
		race11AssertUtil.assertPilot(sco040, 0, 0, false, 26, 19);
		race11AssertUtil.assertPilot(sco136, 0, 0, false, 26, 19);
		race11AssertUtil.assertPilot(sco197, 0, 0, false, 26, 19);
		race11AssertUtil.assertPilot(sco198, 0, 0, false, 26, 19);
		race11AssertUtil.assertPilot(sco249, 0, 0, false, 26, 19);
		race11AssertUtil.assertPilot(sco808, 0, 0, false, 26, 19);
		race11AssertUtil.assertDone(2);

		RaceAssertUtil race12AssertUtil = new RaceAssertUtil(scores, race12);
		race12AssertUtil.assertPilot(sco200, 8, 0, false, 0, 1);
		race12AssertUtil.assertPilot(sco068, 8, 0, false, 2, 2);
		race12AssertUtil.assertPilot(sco808, 0, 0, true, 2, 3);
		race12AssertUtil.assertPilot(sco081, 8, 0, false, 3, 4);
		race12AssertUtil.assertPilot(sco116, 8, 0, false, 4, 5);
		race12AssertUtil.assertPilot(sco179, 7, 0, false, 5, 6);
		race12AssertUtil.assertPilot(sco159, 7, 0, false, 6, 7);
		race12AssertUtil.assertPilot(sco528, 7, 0, false, 7, 8);
		race12AssertUtil.assertPilot(sco467, 6, 0, false, 8, 9);
		race12AssertUtil.assertPilot(sco018, 6, 0, false, 9, 10);
		race12AssertUtil.assertPilot(sco087, 6, 0, false, 10, 11);
		race12AssertUtil.assertPilot(sco158, 6, 0, false, 11, 12);
		race12AssertUtil.assertPilot(sco248, 5, 0, false, 12, 13);
		race12AssertUtil.assertPilot(sco315, 5, 0, false, 13, 14);
		race12AssertUtil.assertPilot(sco529, 5, 0, false, 14, 15);
		race12AssertUtil.assertPilot(sco153, 0, 0, true, 14, 16);
		race12AssertUtil.assertPilot(sco320, 4, 0, false, 15, 17);
		race12AssertUtil.assertPilot(sco156, 3, 0, false, 16, 18);
		race12AssertUtil.assertPilot(sco060, 3, 0, false, 17, 19);
		race12AssertUtil.assertPilot(sco019, 0, 0, false, 26, 20);
		race12AssertUtil.assertPilot(sco040, 0, 0, false, 26, 20);
		race12AssertUtil.assertPilot(sco136, 0, 0, false, 26, 20);
		race12AssertUtil.assertPilot(sco197, 0, 0, false, 26, 20);
		race12AssertUtil.assertPilot(sco198, 0, 0, false, 26, 20);
		race12AssertUtil.assertPilot(sco249, 0, 0, false, 26, 20);
		race12AssertUtil.assertDone(2);

		RaceAssertUtil race13AssertUtil = new RaceAssertUtil(scores, race13);
		race13AssertUtil.assertPilot(sco200, 12, 0, false, 0, 1);
		race13AssertUtil.assertPilot(sco081, 12, 0, false, 2, 2);
		race13AssertUtil.assertPilot(sco179, 11, 0, false, 3, 3);
		race13AssertUtil.assertPilot(sco808, 11, 0, false, 4, 4);
		race13AssertUtil.assertPilot(sco159, 11, 0, false, 5, 5);
		race13AssertUtil.assertPilot(sco068, 10, 0, false, 6, 6);
		race13AssertUtil.assertPilot(sco528, 10, 0, false, 7, 7);
		race13AssertUtil.assertPilot(sco116, 9, 0, false, 8, 8);
		race13AssertUtil.assertPilot(sco248, 9, 0, false, 9, 9);
		race13AssertUtil.assertPilot(sco087, 0, 0, true, 9, 10);
		race13AssertUtil.assertPilot(sco018, 9, 0, false, 10, 11);
		race13AssertUtil.assertPilot(sco158, 9, 0, false, 11, 12);
		race13AssertUtil.assertPilot(sco320, 8, 0, false, 12, 13);
		race13AssertUtil.assertPilot(sco467, 6, 0, false, 13, 14);
		race13AssertUtil.assertPilot(sco529, 6, 0, false, 14, 15);
		race13AssertUtil.assertPilot(sco156, 6, 0, false, 15, 16);
		race13AssertUtil.assertPilot(sco315, 5, 0, false, 16, 17);
		race13AssertUtil.assertPilot(sco153, 4, 0, false, 17, 18);
		race13AssertUtil.assertPilot(sco019, 0, 0, false, 26, 19);
		race13AssertUtil.assertPilot(sco040, 0, 0, false, 26, 19);
		race13AssertUtil.assertPilot(sco060, 0, 0, false, 26, 19);
		race13AssertUtil.assertPilot(sco136, 0, 0, false, 26, 19);
		race13AssertUtil.assertPilot(sco197, 0, 0, false, 26, 19);
		race13AssertUtil.assertPilot(sco198, 0, 0, false, 26, 19);
		race13AssertUtil.assertPilot(sco249, 0, 0, false, 26, 19);
		race13AssertUtil.assertDone(1);

		RaceAssertUtil race14AssertUtil = new RaceAssertUtil(scores, race14);
		race14AssertUtil.assertPilot(sco200, 8, 0, false, 0, 1);
		race14AssertUtil.assertPilot(sco808, 8, 0, false, 2, 2);
		race14AssertUtil.assertPilot(sco068, 8, 0, false, 3, 3);
		race14AssertUtil.assertPilot(sco179, 0, 0, true, 3, 4);
		race14AssertUtil.assertPilot(sco159, 7, 0, false, 4, 5);
		race14AssertUtil.assertPilot(sco116, 7, 0, false, 5, 6);
		race14AssertUtil.assertPilot(sco081, 7, 0, false, 6, 7);
		race14AssertUtil.assertPilot(sco528, 6, 0, false, 7, 8);
		race14AssertUtil.assertPilot(sco248, 6, 0, false, 8, 9);
		race14AssertUtil.assertPilot(sco018, 6, 0, false, 9, 10);
		race14AssertUtil.assertPilot(sco320, 6, 0, false, 10, 11);
		race14AssertUtil.assertPilot(sco087, 5, 0, false, 11, 12);
		race14AssertUtil.assertPilot(sco249, 5, 0, false, 12, 13);
		race14AssertUtil.assertPilot(sco060, 5, 1, false, 13, 14);
		race14AssertUtil.assertPilot(sco315, 4, 0, false, 14, 15);
		race14AssertUtil.assertPilot(sco529, 4, 0, false, 15, 16);
		race14AssertUtil.assertPilot(sco153, 3, 0, false, 16, 17);
		race14AssertUtil.assertPilot(sco156, 3, 1, false, 17, 18);
		race14AssertUtil.assertPilot(sco158, 0, 0, true, 21, 19);
		race14AssertUtil.assertPilot(sco019, 0, 0, false, 26, 20);
		race14AssertUtil.assertPilot(sco040, 0, 0, false, 26, 20);
		race14AssertUtil.assertPilot(sco136, 0, 0, false, 26, 20);
		race14AssertUtil.assertPilot(sco197, 0, 0, false, 26, 20);
		race14AssertUtil.assertPilot(sco198, 0, 0, false, 26, 20);
		race14AssertUtil.assertPilot(sco467, 0, 0, false, 26, 20);
		race14AssertUtil.assertDone(2);

		RaceAssertUtil race15AssertUtil = new RaceAssertUtil(scores, race15);
		race15AssertUtil.assertPilot(sco200, 9, 0, false, 0, 1);
		race15AssertUtil.assertPilot(sco179, 8, 0, false, 2, 2);
		race15AssertUtil.assertPilot(sco116, 8, 0, false, 3, 3);
		race15AssertUtil.assertPilot(sco068, 8, 0, false, 4, 4);
		race15AssertUtil.assertPilot(sco808, 7, 0, false, 5, 5);
		race15AssertUtil.assertPilot(sco159, 0, 0, true, 5, 6);
		race15AssertUtil.assertPilot(sco248, 7, 0, false, 6, 7);
		race15AssertUtil.assertPilot(sco087, 7, 0, false, 7, 8);
		race15AssertUtil.assertPilot(sco320, 7, 0, false, 8, 9);
		race15AssertUtil.assertPilot(sco249, 0, 0, true, 8, 10);
		race15AssertUtil.assertPilot(sco528, 7, 1, false, 9, 11);
		race15AssertUtil.assertPilot(sco018, 7, 0, false, 10, 12);
		race15AssertUtil.assertPilot(sco060, 6, 0, false, 11, 13);
		race15AssertUtil.assertPilot(sco529, 4, 0, false, 12, 14);
		race15AssertUtil.assertPilot(sco153, 4, 0, false, 13, 15);
		race15AssertUtil.assertPilot(sco315, 4, 0, false, 14, 16);
		race15AssertUtil.assertPilot(sco158, 2, 0, false, 15, 17);
		race15AssertUtil.assertPilot(sco019, 0, 0, false, 26, 18);
		race15AssertUtil.assertPilot(sco040, 0, 0, false, 26, 18);
		race15AssertUtil.assertPilot(sco081, 0, 0, false, 26, 18);
		race15AssertUtil.assertPilot(sco136, 0, 0, false, 26, 18);
		race15AssertUtil.assertPilot(sco156, 0, 0, false, 26, 18);
		race15AssertUtil.assertPilot(sco197, 0, 0, false, 26, 18);
		race15AssertUtil.assertPilot(sco198, 0, 0, false, 26, 18);
		race15AssertUtil.assertPilot(sco467, 0, 0, false, 26, 18);
		race15AssertUtil.assertDone(2);

		RaceAssertUtil race16AssertUtil = new RaceAssertUtil(scores, race16);
		race16AssertUtil.assertPilot(sco808, 6, 0, false, 0, 1);
		race16AssertUtil.assertPilot(sco179, 6, 0, false, 2, 2);
		race16AssertUtil.assertPilot(sco116, 6, 0, false, 3, 3);
		race16AssertUtil.assertPilot(sco081, 6, 0, false, 4, 4);
		race16AssertUtil.assertPilot(sco200, 6, 0, false, 5, 5);
		race16AssertUtil.assertPilot(sco060, 5, 0, false, 6, 6);
		race16AssertUtil.assertPilot(sco087, 5, 0, false, 7, 7);
		race16AssertUtil.assertPilot(sco528, 5, 0, false, 8, 8);
		race16AssertUtil.assertPilot(sco249, 5, 0, false, 9, 9);
		race16AssertUtil.assertPilot(sco467, 5, 0, false, 10, 10);
		race16AssertUtil.assertPilot(sco159, 4, 0, false, 11, 11);
		race16AssertUtil.assertPilot(sco529, 4, 0, false, 12, 12);
		race16AssertUtil.assertPilot(sco248, 3, 0, false, 13, 13);
		race16AssertUtil.assertPilot(sco153, 3, 0, false, 14, 14);
		race16AssertUtil.assertPilot(sco320, 3, 0, false, 15, 15);
		race16AssertUtil.assertPilot(sco068, 1, 0, false, 16, 16);
		race16AssertUtil.assertPilot(sco018, 0, 0, false, 26, 17);
		race16AssertUtil.assertPilot(sco019, 0, 0, false, 26, 17);
		race16AssertUtil.assertPilot(sco040, 0, 0, false, 26, 17);
		race16AssertUtil.assertPilot(sco136, 0, 0, false, 26, 17);
		race16AssertUtil.assertPilot(sco156, 0, 0, false, 26, 17);
		race16AssertUtil.assertPilot(sco158, 0, 0, false, 26, 17);
		race16AssertUtil.assertPilot(sco197, 0, 0, false, 26, 17);
		race16AssertUtil.assertPilot(sco198, 0, 0, false, 26, 17);
		race16AssertUtil.assertPilot(sco315, 0, 0, false, 26, 17);
		race16AssertUtil.assertDone(0);

		RaceAssertUtil race17AssertUtil = new RaceAssertUtil(scores, race17);
		race17AssertUtil.assertPilot(sco808, 4, 0, false, 0, 1);
		race17AssertUtil.assertPilot(sco179, 4, 0, false, 2, 2);
		race17AssertUtil.assertPilot(sco200, 4, 0, false, 3, 3);
		race17AssertUtil.assertPilot(sco116, 4, 0, false, 4, 4);
		race17AssertUtil.assertPilot(sco068, 4, 0, false, 5, 5);
		race17AssertUtil.assertPilot(sco081, 4, 0, false, 6, 6);
		race17AssertUtil.assertPilot(sco528, 3, 0, false, 7, 7);
		race17AssertUtil.assertPilot(sco248, 3, 0, false, 8, 8);
		race17AssertUtil.assertPilot(sco249, 2, 0, false, 9, 9);
		race17AssertUtil.assertPilot(sco060, 1, 0, false, 10, 10);
		race17AssertUtil.assertPilot(sco159, 1, 0, false, 11, 11);
		race17AssertUtil.assertPilot(sco087, 1, 0, false, 12, 12);
		race17AssertUtil.assertPilot(sco529, 1, 0, false, 13, 13);
		race17AssertUtil.assertPilot(sco467, 1, 0, false, 14, 14);
		race17AssertUtil.assertPilot(sco320, 1, 0, false, 15, 15);
		race17AssertUtil.assertPilot(sco153, 1, 0, false, 16, 16);
		race17AssertUtil.assertPilot(sco018, 0, 0, false, 26, 17);
		race17AssertUtil.assertPilot(sco019, 0, 0, false, 26, 17);
		race17AssertUtil.assertPilot(sco040, 0, 0, false, 26, 17);
		race17AssertUtil.assertPilot(sco136, 0, 0, false, 26, 17);
		race17AssertUtil.assertPilot(sco156, 0, 0, false, 26, 17);
		race17AssertUtil.assertPilot(sco158, 0, 0, false, 26, 17);
		race17AssertUtil.assertPilot(sco197, 0, 0, false, 26, 17);
		race17AssertUtil.assertPilot(sco198, 0, 0, false, 26, 17);
		race17AssertUtil.assertPilot(sco315, 0, 0, false, 26, 17);
		race17AssertUtil.assertDone(0);

		RaceAssertUtil race18AssertUtil = new RaceAssertUtil(scores, race18);
		race18AssertUtil.assertPilot(sco200, 5, 0, false, 0, 1);
		race18AssertUtil.assertPilot(sco068, 5, 0, false, 2, 2);
		race18AssertUtil.assertPilot(sco808, 5, 0, false, 3, 3);
		race18AssertUtil.assertPilot(sco179, 5, 0, false, 4, 4);
		race18AssertUtil.assertPilot(sco081, 3, 0, false, 5, 5);
		race18AssertUtil.assertPilot(sco116, 3, 0, false, 6, 6);
		race18AssertUtil.assertPilot(sco528, 2, 0, false, 7, 7);
		race18AssertUtil.assertPilot(sco249, 2, 0, false, 8, 8);
		race18AssertUtil.assertPilot(sco159, 2, 0, false, 9, 9);
		race18AssertUtil.assertPilot(sco248, 1, 0, false, 10, 10);
		race18AssertUtil.assertPilot(sco087, 1, 0, false, 11, 11);
		race18AssertUtil.assertPilot(sco529, 1, 0, false, 12, 12);
		race18AssertUtil.assertPilot(sco467, 1, 0, false, 13, 13);
		race18AssertUtil.assertPilot(sco060, 1, 0, false, 14, 14);
		race18AssertUtil.assertPilot(sco018, 0, 0, false, 26, 15);
		race18AssertUtil.assertPilot(sco019, 0, 0, false, 26, 15);
		race18AssertUtil.assertPilot(sco040, 0, 0, false, 26, 15);
		race18AssertUtil.assertPilot(sco136, 0, 0, false, 26, 15);
		race18AssertUtil.assertPilot(sco153, 0, 0, false, 26, 15);
		race18AssertUtil.assertPilot(sco156, 0, 0, false, 26, 15);
		race18AssertUtil.assertPilot(sco158, 0, 0, false, 26, 15);
		race18AssertUtil.assertPilot(sco197, 0, 0, false, 26, 15);
		race18AssertUtil.assertPilot(sco198, 0, 0, false, 26, 15);
		race18AssertUtil.assertPilot(sco315, 0, 0, false, 26, 15);
		race18AssertUtil.assertPilot(sco320, 0, 0, false, 26, 15);
		race18AssertUtil.assertDone(0);

		RaceAssertUtil race19AssertUtil = new RaceAssertUtil(scores, race19);
		race19AssertUtil.assertPilot(sco200, 8, 0, false, 0, 1);
		race19AssertUtil.assertPilot(sco808, 8, 0, false, 2, 2);
		race19AssertUtil.assertPilot(sco068, 7, 0, false, 3, 3);
		race19AssertUtil.assertPilot(sco179, 7, 0, false, 4, 4);
		race19AssertUtil.assertPilot(sco116, 7, 0, false, 5, 5);
		race19AssertUtil.assertPilot(sco159, 6, 0, false, 6, 6);
		race19AssertUtil.assertPilot(sco081, 6, 0, false, 7, 7);
		race19AssertUtil.assertPilot(sco467, 6, 0, false, 8, 8);
		race19AssertUtil.assertPilot(sco087, 6, 0, false, 9, 9);
		race19AssertUtil.assertPilot(sco249, 6, 0, false, 10, 10);
		race19AssertUtil.assertPilot(sco528, 5, 0, false, 11, 11);
		race19AssertUtil.assertPilot(sco529, 4, 0, false, 12, 12);
		race19AssertUtil.assertPilot(sco320, 4, 0, false, 13, 13);
		race19AssertUtil.assertPilot(sco248, 3, 0, false, 14, 14);
		race19AssertUtil.assertPilot(sco060, 2, 0, false, 15, 15);
		race19AssertUtil.assertPilot(sco153, 2, 0, false, 16, 16);
		race19AssertUtil.assertPilot(sco018, 0, 0, false, 26, 17);
		race19AssertUtil.assertPilot(sco019, 0, 0, false, 26, 17);
		race19AssertUtil.assertPilot(sco040, 0, 0, false, 26, 17);
		race19AssertUtil.assertPilot(sco136, 0, 0, false, 26, 17);
		race19AssertUtil.assertPilot(sco156, 0, 0, false, 26, 17);
		race19AssertUtil.assertPilot(sco158, 0, 0, false, 26, 17);
		race19AssertUtil.assertPilot(sco197, 0, 0, false, 26, 17);
		race19AssertUtil.assertPilot(sco198, 0, 0, false, 26, 17);
		race19AssertUtil.assertPilot(sco315, 0, 0, false, 26, 17);
		race19AssertUtil.assertDone(0);

		RaceAssertUtil race20AssertUtil = new RaceAssertUtil(scores, race20);
		race20AssertUtil.assertPilot(sco200, 11, 0, false, 0, 1);
		race20AssertUtil.assertPilot(sco081, 10, 0, false, 2, 2);
		race20AssertUtil.assertPilot(sco808, 10, 0, false, 3, 3);
		race20AssertUtil.assertPilot(sco179, 10, 0, false, 4, 4);
		race20AssertUtil.assertPilot(sco068, 10, 0, false, 5, 5);
		race20AssertUtil.assertPilot(sco116, 10, 0, false, 6, 6);
		race20AssertUtil.assertPilot(sco159, 9, 0, false, 7, 7);
		race20AssertUtil.assertPilot(sco087, 8, 0, false, 8, 8);
		race20AssertUtil.assertPilot(sco060, 8, 0, false, 9, 9);
		race20AssertUtil.assertPilot(sco249, 7, 0, false, 10, 10);
		race20AssertUtil.assertPilot(sco528, 7, 0, false, 11, 11);
		race20AssertUtil.assertPilot(sco248, 6, 0, false, 12, 12);
		race20AssertUtil.assertPilot(sco467, 6, 0, false, 13, 13);
		race20AssertUtil.assertPilot(sco529, 6, 0, false, 14, 14);
		race20AssertUtil.assertPilot(sco320, 6, 0, false, 15, 15);
		race20AssertUtil.assertPilot(sco153, 2, 0, false, 16, 16);
		race20AssertUtil.assertPilot(sco018, 0, 0, false, 26, 17);
		race20AssertUtil.assertPilot(sco019, 0, 0, false, 26, 17);
		race20AssertUtil.assertPilot(sco040, 0, 0, false, 26, 17);
		race20AssertUtil.assertPilot(sco136, 0, 0, false, 26, 17);
		race20AssertUtil.assertPilot(sco156, 0, 0, false, 26, 17);
		race20AssertUtil.assertPilot(sco158, 0, 0, false, 26, 17);
		race20AssertUtil.assertPilot(sco197, 0, 0, false, 26, 17);
		race20AssertUtil.assertPilot(sco198, 0, 0, false, 26, 17);
		race20AssertUtil.assertPilot(sco315, 0, 0, false, 26, 17);
		race20AssertUtil.assertDone(0);

		OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
		overallAssertUtil.assertPilot(sco200, 0, 4, 1, 26, 26, 26, 5, 3);
		overallAssertUtil.assertPilot(sco808, 1, 32, 2, 26, 5, 5, 4, 4);
		overallAssertUtil.assertPilot(sco068, 0, 32, 3, 16, 13, 6, 5, 5);
		overallAssertUtil.assertPilot(sco179, 0, 41, 4, 8, 7, 6, 5, 5);
		overallAssertUtil.assertPilot(sco081, 0, 69, 5, 26, 26, 26, 11, 10);
		overallAssertUtil.assertPilot(sco116, 0, 69, 6, 12, 9, 9, 8, 6);
		overallAssertUtil.assertPilot(sco159, 0, 78, 7, 11, 11, 11, 9, 9);
		overallAssertUtil.assertPilot(sco528, 1, 94, 8, 11, 11, 9, 9, 8);
		overallAssertUtil.assertPilot(sco248, 0, 108, 9, 14, 13, 12, 12, 10);
		overallAssertUtil.assertPilot(sco249, 0, 114, 10, 26, 26, 26, 19, 15);
		overallAssertUtil.assertPilot(sco087, 0, 130, 11, 15, 12, 12, 11, 11);
		overallAssertUtil.assertPilot(sco467, 0, 146, 12, 26, 26, 14, 13, 13);
		overallAssertUtil.assertPilot(sco060, 1, 170, 13, 26, 26, 26, 26, 26);
		overallAssertUtil.assertPilot(sco320, 0, 185, 14, 26, 26, 26, 26, 26);
		overallAssertUtil.assertPilot(sco153, 0, 208, 15, 26, 26, 17, 17, 16);
		overallAssertUtil.assertPilot(sco529, 6, 258, 16, 26, 26, 26, 26, 26);
		overallAssertUtil.assertPilot(sco018, 3, 261, 17, 26, 26, 26, 26, 26);
		overallAssertUtil.assertPilot(sco019, 3, 303, 18, 26, 26, 26, 26, 26);
		overallAssertUtil.assertPilot(sco158, 6, 323, 19, 26, 26, 26, 26, 26);
		overallAssertUtil.assertPilot(sco315, 7, 323, 20, 26, 26, 26, 26, 26);
		overallAssertUtil.assertPilot(sco156, 3, 332, 21, 26, 26, 26, 26, 26);
		overallAssertUtil.assertPilot(sco136, 4, 354, 22, 26, 26, 26, 26, 26);
		overallAssertUtil.assertPilot(sco040, 4, 357, 23, 26, 26, 26, 26, 26);
		overallAssertUtil.assertPilot(sco198, 6, 371, 24, 26, 26, 26, 26, 26);
		overallAssertUtil.assertPilot(sco197, 3, 382, 25, 26, 26, 26, 26, 26);
		overallAssertUtil.assertOrder();
	}

	@Test
	public final void checkEvent5() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event5 = eventDAO.find(series, EVENT5_NAME);
			Race race16 = raceDAO.find(event5, RACE16_NAME);
			Race race17 = raceDAO.find(event5, RACE17_NAME);
			Race race18 = raceDAO.find(event5, RACE18_NAME);
			Race race19 = raceDAO.find(event5, RACE19_NAME);
			Race race20 = raceDAO.find(event5, RACE20_NAME);

			Scores scores = scorer.scoreEvent(event5, Predicates.in(getEventResultsPilots(series, event5)));
			Assert.assertEquals(EVENT5_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race16));
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race17));
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race18));
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race19));
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race20));

			RaceAssertUtil race16AssertUtil = new RaceAssertUtil(scores, race16);
			race16AssertUtil.assertPilot(sco808, 6, 0, false, 0, 1);
			race16AssertUtil.assertPilot(sco179, 6, 0, false, 2, 2);
			race16AssertUtil.assertPilot(sco116, 6, 0, false, 3, 3);
			race16AssertUtil.assertPilot(sco081, 6, 0, false, 4, 4);
			race16AssertUtil.assertPilot(sco200, 6, 0, false, 5, 5);
			race16AssertUtil.assertPilot(sco060, 5, 0, false, 6, 6);
			race16AssertUtil.assertPilot(sco087, 5, 0, false, 7, 7);
			race16AssertUtil.assertPilot(sco528, 5, 0, false, 8, 8);
			race16AssertUtil.assertPilot(sco249, 5, 0, false, 9, 9);
			race16AssertUtil.assertPilot(sco467, 5, 0, false, 10, 10);
			race16AssertUtil.assertPilot(sco159, 4, 0, false, 11, 11);
			race16AssertUtil.assertPilot(sco529, 4, 0, false, 12, 12);
			race16AssertUtil.assertPilot(sco248, 3, 0, false, 13, 13);
			race16AssertUtil.assertPilot(sco153, 3, 0, false, 14, 14);
			race16AssertUtil.assertPilot(sco320, 3, 0, false, 15, 15);
			race16AssertUtil.assertPilot(sco068, 1, 0, false, 16, 16);
			race16AssertUtil.assertDone(0);

			RaceAssertUtil race17AssertUtil = new RaceAssertUtil(scores, race17);
			race17AssertUtil.assertPilot(sco808, 4, 0, false, 0, 1);
			race17AssertUtil.assertPilot(sco179, 4, 0, false, 2, 2);
			race17AssertUtil.assertPilot(sco200, 4, 0, false, 3, 3);
			race17AssertUtil.assertPilot(sco116, 4, 0, false, 4, 4);
			race17AssertUtil.assertPilot(sco068, 4, 0, false, 5, 5);
			race17AssertUtil.assertPilot(sco081, 4, 0, false, 6, 6);
			race17AssertUtil.assertPilot(sco528, 3, 0, false, 7, 7);
			race17AssertUtil.assertPilot(sco248, 3, 0, false, 8, 8);
			race17AssertUtil.assertPilot(sco249, 2, 0, false, 9, 9);
			race17AssertUtil.assertPilot(sco060, 1, 0, false, 10, 10);
			race17AssertUtil.assertPilot(sco159, 1, 0, false, 11, 11);
			race17AssertUtil.assertPilot(sco087, 1, 0, false, 12, 12);
			race17AssertUtil.assertPilot(sco529, 1, 0, false, 13, 13);
			race17AssertUtil.assertPilot(sco467, 1, 0, false, 14, 14);
			race17AssertUtil.assertPilot(sco320, 1, 0, false, 15, 15);
			race17AssertUtil.assertPilot(sco153, 1, 0, false, 16, 16);
			race17AssertUtil.assertDone(0);

			RaceAssertUtil race18AssertUtil = new RaceAssertUtil(scores, race18);
			race18AssertUtil.assertPilot(sco200, 5, 0, false, 0, 1);
			race18AssertUtil.assertPilot(sco068, 5, 0, false, 2, 2);
			race18AssertUtil.assertPilot(sco808, 5, 0, false, 3, 3);
			race18AssertUtil.assertPilot(sco179, 5, 0, false, 4, 4);
			race18AssertUtil.assertPilot(sco081, 3, 0, false, 5, 5);
			race18AssertUtil.assertPilot(sco116, 3, 0, false, 6, 6);
			race18AssertUtil.assertPilot(sco528, 2, 0, false, 7, 7);
			race18AssertUtil.assertPilot(sco249, 2, 0, false, 8, 8);
			race18AssertUtil.assertPilot(sco159, 2, 0, false, 9, 9);
			race18AssertUtil.assertPilot(sco248, 1, 0, false, 10, 10);
			race18AssertUtil.assertPilot(sco087, 1, 0, false, 11, 11);
			race18AssertUtil.assertPilot(sco529, 1, 0, false, 12, 12);
			race18AssertUtil.assertPilot(sco467, 1, 0, false, 13, 13);
			race18AssertUtil.assertPilot(sco060, 1, 0, false, 14, 14);
			race18AssertUtil.assertPilot(sco153, 0, 0, false, 17, 15);
			race18AssertUtil.assertPilot(sco320, 0, 0, false, 17, 15);
			race18AssertUtil.assertDone(0);

			RaceAssertUtil race19AssertUtil = new RaceAssertUtil(scores, race19);
			race19AssertUtil.assertPilot(sco200, 8, 0, false, 0, 1);
			race19AssertUtil.assertPilot(sco808, 8, 0, false, 2, 2);
			race19AssertUtil.assertPilot(sco068, 7, 0, false, 3, 3);
			race19AssertUtil.assertPilot(sco179, 7, 0, false, 4, 4);
			race19AssertUtil.assertPilot(sco116, 7, 0, false, 5, 5);
			race19AssertUtil.assertPilot(sco159, 6, 0, false, 6, 6);
			race19AssertUtil.assertPilot(sco081, 6, 0, false, 7, 7);
			race19AssertUtil.assertPilot(sco467, 6, 0, false, 8, 8);
			race19AssertUtil.assertPilot(sco087, 6, 0, false, 9, 9);
			race19AssertUtil.assertPilot(sco249, 6, 0, false, 10, 10);
			race19AssertUtil.assertPilot(sco528, 5, 0, false, 11, 11);
			race19AssertUtil.assertPilot(sco529, 4, 0, false, 12, 12);
			race19AssertUtil.assertPilot(sco320, 4, 0, false, 13, 13);
			race19AssertUtil.assertPilot(sco248, 3, 0, false, 14, 14);
			race19AssertUtil.assertPilot(sco060, 2, 0, false, 15, 15);
			race19AssertUtil.assertPilot(sco153, 2, 0, false, 16, 16);
			race19AssertUtil.assertDone(0);

			RaceAssertUtil race20AssertUtil = new RaceAssertUtil(scores, race20);
			race20AssertUtil.assertPilot(sco200, 11, 0, false, 0, 1);
			race20AssertUtil.assertPilot(sco081, 10, 0, false, 2, 2);
			race20AssertUtil.assertPilot(sco808, 10, 0, false, 3, 3);
			race20AssertUtil.assertPilot(sco179, 10, 0, false, 4, 4);
			race20AssertUtil.assertPilot(sco068, 10, 0, false, 5, 5);
			race20AssertUtil.assertPilot(sco116, 10, 0, false, 6, 6);
			race20AssertUtil.assertPilot(sco159, 9, 0, false, 7, 7);
			race20AssertUtil.assertPilot(sco087, 8, 0, false, 8, 8);
			race20AssertUtil.assertPilot(sco060, 8, 0, false, 9, 9);
			race20AssertUtil.assertPilot(sco249, 7, 0, false, 10, 10);
			race20AssertUtil.assertPilot(sco528, 7, 0, false, 11, 11);
			race20AssertUtil.assertPilot(sco248, 6, 0, false, 12, 12);
			race20AssertUtil.assertPilot(sco467, 6, 0, false, 13, 13);
			race20AssertUtil.assertPilot(sco529, 6, 0, false, 14, 14);
			race20AssertUtil.assertPilot(sco320, 6, 0, false, 15, 15);
			race20AssertUtil.assertPilot(sco153, 2, 0, false, 16, 16);
			race20AssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 3, 1, 5);
			overallAssertUtil.assertPilot(sco808, 0, 5, 2, 3);
			overallAssertUtil.assertPilot(sco179, 0, 12, 3, 4);
			overallAssertUtil.assertPilot(sco068, 0, 15, 4, 16);
			overallAssertUtil.assertPilot(sco081, 0, 17, 5, 7);
			overallAssertUtil.assertPilot(sco116, 0, 18, 6, 6);
			overallAssertUtil.assertPilot(sco159, 0, 33, 7, 11);
			overallAssertUtil.assertPilot(sco528, 0, 33, 8, 11);
			overallAssertUtil.assertPilot(sco087, 0, 35, 9, 12);
			overallAssertUtil.assertPilot(sco249, 0, 36, 10, 10);
			overallAssertUtil.assertPilot(sco060, 0, 39, 11, 15);
			overallAssertUtil.assertPilot(sco248, 0, 43, 12, 14);
			overallAssertUtil.assertPilot(sco467, 0, 44, 13, 14);
			overallAssertUtil.assertPilot(sco529, 0, 49, 14, 14);
			overallAssertUtil.assertPilot(sco320, 0, 58, 15, 17);
			overallAssertUtil.assertPilot(sco153, 0, 62, 16, 17);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace16() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event5 = eventDAO.find(series, EVENT5_NAME);
			Race race16 = raceDAO.find(event5, RACE16_NAME);

			Scores scores = scorer.scoreRace(race16, Predicates.in(getEventResultsPilots(series, event5)));
			Assert.assertEquals(EVENT5_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race16));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race16);
			raceAssertUtil.assertPilot(sco808, 6, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco179, 6, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco116, 6, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco081, 6, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco200, 6, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco060, 5, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco087, 5, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco528, 5, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco249, 5, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco467, 5, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco159, 4, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco529, 4, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco248, 3, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco153, 3, 0, false, 14, 14);
			raceAssertUtil.assertPilot(sco320, 3, 0, false, 15, 15);
			raceAssertUtil.assertPilot(sco068, 1, 0, false, 16, 16);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco808, 0, 0, 1);
			overallAssertUtil.assertPilot(sco179, 0, 2, 2);
			overallAssertUtil.assertPilot(sco116, 0, 3, 3);
			overallAssertUtil.assertPilot(sco081, 0, 4, 4);
			overallAssertUtil.assertPilot(sco200, 0, 5, 5);
			overallAssertUtil.assertPilot(sco060, 0, 6, 6);
			overallAssertUtil.assertPilot(sco087, 0, 7, 7);
			overallAssertUtil.assertPilot(sco528, 0, 8, 8);
			overallAssertUtil.assertPilot(sco249, 0, 9, 9);
			overallAssertUtil.assertPilot(sco467, 0, 10, 10);
			overallAssertUtil.assertPilot(sco159, 0, 11, 11);
			overallAssertUtil.assertPilot(sco529, 0, 12, 12);
			overallAssertUtil.assertPilot(sco248, 0, 13, 13);
			overallAssertUtil.assertPilot(sco153, 0, 14, 14);
			overallAssertUtil.assertPilot(sco320, 0, 15, 15);
			overallAssertUtil.assertPilot(sco068, 0, 16, 16);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkRace17() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event5 = eventDAO.find(series, EVENT5_NAME);
			Race race17 = raceDAO.find(event5, RACE17_NAME);

			Scores scores = scorer.scoreRace(race17, Predicates.in(getEventResultsPilots(series, event5)));
			Assert.assertEquals(EVENT5_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT5_FLEET, scores.getFleetSize(race17));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race17);
			raceAssertUtil.assertPilot(sco808, 4, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco179, 4, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco200, 4, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco116, 4, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco068, 4, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco081, 4, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco528, 3, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco248, 3, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco249, 2, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco060, 1, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco159, 1, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco087, 1, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco529, 1, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco467, 1, 0, false, 14, 14);
			raceAssertUtil.assertPilot(sco320, 1, 0, false, 15, 15);
			raceAssertUtil.assertPilot(sco153, 1, 0, false, 16, 16);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco808, 0, 0, 1);
			overallAssertUtil.assertPilot(sco179, 0, 2, 2);
			overallAssertUtil.assertPilot(sco200, 0, 3, 3);
			overallAssertUtil.assertPilot(sco116, 0, 4, 4);
			overallAssertUtil.assertPilot(sco068, 0, 5, 5);
			overallAssertUtil.assertPilot(sco081, 0, 6, 6);
			overallAssertUtil.assertPilot(sco528, 0, 7, 7);
			overallAssertUtil.assertPilot(sco248, 0, 8, 8);
			overallAssertUtil.assertPilot(sco249, 0, 9, 9);
			overallAssertUtil.assertPilot(sco060, 0, 10, 10);
			overallAssertUtil.assertPilot(sco159, 0, 11, 11);
			overallAssertUtil.assertPilot(sco087, 0, 12, 12);
			overallAssertUtil.assertPilot(sco529, 0, 13, 13);
			overallAssertUtil.assertPilot(sco467, 0, 14, 14);
			overallAssertUtil.assertPilot(sco320, 0, 15, 15);
			overallAssertUtil.assertPilot(sco153, 0, 16, 16);
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
			raceAssertUtil.assertPilot(sco200, 5, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco068, 5, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco808, 5, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco179, 5, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco081, 3, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco116, 3, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco528, 2, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco249, 2, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco159, 2, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco248, 1, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco087, 1, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco529, 1, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco467, 1, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco060, 1, 0, false, 14, 14);
			raceAssertUtil.assertPilot(sco153, 0, 0, false, 17, 15);
			raceAssertUtil.assertPilot(sco320, 0, 0, false, 17, 15);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(sco068, 0, 2, 2);
			overallAssertUtil.assertPilot(sco808, 0, 3, 3);
			overallAssertUtil.assertPilot(sco179, 0, 4, 4);
			overallAssertUtil.assertPilot(sco081, 0, 5, 5);
			overallAssertUtil.assertPilot(sco116, 0, 6, 6);
			overallAssertUtil.assertPilot(sco528, 0, 7, 7);
			overallAssertUtil.assertPilot(sco249, 0, 8, 8);
			overallAssertUtil.assertPilot(sco159, 0, 9, 9);
			overallAssertUtil.assertPilot(sco248, 0, 10, 10);
			overallAssertUtil.assertPilot(sco087, 0, 11, 11);
			overallAssertUtil.assertPilot(sco529, 0, 12, 12);
			overallAssertUtil.assertPilot(sco467, 0, 13, 13);
			overallAssertUtil.assertPilot(sco060, 0, 14, 14);
			overallAssertUtil.assertPilot(sco153, 0, 17, 15);
			overallAssertUtil.assertPilot(sco320, 0, 17, 15);
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
			raceAssertUtil.assertPilot(sco200, 8, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco808, 8, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco068, 7, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco179, 7, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco116, 7, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco159, 6, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco081, 6, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco467, 6, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco087, 6, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco249, 6, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco528, 5, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco529, 4, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco320, 4, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco248, 3, 0, false, 14, 14);
			raceAssertUtil.assertPilot(sco060, 2, 0, false, 15, 15);
			raceAssertUtil.assertPilot(sco153, 2, 0, false, 16, 16);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(sco808, 0, 2, 2);
			overallAssertUtil.assertPilot(sco068, 0, 3, 3);
			overallAssertUtil.assertPilot(sco179, 0, 4, 4);
			overallAssertUtil.assertPilot(sco116, 0, 5, 5);
			overallAssertUtil.assertPilot(sco159, 0, 6, 6);
			overallAssertUtil.assertPilot(sco081, 0, 7, 7);
			overallAssertUtil.assertPilot(sco467, 0, 8, 8);
			overallAssertUtil.assertPilot(sco087, 0, 9, 9);
			overallAssertUtil.assertPilot(sco249, 0, 10, 10);
			overallAssertUtil.assertPilot(sco528, 0, 11, 11);
			overallAssertUtil.assertPilot(sco529, 0, 12, 12);
			overallAssertUtil.assertPilot(sco320, 0, 13, 13);
			overallAssertUtil.assertPilot(sco248, 0, 14, 14);
			overallAssertUtil.assertPilot(sco060, 0, 15, 15);
			overallAssertUtil.assertPilot(sco153, 0, 16, 16);
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
			raceAssertUtil.assertPilot(sco200, 11, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco081, 10, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco808, 10, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco179, 10, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco068, 10, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco116, 10, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco159, 9, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco087, 8, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco060, 8, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco249, 7, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco528, 7, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco248, 6, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco467, 6, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco529, 6, 0, false, 14, 14);
			raceAssertUtil.assertPilot(sco320, 6, 0, false, 15, 15);
			raceAssertUtil.assertPilot(sco153, 2, 0, false, 16, 16);
			raceAssertUtil.assertDone(0);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(sco081, 0, 2, 2);
			overallAssertUtil.assertPilot(sco808, 0, 3, 3);
			overallAssertUtil.assertPilot(sco179, 0, 4, 4);
			overallAssertUtil.assertPilot(sco068, 0, 5, 5);
			overallAssertUtil.assertPilot(sco116, 0, 6, 6);
			overallAssertUtil.assertPilot(sco159, 0, 7, 7);
			overallAssertUtil.assertPilot(sco087, 0, 8, 8);
			overallAssertUtil.assertPilot(sco060, 0, 9, 9);
			overallAssertUtil.assertPilot(sco249, 0, 10, 10);
			overallAssertUtil.assertPilot(sco528, 0, 11, 11);
			overallAssertUtil.assertPilot(sco248, 0, 12, 12);
			overallAssertUtil.assertPilot(sco467, 0, 13, 13);
			overallAssertUtil.assertPilot(sco529, 0, 14, 14);
			overallAssertUtil.assertPilot(sco320, 0, 15, 15);
			overallAssertUtil.assertPilot(sco153, 0, 16, 16);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}