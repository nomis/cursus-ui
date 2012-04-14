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

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import eu.lp0.cursus.db.data.Penalty;
import eu.lp0.cursus.db.data.Penalty.Type;

@Root(name = "penalty")
public class ScoresXMLPenalty {
	public ScoresXMLPenalty() {
	}

	public ScoresXMLPenalty(Penalty penalty) {
		type = penalty.getType();
		value = penalty.getValue();
		reason = penalty.getReason();
	}

	@Attribute
	private Type type;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Attribute
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Element
	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}