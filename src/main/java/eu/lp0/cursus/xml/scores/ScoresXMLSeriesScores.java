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
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.scoring.Scores;

@Root(name = "seriesScores")
public class ScoresXMLSeriesScores {
	public ScoresXMLSeriesScores() {
	}

	public ScoresXMLSeriesScores(Scores scores) {
		ref = Series.class.getSimpleName() + scores.getSeries().getId();

		discards = scores.getDiscardCount();

		pilots = new ArrayList<ScoresXMLOverallScore>(scores.getOverallOrder().size());
		for (Pilot pilot : scores.getOverallOrder()) {
			pilots.add(new ScoresXMLOverallScore(scores, pilot));
		}

		Multimap<Event, Race> events_ = LinkedHashMultimap.create(scores.getRaces().size(), scores.getRaces().size());
		for (Race race : scores.getRaces()) {
			events_.put(race.getEvent(), race);
		}

		events = new ArrayList<ScoresXMLSeriesEventScores>(events_.keySet().size());
		for (Entry<Event, Collection<Race>> event : events_.asMap().entrySet()) {
			events.add(new ScoresXMLSeriesEventScores(scores, event.getKey(), event.getValue()));
		}
	}

	@Attribute
	private String ref;

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
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
	private List<ScoresXMLOverallScore> pilots;

	public List<ScoresXMLOverallScore> getPilots() {
		return pilots;
	}

	public void setPilots(List<ScoresXMLOverallScore> pilots) {
		this.pilots = pilots;
	}

	@ElementList
	private List<ScoresXMLSeriesEventScores> events;

	public List<ScoresXMLSeriesEventScores> getEvents() {
		return events;
	}

	public void setEvents(List<ScoresXMLSeriesEventScores> events) {
		this.events = events;
	}
}