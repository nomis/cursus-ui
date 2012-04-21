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

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.accessibility.Accessible;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Ordering;

import eu.lp0.cursus.db.Database;
import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.InvalidDatabaseException;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.i18n.Messages;
import eu.lp0.cursus.ui.actions.MoveRaceDownAction;
import eu.lp0.cursus.ui.actions.MoveRaceUpAction;
import eu.lp0.cursus.ui.component.DatabaseWindow;

/**
 * Create 16 races over 4 events (with an extra event at the top and bottom)
 * 
 * Separately, try moving each race all the way:
 * # down, up, down, up
 * # up, down, up, down
 * and verify that the race tree matches the database.
 */
public class MoveRaceEntityTests extends AbstractUITest {
	@Override
	protected Database createEmptyDatabase(Database db) throws InvalidDatabaseException, SQLException {
		db.startSession();
		try {
			DatabaseSession.begin();

			Series series1 = seriesDAO.find(Messages.getString(Database.UNTITLED_SERIES));
			series1.setName("Series 1"); //$NON-NLS-1$
			Event invEvent1 = series1.getEvents().get(0);
			invEvent1.setName("Invalid Event 1"); //$NON-NLS-1$
			Race race0 = invEvent1.getRaces().get(0);
			invEvent1.getRaces().remove(race0);
			raceDAO.remove(race0);
			seriesDAO.persist(series1);

			Series series2 = new Series("Series 2"); //$NON-NLS-1$

			Event event0 = new Event(series2, "Event 0"); //$NON-NLS-1$
			series2.getEvents().add(event0);
			Event event1 = new Event(series2, "Event 1"); //$NON-NLS-1$
			series2.getEvents().add(event1);
			event1.getRaces().add(new Race(event1, "Race 1")); //$NON-NLS-1$
			event1.getRaces().add(new Race(event1, "Race 2")); //$NON-NLS-1$
			event1.getRaces().add(new Race(event1, "Race 3")); //$NON-NLS-1$
			event1.getRaces().add(new Race(event1, "Race 4")); //$NON-NLS-1$
			Event event2 = new Event(series2, "Event 2"); //$NON-NLS-1$
			series2.getEvents().add(event2);
			event2.getRaces().add(new Race(event2, "Race 5")); //$NON-NLS-1$
			event2.getRaces().add(new Race(event2, "Race 6")); //$NON-NLS-1$
			event2.getRaces().add(new Race(event2, "Race 7")); //$NON-NLS-1$
			event2.getRaces().add(new Race(event2, "Race 8")); //$NON-NLS-1$
			Event event3 = new Event(series2, "Event 3"); //$NON-NLS-1$
			series2.getEvents().add(event3);
			event3.getRaces().add(new Race(event3, "Race 9")); //$NON-NLS-1$
			event3.getRaces().add(new Race(event3, "Race 10")); //$NON-NLS-1$
			event3.getRaces().add(new Race(event3, "Race 11")); //$NON-NLS-1$
			event3.getRaces().add(new Race(event3, "Race 12")); //$NON-NLS-1$
			Event event4 = new Event(series2, "Event 4"); //$NON-NLS-1$
			series2.getEvents().add(event4);
			event4.getRaces().add(new Race(event4, "Race 13")); //$NON-NLS-1$
			event4.getRaces().add(new Race(event4, "Race 14")); //$NON-NLS-1$
			event4.getRaces().add(new Race(event4, "Race 15")); //$NON-NLS-1$
			event4.getRaces().add(new Race(event4, "Race 16")); //$NON-NLS-1$
			Event event5 = new Event(series2, "Event 5"); //$NON-NLS-1$
			series2.getEvents().add(event5);
			seriesDAO.persist(series2);

			Series series3 = new Series("Series 3"); //$NON-NLS-1$
			Event invEvent2 = new Event(series3, "Invalid Event 2"); //$NON-NLS-1$
			series3.getEvents().add(invEvent2);
			seriesDAO.persist(series3);

			DatabaseSession.commit();
		} finally {
			db.endSession();
		}

		return db;
	}

	private void moveRace(String event, String race, boolean up) throws Exception {
		Accessible seriesNode = findAccessibleChildByName(raceTree, "Series 2"); //$NON-NLS-1$
		Accessible eventNode = findAccessibleChildByName(seriesNode, event);
		Accessible raceNode = findAccessibleChildByName(eventNode, race);
		accessibleSelect(raceNode, true);

		Race selectedRace = (Race)getSelectedRaceEntity();
		Assert.assertEquals(race, selectedRace.getName());
		Assert.assertEquals(event, selectedRace.getEvent().getName());

		assertInvariant("Pre"); //$NON-NLS-1$

		if (up) {
			doMoves("First move up", selectedRace, true); //$NON-NLS-1$
			doMoves("First move down", selectedRace, false); //$NON-NLS-1$
			doMoves("Second move up", selectedRace, true); //$NON-NLS-1$
			doMoves("Second move down", selectedRace, false); //$NON-NLS-1$
		} else {
			doMoves("First move down", selectedRace, false); //$NON-NLS-1$
			doMoves("First move up", selectedRace, true); //$NON-NLS-1$
			doMoves("Second move down", selectedRace, false); //$NON-NLS-1$
			doMoves("Second move up", selectedRace, true); //$NON-NLS-1$
		}

		assertInvariant("Post"); //$NON-NLS-1$
	}

