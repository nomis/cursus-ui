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
import org.spka.cursus.test.series_2010.AbstractSeries2010;

public class CreateSPKATests2010 {
	@Ignore
	public static class AllScores extends AbstractSeries2010 {
		@SuppressWarnings("nls")
		public AllScores() throws Exception {
			createDatabase();
			CreateSPKATests create = new CreateSPKATests(db, "series_2010", "Series2010", SERIES_NAME, scorer);

			createEvent1Races();
			create.generate("Series2010Event1Scores");
			createEvent2Races();
			create.generate("Series2010Event2Scores");
			createEvent3Races();
			create.generate("Series2010Event3Scores");
			createEvent4Races();
			create.generate("Series2010Event4Scores");
		}
	}

	public static void main(String[] args) throws Exception {
		new AllScores();
	}
}
