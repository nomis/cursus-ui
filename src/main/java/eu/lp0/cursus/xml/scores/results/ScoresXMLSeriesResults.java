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
package eu.lp0.cursus.xml.scores.results;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.Scores;
import eu.lp0.cursus.xml.common.AbstractXMLEntity;
import eu.lp0.cursus.xml.scores.data.ScoresXMLOverallScore;
import eu.lp0.cursus.xml.scores.ref.ScoresXMLEventRef;
import eu.lp0.cursus.xml.scores.ref.ScoresXMLSeriesRef;

@Root(name = "seriesResults")
public class ScoresXMLSeriesResults extends AbstractScoresXMLResults implements ScoresXMLSeriesRef {
	public ScoresXMLSeriesResults() {
	}

	public ScoresXMLSeriesResults(Scores scores) {
		super(scores);

		series = AbstractXMLEntity.generateId(scores.getSeries());

		discards = scores.getDiscardCount();

		events = new ArrayList<ScoresXMLEventRef>(scores.getEvents().size());
		for (Event event : scores.getEvents()) {
			events.add(new ScoresXMLEventRef(event));
		}

		overallPilots = new ArrayList<ScoresXMLOverallScore>(scores.getOverallOrder().size());
		for (Pilot pilot : scores.getOverallOrder()) {
			overallPilots.add(new ScoresXMLOverallScore(scores, pilot));
		}

		Multimap<Event, Race> events_ = LinkedHashMultimap.create(scores.getRaces().size(), scores.getRaces().size());
		for (Race race : scores.getRaces()) {
			events_.put(race.getEvent(), race);
		}

		eventResults = new ArrayList<ScoresXMLSeriesEventResults>(events_.keySet().size());
		for (Entry<Event, Collection<Race>> event : events_.asMap().entrySet()) {
			eventResults.add(new ScoresXMLSeriesEventResults(scores, event.getKey(), event.getValue()));
		}
	}

	@Attribute
	private String series;

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	@Attribute
	private int discards;

	public int getDiscards() {
		return discards;
	}

	public void setDiscards(int discards) {
		this.discards = discards;
	}

	@ElementList(name = "eventRefs")
	private ArrayList<ScoresXMLEventRef> events;

	@Override
	public ArrayList<ScoresXMLEventRef> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<ScoresXMLEventRef> events) {
		this.events = events;
	}

	@ElementList(name = "overallOrder")
	private ArrayList<ScoresXMLOverallScore> overallPilots;

	@Override
	public ArrayList<ScoresXMLOverallScore> getOverallPilots() {
		return overallPilots;
	}

	public void setOverallPilots(ArrayList<ScoresXMLOverallScore> overallPilots) {
		this.overallPilots = overallPilots;
	}

	@ElementList(inline = true)
	private ArrayList<ScoresXMLSeriesEventResults> eventResults;

	public ArrayList<ScoresXMLSeriesEventResults> getEventResults() {
		return eventResults;
	}

	public void setEventResults(ArrayList<ScoresXMLSeriesEventResults> events) {
		this.eventResults = events;
	}
}