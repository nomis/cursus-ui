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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;

import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Penalty;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceAttendee;
import eu.lp0.cursus.db.data.RaceNumber;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.scoring.GenericScores;
import eu.lp0.cursus.xml.ImportReferenceManager;
import eu.lp0.cursus.xml.scores.data.ScoresXMLOverallScore;
import eu.lp0.cursus.xml.scores.data.ScoresXMLPenalty;
import eu.lp0.cursus.xml.scores.data.ScoresXMLRaceAttendee;
import eu.lp0.cursus.xml.scores.data.ScoresXMLRaceNumber;
import eu.lp0.cursus.xml.scores.data.ScoresXMLRaceScore;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLClass;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLEvent;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLPilot;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLRace;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLSeries;
import eu.lp0.cursus.xml.scores.ref.ScoresXMLClassRef;
import eu.lp0.cursus.xml.scores.ref.ScoresXMLEventRef;
import eu.lp0.cursus.xml.scores.ref.ScoresXMLPilotRef;
import eu.lp0.cursus.xml.scores.ref.ScoresXMLRaceRef;
import eu.lp0.cursus.xml.scores.ref.ScoresXMLSeriesRef;
import eu.lp0.cursus.xml.scores.results.AbstractScoresXMLResults;
import eu.lp0.cursus.xml.scores.results.ScoresXMLEventRaceResults;
import eu.lp0.cursus.xml.scores.results.ScoresXMLEventResults;
import eu.lp0.cursus.xml.scores.results.ScoresXMLRaceResults;
import eu.lp0.cursus.xml.scores.results.ScoresXMLSeriesEventResults;
import eu.lp0.cursus.xml.scores.results.ScoresXMLSeriesResults;

public class XMLScores {
	private ScoresXML scoresXML;
	private ImportReferenceManager refMgr = new ImportReferenceManager();
	private Map<ScoresXMLSeries, Series> series = new HashMap<ScoresXMLSeries, Series>();
	private Map<ScoresXMLClass, Class> classes = new HashMap<ScoresXMLClass, Class>();
	private Map<ScoresXMLPilot, Pilot> pilots = new HashMap<ScoresXMLPilot, Pilot>();
	private Map<ScoresXMLEvent, Event> events = new HashMap<ScoresXMLEvent, Event>();
	private Map<ScoresXMLRace, Race> races = new HashMap<ScoresXMLRace, Race>();

	private Multimap<? super AbstractScoresXMLResults, Race> resultsRaces = LinkedHashMultimap.create();
	private Multimap<? super AbstractScoresXMLResults, Event> resultsEvents = HashMultimap.create();
	private Multimap<? super AbstractScoresXMLResults, Pilot> resultsPilots = HashMultimap.create();

	private Map<Race, ScoresXMLEventRaceResults> seriesEventRaceResults = new HashMap<Race, ScoresXMLEventRaceResults>();
	private Table<ScoresXMLEventResults, Race, ScoresXMLEventRaceResults> eventEventRaceResults = HashBasedTable.create();

	private Map<? super AbstractScoresXMLResults, Table<Pilot, Race, ScoresXMLRaceScore>> resultsPilotRaceScores = new HashMap<AbstractScoresXMLResults, Table<Pilot, Race, ScoresXMLRaceScore>>();
	private Table<? super AbstractScoresXMLResults, Pilot, ScoresXMLOverallScore> resultsPilotOverallScores = HashBasedTable.create();

	public XMLScores(ScoresXML scoresXML) {
		this.scoresXML = scoresXML;
		extractEntities();
		extractSeriesResults();
		extractEventResults();
		extractRaceResults();
	}

	public GenericScores newInstance(ScoresXMLSeriesResults results) {
		return newInstance(results, new Subset(results));
	}

	public GenericScores newInstance(ScoresXMLEventResults results) {
		return newInstance(results, new Subset(results));
	}

	public GenericScores newInstance(ScoresXMLRaceResults results) {
		return newInstance(results, new Subset(results));
	}

	Series dereference(ScoresXMLSeriesRef series_) {
		return series.get(refMgr.get(series_));
	}

	Class dereference(ScoresXMLClassRef class_) {
		return classes.get(refMgr.get(class_));
	}

	Pilot dereference(ScoresXMLPilotRef pilot) {
		return pilots.get(refMgr.get(pilot));
	}

	Event dereference(ScoresXMLEventRef event) {
		return events.get(refMgr.get(event));
	}

	Race dereference(ScoresXMLRaceRef race) {
		return races.get(refMgr.get(race));
	}

