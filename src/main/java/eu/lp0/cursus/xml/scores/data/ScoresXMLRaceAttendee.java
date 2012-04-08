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

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import eu.lp0.cursus.db.data.Penalty;
import eu.lp0.cursus.db.data.RaceAttendee;
import eu.lp0.cursus.xml.ExportReferenceManager;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLPilotRef;

@Root(name = "attendee")
public class ScoresXMLRaceAttendee {
	public ScoresXMLRaceAttendee() {
	}

	public ScoresXMLRaceAttendee(ExportReferenceManager refMgr, RaceAttendee attendee) {
		pilot = refMgr.get(attendee.getPilot());
		role = attendee.getType();

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
	private RaceAttendee.Type role;

	public RaceAttendee.Type getRole() {
		return role;
	}

	public void setRole(RaceAttendee.Type role) {
		this.role = role;
	}

	@ElementList(required = false)
	private List<ScoresXMLPenalty> penalties;

	public List<ScoresXMLPenalty> getPenalties() {
		return penalties;
	}

	public void setPenalties(List<ScoresXMLPenalty> penalties) {
		this.penalties = penalties;
	}
}