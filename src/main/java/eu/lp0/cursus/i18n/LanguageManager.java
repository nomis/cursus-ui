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

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;

public class LanguageManager {
	private static final Logger log = LoggerFactory.getLogger(LanguageManager.class);
	private static final EventBus TEMP_BUS = new EventBus(LanguageManager.class.getSimpleName());
	private static final EventBus EVENT_BUS = new EventBus(Locale.class.getSimpleName());

	private static final String PREF_LOCALE_LANGUAGE = "Messages/locale/language"; //$NON-NLS-1$
	private static final String PREF_LOCALE_COUNTRY = "Messages/locale/country"; //$NON-NLS-1$
	private static final String PREF_LOCALE_VARIANT = "Messages/locale/variant"; //$NON-NLS-1$
	private static final Preferences pref = Preferences.userNodeForPackage(LanguageManager.class);

	private static Locale currentLocale = null;
	private static Locale selectedLocale = Locale.ROOT;

	static {
		Locale preferredLocale = LanguageManager.getPreferredLocale();

		if (log.isTraceEnabled()) {
			log.trace("Default locale is \"" + Locale.getDefault() + "\""); //$NON-NLS-1$ //$NON-NLS-2$
			log.trace("Preferred locale is \"" + preferredLocale + "\""); //$NON-NLS-1$ //$NON-NLS-2$

			if (preferredLocale.equals(Locale.ROOT)) {
				log.trace("Using default locale"); //$NON-NLS-1$
			} else {
				log.trace("Using preferred locale"); //$NON-NLS-1$
			}
		}

		try {
			changeLocale(preferredLocale);
		} catch (MissingResourceException e) {
			if (!preferredLocale.equals(Locale.ROOT)) {
				changeLocale(Locale.ROOT);
			} else {
				throw e;
			}
		}
	}

	public static void register(final Object o, boolean fireCurrentLocale) {
		if (fireCurrentLocale) {
			synchronized (TEMP_BUS) {
				TEMP_BUS.register(o);
				try {
					synchronized (EVENT_BUS) {
						Preconditions.checkState(currentLocale != null);
						TEMP_BUS.post(new LocaleChangeEvent(null, currentLocale, selectedLocale));
						EVENT_BUS.register(o);
					}
				} finally {
					TEMP_BUS.unregister(o);
				}
			}
		} else {
			EVENT_BUS.register(o);
		}
	}

	private static void changedLocale(final Locale newLocale, final Locale newSelected) {
		synchronized (EVENT_BUS) {
			final Locale oldLocale = currentLocale;
			final String oldToString = oldLocale == null ? "null" : "\"" + oldLocale + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			if (log.isTraceEnabled()) {
				log.trace("Changed locale from " + oldToString + " to \"" + newLocale + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					if (log.isTraceEnabled()) {
						log.trace("Notify locale change from " + oldToString + " to \"" + newLocale + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
					EVENT_BUS.post(new LocaleChangeEvent(oldLocale, newLocale, newSelected));
					if (log.isTraceEnabled()) {
						log.trace("Notified locale change from " + oldToString + " to \"" + newLocale + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
				}
			});

			currentLocale = newLocale;
			selectedLocale = newSelected;
		}
	}

	private static synchronized void changeLocale(Locale newLocale) {
		Locale loadLocale = newLocale.equals(Locale.ROOT) ? Locale.getDefault() : newLocale;
		try {
			ResourceBundle resourceBundle = ResourceBundle.getBundle(Messages.BUNDLE_NAME, newLocale);

			if (log.isDebugEnabled()) {
				log.debug("Loaded resource bundle \"" + resourceBundle.getLocale() + "\" for locale \"" + loadLocale + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}

			Messages.setBundle(new CheckedResourceBundle(resourceBundle));
			LanguageManager.changedLocale(resourceBundle.getLocale(), newLocale);
		} catch (MissingResourceException e) {
			log.error("Missing resource bundle for locale \"" + loadLocale + "\"", e); //$NON-NLS-1$ //$NON-NLS-2$
			throw e;
		}
	}

	public static Locale getPreferredLocale() {
		synchronized (pref) {
			return new Locale(pref.get(PREF_LOCALE_LANGUAGE, ""), //$NON-NLS-1$
					pref.get(PREF_LOCALE_COUNTRY, ""), //$NON-NLS-1$
					pref.get(PREF_LOCALE_VARIANT, "")); //$NON-NLS-1$
		}
	}

	public static synchronized void setPreferredLocale(Locale locale) {
		synchronized (pref) {
			pref.put(PREF_LOCALE_LANGUAGE, locale.getLanguage());
			pref.put(PREF_LOCALE_COUNTRY, locale.getCountry());
			pref.put(PREF_LOCALE_VARIANT, locale.getVariant());
			if (log.isTraceEnabled()) {
				log.trace("Set preferred locale to \"" + locale + "\""); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		changeLocale(locale);
	}
}