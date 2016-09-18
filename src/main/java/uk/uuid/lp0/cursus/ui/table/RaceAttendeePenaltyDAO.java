/*
	cursus - Race series management program
	Copyright 2011, 2014  Simon Arlott

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU Affero General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU Affero General Public License for more details.

	You should have received a copy of the GNU Affero General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uk.uuid.lp0.cursus.ui.table;

import java.util.List;

import uk.uuid.cursus.db.dao.EntityDAO;
import uk.uuid.cursus.db.dao.PilotDAO;
import uk.uuid.cursus.db.dao.RaceAttendeeDAO;
import uk.uuid.cursus.db.dao.RaceDAO;
import uk.uuid.cursus.db.data.RaceAttendee;

public class RaceAttendeePenaltyDAO implements EntityDAO<RaceAttendeePenalty> {
	private static final PilotDAO pilotDAO = new PilotDAO();
	private static final RaceDAO raceDAO = new RaceDAO();
	private static final RaceAttendeeDAO raceAttendeeDAO = new RaceAttendeeDAO();

	@Override
	public void persist(RaceAttendeePenalty entity) {
		// Simple case where the pilot has not yet been set
		if (entity.getPilot() == null) {
			remove(entity);
			return;
		}

		// Determine where the penalty was
		RaceAttendee srcAttendee;
		if (entity.getDatabaseAttendee() != null) {
			srcAttendee = raceAttendeeDAO.get(entity.getDatabaseAttendee());
		} else {
			srcAttendee = null;
		}

		// Determine where the penalty should be
		RaceAttendee dstAttendee;
		if (srcAttendee != null && srcAttendee.getPilot().equals(entity.getPilot())) {
			dstAttendee = srcAttendee;
		} else {
			dstAttendee = raceAttendeeDAO.get(entity.getRace().getAttendees().get(entity.getPilot()));
		}

		// Remove the old penalty if it was persisted
		if (srcAttendee != null) {
			srcAttendee.getPenalties().remove(entity.getDatabasePenalty());

			if (!srcAttendee.equals(dstAttendee)) {
				// Optmise by not persisting twice on the same attendee
				raceAttendeeDAO.persist(srcAttendee);
			}
		}

		// Add the new penalty and persist it
		dstAttendee.getPenalties().add(entity.getPenalty());
		// The persistence layer should optimise this if the penalty was only modified, not moved
		raceAttendeeDAO.persist(dstAttendee);

		// Store the new location of the penalty
		entity.setDatabaseAttendee(dstAttendee);
		entity.setDatabasePenalty(entity.getPenalty());
	}

	@Override
	public RaceAttendeePenalty merge(RaceAttendeePenalty entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void remove(RaceAttendeePenalty entity) {
		// Nothing has been persisted unless the attendee is set
		if (entity.getDatabaseAttendee() != null) {
			RaceAttendee attendee = raceAttendeeDAO.get(entity.getDatabaseAttendee());
			attendee.getPenalties().remove(entity.getPenalty());
			raceAttendeeDAO.persist(attendee);
			entity.setDatabaseAttendee(null);
			entity.setDatabasePenalty(null);
		}
	}

	@Override
	public void detach(RaceAttendeePenalty entity) {
		if (entity.getDatabaseAttendee() != null) {
			raceAttendeeDAO.detach(entity.getDatabaseAttendee());
		}
		if (entity.getPilot() != null) {
			pilotDAO.detach(entity.getPilot());
		}
		raceDAO.detach(entity.getRace());
	}

	@Override
	public RaceAttendeePenalty get(RaceAttendeePenalty entity) {
		return entity;
	}

	@Override
	public List<RaceAttendeePenalty> findAll() {
		throw new UnsupportedOperationException();
	}
}