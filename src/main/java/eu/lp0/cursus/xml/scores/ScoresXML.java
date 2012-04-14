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
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import com.google.common.base.Preconditions;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.scoring.Scores;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.xml.ExportReferenceManager;
import eu.lp0.cursus.xml.common.AbstractXMLRoot;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLSeries;
import eu.lp0.cursus.xml.scores.results.ScoresXMLEventResults;
import eu.lp0.cursus.xml.scores.results.ScoresXMLRaceResults;
import eu.lp0.cursus.xml.scores.results.ScoresXMLSeriesResults;

@Namespace(reference = Constants.SCORES_XMLNS)
@Root(name = "cursus")
public class ScoresXML extends AbstractXMLRoot {
	public ScoresXML() {
	}

	public ScoresXML(ExportReferenceManager refMgr, Scores seriesScores, List<Scores> eventScores, List<Scores> raceScores) {
		Set<Series> checkSeries = new HashSet<Series>();
		SortedSet<Event> events = new TreeSet<Event>();
		SortedSet<Race> races = new TreeSet<Race>();
		Set<Pilot> pilots = new HashSet<Pilot>();

		if (seriesScores != null) {
			checkSeries.add(seriesScores.getSeries());
			races.addAll(seriesScores.getRaces());
			events.addAll(seriesScores.getEvents());
			pilots.addAll(seriesScores.getPilots());
		}

		if (eventScores != null) {
			this.eventResults = new ArrayList<ScoresXMLEventResults>(eventScores.size());
			for (Scores scores : eventScores) {
				checkSeries.add(scores.getSeries());
				races.addAll(scores.getRaces());
				events.addAll(scores.getEvents());
				pilots.addAll(scores.getPilots());
			}
		}

		if (raceScores != null) {
			this.raceResults = new ArrayList<ScoresXMLRaceResults>(raceScores.size());
			for (Scores scores : raceScores) {
				checkSeries.add(scores.getSeries());
				races.addAll(scores.getRaces());
				events.addAll(scores.getEvents());
				pilots.addAll(scores.getPilots());
			}
		}
		Preconditions.checkArgument(!checkSeries.isEmpty(), "No series"); //$NON-NLS-1$
		Preconditions.checkArgument(checkSeries.size() == 1, "Multiple series not allowed"); //$NON-NLS-1$

		generator = Constants.APP_DESC;
		series = new ScoresXMLSeries(refMgr, checkSeries.iterator().next(), events, races, pilots);
		refMgr.put(series);

		if (seriesScores != null) {
			this.seriesResults = new ScoresXMLSeriesResults(refMgr, seriesScores);
		}

		if (eventScores != null) {
			for (Scores scores : eventScores) {
				this.eventResults.add(new ScoresXMLEventResults(refMgr, scores));
			}
		}

		if (raceScores != null) {
			for (Scores scores : raceScores) {
				this.raceResults.add(new ScoresXMLRaceResults(refMgr, scores));
			}
		}
	}

	@Attribute
	private String generator;

	public String getGenerator() {
		return generator;
	}

	public void setGenerator(String generator) {
		this.generator = generator;
	}

	@Element
	private ScoresXMLSeries series;

	public ScoresXMLSeries getSeries() {
		return series;
	}

	public void setSeries(ScoresXMLSeries series) {
		this.series = series;
	}

	@Element(required = false)
	private ScoresXMLSeriesResults seriesResults;

	public ScoresXMLSeriesResults getSeriesResults() {
		return seriesResults;
	}

	public void setSeriesResults(ScoresXMLSeriesResults seriesResults) {
		this.seriesResults = seriesResults;
	}

	@ElementList(required = false, inline = true)
	private ArrayList<ScoresXMLEventResults> eventResults;

	public ArrayList<ScoresXMLEventResults> getEventResults() {
		return eventResults;
	}

	public void setEventResults(ArrayList<ScoresXMLEventResults> eventResults) {
		this.eventResults = eventResults;
	}

	@ElementList(required = false, inline = true)
	private ArrayList<ScoresXMLRaceResults> raceResults;

	public ArrayList<ScoresXMLRaceResults> getRaceResults() {
		return raceResults;
	}

	public void setRaceResults(ArrayList<ScoresXMLRaceResults> raceResults) {
		this.raceResults = raceResults;
	}
}