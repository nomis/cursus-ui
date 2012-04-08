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

@Namespace(reference = Constants.SCORES_XML_DTD)
@Root(name = "cursus")
public class ScoresXMLFile {
	public ScoresXMLFile() {
	}

	public ScoresXMLFile(Scores seriesScores, List<Scores> eventScores, List<Scores> raceScores) {
		Set<Series> checkSeries = new HashSet<Series>();
		SortedSet<Event> events = new TreeSet<Event>();
		SortedSet<Race> races = new TreeSet<Race>();
		Set<Pilot> pilots = new HashSet<Pilot>();

		if (seriesScores != null) {
			checkSeries.add(seriesScores.getSeries());
			races.addAll(seriesScores.getRaces());
			pilots.addAll(seriesScores.getPilots());
			this.seriesScores = new ScoresXMLSeriesScores(seriesScores);
		}

		if (eventScores != null) {
			this.eventScores = new ArrayList<ScoresXMLEventScores>(eventScores.size());
			for (Scores scores : eventScores) {
				checkSeries.add(scores.getSeries());
				races.addAll(scores.getRaces());
				pilots.addAll(seriesScores.getPilots());
				this.eventScores.add(new ScoresXMLEventScores(scores));
			}
		}

		if (raceScores != null) {
			this.raceScores = new ArrayList<ScoresXMLRaceScores>(raceScores.size());
			for (Scores scores : raceScores) {
				checkSeries.add(scores.getSeries());
				races.addAll(scores.getRaces());
				pilots.addAll(seriesScores.getPilots());
				this.raceScores.add(new ScoresXMLRaceScores(scores));
			}
		}
		Preconditions.checkArgument(!checkSeries.isEmpty(), "No series"); //$NON-NLS-1$
		Preconditions.checkArgument(checkSeries.size() == 1, "Multiple series not allowed"); //$NON-NLS-1$

		for (Race race : races) {
			events.add(race.getEvent());
		}

		series = new ScoresXMLSeries(checkSeries.iterator().next(), events, races, pilots);
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
	private ScoresXMLSeriesScores seriesScores;

	public ScoresXMLSeriesScores getSeriesScores() {
		return seriesScores;
	}

	public void setSeriesScores(ScoresXMLSeriesScores seriesScores) {
		this.seriesScores = seriesScores;
	}

	@ElementList(required = false)
	private List<ScoresXMLEventScores> eventScores;

	public List<ScoresXMLEventScores> getEventScores() {
		return eventScores;
	}

	public void setEventScores(List<ScoresXMLEventScores> eventScores) {
		this.eventScores = eventScores;
	}

	@ElementList(required = false)
	private List<ScoresXMLRaceScores> raceScores;

	public List<ScoresXMLRaceScores> getRaceScores() {
		return raceScores;
	}

	public void setRaceScores(List<ScoresXMLRaceScores> raceScores) {
		this.raceScores = raceScores;
	}
}