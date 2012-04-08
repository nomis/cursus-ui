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

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.Scores;

@Root(name = "pilot")
public class ScoresXMLOverallScore {
	public ScoresXMLOverallScore() {
	}

	public ScoresXMLOverallScore(Scores scores, Pilot pilot) {
		ref = Pilot.class.getSimpleName() + pilot.getId();

		penalties = scores.getOverallPenalties(pilot);
		points = scores.getOverallPoints(pilot);
		position = scores.getOverallPosition(pilot);

		if (scores.getDiscardCount() > 0) {
			discards = new ArrayList<ScoresXMLDiscard>(scores.getDiscardCount());
			for (Race race : scores.getDiscardedRaces(pilot)) {
				discards.add(new ScoresXMLDiscard(race));
			}
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

	@ElementList(required = false)
	private List<ScoresXMLDiscard> discards;

	public List<ScoresXMLDiscard> getDiscards() {
		return discards;
	}

	public void setDiscards(List<ScoresXMLDiscard> discards) {
		this.discards = discards;
	}
}