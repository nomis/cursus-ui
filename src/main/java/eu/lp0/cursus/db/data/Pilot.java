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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Pilot (person competing in the race)
 */
@Entity(name = "pilot")
public class Pilot extends AbstractEntity {
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
	@OneToOne(cascade = CascadeType.ALL)
	public RaceNumber getRaceNumber() {
		return number;
	}

	public void setRaceNumber(RaceNumber number) {
		this.number = number;
	}

	/**
	 * Pilot's race numbers
	 */
	private Set<RaceNumber> numbers;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pilot", orphanRemoval = true)
	public Set<RaceNumber> getRaceNumbers() {
		return numbers;
	}

	public void setRaceNumbers(Set<RaceNumber> numbers) {
		this.numbers = numbers;
	}

	private Set<SeriesClass> classes;

	@ManyToMany(mappedBy = "pilots")
	public Set<SeriesClass> getClasses() {
		return classes;
	}

	public void setClasses(Set<SeriesClass> classes) {
		this.classes = classes;
	}
}