/*
	cursus - Race series management program
	Copyright 2012  Simon Arlott

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
package eu.lp0.cursus.xml.scores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.LinkedListMultimap;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.AbstractOverallPenaltiesData;
import eu.lp0.cursus.scoring.AbstractOverallPointsData;
import eu.lp0.cursus.scoring.AbstractOverallPositionData;
import eu.lp0.cursus.scoring.AbstractRaceDiscardsData;
import eu.lp0.cursus.scoring.AbstractRaceLapsData;
import eu.lp0.cursus.scoring.AbstractRacePenaltiesData;
import eu.lp0.cursus.scoring.AbstractRacePointsData;
import eu.lp0.cursus.scoring.AbstractRacePositionsData;
import eu.lp0.cursus.scoring.AbstractScoresFactory;
import eu.lp0.cursus.scoring.OverallPenaltiesData;
import eu.lp0.cursus.scoring.OverallPointsData;
import eu.lp0.cursus.scoring.OverallPositionData;
import eu.lp0.cursus.scoring.RaceDiscardsData;
import eu.lp0.cursus.scoring.RaceLapsData;
import eu.lp0.cursus.scoring.RacePenaltiesData;
import eu.lp0.cursus.scoring.RacePointsData;
import eu.lp0.cursus.scoring.RacePositionsData;
import eu.lp0.cursus.scoring.ScoredData;
import eu.lp0.cursus.scoring.Scores;
import eu.lp0.cursus.xml.scores.data.ScoresXMLOverallScore;
import eu.lp0.cursus.xml.scores.data.ScoresXMLRaceScore;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLRaceRef;
import eu.lp0.cursus.xml.scores.results.ScoresXMLEventRaceResults;
import eu.lp0.cursus.xml.scores.results.ScoresXMLEventResults;
import eu.lp0.cursus.xml.scores.results.ScoresXMLRaceResults;
import eu.lp0.cursus.xml.scores.results.ScoresXMLSeriesEventResults;
import eu.lp0.cursus.xml.scores.results.ScoresXMLSeriesResults;

public class XMLScoresFactory extends AbstractScoresFactory {
	private final XMLScores file;
	private final ScoresXMLSeriesResults seriesResults;
	private final ScoresXMLEventResults eventResults;
	private final ScoresXMLRaceResults raceResults;

	public XMLScoresFactory(XMLScores file, ScoresXMLSeriesResults seriesResults) {
		this.file = file;
		this.seriesResults = seriesResults;
		this.eventResults = null;
		this.raceResults = null;
	}

	public XMLScoresFactory(XMLScores file, ScoresXMLEventResults eventResults) {
		this.file = file;
		this.seriesResults = null;
		this.eventResults = eventResults;
		this.raceResults = null;
	}

	public XMLScoresFactory(XMLScores file, ScoresXMLRaceResults raceResults) {
		this.file = file;
		this.seriesResults = null;
		this.eventResults = null;
		this.raceResults = raceResults;
	}

	private ScoresXMLEventRaceResults getEventRaceResults(Race race) {
		if (seriesResults != null) {
			for (ScoresXMLSeriesEventResults seriesEventResults : seriesResults.getEvents()) {
				for (ScoresXMLEventRaceResults eventRaceResults : seriesEventResults.getRaces()) {
					if (file.dereference(eventRaceResults.getRace()).equals(race)) {
						return eventRaceResults;
					}
				}
			}
		} else if (eventResults != null) {
			for (ScoresXMLEventRaceResults eventRaceResults : eventResults.getRaces()) {
				if (file.dereference(eventRaceResults.getRace()).equals(race)) {
					return eventRaceResults;
				}
			}
		}
		Preconditions.checkState(false);
		return null;
	}

	private List<ScoresXMLRaceScore> getRaceScores(Race race) {
		if (seriesResults != null || eventResults != null) {
			return getEventRaceResults(race).getRacePilots();
		} else if (raceResults != null) {
			return raceResults.getRacePilots();
		}
		Preconditions.checkState(false);
		return null;
	}

	private List<ScoresXMLOverallScore> getOverallScores() {
		if (seriesResults != null) {
			return seriesResults.getOverallPilots();
		} else if (eventResults != null) {
			return eventResults.getOverallPilots();
		} else if (raceResults != null) {
			return raceResults.getOverallPilots();
		}
		Preconditions.checkState(false);
		return null;
	}

	@Override
	public RaceLapsData newRaceLapsData(Scores scores) {
		return new AbstractRaceLapsData<ScoredData>(scores) {
			@Override
			protected Iterable<Pilot> calculateRaceLaps(Race race) {
				List<Pilot> pilotLaps = new ArrayList<Pilot>();
				for (ScoresXMLRaceScore raceScore : getRaceScores(race)) {
					Pilot pilot = file.dereference(raceScore.getPilot());
					for (int i = 0; i < raceScore.getLaps(); i++) {
						pilotLaps.add(pilot);
					}
				}
				return pilotLaps;
			}
		};
	}

	@Override
	public RacePointsData newRacePointsData(Scores scores) {
		return new AbstractRacePointsData<ScoredData>(scores) {
			@Override
			public int getFleetSize(Race race) {
				if (seriesResults != null || eventResults != null) {
					return getEventRaceResults(race).getFleet();
				} else if (raceResults != null) {
					return raceResults.getFleet();
				}
				Preconditions.checkState(false);
				return 0;
			}

			@Override
			protected Map<Pilot, Integer> calculateRacePoints(Race race) {
				Map<Pilot, Integer> racePoints = new HashMap<Pilot, Integer>();
				for (ScoresXMLRaceScore raceScore : getRaceScores(race)) {
					Pilot pilot = file.dereference(raceScore.getPilot());
					racePoints.put(pilot, raceScore.getPoints());
				}
				return racePoints;
			}

			@Override
			protected boolean calculateSimulatedRacePoints(Pilot pilot, Race race) {
				for (ScoresXMLRaceScore raceScore : getRaceScores(race)) {
					Pilot pilot_ = file.dereference(raceScore.getPilot());
					if (pilot_.equals(pilot)) {
						return raceScore.isSimulated();
					}
				}
				Preconditions.checkState(false);
				return false;
			}
		};
	}

	@Override
	public RacePenaltiesData newRacePenaltiesData(Scores scores) {
		return new AbstractRacePenaltiesData<ScoredData>(scores) {
			@Override
			protected int calculateRacePenalties(Pilot pilot, Race race) {
				for (ScoresXMLRaceScore raceScore : getRaceScores(race)) {
					Pilot pilot_ = file.dereference(raceScore.getPilot());
					if (pilot_.equals(pilot)) {
						return raceScore.getPenalties();
					}
				}
				Preconditions.checkState(false);
				return 0;
			}
		};
	}

	@Override
	public RacePositionsData newRacePositionsData(Scores scores) {
		return new AbstractRacePositionsData<ScoredData>(scores) {
			@Override
			protected LinkedListMultimap<Integer, Pilot> calculateRacePositionsWithOrder(Race race) {
				LinkedListMultimap<Integer, Pilot> racePositions = LinkedListMultimap.create();
				for (ScoresXMLRaceScore raceScore : getRaceScores(race)) {
					Pilot pilot = file.dereference(raceScore.getPilot());
					racePositions.put(raceScore.getPosition(), pilot);
				}
				return racePositions;
			}
		};
	}

	@Override
	public RaceDiscardsData newRaceDiscardsData(Scores scores) {
		int discards;
		if (seriesResults != null) {
			discards = seriesResults.getDiscards();
		} else if (eventResults != null) {
			discards = eventResults.getDiscards();
		} else if (raceResults != null) {
			discards = 0;
		} else {
			Preconditions.checkState(false);
			return null;
		}
		return new AbstractRaceDiscardsData<Scores>(scores, discards) {
			@Override
			protected List<Race> calculateDiscardedRaces(Pilot pilot) {
				List<Race> races = new ArrayList<Race>();
				if (discards > 0) {
					for (ScoresXMLOverallScore overallScore : getOverallScores()) {
						Pilot pilot_ = file.dereference(overallScore.getPilot());
						if (pilot_.equals(pilot)) {
							for (ScoresXMLRaceRef race : overallScore.getDiscards()) {
								races.add(file.dereference(race));
							}
						}
					}
				}
				return races;
			}
		};
	}

	@Override
	public OverallPenaltiesData newOverallPenaltiesData(Scores scores) {
		return new AbstractOverallPenaltiesData<ScoredData>(scores) {
			@Override
			protected int calculateOverallPenalties(Pilot pilot) {
				for (ScoresXMLOverallScore overallScore : getOverallScores()) {
					Pilot pilot_ = file.dereference(overallScore.getPilot());
					if (pilot_.equals(pilot)) {
						return overallScore.getPenalties();
					}
				}
				Preconditions.checkState(false);
				return 0;
			}
		};
	}

	@Override
	public OverallPointsData newOverallPointsData(Scores scores) {
		return new AbstractOverallPointsData<ScoredData>(scores) {
			@Override
			protected int calculateOverallPoints(Pilot pilot) {
				for (ScoresXMLOverallScore overallScore : getOverallScores()) {
					Pilot pilot_ = file.dereference(overallScore.getPilot());
					if (pilot_.equals(pilot)) {
						return overallScore.getPoints();
					}
				}
				Preconditions.checkState(false);
				return 0;
			}
		};
	}

	@Override
	public OverallPositionData newOverallPositionData(Scores scores) {
		return new AbstractOverallPositionData<ScoredData>(scores) {
			@Override
			protected LinkedListMultimap<Integer, Pilot> calculateOverallPositionsWithOrder() {
				LinkedListMultimap<Integer, Pilot> overallPositions = LinkedListMultimap.create();
				for (ScoresXMLOverallScore overallScore : getOverallScores()) {
					Pilot pilot_ = file.dereference(overallScore.getPilot());
					overallPositions.put(overallScore.getPosition(), pilot_);
				}
				return overallPositions;
			}
		};
	}
}