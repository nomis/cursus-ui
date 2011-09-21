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
package eu.lp0.cursus.db.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import eu.lp0.cursus.db.InvalidDatabaseException;
import eu.lp0.cursus.db.data.Cursus;

public class CursusDAO extends AbstractDAO<Cursus> {
	public Cursus findSingleton() throws InvalidDatabaseException {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Cursus> q = cb.createQuery(Cursus.class);
		Root<Cursus> c = q.from(Cursus.class);
		q.select(c);

		TypedQuery<Cursus> tq = em.createQuery(q);
		List<Cursus> rs = tq.getResultList();

		if (rs.size() > 1) {
			throw new InvalidDatabaseException("Too many cursus rows: " + rs.size()); //$NON-NLS-1$
		}

		return rs.isEmpty() ? null : rs.get(0);
	}
}