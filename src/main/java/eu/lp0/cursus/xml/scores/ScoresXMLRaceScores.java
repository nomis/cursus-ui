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
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.google.common.base.Preconditions;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.Scores;

@Root(name = "raceScore")
public class ScoresXMLRaceScores {
	public ScoresXMLRaceScores() {
	}

	public ScoresXMLRaceScores(Scores scores) {
		Preconditions.checkArgument(!scores.getRaces().isEmpty(), "No race"); //$NON-NLS-1$
		Preconditions.checkArgument(scores.getRaces().size() == 1, "Multiple races not allowed"); //$NON-NLS-1$
		Race race = scores.getRaces().iterator().next();

		ref = Race.class.getSimpleName() + race.getId();

		overallPilots = new ArrayList<ScoresXMLOverallScore>(scores.getOverallOrder().size());
		for (Pilot pilot : scores.getOverallOrder()) {
			overallPilots.add(new ScoresXMLOverallScore(scores, pilot));
		}

		pilots = new ArrayList<ScoresXMLRaceScore>(scores.getRaceOrder(race).size());
		for (Pilot pilot : scores.getRaceOrder(race)) {
			pilots.add(new ScoresXMLRaceScore(scores, race, pilot));
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

	@ElementList
	private List<ScoresXMLOverallScore> overallPilots;

	public List<ScoresXMLOverallScore> getOverallPilots() {
		return overallPilots;
	}

	public void setOverallPilots(List<ScoresXMLOverallScore> overallPilots) {
		this.overallPilots = overallPilots;
	}

	@ElementList
	private List<ScoresXMLRaceScore> pilots;

	public List<ScoresXMLRaceScore> getPilots() {
		return pilots;
	}

	public void setPilots(List<ScoresXMLRaceScore> pilots) {
		this.pilots = pilots;
	}
}