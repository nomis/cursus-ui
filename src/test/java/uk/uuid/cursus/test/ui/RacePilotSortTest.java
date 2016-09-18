/*
	cursus - Race series management program
	Copyright 2012-2014  Simon Arlott

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

import java.sql.SQLException;
import java.util.concurrent.Callable;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleTable;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;

import org.junit.Assert;
import org.junit.Test;

import uk.uuid.lp0.cursus.i18n.Messages;
import uk.uuid.cursus.db.Database;
import uk.uuid.cursus.db.DatabaseSession;
import uk.uuid.cursus.db.InvalidDatabaseException;
import uk.uuid.cursus.db.data.Event;
import uk.uuid.cursus.db.data.Pilot;
import uk.uuid.cursus.db.data.Race;
import uk.uuid.cursus.db.data.RaceAttendee;
import uk.uuid.cursus.db.data.Series;

public class RacePilotSortTest extends AbstractUITest {
	@Override
	protected Database createEmptyDatabase(Database database) throws InvalidDatabaseException, SQLException {
		return DummyData1.createEmptyDatabase(super.createEmptyDatabase(database));
	}

	@Test
	public void initialViewTest() throws Exception {
		Accessible seriesNode = findAccessibleChildByName(raceTree, "Series 1"); //$NON-NLS-1$
		Accessible eventNode = findAccessibleChildByName(seriesNode, "Event 1"); //$NON-NLS-1$
		Accessible raceNode1 = findAccessibleChildByName(eventNode, "Race 1"); //$NON-NLS-1$

		Assert.assertNull(getSelectedRaceEntity());

		// Select race 1
		accessibleSelect(raceNode1, true);
		syncOnDatabaseRefresh();

		// Check that they're sorted correctly
		Accessible pilotsTab = findAccessibleChildByName(tabbedPane, Messages.getString("tab.pilots")); //$NON-NLS-1$
		Accessible pilotsTabPane = findAccessibleChildByIndex(pilotsTab, 0);
		Accessible scrollPane = findAccessibleChildByType(pilotsTabPane, JScrollPane.class);
		Accessible viewport = findAccessibleChildByType(scrollPane, JViewport.class);
		final AccessibleTable table = findAccessibleChildByType(viewport, JTable.class).getAccessibleContext().getAccessibleTable();

		callFromEventThread(new Callable<Void>() {
			public Void call() throws Exception {
				Assert.assertEquals(
						Messages.getString("pilot.race-number"), table.getAccessibleColumnHeader().getAccessibleAt(0, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals(19, table.getAccessibleRowCount());
				Assert.assertEquals("A01", table.getAccessibleAt(0, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("ELB01", table.getAccessibleAt(1, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("ELB02", table.getAccessibleAt(2, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("ELB03", table.getAccessibleAt(3, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("H04", table.getAccessibleAt(4, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("H69", table.getAccessibleAt(5, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("H77", table.getAccessibleAt(6, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("H84", table.getAccessibleAt(7, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("H91", table.getAccessibleAt(8, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("K01", table.getAccessibleAt(9, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("K02", table.getAccessibleAt(10, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("K03", table.getAccessibleAt(11, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("NL30", table.getAccessibleAt(12, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("NL50", table.getAccessibleAt(13, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("SL400", table.getAccessibleAt(14, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("SL401", table.getAccessibleAt(15, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("SL406", table.getAccessibleAt(16, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("TO26", table.getAccessibleAt(17, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("TO38", table.getAccessibleAt(18, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$

				return null;
			}
		});
	}

	@Test
	public void initialViewTestWithSomePilots() throws Exception {
		// Add some pilots to the first race
		main.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find("Series 1"); //$NON-NLS-1$
			Event event = eventDAO.find(series, "Event 1"); //$NON-NLS-1$
			Race race = raceDAO.find(event, "Race 1"); //$NON-NLS-1$
			Pilot h77 = pilotDAO.find(series, "Trent"); //$NON-NLS-1$
			Pilot h84 = pilotDAO.find(series, "Trudy"); //$NON-NLS-1$
			Pilot h91 = pilotDAO.find(series, "Victor"); //$NON-NLS-1$
			Pilot k01 = pilotDAO.find(series, "Carol"); //$NON-NLS-1$
			Pilot k02 = pilotDAO.find(series, "Bob"); //$NON-NLS-1$

			race.getAttendees().put(h77, new RaceAttendee(race, h77, RaceAttendee.Type.PILOT));
			race.getAttendees().put(h84, new RaceAttendee(race, h84, RaceAttendee.Type.PILOT));
			race.getAttendees().put(h91, new RaceAttendee(race, h91, RaceAttendee.Type.PILOT));
			race.getAttendees().put(k01, new RaceAttendee(race, k01, RaceAttendee.Type.PILOT));
			race.getAttendees().put(k02, new RaceAttendee(race, k02, RaceAttendee.Type.PILOT));
			raceDAO.persist(race);

			DatabaseSession.commit();
		} finally {
			main.getDatabase().endSession();
		}

		Accessible seriesNode = findAccessibleChildByName(raceTree, "Series 1"); //$NON-NLS-1$
		Accessible eventNode = findAccessibleChildByName(seriesNode, "Event 1"); //$NON-NLS-1$
		Accessible raceNode1 = findAccessibleChildByName(eventNode, "Race 1"); //$NON-NLS-1$

		Assert.assertNull(getSelectedRaceEntity());

		// Select race 1
		accessibleSelect(raceNode1, true);
		syncOnDatabaseRefresh();

		// Check that they're sorted correctly
		Accessible pilotsTab = findAccessibleChildByName(tabbedPane, Messages.getString("tab.pilots")); //$NON-NLS-1$
		Accessible pilotsTabPane = findAccessibleChildByIndex(pilotsTab, 0);
		Accessible scrollPane = findAccessibleChildByType(pilotsTabPane, JScrollPane.class);
		Accessible viewport = findAccessibleChildByType(scrollPane, JViewport.class);
		final AccessibleTable table = findAccessibleChildByType(viewport, JTable.class).getAccessibleContext().getAccessibleTable();

		callFromEventThread(new Callable<Void>() {
			public Void call() throws Exception {
				Assert.assertEquals(
						Messages.getString("pilot.race-number"), table.getAccessibleColumnHeader().getAccessibleAt(0, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals(19, table.getAccessibleRowCount());
				Assert.assertEquals("H77", table.getAccessibleAt(0, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("H84", table.getAccessibleAt(1, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("H91", table.getAccessibleAt(2, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("K01", table.getAccessibleAt(3, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("K02", table.getAccessibleAt(4, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("A01", table.getAccessibleAt(5, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("ELB01", table.getAccessibleAt(6, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("ELB02", table.getAccessibleAt(7, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("ELB03", table.getAccessibleAt(8, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("H04", table.getAccessibleAt(9, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("H69", table.getAccessibleAt(10, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("K03", table.getAccessibleAt(11, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("NL30", table.getAccessibleAt(12, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("NL50", table.getAccessibleAt(13, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("SL400", table.getAccessibleAt(14, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("SL401", table.getAccessibleAt(15, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("SL406", table.getAccessibleAt(16, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("TO26", table.getAccessibleAt(17, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("TO38", table.getAccessibleAt(18, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$

				return null;
			}
		});
	}

	@Test
	public void initialViewTestWithSomePilotsSwitchTabs() throws Exception {
		// Add some pilots to the first race
		main.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.find("Series 1"); //$NON-NLS-1$
			Event event = eventDAO.find(series, "Event 1"); //$NON-NLS-1$
			Race race = raceDAO.find(event, "Race 1"); //$NON-NLS-1$
			Pilot h77 = pilotDAO.find(series, "Trent"); //$NON-NLS-1$
			Pilot h84 = pilotDAO.find(series, "Trudy"); //$NON-NLS-1$
			Pilot h91 = pilotDAO.find(series, "Victor"); //$NON-NLS-1$
			Pilot k01 = pilotDAO.find(series, "Carol"); //$NON-NLS-1$
			Pilot k02 = pilotDAO.find(series, "Bob"); //$NON-NLS-1$

			race.getAttendees().put(h77, new RaceAttendee(race, h77, RaceAttendee.Type.PILOT));
			race.getAttendees().put(h84, new RaceAttendee(race, h84, RaceAttendee.Type.PILOT));
			race.getAttendees().put(h91, new RaceAttendee(race, h91, RaceAttendee.Type.PILOT));
			race.getAttendees().put(k01, new RaceAttendee(race, k01, RaceAttendee.Type.PILOT));
			race.getAttendees().put(k02, new RaceAttendee(race, k02, RaceAttendee.Type.PILOT));
			raceDAO.persist(race);

			DatabaseSession.commit();
		} finally {
			main.getDatabase().endSession();
		}

		Accessible seriesNode = findAccessibleChildByName(raceTree, "Series 1"); //$NON-NLS-1$
		Accessible eventNode = findAccessibleChildByName(seriesNode, "Event 1"); //$NON-NLS-1$
		Accessible raceNode1 = findAccessibleChildByName(eventNode, "Race 1"); //$NON-NLS-1$

		Assert.assertNull(getSelectedRaceEntity());

		// Select race 1
		accessibleSelect(raceNode1, true);
		syncOnDatabaseRefresh();

		// Switch to the laps tab
		Accessible lapsTab = findAccessibleChildByName(tabbedPane, Messages.getString("tab.laps")); //$NON-NLS-1$
		accessibleSelect(lapsTab, true);
		syncOnDatabaseRefresh();

		// Switch back to the pilots tab
		Accessible pilotsTab = findAccessibleChildByName(tabbedPane, Messages.getString("tab.pilots")); //$NON-NLS-1$
		accessibleSelect(pilotsTab, true);
		syncOnDatabaseRefresh();

		// Check that they're sorted correctly
		Accessible pilotsTabPane = findAccessibleChildByIndex(pilotsTab, 0);
		Accessible scrollPane = findAccessibleChildByType(pilotsTabPane, JScrollPane.class);
		Accessible viewport = findAccessibleChildByType(scrollPane, JViewport.class);
		final AccessibleTable table = findAccessibleChildByType(viewport, JTable.class).getAccessibleContext().getAccessibleTable();

		callFromEventThread(new Callable<Void>() {
			public Void call() throws Exception {
				Assert.assertEquals(
						Messages.getString("pilot.race-number"), table.getAccessibleColumnHeader().getAccessibleAt(0, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals(19, table.getAccessibleRowCount());
				Assert.assertEquals("H77", table.getAccessibleAt(0, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("H84", table.getAccessibleAt(1, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("H91", table.getAccessibleAt(2, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("K01", table.getAccessibleAt(3, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("K02", table.getAccessibleAt(4, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("A01", table.getAccessibleAt(5, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("ELB01", table.getAccessibleAt(6, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("ELB02", table.getAccessibleAt(7, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("ELB03", table.getAccessibleAt(8, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("H04", table.getAccessibleAt(9, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("H69", table.getAccessibleAt(10, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("K03", table.getAccessibleAt(11, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("NL30", table.getAccessibleAt(12, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("NL50", table.getAccessibleAt(13, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("SL400", table.getAccessibleAt(14, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("SL401", table.getAccessibleAt(15, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("SL406", table.getAccessibleAt(16, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("TO26", table.getAccessibleAt(17, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$
				Assert.assertEquals("TO38", table.getAccessibleAt(18, 1).getAccessibleContext().getAccessibleName()); //$NON-NLS-1$

				return null;
			}
		});
	}
}