	private void extractEntities() {
		ScoresXMLSeries xmlSeries = scoresXML.getSeries();
		refMgr.put(xmlSeries);
		Series series_ = new Series(wrapNull(xmlSeries.getName()), wrapNull(xmlSeries.getDescription()));
		series.put(xmlSeries, series_);

		for (ScoresXMLClass xmlClass : scoresXML.getSeries().getClasses()) {
			refMgr.put(xmlClass);
			Class class_ = new Class(series_, wrapNull(xmlClass.getName()), wrapNull(xmlClass.getDescription()));
			classes.put(xmlClass, class_);
			series_.getClasses().add(class_);
		}

		for (ScoresXMLPilot xmlPilot : scoresXML.getSeries().getPilots()) {
			refMgr.put(xmlPilot);
			Pilot pilot_ = new Pilot(series_, wrapNull(xmlPilot.getName()), xmlPilot.getGender(), wrapNull(xmlPilot.getCountry()));
			pilots.put(xmlPilot, pilot_);
			series_.getPilots().add(pilot_);

			ScoresXMLRaceNumber xmlRaceNumber = xmlPilot.getRaceNumber();
			RaceNumber raceNumber = new RaceNumber(pilot_, wrapNull(xmlRaceNumber.getOrganisation()), xmlRaceNumber.getNumber());
			pilot_.setRaceNumber(raceNumber);

			for (ScoresXMLClassRef xmlClass : xmlPilot.getClasses()) {
				pilot_.getClasses().add(dereference(xmlClass));
			}
		}

		for (ScoresXMLEvent xmlEvent : scoresXML.getSeries().getEvents()) {
			refMgr.put(xmlEvent);
			Event event = new Event(series_, wrapNull(xmlEvent.getName()), wrapNull(xmlEvent.getDescription()));
			events.put(xmlEvent, event);
			series_.getEvents().add(event);

			for (ScoresXMLRace xmlRace : xmlEvent.getRaces()) {
				refMgr.put(xmlRace);
				Race race = new Race(event, wrapNull(xmlRace.getName()), wrapNull(xmlRace.getDescription()));
				races.put(xmlRace, race);
				event.getRaces().add(race);

				for (ScoresXMLRaceAttendee xmlRaceAttendee : xmlRace.getAttendees()) {
					RaceAttendee attendee = new RaceAttendee(race, dereference(xmlRaceAttendee.getPilot()), xmlRaceAttendee.getType());
					race.getAttendees().put(attendee.getPilot(), attendee);

					if (xmlRaceAttendee.getPenalties() != null) {
						for (ScoresXMLPenalty xmlPenalty : xmlRaceAttendee.getPenalties()) {
							Penalty penalty = new Penalty(xmlPenalty.getType(), xmlPenalty.getValue(), wrapNull(xmlPenalty.getReason()));
							attendee.getPenalties().add(penalty);
						}
					}
				}
			}
		}
	}

	static String wrapNull(String value) {
		return value == null ? "" : value; //$NON-NLS-1$
	}

	private void extractSeriesResults() {
		ScoresXMLSeriesResults seriesResults = scoresXML.getSeriesResults();
		for (ScoresXMLEventRef event : seriesResults.getEvents()) {
			resultsEvents.put(seriesResults, dereference(event));
		}

		Table<Pilot, Race, ScoresXMLRaceScore> raceScores = HashBasedTable.create();
		for (ScoresXMLSeriesEventResults seriesEventResults : seriesResults.getEventResults()) {
			for (ScoresXMLEventRaceResults eventRaceResults : seriesEventResults.getRaceResults()) {
				seriesEventRaceResults.put(dereference(eventRaceResults.getRace()), eventRaceResults);

				Race race = dereference(eventRaceResults.getRace());
				resultsRaces.put(seriesResults, race);
				for (ScoresXMLRaceScore raceScore : eventRaceResults.getRacePilots()) {
					raceScores.row(dereference(raceScore.getPilot())).put(race, raceScore);
				}
			}
		}
		resultsPilotRaceScores.put(seriesResults, raceScores);

		for (ScoresXMLOverallScore overallScore : seriesResults.getOverallPilots()) {
			Pilot pilot = dereference(overallScore.getPilot());
			resultsPilotOverallScores.row(seriesResults).put(pilot, overallScore);
			resultsPilots.put(seriesResults, pilot);
		}
	}

	private void extractEventResults() {
		for (ScoresXMLEventResults eventResult : scoresXML.getEventResults()) {
			for (ScoresXMLEventRef event : eventResult.getEvents()) {
				resultsEvents.put(eventResult, dereference(event));
			}

			Table<Pilot, Race, ScoresXMLRaceScore> raceScores = HashBasedTable.create();
			for (ScoresXMLEventRaceResults eventRaceResults : eventResult.getRaces()) {
				eventEventRaceResults.row(eventResult).put(dereference(eventRaceResults.getRace()), eventRaceResults);

				Race race = dereference(eventRaceResults.getRace());
				resultsRaces.put(eventResult, race);
				for (ScoresXMLRaceScore raceScore : eventRaceResults.getRacePilots()) {
					raceScores.row(dereference(raceScore.getPilot())).put(race, raceScore);
				}
			}
			resultsPilotRaceScores.put(eventResult, raceScores);

			for (ScoresXMLOverallScore overallScore : eventResult.getOverallPilots()) {
				Pilot pilot = dereference(overallScore.getPilot());
				resultsPilotOverallScores.row(eventResult).put(pilot, overallScore);
				resultsPilots.put(eventResult, pilot);
			}
		}
	}

