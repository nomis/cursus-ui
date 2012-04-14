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

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.xml.common.AbstractXMLEntity;
import eu.lp0.cursus.xml.scores.ref.ScoresXMLClassRef;

@Root(name = "class")
public class ScoresXMLClass extends AbstractXMLEntity<Class> {
	public ScoresXMLClass() {
	}

	public ScoresXMLClass(Class class_) {
		super(class_);

		name = class_.getName();
		description = class_.getDescription();
	}

	@Element
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Element
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public ScoresXMLClassRef makeReference() {
		return new ScoresXMLClassRef(this);
	}
}