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
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "race_no")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "organisation", "number" }) })
public class RaceNumber extends AbstractEntity implements Comparable<RaceNumber> {
	private String organisation;
	private Integer number;

	RaceNumber() {
	}

	public RaceNumber(String organisation, int number) {
		setOrganisation(organisation);
		setNumber(number);
	}

	@Column(nullable = false)
	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
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
		int ret = getOrganisation().compareTo(o.getOrganisation());
		if (ret != 0) {
			return ret;
		}

		return ((Integer) getNumber()).compareTo(o.getNumber());
	}

	@Override
	public String toString() {
		return String.format("%s%02d", getOrganisation(), getNumber()); //$NON-NLS-1$
	}
}