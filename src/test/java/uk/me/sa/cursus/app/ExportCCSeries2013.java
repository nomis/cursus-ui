/*
	cursus - Race series management program
	Copyright 2013  Simon Arlott

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

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Ignore;
import org.spka.cursus.test.cc_2013.AbstractSeries2013;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.scoring.data.Scores;
import eu.lp0.cursus.xml.scores.ScoresXML;
import eu.lp0.cursus.xml.scores.ScoresXMLFile;
import eu.lp0.cursus.xml.scores.XMLScores;
import eu.lp0.cursus.xml.scores.results.ScoresXMLEventResults;
import eu.lp0.cursus.xml.scores.results.ScoresXMLRaceResults;

public class ExportCCSeries2013 {
	public static final File SERIES_FILE1 = new File("target/cc2013_1.xml"); //$NON-NLS-1$
	public static final File SERIES_FILE2 = new File("target/cc2013_2.xml"); //$NON-NLS-1$

	@Ignore
	public static class AllScores extends AbstractSeries2013 {
		public final ScoresXMLFile export;

		public AllScores() throws Exception {
			Scores seriesScores;
			List<Scores> eventScores = new ArrayList<Scores>();
			List<Scores> raceScores = new ArrayList<Scores>();

			createDatabase();
			createEvent1Races();

			db.startSession();
			try {
				DatabaseSession.begin();

				Series series = seriesDAO.find(SERIES_NAME);
				seriesScores = scorer.scoreSeries(series);

				for (Event event : series.getEvents()) {
					if (!event.getRaces().isEmpty()) {
						eventScores.add(scorer.scoreRaces(event.getRaces()));

						for (Race race : event.getRaces()) {
							raceScores.add(scorer.scoreRaces(Collections.singletonList(race)));
						}
					}
				}

				export = new ScoresXMLFile(seriesScores, eventScores, raceScores);

				System.out.println(seriesScores.getSeries().getName());
				debugPrintScores(seriesScores);

				for (Scores scores : eventScores) {
					System.out.println();
					System.out.println(scores.getEvents().iterator().next().getName());
					debugPrintScores(scores);
				}
				for (Scores scores : raceScores) {
					System.out.println();
					System.out.println(scores.getRaces().iterator().next().getName());
					debugPrintScores(scores);
				}

				DatabaseSession.commit();
			} finally {
				db.endSession();
			}

			closeDatabase();
		}
	}

	public static void main(String[] args) throws Exception {
		ScoresXMLFile export1 = new AllScores().export;
		export1.to(SERIES_FILE1);

		ScoresXMLFile import_ = new ScoresXMLFile();
		import_.from(SERIES_FILE1);

		ScoresXML file = import_.getData();
		Scores seriesScores;
		List<Scores> eventScores = new ArrayList<Scores>();
		List<Scores> raceScores = new ArrayList<Scores>();
		XMLScores xmlScores = new XMLScores(file);
		seriesScores = xmlScores.newInstance(file.getSeriesResults());
		for (ScoresXMLEventResults scores : file.getEventResults()) {
			eventScores.add(xmlScores.newInstance(scores));
		}
		for (ScoresXMLRaceResults scores : file.getRaceResults()) {
			raceScores.add(xmlScores.newInstance(scores));
		}

		ScoresXMLFile export2 = new ScoresXMLFile(seriesScores, eventScores, raceScores);
		export2.to(SERIES_FILE2);
	}
}
