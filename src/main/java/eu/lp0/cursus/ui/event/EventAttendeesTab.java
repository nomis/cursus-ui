/*
	cursus - Race series management program
	Copyright 2012,2013  Simon Arlott

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
package eu.lp0.cursus.ui.event;

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
import com.google.common.base.Predicates;
import com.google.common.collect.Sets;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.EventDAO;
import eu.lp0.cursus.db.dao.PilotDAO;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Gender;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceNumber;
import eu.lp0.cursus.ui.Constants;
import eu.lp0.cursus.ui.component.AbstractDatabaseTab;
import eu.lp0.cursus.ui.component.BooleanDatabaseColumn;
import eu.lp0.cursus.ui.component.DatabaseColumn;
import eu.lp0.cursus.ui.component.DatabaseTableModel;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.component.EnumDatabaseColumn;
import eu.lp0.cursus.ui.component.StringDatabaseColumn;
import eu.lp0.cursus.ui.table.RaceNumbersDatabaseColumnModel;
import eu.lp0.cursus.util.Background;
import eu.lp0.cursus.util.DatabaseError;

public class EventAttendeesTab extends AbstractDatabaseTab<Event> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private JScrollPane scrollPane;
	private JTable table;
	private DatabaseTableModel<Pilot> model;
	private Event currentEvent;

	private static final EventDAO eventDAO = new EventDAO();
	private static final PilotDAO pilotDAO = new PilotDAO();

	public EventAttendeesTab(DatabaseWindow win) {
		super(Event.class, win, "tab.pilots"); //$NON-NLS-1$
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
				new BooleanDatabaseColumn<Pilot>("pilot.event-attendee", win, pilotDAO) { //$NON-NLS-1$
					@Override
					protected Boolean getValue(Pilot row, boolean editing) {
						assert (currentEvent != null);
						return row.getEvents().contains(currentEvent);
					}

					@Override
					protected boolean setValue(Pilot row, Boolean value) {
						assert (currentEvent != null);
						if (value) {
							row.getEvents().add(currentEvent);
						} else {
							row.getEvents().remove(currentEvent);
						}
						return true;
					}
				}, new StringDatabaseColumn<Pilot>("pilot.race-count") { //$NON-NLS-1$
					@Override
					protected String getValue(Pilot row, boolean editing) {
						return String.valueOf(Sets.filter(row.getRaces().keySet(), Predicates.in(currentEvent.getRaces())).size());
					}

					@Override
					protected boolean setValue(Pilot row, String value) {
						return false;
					}
				}, new RaceNumbersDatabaseColumnModel("pilot.race-number"), //$NON-NLS-1$
				new StringDatabaseColumn<Pilot>("pilot.name") { //$NON-NLS-1$
					@Override
					protected String getValue(Pilot row, boolean editing) {
						return row.getName();
					}

					@Override
					protected boolean setValue(Pilot row, String value) {
						return false;
					}
				}, new EnumDatabaseColumn<Pilot, Gender>("pilot.gender", Gender.class, true) { //$NON-NLS-1$
					@Override
					protected Gender getEnumValue(Pilot row) {
						return row.getGender();
					}

					@Override
					protected boolean setEnumValue(Pilot row, Gender value) {
						return false;
					}
				}, new StringDatabaseColumn<Pilot>("pilot.country") { //$NON-NLS-1$
					@Override
					protected String getValue(Pilot row, boolean editing) {
						return row.getCountry();
					}

					@Override
					protected boolean setValue(Pilot row, String value) {
						return false;
					}
				}));
		model.setupModel(table);
		table.getRowSorter().setSortKeys(
				Arrays.asList(new RowSorter.SortKey(0, SortOrder.DESCENDING), new RowSorter.SortKey(1, SortOrder.ASCENDING), new RowSorter.SortKey(2,
						SortOrder.ASCENDING), new RowSorter.SortKey(3, SortOrder.ASCENDING), new RowSorter.SortKey(4, SortOrder.ASCENDING),
						new RowSorter.SortKey(5, SortOrder.ASCENDING)));

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
			applyRows(rows, action);

			// It's very important for resorting that no other rows
			// have been modified when firing individual updates
			for (int i = 0; i < selected.length; i++) {
				applyAttendee(rows.get(i), currentEvent, action);
				model.fireTableCellUpdated(selected[i], 0);
			}
		}
	}

	private void applyAttendee(Pilot pilot, Event event, boolean action) {
		if (action) {
			pilot.getEvents().add(event);
		} else {
			pilot.getEvents().remove(event);
		}
	}

	private void applyRows(List<Pilot> rows, boolean action) {
		assert (SwingUtilities.isEventDispatchThread());

		Pilot item = null;
		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			for (Pilot row : rows) {
				item = row;

				row = pilotDAO.get(row);
				applyAttendee(row, currentEvent, action);
				pilotDAO.persist(row);
			}

			DatabaseSession.commit();
		} catch (PersistenceException e) {
			if (item != null) {
				log.error(String.format("Unable to update row: row=%s#%d", Pilot.class.getSimpleName(), item.getId()), e); //$NON-NLS-1$
			}
			win.reloadCurrentTabs();
			DatabaseError.errorSaving(win.getFrame(), Constants.APP_NAME, e);
		} finally {
			win.getDatabase().endSession();
		}
	}

	@Override
	@SuppressWarnings("unused")
	public void tabRefresh(Event event) {
		assert (Background.isExecutorThread());

		final Event newEvent;
		final List<Pilot> newPilots;

		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			newEvent = eventDAO.get(event);
			for (Race race : newEvent.getRaces()) {
				;
			}
			newPilots = new ArrayList<Pilot>(newEvent.getSeries().getPilots());
			for (Pilot pilot : newPilots) {
				for (RaceNumber raceNumber : pilot.getRaceNumbers()) {
					;
				}
				for (Event event_ : pilot.getEvents()) {
					;
				}
				for (Race race : pilot.getRaces().keySet()) {
					;
				}
			}
			DatabaseSession.commit();

			eventDAO.detach(event);
		} finally {
			win.getDatabase().endSession();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (!Objects.equal(currentEvent, newEvent)) {
					model.updateModel(Collections.<Pilot>emptyList());
				}
				currentEvent = newEvent;
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
				currentEvent = null;
			}
		});
	}
}