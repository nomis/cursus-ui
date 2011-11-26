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

import eu.lp0.cursus.db.data.RaceNumber;

public class RaceNumberDAO extends AbstractEntityDAO<RaceNumber> {
	public RaceNumberDAO() {
		super(RaceNumber.class);
	}

	public boolean isRaceNumberOk(RaceNumber newRaceNumber) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<RaceNumber> q = cb.createQuery(RaceNumber.class);
		Root<RaceNumber> rn = q.from(RaceNumber.class);
		q.select(rn);
		q.where(cb.equal(rn.get("series"), newRaceNumber.getSeries()), cb.notEqual(rn.get("pilot"), newRaceNumber.getPilot()), //$NON-NLS-1$ //$NON-NLS-2$
				cb.equal(rn.get("organisation"), newRaceNumber.getOrganisation()), cb.equal(rn.get("number"), newRaceNumber.getNumber())); //$NON-NLS-1$ //$NON-NLS-2$ 

		TypedQuery<RaceNumber> tq = em.createQuery(q);
		return tq.getResultList().isEmpty();
	}
}