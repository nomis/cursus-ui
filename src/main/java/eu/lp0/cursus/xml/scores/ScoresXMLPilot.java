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
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.db.data.Gender;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.Scores;

@Root(name = "pilot")
public class ScoresXMLPilot {
	public ScoresXMLPilot() {
	}

	public ScoresXMLPilot(Scores scores, Pilot pilot) {
		id = Pilot.class.getSimpleName() + pilot.getId();
		name = pilot.getName();
		gender = pilot.getGender();
		if (pilot.getRaceNumber() != null) {
			raceNumber = new ScoresXMLRaceNumber(scores, pilot.getRaceNumber());
		}

		classes = new ArrayList<ScoresXMLPilotClass>(pilot.getClasses().size());
		for (Class class_ : pilot.getClasses()) {
			classes.add(new ScoresXMLPilotClass(scores, pilot, class_));
		}

		penalties = scores.getOverallPenalties(pilot);
		points = scores.getOverallPoints(pilot);
		position = scores.getOverallPosition(pilot);

		discards = new ArrayList<ScoresXMLDiscard>(scores.getDiscardCount());
		for (Race race : scores.getDiscardedRaces(pilot)) {
			discards.add(new ScoresXMLDiscard(scores, pilot, race));
		}
	}

	@Attribute
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Element
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Element(required = false)
	private Gender gender;

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Element(required = false)
	private ScoresXMLRaceNumber raceNumber;

	public ScoresXMLRaceNumber getRaceNumber() {
		return raceNumber;
	}

	public void setRaceNumber(ScoresXMLRaceNumber raceNumber) {
		this.raceNumber = raceNumber;
	}

	@ElementList
	private List<ScoresXMLPilotClass> classes;

	public List<ScoresXMLPilotClass> getClasses() {
		return classes;
	}

	public void setClasses(List<ScoresXMLPilotClass> classes) {
		this.classes = classes;
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

	@ElementList
	private List<ScoresXMLDiscard> discards;

	public List<ScoresXMLDiscard> getDiscards() {
		return discards;
	}

	public void setDiscards(List<ScoresXMLDiscard> discards) {
		this.discards = discards;
	}
}