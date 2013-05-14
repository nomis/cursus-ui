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
package eu.lp0.cursus.test.scoring;

import org.junit.Test;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceAttendee;
import eu.lp0.cursus.db.data.RaceEvent;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.scoring.data.Scores;
import eu.lp0.cursus.scoring.scorer.Scorer;
import eu.lp0.cursus.scoring.scorer.ScorerFactory;
import eu.lp0.cursus.test.util.OverallAssertUtil;
import eu.lp0.cursus.test.util.RaceAssertUtil;

/**
 * When a pilot has an average score for all the races, even with a discard,
 * they end up with fleet+1 for all races as there is nothing to average.
 */
public class RaceDiscardTest {
	@Test
	public void score4Races() throws Exception {
		Series series = new Series("Series 1"); //$NON-NLS-1$
		Event event = new Event(series, "Event"); //$NON-NLS-1$
		series.getEvents().add(event);
		Race race1 = new Race(event, "Race 1"); //$NON-NLS-1$
		event.getRaces().add(race1);
		Race race2 = new Race(event, "Race 2"); //$NON-NLS-1$
		event.getRaces().add(race2);
		Race race3 = new Race(event, "Race 3"); //$NON-NLS-1$
		event.getRaces().add(race3);
		Race race4 = new Race(event, "Race 4"); //$NON-NLS-1$
		event.getRaces().add(race4);

		Pilot pilot1 = new Pilot(series, "Pilot 1"); //$NON-NLS-1$
		race1.getAttendees().put(pilot1, new RaceAttendee(race1, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race1, race1.getAttendees().get(pilot1));
		race2.getAttendees().put(pilot1, new RaceAttendee(race2, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race2, race2.getAttendees().get(pilot1));
		race3.getAttendees().put(pilot1, new RaceAttendee(race3, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race3, race3.getAttendees().get(pilot1));
		race4.getAttendees().put(pilot1, new RaceAttendee(race4, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race4, race4.getAttendees().get(pilot1));
		series.getPilots().add(pilot1);

		Pilot pilot2 = new Pilot(series, "Pilot 2"); //$NON-NLS-1$
		race1.getAttendees().put(pilot2, new RaceAttendee(race1, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race1, race1.getAttendees().get(pilot2));
		race2.getAttendees().put(pilot2, new RaceAttendee(race2, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race2, race2.getAttendees().get(pilot2));
		race3.getAttendees().put(pilot2, new RaceAttendee(race3, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race3, race3.getAttendees().get(pilot2));
		race4.getAttendees().put(pilot2, new RaceAttendee(race4, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race4, race4.getAttendees().get(pilot2));
		series.getPilots().add(pilot2);

		Pilot pilot3 = new Pilot(series, "Pilot 3"); //$NON-NLS-1$
		race1.getAttendees().put(pilot3, new RaceAttendee(race1, pilot3, RaceAttendee.Type.PILOT));
		pilot3.getRaces().put(race1, race1.getAttendees().get(pilot3));
		race2.getAttendees().put(pilot3, new RaceAttendee(race2, pilot3, RaceAttendee.Type.PILOT));
		pilot3.getRaces().put(race2, race2.getAttendees().get(pilot3));
		race3.getAttendees().put(pilot3, new RaceAttendee(race3, pilot3, RaceAttendee.Type.PILOT));
		pilot3.getRaces().put(race3, race3.getAttendees().get(pilot3));
		race4.getAttendees().put(pilot3, new RaceAttendee(race4, pilot3, RaceAttendee.Type.PILOT));
		pilot3.getRaces().put(race4, race4.getAttendees().get(pilot3));
		series.getPilots().add(pilot3);

		race1.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$
		race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot2)); //$NON-NLS-1$

		race2.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot2)); //$NON-NLS-1$
		race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$

		race3.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$
		race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot2)); //$NON-NLS-1$

		race4.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$

		Scorer scorer = ScorerFactory.newScorer(TestScorer1.UUID);
		Scores scores = scorer.scoreSeries(series);

		RaceAssertUtil race1AssertUtil = new RaceAssertUtil(scores, race1);
		race1AssertUtil.assertPilot(pilot1, 1, 0, false, 0, 1);
		race1AssertUtil.assertPilot(pilot2, 1, 0, false, 2, 2);
		race1AssertUtil.assertPilot(pilot3, 0, 0, false, 4, 3);
		race1AssertUtil.assertDone(0);

		RaceAssertUtil race2AssertUtil = new RaceAssertUtil(scores, race2);
		race2AssertUtil.assertPilot(pilot2, 1, 0, false, 0, 1);
		race2AssertUtil.assertPilot(pilot1, 1, 0, false, 2, 2);
		race2AssertUtil.assertPilot(pilot3, 0, 0, false, 4, 3);
		race2AssertUtil.assertDone(0);

		RaceAssertUtil race3AssertUtil = new RaceAssertUtil(scores, race3);
		race3AssertUtil.assertPilot(pilot1, 1, 0, false, 0, 1);
		race3AssertUtil.assertPilot(pilot2, 1, 0, false, 2, 2);
		race3AssertUtil.assertPilot(pilot3, 0, 0, false, 4, 3);
		race3AssertUtil.assertDone(0);

		RaceAssertUtil race4AssertUtil = new RaceAssertUtil(scores, race4);
		race4AssertUtil.assertPilot(pilot1, 1, 0, false, 0, 1);
		race4AssertUtil.assertPilot(pilot2, 0, 0, false, 4, 2);
		race4AssertUtil.assertPilot(pilot3, 0, 0, false, 4, 2);
		race4AssertUtil.assertDone(0);

		OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
		overallAssertUtil.assertPilot(pilot1, 0, 0, 1, 2);
		overallAssertUtil.assertPilot(pilot2, 0, 4, 2, 4);
		overallAssertUtil.assertPilot(pilot3, 0, 12, 3, 4);
		overallAssertUtil.assertOrder();
	}

	@Test
	public void score4RacesAverage1() throws Exception {
		Series series = new Series("Series 1"); //$NON-NLS-1$
		Event event = new Event(series, "Event"); //$NON-NLS-1$
		series.getEvents().add(event);
		Race race1 = new Race(event, "Race 1"); //$NON-NLS-1$
		event.getRaces().add(race1);
		Race race2 = new Race(event, "Race 2"); //$NON-NLS-1$
		event.getRaces().add(race2);
		Race race3 = new Race(event, "Race 3"); //$NON-NLS-1$
		event.getRaces().add(race3);
		Race race4 = new Race(event, "Race 4"); //$NON-NLS-1$
		event.getRaces().add(race4);

		Pilot pilot1 = new Pilot(series, "Pilot 1"); //$NON-NLS-1$
		race1.getAttendees().put(pilot1, new RaceAttendee(race1, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race1, race1.getAttendees().get(pilot1));
		race2.getAttendees().put(pilot1, new RaceAttendee(race2, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race2, race2.getAttendees().get(pilot1));
		race3.getAttendees().put(pilot1, new RaceAttendee(race3, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race3, race3.getAttendees().get(pilot1));
		race4.getAttendees().put(pilot1, new RaceAttendee(race4, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race4, race4.getAttendees().get(pilot1));
		series.getPilots().add(pilot1);

		Pilot pilot2 = new Pilot(series, "Pilot 2"); //$NON-NLS-1$
		race1.getAttendees().put(pilot2, new RaceAttendee(race1, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race1, race1.getAttendees().get(pilot2));
		race2.getAttendees().put(pilot2, new RaceAttendee(race2, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race2, race2.getAttendees().get(pilot2));
		race3.getAttendees().put(pilot2, new RaceAttendee(race3, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race3, race3.getAttendees().get(pilot2));
		race4.getAttendees().put(pilot2, new RaceAttendee(race4, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race4, race4.getAttendees().get(pilot2));
		series.getPilots().add(pilot2);

		Pilot pilot3 = new Pilot(series, "Pilot 3"); //$NON-NLS-1$
		race1.getAttendees().put(pilot3, new RaceAttendee(race1, pilot3, RaceAttendee.Type.M_SCORER));
		pilot3.getRaces().put(race1, race1.getAttendees().get(pilot3));
		race2.getAttendees().put(pilot3, new RaceAttendee(race2, pilot3, RaceAttendee.Type.PILOT));
		pilot3.getRaces().put(race2, race2.getAttendees().get(pilot3));
		race3.getAttendees().put(pilot3, new RaceAttendee(race3, pilot3, RaceAttendee.Type.PILOT));
		pilot3.getRaces().put(race3, race3.getAttendees().get(pilot3));
		race4.getAttendees().put(pilot3, new RaceAttendee(race4, pilot3, RaceAttendee.Type.PILOT));
		pilot3.getRaces().put(race4, race4.getAttendees().get(pilot3));
		series.getPilots().add(pilot3);

		Pilot pilot4 = new Pilot(series, "Pilot 4"); //$NON-NLS-1$
		race1.getAttendees().put(pilot4, new RaceAttendee(race1, pilot4, RaceAttendee.Type.PILOT));
		pilot4.getRaces().put(race1, race1.getAttendees().get(pilot4));
		race2.getAttendees().put(pilot4, new RaceAttendee(race2, pilot4, RaceAttendee.Type.PILOT));
		pilot4.getRaces().put(race2, race2.getAttendees().get(pilot4));
		race3.getAttendees().put(pilot4, new RaceAttendee(race3, pilot4, RaceAttendee.Type.PILOT));
		pilot4.getRaces().put(race3, race3.getAttendees().get(pilot4));
		series.getPilots().add(pilot4);

		Pilot pilot5 = new Pilot(series, "Pilot 5"); //$NON-NLS-1$
		race1.getAttendees().put(pilot5, new RaceAttendee(race1, pilot5, RaceAttendee.Type.PILOT));
		pilot5.getRaces().put(race1, race1.getAttendees().get(pilot5));
		race2.getAttendees().put(pilot5, new RaceAttendee(race2, pilot5, RaceAttendee.Type.PILOT));
		pilot5.getRaces().put(race2, race2.getAttendees().get(pilot5));
		series.getPilots().add(pilot5);

		Pilot pilot6 = new Pilot(series, "Pilot 6"); //$NON-NLS-1$
		race1.getAttendees().put(pilot6, new RaceAttendee(race1, pilot6, RaceAttendee.Type.PILOT));
		pilot6.getRaces().put(race1, race1.getAttendees().get(pilot6));
		series.getPilots().add(pilot6);

		race1.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$
		race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot2)); //$NON-NLS-1$

		race2.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot2)); //$NON-NLS-1$
		race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$

		race3.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$
		race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot2)); //$NON-NLS-1$

		race4.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$

		Scorer scorer = ScorerFactory.newScorer(TestScorer1.UUID);
		Scores scores = scorer.scoreSeries(series);

		RaceAssertUtil race1AssertUtil = new RaceAssertUtil(scores, race1);
		race1AssertUtil.assertPilot(pilot1, 1, 0, false, 0, 1);
		race1AssertUtil.assertPilot(pilot2, 1, 0, false, 2, 2);
		race1AssertUtil.assertPilot(pilot3, 0, 0, true, 5, 3);
		race1AssertUtil.assertPilot(pilot4, 0, 0, false, 7, 4);
		race1AssertUtil.assertPilot(pilot5, 0, 0, false, 7, 4);
		race1AssertUtil.assertPilot(pilot6, 0, 0, false, 7, 4);
		race1AssertUtil.assertDone(1);

		RaceAssertUtil race2AssertUtil = new RaceAssertUtil(scores, race2);
		race2AssertUtil.assertPilot(pilot2, 1, 0, false, 0, 1);
		race2AssertUtil.assertPilot(pilot1, 1, 0, false, 2, 2);
		race2AssertUtil.assertPilot(pilot3, 0, 0, false, 6, 3);
		race2AssertUtil.assertPilot(pilot4, 0, 0, false, 6, 3);
		race2AssertUtil.assertPilot(pilot5, 0, 0, false, 6, 3);
		race2AssertUtil.assertPilot(pilot6, 0, 0, false, 6, 3);
		race2AssertUtil.assertDone(0);

		RaceAssertUtil race3AssertUtil = new RaceAssertUtil(scores, race3);
		race3AssertUtil.assertPilot(pilot1, 1, 0, false, 0, 1);
		race3AssertUtil.assertPilot(pilot2, 1, 0, false, 2, 2);
		race3AssertUtil.assertPilot(pilot3, 0, 0, false, 5, 3);
		race3AssertUtil.assertPilot(pilot4, 0, 0, false, 5, 3);
		race3AssertUtil.assertPilot(pilot5, 0, 0, false, 5, 3);
		race3AssertUtil.assertPilot(pilot6, 0, 0, false, 5, 3);
		race3AssertUtil.assertDone(0);

		RaceAssertUtil race4AssertUtil = new RaceAssertUtil(scores, race4);
		race4AssertUtil.assertPilot(pilot1, 1, 0, false, 0, 1);
		race4AssertUtil.assertPilot(pilot2, 0, 0, false, 4, 2);
		race4AssertUtil.assertPilot(pilot3, 0, 0, false, 4, 2);
		race4AssertUtil.assertPilot(pilot4, 0, 0, false, 4, 2);
		race4AssertUtil.assertPilot(pilot5, 0, 0, false, 4, 2);
		race4AssertUtil.assertPilot(pilot6, 0, 0, false, 4, 2);
		race4AssertUtil.assertDone(0);

		OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
		overallAssertUtil.assertPilot(pilot1, 0, 0, 1, 2);
		overallAssertUtil.assertPilot(pilot2, 0, 4, 2, 4);
		overallAssertUtil.assertPilot(pilot3, 0, 14, 3, 6);
		overallAssertUtil.assertPilot(pilot4, 0, 15, 4, 7);
		overallAssertUtil.assertPilot(pilot5, 0, 15, 4, 7);
		overallAssertUtil.assertPilot(pilot6, 0, 15, 4, 7);
		overallAssertUtil.assertOrder();
	}

	@Test
	public void score4RacesAverage2() throws Exception {
		Series series = new Series("Series 1"); //$NON-NLS-1$
		Event event = new Event(series, "Event"); //$NON-NLS-1$
		series.getEvents().add(event);
		Race race1 = new Race(event, "Race 1"); //$NON-NLS-1$
		event.getRaces().add(race1);
		Race race2 = new Race(event, "Race 2"); //$NON-NLS-1$
		event.getRaces().add(race2);
		Race race3 = new Race(event, "Race 3"); //$NON-NLS-1$
		event.getRaces().add(race3);
		Race race4 = new Race(event, "Race 4"); //$NON-NLS-1$
		event.getRaces().add(race4);

		Pilot pilot1 = new Pilot(series, "Pilot 1"); //$NON-NLS-1$
		race1.getAttendees().put(pilot1, new RaceAttendee(race1, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race1, race1.getAttendees().get(pilot1));
		race2.getAttendees().put(pilot1, new RaceAttendee(race2, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race2, race2.getAttendees().get(pilot1));
		race3.getAttendees().put(pilot1, new RaceAttendee(race3, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race3, race3.getAttendees().get(pilot1));
		race4.getAttendees().put(pilot1, new RaceAttendee(race4, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race4, race4.getAttendees().get(pilot1));
		series.getPilots().add(pilot1);

		Pilot pilot2 = new Pilot(series, "Pilot 2"); //$NON-NLS-1$
		race1.getAttendees().put(pilot2, new RaceAttendee(race1, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race1, race1.getAttendees().get(pilot2));
		race2.getAttendees().put(pilot2, new RaceAttendee(race2, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race2, race2.getAttendees().get(pilot2));
		race3.getAttendees().put(pilot2, new RaceAttendee(race3, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race3, race3.getAttendees().get(pilot2));
		race4.getAttendees().put(pilot2, new RaceAttendee(race4, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race4, race4.getAttendees().get(pilot2));
		series.getPilots().add(pilot2);

		Pilot pilot3 = new Pilot(series, "Pilot 3"); //$NON-NLS-1$
		race1.getAttendees().put(pilot3, new RaceAttendee(race1, pilot3, RaceAttendee.Type.M_SCORER));
		pilot3.getRaces().put(race1, race1.getAttendees().get(pilot3));
		race2.getAttendees().put(pilot3, new RaceAttendee(race2, pilot3, RaceAttendee.Type.M_SCORER));
		pilot3.getRaces().put(race2, race2.getAttendees().get(pilot3));
		race3.getAttendees().put(pilot3, new RaceAttendee(race3, pilot3, RaceAttendee.Type.PILOT));
		pilot3.getRaces().put(race3, race3.getAttendees().get(pilot3));
		race4.getAttendees().put(pilot3, new RaceAttendee(race4, pilot3, RaceAttendee.Type.PILOT));
		pilot3.getRaces().put(race4, race4.getAttendees().get(pilot3));
		series.getPilots().add(pilot3);

		Pilot pilot4 = new Pilot(series, "Pilot 4"); //$NON-NLS-1$
		race1.getAttendees().put(pilot4, new RaceAttendee(race1, pilot4, RaceAttendee.Type.PILOT));
		pilot4.getRaces().put(race1, race1.getAttendees().get(pilot4));
		race2.getAttendees().put(pilot4, new RaceAttendee(race2, pilot4, RaceAttendee.Type.PILOT));
		pilot4.getRaces().put(race2, race2.getAttendees().get(pilot4));
		race3.getAttendees().put(pilot4, new RaceAttendee(race3, pilot4, RaceAttendee.Type.PILOT));
		pilot4.getRaces().put(race3, race3.getAttendees().get(pilot4));
		series.getPilots().add(pilot4);

		Pilot pilot5 = new Pilot(series, "Pilot 5"); //$NON-NLS-1$
		race1.getAttendees().put(pilot5, new RaceAttendee(race1, pilot5, RaceAttendee.Type.PILOT));
		pilot5.getRaces().put(race1, race1.getAttendees().get(pilot5));
		race2.getAttendees().put(pilot5, new RaceAttendee(race2, pilot5, RaceAttendee.Type.PILOT));
		pilot5.getRaces().put(race2, race2.getAttendees().get(pilot5));
		series.getPilots().add(pilot5);

		Pilot pilot6 = new Pilot(series, "Pilot 6"); //$NON-NLS-1$
		race1.getAttendees().put(pilot6, new RaceAttendee(race1, pilot6, RaceAttendee.Type.PILOT));
		pilot6.getRaces().put(race1, race1.getAttendees().get(pilot6));
		series.getPilots().add(pilot6);

		race1.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$
		race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot2)); //$NON-NLS-1$

		race2.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot2)); //$NON-NLS-1$
		race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$

		race3.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$
		race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot2)); //$NON-NLS-1$

		race4.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$

		Scorer scorer = ScorerFactory.newScorer(TestScorer1.UUID);
		Scores scores = scorer.scoreSeries(series);

		RaceAssertUtil race1AssertUtil = new RaceAssertUtil(scores, race1);
		race1AssertUtil.assertPilot(pilot1, 1, 0, false, 0, 1);
		race1AssertUtil.assertPilot(pilot2, 1, 0, false, 2, 2);
		race1AssertUtil.assertPilot(pilot3, 0, 0, true, 4, 3);
		race1AssertUtil.assertPilot(pilot4, 0, 0, false, 7, 4);
		race1AssertUtil.assertPilot(pilot5, 0, 0, false, 7, 4);
		race1AssertUtil.assertPilot(pilot6, 0, 0, false, 7, 4);
		race1AssertUtil.assertDone(1);

		RaceAssertUtil race2AssertUtil = new RaceAssertUtil(scores, race2);
		race2AssertUtil.assertPilot(pilot2, 1, 0, false, 0, 1);
		race2AssertUtil.assertPilot(pilot1, 1, 0, false, 2, 2);
		race2AssertUtil.assertPilot(pilot3, 0, 0, true, 4, 3);
		race2AssertUtil.assertPilot(pilot4, 0, 0, false, 6, 4);
		race2AssertUtil.assertPilot(pilot5, 0, 0, false, 6, 4);
		race2AssertUtil.assertPilot(pilot6, 0, 0, false, 6, 4);
		race2AssertUtil.assertDone(1);

		RaceAssertUtil race3AssertUtil = new RaceAssertUtil(scores, race3);
		race3AssertUtil.assertPilot(pilot1, 1, 0, false, 0, 1);
		race3AssertUtil.assertPilot(pilot2, 1, 0, false, 2, 2);
		race3AssertUtil.assertPilot(pilot3, 0, 0, false, 5, 3);
		race3AssertUtil.assertPilot(pilot4, 0, 0, false, 5, 3);
		race3AssertUtil.assertPilot(pilot5, 0, 0, false, 5, 3);
		race3AssertUtil.assertPilot(pilot6, 0, 0, false, 5, 3);
		race3AssertUtil.assertDone(0);

		RaceAssertUtil race4AssertUtil = new RaceAssertUtil(scores, race4);
		race4AssertUtil.assertPilot(pilot1, 1, 0, false, 0, 1);
		race4AssertUtil.assertPilot(pilot2, 0, 0, false, 4, 2);
		race4AssertUtil.assertPilot(pilot3, 0, 0, false, 4, 2);
		race4AssertUtil.assertPilot(pilot4, 0, 0, false, 4, 2);
		race4AssertUtil.assertPilot(pilot5, 0, 0, false, 4, 2);
		race4AssertUtil.assertPilot(pilot6, 0, 0, false, 4, 2);
		race4AssertUtil.assertDone(0);

		OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
		overallAssertUtil.assertPilot(pilot1, 0, 0, 1, 2);
		overallAssertUtil.assertPilot(pilot2, 0, 4, 2, 4);
		overallAssertUtil.assertPilot(pilot3, 0, 12, 3, 5);
		overallAssertUtil.assertPilot(pilot4, 0, 15, 4, 7);
		overallAssertUtil.assertPilot(pilot5, 0, 15, 4, 7);
		overallAssertUtil.assertPilot(pilot6, 0, 15, 4, 7);
		overallAssertUtil.assertOrder();
	}

	@Test
	public void score4RacesAverage3() throws Exception {
		Series series = new Series("Series 1"); //$NON-NLS-1$
		Event event = new Event(series, "Event"); //$NON-NLS-1$
		series.getEvents().add(event);
		Race race1 = new Race(event, "Race 1"); //$NON-NLS-1$
		event.getRaces().add(race1);
		Race race2 = new Race(event, "Race 2"); //$NON-NLS-1$
		event.getRaces().add(race2);
		Race race3 = new Race(event, "Race 3"); //$NON-NLS-1$
		event.getRaces().add(race3);
		Race race4 = new Race(event, "Race 4"); //$NON-NLS-1$
		event.getRaces().add(race4);

		Pilot pilot1 = new Pilot(series, "Pilot 1"); //$NON-NLS-1$
		race1.getAttendees().put(pilot1, new RaceAttendee(race1, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race1, race1.getAttendees().get(pilot1));
		race2.getAttendees().put(pilot1, new RaceAttendee(race2, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race2, race2.getAttendees().get(pilot1));
		race3.getAttendees().put(pilot1, new RaceAttendee(race3, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race3, race3.getAttendees().get(pilot1));
		race4.getAttendees().put(pilot1, new RaceAttendee(race4, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race4, race4.getAttendees().get(pilot1));
		series.getPilots().add(pilot1);

		Pilot pilot2 = new Pilot(series, "Pilot 2"); //$NON-NLS-1$
		race1.getAttendees().put(pilot2, new RaceAttendee(race1, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race1, race1.getAttendees().get(pilot2));
		race2.getAttendees().put(pilot2, new RaceAttendee(race2, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race2, race2.getAttendees().get(pilot2));
		race3.getAttendees().put(pilot2, new RaceAttendee(race3, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race3, race3.getAttendees().get(pilot2));
		race4.getAttendees().put(pilot2, new RaceAttendee(race4, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race4, race4.getAttendees().get(pilot2));
		series.getPilots().add(pilot2);

		Pilot pilot3 = new Pilot(series, "Pilot 3"); //$NON-NLS-1$
		race1.getAttendees().put(pilot3, new RaceAttendee(race1, pilot3, RaceAttendee.Type.M_SCORER));
		pilot3.getRaces().put(race1, race1.getAttendees().get(pilot3));
		race2.getAttendees().put(pilot3, new RaceAttendee(race2, pilot3, RaceAttendee.Type.M_SCORER));
		pilot3.getRaces().put(race2, race2.getAttendees().get(pilot3));
		race3.getAttendees().put(pilot3, new RaceAttendee(race3, pilot3, RaceAttendee.Type.M_SCORER));
		pilot3.getRaces().put(race3, race3.getAttendees().get(pilot3));
		race4.getAttendees().put(pilot3, new RaceAttendee(race4, pilot3, RaceAttendee.Type.PILOT));
		pilot3.getRaces().put(race4, race4.getAttendees().get(pilot3));
		series.getPilots().add(pilot3);

		Pilot pilot4 = new Pilot(series, "Pilot 4"); //$NON-NLS-1$
		race1.getAttendees().put(pilot4, new RaceAttendee(race1, pilot4, RaceAttendee.Type.PILOT));
		pilot4.getRaces().put(race1, race1.getAttendees().get(pilot4));
		race2.getAttendees().put(pilot4, new RaceAttendee(race2, pilot4, RaceAttendee.Type.PILOT));
		pilot4.getRaces().put(race2, race2.getAttendees().get(pilot4));
		race3.getAttendees().put(pilot4, new RaceAttendee(race3, pilot4, RaceAttendee.Type.PILOT));
		pilot4.getRaces().put(race3, race3.getAttendees().get(pilot4));
		series.getPilots().add(pilot4);

		Pilot pilot5 = new Pilot(series, "Pilot 5"); //$NON-NLS-1$
		race1.getAttendees().put(pilot5, new RaceAttendee(race1, pilot5, RaceAttendee.Type.PILOT));
		pilot5.getRaces().put(race1, race1.getAttendees().get(pilot5));
		race2.getAttendees().put(pilot5, new RaceAttendee(race2, pilot5, RaceAttendee.Type.PILOT));
		pilot5.getRaces().put(race2, race2.getAttendees().get(pilot5));
		series.getPilots().add(pilot5);

		Pilot pilot6 = new Pilot(series, "Pilot 6"); //$NON-NLS-1$
		race1.getAttendees().put(pilot6, new RaceAttendee(race1, pilot6, RaceAttendee.Type.PILOT));
		pilot6.getRaces().put(race1, race1.getAttendees().get(pilot6));
		series.getPilots().add(pilot6);

		race1.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$
		race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot2)); //$NON-NLS-1$

		race2.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot2)); //$NON-NLS-1$
		race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$

		race3.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$
		race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot2)); //$NON-NLS-1$

		race4.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$

		Scorer scorer = ScorerFactory.newScorer(TestScorer1.UUID);
		Scores scores = scorer.scoreSeries(series);

		RaceAssertUtil race1AssertUtil = new RaceAssertUtil(scores, race1);
		race1AssertUtil.assertPilot(pilot1, 1, 0, false, 0, 1);
		race1AssertUtil.assertPilot(pilot2, 1, 0, false, 2, 2);
		race1AssertUtil.assertPilot(pilot3, 0, 0, true, 4, 3);
		race1AssertUtil.assertPilot(pilot4, 0, 0, false, 7, 4);
		race1AssertUtil.assertPilot(pilot5, 0, 0, false, 7, 4);
		race1AssertUtil.assertPilot(pilot6, 0, 0, false, 7, 4);
		race1AssertUtil.assertDone(1);

		RaceAssertUtil race2AssertUtil = new RaceAssertUtil(scores, race2);
		race2AssertUtil.assertPilot(pilot2, 1, 0, false, 0, 1);
		race2AssertUtil.assertPilot(pilot1, 1, 0, false, 2, 2);
		race2AssertUtil.assertPilot(pilot3, 0, 0, true, 4, 3);
		race2AssertUtil.assertPilot(pilot4, 0, 0, false, 6, 4);
		race2AssertUtil.assertPilot(pilot5, 0, 0, false, 6, 4);
		race2AssertUtil.assertPilot(pilot6, 0, 0, false, 6, 4);
		race2AssertUtil.assertDone(1);

		RaceAssertUtil race3AssertUtil = new RaceAssertUtil(scores, race3);
		race3AssertUtil.assertPilot(pilot1, 1, 0, false, 0, 1);
		race3AssertUtil.assertPilot(pilot2, 1, 0, false, 2, 2);
		race3AssertUtil.assertPilot(pilot3, 0, 0, true, 4, 3);
		race3AssertUtil.assertPilot(pilot4, 0, 0, false, 5, 4);
		race3AssertUtil.assertPilot(pilot5, 0, 0, false, 5, 4);
		race3AssertUtil.assertPilot(pilot6, 0, 0, false, 5, 4);
		race3AssertUtil.assertDone(1);

		RaceAssertUtil race4AssertUtil = new RaceAssertUtil(scores, race4);
		race4AssertUtil.assertPilot(pilot1, 1, 0, false, 0, 1);
		race4AssertUtil.assertPilot(pilot2, 0, 0, false, 4, 2);
		race4AssertUtil.assertPilot(pilot3, 0, 0, false, 4, 2);
		race4AssertUtil.assertPilot(pilot4, 0, 0, false, 4, 2);
		race4AssertUtil.assertPilot(pilot5, 0, 0, false, 4, 2);
		race4AssertUtil.assertPilot(pilot6, 0, 0, false, 4, 2);
		race4AssertUtil.assertDone(0);

		OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
		overallAssertUtil.assertPilot(pilot1, 0, 0, 1, 2);
		overallAssertUtil.assertPilot(pilot2, 0, 4, 2, 4);
		overallAssertUtil.assertPilot(pilot3, 0, 12, 3, 4);
		overallAssertUtil.assertPilot(pilot4, 0, 15, 4, 7);
		overallAssertUtil.assertPilot(pilot5, 0, 15, 4, 7);
		overallAssertUtil.assertPilot(pilot6, 0, 15, 4, 7);
		overallAssertUtil.assertOrder();
	}

	@Test
	public void score4RacesAverage4() throws Exception {
		Series series = new Series("Series 1"); //$NON-NLS-1$
		Event event = new Event(series, "Event"); //$NON-NLS-1$
		series.getEvents().add(event);
		Race race1 = new Race(event, "Race 1"); //$NON-NLS-1$
		event.getRaces().add(race1);
		Race race2 = new Race(event, "Race 2"); //$NON-NLS-1$
		event.getRaces().add(race2);
		Race race3 = new Race(event, "Race 3"); //$NON-NLS-1$
		event.getRaces().add(race3);
		Race race4 = new Race(event, "Race 4"); //$NON-NLS-1$
		event.getRaces().add(race4);

		Pilot pilot1 = new Pilot(series, "Pilot 1"); //$NON-NLS-1$
		race1.getAttendees().put(pilot1, new RaceAttendee(race1, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race1, race1.getAttendees().get(pilot1));
		race2.getAttendees().put(pilot1, new RaceAttendee(race2, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race2, race2.getAttendees().get(pilot1));
		race3.getAttendees().put(pilot1, new RaceAttendee(race3, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race3, race3.getAttendees().get(pilot1));
		race4.getAttendees().put(pilot1, new RaceAttendee(race4, pilot1, RaceAttendee.Type.PILOT));
		pilot1.getRaces().put(race4, race4.getAttendees().get(pilot1));
		series.getPilots().add(pilot1);

		Pilot pilot2 = new Pilot(series, "Pilot 2"); //$NON-NLS-1$
		race1.getAttendees().put(pilot2, new RaceAttendee(race1, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race1, race1.getAttendees().get(pilot2));
		race2.getAttendees().put(pilot2, new RaceAttendee(race2, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race2, race2.getAttendees().get(pilot2));
		race3.getAttendees().put(pilot2, new RaceAttendee(race3, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race3, race3.getAttendees().get(pilot2));
		race4.getAttendees().put(pilot2, new RaceAttendee(race4, pilot2, RaceAttendee.Type.PILOT));
		pilot2.getRaces().put(race4, race4.getAttendees().get(pilot2));
		series.getPilots().add(pilot2);

		Pilot pilot3 = new Pilot(series, "Pilot 3"); //$NON-NLS-1$
		race1.getAttendees().put(pilot3, new RaceAttendee(race1, pilot3, RaceAttendee.Type.M_SCORER));
		pilot3.getRaces().put(race1, race1.getAttendees().get(pilot3));
		race2.getAttendees().put(pilot3, new RaceAttendee(race2, pilot3, RaceAttendee.Type.M_SCORER));
		pilot3.getRaces().put(race2, race2.getAttendees().get(pilot3));
		race3.getAttendees().put(pilot3, new RaceAttendee(race3, pilot3, RaceAttendee.Type.M_SCORER));
		pilot3.getRaces().put(race3, race3.getAttendees().get(pilot3));
		race4.getAttendees().put(pilot3, new RaceAttendee(race4, pilot3, RaceAttendee.Type.M_SCORER));
		pilot3.getRaces().put(race4, race4.getAttendees().get(pilot3));
		series.getPilots().add(pilot3);

		Pilot pilot4 = new Pilot(series, "Pilot 4"); //$NON-NLS-1$
		race1.getAttendees().put(pilot4, new RaceAttendee(race1, pilot4, RaceAttendee.Type.PILOT));
		pilot4.getRaces().put(race1, race1.getAttendees().get(pilot4));
		race2.getAttendees().put(pilot4, new RaceAttendee(race2, pilot4, RaceAttendee.Type.PILOT));
		pilot4.getRaces().put(race2, race2.getAttendees().get(pilot4));
		race3.getAttendees().put(pilot4, new RaceAttendee(race3, pilot4, RaceAttendee.Type.PILOT));
		pilot4.getRaces().put(race3, race3.getAttendees().get(pilot4));
		series.getPilots().add(pilot4);

		Pilot pilot5 = new Pilot(series, "Pilot 5"); //$NON-NLS-1$
		race1.getAttendees().put(pilot5, new RaceAttendee(race1, pilot5, RaceAttendee.Type.PILOT));
		pilot5.getRaces().put(race1, race1.getAttendees().get(pilot5));
		race2.getAttendees().put(pilot5, new RaceAttendee(race2, pilot5, RaceAttendee.Type.PILOT));
		pilot5.getRaces().put(race2, race2.getAttendees().get(pilot5));
		series.getPilots().add(pilot5);

		Pilot pilot6 = new Pilot(series, "Pilot 6"); //$NON-NLS-1$
		race1.getAttendees().put(pilot6, new RaceAttendee(race1, pilot6, RaceAttendee.Type.PILOT));
		pilot6.getRaces().put(race1, race1.getAttendees().get(pilot6));
		series.getPilots().add(pilot6);

		race1.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$
		race1.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot2)); //$NON-NLS-1$

		race2.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot2)); //$NON-NLS-1$
		race2.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$

		race3.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$
		race3.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot2)); //$NON-NLS-1$

		race4.getEvents().add(new RaceEvent(RaceEvent.Type.START));
		race4.getEvents().add(new RaceEvent(RaceEvent.Type.LAP, "", pilot1)); //$NON-NLS-1$

		Scorer scorer = ScorerFactory.newScorer(TestScorer1.UUID);
		Scores scores = scorer.scoreSeries(series);

		RaceAssertUtil race1AssertUtil = new RaceAssertUtil(scores, race1);
		race1AssertUtil.assertPilot(pilot1, 1, 0, false, 0, 1);
		race1AssertUtil.assertPilot(pilot2, 1, 0, false, 2, 2);
		race1AssertUtil.assertPilot(pilot3, 0, 0, true, 7, 3);
		race1AssertUtil.assertPilot(pilot4, 0, 0, false, 7, 3);
		race1AssertUtil.assertPilot(pilot5, 0, 0, false, 7, 3);
		race1AssertUtil.assertPilot(pilot6, 0, 0, false, 7, 3);
		race1AssertUtil.assertDone(1);

		RaceAssertUtil race2AssertUtil = new RaceAssertUtil(scores, race2);
		race2AssertUtil.assertPilot(pilot2, 1, 0, false, 0, 1);
		race2AssertUtil.assertPilot(pilot1, 1, 0, false, 2, 2);
		race2AssertUtil.assertPilot(pilot3, 0, 0, true, 6, 3);
		race2AssertUtil.assertPilot(pilot4, 0, 0, false, 6, 3);
		race2AssertUtil.assertPilot(pilot5, 0, 0, false, 6, 3);
		race2AssertUtil.assertPilot(pilot6, 0, 0, false, 6, 3);
		race2AssertUtil.assertDone(1);

		RaceAssertUtil race3AssertUtil = new RaceAssertUtil(scores, race3);
		race3AssertUtil.assertPilot(pilot1, 1, 0, false, 0, 1);
		race3AssertUtil.assertPilot(pilot2, 1, 0, false, 2, 2);
		race3AssertUtil.assertPilot(pilot3, 0, 0, true, 5, 3);
		race3AssertUtil.assertPilot(pilot4, 0, 0, false, 5, 3);
		race3AssertUtil.assertPilot(pilot5, 0, 0, false, 5, 3);
		race3AssertUtil.assertPilot(pilot6, 0, 0, false, 5, 3);
		race3AssertUtil.assertDone(1);

		RaceAssertUtil race4AssertUtil = new RaceAssertUtil(scores, race4);
		race4AssertUtil.assertPilot(pilot1, 1, 0, false, 0, 1);
		race4AssertUtil.assertPilot(pilot2, 0, 0, false, 4, 2);
		race4AssertUtil.assertPilot(pilot3, 0, 0, true, 4, 2);
		race4AssertUtil.assertPilot(pilot4, 0, 0, false, 4, 2);
		race4AssertUtil.assertPilot(pilot5, 0, 0, false, 4, 2);
		race4AssertUtil.assertPilot(pilot6, 0, 0, false, 4, 2);
		race4AssertUtil.assertDone(1);

		OverallAssertUtil overallAssertUtil = new OverallAssertUtil(scores);
		overallAssertUtil.assertPilot(pilot1, 0, 0, 1, 2);
		overallAssertUtil.assertPilot(pilot2, 0, 4, 2, 4);
		overallAssertUtil.assertPilot(pilot3, 0, 15, 3, 7);
		overallAssertUtil.assertPilot(pilot4, 0, 15, 3, 7);
		overallAssertUtil.assertPilot(pilot5, 0, 15, 3, 7);
		overallAssertUtil.assertPilot(pilot6, 0, 15, 3, 7);
		overallAssertUtil.assertOrder();
	}
}