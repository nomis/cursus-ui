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
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.common.collect.Sets;

import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.xml.common.AbstractXMLEntity;

public class DataXMLSeries extends AbstractXMLEntity<Series> {
	public DataXMLSeries() {
	}

	public DataXMLSeries(Series series) {
		super(series);

		name = series.getName();
		description = series.getDescription();

		classes = new ArrayList<DataXMLClass>(series.getClasses().size());
		for (Class class_ : series.getClasses()) {
			classes.add(new DataXMLClass(class_));
		}
		Collections.sort(classes);

		this.pilots = new ArrayList<DataXMLPilot>(series.getPilots().size());
		for (Pilot pilot : series.getPilots()) {
			this.pilots.add(new DataXMLPilot(pilot));
		}
		Collections.sort(this.pilots);

		this.events = new ArrayList<DataXMLEvent>(series.getEvents().size());
		for (Event event : series.getEvents()) {
			this.events.add(new DataXMLEvent(event));
		}
	}

	public DataXMLSeries(Series series, SortedSet<Event> events, SortedSet<Race> races, Set<Pilot> pilots) {
		super(series);

		name = series.getName();
		description = series.getDescription();

		Set<Class> classes_ = new HashSet<Class>();
		for (Pilot pilot : pilots) {
			classes_.addAll(pilot.getClasses());
		}

		classes = new ArrayList<DataXMLClass>(classes_.size());
		for (Class class_ : classes_) {
			classes.add(new DataXMLClass(class_));
		}
		Collections.sort(classes);

		this.pilots = new ArrayList<DataXMLPilot>(pilots.size());
		for (Pilot pilot : pilots) {
			this.pilots.add(new DataXMLPilot(pilot));
		}
		Collections.sort(this.pilots);

		this.events = new ArrayList<DataXMLEvent>(events.size());
		for (Event event : events) {
			this.events.add(new DataXMLEvent(event, Sets.intersection(new TreeSet<Race>(event.getRaces()), races), pilots));
		}
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private ArrayList<DataXMLClass> classes;

	public ArrayList<DataXMLClass> getClasses() {
		return classes;
	}

	public void setClasses(ArrayList<DataXMLClass> classes) {
		this.classes = classes;
	}

	private ArrayList<DataXMLPilot> pilots;

	public ArrayList<DataXMLPilot> getPilots() {
		return pilots;
	}

	public void setPilots(ArrayList<DataXMLPilot> pilots) {
		this.pilots = pilots;
	}

	private ArrayList<DataXMLEvent> events;

	public ArrayList<DataXMLEvent> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<DataXMLEvent> events) {
		this.events = events;
	}
}