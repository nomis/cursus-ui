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

import java.util.Enumeration;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import eu.lp0.cursus.db.data.AbstractEntity;

public class DatabaseRowModel<T extends AbstractEntity> {
	private final List<DatabaseColumnModel<T, ?, ?>> columns;

	public DatabaseRowModel(List<DatabaseColumnModel<T, ?, ?>> columns) {
		this.columns = columns;
	}

	public String getColumnName(int mCol) {
		return columns.get(mCol).getColumnName();
	}

	public int getColumnCount() {
		return columns.size();
	}

	public void setupModel(JTable table) {
		Enumeration<TableColumn> cols = table.getColumnModel().getColumns();
		while (cols.hasMoreElements()) {
			TableColumn col = cols.nextElement();
			columns.get(col.getModelIndex()).setupModel(col);
		}
	}

	public boolean isCellEditable(int mCol) {
		return columns.get(mCol).isCellEditable();
	}
}