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

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import eu.lp0.cursus.db.data.Penalty;
import eu.lp0.cursus.db.data.RaceAttendee;
import eu.lp0.cursus.xml.ExportReferenceManager;
import eu.lp0.cursus.xml.scores.ref.ScoresXMLPilotRef;

@Root(name = "attendee")
public class ScoresXMLRaceAttendee implements Comparable<ScoresXMLRaceAttendee> {
	public ScoresXMLRaceAttendee() {
	}

	public ScoresXMLRaceAttendee(ExportReferenceManager refMgr, RaceAttendee attendee) {
		pilot = refMgr.get(attendee.getPilot());
		type = attendee.getType();

		if (!attendee.getPenalties().isEmpty()) {
			penalties = new ArrayList<ScoresXMLPenalty>();
			for (Penalty penalty : attendee.getPenalties()) {
				penalties.add(new ScoresXMLPenalty(penalty));
			}
		}
	}

	@Element
	private ScoresXMLPilotRef pilot;

	public ScoresXMLPilotRef getPilot() {
		return pilot;
	}

	public void setPilot(ScoresXMLPilotRef pilot) {
		this.pilot = pilot;
	}

	@Attribute
	private RaceAttendee.Type type;

	public RaceAttendee.Type getType() {
		return type;
	}

	public void setType(RaceAttendee.Type type) {
		this.type = type;
	}

	@ElementList(required = false, inline = true)
	private ArrayList<ScoresXMLPenalty> penalties;

	public ArrayList<ScoresXMLPenalty> getPenalties() {
		return penalties;
	}

	public void setPenalties(ArrayList<ScoresXMLPenalty> penalties) {
		this.penalties = penalties;
	}

	@Override
	public int compareTo(ScoresXMLRaceAttendee o) {
		return getPilot().getRef().compareTo(o.getPilot().getRef());
	}
}