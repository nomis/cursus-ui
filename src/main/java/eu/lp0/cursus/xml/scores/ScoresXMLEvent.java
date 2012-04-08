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

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;

@Root(name = "event")
public class ScoresXMLEvent {
	public ScoresXMLEvent() {
	}

	public ScoresXMLEvent(Event event, Set<Race> races, Set<Pilot> pilots) {
		id = Event.class.getSimpleName() + event.getId();
		name = event.getName();
		description = event.getDescription();

		this.races = new ArrayList<ScoresXMLRace>(races.size());
		for (Race race : races) {
			this.races.add(new ScoresXMLRace(race, pilots));
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
	private List<ScoresXMLRace> races;

	public List<ScoresXMLRace> getRaces() {
		return races;
	}

	public void setRaces(List<ScoresXMLRace> race) {
		this.races = race;
	}
}