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
package eu.lp0.cursus.xml.data.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import com.google.common.base.Predicates;
import com.google.common.collect.Sets;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.xml.common.AbstractXMLEntity;
import eu.lp0.cursus.xml.data.DataXML;
import eu.lp0.cursus.xml.data.ref.DataXMLEventAttendee;

@Namespace(reference = DataXML.DATA_XMLNS)
@Root(name = "event")
public class DataXMLEvent extends AbstractXMLEntity<Event> {
	public DataXMLEvent() {
	}

	public DataXMLEvent(Event event) {
		super(event);

		name = event.getName();
		description = event.getDescription();

		if (!event.getAttendees().isEmpty()) {
			attendees = new ArrayList<DataXMLEventAttendee>(event.getAttendees().size());
			for (Pilot pilot : event.getAttendees()) {
				attendees.add(new DataXMLEventAttendee(pilot));
			}
			Collections.sort(attendees);
		}

		this.races = new ArrayList<DataXMLRace>(event.getRaces().size());
		for (Race race : event.getRaces()) {
			this.races.add(new DataXMLRace(race));
		}
	}

	public DataXMLEvent(Event event, Set<Race> races, Set<Pilot> pilots) {
		super(event);

		name = event.getName();
		description = event.getDescription();

		if (!event.getAttendees().isEmpty()) {
			attendees = new ArrayList<DataXMLEventAttendee>(event.getAttendees().size());
			for (Pilot pilot : Sets.filter(event.getAttendees(), Predicates.in(pilots))) {
				attendees.add(new DataXMLEventAttendee(pilot));
			}
			Collections.sort(attendees);
		}

		this.races = new ArrayList<DataXMLRace>(races.size());
		for (Race race : races) {
			this.races.add(new DataXMLRace(race, pilots));
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
	private ArrayList<DataXMLEventAttendee> attendees;

	public ArrayList<DataXMLEventAttendee> getAttendees() {
		return attendees;
	}

	public void setAttendees(ArrayList<DataXMLEventAttendee> attendees) {
		this.attendees = attendees;
	}

	@ElementList
	private ArrayList<DataXMLRace> races;

	public ArrayList<DataXMLRace> getRaces() {
		return races;
	}

	public void setRaces(ArrayList<DataXMLRace> race) {
		this.races = race;
	}
}