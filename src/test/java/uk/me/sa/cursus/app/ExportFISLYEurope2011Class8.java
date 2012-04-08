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

import java.io.File;

import org.fisly.cursus.test.europe_2011.Series2011Class8Scores;
import org.junit.Ignore;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.data.Gender;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.scoring.FleetFilter;
import eu.lp0.cursus.scoring.Scores;
import eu.lp0.cursus.xml.scores.ScoresXML;
import eu.lp0.cursus.xml.scores.ScoresXMLFile;
import eu.lp0.cursus.xml.scores.XMLScores;

public class ExportFISLYEurope2011Class8 {
	public static final File SERIES_FILE_A1 = new File("target/fislyEurope2011Class8_a1.xml"); //$NON-NLS-1$
	public static final File SERIES_FILE_A2 = new File("target/fislyEurope2011Class8_a2.xml"); //$NON-NLS-1$
	public static final File SERIES_FILE_M1 = new File("target/fislyEurope2011Class8_m1.xml"); //$NON-NLS-1$
	public static final File SERIES_FILE_M2 = new File("target/fislyEurope2011Class8_m2.xml"); //$NON-NLS-1$
	public static final File SERIES_FILE_F1 = new File("target/fislyEurope2011Class8_f1.xml"); //$NON-NLS-1$
	public static final File SERIES_FILE_F2 = new File("target/fislyEurope2011Class8_f2.xml"); //$NON-NLS-1$

	@Ignore
	public static class AllScores extends Series2011Class8Scores {
		public final ScoresXML exportA;
		public final ScoresXML exportM;
		public final ScoresXML exportF;

		public AllScores() throws Exception {
			Scores seriesScoresA;
			Scores seriesScoresM;
			Scores seriesScoresF;

			createDatabase();
			createData();

			db.startSession();
			try {
				DatabaseSession.begin();

				Series series = seriesDAO.find(SERIES_NAME);
				seriesScoresA = scorer.scoreSeries(series);
				seriesScoresM = scorer.scoreSeries(series, FleetFilter.from(Gender.MALE));
				seriesScoresF = scorer.scoreSeries(series, FleetFilter.from(Gender.FEMALE));

				exportA = new ScoresXML(seriesScoresA, null, null);
				exportM = new ScoresXML(seriesScoresM, null, null);
				exportF = new ScoresXML(seriesScoresF, null, null);

				DatabaseSession.commit();
			} finally {
				db.endSession();
			}

			closeDatabase();
		}
	}

	public static void main(String[] args) throws Exception {
		AllScores allScores = new AllScores();

		{
			ScoresXML export1 = allScores.exportA;
			export1.to(SERIES_FILE_A1);

			ScoresXML import_ = new ScoresXML();
			import_.from(SERIES_FILE_A1);

			ScoresXMLFile file = import_.getData();
			Scores seriesScores;
			XMLScores xmlScores = new XMLScores(file);
			seriesScores = xmlScores.newInstance(file.getSeriesScores());

			ScoresXML export2 = new ScoresXML(seriesScores, null, null);
			export2.to(SERIES_FILE_A2);
		}

		{
			ScoresXML export1 = allScores.exportM;
			export1.to(SERIES_FILE_M1);

			ScoresXML import_ = new ScoresXML();
			import_.from(SERIES_FILE_M1);

			ScoresXMLFile file = import_.getData();
			Scores seriesScores;
			XMLScores xmlScores = new XMLScores(file);
			seriesScores = xmlScores.newInstance(file.getSeriesScores());

			ScoresXML export2 = new ScoresXML(seriesScores, null, null);
			export2.to(SERIES_FILE_M2);
		}

		{
			ScoresXML export1 = allScores.exportF;
			export1.to(SERIES_FILE_F1);

			ScoresXML import_ = new ScoresXML();
			import_.from(SERIES_FILE_F1);

			ScoresXMLFile file = import_.getData();
			Scores seriesScores;
			XMLScores xmlScores = new XMLScores(file);
			seriesScores = xmlScores.newInstance(file.getSeriesScores());

			ScoresXML export2 = new ScoresXML(seriesScores, null, null);
			export2.to(SERIES_FILE_F2);
		}
	}
}