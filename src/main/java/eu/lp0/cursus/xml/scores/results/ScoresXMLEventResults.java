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
import java.util.HashSet;
import java.util.Set;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.google.common.base.Preconditions;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.data.Scores;
import eu.lp0.cursus.xml.common.AbstractXMLEntity;
import eu.lp0.cursus.xml.scores.data.ScoresXMLOverallScore;
import eu.lp0.cursus.xml.scores.ref.ScoresXMLEvent;

@Root(name = "eventResults")
public class ScoresXMLEventResults extends AbstractScoresXMLResults {
	public ScoresXMLEventResults() {
	}

	public ScoresXMLEventResults(Scores scores) {
		super(scores);

		Set<Event> checkEvent = new HashSet<Event>();
		for (Race race : scores.getRaces()) {
			checkEvent.add(race.getEvent());
		}
		Preconditions.checkArgument(!checkEvent.isEmpty(), "No event"); //$NON-NLS-1$
		Preconditions.checkArgument(checkEvent.size() == 1, "Multiple events not allowed"); //$NON-NLS-1$
		event = AbstractXMLEntity.generateId(checkEvent.iterator().next());

		discards = scores.getDiscardCount();

		events = new ArrayList<ScoresXMLEvent>(scores.getEvents().size());
		for (Event event_ : scores.getEvents()) {
			events.add(new ScoresXMLEvent(event_));
		}

		overallPilots = new ArrayList<ScoresXMLOverallScore>(scores.getOverallOrder().size());
		for (Pilot pilot : scores.getOverallOrder()) {
			overallPilots.add(new ScoresXMLOverallScore(scores, pilot));
		}

		raceResults = new ArrayList<ScoresXMLEventRaceResults>(scores.getRaces().size());
		for (Race race : scores.getRaces()) {
			raceResults.add(new ScoresXMLEventRaceResults(scores, race));
		}
	}

	@Attribute
	private String event;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	@Attribute
	private int discards;

	public int getDiscards() {
		return discards;
	}

	public void setDiscards(int discards) {
		this.discards = discards;
	}

	@ElementList(inline = true)
	private ArrayList<ScoresXMLEvent> events;

	@Override
	public ArrayList<ScoresXMLEvent> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<ScoresXMLEvent> events) {
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
	private ArrayList<ScoresXMLEventRaceResults> raceResults;

	public ArrayList<ScoresXMLEventRaceResults> getRaceResults() {
		return raceResults;
	}

	public void setRaceResults(ArrayList<ScoresXMLEventRaceResults> raceResults) {
		this.raceResults = raceResults;
	}
}