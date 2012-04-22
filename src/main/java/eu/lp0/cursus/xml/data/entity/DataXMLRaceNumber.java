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

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import eu.lp0.cursus.db.data.RaceNumber;
import eu.lp0.cursus.xml.data.DataXML;

@Namespace(reference = DataXML.DATA_XMLNS)
@Root(name = "raceNumber")
public class DataXMLRaceNumber {
	public DataXMLRaceNumber() {
	}

	public DataXMLRaceNumber(RaceNumber raceNumber) {
		organisation = raceNumber.getOrganisation();
		number = raceNumber.getNumber();
	}

	@Attribute
	private String organisation;

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	@Attribute
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}