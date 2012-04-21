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
package eu.lp0.cursus.xml.scores.ref;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.xml.common.AbstractXMLEntity;

@Root(name = "eventAttendee")
public class ScoresXMLEventAttendee implements ScoresXMLPilotRef, Comparable<ScoresXMLEventAttendee> {
	public ScoresXMLEventAttendee() {
	}

	public ScoresXMLEventAttendee(Pilot pilot) {
		this.pilot = AbstractXMLEntity.generateId(pilot);
	}

	@Attribute
	private String pilot;

	@Override
	public String getPilot() {
		return pilot;
	}

	public void setPilot(String pilot) {
		this.pilot = pilot;
	}

	@Override
	public int compareTo(ScoresXMLEventAttendee o) {
		return getPilot().compareTo(o.getPilot());
	}
}