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

import org.spka.cursus.scoring.SPKAConstants;
import org.spka.cursus.test.AbstractSeries;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Gender;
import eu.lp0.cursus.db.data.Penalty;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceAttendee;
import eu.lp0.cursus.db.data.RaceEvent;
import eu.lp0.cursus.db.data.RaceNumber;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.scoring.Scorer;
import eu.lp0.cursus.scoring.ScorerFactory;

public abstract class AbstractSeries2011 extends AbstractSeries {
	protected final String SERIES_NAME = "SPKA Race Series 2011/12"; //$NON-NLS-1$
	protected final int SERIES_FLEET = 23;
	protected final int SERIES_FLEET_AT_EVENT1 = 22;
	protected final int SERIES_FLEET_AT_EVENT2 = 22;
	protected final int SERIES_FLEET_AT_EVENT3 = 23;

	protected final String EVENT1_NAME = "Race Event 1"; //$NON-NLS-1$
	protected final int EVENT1_PILOTS = 22;
	protected final int EVENT1_FLEET = 22;
	protected final String RACE1_NAME = "Race 1"; //$NON-NLS-1$

	protected final String EVENT2_NAME = "Race Event 2"; //$NON-NLS-1$
	protected final int EVENT2_PILOTS = 21;
	protected final int EVENT2_FLEET = 23;
	protected final String RACE2_NAME = "Race 2"; //$NON-NLS-1$
	protected final String RACE3_NAME = "Race 3"; //$NON-NLS-1$
	protected final String RACE4_NAME = "Race 4"; //$NON-NLS-1$

	protected final String EVENT3_NAME = "Race Event 3"; //$NON-NLS-1$
	protected final int EVENT3_PILOTS = 19;
	protected final int EVENT3_PILOTS_DAY1 = 18;
	protected final int EVENT3_PILOTS_DAY2 = 19;
	protected final int EVENT3_FLEET = 23;
	protected final String RACE5_NAME = "Race 5"; //$NON-NLS-1$
	protected final String RACE6_NAME = "Race 6"; //$NON-NLS-1$
	protected final String RACE7_NAME = "Race 7"; //$NON-NLS-1$
	protected final String RACE8_NAME = "Race 8"; //$NON-NLS-1$
	protected final String RACE9_NAME = "Race 9"; //$NON-NLS-1$

	protected Scorer scorer = ScorerFactory.newScorer(SPKAConstants.UUID_2011);

	protected Pilot sco018;
	protected Pilot sco019;
	protected Pilot sco040;
	protected Pilot sco060;
	protected Pilot sco068;
	protected Pilot sco081;
	protected Pilot sco087;
	protected Pilot sco116;
	protected Pilot sco136;
	protected Pilot sco153;
	protected Pilot sco156;
	protected Pilot sco158;
	protected Pilot sco159;
	protected Pilot sco179;
	protected Pilot sco197;
	protected Pilot sco198;
	protected Pilot sco200;
	protected Pilot sco248;
	protected Pilot sco249;
	protected Pilot sco320;
	protected Pilot sco467;
	protected Pilot sco528;
	protected Pilot sco808;
	protected Pilot b1045;

	private Series _series;
	private Event _event1;
	private Race _race1;
	private Event _event2;
	private Race _race2;
	private Race _race3;
	private Race _race4;
	private Event _event3;
	private Race _race5;
	private Race _race6;
	private Race _race7;
	private Race _race8;
	private Race _race9;

