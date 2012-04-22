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
package eu.lp0.cursus.xml.scores.data;

import java.util.ArrayList;
import java.util.List;

import eu.lp0.cursus.db.data.Penalty;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.data.Scores;
import eu.lp0.cursus.xml.common.AbstractXMLEntity;
import eu.lp0.cursus.xml.data.entity.DataXMLPenalty;
import eu.lp0.cursus.xml.data.ref.DataXMLPilotRef;

public class ScoresXMLRaceScore implements DataXMLPilotRef {
	public ScoresXMLRaceScore() {
	}

	public ScoresXMLRaceScore(Scores scores, Race race_, Pilot pilot_) {
		pilot = AbstractXMLEntity.generateId(pilot_);

		laps = scores.getLaps(pilot_, race_);
		setSimulated(scores.hasSimulatedRacePoints(pilot_, race_));
		setDiscarded(scores.getDiscardedRaces(pilot_).contains(race_));

		penalties = scores.getRacePenalties(pilot_, race_);
		points = scores.getRacePoints(pilot_, race_);
		position = scores.getRacePosition(pilot_, race_);

		List<Penalty> simulatedPenalties_ = scores.getSimulatedRacePenalties(pilot_, race_);
		if (!simulatedPenalties_.isEmpty()) {
			simulatedPenalties = new ArrayList<DataXMLPenalty>();
			for (Penalty penalty : simulatedPenalties_) {
				simulatedPenalties.add(new DataXMLPenalty(penalty));
			}
		}
	}

	private String pilot;

	public String getPilot() {
		return pilot;
	}

	public void setPilot(String pilot) {
		this.pilot = pilot;
	}

	private int laps;

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}

	private boolean simulated;

	public boolean isSimulated() {
		return simulated;
	}

	public void setSimulated(boolean simulated) {
		this.simulated = simulated;
	}

	private boolean discarded;

	public boolean isDiscarded() {
		return discarded;
	}

	public void setDiscarded(boolean discarded) {
		this.discarded = discarded;
	}

	private int penalties;

	public int getPenalties() {
		return penalties;
	}

	public void setPenalties(int penalties) {
		this.penalties = penalties;
	}

	private int points;

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	private int position;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	private ArrayList<DataXMLPenalty> simulatedPenalties;

	public ArrayList<DataXMLPenalty> getSimulatedPenalties() {
		return simulatedPenalties;
	}

	public void setSimulatedPenalties(ArrayList<DataXMLPenalty> simulatedPenalties) {
		this.simulatedPenalties = simulatedPenalties;
	}
}