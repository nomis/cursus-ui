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
import com.google.common.collect.Maps;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceAttendee;
import eu.lp0.cursus.xml.common.AbstractXMLEntity;
import eu.lp0.cursus.xml.data.DataXML;

@Namespace(reference = DataXML.DATA_XMLNS)
@Root(name = "race")
public class DataXMLRace extends AbstractXMLEntity<Race> {
	public DataXMLRace() {
	}

	public DataXMLRace(Race race) {
		super(race);

		name = race.getName();
		description = race.getDescription();

		if (!race.getAttendees().isEmpty()) {
			attendees = new ArrayList<DataXMLRaceAttendee>(race.getAttendees().size());
			for (RaceAttendee attendee : race.getAttendees().values()) {
				attendees.add(new DataXMLRaceAttendee(attendee));
			}
		}
		Collections.sort(attendees);
	}

	public DataXMLRace(Race race, Set<Pilot> pilots) {
		super(race);

		name = race.getName();
		description = race.getDescription();

		if (!race.getAttendees().isEmpty()) {
			attendees = new ArrayList<DataXMLRaceAttendee>(race.getAttendees().size());
			for (RaceAttendee attendee : Maps.filterKeys(race.getAttendees(), Predicates.in(pilots)).values()) {
				attendees.add(new DataXMLRaceAttendee(attendee));
			}
		}
		Collections.sort(attendees);
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
	private ArrayList<DataXMLRaceAttendee> attendees;

	public ArrayList<DataXMLRaceAttendee> getAttendees() {
		return attendees;
	}

	public void setAttendees(ArrayList<DataXMLRaceAttendee> attendees) {
		this.attendees = attendees;
	}
}