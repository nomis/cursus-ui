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
package eu.lp0.cursus.xml.scores.entity;

import java.util.ArrayList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.db.data.Gender;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.xml.AbstractXMLEntity;
import eu.lp0.cursus.xml.ExportReferenceManager;
import eu.lp0.cursus.xml.scores.data.ScoresXMLRaceNumber;

@Root(name = "pilot")
public class ScoresXMLPilot extends AbstractXMLEntity<Pilot> {
	public ScoresXMLPilot() {
	}

	public ScoresXMLPilot(ExportReferenceManager refMgr, Pilot pilot) {
		super(pilot);

		name = pilot.getName();
		gender = pilot.getGender();
		country = pilot.getCountry();
		if (pilot.getRaceNumber() != null) {
			raceNumber = new ScoresXMLRaceNumber(pilot.getRaceNumber());
		}

		classes = new ArrayList<ScoresXMLClassRef>(pilot.getClasses().size());
		for (Class class_ : pilot.getClasses()) {
			classes.add((ScoresXMLClassRef)refMgr.get(class_));
		}
	}

	@Element(required = Constants.SIMPLE_XML_EMPTY_STRING_REQUIRED_ELEMENT_BUG)
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

	@Element(required = Constants.SIMPLE_XML_EMPTY_STRING_REQUIRED_ELEMENT_BUG)
	private String country;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
	private ArrayList<ScoresXMLClassRef> classes;

	public ArrayList<ScoresXMLClassRef> getClasses() {
		return classes;
	}

	public void setClasses(ArrayList<ScoresXMLClassRef> classes) {
		this.classes = classes;
	}

	@Override
	public ScoresXMLPilotRef makeReference() {
		return new ScoresXMLPilotRef(this);
	}
}