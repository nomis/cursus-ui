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
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.prefs.Preferences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Messages {
	private static final String BUNDLE_NAME = "eu.lp0.cursus.messages"; //$NON-NLS-1$
	private static final Logger log = LoggerFactory.getLogger(Messages.class);

	private static final String PREF_LOCALE_LANGUAGE = "Messages/locale/language"; //$NON-NLS-1$
	private static final String PREF_LOCALE_COUNTRY = "Messages/locale/country"; //$NON-NLS-1$
	private static final String PREF_LOCALE_VARIANT = "Messages/locale/variant"; //$NON-NLS-1$
	private static final Preferences pref = Preferences.userNodeForPackage(Messages.class);

	private static volatile ResourceBundle RESOURCE_BUNDLE;
	private static final ConcurrentHashMap<String, Boolean> MISSING_KEYS = new ConcurrentHashMap<String, Boolean>();

	static {
		Locale preferredLocale = getPreferredLocale();

		if (log.isTraceEnabled()) {
			log.trace("Default locale is \"" + Locale.getDefault() + "\""); //$NON-NLS-1$ //$NON-NLS-2$
			log.trace("Preferred locale is \"" + preferredLocale + "\""); //$NON-NLS-1$ //$NON-NLS-2$

			if (preferredLocale.equals(Locale.ROOT)) {
				log.trace("Using default locale"); //$NON-NLS-1$
			} else {
				log.trace("Using preferred locale"); //$NON-NLS-1$
			}
		}

		changeLocale(preferredLocale);
	}

	public static synchronized void changeLocale(Locale newLocale) {
		Locale loadLocale = newLocale.equals(Locale.ROOT) ? Locale.getDefault() : newLocale;
		try {
			ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, newLocale);

			if (log.isDebugEnabled()) {
				log.debug("Loaded resource bundle \"" + resourceBundle.getLocale() + "\" for locale \"" + loadLocale + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}

			RESOURCE_BUNDLE = resourceBundle;
			MISSING_KEYS.clear();
			LanguageManager.changedLocale(resourceBundle.getLocale(), newLocale);
			setPreferredLocale(newLocale);
		} catch (MissingResourceException e) {
			log.error("Missing resource bundle for locale \"" + loadLocale + "\"", e); //$NON-NLS-1$ //$NON-NLS-2$
			throw e;
		}
	}

	public static String getString(String key) {
		ResourceBundle resourceBundle = RESOURCE_BUNDLE;
		try {
			return resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			if (resourceBundle == RESOURCE_BUNDLE && MISSING_KEYS.putIfAbsent(key, true) == null) {
				log.warn("Missing resource bundle key \"" + key + "\" in locale \"" + RESOURCE_BUNDLE.getLocale() + "\"", e); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			return '!' + key + '!';
		}
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

	public static synchronized Locale getPreferredLocale() {
		return new Locale(pref.get(PREF_LOCALE_LANGUAGE, ""), pref.get(PREF_LOCALE_COUNTRY, ""), pref.get(PREF_LOCALE_VARIANT, "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	private static synchronized void setPreferredLocale(Locale locale) {
		pref.put(PREF_LOCALE_LANGUAGE, locale.getLanguage());
		pref.put(PREF_LOCALE_COUNTRY, locale.getCountry());
		pref.put(PREF_LOCALE_VARIANT, locale.getVariant());
		log.trace("Set preferred locale to \"" + locale + "\""); //$NON-NLS-1$ //$NON-NLS-2$
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