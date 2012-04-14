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
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.google.common.collect.Sets;

import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.xml.ExportReferenceManager;
import eu.lp0.cursus.xml.common.AbstractXMLEntity;
import eu.lp0.cursus.xml.scores.ref.ScoresXMLSeriesRef;

@Root(name = "series")
public class ScoresXMLSeries extends AbstractXMLEntity<Series> {
	public ScoresXMLSeries() {
	}

	public ScoresXMLSeries(ExportReferenceManager refMgr, Series series, SortedSet<Event> events, SortedSet<Race> races, Set<Pilot> pilots) {
		super(series);

		name = series.getName();
		description = series.getDescription();

		Set<Class> classes_ = new HashSet<Class>();
		for (Pilot pilot : pilots) {
			classes_.addAll(pilot.getClasses());
		}

		classes = new ArrayList<ScoresXMLClass>(classes_.size());
		for (Class class_ : classes_) {
			classes.add(refMgr.put(new ScoresXMLClass(class_)));
		}
		Collections.sort(classes);

		this.pilots = new ArrayList<ScoresXMLPilot>(pilots.size());
		for (Pilot pilot : pilots) {
			this.pilots.add(refMgr.put(new ScoresXMLPilot(refMgr, pilot)));
		}
		Collections.sort(this.pilots);

		this.events = new ArrayList<ScoresXMLEvent>(events.size());
		for (Event event : events) {
			this.events.add(refMgr.put(new ScoresXMLEvent(refMgr, event, Sets.intersection(new TreeSet<Race>(event.getRaces()), races), pilots)));
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

	@ElementList
	private ArrayList<ScoresXMLClass> classes;

	public ArrayList<ScoresXMLClass> getClasses() {
		return classes;
	}

	public void setClasses(ArrayList<ScoresXMLClass> classes) {
		this.classes = classes;
	}

	@ElementList
	private ArrayList<ScoresXMLPilot> pilots;

	public ArrayList<ScoresXMLPilot> getPilots() {
		return pilots;
	}

	public void setPilots(ArrayList<ScoresXMLPilot> pilots) {
		this.pilots = pilots;
	}

	@ElementList
	private ArrayList<ScoresXMLEvent> events;

	public ArrayList<ScoresXMLEvent> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<ScoresXMLEvent> events) {
		this.events = events;
	}

	@Override
	public ScoresXMLSeriesRef makeReference() {
		return new ScoresXMLSeriesRef(this);
	}
}