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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Race event
 */
@Entity(name = "event")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "series_id", "name" }) })
public class Event extends AbstractEntity implements Comparable<Event>, RaceHierarchy {
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

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "series_id", nullable = false)
	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	private int seriesOrder;

	@GeneratedValue
	@Column(name = "series_order")
	public int getSeriesOrder() {
		return seriesOrder;
	}

	@SuppressWarnings("unused")
	private void setSeriesOrder(int seriesOrder) {
		this.seriesOrder = seriesOrder;
	}

	private String name;

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String description;

	@Column(nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private List<Race> races = new ArrayList<Race>();

	@OneToMany(mappedBy = "event")
	@OrderColumn(name = "event_order", nullable = false)
	public List<Race> getRaces() {
		return races;
	}

	public void setRaces(List<Race> races) {
		this.races = races;
	}

	private Map<Pilot, PilotAtEvent> penalties = new HashMap<Pilot, PilotAtEvent>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event", orphanRemoval = true)
	@MapKey
	@Column(nullable = false)
	public Map<Pilot, PilotAtEvent> getPenalties() {
		return penalties;
	}

	public void setPenalties(Map<Pilot, PilotAtEvent> penalties) {
		this.penalties = penalties;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public int compareTo(Event o) {
		if (this == o) {
			return 0;
		}

		int ret = getSeries().compareTo(o.getSeries());
		if (ret != 0) {
			return ret;
		}

		return Integer.valueOf(getSeriesOrder()).compareTo(o.getSeriesOrder());
	}
}