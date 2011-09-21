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

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 * Race series
 */
@Entity(name = "series")
public class Series extends AbstractEntity implements Comparable<Series> {
	Series() {
	}

	public Series(String name) {
		setName(name);
	}

	private String name;

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Set<Pilot> pilots;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "series")
	public Set<Pilot> getPilots() {
		return pilots;
	}

	public void setPilots(Set<Pilot> pilots) {
		this.pilots = pilots;
	}

	private Set<Class> classes;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "series")
	public Set<Class> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public int compareTo(Series o) {
		return getName().compareTo(o.getName());
	}
}