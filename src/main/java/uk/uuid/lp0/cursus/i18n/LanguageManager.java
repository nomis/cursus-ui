/*
	cursus - Race series management program
	Copyright 2011, 2013-2014  Simon Arlott

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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.uuid.lp0.cursus.util.MainEventBus;

import com.google.common.base.Preconditions;

public class LanguageManager {
	private static final Logger log = LoggerFactory.getLogger(LanguageManager.class);
	private static final String PREF_LOCALE_LANGUAGE = "Messages/locale/language"; //$NON-NLS-1$
	private static final String PREF_LOCALE_COUNTRY = "Messages/locale/country"; //$NON-NLS-1$
	private static final String PREF_LOCALE_VARIANT = "Messages/locale/variant"; //$NON-NLS-1$
	private static final Preferences pref = Preferences.userNodeForPackage(LanguageManager.class);

	private static Locale currentLocale = null;
	private static Locale selectedLocale = Locale.ROOT;

	static {
		Locale preferredLocale = getPreferredLocale();

		if (log.isTraceEnabled()) {
			log.trace("Default locale is \"{}\"", Locale.getDefault()); //$NON-NLS-1$
			log.trace("Preferred locale is \"{}\"", preferredLocale); //$NON-NLS-1$ 

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
			synchronized (LanguageManager.class) {
				Preconditions.checkState(currentLocale != null);
				MainEventBus.register(o, new LocaleChangeEvent(null, currentLocale, selectedLocale));
			}
		} else {
			MainEventBus.register(o);
		}
	}

	public static void unregister(Object o) {
		MainEventBus.unregister(o);
	}

	private static void changedLocale(final Locale newLocale, final Locale newSelected) {
		synchronized (LanguageManager.class) {
			final Locale oldLocale = currentLocale;
			final String oldToString = oldLocale == null ? "null" : "\"" + oldLocale + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			if (log.isTraceEnabled()) {
				log.trace("Changed locale from " + oldToString + " to \"" + newLocale + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					MainEventBus.post(new LocaleChangeEvent(oldLocale, newLocale, newSelected));
				}
			});

			currentLocale = newLocale;
			selectedLocale = newSelected;
		}
	}

	private static void changeLocale(final Locale newLocale) {
		Locale loadLocale = newLocale.equals(Locale.ROOT) ? Locale.getDefault() : newLocale;
		try {
			final ResourceBundle resourceBundle = ResourceBundle.getBundle(Messages.BUNDLE_NAME, newLocale, new UTF8Control());

			log.debug("Loaded resource bundle \"{}\" for locale \"{}\"", resourceBundle.getLocale(), loadLocale); //$NON-NLS-1$ 

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

	public static void setPreferredLocale(Locale locale) {
		synchronized (pref) {
			pref.put(PREF_LOCALE_LANGUAGE, locale.getLanguage());
			pref.put(PREF_LOCALE_COUNTRY, locale.getCountry());
			pref.put(PREF_LOCALE_VARIANT, locale.getVariant());
			if (log.isTraceEnabled()) {
				log.trace("Set preferred locale to \"" + locale + "\""); //$NON-NLS-1$ //$NON-NLS-2$
			}
			changeLocale(locale);
		}
	}

	private static class UTF8Control extends ResourceBundle.Control {
		@Override
		public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException,
				InstantiationException, IOException {
			InputStream stream = loader.getResourceAsStream(toResourceName(toBundleName(baseName, locale), "properties")); //$NON-NLS-1$

			if (stream == null) {
				return null;
			}

			try {
				return new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8")); //$NON-NLS-1$
			} finally {
				stream.close();
			}
		}
	}
}