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
package eu.lp0.cursus.db;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lp0.cursus.db.dao.CursusDAO;
import eu.lp0.cursus.db.dao.SeriesDAO;
import eu.lp0.cursus.db.data.Cursus;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.i18n.Messages;
import eu.lp0.cursus.util.Constants;

public abstract class Database {
	public static final String UNTITLED_SERIES = "series.untitled"; //$NON-NLS-1$
	public static final String UNTITLED_EVENT = "event.untitled"; //$NON-NLS-1$
	public static final String UNTITLED_RACE = "race.untitled"; //$NON-NLS-1$

	private final Logger log = LoggerFactory.getLogger(getClass());
	private final String name;
	private final DatabaseSession sessionFactory;

	private static final CursusDAO cursusDAO = new CursusDAO();
	private static final SeriesDAO seriesDAO = new SeriesDAO();

	protected Database(String name, String url, String user, String password, boolean checkSchema) throws SQLException, DatabaseVersionException,
			InvalidDatabaseException {
		this.name = name;

		log.info("Connecting to database \"" + name + "\" at \"" + url + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		Properties config = new Properties();
		config.setProperty("javax.persistence.jdbc.url", url); //$NON-NLS-1$
		config.setProperty("javax.persistence.jdbc.user", user); //$NON-NLS-1$
		config.setProperty("javax.persistence.jdbc.password", password); //$NON-NLS-1$

		sessionFactory = initSession(config, checkSchema);

		initDatabase();
	}

	public String getName() {
		return name;
	}

	public final void startSession() {
		sessionFactory.startSession();
	}

	public final void endSession() {
		sessionFactory.endSession();
	}

	private DatabaseSession initSession(Properties config, boolean checkSchema) throws InvalidDatabaseException {
		final String CHECK = "/check"; //$NON-NLS-1$
		final String UPDATE = "/update"; //$NON-NLS-1$

		if (checkSchema) {
			DatabaseSession ro = new DatabaseSession(Persistence.createEntityManagerFactory(getClass().getPackage().getName() + CHECK, config));

			ro.startSession();
			try {
				DatabaseSession.begin();
				checkVersion(true);
				DatabaseSession.commit();
			} finally {
				ro.endSession();
			}
		}

		DatabaseSession rw = new DatabaseSession(Persistence.createEntityManagerFactory(getClass().getPackage().getName() + UPDATE, config));
		rw.startSession();
		try {
			DatabaseSession.begin();
			checkVersion(false);
			DatabaseSession.commit();
		} finally {
			rw.endSession();
		}
		return rw;
	}

	private void checkVersion(boolean checkSchema) throws InvalidDatabaseException {
		Cursus cursus = cursusDAO.findSingleton();
		if (cursus == null) {
			log.info("Database \"" + name + "\" has no version record, setting to " + DatabaseVersion.getLatest()); //$NON-NLS-1$ //$NON-NLS-2$

			cursus = new Cursus(DatabaseVersion.getLatest().asLong(), Constants.APP_DESC);
			cursusDAO.persist(cursus);
		} else {
			log.info("Database \"" + name + "\" has version " + DatabaseVersion.parseLong(cursus.getVersion())); //$NON-NLS-1$ //$NON-NLS-2$
			if (cursus.getVersion() > DatabaseVersion.getLatest().asLong()) {
				if (checkSchema) {
					// TODO support upgrading
					throw new DatabaseVersionException(cursus);
				} else {
					cursus.setVersion(DatabaseVersion.getLatest().asLong());
					log.info("Database \"" + name + "\" version record upgraded to " + DatabaseVersion.getLatest()); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		}
	}

	private void initDatabase() throws InvalidDatabaseException {
		startSession();
		try {
			DatabaseSession.begin();

			List<Series> seriesList = seriesDAO.findAll();
			if (seriesList.isEmpty()) {
				log.info("Database \"" + name + "\" has no series, creating untitled series"); //$NON-NLS-1$ //$NON-NLS-2$

				Series series = new Series(Messages.getString(UNTITLED_SERIES));
				Event event = new Event(series, Messages.getString(UNTITLED_EVENT));
				series.getEvents().add(event);
				Race race = new Race(event, Messages.getString(UNTITLED_RACE));
				event.getRaces().add(race);
				seriesDAO.persist(series);
			}

			DatabaseSession.commit();
		} finally {
			endSession();
		}
	}

	public abstract boolean isSaved();

	public boolean close(boolean force) {
		// TODO consider database saved if all connections in the pool are closed
		return force || !isSaved();
	}
}