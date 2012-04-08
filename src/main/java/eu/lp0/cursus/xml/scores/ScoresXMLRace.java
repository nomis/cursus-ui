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
package eu.lp0.cursus.xml.scores;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceAttendee;

@Root(name = "race")
public class ScoresXMLRace {
	public ScoresXMLRace() {
	}

	public ScoresXMLRace(Race race, Set<Pilot> pilots) {
		id = Race.class.getSimpleName() + race.getId();
		name = race.getName();
		description = race.getDescription();

		this.pilots = new ArrayList<ScoresXMLRaceAttendee>(race.getAttendees().size());
		for (RaceAttendee attendee : race.getAttendees().values()) {
			if (pilots.contains(attendee.getPilot())) {
				this.pilots.add(new ScoresXMLRaceAttendee(attendee));
			}
		}
	}

	@Attribute
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@ElementList
	private List<ScoresXMLRaceAttendee> pilots;

	public List<ScoresXMLRaceAttendee> getPilots() {
		return pilots;
	}

	public void setPilots(List<ScoresXMLRaceAttendee> pilots) {
		this.pilots = pilots;
	}
}