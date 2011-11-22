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
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import eu.lp0.cursus.db.dao.AbstractDAO;
import eu.lp0.cursus.db.data.AbstractEntity;

// TODO refactor this as the race number editing is a mess
public class ReflectionDatabaseRowModel<T extends AbstractEntity, O extends Frame & DatabaseWindow> extends DatabaseRowModel<T> {
	public ReflectionDatabaseRowModel(O win, Class<T> clazz, AbstractDAO<T> dao) {
		super(generateColumnModels(win, clazz, dao));
	}

	private static <T extends AbstractEntity, O extends Frame & DatabaseWindow> List<DatabaseColumnModel<T, ?, ?>> generateColumnModels(O win, Class<T> clazz,
			AbstractDAO<T> dao) {
		SortedMap<Integer, DatabaseColumnModel<T, ?, ?>> columnModels = new TreeMap<Integer, DatabaseColumnModel<T, ?, ?>>();
		for (Method m : clazz.getMethods()) {
			TableModelColumn a = m.getAnnotation(TableModelColumn.class);
			if (a != null) {
				columnModels.put(a.index(), new ReflectionDatabaseColumnModel<T, O>(win, clazz, dao, m, a));
			}
		}
		return new ArrayList<DatabaseColumnModel<T, ?, ?>>(columnModels.values());
	}
}