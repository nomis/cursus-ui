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

import org.junit.Assert;
import org.junit.Test;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceAttendee;
import eu.lp0.cursus.db.data.Series;

public class Series2011DataTests extends AbstractSeries2011 {
	@Test
	public void checkPilots() throws Exception {
		createSeriesData();

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);

			Assert.assertEquals(SERIES_FLEET, getResultsPilots(series).size());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkEvent1() throws Exception {
		createEvent1Races();

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);

			Assert.assertEquals(1, event1.getRaces().size());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace1() throws Exception {
		createRace1Data();

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);
			Race race1 = raceDAO.find(event1, RACE1_NAME);

			Assert.assertEquals(EVENT1_FLEET, race1.getAttendees().size());
			Assert.assertEquals(RaceAttendee.Type.V_SCORER, race1.getAttendees().get(sco060).getType());
			Assert.assertEquals(RaceAttendee.Type.PILOT, race1.getAttendees().get(sco179).getType());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkEvent2() throws Exception {
		createEvent2Races();

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);

			Assert.assertEquals(3, event2.getRaces().size());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace2() throws Exception {
		createRace2Data();

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race2 = raceDAO.find(event2, RACE2_NAME);

			Assert.assertEquals(EVENT2_PILOTS, race2.getAttendees().size());
			Assert.assertEquals(RaceAttendee.Type.M_RACE_MASTER, race2.getAttendees().get(sco197).getType());
			Assert.assertEquals(RaceAttendee.Type.M_SCORER, race2.getAttendees().get(sco198).getType());
			Assert.assertEquals(RaceAttendee.Type.PILOT, race2.getAttendees().get(sco808).getType());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace3() throws Exception {
		createRace3Data();

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race3 = raceDAO.find(event2, RACE3_NAME);

			Assert.assertEquals(EVENT2_PILOTS, race3.getAttendees().size());
			Assert.assertEquals(RaceAttendee.Type.M_RACE_MASTER, race3.getAttendees().get(sco808).getType());
			Assert.assertEquals(RaceAttendee.Type.M_SCORER, race3.getAttendees().get(sco179).getType());
			Assert.assertEquals(RaceAttendee.Type.PILOT, race3.getAttendees().get(sco156).getType());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace4() throws Exception {
		createRace4Data();

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);
			Race race4 = raceDAO.find(event2, RACE4_NAME);

			Assert.assertEquals(EVENT2_PILOTS, race4.getAttendees().size());
			Assert.assertEquals(RaceAttendee.Type.M_RACE_MASTER, race4.getAttendees().get(sco136).getType());
			Assert.assertEquals(RaceAttendee.Type.M_SCORER, race4.getAttendees().get(sco060).getType());
			Assert.assertEquals(RaceAttendee.Type.PILOT, race4.getAttendees().get(sco808).getType());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}