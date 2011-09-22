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
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Pilot (person competing in the race)
 */
@Entity(name = "pilot")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "series_id", "race_no_id" }) })
public class Pilot extends AbstractEntity {
	Pilot() {
	}

	public Pilot(Series series, String name) {
		this(series, name, null, null);
	}

	public Pilot(Series series, String name, Gender gender) {
		this(series, name, gender, null);
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

	@Column(nullable = false)
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
	@OneToOne(cascade = CascadeType.ALL, optional = true)
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pilot", orphanRemoval = true)
	public Set<RaceNumber> getRaceNumbers() {
		return numbers;
	}

	public void setRaceNumbers(Set<RaceNumber> numbers) {
		this.numbers = numbers;
	}

	private Set<Class> classes = new HashSet<Class>();

	@ManyToMany(mappedBy = "pilots")
	public Set<Class> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

	private Map<Event, PilotEventPenalties> eventPenalties;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pilot", orphanRemoval = true)
	@MapKey
	@Column(nullable = false)
	public Map<Event, PilotEventPenalties> getEventPenalties() {
		return eventPenalties;
	}

	public void setEventPenalties(Map<Event, PilotEventPenalties> eventPenalties) {
		this.eventPenalties = eventPenalties;
	}

	private int fixedPenalties;

	/**
	 * Total fixed penalty points to be applied to the series score
	 */
	@Column(nullable = false)
	public int getFixedPenalties() {
		return fixedPenalties;
	}

	public void setFixedPenalties(int fixedPenalties) {
		this.fixedPenalties = fixedPenalties;
	}
}