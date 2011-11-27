/*
	cursus - Race series management program
	Copyright 2011  Simon Arlott

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
package eu.lp0.cursus.test.ui;

import junit.framework.Assert;

import org.junit.Test;

import eu.lp0.cursus.db.Database;
import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.data.Cursus;

public class RunApplicationTest extends AbstractUITest {
	@Test(timeout = CALL_TIMEOUT)
	public void testDatabase() {
		Database db = main.getDatabase();
		Assert.assertNotNull(db);

		db.startSession();
		try {
			DatabaseSession.begin();

			Cursus cursus = cursusDAO.findSingleton();
			Assert.assertNotNull(cursus);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}