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

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;

public class LanguageManager {
	private static final Logger log = LoggerFactory.getLogger(LanguageManager.class);
	private static final EventBus TEMP_BUS = new EventBus(LanguageManager.class.getSimpleName());
	private static final EventBus EVENT_BUS = new EventBus(Locale.class.getSimpleName());
	private static Locale currentLocale = null;
	private static Locale selectedLocale = Locale.ROOT;

	public static void register(final Object o, boolean fireCurrentLocale) {
		// Force static initialisation to run
		Messages.getPreferredLocale();

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

	static void changedLocale(final Locale newLocale, final Locale newSelected) {
		synchronized (EVENT_BUS) {
			final Locale oldLocale = currentLocale;
			final String oldToString = oldLocale == null ? "null" : "\"" + oldLocale + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

			if (log.isTraceEnabled()) {
				log.trace("Changing locale from " + oldToString + " to \"" + newLocale + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					log.trace("Notify locale change from " + oldToString + " to \"" + newLocale + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EVENT_BUS.post(new LocaleChangeEvent(oldLocale, newLocale, newSelected));
					log.trace("Notified locale change from " + oldToString + " to \"" + newLocale + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
			});

			currentLocale = newLocale;
			selectedLocale = newSelected;
		}
	}
}