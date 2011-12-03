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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;

import eu.lp0.cursus.util.Constants;

/**
 * Race event
 */
@Entity(name = "event")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "series_id", "name" }) })
public final class Event extends AbstractEntity implements Comparable<Event>, RaceEntity {
	Event() {
	}

	public Event(Series series) {
		this(series, "", ""); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public Event(Series series, String name) {
		this(series, name, ""); //$NON-NLS-1$
	}

	public Event(Series series, String name, String description) {
		setSeries(series);
		setName(name);
		setDescription(description);
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

	@Column(name = "series_order", nullable = false)
	public Integer getSeriesOrder() {
		int index = getSeries().getEvents().indexOf(this);
		Preconditions.checkState(index != -1, "Event does not exist in series"); //$NON-NLS-1$
		return index;
	}

	@SuppressWarnings("unused")
	private void setSeriesOrder(int seriesOrder) {
		// Preconditions.checkArgument(getSeriesOrder() == seriesOrder);
	}

	private String name;

	@Column(nullable = false, length = Constants.MAX_STRING_LEN)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String description;

	@Column(nullable = false, length = Constants.MAX_STRING_LEN)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private List<Race> races = new ArrayList<Race>();

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "event", orphanRemoval = true)
	@OrderColumn(name = "event_order", nullable = false)
	public List<Race> getRaces() {
		return races;
	}

	public void setRaces(List<Race> races) {
		this.races = races;
	}

	@Override
	public String toString() {
		return getName().length() > 0 ? getName() : "[#" + getId() + "]"; //$NON-NLS-1$ //$NON-NLS-2$ 
	}

	@Override
	public int compareTo(Event o) {
		return ComparisonChain.start().compare(getSeries(), o.getSeries()).compare(getName(), o.getName()).result();
	}
}