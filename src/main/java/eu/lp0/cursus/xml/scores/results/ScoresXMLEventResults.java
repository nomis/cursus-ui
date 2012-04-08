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
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.google.common.base.Preconditions;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.Scores;
import eu.lp0.cursus.xml.ExportReferenceManager;
import eu.lp0.cursus.xml.scores.data.ScoresXMLOverallScore;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLEventRef;

@Root(name = "eventResults")
public class ScoresXMLEventResults extends AbstractScoresXMLResults {
	public ScoresXMLEventResults() {
	}

	public ScoresXMLEventResults(ExportReferenceManager refMgr, Scores scores) {
		super(scores);

		Set<Event> checkEvent = new HashSet<Event>();
		for (Race race : scores.getRaces()) {
			checkEvent.add(race.getEvent());
		}
		Preconditions.checkArgument(!checkEvent.isEmpty(), "No event"); //$NON-NLS-1$
		Preconditions.checkArgument(checkEvent.size() == 1, "Multiple events not allowed"); //$NON-NLS-1$
		event = refMgr.get(checkEvent.iterator().next());

		discards = scores.getDiscardCount();

		overallPilots = new ArrayList<ScoresXMLOverallScore>(scores.getOverallOrder().size());
		for (Pilot pilot : scores.getOverallOrder()) {
			overallPilots.add(new ScoresXMLOverallScore(refMgr, scores, pilot));
		}

		races = new ArrayList<ScoresXMLEventRaceResults>(scores.getRaces().size());
		for (Race race : scores.getRaces()) {
			races.add(new ScoresXMLEventRaceResults(refMgr, scores, race));
		}
	}

	@Element
	private ScoresXMLEventRef event;

	public ScoresXMLEventRef getEvent() {
		return event;
	}

	public void setEvent(ScoresXMLEventRef event) {
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

	@ElementList(name = "overallOrder")
	private ArrayList<ScoresXMLOverallScore> overallPilots;

	public ArrayList<ScoresXMLOverallScore> getOverallPilots() {
		return overallPilots;
	}

	public void setOverallPilots(ArrayList<ScoresXMLOverallScore> overallPilots) {
		this.overallPilots = overallPilots;
	}

	@ElementList
	private ArrayList<ScoresXMLEventRaceResults> races;

	public ArrayList<ScoresXMLEventRaceResults> getRaces() {
		return races;
	}

	public void setRaces(ArrayList<ScoresXMLEventRaceResults> race) {
		this.races = race;
	}
}