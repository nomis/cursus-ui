/*
	cursus - Race series management program
	Copyright 2011-2013  Simon Arlott

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
package eu.lp0.cursus.ui.race;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.RaceDAO;
import eu.lp0.cursus.db.data.Penalty;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceAttendee;
import eu.lp0.cursus.db.data.RaceNumber;
import eu.lp0.cursus.i18n.Messages;
import eu.lp0.cursus.ui.Constants;
import eu.lp0.cursus.ui.component.AbstractDatabaseTab;
import eu.lp0.cursus.ui.component.DatabaseColumn;
import eu.lp0.cursus.ui.component.DatabaseTableModel;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.component.DeleteDatabaseColumn;
import eu.lp0.cursus.ui.component.EnumDatabaseColumn;
import eu.lp0.cursus.ui.component.StringDatabaseColumn;
import eu.lp0.cursus.ui.table.RaceAttendeePenalty;
import eu.lp0.cursus.ui.table.RaceAttendeePenaltyDAO;
import eu.lp0.cursus.ui.table.RaceAttendeesDatabaseColumn;
import eu.lp0.cursus.util.Background;
import eu.lp0.cursus.util.DatabaseError;

public class RacePenaltiesTab extends AbstractDatabaseTab<Race> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private JScrollPane scrollPane;
	private JTable table;
	private DatabaseTableModel<RaceAttendeePenalty> model;
	private RaceAttendeesDatabaseColumn raceAttendeesColumn;

	private Race currentRace;

	private static final RaceDAO raceDAO = new RaceDAO();
	private static final RaceAttendeePenaltyDAO raceAttendeePenaltyDAO = new RaceAttendeePenaltyDAO();

	public RacePenaltiesTab(DatabaseWindow win) {
		super(Race.class, win, "tab.penalties"); //$NON-NLS-1$
		initialise();
	}

	@SuppressWarnings("unchecked")
	private void initialise() {
		setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		final DeleteDatabaseColumn<RaceAttendeePenalty> delColumn;
		model = new DatabaseTableModel<RaceAttendeePenalty>(Arrays.<DatabaseColumn<RaceAttendeePenalty, ?>>asList( //
				raceAttendeesColumn = new RaceAttendeesDatabaseColumn("penalty.pilot", win, raceAttendeePenaltyDAO), //$NON-NLS-1$
				new EnumDatabaseColumn<RaceAttendeePenalty, Penalty.Type>("penalty.type", win, raceAttendeePenaltyDAO, Penalty.Type.class, false) { //$NON-NLS-1$
					@Override
					protected Penalty.Type getEnumValue(RaceAttendeePenalty row) {
						return row.getPenalty().getType();
					}

					@Override
					protected boolean setEnumValue(RaceAttendeePenalty row, Penalty.Type value) {
						row.getPenalty().setType(value);
						return true;
					}
				}, new StringDatabaseColumn<RaceAttendeePenalty>("penalty.value", win, raceAttendeePenaltyDAO, Constants.MAX_STRING_LEN) { //$NON-NLS-1$
					@Override
					protected String getValue(RaceAttendeePenalty row, boolean editing) {
						return String.valueOf(row.getPenalty().getValue()); // FIXME
					}

					@Override
					protected boolean setValue(RaceAttendeePenalty row, String value) {
						row.getPenalty().setValue(Integer.valueOf(value)); // FIXME
						return true;
					}
				}, new StringDatabaseColumn<RaceAttendeePenalty>("penalty.reason", win, raceAttendeePenaltyDAO, Constants.MAX_STRING_LEN) { //$NON-NLS-1$
					@Override
					protected String getValue(RaceAttendeePenalty row, boolean editing) {
						return row.getPenalty().getReason();
					}

					@Override
					protected boolean setValue(RaceAttendeePenalty row, String value) {
						row.getPenalty().setReason(value);
						return true;
					}
				}, delColumn = new DeleteDatabaseColumn<RaceAttendeePenalty>(win, raceAttendeePenaltyDAO, "menu.penalty.delete") { //$NON-NLS-1$
					@Override
					protected String getValue(RaceAttendeePenalty row, boolean editing) {
						return row.toString();
					}

					@Override
					protected RaceAttendeePenalty newRow() {
						if (table.isEnabled()) {
							assert (currentRace != null);
							return new RaceAttendeePenalty(currentRace);
						} else {
							JOptionPane.showMessageDialog(win.getFrame(), Messages.getString("menu.penalty.add.no-attendees"), //$NON-NLS-1$
									Messages.getString("menu.penalty.add"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
							return null;
						}
					}
				}));
		model.setupModel(table);

		table.getRowSorter().setSortKeys(
				Arrays.asList(new RowSorter.SortKey(0, SortOrder.ASCENDING), new RowSorter.SortKey(1, SortOrder.ASCENDING), new RowSorter.SortKey(2,
						SortOrder.ASCENDING), new RowSorter.SortKey(3, SortOrder.ASCENDING), new RowSorter.SortKey(4, SortOrder.ASCENDING)));

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.isConsumed()) {
					return;
				}
				if (ke.getKeyCode() == KeyEvent.VK_INSERT) {
					if (delColumn != null) {
						delColumn.addRow();
					}
					ke.consume();
				} else if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
					doDelete();
					ke.consume();
				}
			}
		});

		// table.setEnabled(false);
	}

	private void updateModel(Race race) {
		assert (SwingUtilities.isEventDispatchThread());

		// Remove everything when the race changes
		if (!Objects.equal(race, currentRace)) {
			model.updateModel(Collections.<RaceAttendeePenalty>emptyList());
			raceAttendeesColumn.setRace(null);
		}

		currentRace = race;
		raceAttendeesColumn.setRace(race);
		model.updateModel(generateRaceAttendeePenalties(race));

		// table.setEnabled(race != null && !race.getAttendees().isEmpty());
	}

	private List<RaceAttendeePenalty> generateRaceAttendeePenalties(Race race) {
		if (race == null) {
			return Collections.<RaceAttendeePenalty>emptyList();
		} else {
			List<RaceAttendeePenalty> penalties = new ArrayList<RaceAttendeePenalty>(race.getAttendees().size());
			// Add all penalties from the database
			for (RaceAttendee attendee : race.getAttendees().values()) {
				for (Penalty penalty : attendee.getPenalties()) {
					if (!EnumDatabaseColumn.isTransient(penalty.getType())) {
						penalties.add(new RaceAttendeePenalty(attendee, penalty));
					}
				}
			}
			// Add all unsaved penalties
			for (RaceAttendeePenalty penalty : model) {
				if (penalty.getDatabaseAttendee() == null && !EnumDatabaseColumn.isTransient(penalty.getPenalty().getType())) {
					// But only if there is no pilot set or the pilot is attending the race
					//
					// Note: it is not safe to modify the penalty here (to unset the pilot)
					// as it would require the model to fire an update... but the pilot
					// must be null otherwise the penalty should have been saved
					if (penalty.getPilot() == null || race.getAttendees().containsKey(penalty.getPilot())) {
						// Must refresh the race, as the attendees may have changed
						penalty.setRace(race);

						penalties.add(penalty);
					}
				}
			}
			return penalties;
		}
	}

	private void doDelete() {
		int[] selected = table.getSelectedRows();
		for (int i = 0; i < selected.length; i++) {
			selected[i] = table.convertRowIndexToModel(selected[i]);
		}

		List<RaceAttendeePenalty> rows = new ArrayList<RaceAttendeePenalty>(selected.length);
		for (int mRow : selected) {
			rows.add(model.getValueAt(mRow));
		}

		if (!rows.isEmpty() && confirmDelete(rows)) {
			deleteRows(rows);

			// Delete in reverse order as each row removed affects the indexes of the rows after it
			for (int mRow : Ordering.natural().reverse().sortedCopy(Ints.asList(selected))) {
				model.deleteRow(mRow);
			}
		}
	}

	private boolean confirmDelete(List<RaceAttendeePenalty> rows) {
		Preconditions.checkArgument(!rows.isEmpty());
		String action;
		String title;
		if (rows.size() == 1) {
			String value = rows.get(0).toString();
			action = Messages.getString("menu.penalty.delete.confirm", value); //$NON-NLS-1$
			title = Messages.getString("menu.penalty.delete") + Constants.EN_DASH + value; //$NON-NLS-1$
		} else {
			action = Messages.getString("menu.penalty.delete-multiple.confirm", rows.size()); //$NON-NLS-1$
			title = Messages.getString("menu.penalty.delete-multiple"); //$NON-NLS-1$
		}
		switch (JOptionPane.showConfirmDialog(win.getFrame(), action, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
		case JOptionPane.YES_OPTION:
			return true;
		case JOptionPane.NO_OPTION:
		case JOptionPane.CLOSED_OPTION:
		default:
			return false;
		}
	}

	private void deleteRows(List<RaceAttendeePenalty> rows) {
		assert (SwingUtilities.isEventDispatchThread());

		RaceAttendeePenalty item = null;
		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			for (RaceAttendeePenalty row : rows) {
				item = row;
				// no get() as this is a fake DAO
				raceAttendeePenaltyDAO.remove(row);
			}

			DatabaseSession.commit();
		} catch (PersistenceException e) {
			if (item != null) {
				log.error(String.format("Unable to delete row: row=%s#%d", RaceAttendeePenalty.class.getSimpleName(), item.getId()), e); //$NON-NLS-1$
			} else {
				log.error("Unable to delete row", e); //$NON-NLS-1$
			}
			win.reloadCurrentTabs();
			DatabaseError.errorSaving(win.getFrame(), Constants.APP_NAME, e);
		} finally {
			win.getDatabase().endSession();
		}
	}

	@Override
	@SuppressWarnings("unused")
	public void tabRefresh(Race race) {
		assert (Background.isExecutorThread());

		final Race newRace;

		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			newRace = raceDAO.get(race);
			for (RaceAttendee attendee : newRace.getAttendees().values()) {
				for (RaceNumber raceNumber : attendee.getPilot().getRaceNumbers()) {
					;
				}
				for (Penalty penalty : attendee.getPenalties()) {
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
				updateModel(newRace);
			}
		});
	}

	@Override
	public void tabClear() {
		assert (Background.isExecutorThread());

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				updateModel(null);
			}
		});
	}
}