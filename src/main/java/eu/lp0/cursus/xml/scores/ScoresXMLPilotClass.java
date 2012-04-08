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

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import eu.lp0.cursus.db.data.Class;

@Root(name = "pilotClass")
public class ScoresXMLPilotClass {
	public ScoresXMLPilotClass() {
	}

	public ScoresXMLPilotClass(Class class_) {
		ref = Class.class.getSimpleName() + class_.getId();
	}

	@Attribute
	private String ref;

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}
}