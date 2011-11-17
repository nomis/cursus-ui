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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;

import eu.lp0.cursus.util.Constants;

@Entity(name = "race_no")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "series_id", "organisation", "number" }) })
public final class RaceNumber extends AbstractEntity implements Comparable<RaceNumber> {
	public static final Pattern RACE_NUMBER_PATTERN = Pattern.compile("^([^0-9]+)([0-9]+)$"); //$NON-NLS-1$

	private String organisation;
	private Integer number;

	RaceNumber() {
	}

	public RaceNumber(Pilot pilot, String organisation, int number) {
		setSeries(pilot.getSeries());
		setPilot(pilot);
		setOrganisation(organisation);
		setNumber(number);
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

	@Column(nullable = false, length = Constants.MAX_STRING_LEN)
	public String getOrganisation() {
		return organisation.replaceAll("[0-9]", ""); //$NON-NLS-1$ //$NON-NLS-2$;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation.replaceAll("[0-9]", ""); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Column(nullable = false)
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	private Pilot pilot;

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	@Override
	public int compareTo(RaceNumber o) {
		return ComparisonChain.start().compare(getOrganisation(), o.getOrganisation()).compare(getNumber(), o.getNumber()).result();
	}

	@Override
	public String toString() {
		return String.format("%s%02d", getOrganisation(), getNumber()); //$NON-NLS-1$
	}

	public static RaceNumber valueOfFor(String value, Pilot pilot) {
		Matcher matcher = RACE_NUMBER_PATTERN.matcher(value);
		Preconditions.checkArgument(matcher.matches(), "Unable to parse race number: %s", value); //$NON-NLS-1$
		String organisation = matcher.group(1);
		int number = Integer.valueOf(matcher.group(2));

		RaceNumber raceNumber = new RaceNumber(pilot, organisation, number);
		for (RaceNumber pilotRaceNumber : pilot.getRaceNumbers()) {
			if (pilotRaceNumber.compareTo(raceNumber) == 0) {
				raceNumber = pilotRaceNumber;
			}
		}
		return raceNumber;
	}
}