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

import org.junit.Ignore;
import org.spka.cursus.test.series_2011.AbstractSeries2011;

public class CreateSPKATests2011 {
	@Ignore
	public static class AllScores extends AbstractSeries2011 {
		@SuppressWarnings("nls")
		public AllScores() throws Exception {
			createDatabase();
			CreateSPKATests create = new CreateSPKATests(db, "series_2011", "Series2011", SERIES_NAME, scorer);

			createNonEvent1Data();
			create.generate("Series2011NonEvent1Scores");
			createEvent1Races();
			create.generate("Series2011Event1Scores");
			createNonEvent2Data();
			create.generate("Series2011NonEvent2Scores");
			createEvent2Races();
			create.generate("Series2011Event2Scores");
			createNonEvent3Data();
			create.generate("Series2011NonEvent3Scores");
			createEvent3Races();
			create.generate("Series2011Event3Scores");
			createEvent4Races();
			create.generate("Series2011Event4Scores");
			createEvent5Races();
			create.generate("Series2011Event5Scores");
		}
	}

	public static void main(String[] args) throws Exception {
		new AllScores();
	}
}
