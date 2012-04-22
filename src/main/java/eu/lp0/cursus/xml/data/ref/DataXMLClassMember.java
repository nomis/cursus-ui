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
package eu.lp0.cursus.xml.data.ref;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.xml.common.AbstractXMLEntity;
import eu.lp0.cursus.xml.data.DataXML;

@Namespace(reference = DataXML.DATA_XMLNS)
@Root(name = "member")
public class DataXMLClassMember implements DataXMLClassRef, Comparable<DataXMLClassMember> {
	public DataXMLClassMember() {
	}

	public DataXMLClassMember(Class class_) {
		this.class_ = AbstractXMLEntity.generateId(class_);
	}

	@Attribute(name = "class")
	private String class_;

	@Override
	public String getClass_() {
		return class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	@Override
	public int compareTo(DataXMLClassMember o) {
		return getClass_().compareTo(o.getClass_());
	}
}