/*
	cursus - Race series management program
	Copyright 2011-2014  Simon Arlott

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
package eu.lp0.cursus.ui.race;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.PilotDAO;
import eu.lp0.cursus.db.dao.RaceAttendeeDAO;
import eu.lp0.cursus.db.dao.RaceDAO;
import eu.lp0.cursus.db.data.Sex;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceAttendee;
import eu.lp0.cursus.db.data.RaceAttendee.Type;
import eu.lp0.cursus.db.data.RaceNumber;
import eu.lp0.cursus.ui.Constants;
import eu.lp0.cursus.ui.component.AbstractDatabaseTab;
import eu.lp0.cursus.ui.component.DatabaseColumn;
import eu.lp0.cursus.ui.component.DatabaseTableModel;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.component.EnumDatabaseColumn;
import eu.lp0.cursus.ui.component.StringDatabaseColumn;
import eu.lp0.cursus.ui.table.RaceNumbersDatabaseColumnModel;
import eu.lp0.cursus.util.Background;
import eu.lp0.cursus.util.DatabaseError;

public class RaceAttendeesTab extends AbstractDatabaseTab<Race> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private JScrollPane scrollPane;
	private JTable table;
	private DatabaseTableModel<Pilot> model;
	private Race currentRace;

	private static final RaceDAO raceDAO = new RaceDAO();
	private static final PilotDAO pilotDAO = new PilotDAO();
	private static final RaceAttendeeDAO raceAttendeeDAO = new RaceAttendeeDAO();

	public RaceAttendeesTab(DatabaseWindow win) {
		super(Race.class, win, "tab.pilots"); //$NON-NLS-1$
		initialise();
	}

	@SuppressWarnings("unchecked")
	private void initialise() {
		setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		model = new DatabaseTableModel<Pilot>(Arrays.<DatabaseColumn<Pilot, ?>>asList( //
				new EnumDatabaseColumn<Pilot, RaceAttendee.Type>("pilot.race-attendee", win, pilotDAO, RaceAttendee.Type.class, true) { //$NON-NLS-1$
					@Override
					protected Type getEnumValue(Pilot row) {
						assert (currentRace != null);
						RaceAttendee attendee = row.getRaces().get(currentRace);
						return attendee != null ? attendee.getType() : null;
					}

					@Override
					protected boolean setEnumValue(Pilot row, Type value) {
						assert (currentRace != null);
						RaceAttendee attendee = row.getRaces().get(currentRace);
						if (attendee == null) {
							if (value != null) {
								attendee = new RaceAttendee(currentRace, row, value);
								row.getRaces().put(currentRace, attendee);
							}
						} else {
							if (value != null) {
								attendee.setType(value);
							} else {
								row.getRaces().remove(currentRace);
							}
						}
						return true;
					}
				}, new RaceNumbersDatabaseColumnModel("pilot.race-number"), //$NON-NLS-1$
				new StringDatabaseColumn<Pilot>("pilot.name") { //$NON-NLS-1$
					@Override
					protected String getValue(Pilot row, boolean editing) {
						return row.getName();
					}

					@Override
					protected boolean setValue(Pilot row, String value) {
						row.setName(value);
						return true;
					}
				}, new EnumDatabaseColumn<Pilot, Sex>("pilot.sex", Sex.class, true) { //$NON-NLS-1$
					@Override
					protected Sex getEnumValue(Pilot row) {
						return row.getSex();
					}

					@Override
					protected boolean setEnumValue(Pilot row, Sex value) {
						row.setSex(value);
						return true;
					}
				}, new StringDatabaseColumn<Pilot>("pilot.country") { //$NON-NLS-1$
					@Override
					protected String getValue(Pilot row, boolean editing) {
						return row.getCountry();
					}

					@Override
					protected boolean setValue(Pilot row, String value) {
						row.setCountry(value);
						return true;
					}
				}));
		model.setupModel(table);
		table.getRowSorter().setSortKeys(
				Arrays.asList(new RowSorter.SortKey(0, SortOrder.DESCENDING), new RowSorter.SortKey(1, SortOrder.ASCENDING), new RowSorter.SortKey(2,
						SortOrder.ASCENDING), new RowSorter.SortKey(3, SortOrder.ASCENDING), new RowSorter.SortKey(4, SortOrder.ASCENDING)));

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.isConsumed()) {
					return;
				}
				if (ke.getKeyCode() == KeyEvent.VK_INSERT) {
					addSelectedPilots();
					ke.consume();
				} else if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
					removeSelectedPilots();
					ke.consume();
				}
			}
		});
	}

	private void addSelectedPilots() {
		applySelectedPilots(true);
	}

	private void removeSelectedPilots() {
		applySelectedPilots(false);
	}

	private void applySelectedPilots(boolean action) {
		int[] selected = table.getSelectedRows();
		for (int i = 0; i < selected.length; i++) {
			selected[i] = table.convertRowIndexToModel(selected[i]);
		}

		List<Pilot> rows = new ArrayList<Pilot>(selected.length);
		for (int mRow : selected) {
			rows.add(model.getValueAt(mRow));
		}

		if (!rows.isEmpty()) {
			List<RaceAttendee> attendees = applyRows(rows, action);

			// It's very important for resorting that no other rows
			// have been modified when firing individual updates
			for (int i = 0; i < selected.length; i++) {
				// Provide the (new) detached RaceAttendees for the model to use
				applyAttendee(rows.get(i), currentRace, attendees.get(i), action);
				model.fireTableCellUpdated(selected[i], 0);
			}
		}
	}

	/**
	 * This is called to update the Pilot in the database as well as the Pilot in the model
	 * 
	 * To keep things consistent when a new RaceAttendee is created in the database it
	 * must be used for the model Pilot too, so this is returned on creation
	 */
	private RaceAttendee applyAttendee(Pilot pilot, Race race, RaceAttendee newAttendee, boolean action) {
		if (action) {
			RaceAttendee attendee = pilot.getRaces().get(race);
			if (attendee == null) {
				attendee = newAttendee;
				if (attendee == null) {
					attendee = new RaceAttendee(race, pilot, RaceAttendee.Type.PILOT);
				}
				pilot.getRaces().put(race, attendee);
			} else {
				attendee.setType(RaceAttendee.Type.PILOT);
			}
			return attendee;
		} else {
			pilot.getRaces().remove(race);
			return null;
		}
	}

	/**
	 * Apply updates to database Pilots returning any new RaceAttendees (detached)
	 */
	private List<RaceAttendee> applyRows(List<Pilot> rows, boolean action) {
		assert (SwingUtilities.isEventDispatchThread());

		List<RaceAttendee> attendees = new ArrayList<RaceAttendee>(rows.size());
		Pilot item = null;
		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			for (Pilot row : rows) {
				item = row;

				row = pilotDAO.get(row);
				attendees.add(applyAttendee(row, currentRace, null, action));
				pilotDAO.persist(row);
			}

			DatabaseSession.commit();

			for (RaceAttendee attendee : attendees) {
				if (attendee != null) {
					raceAttendeeDAO.detach(attendee);
				}
			}
		} catch (PersistenceException e) {
			if (item != null) {
				log.error(String.format("Unable to update row: row=%s#%d", Pilot.class.getSimpleName(), item.getId()), e); //$NON-NLS-1$
			}
			win.reloadCurrentTabs();
			DatabaseError.errorSaving(win.getFrame(), Constants.APP_NAME, e);
		} finally {
			win.getDatabase().endSession();
		}

		return attendees;
	}

	@Override
	@SuppressWarnings("unused")
	public void tabRefresh(Race race) {
		assert (Background.isExecutorThread());

		final Race newRace;
		final List<Pilot> newPilots;

		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			newRace = raceDAO.get(race);
			newPilots = new ArrayList<Pilot>(newRace.getEvent().getSeries().getPilots());
			for (Pilot pilot : newPilots) {
				for (RaceNumber raceNumber : pilot.getRaceNumbers()) {
					;
				}
				for (RaceAttendee raceAttendee : pilot.getRaces().values()) {
					;
				}
			}
			DatabaseSession.commit();

			raceDAO.detach(race);
		} finally {
			win.getDatabase().endSession();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (!Objects.equal(currentRace, newRace)) {
					model.updateModel(Collections.<Pilot>emptyList());
				}
				currentRace = newRace;
				model.updateModel(newPilots);
			}
		});
	}

	@Override
	public void tabClear() {
		assert (Background.isExecutorThread());

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				model.updateModel(Collections.<Pilot>emptyList());
				currentRace = null;
			}
		});
	}
}