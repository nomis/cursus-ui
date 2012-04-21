/*
	cursus - Race series management program
	Copyright 2012  Simon Arlott

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
package eu.lp0.cursus.xml.scores.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.xml.common.AbstractXMLEntity;
import eu.lp0.cursus.xml.scores.ref.ScoresXMLEventAttendee;

@Root(name = "event")
public class ScoresXMLEvent extends AbstractXMLEntity<Event> {
	public ScoresXMLEvent() {
	}

	public ScoresXMLEvent(Event event, Set<Race> races, Set<Pilot> pilots) {
		super(event);

		name = event.getName();
		description = event.getDescription();

		if (!event.getAttendees().isEmpty()) {
			attendees = new ArrayList<ScoresXMLEventAttendee>(event.getAttendees().size());
			for (Pilot pilot : event.getAttendees()) {
				attendees.add(new ScoresXMLEventAttendee(pilot));
			}
			Collections.sort(attendees);
		}

		this.races = new ArrayList<ScoresXMLRace>(races.size());
		for (Race race : races) {
			this.races.add(new ScoresXMLRace(race, pilots));
		}
	}

	@Element
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Element
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ElementList(required = false, inline = true)
	private ArrayList<ScoresXMLEventAttendee> attendees;

	public ArrayList<ScoresXMLEventAttendee> getAttendees() {
		return attendees;
	}

	public void setAttendees(ArrayList<ScoresXMLEventAttendee> attendees) {
		this.attendees = attendees;
	}

	@ElementList
	private ArrayList<ScoresXMLRace> races;

	public ArrayList<ScoresXMLRace> getRaces() {
		return races;
	}

	public void setRaces(ArrayList<ScoresXMLRace> race) {
		this.races = race;
	}
}