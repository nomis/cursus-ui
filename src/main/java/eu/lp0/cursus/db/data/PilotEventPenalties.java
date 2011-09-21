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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Pilot event penalties
 */
@Entity(name = "pilot_event_penalties")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "event_id", "pilot_id" }) })
public class PilotEventPenalties extends AbstractEntity {
	PilotEventPenalties() {
	}

	public PilotEventPenalties(Event event, Pilot pilot) {
		setEvent(event);
		setPilot(pilot);
	}

	private Event event;

	@ManyToOne(optional = false)
	@JoinColumn(name = "event_id", nullable = false)
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
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

	private int fixedPenalties;

	/**
	 * Total fixed penalty points to be applied to the event score
	 */
	@Column(nullable = false)
	public int getFixedPenalties() {
		return fixedPenalties;
	}

	public void setFixedPenalties(int fixedPenalties) {
		this.fixedPenalties = fixedPenalties;
	}
}