	protected void createSeriesData() throws Exception {
		if (_series != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			// Remove the default series
			for (Series series : seriesDAO.findAll()) {
				seriesDAO.remove(series);
			}

			// Create the 2011/12 series
			Series series = new Series(SERIES_NAME);

			// Add all the pilots
			sco018 = new Pilot(series, "SCO018", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco018);

			sco019 = new Pilot(series, "SCO019", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco019);

			sco040 = new Pilot(series, "SCO040", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco040);

			sco060 = new Pilot(series, "SCO060", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco060);

			sco068 = new Pilot(series, "SCO068", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco068);

			sco081 = new Pilot(series, "SCO081", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco081);

			sco087 = new Pilot(series, "SCO087", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco087);

			sco116 = new Pilot(series, "SCO116", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco116);

			sco136 = new Pilot(series, "SCO136", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco136);

			sco153 = new Pilot(series, "SCO153", Gender.FEMALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco153);

			sco156 = new Pilot(series, "SCO156", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco156);

			sco158 = new Pilot(series, "SCO158", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco158);

			sco159 = new Pilot(series, "SCO159", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco159);

			sco179 = new Pilot(series, "SCO179", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco179);

			sco197 = new Pilot(series, "SCO197", Gender.FEMALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco197);

			sco198 = new Pilot(series, "SCO198", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco198);

			sco200 = new Pilot(series, "SCO200", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco200);

			sco248 = new Pilot(series, "SCO248", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco248);

			sco249 = new Pilot(series, "SCO249", Gender.FEMALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco249);

			sco320 = new Pilot(series, "SCO320", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco320);

			sco467 = new Pilot(series, "SCO467", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco467);

			sco528 = new Pilot(series, "SCO528", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco528);

			sco808 = new Pilot(series, "SCO808", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco808);

			b1045 = new Pilot(series, "B1045", Gender.MALE, "Belgium"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(b1045);

			// Create race numbers
			for (Pilot pilot : series.getPilots()) {
				pilot.setRaceNumber(RaceNumber.valueOfFor(pilot.getName(), pilot));
			}

			// Save
			seriesDAO.persist(series);

			DatabaseSession.commit();

			_series = series;
		} finally {
			db.endSession();
		}
	}

	protected void createEvent1Data() throws Exception {
		createSeriesData();

		if (_event1 != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);

			Event event1 = new Event(series, EVENT1_NAME);
			series.getEvents().add(event1);
			eventDAO.persist(event1);

			DatabaseSession.commit();

			_event1 = event1;
		} finally {
			db.endSession();
		}
	}

	protected void createEvent1Races() throws Exception {
		createRace1Data();
	}

