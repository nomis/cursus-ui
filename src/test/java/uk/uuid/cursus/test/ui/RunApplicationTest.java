/*
	cursus - Race series management program
	Copyright 2011, 2013-2014  Simon Arlott

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
package uk.uuid.cursus.test.ui;

import org.junit.Assert;
import org.junit.Test;

import uk.uuid.cursus.db.Database;
import uk.uuid.cursus.db.DatabaseSession;
import uk.uuid.cursus.db.data.Cursus;

public class RunApplicationTest extends AbstractUITest {
	@Test
	public void testDatabase() throws Exception {
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

		Assert.assertNull(getSelectedRaceEntity());
	}
}