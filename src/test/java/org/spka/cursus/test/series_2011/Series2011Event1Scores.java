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
package org.spka.cursus.test.series_2011;

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
 * Scores at the end of event 1 (29/10/2011 to 30/10/2011)
 */
public class Series2011Event1Scores extends AbstractSeries2011 {
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
			Assert.assertArrayEquals(new SeriesScore[] { new SeriesScore(sco068, 0, 1), new SeriesScore(sco200, 2, 2), new SeriesScore(sco179, 3, 3),
					new SeriesScore(sco808, 4, 4), new SeriesScore(sco116, 5, 5), new SeriesScore(sco081, 6, 6), new SeriesScore(sco136, 7, 7),
					new SeriesScore(sco248, 8, 8), new SeriesScore(sco528, 9, 9), new SeriesScore(sco467, 10, 10), new SeriesScore(sco159, 11, 11),
					new SeriesScore(sco019, 12, 12), new SeriesScore(sco018, 13, 13), new SeriesScore(sco198, 14, 14), new SeriesScore(sco087, 15, 15),
					new SeriesScore(sco197, 16, 16), new SeriesScore(sco158, 17, 17), new SeriesScore(sco320, 18, 18), new SeriesScore(sco249, 19, 19),
					new SeriesScore(sco060, 23, 20), new SeriesScore(sco153, 23, 20), new SeriesScore(sco156, 23, 20) }, pilotScores.toArray());

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
			Assert.assertArrayEquals(new EventScore[] { new EventScore(sco068, 0, 1), new EventScore(sco200, 2, 2), new EventScore(sco179, 3, 3),
					new EventScore(sco808, 4, 4), new EventScore(sco116, 5, 5), new EventScore(sco081, 6, 6), new EventScore(sco136, 7, 7),
					new EventScore(sco248, 8, 8), new EventScore(sco528, 9, 9), new EventScore(sco467, 10, 10), new EventScore(sco159, 11, 11),
					new EventScore(sco019, 12, 12), new EventScore(sco018, 13, 13), new EventScore(sco198, 14, 14), new EventScore(sco087, 15, 15),
					new EventScore(sco197, 16, 16), new EventScore(sco158, 17, 17), new EventScore(sco320, 18, 18), new EventScore(sco249, 19, 19),
					new EventScore(sco060, 23, 20), new EventScore(sco153, 23, 20), new EventScore(sco156, 23, 20) }, pilotScores.toArray());

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
			Assert.assertArrayEquals(new RaceScore[] { new RaceScore(sco068, 0, 5, 1), new RaceScore(sco200, 2, 5, 2), new RaceScore(sco179, 3, 5, 3),
					new RaceScore(sco808, 4, 5, 4), new RaceScore(sco116, 5, 5, 5), new RaceScore(sco081, 6, 5, 6), new RaceScore(sco136, 7, 4, 7),
					new RaceScore(sco248, 8, 4, 8), new RaceScore(sco528, 9, 4, 9), new RaceScore(sco467, 10, 4, 10), new RaceScore(sco159, 11, 4, 11),
					new RaceScore(sco019, 12, 3, 12), new RaceScore(sco018, 13, 3, 13), new RaceScore(sco198, 14, 3, 14), new RaceScore(sco087, 15, 3, 15),
					new RaceScore(sco197, 16, 2, 16), new RaceScore(sco158, 17, 2, 17), new RaceScore(sco320, 18, 1, 18), new RaceScore(sco249, 19, 1, 19),
					new RaceScore(sco060, 23, 0, 20), new RaceScore(sco153, 23, 0, 20), new RaceScore(sco156, 23, 0, 20) }, pilotScores.toArray());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}