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
package eu.lp0.cursus.xml.common;

import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;

@NamespaceList(@Namespace(prefix = "xml", reference = AbstractXMLRoot.XML_XMLNS))
public class AbstractXMLRoot {
	public static final String XML_XMLNS = "http://www.w3.org/XML/1998/namespace"; //$NON-NLS-1$

	// FIXME change when PEN request is approved
	public static final String CURSUS_XMLNS = "urn:oid:1.3.6.1.3.63193331266300908.1.0.1"; //$NON-NLS-1$
}