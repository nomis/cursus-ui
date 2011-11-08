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
package org.spka.cursus.scoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceEvent;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.scoring.EventScore;
import eu.lp0.cursus.scoring.RaceScore;
import eu.lp0.cursus.scoring.Scorer;
import eu.lp0.cursus.scoring.ScoringSystem;
import eu.lp0.cursus.scoring.SeriesScore;

@ScoringSystem(uuid = Scoring2010.UUID, name = "SPKA 2010/11")
public class Scoring2010 implements Scorer {
	public static final String UUID = "d3071221-d71d-aa18-6637-d3115eea48e6"; //$NON-NLS-1$

	public int calculateFleetSize(Race race) {
		return race.getAttendees().size();
	}

	public int calculateFleetSize(Event event) {
		Set<Pilot> pilots = new HashSet<Pilot>();
		for (Race race : event.getRaces()) {
			pilots.addAll(race.getAttendees().keySet());
		}
		return pilots.size();
	}

	public int calculateFleetSize(Series series) {
		Set<Pilot> pilots = new HashSet<Pilot>();
		for (Event event : series.getEvents()) {
			for (Race race : event.getRaces()) {
				pilots.addAll(race.getAttendees().keySet());
			}
		}
		return pilots.size();
	}

	public class RaceCount implements Comparable<RaceCount> {
		private final Pilot pilot;
		private int laps;
		private int order;

		public RaceCount(Pilot pilot) {
			this.pilot = pilot;
		}

		public Pilot getPilot() {
			return pilot;
		}

		public int getLaps() {
			return laps;
		}

		public void completeLap(int event) {
			this.order = event;
			laps++;
		}

		@Override
		public int compareTo(RaceCount o) {
			// Descending laps
			int ret = Integer.valueOf(o.laps).compareTo(laps);
			if (ret != 0) {
				return ret;
			}

			// Ascending order
			ret = Integer.valueOf(order).compareTo(o.order);
			if (ret != 0) {
				return ret;
			}

			// If order is the same, there must have been no laps
			assert (laps == 0);
			return pilot.getName().compareTo(o.pilot.getName());
		}
	}

	private List<RaceScore> calculateRaceScoresForPilots(Set<Pilot> pilots, Race race) {
		Map<Pilot, RaceCount> laps = new HashMap<Pilot, RaceCount>();
		for (Pilot pilot : pilots) {
			laps.put(pilot, new RaceCount(pilot));
		}

		int events = 0;
		for (RaceEvent event : race.getEvents()) {
			events++;
			RaceCount count = laps.get(event.getPilot());
			// If they aren't marked as attending the race, they don't
			// get scored. Don't include non-attendees that may have
			// attended at other races if we're calculating event or
			// series scores.
			if (race.getAttendees().containsKey(event.getPilot())) {
				count.completeLap(events);
			}
		}

		List<RaceScore> pilotScores = new ArrayList<RaceScore>();
		int fleet = calculateFleetSize(race);
		int position = 1;
		for (RaceCount lap : new TreeSet<RaceCount>(laps.values())) {
			int points;
			if (lap.getLaps() == 0) {
				points = fleet + 1;
			} else if (position == 1) {
				points = 0;
			} else {
				points = position;
			}
			pilotScores.add(new RaceScore(lap.getPilot(), points, lap.getLaps(), position));
			if (lap.getLaps() > 0) {
				position++;
			}
		}

		return pilotScores;
	}

	public List<RaceScore> calculateRaceScores(Race race) {
		return calculateRaceScoresForPilots(race.getAttendees().keySet(), race);
	}

	public class PointsCount implements Comparable<PointsCount> {
		private final Pilot pilot;
		private int points;
		private int order;

		public PointsCount(Pilot pilot) {
			this.pilot = pilot;
		}

		public Pilot getPilot() {
			return pilot;
		}

		public int getPoints() {
			return points;
		}

		@SuppressWarnings("hiding")
		public void addPoints(int points, int order) {
			this.points += points;
			this.order = order;
		}

		@Override
		public int compareTo(PointsCount o) {
			// Ascending points
			int ret = Integer.valueOf(points).compareTo(o.points);
			if (ret != 0) {
				return ret;
			}

			// Ascending order
			ret = Integer.valueOf(order).compareTo(o.order);
			assert (ret != 0);
			return ret;
		}
	}

	private Map<Pilot, PointsCount> calculateMultiRaceScores(Set<Pilot> pilots, List<Race> races) {
		Map<Pilot, PointsCount> points = new HashMap<Pilot, PointsCount>();
		for (Pilot pilot : pilots) {
			points.put(pilot, new PointsCount(pilot));
		}

		int order = 0;
		for (Race race : races) {
			for (RaceScore score : calculateRaceScoresForPilots(pilots, race)) {
				order++;
				PointsCount count = points.get(score.getPilot());
				if (count != null) {
					count.addPoints(score.getPoints(), order);
				}
			}
		}
		return points;
	}

	public List<EventScore> calculateEventScores(Event event) {
		Set<Pilot> pilots = new HashSet<Pilot>();
		for (Race race : event.getRaces()) {
			pilots.addAll(race.getAttendees().keySet());
		}

		Map<Pilot, PointsCount> points = calculateMultiRaceScores(pilots, event.getRaces());

		List<EventScore> pilotScores = new ArrayList<EventScore>();
		int position = 1;
		int lastPoints = -1;
		int incPos = 1;
		for (PointsCount count : new TreeSet<PointsCount>(points.values())) {
			if (lastPoints != -1) {
				if (count.getPoints() > lastPoints) {
					position += incPos;
					incPos = 1;
				} else {
					incPos++;
				}
			}
			pilotScores.add(new EventScore(count.getPilot(), count.getPoints(), position));
			lastPoints = count.getPoints();
		}

		return pilotScores;
	}

	public List<SeriesScore> calculateSeriesScores(Series series) {
		Set<Pilot> pilots = new HashSet<Pilot>();
		List<Race> races = new ArrayList<Race>();
		for (Event event : series.getEvents()) {
			for (Race race : event.getRaces()) {
				pilots.addAll(race.getAttendees().keySet());
				races.add(race);
			}
		}

		Map<Pilot, PointsCount> points = calculateMultiRaceScores(pilots, races);

		List<SeriesScore> pilotScores = new ArrayList<SeriesScore>();
		int position = 1;
		int lastPoints = -1;
		int incPos = 1;
		for (PointsCount count : new TreeSet<PointsCount>(points.values())) {
			if (lastPoints != -1) {
				if (count.getPoints() > lastPoints) {
					position += incPos;
					incPos = 1;
				} else {
					incPos++;
				}
			}
			pilotScores.add(new SeriesScore(count.getPilot(), count.getPoints(), position));
			lastPoints = count.getPoints();
		}

		return pilotScores;
	}
}