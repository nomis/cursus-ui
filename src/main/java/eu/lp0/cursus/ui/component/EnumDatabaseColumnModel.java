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
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.table.TableCellEditor;

import eu.lp0.cursus.db.dao.AbstractDAO;
import eu.lp0.cursus.db.data.AbstractEntity;

public abstract class EnumDatabaseColumnModel<T extends AbstractEntity, V extends Enum<?>, O extends Frame & DatabaseWindow> extends
		DatabaseColumnModel<T, Object, O> {
	private final Class<V> type;
	private final boolean nullable;

	public EnumDatabaseColumnModel(String name, Class<V> type, boolean nullable) {
		super(name);
		this.type = type;
		this.nullable = nullable;
	}

	public EnumDatabaseColumnModel(String name, O win, AbstractDAO<T> dao, Class<V> type, boolean nullable) {
		super(name, win, dao);
		this.type = type;
		this.nullable = nullable;
	}

	@Override
	protected TableCellEditor createCellEditor() {
		Vector<Object> values = new Vector<Object>();
		if (nullable) {
			values.add(""); //$NON-NLS-1$
		}
		values.addAll(Arrays.asList(type.getEnumConstants()));
		return new DatabaseTableCellEditor<T, Object>(this, new JComboBox(values));
	}

	@Override
	protected final Object getValue(T row) {
		Object value = getEnumValue(row);
		if (value == null) {
			assert (nullable);
			value = ""; //$NON-NLS-1$
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected final boolean setValue(T row, Object value) {
		if (value.equals("")) { //$NON-NLS-1$
			assert (nullable);
			value = null;
		}
		return setEnumValue(row, (V)value);
	}

	protected abstract V getEnumValue(T row);

	protected abstract boolean setEnumValue(T row, V value);
}