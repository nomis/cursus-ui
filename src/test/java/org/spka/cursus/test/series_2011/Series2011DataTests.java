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

			Assert.assertEquals(SERIES_FLEET, getSeriesResultsPilots(series).size());

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

	@Test
	public void checkRace5() throws Exception {
		createRace5Data();

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Race race5 = raceDAO.find(event3, RACE5_NAME);

			Assert.assertEquals(EVENT3_PILOTS, race5.getAttendees().size());
			Assert.assertEquals(RaceAttendee.Type.M_RACE_MASTER, race5.getAttendees().get(sco248).getType());
			Assert.assertEquals(RaceAttendee.Type.M_SCORER, race5.getAttendees().get(sco018).getType());
			Assert.assertEquals(RaceAttendee.Type.PILOT, race5.getAttendees().get(sco808).getType());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace6() throws Exception {
		createRace6Data();

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Race race6 = raceDAO.find(event3, RACE6_NAME);

			Assert.assertEquals(EVENT3_PILOTS, race6.getAttendees().size());
			Assert.assertEquals(RaceAttendee.Type.M_RACE_MASTER, race6.getAttendees().get(sco081).getType());
			Assert.assertEquals(RaceAttendee.Type.M_SCORER, race6.getAttendees().get(sco116).getType());
			Assert.assertEquals(RaceAttendee.Type.PILOT, race6.getAttendees().get(sco808).getType());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace7() throws Exception {
		createRace7Data();

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Race race7 = raceDAO.find(event3, RACE7_NAME);

			Assert.assertEquals(EVENT3_PILOTS, race7.getAttendees().size());
			Assert.assertEquals(RaceAttendee.Type.M_RACE_MASTER, race7.getAttendees().get(sco159).getType());
			Assert.assertEquals(RaceAttendee.Type.M_SCORER, race7.getAttendees().get(sco019).getType());
			Assert.assertEquals(RaceAttendee.Type.PILOT, race7.getAttendees().get(sco808).getType());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace8() throws Exception {
		createRace8Data();

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Race race8 = raceDAO.find(event3, RACE8_NAME);

			Assert.assertEquals(EVENT3_PILOTS, race8.getAttendees().size());
			Assert.assertEquals(RaceAttendee.Type.M_RACE_MASTER, race8.getAttendees().get(sco087).getType());
			Assert.assertEquals(RaceAttendee.Type.M_SCORER, race8.getAttendees().get(sco153).getType());
			Assert.assertEquals(RaceAttendee.Type.PILOT, race8.getAttendees().get(sco808).getType());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}

	@Test
	public void checkRace9() throws Exception {
		createRace9Data();

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);
			Race race9 = raceDAO.find(event3, RACE9_NAME);

			Assert.assertEquals(EVENT3_PILOTS, race9.getAttendees().size());
			Assert.assertEquals(RaceAttendee.Type.M_RACE_MASTER, race9.getAttendees().get(sco249).getType());
			Assert.assertEquals(RaceAttendee.Type.M_SCORER, race9.getAttendees().get(sco068).getType());
			Assert.assertEquals(RaceAttendee.Type.PILOT, race9.getAttendees().get(sco808).getType());

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}