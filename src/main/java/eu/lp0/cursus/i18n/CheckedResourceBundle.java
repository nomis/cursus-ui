/*
	cursus - Race series management program
	Copyright 2011  Simon Arlott

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
package eu.lp0.cursus.i18n;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckedResourceBundle extends ForwardingResourceBundle {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final ResourceBundle bundle;
	private final ConcurrentHashMap<String, Boolean> missing = new ConcurrentHashMap<String, Boolean>();

	public CheckedResourceBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	@Override
	protected ResourceBundle delegate() {
		return bundle;
	}

	@Override
	protected Object handleGetObject(String key) {
		try {
			return super.handleGetObject(key);
		} catch (MissingResourceException e) {
			if (missing.putIfAbsent(key, true) == null) {
				log.warn("Missing resource bundle key \"" + key + "\" in locale \"" + getLocale() + "\"", e); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			return '!' + key + '!';
		}
	}
}