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
 * Scores at the end of event 2 (12/02/2011 to 13/02/2011)
 */
public class Series2010Event2Scores extends Series2010Event1Scores {
	@Override
	@Before
	public void createData() throws Exception {
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

			Assert.assertEquals(SERIES_FLEET_AT_EVENT2, scorer.calculateFleetSize(series));

			List<SeriesScore> pilotScores = scorer.calculateSeriesScores(series);

			Assert.assertEquals(SERIES_FLEET_AT_EVENT2, pilotScores.size());
			Assert.fail("TODO"); //$NON-NLS-1$

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkEvent2() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);

			Assert.assertEquals(EVENT2_FLEET, scorer.calculateFleetSize(event2));

			List<EventScore> pilotScores = scorer.calculateEventScores(event2);

			Assert.assertEquals(EVENT2_FLEET, pilotScores.size());
			Assert.assertArrayEquals(new EventScore[] { new EventScore(sco200, 0, 1), new EventScore(sco159, 2, 2), new EventScore(sco068, 3, 3),
					new EventScore(sco248, 4, 4), new EventScore(sco019, 5, 5), new EventScore(sco018, 6, 6), new EventScore(sco158, 7, 7),
					new EventScore(sco060, 8, 8), new EventScore(sco087, 9, 9), new EventScore(sco116, 10, 10), new EventScore(sco249, 11, 11),
					new EventScore(sco081, 16, 12), new EventScore(sco153, 16, 12), new EventScore(sco179, 16, 12), new EventScore(sco808, 16, 12) },
					pilotScores.toArray());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace4() throws Exception {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race4 = raceDAO.find(event2, RACE4_NAME);

			Assert.assertEquals(EVENT2_FLEET, scorer.calculateFleetSize(race4));

			List<RaceScore> pilotScores = scorer.calculateRaceScores(race4);

			Assert.assertEquals(EVENT2_FLEET, pilotScores.size());
			Assert.assertArrayEquals(new RaceScore[] { new RaceScore(sco200, 0, 6, 1), new RaceScore(sco159, 2, 6, 2), new RaceScore(sco068, 3, 6, 3),
					new RaceScore(sco248, 4, 5, 4), new RaceScore(sco019, 5, 4, 5), new RaceScore(sco018, 6, 4, 6), new RaceScore(sco158, 7, 3, 7),
					new RaceScore(sco060, 8, 3, 8), new RaceScore(sco087, 9, 3, 9), new RaceScore(sco116, 10, 3, 10), new RaceScore(sco249, 11, 3, 11),
					new RaceScore(sco081, 16, 0, 12), new RaceScore(sco153, 16, 0, 12), new RaceScore(sco179, 16, 0, 12), new RaceScore(sco808, 16, 0, 12) },
					pilotScores.toArray());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}