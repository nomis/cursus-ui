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
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.google.common.base.Preconditions;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.Scores;
import eu.lp0.cursus.xml.ExportReferenceManager;
import eu.lp0.cursus.xml.scores.data.ScoresXMLOverallScore;
import eu.lp0.cursus.xml.scores.data.ScoresXMLRaceScore;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLRaceRef;

@Root(name = "raceResults")
public class ScoresXMLRaceResults {
	public ScoresXMLRaceResults() {
	}

	public ScoresXMLRaceResults(ExportReferenceManager refMgr, Scores scores) {
		Preconditions.checkArgument(!scores.getRaces().isEmpty(), "No race"); //$NON-NLS-1$
		Preconditions.checkArgument(scores.getRaces().size() == 1, "Multiple races not allowed"); //$NON-NLS-1$
		Race race_ = scores.getRaces().iterator().next();
		race = refMgr.get(race_);

		fleet = scores.getFleetSize(race_);

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

	@ElementList(name = "overallOrder")
	private List<ScoresXMLOverallScore> overallPilots;

	public List<ScoresXMLOverallScore> getOverallPilots() {
		return overallPilots;
	}

	public void setOverallPilots(List<ScoresXMLOverallScore> overallPilots) {
		this.overallPilots = overallPilots;
	}

	@ElementList(name = "raceOrder")
	private List<ScoresXMLRaceScore> racePilots;

	public List<ScoresXMLRaceScore> getRacePilots() {
		return racePilots;
	}

	public void setRacePilots(List<ScoresXMLRaceScore> racePilots) {
		this.racePilots = racePilots;
	}
}