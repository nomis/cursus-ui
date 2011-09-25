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

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import eu.lp0.cursus.db.data.AbstractEntity;
import eu.lp0.cursus.db.data.RaceHierarchy;

public class RaceHierarchyDAO<E extends AbstractEntity & RaceHierarchy> extends AbstractDAO<E> {
	public RaceHierarchyDAO(Class<E> clazz) {
		super(clazz);
	}

	public boolean isNameOk(E entity, String newName) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<E> q = cb.createQuery(clazz);
		Root<E> s = q.from(clazz);
		q.select(s);
		q.where(cb.notEqual(s.get("id"), entity.getId()), cb.equal(s.get("name"), newName)); //$NON-NLS-1$ //$NON-NLS-2$

		TypedQuery<E> tq = em.createQuery(q);
		return tq.getResultList().isEmpty();
	}
}