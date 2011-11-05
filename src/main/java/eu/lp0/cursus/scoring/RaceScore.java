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
package eu.lp0.cursus.scoring;

import eu.lp0.cursus.db.data.Pilot;

public class RaceScore {
	private final Pilot pilot;
	private final int points;
	private final int laps;
	private final int position;

	public RaceScore(Pilot pilot, int points, int laps, int position) {
		this.pilot = pilot;
		this.points = points;
		this.laps = laps;
		this.position = position;
	}

	public Pilot getPilot() {
		return pilot;
	}

	public int getPoints() {
		return points;
	}

	public int getLaps() {
		return laps;
	}

	public int getPosition() {
		return position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + laps;
		result = prime * result + ((pilot == null) ? 0 : pilot.hashCode());
		result = prime * result + points;
		result = prime * result + position;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RaceScore other = (RaceScore)obj;
		if (laps != other.laps) {
			return false;
		}
		if (pilot == null) {
			if (other.pilot != null) {
				return false;
			}
		} else if (!pilot.equals(other.pilot)) {
			return false;
		}
		if (points != other.points) {
			return false;
		}
		if (position != other.position) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "RaceScore [pilot=" + pilot + ", points=" + points + ", laps=" + laps + ", position=" + position + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	}
}