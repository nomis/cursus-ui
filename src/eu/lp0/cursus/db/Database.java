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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lp0.cursus.db.data.Cursus;
import eu.lp0.cursus.util.Constants;

public abstract class Database {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final String name;
	private final EntityManagerFactory emf;

	public String getName() {
		return name;
	}

	protected Database(String name, String url, String user, String password) throws SQLException, DatabaseVersionException {
		this.name = name;

		log.info("Connecting to database \"" + name + "\" at \"" + url + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		Properties conn = new Properties();
		conn.setProperty("javax.persistence.jdbc.url", url); //$NON-NLS-1$
		conn.setProperty("javax.persistence.jdbc.user", user); //$NON-NLS-1$
		conn.setProperty("javax.persistence.jdbc.password", password); //$NON-NLS-1$
		emf = Persistence.createEntityManagerFactory(getClass().getPackage().getName(), conn);

		initDatabase();
	}

	private void initDatabase() throws DatabaseVersionException {
		EntityManager em = getEM();
		try {
			em.getTransaction().begin();

			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Cursus> q = cb.createQuery(Cursus.class);
			Root<Cursus> c = q.from(Cursus.class);
			q.select(c);
			q.orderBy(cb.desc(c.get("version"))); //$NON-NLS-1$

			TypedQuery<Cursus> tq = em.createQuery(q);
			List<Cursus> rs = tq.getResultList();

			if (rs.isEmpty()) {
				Cursus cursus = new Cursus();
				cursus.setVersion(DatabaseVersion.getLatest());
				cursus.setDescription(Constants.APP_DESC);
				em.persist(cursus);
			} else if (rs.get(0).getVersion() != DatabaseVersion.getLatest()) {
				throw new DatabaseVersionException(rs.get(0));
			}

			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public EntityManager getEM() {
		return emf.createEntityManager();
	}

	public abstract boolean isSaved();

	public boolean close(boolean force) {
		// TODO consider database saved if all connections in the pool are closed
		return force || !isSaved();
	}
}