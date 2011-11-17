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
package org.fisly.cursus.test.europe_2011;

import org.fisly.cursus.scoring.FISLYConstants;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Gender;
import eu.lp0.cursus.db.data.Penalty;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceAttendee;
import eu.lp0.cursus.db.data.RaceEvent;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.scoring.Scorer;
import eu.lp0.cursus.scoring.ScorerFactory;
import eu.lp0.cursus.test.db.AbstractDatabaseTest;

public abstract class AbstractSeries2011 extends AbstractDatabaseTest {
	protected final String SERIES_NAME = "FISLY European Championships 2011"; //$NON-NLS-1$
	protected final int SERIES_PILOTS = 74;
	protected final int SERIES_M_FLEET = 61;
	protected final int SERIES_F_FLEET = 13;

	protected final String EVENT1_NAME = "Class 8"; //$NON-NLS-1$
	protected final int EVENT1_M_FLEET = 61;
	protected final int EVENT1_F_FLEET = 12;
	protected final String RACE1_NAME = "Race 1"; //$NON-NLS-1$
	protected final String RACE2_NAME = "Race 2"; //$NON-NLS-1$
	protected final String RACE3_NAME = "Race 3"; //$NON-NLS-1$
	protected final String RACE4_NAME = "Race 4"; //$NON-NLS-1$
	protected final String RACE5_NAME = "Race 5"; //$NON-NLS-1$
	protected final String RACE6_NAME = "Race 6"; //$NON-NLS-1$

	protected Scorer scorer = ScorerFactory.newScorer(FISLYConstants.UUID_2010);

	protected Pilot F01;
	protected Pilot F02;
	protected Pilot F03;
	protected Pilot F04;
	protected Pilot F05;
	protected Pilot F06;
	protected Pilot F07;
	protected Pilot F08;
	protected Pilot F09;
	protected Pilot F10;
	protected Pilot F11;
	protected Pilot F12;
	protected Pilot F13;
	protected Pilot G21;
	protected Pilot G22;
	protected Pilot G23;
	protected Pilot G24;
	protected Pilot G25;
	protected Pilot G26;
	protected Pilot G27;
	protected Pilot G28;
	protected Pilot G29;
	protected Pilot G30;
	protected Pilot G31;
	protected Pilot G32;
	protected Pilot G33;
	protected Pilot G34;
	protected Pilot G35;
	protected Pilot G36;
	protected Pilot K41;
	protected Pilot K42;
	protected Pilot K43;
	protected Pilot K44;
	protected Pilot K46;
	protected Pilot K45;
	protected Pilot K47;
	protected Pilot K48;
	protected Pilot K49;
	protected Pilot K50;
	protected Pilot K51;
	protected Pilot K52;
	protected Pilot K53;
	protected Pilot H61;
	protected Pilot H62;
	protected Pilot H63;
	protected Pilot H64;
	protected Pilot H65;
	protected Pilot H66;
	protected Pilot H67;
	protected Pilot H69;
	protected Pilot H70;
	protected Pilot B81;
	protected Pilot B82;
	protected Pilot IR91;
	protected Pilot IR92;
	protected Pilot IR93;
	protected Pilot SCO101;
	protected Pilot SCO102;
	protected Pilot SCO103;
	protected Pilot SCO104;
	protected Pilot SCO105;
	protected Pilot SCO106;
	protected Pilot SCO107;
	protected Pilot SCO108;
	protected Pilot SCO109;
	protected Pilot SCO110;
	protected Pilot SCO113;
	protected Pilot E111;
	protected Pilot CZ131;
	protected Pilot CZ132;
	protected Pilot CZ133;
	protected Pilot CZ134;
	protected Pilot CZ135;
	protected Pilot SUI152;

	private Series _series;
	private Event _event1;
	private Race _race1;
	private Race _race2;
	private Race _race3;
	private Race _race4;
	private Race _race5;
	private Race _race6;

