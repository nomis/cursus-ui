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
package eu.lp0.cursus.ui.series;

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

import com.google.common.base.Preconditions;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.PilotDAO;
import eu.lp0.cursus.db.dao.SeriesDAO;
import eu.lp0.cursus.db.data.Gender;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.RaceNumber;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.i18n.Messages;
import eu.lp0.cursus.ui.component.AbstractDatabaseTab;
import eu.lp0.cursus.ui.component.DatabaseColumn;
import eu.lp0.cursus.ui.component.DatabaseTableModel;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.component.DeleteDatabaseColumn;
import eu.lp0.cursus.ui.component.EnumDatabaseColumn;
import eu.lp0.cursus.ui.component.StringDatabaseColumn;
import eu.lp0.cursus.ui.table.RaceNumbersDatabaseColumnModel;
import eu.lp0.cursus.util.Background;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.util.DatabaseError;

public class SeriesPilotsTab extends AbstractDatabaseTab<Series> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private JScrollPane scrollPane;
	private JTable table;
	private DatabaseTableModel<Pilot> model;
	private Series currentSeries = null;

	private static final SeriesDAO seriesDAO = new SeriesDAO();
	private static final PilotDAO pilotDAO = new PilotDAO();

	public SeriesPilotsTab(DatabaseWindow win) {
		super(Series.class, win, "tab.pilots"); //$NON-NLS-1$
		initialise();
	}

	@SuppressWarnings("unchecked")
	private void initialise() {
		setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		final DeleteDatabaseColumn<Pilot> delColumn;
		model = new DatabaseTableModel<Pilot>(Arrays.<DatabaseColumn<Pilot, ?>>asList( //
				new RaceNumbersDatabaseColumnModel("pilot.race-number", win), //$NON-NLS-1$
				new StringDatabaseColumn<Pilot>("pilot.name", win, pilotDAO, Constants.MAX_STRING_LEN) { //$NON-NLS-1$
					@Override
					protected String getValue(Pilot row, boolean editing) {
						return row.getName();
					}

					@Override
					protected boolean setValue(Pilot row, String value) {
						row.setName(value);
						return true;
					}
				}, new EnumDatabaseColumn<Pilot, Gender>("pilot.gender", win, pilotDAO, Gender.class, true) { //$NON-NLS-1$
					@Override
					protected Gender getEnumValue(Pilot row) {
						return row.getGender();
					}

					@Override
					protected boolean setEnumValue(Pilot row, Gender value) {
						row.setGender(value);
						return true;
					}
				}, new StringDatabaseColumn<Pilot>("pilot.country", win, pilotDAO, Constants.MAX_STRING_LEN) { //$NON-NLS-1$
					@Override
					protected String getValue(Pilot row, boolean editing) {
						return row.getCountry();
					}

					@Override
					protected boolean setValue(Pilot row, String value) {
						row.setCountry(value);
						return true;
					}
				}, delColumn = new DeleteDatabaseColumn<Pilot>(win, pilotDAO, "menu.pilot.delete") { //$NON-NLS-1$
					@Override
					protected String getValue(Pilot row, boolean editing) {
						return row.getName();
					}

					@Override
					protected Pilot newRow() {
						return new Pilot(currentSeries, ""); //$NON-NLS-1$
					}
				}));
		model.setupModel(table);
		table.getRowSorter().setSortKeys(
				Arrays.asList(new RowSorter.SortKey(0, SortOrder.ASCENDING), new RowSorter.SortKey(1, SortOrder.ASCENDING), new RowSorter.SortKey(2,
						SortOrder.ASCENDING), new RowSorter.SortKey(3, SortOrder.ASCENDING)));

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.isConsumed()) {
					return;
				}
				if (ke.getKeyCode() == KeyEvent.VK_INSERT) {
					delColumn.addRow();
					ke.consume();
				} else if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
					doDelete();
					ke.consume();
				}
			}
		});
	}

	private void doDelete() {
		int[] selected = table.getSelectedRows();
		for (int i = 0; i < selected.length; i++) {
			selected[i] = table.convertRowIndexToModel(selected[i]);
		}

		List<Pilot> rows = new ArrayList<Pilot>(selected.length);
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

	private boolean confirmDelete(List<Pilot> rows) {
		Preconditions.checkArgument(!rows.isEmpty());
		String action;
		String title;
		if (rows.size() == 1) {
			String value = rows.get(0).getName();
			action = String.format(Messages.getString("menu.pilot.delete.confirm"), value); //$NON-NLS-1$
			title = Messages.getString("menu.pilot.delete") + Constants.EN_DASH + value; //$NON-NLS-1$
		} else {
			action = String.format(Messages.getString("menu.pilot.delete-multiple.confirm"), rows.size()); //$NON-NLS-1$
			title = Messages.getString("menu.pilot.delete-multiple"); //$NON-NLS-1$
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

	private void deleteRows(List<Pilot> rows) {
		assert (SwingUtilities.isEventDispatchThread());

		Pilot item = null;
		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			for (Pilot row : rows) {
				item = row;
				row = pilotDAO.get(row);
				pilotDAO.remove(row);
			}

			DatabaseSession.commit();
		} catch (PersistenceException e) {
			if (item != null) {
				log.error(String.format("Unable to delete row: row=%s#%d", Pilot.class.getSimpleName(), item.getId()), e); //$NON-NLS-1$
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
	public void tabRefresh(Series series) {
		assert (Background.isExecutorThread());

		final Series newSeries;
		final List<Pilot> newPilots;

		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			newSeries = seriesDAO.get(series);
			newPilots = new ArrayList<Pilot>(newSeries.getPilots());
			for (Pilot pilot : newPilots) {
				for (RaceNumber raceNumber : pilot.getRaceNumbers()) {
					;
				}
			}
			DatabaseSession.commit();

			seriesDAO.detach(newSeries);
		} finally {
			win.getDatabase().endSession();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				currentSeries = newSeries;
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
				currentSeries = null;
				model.updateModel(Collections.<Pilot>emptyList());
			}
		});
	}
}