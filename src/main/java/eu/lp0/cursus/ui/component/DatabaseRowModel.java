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

import java.awt.Frame;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import eu.lp0.cursus.db.dao.AbstractDAO;
import eu.lp0.cursus.db.data.AbstractEntity;

public class DatabaseRowModel<T extends AbstractEntity, O extends Frame & DatabaseWindow> {
	private final List<DatabaseColumnModel<T, ?>> columns = new ArrayList<DatabaseColumnModel<T, ?>>();

	// TODO remove this as the race number editing is a mess
	public DatabaseRowModel(O win, Class<T> clazz, AbstractDAO<T> dao) {
		SortedMap<Integer, DatabaseColumnModel<T, ?>> columnModels = new TreeMap<Integer, DatabaseColumnModel<T, ?>>();
		for (Method m : clazz.getMethods()) {
			TableModelColumn a = m.getAnnotation(TableModelColumn.class);
			if (a != null) {
				columnModels.put(a.index(), new ReflectionDatabaseColumnModel<T, O>(win, clazz, dao, m, a));
			}
		}
		columns.addAll(columnModels.values());
	}

	public DatabaseRowModel(List<DatabaseColumnModel<T, ?>> columns) {
		columns.addAll(columns);
	}

	public String getColumnName(int mCol) {
		return columns.get(mCol).getColumnName();
	}

	public int getColumnCount() {
		return columns.size();
	}

	public void setupEditableModel(JTable table) {
		Enumeration<TableColumn> cols = table.getColumnModel().getColumns();
		while (cols.hasMoreElements()) {
			TableColumn col = cols.nextElement();
			columns.get(col.getModelIndex()).setupEditableModel(col);
		}
	}

	public boolean isCellEditable(int mCol) {
		return columns.get(mCol).isCellEditable();
	}
}