	private void registerPilots(Race race) {
		race.getAttendees().put(F01, new RaceAttendee(race, F01, RaceAttendee.Type.PILOT));
		race.getAttendees().put(F02, new RaceAttendee(race, F02, RaceAttendee.Type.PILOT));
		race.getAttendees().put(F03, new RaceAttendee(race, F03, RaceAttendee.Type.PILOT));
		race.getAttendees().put(F04, new RaceAttendee(race, F04, RaceAttendee.Type.PILOT));
		race.getAttendees().put(F05, new RaceAttendee(race, F05, RaceAttendee.Type.PILOT));
		race.getAttendees().put(F06, new RaceAttendee(race, F06, RaceAttendee.Type.PILOT));
		race.getAttendees().put(F07, new RaceAttendee(race, F07, RaceAttendee.Type.PILOT));
		race.getAttendees().put(F08, new RaceAttendee(race, F08, RaceAttendee.Type.PILOT));
		race.getAttendees().put(F09, new RaceAttendee(race, F09, RaceAttendee.Type.PILOT));
		race.getAttendees().put(F10, new RaceAttendee(race, F10, RaceAttendee.Type.PILOT));
		race.getAttendees().put(F11, new RaceAttendee(race, F11, RaceAttendee.Type.PILOT));
		race.getAttendees().put(F12, new RaceAttendee(race, F12, RaceAttendee.Type.PILOT));
		race.getAttendees().put(F13, new RaceAttendee(race, F13, RaceAttendee.Type.PILOT));
		race.getAttendees().put(G21, new RaceAttendee(race, G21, RaceAttendee.Type.PILOT));
		race.getAttendees().put(G22, new RaceAttendee(race, G22, RaceAttendee.Type.PILOT));
		race.getAttendees().put(G23, new RaceAttendee(race, G23, RaceAttendee.Type.PILOT));
		race.getAttendees().put(G24, new RaceAttendee(race, G24, RaceAttendee.Type.PILOT));
		race.getAttendees().put(G25, new RaceAttendee(race, G25, RaceAttendee.Type.PILOT));
		race.getAttendees().put(G26, new RaceAttendee(race, G26, RaceAttendee.Type.PILOT));
		race.getAttendees().put(G27, new RaceAttendee(race, G27, RaceAttendee.Type.PILOT));
		race.getAttendees().put(G28, new RaceAttendee(race, G28, RaceAttendee.Type.PILOT));
		race.getAttendees().put(G29, new RaceAttendee(race, G29, RaceAttendee.Type.PILOT));
		race.getAttendees().put(G30, new RaceAttendee(race, G30, RaceAttendee.Type.PILOT));
		race.getAttendees().put(G31, new RaceAttendee(race, G31, RaceAttendee.Type.PILOT));
		race.getAttendees().put(G32, new RaceAttendee(race, G32, RaceAttendee.Type.PILOT));
		race.getAttendees().put(G33, new RaceAttendee(race, G33, RaceAttendee.Type.PILOT));
		race.getAttendees().put(G34, new RaceAttendee(race, G34, RaceAttendee.Type.PILOT));
		race.getAttendees().put(G35, new RaceAttendee(race, G35, RaceAttendee.Type.PILOT));
		race.getAttendees().put(G36, new RaceAttendee(race, G36, RaceAttendee.Type.PILOT));
		race.getAttendees().put(K41, new RaceAttendee(race, K41, RaceAttendee.Type.PILOT));
		race.getAttendees().put(K42, new RaceAttendee(race, K42, RaceAttendee.Type.PILOT));
		race.getAttendees().put(K43, new RaceAttendee(race, K43, RaceAttendee.Type.PILOT));
		race.getAttendees().put(K44, new RaceAttendee(race, K44, RaceAttendee.Type.PILOT));
		race.getAttendees().put(K46, new RaceAttendee(race, K46, RaceAttendee.Type.PILOT));
		race.getAttendees().put(K45, new RaceAttendee(race, K45, RaceAttendee.Type.PILOT));
		race.getAttendees().put(K47, new RaceAttendee(race, K47, RaceAttendee.Type.PILOT));
		race.getAttendees().put(K48, new RaceAttendee(race, K48, RaceAttendee.Type.PILOT));
		race.getAttendees().put(K49, new RaceAttendee(race, K49, RaceAttendee.Type.PILOT));
		race.getAttendees().put(K50, new RaceAttendee(race, K50, RaceAttendee.Type.PILOT));
		// race.getAttendees().put(K51, new RaceAttendee(race, K51, RaceAttendee.Type.PILOT));
		race.getAttendees().put(K52, new RaceAttendee(race, K52, RaceAttendee.Type.PILOT));
		race.getAttendees().put(K53, new RaceAttendee(race, K53, RaceAttendee.Type.PILOT));
		race.getAttendees().put(H61, new RaceAttendee(race, H61, RaceAttendee.Type.PILOT));
		race.getAttendees().put(H62, new RaceAttendee(race, H62, RaceAttendee.Type.PILOT));
		race.getAttendees().put(H63, new RaceAttendee(race, H63, RaceAttendee.Type.PILOT));
		race.getAttendees().put(H64, new RaceAttendee(race, H64, RaceAttendee.Type.PILOT));
		race.getAttendees().put(H65, new RaceAttendee(race, H65, RaceAttendee.Type.PILOT));
		race.getAttendees().put(H66, new RaceAttendee(race, H66, RaceAttendee.Type.PILOT));
		race.getAttendees().put(H67, new RaceAttendee(race, H67, RaceAttendee.Type.PILOT));
		race.getAttendees().put(H69, new RaceAttendee(race, H69, RaceAttendee.Type.PILOT));
		race.getAttendees().put(H70, new RaceAttendee(race, H70, RaceAttendee.Type.PILOT));
		race.getAttendees().put(B81, new RaceAttendee(race, B81, RaceAttendee.Type.PILOT));
		race.getAttendees().put(B82, new RaceAttendee(race, B82, RaceAttendee.Type.PILOT));
		race.getAttendees().put(IR91, new RaceAttendee(race, IR91, RaceAttendee.Type.PILOT));
		race.getAttendees().put(IR92, new RaceAttendee(race, IR92, RaceAttendee.Type.PILOT));
		race.getAttendees().put(IR93, new RaceAttendee(race, IR93, RaceAttendee.Type.PILOT));
		race.getAttendees().put(SCO101, new RaceAttendee(race, SCO101, RaceAttendee.Type.PILOT));
		race.getAttendees().put(SCO102, new RaceAttendee(race, SCO102, RaceAttendee.Type.PILOT));
		race.getAttendees().put(SCO103, new RaceAttendee(race, SCO103, RaceAttendee.Type.PILOT));
		race.getAttendees().put(SCO104, new RaceAttendee(race, SCO104, RaceAttendee.Type.PILOT));
		race.getAttendees().put(SCO105, new RaceAttendee(race, SCO105, RaceAttendee.Type.PILOT));
		race.getAttendees().put(SCO106, new RaceAttendee(race, SCO106, RaceAttendee.Type.PILOT));
		race.getAttendees().put(SCO107, new RaceAttendee(race, SCO107, RaceAttendee.Type.PILOT));
		race.getAttendees().put(SCO108, new RaceAttendee(race, SCO108, RaceAttendee.Type.PILOT));
		race.getAttendees().put(SCO109, new RaceAttendee(race, SCO109, RaceAttendee.Type.PILOT));
		race.getAttendees().put(SCO110, new RaceAttendee(race, SCO110, RaceAttendee.Type.PILOT));
		race.getAttendees().put(SCO113, new RaceAttendee(race, SCO113, RaceAttendee.Type.PILOT));
		race.getAttendees().put(E111, new RaceAttendee(race, E111, RaceAttendee.Type.PILOT));
		race.getAttendees().put(CZ131, new RaceAttendee(race, CZ131, RaceAttendee.Type.PILOT));
		race.getAttendees().put(CZ132, new RaceAttendee(race, CZ132, RaceAttendee.Type.PILOT));
		race.getAttendees().put(CZ133, new RaceAttendee(race, CZ133, RaceAttendee.Type.PILOT));
		race.getAttendees().put(CZ134, new RaceAttendee(race, CZ134, RaceAttendee.Type.PILOT));
		race.getAttendees().put(CZ135, new RaceAttendee(race, CZ135, RaceAttendee.Type.PILOT));
		race.getAttendees().put(SUI152, new RaceAttendee(race, SUI152, RaceAttendee.Type.PILOT));
	}

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

