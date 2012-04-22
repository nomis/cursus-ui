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
package eu.lp0.cursus.xml;

import java.util.Properties;

import org.beanio.types.ConfigurableTypeHandler;
import org.beanio.types.TypeConversionException;
import org.beanio.types.TypeHandler;

public class EnumTypeHandler<T extends Enum<T>> implements ConfigurableTypeHandler {
	private Class<T> type;

	@SuppressWarnings({ "rawtypes" })
	@Override
	public TypeHandler newInstance(Properties properties) throws IllegalArgumentException {
		return new EnumTypeHandler();
	}

	@Override
	public Object parse(String text) throws TypeConversionException {
		return Enum.valueOf(type, text);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String format(Object value) {
		return ((T)value).name();
	}

	@Override
	public Class<T> getType() {
		assert (type != null);
		return type;
	}

	@SuppressWarnings("unchecked")
	public void setEnum(String value) throws ClassNotFoundException {
		type = (Class<T>)Class.forName(value);
	}
}