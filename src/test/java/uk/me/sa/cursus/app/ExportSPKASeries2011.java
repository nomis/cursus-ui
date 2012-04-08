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
package uk.me.sa.cursus.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.spka.cursus.test.series_2011.Series2011Event3Scores;

import com.google.common.base.Predicates;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.scoring.Scores;
import eu.lp0.cursus.xml.ScoresXML;

public class ExportSPKASeries2011 {
	public static class AllScores extends Series2011Event3Scores {
		public final ScoresXML export;

		public AllScores() throws Exception {
			Scores seriesScores;
			List<Scores> eventScores = new ArrayList<Scores>();
			List<Scores> raceScores = new ArrayList<Scores>();

			createDatabase();
			createData();

			db.startSession();
			try {
				DatabaseSession.begin();

				Series series = seriesDAO.find(SERIES_NAME);
				seriesScores = scorer.scoreSeries(series, Predicates.in(getSeriesResultsPilots(series)));

				for (Event event : series.getEvents()) {
					eventScores.add(scorer.scoreRaces(event.getRaces(), Predicates.in(getEventResultsPilots(series, event))));

					for (Race race : event.getRaces()) {
						raceScores.add(scorer.scoreRaces(Collections.singletonList(race), Predicates.in(getEventResultsPilots(series, event))));
					}
				}

				export = new ScoresXML(seriesScores, eventScores, raceScores);

				DatabaseSession.commit();
			} finally {
				db.endSession();
			}

			closeDatabase();
		}
	}

	public static void main(String[] args) throws Exception {
		new AllScores().export.to(System.out);
	}
}