			// Create the 2011 series
			Series series = new Series(SERIES_NAME);

			// Add all the pilots
			F01 = new Pilot(series, "F01", Gender.M); //$NON-NLS-1$
			series.getPilots().add(F01);

			F02 = new Pilot(series, "F02", Gender.M); //$NON-NLS-1$
			series.getPilots().add(F02);

			F03 = new Pilot(series, "F03", Gender.M); //$NON-NLS-1$
			series.getPilots().add(F03);

			F04 = new Pilot(series, "F04", Gender.M); //$NON-NLS-1$
			series.getPilots().add(F04);

			F05 = new Pilot(series, "F05", Gender.M); //$NON-NLS-1$
			series.getPilots().add(F05);

			F06 = new Pilot(series, "F06", Gender.M); //$NON-NLS-1$
			series.getPilots().add(F06);

			F07 = new Pilot(series, "F07", Gender.M); //$NON-NLS-1$
			series.getPilots().add(F07);

			F08 = new Pilot(series, "F08", Gender.M); //$NON-NLS-1$
			series.getPilots().add(F08);

			F09 = new Pilot(series, "F09", Gender.M); //$NON-NLS-1$
			series.getPilots().add(F09);

			F10 = new Pilot(series, "F10", Gender.M); //$NON-NLS-1$
			series.getPilots().add(F10);