	private void extractRaceResults() {
		for (ScoresXMLRaceResults raceResult : scoresXML.getRaceResults()) {
			Race race = dereference(raceResult.getRace());
			resultsRaces.put(raceResult, race);

			Table<Pilot, Race, ScoresXMLRaceScore> raceScores = HashBasedTable.create();
			for (ScoresXMLRaceScore raceScore : raceResult.getRacePilots()) {
				raceScores.row(dereference(raceScore.getPilot())).put(race, raceScore);
			}
			resultsPilotRaceScores.put(raceResult, raceScores);

			for (ScoresXMLOverallScore overallScore : raceResult.getOverallPilots()) {
				Pilot pilot = dereference(overallScore.getPilot());
				resultsPilotOverallScores.row(raceResult).put(pilot, overallScore);
				resultsPilots.put(raceResult, pilot);
			}
		}
	}

	private GenericScores newInstance(AbstractScoresXMLResults results, Subset subset) {
		return new GenericScores(ImmutableSet.copyOf(resultsPilots.get(results)), ImmutableList.copyOf(resultsRaces.get(results)),
				ImmutableSet.copyOf(resultsEvents.get(results)), Predicates.<Pilot>alwaysTrue(), new XMLScoresFactory(this, subset), new XMLScorer());
	}

	class Subset {
		private final ScoresXMLSeriesResults seriesResults;
		private final ScoresXMLEventResults eventResults;
		private final ScoresXMLRaceResults raceResults;

		private final Map<Race, ScoresXMLEventRaceResults> eventRaceResults;
		private final Table<Pilot, Race, ScoresXMLRaceScore> pilotRaceScores;
		private final Map<Pilot, ScoresXMLOverallScore> pilotOverallScores;

		public Subset(ScoresXMLSeriesResults seriesResults) {
			Preconditions.checkNotNull(seriesResults);
			Preconditions.checkArgument(scoresXML.getSeriesResults().equals(seriesResults));
			this.seriesResults = seriesResults;
			this.eventResults = null;
			this.raceResults = null;
			this.eventRaceResults = seriesEventRaceResults;
			this.pilotRaceScores = resultsPilotRaceScores.get(seriesResults);
			this.pilotOverallScores = resultsPilotOverallScores.row(seriesResults);
		}

		public Subset(ScoresXMLEventResults eventResults) {
			Preconditions.checkNotNull(eventResults);
			Preconditions.checkArgument(scoresXML.getEventResults().contains(eventResults));
			this.seriesResults = null;
			this.eventResults = eventResults;
			this.raceResults = null;
			this.eventRaceResults = eventEventRaceResults.row(eventResults);
			this.pilotRaceScores = resultsPilotRaceScores.get(eventResults);
			this.pilotOverallScores = resultsPilotOverallScores.row(eventResults);
		}

		public Subset(ScoresXMLRaceResults raceResults) {
			Preconditions.checkNotNull(raceResults);
			Preconditions.checkArgument(scoresXML.getRaceResults().contains(raceResults));
			this.seriesResults = null;
			this.eventResults = null;
			this.raceResults = raceResults;
			this.eventRaceResults = null;
			this.pilotRaceScores = resultsPilotRaceScores.get(raceResults);
			this.pilotOverallScores = resultsPilotOverallScores.row(raceResults);
		}

		public int getDiscards() {
			if (seriesResults != null) {
				return seriesResults.getDiscards();
			} else if (eventResults != null) {
				return eventResults.getDiscards();
			} else {
				return 0;
			}
		}

		public int getFleetSize(Race race) {
			if (eventRaceResults != null) {
				return eventRaceResults.get(race).getFleet();
			} else {
				return raceResults.getFleet();
			}
		}

		public List<ScoresXMLRaceScore> getRaceScores(Race race) {
			if (eventRaceResults != null) {
				return eventRaceResults.get(race).getRacePilots();
			} else {
				return raceResults.getRacePilots();
			}
		}

		public ScoresXMLRaceScore getRaceScore(Pilot pilot, Race race) {
			return pilotRaceScores.row(pilot).get(race);
		}

		public List<ScoresXMLOverallScore> getOverallScores() {
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

		public ScoresXMLOverallScore getOverallScore(Pilot pilot) {
			return pilotOverallScores.get(pilot);
		}
	}
}