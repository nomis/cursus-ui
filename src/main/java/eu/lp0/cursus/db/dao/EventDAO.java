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

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Series;

public class EventDAO extends NamedEntityDAO<Event> {
	public EventDAO() {
		super(Event.class);
	}

	public Event find(Series series, String name) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Event> q = cb.createQuery(Event.class);
		Root<Event> s = q.from(Event.class);
		q.select(s);
		q.where(cb.equal(s.get("series"), series)); //$NON-NLS-1$
		q.where(cb.equal(s.get("name"), name)); //$NON-NLS-1$

		TypedQuery<Event> tq = em.createQuery(q);
		return tq.getSingleResult();
	}

	@Override
	protected Predicate[] withParentRestriction(CriteriaBuilder cb, Root<Event> e, Event event, Predicate... predicates) {
		predicates = Arrays.copyOf(predicates, predicates.length + 1);
		predicates[predicates.length - 1] = cb.equal(e.get("series"), event.getSeries()); //$NON-NLS-1$
		return predicates;
	}
}