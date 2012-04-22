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

import com.google.common.base.Preconditions;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.data.Scores;
import eu.lp0.cursus.xml.common.AbstractXMLEntity;

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

		raceResults = new ArrayList<ScoresXMLEventRaceResults>(scores.getRaces().size());
		for (Race race : scores.getRaces()) {
			raceResults.add(new ScoresXMLEventRaceResults(scores, race));
		}
	}

	private String event;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	private int discards;

	public int getDiscards() {
		return discards;
	}

	public void setDiscards(int discards) {
		this.discards = discards;
	}

	private ArrayList<ScoresXMLEventRaceResults> raceResults;

	public ArrayList<ScoresXMLEventRaceResults> getRaceResults() {
		return raceResults;
	}

	public void setRaceResults(ArrayList<ScoresXMLEventRaceResults> raceResults) {
		this.raceResults = raceResults;
	}
}