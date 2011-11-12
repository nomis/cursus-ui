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
package eu.lp0.cursus.db.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import eu.lp0.cursus.util.Messages;

/**
 * Race attendees who are pilots
 */
@Entity(name = "race_attendee")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "race_id", "pilot_id" }) })
public class RaceAttendee extends AbstractEntity {
	/**
	 * The fleet consists only of the PILOTs
	 * 
	 * Mandatory/voluntary participation in other activities may affect the scores of pilots who didn't compete
	 */
	public enum Type {
		/** Pilot racing */
		PILOT ("attendee.pilot"), //$NON-NLS-1$

		/** Mandatory race master */
		M_RACE_MASTER ("attendee.race-master.mandatory"), //$NON-NLS-1$

		/** Mandatory scorer */
		M_SCORER ("attendee.scorer.mandatory"), //$NON-NLS-1$

		/** Mandatory marshal */
		M_MARSHAL ("attendee.marshal.mandatory"), //$NON-NLS-1$

		/** Voluntary race master */
		V_RACE_MASTER ("attendee.race-master.voluntary"), //$NON-NLS-1$

		/** Voluntary scorer */
		V_SCORER ("attendee.scorer.voluntary"), //$NON-NLS-1$

		/** Voluntary marshal */
		V_MARSHAL ("attendee.marshal.voluntary"), //$NON-NLS-1$

		/** Other participant */
		OTHER ("attendee.other"); //$NON-NLS-1$ 

		private final String key;
		private final boolean mandatory = name().startsWith("M_"); //$NON-NLS-1$
		private final boolean voluntary = name().startsWith("V_"); //$NON-NLS-1$

		private Type(String key) {
			this.key = key;
		}

		/**
		 * Determines if this participation is mandatory or not
		 * 
		 * <b>Warning: {@code isMandatory} does not imply {@code !isVolutunary}</b>
		 * 
		 * @return {@code true} if pilot participation is mandatory
		 */
		public boolean isMandatory() {
			return mandatory;
		}

		/**
		 * Determines if this participation is voluntary or not
		 * 
		 * <b>Warning: {@code isVolutunary} does not imply {@code !isMandatory}</b>
		 * 
		 * @return {@code true} if pilot participation is voluntary
		 */
		public boolean isVoluntary() {
			return voluntary;
		}

		@Override
		public String toString() {
			return Messages.getString(key);
		}
	}

	RaceAttendee() {
	}

	public RaceAttendee(Race race, Pilot pilot, Type type) {
		setRace(race);
		setPilot(pilot);
		setType(type);
	}

	private Race race;

	@ManyToOne(optional = false)
	@JoinColumn(name = "race_id", nullable = false)
	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	private Pilot pilot;

	@ManyToOne(optional = false)
	@JoinColumn(name = "pilot_id", nullable = false)
	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	private Type type;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	private List<Penalty> penalties = new ArrayList<Penalty>();

	@ElementCollection
	@CollectionTable(name = "race_attendee_penalties", joinColumns = @JoinColumn(name = "race_attendee_id"))
	@OrderColumn(name = "penalties_order")
	public List<Penalty> getPenalties() {
		return penalties;
	}

	public void setPenalties(List<Penalty> penalties) {
		this.penalties = penalties;
	}
}