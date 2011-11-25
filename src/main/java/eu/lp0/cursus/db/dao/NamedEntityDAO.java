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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import eu.lp0.cursus.db.data.AbstractEntity;
import eu.lp0.cursus.db.data.NamedEntity;

public class NamedEntityDAO<E extends AbstractEntity & NamedEntity> extends AbstractDAO<E> {
	public NamedEntityDAO(Class<E> clazz) {
		super(clazz);
	}

	public boolean isNameOk(E entity, boolean isUpdate, String newName) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<E> q = cb.createQuery(clazz);
		Root<E> ne = q.from(clazz);
		q.select(ne);
		if (isUpdate) {
			// Exclude this entity when doing an update
			q.where(withParentRestriction(cb, ne, entity, cb.notEqual(ne.get("id"), entity.getId()), cb.equal(ne.get("name"), newName))); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			q.where(withParentRestriction(cb, ne, entity, cb.equal(ne.get("name"), newName))); //$NON-NLS-1$ 
		}

		TypedQuery<E> tq = em.createQuery(q);
		return tq.getResultList().isEmpty();
	}

	protected Predicate[] withParentRestriction(CriteriaBuilder cb, Root<E> rh, E entity, Predicate... predicates) {
		return predicates;
	}
}