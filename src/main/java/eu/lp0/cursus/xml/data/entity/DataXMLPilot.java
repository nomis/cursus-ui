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
package eu.lp0.cursus.xml.data.entity;

import java.util.ArrayList;
import java.util.Collections;

import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.db.data.Gender;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.xml.common.AbstractXMLEntity;
import eu.lp0.cursus.xml.data.ref.DataXMLClassMember;

public class DataXMLPilot extends AbstractXMLEntity<Pilot> {
	public DataXMLPilot() {
	}

	public DataXMLPilot(Pilot pilot) {
		super(pilot);

		name = pilot.getName();
		gender = pilot.getGender();
		country = pilot.getCountry();
		if (pilot.getRaceNumber() != null) {
			raceNumber = new DataXMLRaceNumber(pilot.getRaceNumber());
		}

		if (!pilot.getClasses().isEmpty()) {
			classes = new ArrayList<DataXMLClassMember>(pilot.getClasses().size());
			for (Class class_ : pilot.getClasses()) {
				classes.add(new DataXMLClassMember(class_));
			}
			Collections.sort(classes);
		}
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Gender gender;

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	private String country;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	private DataXMLRaceNumber raceNumber;

	public DataXMLRaceNumber getRaceNumber() {
		return raceNumber;
	}

	public void setRaceNumber(DataXMLRaceNumber raceNumber) {
		this.raceNumber = raceNumber;
	}

	private ArrayList<DataXMLClassMember> classes;

	public ArrayList<DataXMLClassMember> getClasses() {
		return classes;
	}

	public void setClasses(ArrayList<DataXMLClassMember> classes) {
		this.classes = classes;
	}
}