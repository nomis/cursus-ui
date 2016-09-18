/*
	cursus - Race series management program
	Copyright 2011, 2014  Simon Arlott

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
package uk.uuid.lp0.cursus.ui.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.RowSorter.SortKey;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import uk.uuid.lp0.cursus.i18n.LanguageManager;
import uk.uuid.lp0.cursus.i18n.LocaleChangeEvent;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import uk.uuid.cursus.db.data.Entity;

public class DatabaseTableModel<T extends Entity> extends AbstractTableModel implements Iterable<T> {
	private final List<DatabaseColumn<T, ?>> columns;
	private final ArrayList<T> rows = new ArrayList<T>();
	private final TableRowSorter<? super TableModel> sorter = new TableRowSorter<TableModel>(getRealModel());;
	private final EventBus eventBus = new EventBus(getClass().getSimpleName());

	public DatabaseTableModel(List<DatabaseColumn<T, ?>> columns) {
		this.columns = columns;
		sorter.setSortsOnUpdates(true);
		for (DatabaseColumn<T, ?> col : columns) {
			eventBus.register(col);
		}
		LanguageManager.register(this, false);
	}

	@Subscribe
	public final void updateLanguage(LocaleChangeEvent lce) {
		assert (SwingUtilities.isEventDispatchThread());

		List<? extends SortKey> sortKeys = ImmutableList.copyOf(sorter.getSortKeys()); // Copy current sort keys
		eventBus.post(lce); // Update enum lists
		fireTableDataChanged(); // Force cancel editing
		fireTableStructureChanged(); // Reload column names
		sorter.setSortKeys(sortKeys); // The sort keys will be reset when the structure changes
	}

	@Override
	public String getColumnName(int mCol) {
		return columns.get(mCol).getName();
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public int getColumnCount() {
		return columns.size();
	}

	public T getValueAt(int mRow) {
		return rows.get(mRow);
	}

	// Every cell value is the row entity object, which avoids
	// unnecessary intermediate representations but requires
	// the renderer and editor to obtain the column value from
	// the row object
	@Override
	public T getValueAt(int mRow, int mCol) {
		return rows.get(mRow);
	}

	@Override
	public void setValueAt(Object value, int mRow, int mCol) {
		// The update has already been committed as it had to be verified before allowing editing to stop
		fireTableCellUpdated(mRow, mCol);
	}

	@Override
	public boolean isCellEditable(int mRow, int mCol) {
		return columns.get(mCol).isCellEditable(rows.get(mRow));
	}

	public void setupModel(JTable table) {
		table.setAutoCreateColumnsFromModel(false);
		table.setModel(this);
		table.setColumnModel(new DefaultTableColumnModel());
		table.setRowSorter(sorter);
		table.setSurrendersFocusOnKeystroke(true);

		TableColumnModel cols = table.getColumnModel();
		int i = 0;
		for (DatabaseColumn<T, ?> col : columns) {
			col.setModelIndex(i++);
			cols.addColumn(col);
			col.setupModel(table, this, sorter);
		}

		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.doLayout();
	}

	private TableModel getRealModel() {
		// The sorter needs to get a real view of the data
		// otherwise it'll sort on the row object itself
		return new AbstractTableModel() {
			@Override
			public Object getValueAt(int mRow, int mCol) {
				return columns.get(mCol).getValue(rows.get(mRow), false);
			}

			@Override
			public int getRowCount() {
				return DatabaseTableModel.this.getRowCount();
			}

			@Override
			public int getColumnCount() {
				return DatabaseTableModel.this.getColumnCount();
			}
		};
	}

	public void updateModel(List<T> newRows) {
		for (int idxOld = 0; idxOld < rows.size(); idxOld++) {
			int idxNew = newRows.indexOf(rows.get(idxOld));
			if (idxNew != -1) {
				rows.set(idxOld, newRows.get(idxNew));
				fireTableRowsUpdated(idxOld, idxOld);
				newRows.remove(idxNew);
			} else {
				rows.remove(idxOld);
				fireTableRowsDeleted(idxOld, idxOld);
				idxOld--;
			}
		}

		for (T newRow : newRows) {
			int idxNew = rows.size();
			rows.add(newRow);
			fireTableRowsInserted(idxNew, idxNew);
		}

		rows.trimToSize();
	}

	public void reloadModel() {
		if (!rows.isEmpty()) {
			fireTableRowsUpdated(0, rows.size() - 1);
		}
	}

	@Override
	public Iterator<T> iterator() {
		return Iterators.unmodifiableIterator(rows.iterator());
	}

	protected void addRow(T row) {
		int idxNew = rows.size();
		rows.add(row);
		fireTableRowsInserted(idxNew, idxNew);
	}

	public void deleteRow(int mRow) {
		rows.remove(mRow);
		fireTableRowsDeleted(mRow, mRow);
	}
}