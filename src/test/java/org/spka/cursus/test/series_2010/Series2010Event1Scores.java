/*
	cursus - Race series management program
	Copyright 2011  Simon Arlott

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

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.scoring.old.EventScore;
import eu.lp0.cursus.scoring.old.RaceScore;
import eu.lp0.cursus.scoring.old.SeriesScore;

/**
 * Scores at the end of event 1 (16/10/2010 to 17/10/2010)
 */
public class Series2010Event1Scores extends AbstractSeries2010 {
	@Before
	public void createData() throws Exception {
		createEvent1Races();
	}

	@Test
	public void checkSeries() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);

			Assert.assertEquals(SERIES_FLEET_AT_EVENT1, scorer.calculateFleetSize(series));

			List<SeriesScore> pilotScores = scorer.calculateSeriesScores(series);

			Assert.assertEquals(SERIES_FLEET_AT_EVENT1, pilotScores.size());
			Assert.assertArrayEquals(new SeriesScore[] { new SeriesScore(sco200, 0, 1), new SeriesScore(sco179, 9, 2), new SeriesScore(sco159, 11, 3),
					new SeriesScore(sco081, 13, 4), new SeriesScore(sco068, 14, 5), new SeriesScore(sco019, 16, 6), new SeriesScore(sco116, 19, 7),
					new SeriesScore(sco808, 21, 8), new SeriesScore(sco248, 21, 9), new SeriesScore(sco249, 26, 10), new SeriesScore(sco060, 27, 11),
					new SeriesScore(sco042, 31, 12), new SeriesScore(sco018, 32, 13), new SeriesScore(sco087, 1, 39, 14), new SeriesScore(sco153, 44, 15),
					new SeriesScore(sco197, 45, 16), new SeriesScore(sco156, 54, 17) }, pilotScores.toArray());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkEvent1() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);

			Assert.assertEquals(EVENT1_FLEET, scorer.calculateFleetSize(event1));

			List<EventScore> pilotScores = scorer.calculateEventScores(event1);

			Assert.assertEquals(EVENT1_FLEET, pilotScores.size());
			Assert.assertArrayEquals(new EventScore[] { new EventScore(sco200, 0, 1), new EventScore(sco179, 9, 2), new EventScore(sco159, 11, 3),
					new EventScore(sco081, 13, 4), new EventScore(sco068, 14, 5), new EventScore(sco019, 16, 6), new EventScore(sco116, 19, 7),
					new EventScore(sco808, 21, 8), new EventScore(sco248, 21, 9), new EventScore(sco249, 26, 10), new EventScore(sco060, 27, 11),
					new EventScore(sco042, 31, 12), new EventScore(sco018, 32, 13), new EventScore(sco087, 1, 39, 14), new EventScore(sco153, 44, 15),
					new EventScore(sco197, 45, 16), new EventScore(sco156, 54, 17) }, pilotScores.toArray());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace1() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Race race1 = raceDAO.find(event1, RACE1_NAME);

			Assert.assertEquals(EVENT1_FLEET, scorer.calculateFleetSize(race1));

			List<RaceScore> pilotScores = scorer.calculateRaceScores(race1);

			Assert.assertEquals(EVENT1_FLEET, pilotScores.size());
			Assert.assertArrayEquals(new RaceScore[] { new RaceScore(sco200, 0, 5, 1), new RaceScore(sco179, 2, 5, 2), new RaceScore(sco019, 3, 5, 3),
					new RaceScore(sco159, 4, 4, 4), new RaceScore(sco081, 5, 4, 5), new RaceScore(sco116, 6, 3, 6), new RaceScore(sco068, 7, 2, 7),
					new RaceScore(sco248, 8, 2, 8), new RaceScore(sco249, 9, 2, 9), new RaceScore(sco042, 10, 2, 10), new RaceScore(sco018, 11, 2, 11),
					new RaceScore(sco060, 12, 1, 12), new RaceScore(sco087, 18, 0, 13), new RaceScore(sco153, 18, 0, 13), new RaceScore(sco156, 18, 0, 13),
					new RaceScore(sco197, 18, 0, 13), new RaceScore(sco808, 18, 0, 13) }, pilotScores.toArray());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace2() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Race race2 = raceDAO.find(event1, RACE2_NAME);

			Assert.assertEquals(EVENT1_FLEET, scorer.calculateFleetSize(race2));

			List<RaceScore> pilotScores = scorer.calculateRaceScores(race2);

			Assert.assertEquals(EVENT1_FLEET, pilotScores.size());
			Assert.assertArrayEquals(new RaceScore[] { new RaceScore(sco808, 0, 5, 1), new RaceScore(sco019, 2, 5, 2), new RaceScore(sco068, 3, 4, 3),
					new RaceScore(sco179, 4, 4, 4), new RaceScore(sco116, 5, 4, 5), new RaceScore(sco081, 6, 3, 6), new RaceScore(sco248, 7, 3, 7),
					new RaceScore(sco249, 8, 3, 8), new RaceScore(sco018, 9, 3, 9), new RaceScore(sco060, 10, 2, 10), new RaceScore(sco042, 11, 2, 11),
					new RaceScore(sco197, 12, 2, 12), new RaceScore(sco153, 13, 2, 13), new RaceScore(sco087, 18, 0, 14), new RaceScore(sco156, 18, 0, 14),
					new RaceScore(sco159, 18, 0, 14), new RaceScore(sco200, 18, 0, 14) }, pilotScores.toArray());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace3() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Race race3 = raceDAO.find(event1, RACE3_NAME);

			Assert.assertEquals(EVENT1_FLEET, scorer.calculateFleetSize(race3));

			List<RaceScore> pilotScores = scorer.calculateRaceScores(race3);

			Assert.assertEquals(EVENT1_FLEET, pilotScores.size());
			Assert.assertArrayEquals(new RaceScore[] { new RaceScore(sco200, 0, 7, 1), new RaceScore(sco081, 2, 7, 2), new RaceScore(sco159, 3, 6, 3),
					new RaceScore(sco068, 4, 6, 4), new RaceScore(sco060, 5, 5, 5), new RaceScore(sco248, 6, 4, 6), new RaceScore(sco087, 1, 8, 4, 7),
					new RaceScore(sco116, 8, 3, 8), new RaceScore(sco249, 9, 3, 9), new RaceScore(sco042, 10, 3, 10), new RaceScore(sco019, 11, 2, 11),
					new RaceScore(sco018, 12, 1, 12), new RaceScore(sco153, 13, 1, 13), new RaceScore(sco808, 14, 1, 14), new RaceScore(sco156, 18, 0, 15),
					new RaceScore(sco179, 18, 0, 15), new RaceScore(sco197, 18, 0, 15) }, pilotScores.toArray());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}