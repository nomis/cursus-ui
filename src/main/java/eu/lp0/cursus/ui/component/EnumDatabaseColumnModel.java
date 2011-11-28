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

import java.util.Arrays;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.table.TableCellEditor;

import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;

import eu.lp0.cursus.db.dao.EntityDAO;
import eu.lp0.cursus.db.data.Entity;
import eu.lp0.cursus.i18n.LocaleChangeEvent;
import eu.lp0.cursus.i18n.TranslatedEnum;

public abstract class EnumDatabaseColumnModel<T extends Entity, V extends Enum<?>> extends DatabaseColumnModel<T, Object> {
	private final MutableListComboBoxModel<Object> values;
	private final Class<V> type;
	private final boolean nullable;

	public EnumDatabaseColumnModel(String name, Class<V> type, boolean nullable) {
		super(name);
		this.type = type;
		this.nullable = nullable;
		this.values = new MutableListComboBoxModel<Object>(generateValues());
	}

	public EnumDatabaseColumnModel(String name, DatabaseWindow win, EntityDAO<T> dao, Class<V> type, boolean nullable) {
		super(name, win, dao);
		this.type = type;
		this.nullable = nullable;
		this.values = new MutableListComboBoxModel<Object>(generateValues());
	}

	// Uses DatabaseTableModel's EventBus
	@Subscribe
	public void updateLocale(LocaleChangeEvent lce) {
		if (TranslatedEnum.class.isAssignableFrom(type)) {
			values.replaceAll(generateValues());
		}
	}

	@Override
	protected TableCellEditor createCellEditor() {
		return new DatabaseTableCellEditor<T, Object>(this, new JComboBox(values));
	}

	private List<Object> generateValues() {
		if (nullable) {
			return Lists.<Object>asList("", type.getEnumConstants()); //$NON-NLS-1$
		} else {
			return Arrays.asList((Object[])type.getEnumConstants());
		}
	}

	@Override
	protected final Object getValue(T row, boolean editing) {
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