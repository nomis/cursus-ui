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

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	public static final String BUNDLE_NAME = "eu.lp0.cursus.messages"; //$NON-NLS-1$
	private static volatile ResourceBundle RESOURCE_BUNDLE;

	static {
		LanguageManager.getPreferredLocale();
	}

	static void setBundle(ResourceBundle bundle) {
		RESOURCE_BUNDLE = bundle;
	}

	public static String getString(String key) {
		return RESOURCE_BUNDLE.getString(key);
	}

	public static String getString(String key, Object... args) {
		return String.format(getString(key), args);
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

	public static String getAccessibleName(String key) {
		return getString(String.format("accessible.%s.name", key)); //$NON-NLS-1$
	}

	public static String getAccessibleName(String key, Object... args) {
		return String.format(getString(String.format("accessible.%s.name", key)), args); //$NON-NLS-1$
	}

	public static String getAccessibleDesc(String key) {
		return getString(String.format("accessible.%s.desc", key)); //$NON-NLS-1$
	}

	public static String getAccessibleDesc(String key, Object... args) {
		return String.format(getString(String.format("accessible.%s.desc", key)), args); //$NON-NLS-1$
	}

	public static void setAccessible(Component c, String key) {
		c.getAccessibleContext().setAccessibleName(getAccessibleName(key));
		c.getAccessibleContext().setAccessibleDescription(getAccessibleDesc(key));
	}

	public static void setAccessible(Component c, String key, Object... args) {
		c.getAccessibleContext().setAccessibleName(getAccessibleName(key, args));
		c.getAccessibleContext().setAccessibleDescription(getAccessibleDesc(key, args));
	}
}