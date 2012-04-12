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
import eu.lp0.cursus.xml.scores.data.ScoresXMLRaceScore;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLEventRef;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLRaceRef;

@Root(name = "raceResults")
public class ScoresXMLRaceResults extends AbstractScoresXMLResults {
	public ScoresXMLRaceResults() {
	}

	public ScoresXMLRaceResults(ExportReferenceManager refMgr, Scores scores) {
		super(scores);

		Preconditions.checkArgument(!scores.getRaces().isEmpty(), "No race"); //$NON-NLS-1$
		Preconditions.checkArgument(scores.getRaces().size() == 1, "Multiple races not allowed"); //$NON-NLS-1$
		Race race_ = scores.getRaces().iterator().next();
		race = refMgr.get(race_);

		fleet = scores.getFleetSize(race_);

		events = new ArrayList<ScoresXMLEventRef>(scores.getEvents().size());
		for (Event event_ : scores.getEvents()) {
			events.add((ScoresXMLEventRef)refMgr.get(event_));
		}

		overallPilots = new ArrayList<ScoresXMLOverallScore>(scores.getOverallOrder().size());
		for (Pilot pilot : scores.getOverallOrder()) {
			overallPilots.add(new ScoresXMLOverallScore(refMgr, scores, pilot));
		}

		racePilots = new ArrayList<ScoresXMLRaceScore>(scores.getRaceOrder(race_).size());
		for (Pilot pilot : scores.getRaceOrder(race_)) {
			racePilots.add(new ScoresXMLRaceScore(refMgr, scores, race_, pilot));
		}
	}

	@Element
	private ScoresXMLRaceRef race;

	public ScoresXMLRaceRef getRace() {
		return race;
	}

	public void setRace(ScoresXMLRaceRef race) {
		this.race = race;
	}

	@Attribute
	private int fleet;

	public int getFleet() {
		return fleet;
	}

	public void setFleet(int fleet) {
		this.fleet = fleet;
	}

	@ElementList
	private ArrayList<ScoresXMLEventRef> events;

	public ArrayList<ScoresXMLEventRef> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<ScoresXMLEventRef> events) {
		this.events = events;
	}

	@ElementList(name = "overallOrder")
	private ArrayList<ScoresXMLOverallScore> overallPilots;

	public ArrayList<ScoresXMLOverallScore> getOverallPilots() {
		return overallPilots;
	}

	public void setOverallPilots(ArrayList<ScoresXMLOverallScore> overallPilots) {
		this.overallPilots = overallPilots;
	}

	@ElementList(name = "raceOrder")
	private ArrayList<ScoresXMLRaceScore> racePilots;

	public ArrayList<ScoresXMLRaceScore> getRacePilots() {
		return racePilots;
	}

	public void setRacePilots(ArrayList<ScoresXMLRaceScore> racePilots) {
		this.racePilots = racePilots;
	}
}