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

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lp0.cursus.db.Database;
import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.MemoryDatabase;
import eu.lp0.cursus.db.dao.EventDAO;
import eu.lp0.cursus.db.dao.RaceDAO;
import eu.lp0.cursus.db.dao.SeriesDAO;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceAttendee;
import eu.lp0.cursus.db.data.Series;

public abstract class AbstractSeries2011 {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	protected final String SERIES_NAME = "SPKA Race Series 2011/12"; //$NON-NLS-1$
	protected final String EVENT1_NAME = "Race Event 1"; //$NON-NLS-1$
	protected final String RACE1_NAME = "Race 1"; //$NON-NLS-1$

	protected SeriesDAO seriesDAO = new SeriesDAO();
	protected EventDAO eventDAO = new EventDAO();
	protected RaceDAO raceDAO = new RaceDAO();

	protected Database db;
	protected Pilot sco018;
	protected Pilot sco019;
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

	private Series _series;
	private Event _event1;
	private Race _race1;

	@Before
	public void createDatabase() throws Exception {
		db = new MemoryDatabase();
	}

	@After
	public void closeDatabase() {
		db.close(true);
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

			// Create the 2011/12 series
			Series series = new Series(SERIES_NAME);

			// Add all the pilots
			sco018 = new Pilot(series, "SCO018"); //$NON-NLS-1$
			series.getPilots().add(sco018);

			sco019 = new Pilot(series, "SCO019"); //$NON-NLS-1$
			series.getPilots().add(sco019);

			sco060 = new Pilot(series, "SCO060"); //$NON-NLS-1$
			series.getPilots().add(sco060);

			sco068 = new Pilot(series, "SCO068"); //$NON-NLS-1$
			series.getPilots().add(sco068);

			sco081 = new Pilot(series, "SCO081"); //$NON-NLS-1$
			series.getPilots().add(sco081);

			sco087 = new Pilot(series, "SCO087"); //$NON-NLS-1$
			series.getPilots().add(sco087);

			sco116 = new Pilot(series, "SCO116"); //$NON-NLS-1$
			series.getPilots().add(sco116);

			sco136 = new Pilot(series, "SCO136"); //$NON-NLS-1$
			series.getPilots().add(sco136);

			sco153 = new Pilot(series, "SCO153"); //$NON-NLS-1$
			series.getPilots().add(sco153);

			sco156 = new Pilot(series, "SCO156"); //$NON-NLS-1$
			series.getPilots().add(sco156);

			sco158 = new Pilot(series, "SCO158"); //$NON-NLS-1$
			series.getPilots().add(sco158);

			sco159 = new Pilot(series, "SCO159"); //$NON-NLS-1$
			series.getPilots().add(sco159);

			sco179 = new Pilot(series, "SCO179"); //$NON-NLS-1$
			series.getPilots().add(sco179);

			sco197 = new Pilot(series, "SCO197"); //$NON-NLS-1$
			series.getPilots().add(sco197);

			sco198 = new Pilot(series, "SCO198"); //$NON-NLS-1$
			series.getPilots().add(sco198);

			sco200 = new Pilot(series, "SCO200"); //$NON-NLS-1$
			series.getPilots().add(sco200);

			sco248 = new Pilot(series, "SCO248"); //$NON-NLS-1$
			series.getPilots().add(sco248);

			sco249 = new Pilot(series, "SCO249"); //$NON-NLS-1$
			series.getPilots().add(sco249);

			sco320 = new Pilot(series, "SCO320"); //$NON-NLS-1$
			series.getPilots().add(sco320);

			sco467 = new Pilot(series, "SCO467"); //$NON-NLS-1$
			series.getPilots().add(sco467);

			sco528 = new Pilot(series, "SCO528"); //$NON-NLS-1$
			series.getPilots().add(sco528);

			sco808 = new Pilot(series, "SCO808"); //$NON-NLS-1$
			series.getPilots().add(sco808);

			// Save
			seriesDAO.persist(series);

			DatabaseSession.commit();

			_series = series;
		} finally {
			db.endSession();
		}

		createEvent1Data();
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
			raceDAO.persist(race1);

			DatabaseSession.commit();

			_race1 = race1;
		} finally {
			db.endSession();
		}
	}
}