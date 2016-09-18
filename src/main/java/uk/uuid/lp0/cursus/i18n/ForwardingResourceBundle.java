/*
	cursus - Race series management program
	Copyright 2011, 2014  Simon Arlott

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU Affero General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU Affero General Public License for more details.

	You should have received a copy of the GNU Affero General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uk.uuid.lp0.cursus.i18n;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public abstract class ForwardingResourceBundle extends ResourceBundle {
	protected abstract ResourceBundle delegate();

	@Override
	public boolean containsKey(String key) {
		return delegate().containsKey(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		return delegate().getKeys();
	}

	@Override
	public Locale getLocale() {
		return delegate().getLocale();
	}

	@Override
	protected Object handleGetObject(String key) {
		return delegate().getObject(key);
	}

	@Override
	public Set<String> keySet() {
		return delegate().keySet();
	}

	@Override
	public String toString() {
		return delegate().toString();
	}
}