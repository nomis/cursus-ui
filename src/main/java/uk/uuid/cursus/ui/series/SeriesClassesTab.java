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
package uk.uuid.cursus.ui.series;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.uuid.cursus.ui.Constants;
import uk.uuid.cursus.ui.component.AbstractDatabaseTab;
import uk.uuid.cursus.ui.component.BooleanDatabaseColumn;
import uk.uuid.cursus.ui.component.DatabaseColumn;
import uk.uuid.cursus.ui.component.DatabaseTableModel;
import uk.uuid.cursus.ui.component.DatabaseWindow;
import uk.uuid.cursus.ui.component.EnumDatabaseColumn;
import uk.uuid.cursus.ui.component.StringDatabaseColumn;
import uk.uuid.cursus.ui.table.RaceNumbersDatabaseColumnModel;
import uk.uuid.cursus.ui.tree.ClassTree;
import uk.uuid.cursus.util.Background;
import uk.uuid.cursus.util.DatabaseError;

import com.google.common.collect.Ordering;

import uk.uuid.cursus.db.DatabaseSession;
import uk.uuid.cursus.db.dao.PilotDAO;
import uk.uuid.cursus.db.dao.SeriesDAO;
import uk.uuid.cursus.db.data.Class;
import uk.uuid.cursus.db.data.Sex;
import uk.uuid.cursus.db.data.Pilot;
import uk.uuid.cursus.db.data.RaceNumber;
import uk.uuid.cursus.db.data.Series;

public class SeriesClassesTab extends AbstractDatabaseTab<Series> implements TreeSelectionListener {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private JSplitPane splitPane;
	private JScrollPane leftScrollPane;
	private ClassTree list;
	private JScrollPane rightScrollPane;
	private JTable table;
	private DatabaseTableModel<Pilot> model;

	private static final SeriesDAO seriesDAO = new SeriesDAO();
	private static final PilotDAO pilotDAO = new PilotDAO();

	public SeriesClassesTab(DatabaseWindow win) {
		super(Series.class, win, "tab.classes"); //$NON-NLS-1$
		initialise();
	}

	@SuppressWarnings("unchecked")
	private void initialise() {
		setLayout(new BorderLayout(0, 0));

		splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);

		leftScrollPane = new JScrollPane();
		leftScrollPane.setMinimumSize(new Dimension(50, 0));
		leftScrollPane.setPreferredSize(new Dimension(150, 0));
		splitPane.setLeftComponent(leftScrollPane);

		list = new ClassTree(win, this);
		list.setBorder(new EmptyBorder(2, 2, 2, 2));
		list.setShowsRootHandles(false);
		list.addTreeSelectionListener(this);
		leftScrollPane.setViewportView(list);
		leftScrollPane.setBorder(null);

		rightScrollPane = new JScrollPane();
		rightScrollPane.setMinimumSize(new Dimension(50, 0));
		rightScrollPane.setBorder(new EmptyBorder(0, 2, 0, 0));
		splitPane.setRightComponent(rightScrollPane);

		table = new JTable();
		rightScrollPane.setViewportView(table);

		model = new DatabaseTableModel<Pilot>(Arrays.<DatabaseColumn<Pilot, ?>>asList( //
				new BooleanDatabaseColumn<Pilot>(null, win, pilotDAO) {
					@Override
					protected Boolean getValue(Pilot row, boolean editing) {
						return row.getClasses().contains(list.getSelected());
					}

					@Override
					protected boolean setValue(Pilot row, Boolean value) {
						Class clazz = list.getSelected();
						if (value) {
							row.getClasses().add(clazz);
						} else {
							row.getClasses().remove(clazz);
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
		table.setEnabled(false);
	}

	@Override
	public void valueChanged(final TreeSelectionEvent tse) {
		if (tse.isAddedPath()) {
			model.reloadModel();
			table.setEnabled(true);
		} else {
			table.setEnabled(false);
			table.clearSelection();
			model.reloadModel();
		}
	}

	private void addSelectedPilots() {
		applySelectedPilots(true);
	}

	private void removeSelectedPilots() {
		applySelectedPilots(false);
	}

	private void applySelectedPilots(boolean action) {
		Class cls = list.getSelected();
		if (cls == null) {
			return;
		}

		int[] selected = table.getSelectedRows();
		for (int i = 0; i < selected.length; i++) {
			selected[i] = table.convertRowIndexToModel(selected[i]);
		}

		List<Pilot> rows = new ArrayList<Pilot>(selected.length);
		for (int mRow : selected) {
			rows.add(model.getValueAt(mRow));
		}

		if (!rows.isEmpty()) {
			applyRows(rows, cls, action);

			// It's very important for resorting that no other rows
			// have been modified when firing individual updates
			for (int i = 0; i < selected.length; i++) {
				applyClasses(rows.get(i), cls, action);
				model.fireTableCellUpdated(selected[i], 0);
			}
		}
	}

	private void applyClasses(Pilot pilot, Class cls, boolean action) {
		if (action) {
			pilot.getClasses().add(cls);
		} else {
			pilot.getClasses().remove(cls);
		}
	}

	private void applyRows(List<Pilot> rows, Class cls, boolean action) {
		assert (SwingUtilities.isEventDispatchThread());

		Pilot item = null;
		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			for (Pilot row : rows) {
				item = row;

				row = pilotDAO.get(row);
				applyClasses(row, cls, action);
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
	public void tabRefresh(Series series) {
		assert (Background.isExecutorThread());

		final Series newSeries;
		final List<Class> newClasses;
		final List<Pilot> newPilots;

		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			newSeries = seriesDAO.get(series);
			newClasses = Ordering.natural().immutableSortedCopy(newSeries.getClasses());
			newPilots = new ArrayList<Pilot>(newSeries.getPilots());
			for (Pilot pilot : newPilots) {
				for (RaceNumber raceNumber : pilot.getRaceNumbers()) {
					;
				}
				for (Class cls : pilot.getClasses()) {
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
				model.updateModel(newPilots);
				list.updateModel(newSeries, newClasses);
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
				// This will de-select the current class and fire valueChanged to reload the pilots model too
				list.updateModel(null, null);
			}
		});
	}
}