	protected void createRace1Data() throws Exception {
		createEvent1Data();

		if (_race1 != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);

			Race race1 = new Race(event1, RACE1_NAME);
			event1.getRaces().add(race1);
			race1.getAttendees().put(sco018, new RaceAttendee(race1, sco018, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco019, new RaceAttendee(race1, sco019, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco060, new RaceAttendee(race1, sco060, RaceAttendee.Type.V_SCORER));
			race1.getAttendees().put(sco068, new RaceAttendee(race1, sco068, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco081, new RaceAttendee(race1, sco081, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco087, new RaceAttendee(race1, sco087, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco116, new RaceAttendee(race1, sco116, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco136, new RaceAttendee(race1, sco136, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco153, new RaceAttendee(race1, sco153, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco156, new RaceAttendee(race1, sco156, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco158, new RaceAttendee(race1, sco158, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco159, new RaceAttendee(race1, sco159, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco179, new RaceAttendee(race1, sco179, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco197, new RaceAttendee(race1, sco197, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco198, new RaceAttendee(race1, sco198, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco200, new RaceAttendee(race1, sco200, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco248, new RaceAttendee(race1, sco248, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco249, new RaceAttendee(race1, sco249, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco320, new RaceAttendee(race1, sco320, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco467, new RaceAttendee(race1, sco467, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco528, new RaceAttendee(race1, sco528, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco808, new RaceAttendee(race1, sco808, RaceAttendee.Type.PILOT));
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			// 1
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "136", sco136)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "198", sco198)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "320", sco320)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "18", sco018)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "197", sco197)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "158", sco158)); //$NON-NLS-1$
			// 2
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "136", sco136)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "198", sco198)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "18", sco018)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "197", sco197)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "158", sco158)); //$NON-NLS-1$
			// 3
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "136", sco136)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "18", sco018)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "198", sco198)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			// 4
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "136", sco136)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			// 5
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			raceDAO.persist(race1);

			DatabaseSession.commit();

			_race1 = race1;
		} finally {
			db.endSession();
		}
	}

	protected void createEvent2Data() throws Exception {
		createSeriesData();

		if (_event2 != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);

			Event event2 = new Event(series, EVENT2_NAME);
			series.getEvents().add(event2);
			eventDAO.persist(event2);

			DatabaseSession.commit();

			_event2 = event2;
		} finally {
			db.endSession();
		}
	}

	protected void createEvent2Races() throws Exception {
		createRace2Data();
		createRace3Data();
		createRace4Data();
	}

	protected void createRace2Data() throws Exception {
		createEvent2Data();

		if (_race2 != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);

			Race race2 = new Race(event2, RACE2_NAME);
			event2.getRaces().add(race2);
			// race2.getAttendees().put(sco018, new RaceAttendee(race2, sco018, RaceAttendee.Type.PILOT));
			RaceAttendee att019 = new RaceAttendee(race2, sco019, RaceAttendee.Type.PILOT);
			att019.getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC));
			race2.getAttendees().put(sco019, att019);
			race2.getAttendees().put(sco060, new RaceAttendee(race2, sco060, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco068, new RaceAttendee(race2, sco068, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco081, new RaceAttendee(race2, sco081, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco087, new RaceAttendee(race2, sco087, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco116, new RaceAttendee(race2, sco116, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco136, new RaceAttendee(race2, sco136, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco153, new RaceAttendee(race2, sco153, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco156, new RaceAttendee(race2, sco156, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco159, new RaceAttendee(race2, sco159, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco179, new RaceAttendee(race2, sco179, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco197, new RaceAttendee(race2, sco197, RaceAttendee.Type.M_RACE_MASTER));
			race2.getAttendees().put(sco198, new RaceAttendee(race2, sco198, RaceAttendee.Type.M_SCORER));
			race2.getAttendees().put(sco200, new RaceAttendee(race2, sco200, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco248, new RaceAttendee(race2, sco248, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco249, new RaceAttendee(race2, sco249, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco320, new RaceAttendee(race2, sco320, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco467, new RaceAttendee(race2, sco467, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco528, new RaceAttendee(race2, sco528, RaceAttendee.Type.PILOT));
			RaceAttendee att808 = new RaceAttendee(race2, sco808, RaceAttendee.Type.PILOT);
			att808.getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC));
			race2.getAttendees().put(sco808, att808);
			race2.getAttendees().put(b1045, new RaceAttendee(race2, b1045, RaceAttendee.Type.PILOT));
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			// 1
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			// http://www.flickr.com/photos/lp0/6838159693/in/set-72157629234378617/
			// B1045 didn't cross the start line before the race started
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			// 2
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			// 3
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			// 4
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			// 5
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			// 6
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "153", sco153)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			raceDAO.persist(race2);

			DatabaseSession.commit();

			_race2 = race2;
		} finally {
			db.endSession();
		}
	}

	protected void createRace3Data() throws Exception {
		createEvent2Data();

		if (_race3 != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);

			Race race3 = new Race(event2, RACE3_NAME);
			event2.getRaces().add(race3);
			race3.getAttendees().put(sco019, new RaceAttendee(race3, sco019, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco060, new RaceAttendee(race3, sco060, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco068, new RaceAttendee(race3, sco068, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco081, new RaceAttendee(race3, sco081, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco087, new RaceAttendee(race3, sco087, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco116, new RaceAttendee(race3, sco116, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco136, new RaceAttendee(race3, sco136, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco153, new RaceAttendee(race3, sco153, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco156, new RaceAttendee(race3, sco156, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco159, new RaceAttendee(race3, sco159, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco179, new RaceAttendee(race3, sco179, RaceAttendee.Type.M_SCORER));
			race3.getAttendees().put(sco197, new RaceAttendee(race3, sco197, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco198, new RaceAttendee(race3, sco198, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco200, new RaceAttendee(race3, sco200, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco248, new RaceAttendee(race3, sco248, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco249, new RaceAttendee(race3, sco249, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco320, new RaceAttendee(race3, sco320, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco467, new RaceAttendee(race3, sco467, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco528, new RaceAttendee(race3, sco528, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco808, new RaceAttendee(race3, sco808, RaceAttendee.Type.M_RACE_MASTER));
			race3.getAttendees().put(b1045, new RaceAttendee(race3, b1045, RaceAttendee.Type.PILOT));
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			// 1
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "136", sco136)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			// 2
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			// 3
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "136", sco136)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "60", sco060)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			// 4
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			// 5
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "136", sco136)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "153", sco153)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "198", sco198)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			// 6
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "60", sco060)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "136", sco136)); //$NON-NLS-1$
			// 7
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "60", sco060)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "136", sco136)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "153", sco153)); //$NON-NLS-1$
			raceDAO.persist(race3);

			DatabaseSession.commit();

			_race3 = race3;
		} finally {
			db.endSession();
		}
	}

	protected void createRace4Data() throws Exception {
		createEvent2Data();

		if (_race4 != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event2 = eventDAO.find(series, EVENT2_NAME);

			Race race4 = new Race(event2, RACE4_NAME);
			event2.getRaces().add(race4);
			race4.getAttendees().put(sco019, new RaceAttendee(race4, sco019, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco060, new RaceAttendee(race4, sco060, RaceAttendee.Type.M_SCORER));
			race4.getAttendees().put(sco068, new RaceAttendee(race4, sco068, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco081, new RaceAttendee(race4, sco081, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco087, new RaceAttendee(race4, sco087, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco116, new RaceAttendee(race4, sco116, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco136, new RaceAttendee(race4, sco136, RaceAttendee.Type.M_RACE_MASTER));
			race4.getAttendees().put(sco153, new RaceAttendee(race4, sco153, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco156, new RaceAttendee(race4, sco156, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco159, new RaceAttendee(race4, sco159, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco179, new RaceAttendee(race4, sco179, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco197, new RaceAttendee(race4, sco197, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco198, new RaceAttendee(race4, sco198, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco200, new RaceAttendee(race4, sco200, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco248, new RaceAttendee(race4, sco248, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco249, new RaceAttendee(race4, sco249, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco320, new RaceAttendee(race4, sco320, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco467, new RaceAttendee(race4, sco467, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco528, new RaceAttendee(race4, sco528, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco808, new RaceAttendee(race4, sco808, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(b1045, new RaceAttendee(race4, b1045, RaceAttendee.Type.PILOT));
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			// 1
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			// 2
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			// 3
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "153", sco153)); //$NON-NLS-1$
			// 4
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			// 5
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			// 6
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			// 7
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "153", sco153)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "B1045", b1045)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			raceDAO.persist(race4);

			DatabaseSession.commit();

			_race4 = race4;
		} finally {
			db.endSession();
		}
	}

	protected void createEvent3Data() throws Exception {
		createSeriesData();

		if (_event3 != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);

			Event event3 = new Event(series, EVENT3_NAME);
			series.getEvents().add(event3);
			eventDAO.persist(event3);

			DatabaseSession.commit();

			_event3 = event3;
		} finally {
			db.endSession();
		}
	}

	protected void createEvent3Races() throws Exception {
		createRace5Data();
		createRace6Data();
		createRace7Data();
		createRace8Data();
		createRace9Data();
	}

	protected void createRace5Data() throws Exception {
		createEvent3Data();

		if (_race5 != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);

			Race race5 = new Race(event3, RACE5_NAME);
			event3.getRaces().add(race5);
			race5.getAttendees().put(sco018, new RaceAttendee(race5, sco018, RaceAttendee.Type.M_SCORER));
			race5.getAttendees().put(sco019, new RaceAttendee(race5, sco019, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco060, new RaceAttendee(race5, sco060, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco068, new RaceAttendee(race5, sco068, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco081, new RaceAttendee(race5, sco081, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco087, new RaceAttendee(race5, sco087, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco116, new RaceAttendee(race5, sco116, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco153, new RaceAttendee(race5, sco153, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco156, new RaceAttendee(race5, sco156, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco159, new RaceAttendee(race5, sco159, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco179, new RaceAttendee(race5, sco179, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco248, new RaceAttendee(race5, sco248, RaceAttendee.Type.M_RACE_MASTER));
			race5.getAttendees().put(sco249, new RaceAttendee(race5, sco249, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco320, new RaceAttendee(race5, sco320, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco467, new RaceAttendee(race5, sco467, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco528, new RaceAttendee(race5, sco528, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco808, new RaceAttendee(race5, sco808, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco040, new RaceAttendee(race5, sco040, RaceAttendee.Type.PILOT));
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			// 1
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "153", sco153)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "60", sco060)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "40", sco040)); //$NON-NLS-1$
			// 2
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "153", sco153)); //$NON-NLS-1$
			// 3
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "153", sco153)); //$NON-NLS-1$
			// 4
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			// 5
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			// 6
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			raceDAO.persist(race5);

			DatabaseSession.commit();

			_race5 = race5;
		} finally {
			db.endSession();
		}
	}

	protected void createRace6Data() throws Exception {
		createEvent3Data();

		if (_race6 != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);

			Race race6 = new Race(event3, RACE6_NAME);
			event3.getRaces().add(race6);
			race6.getAttendees().put(sco018, new RaceAttendee(race6, sco018, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco019, new RaceAttendee(race6, sco019, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco060, new RaceAttendee(race6, sco060, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco068, new RaceAttendee(race6, sco068, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco081, new RaceAttendee(race6, sco081, RaceAttendee.Type.M_RACE_MASTER));
			race6.getAttendees().put(sco087, new RaceAttendee(race6, sco087, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco116, new RaceAttendee(race6, sco116, RaceAttendee.Type.M_SCORER));
			race6.getAttendees().put(sco153, new RaceAttendee(race6, sco153, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco156, new RaceAttendee(race6, sco156, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco159, new RaceAttendee(race6, sco159, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco179, new RaceAttendee(race6, sco179, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco248, new RaceAttendee(race6, sco248, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco249, new RaceAttendee(race6, sco249, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco320, new RaceAttendee(race6, sco320, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco467, new RaceAttendee(race6, sco467, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco528, new RaceAttendee(race6, sco528, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco808, new RaceAttendee(race6, sco808, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco040, new RaceAttendee(race6, sco040, RaceAttendee.Type.PILOT));
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			// 1
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "60", sco060)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "18", sco018)); //$NON-NLS-1$
			// 2
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "40", sco040)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "60", sco060)); //$NON-NLS-1$
			// 3
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "320", sco320)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			// 4
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "18", sco018)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "60", sco060)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "40", sco040)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			// 5
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "320", sco320)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "60", sco060)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			// 6
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "153", sco153)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "18", sco018)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			// 7
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "320", sco320)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "60", sco060)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			// 8
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "18", sco018)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "60", sco060)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "320", sco320)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			// 9
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "18", sco018)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "153", sco153)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			// 10
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "60", sco060)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "320", sco320)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "19", sco019)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "18", sco018)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "153", sco153)); //$NON-NLS-1$
			raceDAO.persist(race6);

			DatabaseSession.commit();

			_race6 = race6;
		} finally {
			db.endSession();
		}
	}

	protected void createRace7Data() throws Exception {
		createEvent3Data();

		if (_race7 != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);

			Race race7 = new Race(event3, RACE7_NAME);
			event3.getRaces().add(race7);
			race7.getAttendees().put(sco018, new RaceAttendee(race7, sco018, RaceAttendee.Type.PILOT));
			race7.getAttendees().put(sco019, new RaceAttendee(race7, sco019, RaceAttendee.Type.M_SCORER));
			race7.getAttendees().put(sco060, new RaceAttendee(race7, sco060, RaceAttendee.Type.PILOT));
			race7.getAttendees().put(sco068, new RaceAttendee(race7, sco068, RaceAttendee.Type.PILOT));
			race7.getAttendees().put(sco081, new RaceAttendee(race7, sco081, RaceAttendee.Type.PILOT));
			race7.getAttendees().put(sco087, new RaceAttendee(race7, sco087, RaceAttendee.Type.PILOT));
			race7.getAttendees().put(sco116, new RaceAttendee(race7, sco116, RaceAttendee.Type.PILOT));
			race7.getAttendees().put(sco153, new RaceAttendee(race7, sco153, RaceAttendee.Type.PILOT));
			race7.getAttendees().put(sco156, new RaceAttendee(race7, sco156, RaceAttendee.Type.PILOT));
			race7.getAttendees().put(sco159, new RaceAttendee(race7, sco159, RaceAttendee.Type.M_RACE_MASTER));
			race7.getAttendees().put(sco179, new RaceAttendee(race7, sco179, RaceAttendee.Type.PILOT));
			race7.getAttendees().put(sco248, new RaceAttendee(race7, sco248, RaceAttendee.Type.PILOT));
			race7.getAttendees().put(sco249, new RaceAttendee(race7, sco249, RaceAttendee.Type.PILOT));
			race7.getAttendees().put(sco320, new RaceAttendee(race7, sco320, RaceAttendee.Type.PILOT));
			race7.getAttendees().put(sco467, new RaceAttendee(race7, sco467, RaceAttendee.Type.PILOT));
			race7.getAttendees().put(sco528, new RaceAttendee(race7, sco528, RaceAttendee.Type.PILOT));
			race7.getAttendees().put(sco808, new RaceAttendee(race7, sco808, RaceAttendee.Type.PILOT));
			race7.getAttendees().put(sco040, new RaceAttendee(race7, sco040, RaceAttendee.Type.PILOT));
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			// 1
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "320", sco320)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "60", sco060)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "153", sco153)); //$NON-NLS-1$
			// 2
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "81", sco081)); //$NON-NLS-1$
			// 3
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			// 4
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race7.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			raceDAO.persist(race7);

			DatabaseSession.commit();

			_race7 = race7;
		} finally {
			db.endSession();
		}
	}

	protected void createRace8Data() throws Exception {
		createEvent3Data();

		if (_race8 != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);

			Race race8 = new Race(event3, RACE8_NAME);
			event3.getRaces().add(race8);
			race8.getAttendees().put(sco018, new RaceAttendee(race8, sco018, RaceAttendee.Type.PILOT));
			race8.getAttendees().put(sco019, new RaceAttendee(race8, sco019, RaceAttendee.Type.PILOT));
			race8.getAttendees().put(sco060, new RaceAttendee(race8, sco060, RaceAttendee.Type.PILOT));
			race8.getAttendees().put(sco068, new RaceAttendee(race8, sco068, RaceAttendee.Type.PILOT));
			race8.getAttendees().put(sco081, new RaceAttendee(race8, sco081, RaceAttendee.Type.PILOT));
			race8.getAttendees().put(sco087, new RaceAttendee(race8, sco087, RaceAttendee.Type.M_RACE_MASTER));
			race8.getAttendees().put(sco116, new RaceAttendee(race8, sco116, RaceAttendee.Type.PILOT));
			race8.getAttendees().put(sco153, new RaceAttendee(race8, sco153, RaceAttendee.Type.M_SCORER));
			race8.getAttendees().put(sco156, new RaceAttendee(race8, sco156, RaceAttendee.Type.PILOT));
			race8.getAttendees().put(sco159, new RaceAttendee(race8, sco159, RaceAttendee.Type.PILOT));
			race8.getAttendees().put(sco179, new RaceAttendee(race8, sco179, RaceAttendee.Type.PILOT));
			race8.getAttendees().put(sco200, new RaceAttendee(race8, sco200, RaceAttendee.Type.PILOT));
			race8.getAttendees().put(sco248, new RaceAttendee(race8, sco248, RaceAttendee.Type.PILOT));
			race8.getAttendees().put(sco249, new RaceAttendee(race8, sco249, RaceAttendee.Type.PILOT));
			race8.getAttendees().put(sco320, new RaceAttendee(race8, sco320, RaceAttendee.Type.PILOT));
			race8.getAttendees().put(sco467, new RaceAttendee(race8, sco467, RaceAttendee.Type.PILOT));
			race8.getAttendees().put(sco528, new RaceAttendee(race8, sco528, RaceAttendee.Type.PILOT));
			race8.getAttendees().put(sco808, new RaceAttendee(race8, sco808, RaceAttendee.Type.PILOT));
			race8.getAttendees().put(sco040, new RaceAttendee(race8, sco040, RaceAttendee.Type.PILOT));
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			// 1
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "320", sco320)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "40", sco040)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "156", sco156)); //$NON-NLS-1$
			// 2
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "320", sco320)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "40", sco040)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "156", sco156)); //$NON-NLS-1$
			// 3
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "320", sco320)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "40", sco040)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "156", sco156)); //$NON-NLS-1$
			// 4
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "320", sco320)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "40", sco040)); //$NON-NLS-1$
			// 5
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			// 6
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "249", sco249)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			// 7
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "68", sco068)); //$NON-NLS-1$
			race8.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			raceDAO.persist(race8);

			DatabaseSession.commit();

			_race8 = race8;
		} finally {
			db.endSession();
		}
	}

	protected void createRace9Data() throws Exception {
		createEvent3Data();

		if (_race9 != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event3 = eventDAO.find(series, EVENT3_NAME);

			Race race9 = new Race(event3, RACE9_NAME);
			event3.getRaces().add(race9);
			race9.getAttendees().put(sco018, new RaceAttendee(race9, sco018, RaceAttendee.Type.PILOT));
			race9.getAttendees().put(sco019, new RaceAttendee(race9, sco019, RaceAttendee.Type.PILOT));
			race9.getAttendees().put(sco060, new RaceAttendee(race9, sco060, RaceAttendee.Type.PILOT));
			race9.getAttendees().put(sco068, new RaceAttendee(race9, sco068, RaceAttendee.Type.M_SCORER));
			race9.getAttendees().put(sco081, new RaceAttendee(race9, sco081, RaceAttendee.Type.PILOT));
			race9.getAttendees().put(sco087, new RaceAttendee(race9, sco087, RaceAttendee.Type.PILOT));
			race9.getAttendees().put(sco116, new RaceAttendee(race9, sco116, RaceAttendee.Type.PILOT));
			race9.getAttendees().put(sco153, new RaceAttendee(race9, sco153, RaceAttendee.Type.PILOT));
			race9.getAttendees().put(sco156, new RaceAttendee(race9, sco156, RaceAttendee.Type.PILOT));
			race9.getAttendees().put(sco159, new RaceAttendee(race9, sco159, RaceAttendee.Type.PILOT));
			race9.getAttendees().put(sco179, new RaceAttendee(race9, sco179, RaceAttendee.Type.PILOT));
			race9.getAttendees().put(sco200, new RaceAttendee(race9, sco200, RaceAttendee.Type.PILOT));
			race9.getAttendees().put(sco248, new RaceAttendee(race9, sco248, RaceAttendee.Type.PILOT));
			race9.getAttendees().put(sco249, new RaceAttendee(race9, sco249, RaceAttendee.Type.M_RACE_MASTER));
			race9.getAttendees().put(sco320, new RaceAttendee(race9, sco320, RaceAttendee.Type.PILOT));
			race9.getAttendees().put(sco467, new RaceAttendee(race9, sco467, RaceAttendee.Type.PILOT));
			race9.getAttendees().put(sco528, new RaceAttendee(race9, sco528, RaceAttendee.Type.PILOT));
			race9.getAttendees().put(sco808, new RaceAttendee(race9, sco808, RaceAttendee.Type.PILOT));
			race9.getAttendees().put(sco040, new RaceAttendee(race9, sco040, RaceAttendee.Type.PILOT));
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			// 1
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "320", sco320)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "87", sco087)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "153", sco153)); //$NON-NLS-1$
			// 2
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "320", sco320)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "153", sco153)); //$NON-NLS-1$
			// 3
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "159", sco159)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "320", sco320)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "467", sco467)); //$NON-NLS-1$
			// 4
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "116", sco116)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "179", sco179)); //$NON-NLS-1$
			// 5
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "200", sco200)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "528", sco528)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "808", sco808)); //$NON-NLS-1$
			race9.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "248", sco248)); //$NON-NLS-1$
			raceDAO.persist(race9);

			DatabaseSession.commit();

			_race9 = race9;
		} finally {
			db.endSession();
		}
	}
}