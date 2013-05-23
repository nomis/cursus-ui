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
package eu.lp0.cursus.test.db.data;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Ordering;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.test.db.AbstractDatabaseTest;

public class PilotClassesTests extends AbstractDatabaseTest {
	@Override
	@Before
	public void createDatabase() throws Exception {
		super.createDatabase();
		populateDefaultData();
	}

	@Test
	public void pilotClasses() {
		// Save data
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.findAll().get(0);

			Pilot pilot1 = new Pilot(series, "Pilot 1"); //$NON-NLS-1$
			pilotDAO.persist(pilot1);

			Pilot pilot2 = new Pilot(series, "Pilot 2"); //$NON-NLS-1$
			pilotDAO.persist(pilot2);

			Pilot pilot3 = new Pilot(series, "Pilot 3"); //$NON-NLS-1$
			pilotDAO.persist(pilot3);

			Class class1 = new Class(series, "Class 1"); //$NON-NLS-1$
			classDAO.persist(class1);

			Class class2 = new Class(series, "Class 2"); //$NON-NLS-1$
			classDAO.persist(class2);

			Class class3 = new Class(series, "Class 3"); //$NON-NLS-1$
			classDAO.persist(class3);

			pilot1.getClasses().add(class1);
			pilot1.getClasses().add(class2);
			pilotDAO.persist(pilot1);

			pilot2.getClasses().add(class2);
			pilot2.getClasses().add(class3);
			pilotDAO.persist(pilot2);

			pilot3.getClasses().add(class3);
			pilot3.getClasses().add(class1);
			pilotDAO.persist(pilot3);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}

		checkData(true);
	}

	@Test
	public void classPilots() {
		// Save data
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.findAll().get(0);

			Pilot pilot1 = new Pilot(series, "Pilot 1"); //$NON-NLS-1$
			pilotDAO.persist(pilot1);

			Pilot pilot2 = new Pilot(series, "Pilot 2"); //$NON-NLS-1$
			pilotDAO.persist(pilot2);

			Pilot pilot3 = new Pilot(series, "Pilot 3"); //$NON-NLS-1$
			pilotDAO.persist(pilot3);

			Class class1 = new Class(series, "Class 1"); //$NON-NLS-1$
			classDAO.persist(class1);

			Class class2 = new Class(series, "Class 2"); //$NON-NLS-1$
			classDAO.persist(class2);

			Class class3 = new Class(series, "Class 3"); //$NON-NLS-1$
			classDAO.persist(class3);

			class1.getPilots().add(pilot1);
			class1.getPilots().add(pilot3);
			classDAO.persist(class1);

			class2.getPilots().add(pilot1);
			class2.getPilots().add(pilot2);
			classDAO.persist(class2);

			class3.getPilots().add(pilot2);
			class3.getPilots().add(pilot3);
			classDAO.persist(class3);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}

		checkData(false);
	}

	private void checkData(boolean correctDirection) {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.findAll().get(0);

			Class class1 = classDAO.find(series, "Class 1"); //$NON-NLS-1$
			Class class2 = classDAO.find(series, "Class 2"); //$NON-NLS-1$
			Class class3 = classDAO.find(series, "Class 3"); //$NON-NLS-1$

			Assert.assertNotNull(class1);
			Assert.assertNotNull(class2);
			Assert.assertNotNull(class3);

			Pilot pilot1 = pilotDAO.find(series, "Pilot 1"); //$NON-NLS-1$
			Pilot pilot2 = pilotDAO.find(series, "Pilot 2"); //$NON-NLS-1$
			Pilot pilot3 = pilotDAO.find(series, "Pilot 3"); //$NON-NLS-1$

			Assert.assertNotNull(pilot1);
			Assert.assertNotNull(pilot2);
			Assert.assertNotNull(pilot3);

			if (correctDirection) {
				Assert.assertArrayEquals(Ordering.usingToString().sortedCopy(Arrays.asList(pilot1, pilot3)).toArray(),
						Ordering.usingToString().sortedCopy(class1.getPilots()).toArray());
				Assert.assertArrayEquals(Ordering.usingToString().sortedCopy(Arrays.asList(pilot1, pilot2)).toArray(),
						Ordering.usingToString().sortedCopy(class2.getPilots()).toArray());
				Assert.assertArrayEquals(Ordering.usingToString().sortedCopy(Arrays.asList(pilot2, pilot3)).toArray(),
						Ordering.usingToString().sortedCopy(class3.getPilots()).toArray());

				Assert.assertArrayEquals(Ordering.usingToString().sortedCopy(Arrays.asList(class1, class2)).toArray(),
						Ordering.usingToString().sortedCopy(pilot1.getClasses()).toArray());
				Assert.assertArrayEquals(Ordering.usingToString().sortedCopy(Arrays.asList(class2, class3)).toArray(),
						Ordering.usingToString().sortedCopy(pilot2.getClasses()).toArray());
				Assert.assertArrayEquals(Ordering.usingToString().sortedCopy(Arrays.asList(class3, class1)).toArray(),
						Ordering.usingToString().sortedCopy(pilot3.getClasses()).toArray());
			} else {
				Assert.assertEquals(0, class1.getPilots().size());
				Assert.assertEquals(0, class2.getPilots().size());
				Assert.assertEquals(0, class3.getPilots().size());

				Assert.assertEquals(0, pilot1.getClasses().size());
				Assert.assertEquals(0, pilot2.getClasses().size());
				Assert.assertEquals(0, pilot3.getClasses().size());
			}

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}
	}
}