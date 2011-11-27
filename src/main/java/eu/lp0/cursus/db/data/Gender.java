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
package eu.lp0.cursus.db.data;

import eu.lp0.cursus.util.Messages;

public enum Gender {
	/** Male */
	MALE ("gender.male"), //$NON-NLS-1$

	/** Female */
	FEMALE ("gender.female"); //$NON-NLS-1$

	private final String key;

	private Gender(String key) {
		this.key = key;
	}

	public String toLongString() {
		return Messages.getString(key + ".long"); //$NON-NLS-1$
	}

	public String toShortString() {
		return Messages.getString(key + ".short"); //$NON-NLS-1$
	}

	@Override
	public String toString() {
		return toLongString();
	}
}