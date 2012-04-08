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

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.google.common.base.Preconditions;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.Scores;

@Root(name = "eventScore")
public class ScoresXMLEventScores {
	public ScoresXMLEventScores() {
	}

	public ScoresXMLEventScores(Scores scores) {
		Set<Event> checkEvent = new HashSet<Event>();
		for (Race race : scores.getRaces()) {
			checkEvent.add(race.getEvent());
		}
		Preconditions.checkArgument(!checkEvent.isEmpty(), "No event"); //$NON-NLS-1$
		Preconditions.checkArgument(checkEvent.size() == 1, "Multiple events not allowed"); //$NON-NLS-1$
		Event event = checkEvent.iterator().next();

		ref = Event.class.getSimpleName() + event.getId();

		discards = scores.getDiscardCount();

		pilots = new ArrayList<ScoresXMLOverallScore>(scores.getOverallOrder().size());
		for (Pilot pilot : scores.getOverallOrder()) {
			pilots.add(new ScoresXMLOverallScore(scores, pilot));
		}

		races = new ArrayList<ScoresXMLEventRaceScores>(scores.getRaces().size());
		for (Race race : scores.getRaces()) {
			races.add(new ScoresXMLEventRaceScores(scores, race));
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
	private List<ScoresXMLEventRaceScores> races;

	public List<ScoresXMLEventRaceScores> getRaces() {
		return races;
	}

	public void setRaces(List<ScoresXMLEventRaceScores> race) {
		this.races = race;
	}
}