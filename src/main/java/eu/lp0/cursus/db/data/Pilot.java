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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import eu.lp0.cursus.util.Constants;

/**
 * Pilot (person competing in the race)
 */
@Entity(name = "pilot")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "series_id", "race_no_id" }) })
public final class Pilot extends AbstractEntity {
	Pilot() {
	}

	public Pilot(Series series, String name) {
		this(series, name, null, ""); //$NON-NLS-1$
	}

	public Pilot(Series series, String name, Gender gender) {
		this(series, name, gender, ""); //$NON-NLS-1$
	}

	public Pilot(Series series, String name, Gender gender, String country) {
		setSeries(series);
		setName(name);
		setGender(gender);
		setCountry(country);
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

	private String name;

	@Column(nullable = false, length = Constants.MAX_STRING_LEN)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Gender gender;

	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	private String country;

	@Column(nullable = false, length = Constants.MAX_STRING_LEN)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	private RaceNumber number;

	/**
	 * Primary race number
	 */
	@OneToOne(optional = true)
	@JoinColumn(name = "race_no_id", nullable = true)
	public RaceNumber getRaceNumber() {
		return number;
	}

	public void setRaceNumber(RaceNumber number) {
		this.number = number;
	}

	/**
	 * Pilot's race numbers
	 */
	private Set<RaceNumber> numbers = new HashSet<RaceNumber>();

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "pilot", orphanRemoval = true)
	public Set<RaceNumber> getRaceNumbers() {
		return numbers;
	}

	public void setRaceNumbers(Set<RaceNumber> numbers) {
		this.numbers = numbers;
	}

	private Set<Class> classes = new HashSet<Class>();

	@ManyToMany
	@JoinTable
	public Set<Class> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

	private Map<Race, RaceAttendee> races = new HashMap<Race, RaceAttendee>();

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "pilot", orphanRemoval = true)
	@MapKeyJoinColumn(name = "race_id", nullable = false)
	public Map<Race, RaceAttendee> getRaces() {
		return races;
	}

	public void setRaces(Map<Race, RaceAttendee> races) {
		this.races = races;
	}

	@Override
	public String toString() {
		return "Pilot [name=" + name + ", gender=" + gender + ", country=" + country + ", number=" + number + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$//$NON-NLS-4$ //$NON-NLS-5$
	}
}