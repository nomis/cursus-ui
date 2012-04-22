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

import eu.lp0.cursus.db.data.Penalty;
import eu.lp0.cursus.db.data.RaceAttendee;
import eu.lp0.cursus.xml.common.AbstractXMLEntity;
import eu.lp0.cursus.xml.data.ref.DataXMLPilotRef;

public class DataXMLRaceAttendee implements Comparable<DataXMLRaceAttendee>, DataXMLPilotRef {
	public DataXMLRaceAttendee() {
	}

	public DataXMLRaceAttendee(RaceAttendee attendee) {
		pilot = AbstractXMLEntity.generateId(attendee.getPilot());
		type = attendee.getType();

		if (!attendee.getPenalties().isEmpty()) {
			penalties = new ArrayList<DataXMLPenalty>();
			for (Penalty penalty : attendee.getPenalties()) {
				penalties.add(new DataXMLPenalty(penalty));
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

	private RaceAttendee.Type type;

	public RaceAttendee.Type getType() {
		return type;
	}

	public void setType(RaceAttendee.Type type) {
		this.type = type;
	}

	private ArrayList<DataXMLPenalty> penalties;

	public ArrayList<DataXMLPenalty> getPenalties() {
		return penalties;
	}

	public void setPenalties(ArrayList<DataXMLPenalty> penalties) {
		this.penalties = penalties;
	}

	@Override
	public int compareTo(DataXMLRaceAttendee o) {
		return getPilot().compareTo(o.getPilot());
	}
}