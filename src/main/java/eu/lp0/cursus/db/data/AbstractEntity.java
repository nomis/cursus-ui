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
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import eu.lp0.cursus.db.IDGenerator;

@MappedSuperclass
public abstract class AbstractEntity implements Entity, Cloneable {
	private Long id;

	@Id
	@Column(nullable = false)
	public final Long getId() {
		if (id == null) {
			setId(IDGenerator.next());
		}
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	@Override
	public final boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (o == null) {
			return false;
		}

		if (!getClass().equals(o.getClass())) {
			return false;
		}

		AbstractEntity e = (AbstractEntity)o;
		// Don't implement lazy ID generation here as it's likely that a call to hashCode() will be made too
		return getId().equals(e.getId());
	}

	@Override
	public final int hashCode() {
		long fixedId = getId();
		return (int)(fixedId ^ (fixedId >>> 32));
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
}