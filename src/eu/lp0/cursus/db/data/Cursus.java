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

/**
 * Database identifier
 */
@Entity(name = "cursus")
public class Cursus extends AbstractEntity {
	private Long version;

	public Cursus() {
	}

	public Cursus(long version, String description) {
		setVersion(version);
		setDescription(description);
	}

	@Column(nullable = false)
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	private String description;

	@Column(nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}