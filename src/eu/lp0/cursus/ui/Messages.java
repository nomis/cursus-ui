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
package eu.lp0.cursus.ui;

import java.awt.event.KeyEvent;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	private static final String BUNDLE_NAME = "eu.lp0.cursus.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	public static int getKeyEvent(String key) {
		try {
			return KeyEvent.class.getDeclaredField("VK_" + RESOURCE_BUNDLE.getString(key + '&')).getInt(KeyEvent.class); //$NON-NLS-1$
		} catch (MissingResourceException e) {
			return 0;
		} catch (IllegalArgumentException e) {
			return 0;
		} catch (SecurityException e) {
			return 0;
		} catch (IllegalAccessException e) {
			return 0;
		} catch (NoSuchFieldException e) {
			return 0;
		}
	}
}