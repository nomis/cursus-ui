/*
	cursus - Race series management program
	Copyright 2013  Simon Arlott

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
package org.spka.cursus.test.cc_2013;

import org.fisly.cursus.scoring.FISLYConstants;
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
import eu.lp0.cursus.scoring.scorer.Scorer;
import eu.lp0.cursus.scoring.scorer.ScorerFactory;

public abstract class AbstractSeries2013 extends AbstractSeries {
	protected final String SERIES_NAME = "Celtic Challenge 2013"; //$NON-NLS-1$
	protected final int SERIES_FLEET = 15;
	protected final int SERIES_FLEET_AT_EVENT1 = 15;
	protected final int SERIES_FLEET_AT_EVENT2 = 15;

	protected final String EVENT1_NAME = "Race Event 1"; //$NON-NLS-1$
	protected final String EVENT1_DESC = "Benone (25/05/2013 and 26/05/2013)"; //$NON-NLS-1$
	protected final int EVENT1_FLEET = 15;
	protected final String RACE1_NAME = "Race 1"; //$NON-NLS-1$
	protected final String RACE1_DESC = "Benone (25/05/2013)"; //$NON-NLS-1$
	protected final int RACE1_PILOTS = 15;
	protected final String RACE2_NAME = "Race 2"; //$NON-NLS-1$
	protected final String RACE2_DESC = "Benone (25/05/2013)"; //$NON-NLS-1$
	protected final int RACE2_PILOTS = 15;
	protected final String RACE3_NAME = "Race 3"; //$NON-NLS-1$
	protected final String RACE3_DESC = "Benone (25/05/2013)"; //$NON-NLS-1$
	protected final int RACE3_PILOTS = 15;
	protected final String RACE4_NAME = "Race 4"; //$NON-NLS-1$
	protected final String RACE4_DESC = "Benone (25/05/2013)"; //$NON-NLS-1$
	protected final int RACE4_PILOTS = 15;
	protected final String RACE5_NAME = "Race 5"; //$NON-NLS-1$
	protected final String RACE5_DESC = "Benone (26/05/2013)"; //$NON-NLS-1$
	protected final int RACE5_PILOTS = 15;
	protected final String RACE6_NAME = "Race 6"; //$NON-NLS-1$
	protected final String RACE6_DESC = "Benone (26/05/2013)"; //$NON-NLS-1$
	protected final int RACE6_PILOTS = 15;

	protected final String EVENT2_NAME = "Race Event 2"; //$NON-NLS-1$
	protected final String EVENT2_DESC = "West Sands (?)"; //$NON-NLS-1$
	protected final int EVENT2_FLEET = 0;

	protected Scorer scorer = ScorerFactory.newScorer(FISLYConstants.UUID_2010);

	protected Pilot sco060;
	protected Pilot sco116;
	protected Pilot sco153;
	protected Pilot sco159;
	protected Pilot sco528;
	protected Pilot sco666;
	protected Pilot sco808;
	protected Pilot ir001;
	protected Pilot ir014;
	protected Pilot ir016;
	protected Pilot ir025;
	protected Pilot ir053;
	protected Pilot ir077;
	protected Pilot ir085;
	protected Pilot ir181;

	private Series _series;
	private Event _event1;
	private Race _race1;
	private Race _race2;
	private Race _race3;
	private Race _race4;
	private Race _race5;
	private Race _race6;

	protected void createSeriesData() throws Exception {
		if (_series != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			// Create the 2012/13 series
			Series series = new Series(SERIES_NAME);

			// Add all the pilots
			sco060 = new Pilot(series, "SCO060", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco060);

			sco116 = new Pilot(series, "SCO116", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco116);

			sco153 = new Pilot(series, "SCO153", Gender.FEMALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco153);

			sco159 = new Pilot(series, "SCO159", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco159);

			sco528 = new Pilot(series, "SCO528", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco528);

			sco666 = new Pilot(series, "SCO666", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco666);

			sco808 = new Pilot(series, "SCO808", Gender.MALE, "Scotland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(sco808);

			ir001 = new Pilot(series, "IR001", Gender.MALE, "Ireland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(ir001);

			ir014 = new Pilot(series, "IR014", Gender.MALE, "Ireland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(ir014);

			ir016 = new Pilot(series, "IR016", Gender.MALE, "Ireland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(ir016);

			ir025 = new Pilot(series, "IR025", Gender.MALE, "Ireland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(ir025);

			ir053 = new Pilot(series, "IR053", Gender.MALE, "Ireland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(ir053);

			ir077 = new Pilot(series, "IR077", Gender.MALE, "Ireland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(ir077);

			ir085 = new Pilot(series, "IR085", Gender.MALE, "Ireland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(ir085);

			ir181 = new Pilot(series, "IR181", Gender.MALE, "Ireland"); //$NON-NLS-1$ //$NON-NLS-2$
			series.getPilots().add(ir181);

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

			Event event1 = new Event(series, EVENT1_NAME, EVENT1_DESC);
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
		createRace2Data();
		createRace3Data();
		createRace4Data();
		createRace5Data();
		createRace6Data();
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

			Race race1 = new Race(event1, RACE1_NAME, RACE1_DESC);
			event1.getRaces().add(race1);
			race1.getAttendees().put(sco060, new RaceAttendee(race1, sco060, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco116, new RaceAttendee(race1, sco116, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco153, new RaceAttendee(race1, sco153, RaceAttendee.Type.PILOT));
			RaceAttendee att159 = new RaceAttendee(race1, sco159, RaceAttendee.Type.PILOT);
			att159.getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC, "Hit a mark")); //$NON-NLS-1$
			race1.getAttendees().put(sco159, att159);
			race1.getAttendees().put(sco528, new RaceAttendee(race1, sco528, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco666, new RaceAttendee(race1, sco666, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(sco808, new RaceAttendee(race1, sco808, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(ir001, new RaceAttendee(race1, ir001, RaceAttendee.Type.PILOT));
			RaceAttendee att014 = new RaceAttendee(race1, ir014, RaceAttendee.Type.PILOT);
			att014.getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC, "Hit a mark")); //$NON-NLS-1$
			race1.getAttendees().put(ir014, att014);
			race1.getAttendees().put(ir016, new RaceAttendee(race1, ir016, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(ir025, new RaceAttendee(race1, ir025, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(ir053, new RaceAttendee(race1, ir053, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(ir077, new RaceAttendee(race1, ir077, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(ir085, new RaceAttendee(race1, ir085, RaceAttendee.Type.PILOT));
			race1.getAttendees().put(ir181, new RaceAttendee(race1, ir181, RaceAttendee.Type.PILOT));
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			// 1
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco666)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir053)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			// 2
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir016)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco153)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco666)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			// 3
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir077)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			// 4
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco666)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco153)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			// 5
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco060)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco666)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			// 6
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco153)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir053)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco666)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir077)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			// 7
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir053)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir016)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco153)); //$NON-NLS-1$
			raceDAO.persist(race1);

			DatabaseSession.commit();

			_race1 = race1;
		} finally {
			db.endSession();
		}
	}

	protected void createRace2Data() throws Exception {
		createEvent1Data();

		if (_race2 != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);

			Race race2 = new Race(event1, RACE2_NAME, RACE2_DESC);
			event1.getRaces().add(race2);
			race2.getAttendees().put(sco060, new RaceAttendee(race2, sco060, RaceAttendee.Type.V_SCORER));
			race2.getAttendees().put(sco116, new RaceAttendee(race2, sco116, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco153, new RaceAttendee(race2, sco153, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco159, new RaceAttendee(race2, sco159, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco528, new RaceAttendee(race2, sco528, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco666, new RaceAttendee(race2, sco666, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(sco808, new RaceAttendee(race2, sco808, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(ir001, new RaceAttendee(race2, ir001, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(ir014, new RaceAttendee(race2, ir014, RaceAttendee.Type.PILOT));
			RaceAttendee att016 = new RaceAttendee(race2, ir016, RaceAttendee.Type.PILOT);
			att016.getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC, "Hit a mark")); //$NON-NLS-1$
			race2.getAttendees().put(ir016, att016);
			race2.getAttendees().put(ir025, new RaceAttendee(race2, ir025, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(ir053, new RaceAttendee(race2, ir053, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(ir077, new RaceAttendee(race2, ir077, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(ir085, new RaceAttendee(race2, ir085, RaceAttendee.Type.PILOT));
			race2.getAttendees().put(ir181, new RaceAttendee(race2, ir181, RaceAttendee.Type.PILOT));
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			// 1
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir053)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco666)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir077)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			// 2
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir016)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco666)); //$NON-NLS-1$
			// 3
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir077)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir053)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco153)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir016)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			// 4
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco666)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir053)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir077)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			// 5
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir016)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco666)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir053)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir077)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco153)); //$NON-NLS-1$
			raceDAO.persist(race2);

			DatabaseSession.commit();

			_race2 = race2;
		} finally {
			db.endSession();
		}
	}

	protected void createRace3Data() throws Exception {
		createEvent1Data();

		if (_race3 != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);

			Race race3 = new Race(event1, RACE3_NAME, RACE3_DESC);
			event1.getRaces().add(race3);
			race3.getAttendees().put(sco060, new RaceAttendee(race3, sco060, RaceAttendee.Type.V_SCORER));
			race3.getAttendees().put(sco116, new RaceAttendee(race3, sco116, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco153, new RaceAttendee(race3, sco153, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco159, new RaceAttendee(race3, sco159, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco528, new RaceAttendee(race3, sco528, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco666, new RaceAttendee(race3, sco666, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(sco808, new RaceAttendee(race3, sco808, RaceAttendee.Type.PILOT));
			RaceAttendee att001 = new RaceAttendee(race3, ir001, RaceAttendee.Type.PILOT);
			att001.getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC, "Hit a mark")); //$NON-NLS-1$
			att001.getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC, "Hit a mark")); //$NON-NLS-1$
			race3.getAttendees().put(ir001, att001);
			race3.getAttendees().put(ir014, new RaceAttendee(race3, ir014, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(ir016, new RaceAttendee(race3, ir016, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(ir025, new RaceAttendee(race3, ir025, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(ir053, new RaceAttendee(race3, ir053, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(ir077, new RaceAttendee(race3, ir077, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(ir085, new RaceAttendee(race3, ir085, RaceAttendee.Type.PILOT));
			race3.getAttendees().put(ir181, new RaceAttendee(race3, ir181, RaceAttendee.Type.PILOT));
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			// 1
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco666)); //$NON-NLS-1$
			// 2
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir053)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir016)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir077)); //$NON-NLS-1$
			// 3
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco666)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			// 4
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir016)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			// 5
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco666)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir077)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir053)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco153)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir016)); //$NON-NLS-1$
			raceDAO.persist(race3);

			DatabaseSession.commit();

			_race3 = race3;
		} finally {
			db.endSession();
		}
	}

	protected void createRace4Data() throws Exception {
		createEvent1Data();

		if (_race4 != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);

			Race race4 = new Race(event1, RACE4_NAME, RACE4_DESC);
			event1.getRaces().add(race4);
			race4.getAttendees().put(sco060, new RaceAttendee(race4, sco060, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco116, new RaceAttendee(race4, sco116, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco153, new RaceAttendee(race4, sco153, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco159, new RaceAttendee(race4, sco159, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco528, new RaceAttendee(race4, sco528, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco666, new RaceAttendee(race4, sco666, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(sco808, new RaceAttendee(race4, sco808, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(ir001, new RaceAttendee(race4, ir001, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(ir014, new RaceAttendee(race4, ir014, RaceAttendee.Type.PILOT));
			RaceAttendee att016 = new RaceAttendee(race4, ir016, RaceAttendee.Type.PILOT);
			att016.getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC, "Hit a mark")); //$NON-NLS-1$
			race4.getAttendees().put(ir016, att016);
			race4.getAttendees().put(ir025, new RaceAttendee(race4, ir025, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(ir053, new RaceAttendee(race4, ir053, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(ir077, new RaceAttendee(race4, ir077, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(ir085, new RaceAttendee(race4, ir085, RaceAttendee.Type.PILOT));
			race4.getAttendees().put(ir181, new RaceAttendee(race4, ir181, RaceAttendee.Type.PILOT));
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			// 1
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			// 2
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir077)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir053)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir016)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco666)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			// 3
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir077)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			// 4
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco666)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir016)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir053)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir077)); //$NON-NLS-1$
			raceDAO.persist(race4);

			DatabaseSession.commit();

			_race4 = race4;
		} finally {
			db.endSession();
		}
	}

	protected void createRace5Data() throws Exception {
		createEvent1Data();

		if (_race5 != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);

			Race race5 = new Race(event1, RACE5_NAME, RACE5_DESC);
			event1.getRaces().add(race5);
			race5.getAttendees().put(sco060, new RaceAttendee(race5, sco060, RaceAttendee.Type.V_SCORER));
			race5.getAttendees().put(sco116, new RaceAttendee(race5, sco116, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco153, new RaceAttendee(race5, sco153, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco159, new RaceAttendee(race5, sco159, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco528, new RaceAttendee(race5, sco528, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco666, new RaceAttendee(race5, sco666, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(sco808, new RaceAttendee(race5, sco808, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(ir001, new RaceAttendee(race5, ir001, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(ir014, new RaceAttendee(race5, ir014, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(ir016, new RaceAttendee(race5, ir016, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(ir025, new RaceAttendee(race5, ir025, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(ir053, new RaceAttendee(race5, ir053, RaceAttendee.Type.PILOT));
			race5.getAttendees().put(ir077, new RaceAttendee(race5, ir077, RaceAttendee.Type.PILOT));
			RaceAttendee att085 = new RaceAttendee(race5, ir085, RaceAttendee.Type.PILOT);
			att085.getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC, "Hit a mark")); //$NON-NLS-1$
			race5.getAttendees().put(ir085, att085);
			race5.getAttendees().put(ir181, new RaceAttendee(race5, ir181, RaceAttendee.Type.PILOT));
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			// 1
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir077)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			// 2
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir053)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco666)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			// 3
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir077)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco666)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			// 4
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			// 5
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir053)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir077)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			// 6
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			// 7
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir077)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir016)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir053)); //$NON-NLS-1$
			raceDAO.persist(race5);

			DatabaseSession.commit();

			_race5 = race5;
		} finally {
			db.endSession();
		}
	}

	protected void createRace6Data() throws Exception {
		createEvent1Data();

		if (_race6 != null) {
			return;
		}

		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find(SERIES_NAME);
			Event event1 = eventDAO.find(series, EVENT1_NAME);

			Race race6 = new Race(event1, RACE6_NAME, RACE6_DESC);
			event1.getRaces().add(race6);
			race6.getAttendees().put(sco060, new RaceAttendee(race6, sco060, RaceAttendee.Type.V_SCORER));
			race6.getAttendees().put(sco116, new RaceAttendee(race6, sco116, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco153, new RaceAttendee(race6, sco153, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco159, new RaceAttendee(race6, sco159, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco528, new RaceAttendee(race6, sco528, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(sco666, new RaceAttendee(race6, sco666, RaceAttendee.Type.PILOT));
			RaceAttendee att808 = new RaceAttendee(race6, sco808, RaceAttendee.Type.PILOT);
			att808.getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC, "Went over IR53's lines")); //$NON-NLS-1$
			race6.getAttendees().put(sco808, att808);
			race6.getAttendees().put(ir001, new RaceAttendee(race6, ir001, RaceAttendee.Type.PILOT));
			RaceAttendee att014 = new RaceAttendee(race6, ir014, RaceAttendee.Type.PILOT);
			att014.getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC, "Wrong side of red line")); //$NON-NLS-1$
			race6.getAttendees().put(ir014, att014);
			race6.getAttendees().put(ir016, new RaceAttendee(race6, ir016, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(ir025, new RaceAttendee(race6, ir025, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(ir053, new RaceAttendee(race6, ir053, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(ir077, new RaceAttendee(race6, ir077, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(ir085, new RaceAttendee(race6, ir085, RaceAttendee.Type.PILOT));
			race6.getAttendees().put(ir181, new RaceAttendee(race6, ir181, RaceAttendee.Type.PILOT));
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			// 1
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			// 2
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir077)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir053)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			// 3
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir077)); //$NON-NLS-1$
			// 4
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir053)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir016)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			// 5
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco153)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir016)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			// 6
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir014)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir016)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			// 7
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco116)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco528)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir025)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir053)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir016)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir181)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco808)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir001)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", sco159)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", ir085)); //$NON-NLS-1$
			raceDAO.persist(race6);

			DatabaseSession.commit();

			_race6 = race6;
		} finally {
			db.endSession();
		}
	}
}
