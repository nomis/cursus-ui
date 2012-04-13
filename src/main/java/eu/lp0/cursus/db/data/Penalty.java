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

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.google.common.collect.ComparisonChain;

import eu.lp0.cursus.i18n.Messages;
import eu.lp0.cursus.i18n.TranslatedEnum;
import eu.lp0.cursus.ui.common.HiddenEnumConstant;
import eu.lp0.cursus.util.Constants;

/**
 * Penalty
 */
@Embeddable
public final class Penalty implements Comparable<Penalty>, Cloneable {
	public enum Type implements TranslatedEnum {
		/** Number of penalties where the points given for them are calculated automatically */
		AUTOMATIC ("penalty.automatic", 1), //$NON-NLS-1$

		/** Total fixed penalty points to be applied */
		FIXED ("penalty.fixed", 1), //$NON-NLS-1$

		/** Lap count adjustment to be applied */
		LAPS ("penalty.laps", 0), //$NON-NLS-1$

		/** Non-attendance at event penalty */
		@HiddenEnumConstant
		EVENT_NON_ATTENDANCE ("penalty.event-non-attendance", 1); //$NON-NLS-1$

		private final String key;
		private final int defaultValue;

		private Type(String key, int defaultValue) {
			this.key = key;
			this.defaultValue = defaultValue;
		}

		public int getDefaultValue() {
			return defaultValue;
		}

		@Override
		public String toString() {
			return Messages.getString(key);
		}
	}

	Penalty() {
	}

	public Penalty(Type type) {
		this(type, ""); //$NON-NLS-1$
	}

	public Penalty(Type type, int value) {
		this(type, value, ""); //$NON-NLS-1$
	}

	public Penalty(Type type, String reason) {
		this(type, type.getDefaultValue(), reason);
	}

	public Penalty(Type type, int value, String reason) {
		setType(type);
		setValue(value);
		setReason(reason);
	}

	private Type type;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	private int value;

	@Column(nullable = false)
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	private String reason;

	@Column(nullable = false, length = Constants.MAX_STRING_LEN)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Penalty) {
			return compareTo((Penalty)o) == 0;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int compareTo(Penalty o) {
		return ComparisonChain.start().compare(getType(), o.getType()).compare(o.getValue(), getValue()).compare(getReason(), o.getReason()).result();
	}

	@Override
	public Penalty clone() {
		try {
			return (Penalty)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
}