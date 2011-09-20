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

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import eu.lp0.cursus.db.data.SeriesClass;

public class SeriesClassDAO extends AbstractDAO<SeriesClass> {
	public List<SeriesClass> getAll() {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<SeriesClass> q = cb.createQuery(SeriesClass.class);
		Root<SeriesClass> c = q.from(SeriesClass.class);
		q.select(c);

		TypedQuery<SeriesClass> tq = em.createQuery(q);
		List<SeriesClass> rs = tq.getResultList();

		Collections.sort(rs);
		return rs;
	}
}