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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Predicates;

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
import eu.lp0.cursus.xml.scores.entity.ScoresXMLClass;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLClassRef;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLEvent;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLEventRef;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLPilot;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLPilotRef;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLRace;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLRaceRef;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLSeries;
import eu.lp0.cursus.xml.scores.entity.ScoresXMLSeriesRef;
import eu.lp0.cursus.xml.scores.results.ScoresXMLEventRaceResults;
import eu.lp0.cursus.xml.scores.results.ScoresXMLEventResults;
import eu.lp0.cursus.xml.scores.results.ScoresXMLRaceResults;
import eu.lp0.cursus.xml.scores.results.ScoresXMLSeriesEventResults;
import eu.lp0.cursus.xml.scores.results.ScoresXMLSeriesResults;

public class XMLScores {
	private ScoresXMLFile file;
	private ImportReferenceManager refMgr = new ImportReferenceManager();
	private Map<ScoresXMLSeries, Series> series = new HashMap<ScoresXMLSeries, Series>();
	private Map<ScoresXMLClass, Class> classes = new HashMap<ScoresXMLClass, Class>();
	private Map<ScoresXMLPilot, Pilot> pilots = new HashMap<ScoresXMLPilot, Pilot>();
	private Map<ScoresXMLEvent, Event> events = new HashMap<ScoresXMLEvent, Event>();
	private Map<ScoresXMLRace, Race> races = new HashMap<ScoresXMLRace, Race>();

	public XMLScores(ScoresXMLFile file) {
		this.file = file;
		extractData();
	}

	private void extractData() {
		ScoresXMLSeries xmlSeries = file.getSeries();
		refMgr.put(xmlSeries);
		Series series_ = new Series(wrapNull(xmlSeries.getName()), wrapNull(xmlSeries.getDescription()));
		series.put(xmlSeries, series_);

		for (ScoresXMLClass xmlClass : file.getSeries().getClasses()) {
			refMgr.put(xmlClass);
			Class class_ = new Class(series_, wrapNull(xmlClass.getName()), wrapNull(xmlClass.getDescription()));
			classes.put(xmlClass, class_);
			series_.getClasses().add(class_);
		}

		for (ScoresXMLPilot xmlPilot : file.getSeries().getPilots()) {
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

		for (ScoresXMLEvent xmlEvent : file.getSeries().getEvents()) {
			refMgr.put(xmlEvent);
			Event event_ = new Event(series_, wrapNull(xmlEvent.getName()), wrapNull(xmlEvent.getDescription()));
			events.put(xmlEvent, event_);
			series_.getEvents().add(event_);

			for (ScoresXMLRace xmlRace : xmlEvent.getRaces()) {
				refMgr.put(xmlRace);
				Race race_ = new Race(event_, wrapNull(xmlRace.getName()), wrapNull(xmlRace.getDescription()));
				races.put(xmlRace, race_);
				event_.getRaces().add(race_);

				for (ScoresXMLRaceAttendee xmlRaceAttendee : xmlRace.getAttendees()) {
					RaceAttendee attendee = new RaceAttendee(race_, dereference(xmlRaceAttendee.getPilot()), xmlRaceAttendee.getType());
					race_.getAttendees().put(attendee.getPilot(), attendee);

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

	private static String wrapNull(String value) {
		return value == null ? "" : value; //$NON-NLS-1$
	}

	public Series dereference(ScoresXMLSeriesRef series_) {
		return series.get(refMgr.get(series_));
	}

	public Class dereference(ScoresXMLClassRef class_) {
		return classes.get(refMgr.get(class_));
	}

	public Pilot dereference(ScoresXMLPilotRef pilot) {
		return pilots.get(refMgr.get(pilot));
	}

	public Event dereference(ScoresXMLEventRef event) {
		return events.get(refMgr.get(event));
	}

	public Race dereference(ScoresXMLRaceRef race) {
		return races.get(refMgr.get(race));
	}

	public GenericScores newInstance(ScoresXMLSeriesResults results) {
		return new GenericScores(extractPilots(results.getOverallPilots()), extractRaces(results), Predicates.<Pilot>alwaysTrue(), new XMLScoresFactory(this,
				results), new XMLScorer());
	}

	public GenericScores newInstance(ScoresXMLEventResults results) {
		return new GenericScores(extractPilots(results.getOverallPilots()), extractRaces(results), Predicates.<Pilot>alwaysTrue(), new XMLScoresFactory(this,
				results), new XMLScorer());
	}

	public GenericScores newInstance(ScoresXMLRaceResults results) {
		return new GenericScores(extractPilots(results.getOverallPilots()), extractRaces(results), Predicates.<Pilot>alwaysTrue(), new XMLScoresFactory(this,
				results), new XMLScorer());
	}

	private Set<Pilot> extractPilots(List<ScoresXMLOverallScore> overallScores) {
		Set<Pilot> pilots_ = new HashSet<Pilot>();
		for (ScoresXMLOverallScore overallScore : overallScores) {
			pilots_.add(dereference(overallScore.getPilot()));
		}
		return pilots_;
	}

	private List<Race> extractRaces(ScoresXMLSeriesResults results) {
		List<Race> races_ = new ArrayList<Race>();
		for (ScoresXMLSeriesEventResults eventResults : results.getEvents()) {
			for (ScoresXMLEventRaceResults race : eventResults.getRaces()) {
				races_.add(dereference(race.getRace()));
			}
		}
		return races_;
	}

	private List<Race> extractRaces(ScoresXMLEventResults results) {
		List<Race> races_ = new ArrayList<Race>();
		for (ScoresXMLEventRaceResults race : results.getRaces()) {
			races_.add(dereference(race.getRace()));
		}
		return races_;
	}

	private List<Race> extractRaces(ScoresXMLRaceResults results) {
		return Collections.singletonList(dereference(results.getRace()));
	}
}
