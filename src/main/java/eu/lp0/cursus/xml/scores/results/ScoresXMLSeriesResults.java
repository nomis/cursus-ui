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

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.data.Scores;
import eu.lp0.cursus.xml.common.AbstractXMLEntity;
import eu.lp0.cursus.xml.data.ref.DataXMLSeriesRef;

public class ScoresXMLSeriesResults extends AbstractScoresXMLResults implements DataXMLSeriesRef {
	public ScoresXMLSeriesResults() {
	}

	public ScoresXMLSeriesResults(Scores scores) {
		super(scores);

		series = AbstractXMLEntity.generateId(scores.getSeries());

		discards = scores.getDiscardCount();

		Multimap<Event, Race> events_ = LinkedHashMultimap.create(scores.getRaces().size(), scores.getRaces().size());
		for (Race race : scores.getRaces()) {
			events_.put(race.getEvent(), race);
		}

		eventResults = new ArrayList<ScoresXMLSeriesEventResults>(events_.keySet().size());
		for (Entry<Event, Collection<Race>> event : events_.asMap().entrySet()) {
			eventResults.add(new ScoresXMLSeriesEventResults(scores, event.getKey(), event.getValue()));
		}
	}

	private String series;

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	private int discards;

	public int getDiscards() {
		return discards;
	}

	public void setDiscards(int discards) {
		this.discards = discards;
	}

	private ArrayList<ScoresXMLSeriesEventResults> eventResults;

	public ArrayList<ScoresXMLSeriesEventResults> getEventResults() {
		return eventResults;
	}

	public void setEventResults(ArrayList<ScoresXMLSeriesEventResults> events) {
		this.eventResults = events;
	}
}