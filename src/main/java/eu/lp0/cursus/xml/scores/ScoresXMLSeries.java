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
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.scoring.Scores;
import eu.lp0.cursus.util.Constants;

@Namespace(reference = Constants.SCORES_XML_DTD)
@Root(name = "series")
public class ScoresXMLSeries {
	public ScoresXMLSeries() {
	}

	public ScoresXMLSeries(Scores scores) {
		id = Series.class.getSimpleName() + scores.getSeries().getId();
		name = scores.getSeries().getName();
		description = scores.getSeries().getDescription();
		discards = scores.getDiscardCount();

		classes = new ArrayList<ScoresXMLClass>(scores.getSeries().getClasses().size());
		for (Class class_ : scores.getSeries().getClasses()) {
			if (!Sets.intersection(scores.getPilots(), class_.getPilots()).isEmpty()) {
				classes.add(new ScoresXMLClass(scores, class_));
			}
		}

		pilots = new ArrayList<ScoresXMLPilot>(scores.getPilots().size());
		for (Pilot pilot : scores.getOverallOrder()) {
			pilots.add(new ScoresXMLPilot(scores, pilot));
		}

		Multimap<Event, Race> events_ = LinkedHashMultimap.create(scores.getRaces().size(), scores.getRaces().size());
		for (Race race : scores.getRaces()) {
			events_.put(race.getEvent(), race);
		}

		events = new ArrayList<ScoresXMLEvent>(events_.keySet().size());
		for (Entry<Event, Collection<Race>> event : events_.asMap().entrySet()) {
			events.add(new ScoresXMLEvent(scores, event.getKey(), event.getValue()));
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

	@Attribute
	private int discards;

	public int getDiscards() {
		return discards;
	}

	public void setDiscards(int discards) {
		this.discards = discards;
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