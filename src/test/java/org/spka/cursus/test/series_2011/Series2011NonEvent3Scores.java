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
 * Scores at the end of non-event 3 (03/03/2012 to 04/03/2012)
 */
public class Series2011NonEvent3Scores extends Series2011Event2Scores {
	@Override
	@Before
	public void createData() throws Exception {
		super.createData();
		createNonEvent3Data();
	}

	@Override
	@Test
	public void checkSeries() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event nonEvent3 = eventDAO.find(series, NON_EVENT3_NAME);
			Scores scores = scorer.scoreSeries(series, getSeriesResultsPilots(series, nonEvent3), Predicates.in(getSeriesResultsPilots(series, nonEvent3)));
			checkSeriesAtNonEvent3(scores);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public final void checkSeriesAtNonEvent3() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Event nonEvent3 = eventDAO.find(series, NON_EVENT3_NAME);

			List<Race> races = new ArrayList<Race>();
			races.addAll(event1.getRaces());
			races.addAll(event2.getRaces());

			Scores scores = scorer.scoreRaces(races, getSeriesResultsPilots(series, nonEvent3), getSeriesResultsEvents(series, nonEvent3),
					Predicates.in(getSeriesResultsPilots(series, nonEvent3)));
			checkSeriesAtNonEvent3(scores);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	private void checkSeriesAtNonEvent3(Scores scores) throws Exception {
		Series series = seriesDAO.find(SERIES_NAME);
		Event event1 = eventDAO.find(series, EVENT1_NAME);
		Race race1 = raceDAO.find(event1, RACE1_NAME);
		Event event2 = eventDAO.find(series, EVENT2_NAME);
		Race race2 = raceDAO.find(event2, RACE2_NAME);
		Race race3 = raceDAO.find(event2, RACE3_NAME);
		Race race4 = raceDAO.find(event2, RACE4_NAME);

		Assert.assertEquals(SERIES_FLEET_AT_NON_EVENT3, scores.getPilots().size());
		Assert.assertEquals(SERIES_FLEET_AT_NON_EVENT3, scores.getFleetSize(race1));
		Assert.assertEquals(SERIES_FLEET_AT_NON_EVENT3, scores.getFleetSize(race2));
		Assert.assertEquals(SERIES_FLEET_AT_NON_EVENT3, scores.getFleetSize(race3));
		Assert.assertEquals(SERIES_FLEET_AT_NON_EVENT3, scores.getFleetSize(race4));

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
		race2AssertUtil.assertPilot(sco019, 1, 1, false, 13, 13); // 2, 1, 13, 13
		race2AssertUtil.assertPilot(sco153, 1, 0, false, 14, 14); // 2, 0, 14, 14
		race2AssertUtil.assertPilot(sco198, 0, 0, true, 15, 15);
		race2AssertUtil.assertPilot(sco197, 0, 0, true, 20, 16);
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
		race4AssertUtil.assertPilot(sco136, 0, 0, true, 8, 9);
		race4AssertUtil.assertPilot(sco248, 4, 0, false, 9, 10);
		race4AssertUtil.assertPilot(sco087, 4, 0, false, 10, 11);
		race4AssertUtil.assertPilot(sco081, 2, 0, false, 11, 12);
		race4AssertUtil.assertPilot(sco467, 2, 0, false, 12, 13);
		race4AssertUtil.assertPilot(sco153, 2, 0, false, 13, 14);
		race4AssertUtil.assertPilot(sco060, 0, 0, true, 18, 15);
		race4AssertUtil.assertPilot(sco018, 0, 0, false, 24, 16);
		race4AssertUtil.assertPilot(sco019, 0, 0, false, 24, 16);
		race4AssertUtil.assertPilot(sco040, 0, 0, false, 24, 16);
		race4AssertUtil.assertPilot(sco156, 0, 0, false, 24, 16);
		race4AssertUtil.assertPilot(sco158, 0, 0, false, 24, 16);
		race4AssertUtil.assertPilot(sco197, 0, 0, false, 24, 16);
		race4AssertUtil.assertPilot(sco198, 0, 0, false, 24, 16);
		race4AssertUtil.assertPilot(sco320, 0, 0, false, 24, 16);
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
		overallAssertUtil.assertPilot(sco249, 0, 23, 9, 19);
		overallAssertUtil.assertPilot(sco136, 1, 24, 10, 24);
		overallAssertUtil.assertPilot(sco248, 0, 26, 11, 10);
		overallAssertUtil.assertPilot(sco087, 0, 32, 12, 15);
		overallAssertUtil.assertPilot(sco467, 0, 32, 13, 12);
		overallAssertUtil.assertPilot(sco019, 1, 39, 14, 24);
		overallAssertUtil.assertPilot(sco153, 0, 41, 15, 24);
		overallAssertUtil.assertPilot(sco198, 3, 47, 16, 24);
		overallAssertUtil.assertPilot(sco060, 0, 54, 17, 24);
		overallAssertUtil.assertPilot(sco197, 0, 60, 18, 24);
		overallAssertUtil.assertPilot(sco018, 2, 63, 19, 24);
		overallAssertUtil.assertPilot(sco320, 0, 66, 20, 24);
		overallAssertUtil.assertPilot(sco158, 4, 69, 21, 24);
		overallAssertUtil.assertPilot(sco156, 1, 73, 22, 24);
		overallAssertUtil.assertPilot(sco040, 2, 74, 23, 24);
		overallAssertUtil.assertOrder();
	}

	@Test
	public final void checkNonEvent3() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event nonEvent3 = eventDAO.find(series, NON_EVENT3_NAME);
			Assert.assertEquals(0, nonEvent3.getRaces().size());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}