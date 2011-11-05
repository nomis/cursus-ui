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
package org.spka.cursus.test;

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

			Assert.assertEquals(SERIES_FLEET, scorer.calculateFleetSize(series));

			List<SeriesScore> pilotScores = scorer.calculateSeriesScores(series);

			Assert.assertEquals(SERIES_FLEET, pilotScores.size());
			Assert.assertEquals(new SeriesScore(sco068, 0, 1), pilotScores.get(0));
			Assert.assertEquals(new SeriesScore(sco200, 2, 2), pilotScores.get(1));
			Assert.assertEquals(new SeriesScore(sco179, 3, 3), pilotScores.get(2));
			Assert.assertEquals(new SeriesScore(sco808, 4, 4), pilotScores.get(3));
			Assert.assertEquals(new SeriesScore(sco116, 5, 5), pilotScores.get(4));
			Assert.assertEquals(new SeriesScore(sco081, 6, 6), pilotScores.get(5));
			Assert.assertEquals(new SeriesScore(sco136, 7, 7), pilotScores.get(6));
			Assert.assertEquals(new SeriesScore(sco248, 8, 8), pilotScores.get(7));
			Assert.assertEquals(new SeriesScore(sco528, 9, 9), pilotScores.get(8));
			Assert.assertEquals(new SeriesScore(sco467, 10, 10), pilotScores.get(9));
			Assert.assertEquals(new SeriesScore(sco159, 11, 11), pilotScores.get(10));
			Assert.assertEquals(new SeriesScore(sco019, 12, 12), pilotScores.get(11));
			Assert.assertEquals(new SeriesScore(sco018, 13, 13), pilotScores.get(12));
			Assert.assertEquals(new SeriesScore(sco198, 14, 14), pilotScores.get(13));
			Assert.assertEquals(new SeriesScore(sco087, 15, 15), pilotScores.get(14));
			Assert.assertEquals(new SeriesScore(sco197, 16, 16), pilotScores.get(15));
			Assert.assertEquals(new SeriesScore(sco158, 17, 17), pilotScores.get(16));
			Assert.assertEquals(new SeriesScore(sco320, 18, 18), pilotScores.get(17));
			Assert.assertEquals(new SeriesScore(sco249, 19, 19), pilotScores.get(18));
			Assert.assertEquals(new SeriesScore(sco060, 23, 20), pilotScores.get(19));
			Assert.assertEquals(new SeriesScore(sco153, 23, 20), pilotScores.get(20));
			Assert.assertEquals(new SeriesScore(sco156, 23, 20), pilotScores.get(21));

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
			Assert.assertEquals(new EventScore(sco068, 0, 1), pilotScores.get(0));
			Assert.assertEquals(new EventScore(sco200, 2, 2), pilotScores.get(1));
			Assert.assertEquals(new EventScore(sco179, 3, 3), pilotScores.get(2));
			Assert.assertEquals(new EventScore(sco808, 4, 4), pilotScores.get(3));
			Assert.assertEquals(new EventScore(sco116, 5, 5), pilotScores.get(4));
			Assert.assertEquals(new EventScore(sco081, 6, 6), pilotScores.get(5));
			Assert.assertEquals(new EventScore(sco136, 7, 7), pilotScores.get(6));
			Assert.assertEquals(new EventScore(sco248, 8, 8), pilotScores.get(7));
			Assert.assertEquals(new EventScore(sco528, 9, 9), pilotScores.get(8));
			Assert.assertEquals(new EventScore(sco467, 10, 10), pilotScores.get(9));
			Assert.assertEquals(new EventScore(sco159, 11, 11), pilotScores.get(10));
			Assert.assertEquals(new EventScore(sco019, 12, 12), pilotScores.get(11));
			Assert.assertEquals(new EventScore(sco018, 13, 13), pilotScores.get(12));
			Assert.assertEquals(new EventScore(sco198, 14, 14), pilotScores.get(13));
			Assert.assertEquals(new EventScore(sco087, 15, 15), pilotScores.get(14));
			Assert.assertEquals(new EventScore(sco197, 16, 16), pilotScores.get(15));
			Assert.assertEquals(new EventScore(sco158, 17, 17), pilotScores.get(16));
			Assert.assertEquals(new EventScore(sco320, 18, 18), pilotScores.get(17));
			Assert.assertEquals(new EventScore(sco249, 19, 19), pilotScores.get(18));
			Assert.assertEquals(new EventScore(sco060, 23, 20), pilotScores.get(19));
			Assert.assertEquals(new EventScore(sco153, 23, 20), pilotScores.get(20));
			Assert.assertEquals(new EventScore(sco156, 23, 20), pilotScores.get(21));

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
			Assert.assertEquals(new RaceScore(sco068, 0, 5, 1), pilotScores.get(0));
			Assert.assertEquals(new RaceScore(sco200, 2, 5, 2), pilotScores.get(1));
			Assert.assertEquals(new RaceScore(sco179, 3, 5, 3), pilotScores.get(2));
			Assert.assertEquals(new RaceScore(sco808, 4, 5, 4), pilotScores.get(3));
			Assert.assertEquals(new RaceScore(sco116, 5, 5, 5), pilotScores.get(4));
			Assert.assertEquals(new RaceScore(sco081, 6, 5, 6), pilotScores.get(5));
			Assert.assertEquals(new RaceScore(sco136, 7, 4, 7), pilotScores.get(6));
			Assert.assertEquals(new RaceScore(sco248, 8, 4, 8), pilotScores.get(7));
			Assert.assertEquals(new RaceScore(sco528, 9, 4, 9), pilotScores.get(8));
			Assert.assertEquals(new RaceScore(sco467, 10, 4, 10), pilotScores.get(9));
			Assert.assertEquals(new RaceScore(sco159, 11, 4, 11), pilotScores.get(10));
			Assert.assertEquals(new RaceScore(sco019, 12, 3, 12), pilotScores.get(11));
			Assert.assertEquals(new RaceScore(sco018, 13, 3, 13), pilotScores.get(12));
			Assert.assertEquals(new RaceScore(sco198, 14, 3, 14), pilotScores.get(13));
			Assert.assertEquals(new RaceScore(sco087, 15, 3, 15), pilotScores.get(14));
			Assert.assertEquals(new RaceScore(sco197, 16, 2, 16), pilotScores.get(15));
			Assert.assertEquals(new RaceScore(sco158, 17, 2, 17), pilotScores.get(16));
			Assert.assertEquals(new RaceScore(sco320, 18, 1, 18), pilotScores.get(17));
			Assert.assertEquals(new RaceScore(sco249, 19, 1, 19), pilotScores.get(18));
			Assert.assertEquals(new RaceScore(sco060, 23, 0, 20), pilotScores.get(19));
			Assert.assertEquals(new RaceScore(sco153, 23, 0, 20), pilotScores.get(20));
			Assert.assertEquals(new RaceScore(sco156, 23, 0, 20), pilotScores.get(21));

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}