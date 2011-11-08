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
import eu.lp0.cursus.scoring.EventScore;
import eu.lp0.cursus.scoring.RaceScore;
import eu.lp0.cursus.scoring.SeriesScore;

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

			Assert.assertEquals(SERIES_FLEET_AT_EVENT4, scorer.calculateFleetSize(series));

			List<SeriesScore> pilotScores = scorer.calculateSeriesScores(series);

			Assert.assertEquals(SERIES_FLEET_AT_EVENT4, pilotScores.size());
			Assert.fail("TODO"); //$NON-NLS-1$

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkEvent4() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event4 = eventDAO.find(series, EVENT4_NAME);

			Assert.assertEquals(EVENT4_FLEET, scorer.calculateFleetSize(event4));

			List<EventScore> pilotScores = scorer.calculateEventScores(event4);

			Assert.assertEquals(EVENT4_FLEET, pilotScores.size());
			Assert.assertArrayEquals(new EventScore[] { new EventScore(sco808, 9, 1), new EventScore(sco068, 13, 2), new EventScore(sco179, 14, 3),
					new EventScore(sco159, 14, 3), new EventScore(sco081, 17, 5), new EventScore(sco200, 21, 6), new EventScore(sco248, 21, 6),
					new EventScore(sco087, 21, 6), new EventScore(sco249, 27, 9), new EventScore(sco116, 27, 9), new EventScore(sco136, 31, 11),
					new EventScore(sco018, 33, 12), new EventScore(sco153, 39, 13), new EventScore(sco019, 41, 14), new EventScore(sco156, 47, 15) },
					pilotScores.toArray());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace6() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event4 = eventDAO.find(series, EVENT4_NAME);
			Race race6 = raceDAO.find(event4, RACE6_NAME);

			Assert.assertEquals(EVENT4_FLEET, scorer.calculateFleetSize(race6));

			List<RaceScore> pilotScores = scorer.calculateRaceScores(race6);

			Assert.assertEquals(EVENT4_FLEET, pilotScores.size());
			Assert.assertArrayEquals(new RaceScore[] { new RaceScore(sco068, 0, 6, 1), new RaceScore(sco179, 2, 6, 2), new RaceScore(sco808, 3, 6, 3),
					new RaceScore(sco159, 4, 6, 4), new RaceScore(sco200, 5, 6, 5), new RaceScore(sco081, 6, 6, 6), new RaceScore(sco116, 7, 6, 7),
					new RaceScore(sco087, 8, 6, 8), new RaceScore(sco136, 9, 6, 9), new RaceScore(sco248, 10, 6, 10), new RaceScore(sco249, 11, 5, 11),
					new RaceScore(sco019, 12, 4, 12), new RaceScore(sco018, 13, 2, 13), new RaceScore(sco153, 16, 0, 14), new RaceScore(sco156, 16, 0, 14) },
					pilotScores.toArray());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace7() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event4 = eventDAO.find(series, EVENT4_NAME);
			Race race7 = raceDAO.find(event4, RACE7_NAME);

			Assert.assertEquals(EVENT4_FLEET, scorer.calculateFleetSize(race7));

			List<RaceScore> pilotScores = scorer.calculateRaceScores(race7);

			Assert.assertEquals(EVENT4_FLEET, pilotScores.size());
			Assert.assertArrayEquals(new RaceScore[] { new RaceScore(sco200, 0, 4, 1), new RaceScore(sco808, 2, 4, 2), new RaceScore(sco159, 3, 4, 3),
					new RaceScore(sco018, 4, 4, 4), new RaceScore(sco087, 5, 4, 5), new RaceScore(sco136, 6, 4, 6), new RaceScore(sco068, 7, 4, 7),
					new RaceScore(sco248, 8, 4, 8), new RaceScore(sco081, 9, 4, 9), new RaceScore(sco116, 10, 4, 10), new RaceScore(sco249, 11, 4, 11),
					new RaceScore(sco179, 12, 3, 12), new RaceScore(sco019, 13, 3, 13), new RaceScore(sco153, 14, 2, 14), new RaceScore(sco156, 15, 1, 15) },
					pilotScores.toArray());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace8() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event4 = eventDAO.find(series, EVENT4_NAME);
			Race race8 = raceDAO.find(event4, RACE8_NAME);

			Assert.assertEquals(EVENT4_FLEET, scorer.calculateFleetSize(race8));

			List<RaceScore> pilotScores = scorer.calculateRaceScores(race8);

			Assert.assertEquals(EVENT4_FLEET, pilotScores.size());
			Assert.assertArrayEquals(new RaceScore[] { new RaceScore(sco179, 0, 8, 1), new RaceScore(sco081, 2, 8, 2), new RaceScore(sco248, 3, 8, 3),
					new RaceScore(sco808, 4, 7, 4), new RaceScore(sco249, 5, 6, 5), new RaceScore(sco068, 6, 5, 6), new RaceScore(sco159, 7, 4, 7),
					new RaceScore(sco087, 8, 4, 8), new RaceScore(sco153, 9, 3, 9), new RaceScore(sco116, 10, 2, 10), new RaceScore(sco018, 16, 0, 11),
					new RaceScore(sco019, 16, 0, 11), new RaceScore(sco136, 16, 0, 11), new RaceScore(sco156, 16, 0, 11), new RaceScore(sco200, 16, 0, 11) },
					pilotScores.toArray());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}