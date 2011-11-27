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

import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.util.Messages;

/**
 * Penalty
 */
@Embeddable
public final class Penalty implements Comparable<Penalty>, Cloneable {
	public enum Type {
		/** Number of penalties where the points given for them are calculated automatically */
		AUTOMATIC ("penalty.automatic"), //$NON-NLS-1$

		/** Total fixed penalty points to be applied */
		FIXED ("penalty.fixed"); //$NON-NLS-1$

		private final String key;

		private Type(String key) {
			this.key = key;
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
		this(type, 1, reason);
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