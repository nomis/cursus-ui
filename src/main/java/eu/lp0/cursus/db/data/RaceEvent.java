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

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import eu.lp0.cursus.util.Constants;

/**
 * Race Event
 */
// TODO this needs a better name
@Embeddable
public final class RaceEvent {
	public enum Type {
		START, LAP, INVALID_LAP, FINISH;
	}

	RaceEvent() {
	}

	public RaceEvent(Type type) {
		this(null, type);
	}

	public RaceEvent(Type type, String data) {
		this(null, type, data);
	}

	public RaceEvent(Type type, String data, Pilot pilot) {
		this(null, type, data, pilot);
	}

	public RaceEvent(Timestamp timestamp, Type type) {
		this(timestamp, type, "", null); //$NON-NLS-1$
	}

	public RaceEvent(Timestamp timestamp, Type type, String data) {
		this(timestamp, type, data, null);
	}

	public RaceEvent(Timestamp timestamp, Type type, String data, Pilot pilot) {
		setTimestamp(timestamp);
		setType(type);
		setData(data);
		setPilot(pilot);
	}

	private Timestamp timestamp;

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
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

	private String data;

	@Column(nullable = false, length = Constants.MAX_STRING_LEN)
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	private Pilot pilot;

	@ManyToOne
	@JoinColumn(name = "pilot_id")
	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}
}