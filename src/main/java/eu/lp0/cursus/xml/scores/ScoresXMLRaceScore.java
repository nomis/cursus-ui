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

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.Scores;

@Root(name = "pilot")
public class ScoresXMLRaceScore {
	public ScoresXMLRaceScore() {
	}

	public ScoresXMLRaceScore(Scores scores, Race race_, Pilot pilot_) {
		ref = Pilot.class.getSimpleName() + pilot_.getId();

		laps = scores.getLaps(pilot_, race_);
		setSimulated(scores.hasSimulatedRacePoints(pilot_, race_));
		setDiscarded(scores.getDiscardedRaces(pilot_).contains(race_));

		penalties = scores.getRacePenalties(pilot_, race_);
		points = scores.getRacePoints(pilot_, race_);
		position = scores.getRacePosition(pilot_, race_);
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
	private int laps;

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}

	@Attribute
	private boolean simulated;

	public boolean isSimulated() {
		return simulated;
	}

	public void setSimulated(boolean simulated) {
		this.simulated = simulated;
	}

	@Attribute
	private boolean discarded;

	public boolean isDiscarded() {
		return discarded;
	}

	public void setDiscarded(boolean discarded) {
		this.discarded = discarded;
	}

	@Attribute
	private int penalties;

	public int getPenalties() {
		return penalties;
	}

	public void setPenalties(int penalties) {
		this.penalties = penalties;
	}

	@Attribute
	private int points;

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Attribute
	private int position;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}