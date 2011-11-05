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
package eu.lp0.cursus.test;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lp0.cursus.db.Database;
import eu.lp0.cursus.db.MemoryDatabase;
import eu.lp0.cursus.db.dao.ClassDAO;
import eu.lp0.cursus.db.dao.CursusDAO;
import eu.lp0.cursus.db.dao.EventDAO;
import eu.lp0.cursus.db.dao.PilotDAO;
import eu.lp0.cursus.db.dao.RaceDAO;
import eu.lp0.cursus.db.dao.RaceNumberDAO;
import eu.lp0.cursus.db.dao.SeriesDAO;

public abstract class AbstractDatabaseTest {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	protected ClassDAO classDAO = new ClassDAO();
	protected CursusDAO cursusDAO = new CursusDAO();
	protected EventDAO eventDAO = new EventDAO();
	protected PilotDAO pilotDAO = new PilotDAO();
	protected RaceDAO raceDAO = new RaceDAO();
	protected RaceNumberDAO raceNumberDAO = new RaceNumberDAO();
	protected SeriesDAO seriesDAO = new SeriesDAO();

	protected Database db;

	@Before
	public void createDatabase() throws Exception {
		db = new MemoryDatabase();
	}

	@After
	public void closeDatabase() {
		db.close(true);
	}
}