	private void doMoves(String message, Race selectedRace, final boolean up) throws Exception {
		for (int i = 0; i < 20; i++) {
			final Race tmp = (Race)getSelectedRaceEntity();
			Assert.assertEquals(message + " " + (i + 1), selectedRace, tmp); //$NON-NLS-1$

			runFromEventThread(new Runnable() {
				@Override
				public void run() {
					if (up) {
						new MoveRaceUpAction(((DatabaseWindow)mainWindow), tmp).actionPerformed(null);
					} else {
						new MoveRaceDownAction(((DatabaseWindow)mainWindow), tmp).actionPerformed(null);
					}
				}
			});

			assertInvariant(message + " " + (i + 1)); //$NON-NLS-1$
		}
	}

	private void assertInvariant(final String message) throws Exception {
		syncOnDatabaseChange();

		callFromEventThread(new Callable<Void>() {
			public Void call() throws Exception {
				Database db = main.getDatabase();

				db.startSession();
				try {
					DatabaseSession.begin();

					List<Series> seriesList = Ordering.natural().sortedCopy(seriesDAO.findAll());
					// log.debug("Series: " + Arrays.toString(seriesList.toArray())); //$NON-NLS-1$
					for (int s = 0; s < seriesList.size(); s++) {
						Series series = seriesList.get(s);
						Accessible seriesNode = findAccessibleChildByIndex(raceTree, s);
						Assert.assertNotNull(message, seriesNode);
						Assert.assertEquals(message + " (at " + s + ")", series.getName(), seriesNode.getAccessibleContext().getAccessibleName()); //$NON-NLS-1$ //$NON-NLS-2$

						// log.debug("Event: " + series + " " + Arrays.toString(series.getEvents().toArray())); //$NON-NLS-1$ //$NON-NLS-2$
						for (int e = 0; e < series.getEvents().size(); e++) {
							Event event = series.getEvents().get(e);
							Accessible eventNode = findAccessibleChildByIndex(seriesNode, e);
							Assert.assertNotNull(message, eventNode);
							Assert.assertEquals(message + " (at " + e + ")", event.getName(), eventNode.getAccessibleContext().getAccessibleName()); //$NON-NLS-1$ //$NON-NLS-2$

							// log.debug("Race: " + event + " " + Arrays.toString(event.getRaces().toArray())); //$NON-NLS-1$ //$NON-NLS-2$
							for (int r = 0; r < event.getRaces().size(); r++) {
								Race race = event.getRaces().get(r);
								Accessible raceNode = findAccessibleChildByIndex(eventNode, r);
								Assert.assertNotNull(message, raceNode);
								Assert.assertEquals(message + " (at " + r + ")", race.getName(), raceNode.getAccessibleContext().getAccessibleName()); //$NON-NLS-1$ //$NON-NLS-2$
							}
						}
					}

					DatabaseSession.commit();
				} finally {
					db.endSession();
				}

				return null;
			}
		});
	}

	@Test
	public void moveRace1Up() throws Exception {
		moveRace("Event 1", "Race 1", true);  //$NON-NLS-1$//$NON-NLS-2$
	}

	@Test
	public void moveRace2Up() throws Exception {
		moveRace("Event 1", "Race 2", true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace3Up() throws Exception {
		moveRace("Event 1", "Race 3", true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace4Up() throws Exception {
		moveRace("Event 1", "Race 4", true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace5Up() throws Exception {
		moveRace("Event 2", "Race 5", true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace6Up() throws Exception {
		moveRace("Event 2", "Race 6", true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace7Up() throws Exception {
		moveRace("Event 2", "Race 7", true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace8Up() throws Exception {
		moveRace("Event 2", "Race 8", true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace9Up() throws Exception {
		moveRace("Event 3", "Race 9", true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace10Up() throws Exception {
		moveRace("Event 3", "Race 10", true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace11Up() throws Exception {
		moveRace("Event 3", "Race 11", true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace12Up() throws Exception {
		moveRace("Event 3", "Race 12", true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace13Up() throws Exception {
		moveRace("Event 4", "Race 13", true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace14Up() throws Exception {
		moveRace("Event 4", "Race 14", true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace15Up() throws Exception {
		moveRace("Event 4", "Race 15", true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace16Up() throws Exception {
		moveRace("Event 4", "Race 16", true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace1Down() throws Exception {
		moveRace("Event 1", "Race 1", false);  //$NON-NLS-1$//$NON-NLS-2$
	}

	@Test
	public void moveRace2Down() throws Exception {
		moveRace("Event 1", "Race 2", false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace3Down() throws Exception {
		moveRace("Event 1", "Race 3", false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace4Down() throws Exception {
		moveRace("Event 1", "Race 4", false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace5Down() throws Exception {
		moveRace("Event 2", "Race 5", false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace6Down() throws Exception {
		moveRace("Event 2", "Race 6", false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace7Down() throws Exception {
		moveRace("Event 2", "Race 7", false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace8Down() throws Exception {
		moveRace("Event 2", "Race 8", false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace9Down() throws Exception {
		moveRace("Event 3", "Race 9", false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace10Down() throws Exception {
		moveRace("Event 3", "Race 10", false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace11Down() throws Exception {
		moveRace("Event 3", "Race 11", false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace12Down() throws Exception {
		moveRace("Event 3", "Race 12", false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace13Down() throws Exception {
		moveRace("Event 4", "Race 13", false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace14Down() throws Exception {
		moveRace("Event 4", "Race 14", false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace15Down() throws Exception {
		moveRace("Event 4", "Race 15", false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Test
	public void moveRace16Down() throws Exception {
		moveRace("Event 4", "Race 16", false); //$NON-NLS-1$ //$NON-NLS-2$
	}
}