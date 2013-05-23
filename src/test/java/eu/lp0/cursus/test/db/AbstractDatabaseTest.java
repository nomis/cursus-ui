/*
	cursus - Race series management program
	Copyright 2011,2013  Simon Arlott

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
package eu.lp0.cursus.test.db;

import org.junit.After;
import org.junit.Before;

import eu.lp0.cursus.db.Database;
import eu.lp0.cursus.db.MemoryDatabase;

public abstract class AbstractDatabaseTest extends AbstractDataTest {
	protected Database db;

	@Before
	public void createDatabase() throws Exception {
		db = new MemoryDatabase(getClass().getName());
	}

	protected void populateDefaultData() {
		populateDefaultData(db);
	}

	@After
	public void closeDatabase() {
		db.close(true);
	}
}