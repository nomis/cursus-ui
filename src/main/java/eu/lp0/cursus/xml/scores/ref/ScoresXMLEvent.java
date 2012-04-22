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
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.xml.common.AbstractXMLEntity;
import eu.lp0.cursus.xml.data.ref.DataXMLEventRef;
import eu.lp0.cursus.xml.scores.ScoresXML;

// Simple won't be able to read this as the name conflicts with the data xmlns
@Namespace(reference = ScoresXML.SCORES_XMLNS)
@Root(name = "event")
public class ScoresXMLEvent implements DataXMLEventRef {
	public ScoresXMLEvent() {
	}

	public ScoresXMLEvent(Event event) {
		this.event = AbstractXMLEntity.generateId(event);
	}

	@Attribute(name = "ref")
	private String event;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
}