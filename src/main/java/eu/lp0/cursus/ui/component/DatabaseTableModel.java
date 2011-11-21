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
package eu.lp0.cursus.ui.component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import eu.lp0.cursus.db.data.AbstractEntity;

public class DatabaseTableModel<T extends AbstractEntity> extends AbstractTableModel {
	private final DatabaseRowModel<T> rowModel;
	private final ArrayList<T> rows = new ArrayList<T>();

	public DatabaseTableModel(DatabaseRowModel<T> rowModel) {
		this.rowModel = rowModel;
	}

	@Override
	public String getColumnName(int mCol) {
		return rowModel.getColumnName(mCol);
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public int getColumnCount() {
		return rowModel.getColumnCount();
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
	public boolean isCellEditable(int mRow, int mCol) {
		return rowModel.isCellEditable(mCol);
	}

	public void setupEditableModel(JTable table) {
		table.setModel(this);

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(this);
		sorter.setSortsOnUpdates(true);
		table.setRowSorter(sorter);

		rowModel.setupEditableModel(table);
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
}