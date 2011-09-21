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

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Race
 */
@Entity(name = "race")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "event_id", "name" }) })
public class Race extends AbstractEntity {
	Race() {
	}

	public Race(Event event, String name) {
		this(event, name, ""); //$NON-NLS-1$
	}

	public Race(Event event, String name, String description) {
		setEvent(event);
		setName(name);
		setDescription(description);
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

	private String name;

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String description;

	@Column(nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private Map<Pilot, Attendee> attendees;

	@CollectionTable(name = "race_attendees", joinColumns = { @JoinColumn(name = "race_id") })
	@MapKeyJoinColumn(name = "pilot_id", nullable = false)
	@ElementCollection
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	public Map<Pilot, Attendee> getAttendees() {
		return attendees;
	}

	public void setAttendees(Map<Pilot, Attendee> attendees) {
		this.attendees = attendees;
	}
}