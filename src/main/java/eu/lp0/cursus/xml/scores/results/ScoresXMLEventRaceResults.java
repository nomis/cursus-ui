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

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.Scores;
import eu.lp0.cursus.xml.ExportReferenceManager;
import eu.lp0.cursus.xml.scores.data.ScoresXMLRaceScore;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLRaceRef;

@Root(name = "eventRaceResults")
public class ScoresXMLEventRaceResults {
	public ScoresXMLEventRaceResults() {
	}

	public ScoresXMLEventRaceResults(ExportReferenceManager refMgr, Scores scores, Race race) {
		this.race = refMgr.get(race);

		fleet = scores.getFleetSize(race);

		racePilots = new ArrayList<ScoresXMLRaceScore>(scores.getRaceOrder(race).size());
		for (Pilot pilot : scores.getRaceOrder(race)) {
			racePilots.add(new ScoresXMLRaceScore(refMgr, scores, race, pilot));
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

	@ElementList(name = "raceOrder")
	private List<ScoresXMLRaceScore> racePilots;

	public List<ScoresXMLRaceScore> getRacePilots() {
		return racePilots;
	}

	public void setRacePilots(List<ScoresXMLRaceScore> racePilots) {
		this.racePilots = racePilots;
	}
}