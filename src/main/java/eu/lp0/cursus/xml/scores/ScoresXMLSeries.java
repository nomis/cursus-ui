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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.google.common.collect.Sets;

import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;

@Root(name = "series")
public class ScoresXMLSeries {
	public ScoresXMLSeries() {
	}

	public ScoresXMLSeries(Series series, SortedSet<Event> events, SortedSet<Race> races, Set<Pilot> pilots) {
		id = Series.class.getSimpleName() + series.getId();
		name = series.getName();
		description = series.getDescription();

		Set<Class> classes_ = new HashSet<Class>();
		this.pilots = new ArrayList<ScoresXMLPilot>(pilots.size());
		for (Pilot pilot : pilots) {
			this.pilots.add(new ScoresXMLPilot(pilot));
			classes_.addAll(pilot.getClasses());
		}

		classes = new ArrayList<ScoresXMLClass>(classes_.size());
		for (Class class_ : classes_) {
			classes.add(new ScoresXMLClass(class_));
		}

		this.events = new ArrayList<ScoresXMLEvent>(events.size());
		for (Event event : events) {
			this.events.add(new ScoresXMLEvent(event, Sets.intersection(new TreeSet<Race>(event.getRaces()), races), pilots));
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
	private List<ScoresXMLClass> classes;

	public List<ScoresXMLClass> getClasses() {
		return classes;
	}

	public void setClasses(List<ScoresXMLClass> classes) {
		this.classes = classes;
	}

	@ElementList
	private List<ScoresXMLPilot> pilots;

	public List<ScoresXMLPilot> getPilots() {
		return pilots;
	}

	public void setPilots(List<ScoresXMLPilot> pilots) {
		this.pilots = pilots;
	}

	@ElementList
	private List<ScoresXMLEvent> events;

	public List<ScoresXMLEvent> getEvents() {
		return events;
	}

	public void setEvents(List<ScoresXMLEvent> events) {
		this.events = events;
	}
}