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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Pilot class groupings within series (to segregate race scores)
 */
@Entity(name = "class")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "series_id", "name" }) })
public class Class extends AbstractEntity implements Comparable<Class> {
	private String name;

	Class() {
	}

	public Class(Series series, String name) {
		setSeries(series);
		setName(name);
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Series series;

	@ManyToOne(optional = false)
	@JoinColumn(name = "series_id", nullable = false)
	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	private Set<Pilot> pilots = new HashSet<Pilot>();

	@ManyToMany
	@JoinTable
	public Set<Pilot> getPilots() {
		return pilots;
	}

	public void setPilots(Set<Pilot> pilots) {
		this.pilots = pilots;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public int compareTo(Class o) {
		int ret = getSeries().compareTo(o.getSeries());
		if (ret != 0) {
			return ret;
		}
		return getName().compareTo(o.getName());
	}
}