/*
	cursus - Race series management program
	Copyright 2011-2012, 2014  Simon Arlott

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU Affero General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU Affero General Public License for more details.

	You should have received a copy of the GNU Affero General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uk.me.sa.cursus.app;

import java.sql.SQLException;

import eu.lp0.cursus.app.Main;
import eu.lp0.cursus.db.Database;
import eu.lp0.cursus.db.InvalidDatabaseException;
import eu.lp0.cursus.test.ui.DummyData1;
import eu.lp0.cursus.util.Background;

public class MainTest extends Main {
	public static void main(String[] args) {
		Background.execute(new MainTest(args));
	}

	public MainTest(String[] args) {
		super(args);
	}

	@Override
	protected Database createEmptyDatabase() throws InvalidDatabaseException, SQLException {
		return DummyData1.createEmptyDatabase(super.createEmptyDatabase());
	}
}