			F11 = new Pilot(series, "F11", Gender.F); //$NON-NLS-1$
			series.getPilots().add(F11);

			F12 = new Pilot(series, "F12", Gender.F); //$NON-NLS-1$
			series.getPilots().add(F12);

			F13 = new Pilot(series, "F13", Gender.F); //$NON-NLS-1$
			series.getPilots().add(F13);

			G21 = new Pilot(series, "G21", Gender.M); //$NON-NLS-1$
			series.getPilots().add(G21);

			G22 = new Pilot(series, "G22", Gender.M); //$NON-NLS-1$
			series.getPilots().add(G22);

			G23 = new Pilot(series, "G23", Gender.M); //$NON-NLS-1$
			series.getPilots().add(G23);

			G24 = new Pilot(series, "G24", Gender.M); //$NON-NLS-1$
			series.getPilots().add(G24);

			G25 = new Pilot(series, "G25", Gender.M); //$NON-NLS-1$
			series.getPilots().add(G25);

			G26 = new Pilot(series, "G26", Gender.M); //$NON-NLS-1$
			series.getPilots().add(G26);

			G27 = new Pilot(series, "G27", Gender.M); //$NON-NLS-1$
			series.getPilots().add(G27);

			G28 = new Pilot(series, "G28", Gender.M); //$NON-NLS-1$
			series.getPilots().add(G28);

			G29 = new Pilot(series, "G29", Gender.M); //$NON-NLS-1$
			series.getPilots().add(G29);

			G30 = new Pilot(series, "G30", Gender.M); //$NON-NLS-1$
			series.getPilots().add(G30);

			G31 = new Pilot(series, "G31", Gender.F); //$NON-NLS-1$
			series.getPilots().add(G31);

			G32 = new Pilot(series, "G32", Gender.F); //$NON-NLS-1$
			series.getPilots().add(G32);

			G33 = new Pilot(series, "G33", Gender.F); //$NON-NLS-1$
			series.getPilots().add(G33);

			G34 = new Pilot(series, "G34", Gender.F); //$NON-NLS-1$
			series.getPilots().add(G34);

			G35 = new Pilot(series, "G35", Gender.F); //$NON-NLS-1$
			series.getPilots().add(G35);

			G36 = new Pilot(series, "G36", Gender.F); //$NON-NLS-1$
			series.getPilots().add(G36);

			K41 = new Pilot(series, "K41", Gender.M); //$NON-NLS-1$
			series.getPilots().add(K41);

			K42 = new Pilot(series, "K42", Gender.M); //$NON-NLS-1$
			series.getPilots().add(K42);

			K43 = new Pilot(series, "K43", Gender.M); //$NON-NLS-1$
			series.getPilots().add(K43);

			K44 = new Pilot(series, "K44", Gender.M); //$NON-NLS-1$
			series.getPilots().add(K44);

			K46 = new Pilot(series, "K46", Gender.M); //$NON-NLS-1$
			series.getPilots().add(K46);

