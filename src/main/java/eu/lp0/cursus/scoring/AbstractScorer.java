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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;

public abstract class AbstractScorer implements Scorer {
	@Override
	public Scores scoreSeries(Series series) {
		return scoreSeries(series, series.getPilots());
	}

	@Override
	public Scores scoreSeries(Series series, Set<Pilot> pilots) {
		List<Race> races = new ArrayList<Race>();
		for (Event event : series.getEvents()) {
			races.addAll(event.getRaces());
		}
		return scoreRaces(races, pilots);
	}

	@Override
	public Scores scoreEvent(Event event) {
		return scoreRaces(event.getRaces());
	}

	@Override
	public Scores scoreEvent(Event event, Set<Pilot> pilots) {
		return scoreRaces(event.getRaces(), pilots);
	}

	@Override
	public Scores scoreRace(Race race) {
		return scoreRace(race, race.getAttendees().keySet());
	}

	@Override
	public Scores scoreRace(Race race, Set<Pilot> pilots) {
		return scoreRaces(Arrays.asList(race), pilots);
	}

	@Override
	public Scores scoreRaces(List<Race> races) {
		Set<Pilot> pilots = new HashSet<Pilot>();
		for (Race race : races) {
			pilots.addAll(race.getAttendees().keySet());
		}
		return scoreRaces(races, pilots);
	}
}