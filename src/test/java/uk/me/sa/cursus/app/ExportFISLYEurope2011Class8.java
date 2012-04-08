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

import org.fisly.cursus.test.europe_2011.Series2011Class8Scores;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.scoring.Scores;
import eu.lp0.cursus.xml.ScoresXML;

public class ExportFISLYEurope2011Class8 {
	public static class AllScores extends Series2011Class8Scores {
		public final ScoresXML export;

		public AllScores() throws Exception {
			Scores seriesScores;

			createDatabase();
			createData();

			db.startSession();
			try {
				DatabaseSession.begin();

				Series series = seriesDAO.find(SERIES_NAME);
				seriesScores = scorer.scoreSeries(series);

				export = new ScoresXML(seriesScores, null, null);

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