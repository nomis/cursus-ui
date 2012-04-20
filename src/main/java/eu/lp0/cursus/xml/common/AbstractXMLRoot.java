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

@NamespaceList({ @Namespace(prefix = "xml", reference = AbstractXMLRoot.XML_XMLNS), @Namespace(prefix = "xsi", reference = AbstractXMLRoot.XSI_XMLNS) })
public abstract class AbstractXMLRoot {
	public static final String XML_XMLNS = "http://www.w3.org/XML/1998/namespace"; //$NON-NLS-1$
	public static final String XSI_XMLNS = "http://www.w3.org/2001/XMLSchema-instance"; //$NON-NLS-1$

	public static final String NS_XML_CURSUS_OID = "1.3.6.1.4.1.39777.1.0.1"; //$NON-NLS-1$
	public static final String OID_URN_PREFIX = "urn:oid:"; //$NON-NLS-1$
	public static final String XSD_URI_PREFIX = "https://xsd.s85.org/urn/oid/"; //$NON-NLS-1$
}