			K45 = new Pilot(series, "K45", Gender.M); //$NON-NLS-1$
			series.getPilots().add(K45);

			K47 = new Pilot(series, "K47", Gender.M); //$NON-NLS-1$
			series.getPilots().add(K47);

			K48 = new Pilot(series, "K48", Gender.M); //$NON-NLS-1$
			series.getPilots().add(K48);

			K49 = new Pilot(series, "K49", Gender.M); //$NON-NLS-1$
			series.getPilots().add(K49);

			K50 = new Pilot(series, "K50", Gender.M); //$NON-NLS-1$
			series.getPilots().add(K50);

			K51 = new Pilot(series, "K51", Gender.F); //$NON-NLS-1$
			series.getPilots().add(K51);

			K52 = new Pilot(series, "K52", Gender.F); //$NON-NLS-1$
			series.getPilots().add(K52);

			K53 = new Pilot(series, "K53", Gender.F); //$NON-NLS-1$
			series.getPilots().add(K53);

			H61 = new Pilot(series, "H61", Gender.M); //$NON-NLS-1$
			series.getPilots().add(H61);

			H62 = new Pilot(series, "H62", Gender.M); //$NON-NLS-1$
			series.getPilots().add(H62);

			H63 = new Pilot(series, "H63", Gender.M); //$NON-NLS-1$
			series.getPilots().add(H63);

			H64 = new Pilot(series, "H64", Gender.M); //$NON-NLS-1$
			series.getPilots().add(H64);

			H65 = new Pilot(series, "H65", Gender.M); //$NON-NLS-1$
			series.getPilots().add(H65);

			H66 = new Pilot(series, "H66", Gender.M); //$NON-NLS-1$
			series.getPilots().add(H66);

			H67 = new Pilot(series, "H67", Gender.M); //$NON-NLS-1$
			series.getPilots().add(H67);

			H69 = new Pilot(series, "K69", Gender.M); //$NON-NLS-1$
			series.getPilots().add(H69);

			H70 = new Pilot(series, "H70", Gender.M); //$NON-NLS-1$
			series.getPilots().add(H70);

			B81 = new Pilot(series, "B81", Gender.M); //$NON-NLS-1$
			series.getPilots().add(B81);

			B82 = new Pilot(series, "B82", Gender.M); //$NON-NLS-1$
			series.getPilots().add(B82);

			IR91 = new Pilot(series, "IR91", Gender.M); //$NON-NLS-1$
			series.getPilots().add(IR91);

			IR92 = new Pilot(series, "IR92", Gender.M); //$NON-NLS-1$
			series.getPilots().add(IR92);

			IR93 = new Pilot(series, "IR93", Gender.M); //$NON-NLS-1$
			series.getPilots().add(IR93);

			SCO101 = new Pilot(series, "SCO101", Gender.M); //$NON-NLS-1$
			series.getPilots().add(SCO101);

			SCO102 = new Pilot(series, "SCO102", Gender.M); //$NON-NLS-1$
			series.getPilots().add(SCO102);

			SCO103 = new Pilot(series, "SCO103", Gender.M); //$NON-NLS-1$
			series.getPilots().add(SCO103);

			SCO104 = new Pilot(series, "SCO104", Gender.M); //$NON-NLS-1$
			series.getPilots().add(SCO104);

			SCO105 = new Pilot(series, "SCO105", Gender.M); //$NON-NLS-1$
			series.getPilots().add(SCO105);

			SCO106 = new Pilot(series, "SCO106", Gender.M); //$NON-NLS-1$
			series.getPilots().add(SCO106);

			SCO107 = new Pilot(series, "SCO107", Gender.M); //$NON-NLS-1$
			series.getPilots().add(SCO107);

			SCO108 = new Pilot(series, "SCO108", Gender.M); //$NON-NLS-1$
			series.getPilots().add(SCO108);

			SCO109 = new Pilot(series, "SCO109", Gender.M); //$NON-NLS-1$
			series.getPilots().add(SCO109);

			SCO110 = new Pilot(series, "SCO110", Gender.M); //$NON-NLS-1$
			series.getPilots().add(SCO110);

