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
import java.util.List;
import java.util.Map.Entry;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.Scores;
import eu.lp0.cursus.xml.ExportReferenceManager;
import eu.lp0.cursus.xml.scores.data.ScoresXMLOverallScore;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLSeriesRef;

@Root(name = "seriesResults")
public class ScoresXMLSeriesResults {
	public ScoresXMLSeriesResults() {
	}

	public ScoresXMLSeriesResults(ExportReferenceManager refMgr, Scores scores) {
		series = refMgr.get(scores.getSeries());

		discards = scores.getDiscardCount();

		overallPilots = new ArrayList<ScoresXMLOverallScore>(scores.getOverallOrder().size());
		for (Pilot pilot : scores.getOverallOrder()) {
			overallPilots.add(new ScoresXMLOverallScore(refMgr, scores, pilot));
		}

		Multimap<Event, Race> events_ = LinkedHashMultimap.create(scores.getRaces().size(), scores.getRaces().size());
		for (Race race : scores.getRaces()) {
			events_.put(race.getEvent(), race);
		}

		events = new ArrayList<ScoresXMLSeriesEventResults>(events_.keySet().size());
		for (Entry<Event, Collection<Race>> event : events_.asMap().entrySet()) {
			events.add(new ScoresXMLSeriesEventResults(refMgr, scores, event.getKey(), event.getValue()));
		}
	}

	@Element
	private ScoresXMLSeriesRef series;

	public ScoresXMLSeriesRef getSeries() {
		return series;
	}

	public void setSeries(ScoresXMLSeriesRef series) {
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

	@ElementList(name = "overallOrder")
	private List<ScoresXMLOverallScore> overallPilots;

	public List<ScoresXMLOverallScore> getOverallPilots() {
		return overallPilots;
	}

	public void setOverallPilots(List<ScoresXMLOverallScore> overallPilots) {
		this.overallPilots = overallPilots;
	}

	@ElementList
	private List<ScoresXMLSeriesEventResults> events;

	public List<ScoresXMLSeriesEventResults> getEvents() {
		return events;
	}

	public void setEvents(List<ScoresXMLSeriesEventResults> events) {
		this.events = events;
	}
}