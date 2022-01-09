/*
	cursus - Race series management program
	Copyright 2011, 2013-2014  Simon Arlott

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
package uk.uuid.cursus.ui.table;

import java.util.Locale;

import uk.uuid.cursus.i18n.Messages;

import com.google.common.base.Objects;

import uk.uuid.cursus.db.data.Entity;
import uk.uuid.cursus.db.data.Penalty;
import uk.uuid.cursus.db.data.Pilot;
import uk.uuid.cursus.db.data.Race;
import uk.uuid.cursus.db.data.RaceAttendee;

public final class RaceAttendeePenalty implements Entity {
	private RaceAttendee dbAttendee;
	private Pilot pilot;
	private Race race;
	private Penalty dbPenalty;
	private final Penalty penalty;

	public RaceAttendeePenalty(Race race) {
		this.dbAttendee = null;
		this.pilot = null;
		this.race = race;
		this.dbPenalty = null;
		this.penalty = new Penalty(Penalty.Type.AUTOMATIC);
	}

	public RaceAttendeePenalty(RaceAttendee attendee, Penalty penalty) {
		this.dbAttendee = attendee;
		this.pilot = attendee.getPilot();
		this.race = attendee.getRace();
		this.dbPenalty = penalty.clone();
		this.penalty = penalty.clone();
	}

	public RaceAttendee getDatabaseAttendee() {
		return dbAttendee;
	}

	public void setDatabaseAttendee(RaceAttendee attendee) {
		assert (attendee == null || attendee.getRace().equals(race));
		this.dbAttendee = attendee;
	}

	public Penalty getDatabasePenalty() {
		return dbPenalty;
	}

	public void setDatabasePenalty(Penalty penalty) {
		this.dbPenalty = penalty != null ? penalty.clone() : null;
	}

	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		assert (this.race.equals(race));
		this.race = race;
	}

	public Penalty getPenalty() {
		return penalty;
	}

	@Override
	public Long getId() {
		return (long)super.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof RaceAttendeePenalty) {
			RaceAttendeePenalty p = (RaceAttendeePenalty)o;
			return Objects.equal(getDatabaseAttendee(), p.getDatabaseAttendee()) && Objects.equal(getPilot(), p.getPilot())
					&& Objects.equal(getDatabasePenalty(), p.getDatabasePenalty()) && Objects.equal(getPenalty(), p.getPenalty())
					&& Objects.equal(getRace(), p.getRace());
		}
		return false;
	}

	@Override
	public int hashCode() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		boolean one = penalty.getValue() == 1 || penalty.getValue() == -1;
		return String.format(Messages.getString("attendee.penalty." + penalty.getType().name().toLowerCase(Locale.ENGLISH) + (one ? "1" : "")), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new PilotWrapper(getPilot()), penalty.getValue());
	}
}