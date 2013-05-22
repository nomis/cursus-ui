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
package eu.lp0.cursus.test.ui;

import java.sql.SQLException;
import java.util.concurrent.Callable;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleTable;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import eu.lp0.cursus.db.Database;
import eu.lp0.cursus.db.InvalidDatabaseException;
import eu.lp0.cursus.i18n.Messages;

public class RacePenaltyAddTest extends AbstractUITest {

	@Override
	protected Database createEmptyDatabase(Database database) throws InvalidDatabaseException, SQLException {
		return DummyData1.createEmptyDatabase(super.createEmptyDatabase(database));
	}

	// TODO this test causes a modal popup to appear
	@Ignore
	@Test
	public void addPenaltyTest() throws Exception {
		Accessible seriesNode = findAccessibleChildByName(raceTree, "Series 1"); //$NON-NLS-1$
		Accessible eventNode = findAccessibleChildByName(seriesNode, "Event 1"); //$NON-NLS-1$
		Accessible raceNode1 = findAccessibleChildByName(eventNode, "Race 1"); //$NON-NLS-1$

		Assert.assertNull(getSelectedRaceEntity());

		// Select race 1
		accessibleSelect(raceNode1, true);
		syncOnDatabaseRefresh();

		// Switch to the penalties tab
		Accessible penaltiesTab = findAccessibleChildByName(tabbedPane, Messages.getString("tab.penalties")); //$NON-NLS-1$
		accessibleSelect(penaltiesTab, true);
		syncOnDatabaseRefresh();

		Accessible penaltiesTabPane = findAccessibleChildByIndex(penaltiesTab, 0);
		Accessible scrollPane = findAccessibleChildByType(penaltiesTabPane, JScrollPane.class);
		Accessible viewport = findAccessibleChildByType(scrollPane, JViewport.class);
		final AccessibleTable table = findAccessibleChildByType(viewport, JTable.class).getAccessibleContext().getAccessibleTable();

		callFromEventThread(new Callable<Void>() {
			public Void call() throws Exception {
				Assert.assertEquals("+", table.getAccessibleColumnHeader().getAccessibleAt(0, 4).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals(0, table.getAccessibleRowCount());

				return null;
			}
		});

		callFromEventThread(new Callable<Void>() {
			public Void call() throws Exception {
				table.getAccessibleColumnHeader().getAccessibleAt(0, 4).getAccessibleContext().getAccessibleAction().doAccessibleAction(0);

				return null;
			}
		});

		callFromEventThread(new Callable<Void>() {
			public Void call() throws Exception {
				Assert.assertEquals("+", table.getAccessibleColumnHeader().getAccessibleAt(0, 4).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals(0, table.getAccessibleRowCount());

				return null;
			}
		});

		callFromEventThread(new Callable<Void>() {
			public Void call() throws Exception {
				table.getAccessibleColumnHeader().getAccessibleAt(0, 4).getAccessibleContext().getAccessibleAction().doAccessibleAction(0);

				return null;
			}
		});

		callFromEventThread(new Callable<Void>() {
			public Void call() throws Exception {
				Assert.assertEquals("+", table.getAccessibleColumnHeader().getAccessibleAt(0, 4).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals(0, table.getAccessibleRowCount());

				return null;
			}
		});
	}
}