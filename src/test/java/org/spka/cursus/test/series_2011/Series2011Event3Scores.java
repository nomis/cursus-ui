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
 * Scores at the end of event 3 (31/03/2012 to 01/04/2012)
 */
public class Series2011Event3Scores extends Series2011Event2Scores {
	@Override
	@Before
	public void createData() throws Exception {
		super.createData();
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

			Scores scores = scorer.scoreRaces(races, getSeriesResultsPilots(series, event3), Predicates.in(getSeriesResultsPilots(series, event3)));
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

		Assert.assertEquals(SERIES_FLEET_AT_EVENT3, scores.getPilots().size());
		Assert.assertEquals(SERIES_FLEET_AT_EVENT3, scores.getFleetSize(race1));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT3, scores.getFleetSize(race2));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT3, scores.getFleetSize(race3));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT3, scores.getFleetSize(race4));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT3, scores.getFleetSize(race5));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT3, scores.getFleetSize(race6));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT3, scores.getFleetSize(race7));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT3, scores.getFleetSize(race8));
		Assert.assertEquals(SERIES_FLEET_AT_EVENT3, scores.getFleetSize(race9));

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
		race1AssertUtil.assertPilot(sco040, 0, 0, false, 24, 20);
		race1AssertUtil.assertPilot(sco060, 0, 0, false, 24, 20);
		race1AssertUtil.assertPilot(sco153, 0, 0, false, 24, 20);
		race1AssertUtil.assertPilot(sco156, 0, 0, false, 24, 20);
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
		race2AssertUtil.assertPilot(sco198, 0, 0, true, 21, 15);
		race2AssertUtil.assertPilot(sco197, 0, 0, true, 23, 16);
		race2AssertUtil.assertPilot(sco018, 0, 0, false, 24, 17);
		race2AssertUtil.assertPilot(sco040, 0, 0, false, 24, 17);
		race2AssertUtil.assertPilot(sco060, 0, 0, false, 24, 17);
		race2AssertUtil.assertPilot(sco136, 0, 0, false, 24, 17);
		race2AssertUtil.assertPilot(sco156, 0, 0, false, 24, 17);
		race2AssertUtil.assertPilot(sco158, 0, 0, false, 24, 17);
		race2AssertUtil.assertPilot(sco320, 0, 0, false, 24, 17);
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
		race3AssertUtil.assertPilot(sco018, 0, 0, false, 24, 18);
		race3AssertUtil.assertPilot(sco040, 0, 0, false, 24, 18);
		race3AssertUtil.assertPilot(sco156, 0, 0, false, 24, 18);
		race3AssertUtil.assertPilot(sco158, 0, 0, false, 24, 18);
		race3AssertUtil.assertPilot(sco197, 0, 0, false, 24, 18);
		race3AssertUtil.assertPilot(sco320, 0, 0, false, 24, 18);
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
		race4AssertUtil.assertPilot(sco467, 2, 0, false, 12, 12);
		race4AssertUtil.assertPilot(sco153, 2, 0, false, 13, 13);
		race4AssertUtil.assertPilot(sco060, 0, 0, true, 15, 14);
		race4AssertUtil.assertPilot(sco136, 0, 0, true, 19, 15);
		race4AssertUtil.assertPilot(sco018, 0, 0, false, 24, 16);
		race4AssertUtil.assertPilot(sco019, 0, 0, false, 24, 16);
		race4AssertUtil.assertPilot(sco040, 0, 0, false, 24, 16);
		race4AssertUtil.assertPilot(sco156, 0, 0, false, 24, 16);
		race4AssertUtil.assertPilot(sco158, 0, 0, false, 24, 16);
		race4AssertUtil.assertPilot(sco197, 0, 0, false, 24, 16);
		race4AssertUtil.assertPilot(sco198, 0, 0, false, 24, 16);
		race4AssertUtil.assertPilot(sco320, 0, 0, false, 24, 16);
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
		race5AssertUtil.assertPilot(sco018, 0, 0, true, 20, 16);
		race5AssertUtil.assertPilot(sco136, 0, 0, false, 24, 17);
		race5AssertUtil.assertPilot(sco156, 0, 0, false, 24, 17);
		race5AssertUtil.assertPilot(sco158, 0, 0, false, 24, 17);
		race5AssertUtil.assertPilot(sco197, 0, 0, false, 24, 17);
		race5AssertUtil.assertPilot(sco198, 0, 0, false, 24, 17);
		race5AssertUtil.assertPilot(sco200, 0, 0, false, 24, 17);
		race5AssertUtil.assertPilot(sco320, 0, 0, false, 24, 17);
		race5AssertUtil.assertDone(2);

		RaceAssertUtil race6AssertUtil = new RaceAssertUtil(scores, race6);
		race6AssertUtil.assertPilot(sco808, 10, 0, false, 0, 1);
		race6AssertUtil.assertPilot(sco179, 10, 0, false, 2, 2);
		race6AssertUtil.assertPilot(sco159, 9, 0, false, 3, 3);
		race6AssertUtil.assertPilot(sco019, 9, 0, false, 4, 4);
		race6AssertUtil.assertPilot(sco249, 9, 0, false, 5, 5);
		race6AssertUtil.assertPilot(sco528, 8, 0, false, 6, 6);
		race6AssertUtil.assertPilot(sco116, 0, 0, true, 6, 7);
		race6AssertUtil.assertPilot(sco087, 8, 0, false, 7, 8);
		race6AssertUtil.assertPilot(sco081, 0, 0, true, 7, 9);
		race6AssertUtil.assertPilot(sco060, 7, 0, false, 8, 10);
		race6AssertUtil.assertPilot(sco467, 7, 0, false, 9, 11);
		race6AssertUtil.assertPilot(sco248, 6, 0, false, 10, 12);
		race6AssertUtil.assertPilot(sco018, 6, 0, false, 11, 13);
		race6AssertUtil.assertPilot(sco320, 5, 0, false, 12, 14);
		race6AssertUtil.assertPilot(sco068, 3, 0, false, 13, 15);
		race6AssertUtil.assertPilot(sco153, 3, 0, false, 14, 16);
		race6AssertUtil.assertPilot(sco040, 2, 0, false, 15, 17);
		race6AssertUtil.assertPilot(sco136, 0, 0, false, 24, 18);
		race6AssertUtil.assertPilot(sco156, 0, 0, false, 24, 18);
		race6AssertUtil.assertPilot(sco158, 0, 0, false, 24, 18);
		race6AssertUtil.assertPilot(sco197, 0, 0, false, 24, 18);
		race6AssertUtil.assertPilot(sco198, 0, 0, false, 24, 18);
		race6AssertUtil.assertPilot(sco200, 0, 0, false, 24, 18);
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
		race7AssertUtil.assertPilot(sco019, 0, 0, true, 12, 14);
		race7AssertUtil.assertPilot(sco153, 1, 0, false, 13, 15);
		race7AssertUtil.assertPilot(sco018, 0, 0, false, 24, 16);
		race7AssertUtil.assertPilot(sco040, 0, 0, false, 24, 16);
		race7AssertUtil.assertPilot(sco136, 0, 0, false, 24, 16);
		race7AssertUtil.assertPilot(sco156, 0, 0, false, 24, 16);
		race7AssertUtil.assertPilot(sco158, 0, 0, false, 24, 16);
		race7AssertUtil.assertPilot(sco197, 0, 0, false, 24, 16);
		race7AssertUtil.assertPilot(sco198, 0, 0, false, 24, 16);
		race7AssertUtil.assertPilot(sco200, 0, 0, false, 24, 16);
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
		race8AssertUtil.assertPilot(sco153, 0, 0, true, 13, 15);
		race8AssertUtil.assertPilot(sco018, 0, 0, false, 24, 16);
		race8AssertUtil.assertPilot(sco019, 0, 0, false, 24, 16);
		race8AssertUtil.assertPilot(sco060, 0, 0, false, 24, 16);
		race8AssertUtil.assertPilot(sco081, 0, 0, false, 24, 16);
		race8AssertUtil.assertPilot(sco136, 0, 0, false, 24, 16);
		race8AssertUtil.assertPilot(sco158, 0, 0, false, 24, 16);
		race8AssertUtil.assertPilot(sco197, 0, 0, false, 24, 16);
		race8AssertUtil.assertPilot(sco198, 0, 0, false, 24, 16);
		race8AssertUtil.assertDone(2);

		RaceAssertUtil race9AssertUtil = new RaceAssertUtil(scores, race9);
		race9AssertUtil.assertPilot(sco200, 5, 0, false, 0, 1);
		race9AssertUtil.assertPilot(sco528, 5, 0, false, 2, 2);
		race9AssertUtil.assertPilot(sco068, 0, 0, true, 2, 3);
		race9AssertUtil.assertPilot(sco808, 5, 0, false, 3, 4);
		race9AssertUtil.assertPilot(sco248, 5, 0, false, 4, 5);
		race9AssertUtil.assertPilot(sco116, 4, 0, false, 5, 6);
		race9AssertUtil.assertPilot(sco249, 0, 0, true, 5, 7);
		race9AssertUtil.assertPilot(sco179, 4, 0, false, 6, 8);
		race9AssertUtil.assertPilot(sco159, 3, 0, false, 7, 9);
		race9AssertUtil.assertPilot(sco320, 3, 0, false, 8, 10);
		race9AssertUtil.assertPilot(sco467, 3, 0, false, 9, 11);
		race9AssertUtil.assertPilot(sco153, 2, 0, false, 10, 12);
		race9AssertUtil.assertPilot(sco087, 1, 0, false, 11, 13);
		race9AssertUtil.assertPilot(sco018, 0, 0, false, 24, 14);
		race9AssertUtil.assertPilot(sco019, 0, 0, false, 24, 14);
		race9AssertUtil.assertPilot(sco040, 0, 0, false, 24, 14);
		race9AssertUtil.assertPilot(sco060, 0, 0, false, 24, 14);
		race9AssertUtil.assertPilot(sco081, 0, 0, false, 24, 14);
		race9AssertUtil.assertPilot(sco136, 0, 0, false, 24, 14);
		race9AssertUtil.assertPilot(sco156, 0, 0, false, 24, 14);
		race9AssertUtil.assertPilot(sco158, 0, 0, false, 24, 14);
		race9AssertUtil.assertPilot(sco197, 0, 0, false, 24, 14);
		race9AssertUtil.assertPilot(sco198, 0, 0, false, 24, 14);
		race9AssertUtil.assertDone(2);

		OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
		overallAssertUtil.assertPilot(sco068, 0, 12, 1, 13, 4);
		overallAssertUtil.assertPilot(sco808, 1, 17, 2, 5, 4);
		overallAssertUtil.assertPilot(sco179, 0, 21, 3, 8, 7);
		overallAssertUtil.assertPilot(sco200, 0, 28, 4, 24, 24);
		overallAssertUtil.assertPilot(sco159, 0, 35, 5, 11, 9);
		overallAssertUtil.assertPilot(sco249, 0, 37, 6, 19, 8);
		overallAssertUtil.assertPilot(sco528, 0, 38, 7, 9, 7);
		overallAssertUtil.assertPilot(sco116, 0, 39, 8, 12, 9);
		overallAssertUtil.assertPilot(sco248, 0, 47, 9, 10, 10);
		overallAssertUtil.assertPilot(sco081, 0, 50, 10, 24, 24);
		overallAssertUtil.assertPilot(sco087, 0, 63, 11, 15, 12);
		overallAssertUtil.assertPilot(sco467, 0, 65, 12, 12, 11);
		overallAssertUtil.assertPilot(sco019, 1, 83, 13, 24, 24);
		overallAssertUtil.assertPilot(sco153, 0, 89, 14, 24, 14);
		overallAssertUtil.assertPilot(sco320, 0, 107, 15, 24, 24);
		overallAssertUtil.assertPilot(sco060, 0, 107, 16, 24, 24);
		overallAssertUtil.assertPilot(sco136, 1, 131, 17, 24, 24);
		overallAssertUtil.assertPilot(sco040, 2, 139, 18, 24, 24);
		overallAssertUtil.assertPilot(sco018, 1, 141, 19, 24, 24);
		overallAssertUtil.assertPilot(sco198, 1, 147, 20, 24, 24);
		overallAssertUtil.assertPilot(sco156, 1, 158, 21, 24, 24);
		overallAssertUtil.assertPilot(sco197, 1, 160, 22, 24, 24);
		overallAssertUtil.assertPilot(sco158, 2, 163, 23, 24, 24);
		overallAssertUtil.assertOrder();
	}

	@Test
	public final void checkEvent3() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Race race5 = raceDAO.find(event3, RACE5_NAME);
			Race race6 = raceDAO.find(event3, RACE6_NAME);
			Race race7 = raceDAO.find(event3, RACE7_NAME);
			Race race8 = raceDAO.find(event3, RACE8_NAME);
			Race race9 = raceDAO.find(event3, RACE9_NAME);

			Scores scores = scorer.scoreEvent(event3, Predicates.in(getEventResultsPilots(series, event3)));
			Assert.assertEquals(EVENT3_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT3_FLEET, scores.getFleetSize(race5));
			Assert.assertEquals(EVENT3_FLEET, scores.getFleetSize(race6));
			Assert.assertEquals(EVENT3_FLEET, scores.getFleetSize(race7));
			Assert.assertEquals(EVENT3_FLEET, scores.getFleetSize(race8));
			Assert.assertEquals(EVENT3_FLEET, scores.getFleetSize(race9));

			RaceAssertUtil race5AssertUtil = new RaceAssertUtil(scores, race5);
			race5AssertUtil.assertPilot(sco179, 6, 0, false, 0, 1);
			race5AssertUtil.assertPilot(sco068, 5, 0, false, 2, 2);
			race5AssertUtil.assertPilot(sco116, 5, 0, false, 3, 3);
			race5AssertUtil.assertPilot(sco019, 5, 0, false, 4, 4);
			race5AssertUtil.assertPilot(sco808, 5, 0, false, 5, 5);
			race5AssertUtil.assertPilot(sco248, 0, 0, true, 5, 6);
			race5AssertUtil.assertPilot(sco528, 5, 0, false, 6, 7);
			race5AssertUtil.assertPilot(sco249, 4, 0, false, 7, 8);
			race5AssertUtil.assertPilot(sco087, 4, 0, false, 8, 9);
			race5AssertUtil.assertPilot(sco159, 4, 0, false, 9, 10);
			race5AssertUtil.assertPilot(sco081, 3, 0, false, 10, 11);
			race5AssertUtil.assertPilot(sco467, 3, 0, false, 11, 12);
			race5AssertUtil.assertPilot(sco153, 3, 0, false, 12, 13);
			race5AssertUtil.assertPilot(sco060, 1, 0, false, 13, 14);
			race5AssertUtil.assertPilot(sco040, 1, 0, false, 14, 15);
			race5AssertUtil.assertPilot(sco018, 0, 0, true, 17, 16);
			race5AssertUtil.assertPilot(sco156, 0, 0, false, 20, 17);
			race5AssertUtil.assertPilot(sco200, 0, 0, false, 20, 17);
			race5AssertUtil.assertPilot(sco320, 0, 0, false, 20, 17);
			race5AssertUtil.assertDone(2);

			RaceAssertUtil race6AssertUtil = new RaceAssertUtil(scores, race6);
			race6AssertUtil.assertPilot(sco808, 10, 0, false, 0, 1);
			race6AssertUtil.assertPilot(sco179, 10, 0, false, 2, 2);
			race6AssertUtil.assertPilot(sco159, 9, 0, false, 3, 3);
			race6AssertUtil.assertPilot(sco019, 9, 0, false, 4, 4);
			race6AssertUtil.assertPilot(sco249, 9, 0, false, 5, 5);
			race6AssertUtil.assertPilot(sco528, 8, 0, false, 6, 6);
			race6AssertUtil.assertPilot(sco116, 0, 0, true, 6, 7);
			race6AssertUtil.assertPilot(sco087, 8, 0, false, 7, 8);
			race6AssertUtil.assertPilot(sco060, 7, 0, false, 8, 9);
			race6AssertUtil.assertPilot(sco467, 7, 0, false, 9, 10);
			race6AssertUtil.assertPilot(sco248, 6, 0, false, 10, 11);
			race6AssertUtil.assertPilot(sco018, 6, 0, false, 11, 12);
			race6AssertUtil.assertPilot(sco320, 5, 0, false, 12, 13);
			race6AssertUtil.assertPilot(sco068, 3, 0, false, 13, 14);
			race6AssertUtil.assertPilot(sco081, 0, 0, true, 13, 15);
			race6AssertUtil.assertPilot(sco153, 3, 0, false, 14, 16);
			race6AssertUtil.assertPilot(sco040, 2, 0, false, 15, 17);
			race6AssertUtil.assertPilot(sco156, 0, 0, false, 20, 18);
			race6AssertUtil.assertPilot(sco200, 0, 0, false, 20, 18);
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
			race7AssertUtil.assertPilot(sco019, 0, 0, true, 9, 11);
			race7AssertUtil.assertPilot(sco320, 1, 0, false, 10, 12);
			race7AssertUtil.assertPilot(sco060, 1, 0, false, 11, 13);
			race7AssertUtil.assertPilot(sco116, 1, 0, false, 12, 14);
			race7AssertUtil.assertPilot(sco153, 1, 0, false, 13, 15);
			race7AssertUtil.assertPilot(sco018, 0, 0, false, 20, 16);
			race7AssertUtil.assertPilot(sco040, 0, 0, false, 20, 16);
			race7AssertUtil.assertPilot(sco156, 0, 0, false, 20, 16);
			race7AssertUtil.assertPilot(sco200, 0, 0, false, 20, 16);
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
			race8AssertUtil.assertPilot(sco087, 0, 0, true, 8, 9);
			race8AssertUtil.assertPilot(sco116, 5, 0, false, 9, 10);
			race8AssertUtil.assertPilot(sco467, 5, 0, false, 10, 11);
			race8AssertUtil.assertPilot(sco320, 4, 0, false, 11, 12);
			race8AssertUtil.assertPilot(sco040, 4, 0, false, 12, 13);
			race8AssertUtil.assertPilot(sco153, 0, 0, true, 12, 14);
			race8AssertUtil.assertPilot(sco156, 3, 0, false, 13, 15);
			race8AssertUtil.assertPilot(sco018, 0, 0, false, 20, 16);
			race8AssertUtil.assertPilot(sco019, 0, 0, false, 20, 16);
			race8AssertUtil.assertPilot(sco060, 0, 0, false, 20, 16);
			race8AssertUtil.assertPilot(sco081, 0, 0, false, 20, 16);
			race8AssertUtil.assertDone(2);

			RaceAssertUtil race9AssertUtil = new RaceAssertUtil(scores, race9);
			race9AssertUtil.assertPilot(sco200, 5, 0, false, 0, 1);
			race9AssertUtil.assertPilot(sco068, 0, 0, true, 1, 2);
			race9AssertUtil.assertPilot(sco528, 5, 0, false, 2, 3);
			race9AssertUtil.assertPilot(sco808, 5, 0, false, 3, 4);
			race9AssertUtil.assertPilot(sco249, 0, 0, true, 3, 5);
			race9AssertUtil.assertPilot(sco248, 5, 0, false, 4, 6);
			race9AssertUtil.assertPilot(sco116, 4, 0, false, 5, 7);
			race9AssertUtil.assertPilot(sco179, 4, 0, false, 6, 8);
			race9AssertUtil.assertPilot(sco159, 3, 0, false, 7, 9);
			race9AssertUtil.assertPilot(sco320, 3, 0, false, 8, 10);
			race9AssertUtil.assertPilot(sco467, 3, 0, false, 9, 11);
			race9AssertUtil.assertPilot(sco153, 2, 0, false, 10, 12);
			race9AssertUtil.assertPilot(sco087, 1, 0, false, 11, 13);
			race9AssertUtil.assertPilot(sco018, 0, 0, false, 20, 14);
			race9AssertUtil.assertPilot(sco019, 0, 0, false, 20, 14);
			race9AssertUtil.assertPilot(sco040, 0, 0, false, 20, 14);
			race9AssertUtil.assertPilot(sco060, 0, 0, false, 20, 14);
			race9AssertUtil.assertPilot(sco081, 0, 0, false, 20, 14);
			race9AssertUtil.assertPilot(sco156, 0, 0, false, 20, 14);
			race9AssertUtil.assertDone(2);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco068, 0, 5, 1, 13);
			overallAssertUtil.assertPilot(sco808, 0, 10, 2, 5);
			overallAssertUtil.assertPilot(sco249, 0, 13, 3, 7);
			overallAssertUtil.assertPilot(sco179, 0, 15, 4, 8);
			overallAssertUtil.assertPilot(sco159, 0, 19, 5, 9);
			overallAssertUtil.assertPilot(sco248, 0, 19, 5, 10);
			overallAssertUtil.assertPilot(sco528, 0, 20, 7, 6);
			overallAssertUtil.assertPilot(sco116, 0, 23, 8, 12);
			overallAssertUtil.assertPilot(sco087, 0, 31, 9, 11);
			overallAssertUtil.assertPilot(sco467, 0, 33, 10, 11);
			overallAssertUtil.assertPilot(sco019, 0, 37, 11, 20);
			overallAssertUtil.assertPilot(sco320, 0, 41, 12, 20);
			overallAssertUtil.assertPilot(sco200, 0, 42, 13, 20);
			overallAssertUtil.assertPilot(sco153, 0, 47, 14, 14);
			overallAssertUtil.assertPilot(sco060, 0, 52, 15, 20);
			overallAssertUtil.assertPilot(sco081, 0, 52, 16, 20);
			overallAssertUtil.assertPilot(sco040, 0, 61, 17, 20);
			overallAssertUtil.assertPilot(sco018, 0, 68, 18, 20);
			overallAssertUtil.assertPilot(sco156, 0, 73, 19, 20);
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
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Race race5 = raceDAO.find(event3, RACE5_NAME);

			Scores scores = scorer.scoreRace(race5, Predicates.in(getEventResultsPilots(series, event3)));
			Assert.assertEquals(EVENT3_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT3_FLEET, scores.getFleetSize(race5));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race5);
			raceAssertUtil.assertPilot(sco179, 6, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco068, 5, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco116, 5, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco019, 5, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco808, 5, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco528, 5, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco249, 4, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco087, 4, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco159, 4, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco081, 3, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco467, 3, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco153, 3, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco060, 1, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco040, 1, 0, false, 14, 14);
			raceAssertUtil.assertPilot(sco018, 0, 0, true, 20, 15);
			raceAssertUtil.assertPilot(sco156, 0, 0, false, 20, 15);
			raceAssertUtil.assertPilot(sco200, 0, 0, false, 20, 15);
			raceAssertUtil.assertPilot(sco248, 0, 0, true, 20, 15);
			raceAssertUtil.assertPilot(sco320, 0, 0, false, 20, 15);
			raceAssertUtil.assertDone(2);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco179, 0, 0, 1);
			overallAssertUtil.assertPilot(sco068, 0, 2, 2);
			overallAssertUtil.assertPilot(sco116, 0, 3, 3);
			overallAssertUtil.assertPilot(sco019, 0, 4, 4);
			overallAssertUtil.assertPilot(sco808, 0, 5, 5);
			overallAssertUtil.assertPilot(sco528, 0, 6, 6);
			overallAssertUtil.assertPilot(sco249, 0, 7, 7);
			overallAssertUtil.assertPilot(sco087, 0, 8, 8);
			overallAssertUtil.assertPilot(sco159, 0, 9, 9);
			overallAssertUtil.assertPilot(sco081, 0, 10, 10);
			overallAssertUtil.assertPilot(sco467, 0, 11, 11);
			overallAssertUtil.assertPilot(sco153, 0, 12, 12);
			overallAssertUtil.assertPilot(sco060, 0, 13, 13);
			overallAssertUtil.assertPilot(sco040, 0, 14, 14);
			overallAssertUtil.assertPilot(sco018, 0, 20, 15);
			overallAssertUtil.assertPilot(sco156, 0, 20, 15);
			overallAssertUtil.assertPilot(sco200, 0, 20, 15);
			overallAssertUtil.assertPilot(sco248, 0, 20, 15);
			overallAssertUtil.assertPilot(sco320, 0, 20, 15);
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
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Race race6 = raceDAO.find(event3, RACE6_NAME);

			Scores scores = scorer.scoreRace(race6, Predicates.in(getEventResultsPilots(series, event3)));
			Assert.assertEquals(EVENT3_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT3_FLEET, scores.getFleetSize(race6));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race6);
			raceAssertUtil.assertPilot(sco808, 10, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco179, 10, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco159, 9, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco019, 9, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco249, 9, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco528, 8, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco087, 8, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco060, 7, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco467, 7, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco248, 6, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco018, 6, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco320, 5, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco068, 3, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco153, 3, 0, false, 14, 14);
			raceAssertUtil.assertPilot(sco040, 2, 0, false, 15, 15);
			raceAssertUtil.assertPilot(sco081, 0, 0, true, 20, 16);
			raceAssertUtil.assertPilot(sco116, 0, 0, true, 20, 16);
			raceAssertUtil.assertPilot(sco156, 0, 0, false, 20, 16);
			raceAssertUtil.assertPilot(sco200, 0, 0, false, 20, 16);
			raceAssertUtil.assertDone(2);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco808, 0, 0, 1);
			overallAssertUtil.assertPilot(sco179, 0, 2, 2);
			overallAssertUtil.assertPilot(sco159, 0, 3, 3);
			overallAssertUtil.assertPilot(sco019, 0, 4, 4);
			overallAssertUtil.assertPilot(sco249, 0, 5, 5);
			overallAssertUtil.assertPilot(sco528, 0, 6, 6);
			overallAssertUtil.assertPilot(sco087, 0, 7, 7);
			overallAssertUtil.assertPilot(sco060, 0, 8, 8);
			overallAssertUtil.assertPilot(sco467, 0, 9, 9);
			overallAssertUtil.assertPilot(sco248, 0, 10, 10);
			overallAssertUtil.assertPilot(sco018, 0, 11, 11);
			overallAssertUtil.assertPilot(sco320, 0, 12, 12);
			overallAssertUtil.assertPilot(sco068, 0, 13, 13);
			overallAssertUtil.assertPilot(sco153, 0, 14, 14);
			overallAssertUtil.assertPilot(sco040, 0, 15, 15);
			overallAssertUtil.assertPilot(sco081, 0, 20, 16);
			overallAssertUtil.assertPilot(sco116, 0, 20, 16);
			overallAssertUtil.assertPilot(sco156, 0, 20, 16);
			overallAssertUtil.assertPilot(sco200, 0, 20, 16);
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
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Race race7 = raceDAO.find(event3, RACE7_NAME);

			Scores scores = scorer.scoreRace(race7, Predicates.in(getEventResultsPilots(series, event3)));
			Assert.assertEquals(EVENT3_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT3_FLEET, scores.getFleetSize(race7));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race7);
			raceAssertUtil.assertPilot(sco249, 4, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco068, 4, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco248, 4, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco808, 4, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco467, 4, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco528, 3, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco179, 3, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco087, 2, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco081, 2, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco320, 1, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco060, 1, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco116, 1, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco153, 1, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco018, 0, 0, false, 20, 14);
			raceAssertUtil.assertPilot(sco019, 0, 0, true, 20, 14);
			raceAssertUtil.assertPilot(sco040, 0, 0, false, 20, 14);
			raceAssertUtil.assertPilot(sco156, 0, 0, false, 20, 14);
			raceAssertUtil.assertPilot(sco159, 0, 0, true, 20, 14);
			raceAssertUtil.assertPilot(sco200, 0, 0, false, 20, 14);
			raceAssertUtil.assertDone(2);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco249, 0, 0, 1);
			overallAssertUtil.assertPilot(sco068, 0, 2, 2);
			overallAssertUtil.assertPilot(sco248, 0, 3, 3);
			overallAssertUtil.assertPilot(sco808, 0, 4, 4);
			overallAssertUtil.assertPilot(sco467, 0, 5, 5);
			overallAssertUtil.assertPilot(sco528, 0, 6, 6);
			overallAssertUtil.assertPilot(sco179, 0, 7, 7);
			overallAssertUtil.assertPilot(sco087, 0, 8, 8);
			overallAssertUtil.assertPilot(sco081, 0, 9, 9);
			overallAssertUtil.assertPilot(sco320, 0, 10, 10);
			overallAssertUtil.assertPilot(sco060, 0, 11, 11);
			overallAssertUtil.assertPilot(sco116, 0, 12, 12);
			overallAssertUtil.assertPilot(sco153, 0, 13, 13);
			overallAssertUtil.assertPilot(sco018, 0, 20, 14);
			overallAssertUtil.assertPilot(sco019, 0, 20, 14);
			overallAssertUtil.assertPilot(sco040, 0, 20, 14);
			overallAssertUtil.assertPilot(sco156, 0, 20, 14);
			overallAssertUtil.assertPilot(sco159, 0, 20, 14);
			overallAssertUtil.assertPilot(sco200, 0, 20, 14);
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
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Race race8 = raceDAO.find(event3, RACE8_NAME);

			Scores scores = scorer.scoreRace(race8, Predicates.in(getEventResultsPilots(series, event3)));
			Assert.assertEquals(EVENT3_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT3_FLEET, scores.getFleetSize(race8));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race8);
			raceAssertUtil.assertPilot(sco068, 7, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco200, 7, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco808, 6, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco159, 6, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco249, 6, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco528, 6, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco248, 6, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco179, 5, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco116, 5, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco467, 5, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco320, 4, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco040, 4, 0, false, 12, 12);
			raceAssertUtil.assertPilot(sco156, 3, 0, false, 13, 13);
			raceAssertUtil.assertPilot(sco018, 0, 0, false, 20, 14);
			raceAssertUtil.assertPilot(sco019, 0, 0, false, 20, 14);
			raceAssertUtil.assertPilot(sco060, 0, 0, false, 20, 14);
			raceAssertUtil.assertPilot(sco081, 0, 0, false, 20, 14);
			raceAssertUtil.assertPilot(sco087, 0, 0, true, 20, 14);
			raceAssertUtil.assertPilot(sco153, 0, 0, true, 20, 14);
			raceAssertUtil.assertDone(2);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco068, 0, 0, 1);
			overallAssertUtil.assertPilot(sco200, 0, 2, 2);
			overallAssertUtil.assertPilot(sco808, 0, 3, 3);
			overallAssertUtil.assertPilot(sco159, 0, 4, 4);
			overallAssertUtil.assertPilot(sco249, 0, 5, 5);
			overallAssertUtil.assertPilot(sco528, 0, 6, 6);
			overallAssertUtil.assertPilot(sco248, 0, 7, 7);
			overallAssertUtil.assertPilot(sco179, 0, 8, 8);
			overallAssertUtil.assertPilot(sco116, 0, 9, 9);
			overallAssertUtil.assertPilot(sco467, 0, 10, 10);
			overallAssertUtil.assertPilot(sco320, 0, 11, 11);
			overallAssertUtil.assertPilot(sco040, 0, 12, 12);
			overallAssertUtil.assertPilot(sco156, 0, 13, 13);
			overallAssertUtil.assertPilot(sco018, 0, 20, 14);
			overallAssertUtil.assertPilot(sco019, 0, 20, 14);
			overallAssertUtil.assertPilot(sco060, 0, 20, 14);
			overallAssertUtil.assertPilot(sco081, 0, 20, 14);
			overallAssertUtil.assertPilot(sco087, 0, 20, 14);
			overallAssertUtil.assertPilot(sco153, 0, 20, 14);
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
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Race race9 = raceDAO.find(event3, RACE9_NAME);

			Scores scores = scorer.scoreRace(race9, Predicates.in(getEventResultsPilots(series, event3)));
			Assert.assertEquals(EVENT3_FLEET, scores.getPilots().size());
			Assert.assertEquals(EVENT3_FLEET, scores.getFleetSize(race9));

			RaceAssertUtil raceAssertUtil = new RaceAssertUtil(scores, race9);
			raceAssertUtil.assertPilot(sco200, 5, 0, false, 0, 1);
			raceAssertUtil.assertPilot(sco528, 5, 0, false, 2, 2);
			raceAssertUtil.assertPilot(sco808, 5, 0, false, 3, 3);
			raceAssertUtil.assertPilot(sco248, 5, 0, false, 4, 4);
			raceAssertUtil.assertPilot(sco116, 4, 0, false, 5, 5);
			raceAssertUtil.assertPilot(sco179, 4, 0, false, 6, 6);
			raceAssertUtil.assertPilot(sco159, 3, 0, false, 7, 7);
			raceAssertUtil.assertPilot(sco320, 3, 0, false, 8, 8);
			raceAssertUtil.assertPilot(sco467, 3, 0, false, 9, 9);
			raceAssertUtil.assertPilot(sco153, 2, 0, false, 10, 10);
			raceAssertUtil.assertPilot(sco087, 1, 0, false, 11, 11);
			raceAssertUtil.assertPilot(sco018, 0, 0, false, 20, 12);
			raceAssertUtil.assertPilot(sco019, 0, 0, false, 20, 12);
			raceAssertUtil.assertPilot(sco040, 0, 0, false, 20, 12);
			raceAssertUtil.assertPilot(sco060, 0, 0, false, 20, 12);
			raceAssertUtil.assertPilot(sco068, 0, 0, true, 20, 12);
			raceAssertUtil.assertPilot(sco081, 0, 0, false, 20, 12);
			raceAssertUtil.assertPilot(sco156, 0, 0, false, 20, 12);
			raceAssertUtil.assertPilot(sco249, 0, 0, true, 20, 12);
			raceAssertUtil.assertDone(2);

			OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
			overallAssertUtil.assertPilot(sco200, 0, 0, 1);
			overallAssertUtil.assertPilot(sco528, 0, 2, 2);
			overallAssertUtil.assertPilot(sco808, 0, 3, 3);
			overallAssertUtil.assertPilot(sco248, 0, 4, 4);
			overallAssertUtil.assertPilot(sco116, 0, 5, 5);
			overallAssertUtil.assertPilot(sco179, 0, 6, 6);
			overallAssertUtil.assertPilot(sco159, 0, 7, 7);
			overallAssertUtil.assertPilot(sco320, 0, 8, 8);
			overallAssertUtil.assertPilot(sco467, 0, 9, 9);
			overallAssertUtil.assertPilot(sco153, 0, 10, 10);
			overallAssertUtil.assertPilot(sco087, 0, 11, 11);
			overallAssertUtil.assertPilot(sco018, 0, 20, 12);
			overallAssertUtil.assertPilot(sco019, 0, 20, 12);
			overallAssertUtil.assertPilot(sco040, 0, 20, 12);
			overallAssertUtil.assertPilot(sco060, 0, 20, 12);
			overallAssertUtil.assertPilot(sco068, 0, 20, 12);
			overallAssertUtil.assertPilot(sco081, 0, 20, 12);
			overallAssertUtil.assertPilot(sco156, 0, 20, 12);
			overallAssertUtil.assertPilot(sco249, 0, 20, 12);
			overallAssertUtil.assertOrder();

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}