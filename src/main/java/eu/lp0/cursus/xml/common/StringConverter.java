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

import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

class StringConverter implements Converter<String> {
	/**
	 * Workaround for Simple's inability to read an empty element as an empty string
	 */
	@Override
	public String read(InputNode node) throws Exception {
		String value = node.getValue();
		return value == null ? "" : value; //$NON-NLS-1$
	}

	/**
	 * Preserve whitespace
	 */
	@Override
	public void write(OutputNode node, String value) throws Exception {
		node.setAttribute("xml:space", "preserve"); //$NON-NLS-1$ //$NON-NLS-2$ 
		node.setValue(value);
	}
}