			SCO113 = new Pilot(series, "SCO113", Gender.F); //$NON-NLS-1$
			series.getPilots().add(SCO113);

			E111 = new Pilot(series, "E111", Gender.M); //$NON-NLS-1$
			series.getPilots().add(E111);

			CZ131 = new Pilot(series, "CZ131", Gender.M); //$NON-NLS-1$
			series.getPilots().add(CZ131);

			CZ132 = new Pilot(series, "CZ132", Gender.M); //$NON-NLS-1$
			series.getPilots().add(CZ132);

			CZ133 = new Pilot(series, "CZ133", Gender.M); //$NON-NLS-1$
			series.getPilots().add(CZ133);

			CZ134 = new Pilot(series, "CZ134", Gender.M); //$NON-NLS-1$
			series.getPilots().add(CZ134);

			CZ135 = new Pilot(series, "CZ135", Gender.M); //$NON-NLS-1$
			series.getPilots().add(CZ135);

			SUI152 = new Pilot(series, "SUI152", Gender.M); //$NON-NLS-1$
			series.getPilots().add(SUI152);

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

			Race race1 = new Race(event1, RACE1_NAME);
			registerPilots(race1);
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO107)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO107)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO107)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO107)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.FINISH));
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO107)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
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

			Race race2 = new Race(event1, RACE2_NAME);
			registerPilots(race2);
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO101)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", E111)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO107)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K53)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO107)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO107)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO107)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K53)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO107)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.FINISH));
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO107)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
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

			Race race3 = new Race(event1, RACE3_NAME);
			registerPilots(race3);
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO101)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO107)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO101)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K53)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO101)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K53)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO101)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K53)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO101)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.FINISH));
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO101)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K53)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
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

			Race race4 = new Race(event1, RACE4_NAME);
			registerPilots(race4);
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO101)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", E111)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO101)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO101)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", E111)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO101)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.FINISH));
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ134)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO101)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", E111)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
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

			Race race5 = new Race(event1, RACE5_NAME);
			registerPilots(race5);
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO101)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO107)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO107)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.FINISH));
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO101)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO109)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO107)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race5.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
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

			Race race6 = new Race(event1, RACE6_NAME);
			registerPilots(race6);
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.START));
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO101)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO110)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO113)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO107)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G33)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO108)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO103)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ135)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K49)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ132)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F13)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F04)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G34)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.FINISH));
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G25)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H66)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G22)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K45)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G23)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H67)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F11)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G27)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F06)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F07)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G24)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G28)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR93)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G35)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H69)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G30)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K41)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H70)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G26)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H62)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F05)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR91)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G21)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F09)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SUI152)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F12)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H65)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F08)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ131)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO104)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H64)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K43)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K42)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G29)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B81)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K47)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G36)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K46)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", B82)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO105)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K50)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K48)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", IR92)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H63)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G31)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K44)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F01)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", CZ133)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", G32)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO102)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F02)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO107)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F03)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", F10)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", SCO106)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", K52)); //$NON-NLS-1$
			race6.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", H61)); //$NON-NLS-1$

			// It doesn't matter which penalty races are assigned to for the overall results
			race6.getAttendees().get(F02).getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC));
			race6.getAttendees().get(F07).getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC));
			race6.getAttendees().get(F09).getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC));
			race6.getAttendees().get(G22).getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC));
			race6.getAttendees().get(G23).getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC));
			race6.getAttendees().get(G32).getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC));
			race6.getAttendees().get(H61).getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC));
			race6.getAttendees().get(H66).getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC));
			race6.getAttendees().get(K41).getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC));
			race6.getAttendees().get(K46).getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC));
			race6.getAttendees().get(IR91).getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC));
			race6.getAttendees().get(IR92).getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC));
			race6.getAttendees().get(SCO102).getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC, 2));
			race6.getAttendees().get(SCO106).getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC));
			race6.getAttendees().get(SCO108).getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC));
			race6.getAttendees().get(SCO109).getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC, 2));
			race6.getAttendees().get(CZ134).getPenalties().add(new Penalty(Penalty.Type.AUTOMATIC));
			raceDAO.persist(race6);

			DatabaseSession.commit();

			_race6 = race6;
		} finally {
			db.endSession();
		}
	}
}