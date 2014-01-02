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
package eu.lp0.cursus.i18n;

import java.util.Locale;

public class LocaleChangeEvent {
	private final Locale oldLocale;
	private final Locale newLocale;
	private final Locale selLocale;

	public LocaleChangeEvent(Locale oldLocale, Locale newLocale, Locale selLocale) {
		this.oldLocale = oldLocale;
		this.newLocale = newLocale;
		this.selLocale = selLocale;
	}

	public Locale getOldLocale() {
		return oldLocale;
	}

	public Locale getNewLocale() {
		return newLocale;
	}

	public Locale getSelectedLocale() {
		return selLocale;
	}
}