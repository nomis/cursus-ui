/*
	cursus - Race series management program
	Copyright 2011,2013  Simon Arlott

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
package eu.lp0.cursus.test.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lp0.cursus.db.Database;
import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.ClassDAO;
import eu.lp0.cursus.db.dao.CursusDAO;
import eu.lp0.cursus.db.dao.EventDAO;
import eu.lp0.cursus.db.dao.PilotDAO;
import eu.lp0.cursus.db.dao.RaceAttendeeDAO;
import eu.lp0.cursus.db.dao.RaceDAO;
import eu.lp0.cursus.db.dao.RaceNumberDAO;
import eu.lp0.cursus.db.dao.SeriesDAO;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;

public abstract class AbstractDataTest {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	protected ClassDAO classDAO = new ClassDAO();
	protected CursusDAO cursusDAO = new CursusDAO();
	protected EventDAO eventDAO = new EventDAO();
	protected PilotDAO pilotDAO = new PilotDAO();
	protected RaceDAO raceDAO = new RaceDAO();
	protected RaceAttendeeDAO raceAttendeeDAO = new RaceAttendeeDAO();
	protected RaceNumberDAO raceNumberDAO = new RaceNumberDAO();
	protected SeriesDAO seriesDAO = new SeriesDAO();

	protected String DEFAULT_SERIES = "Test Series"; //$NON-NLS-1$
	protected String DEFAULT_EVENT = "Test Event"; //$NON-NLS-1$
	protected String DEFAULT_RACE = "Test Race"; //$NON-NLS-1$

	static {
		try {
			assert (false);
			throw new Error("Assertions disabled"); //$NON-NLS-1$
		} catch (AssertionError e) {
		}
	}

	protected void populateDefaultData(Database db) {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = new Series(DEFAULT_SERIES);
			Event event = new Event(series, DEFAULT_EVENT);
			series.getEvents().add(event);
			Race race = new Race(event, DEFAULT_RACE);
			event.getRaces().add(race);
			seriesDAO.persist(series);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}