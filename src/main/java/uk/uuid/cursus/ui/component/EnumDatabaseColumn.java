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
package uk.uuid.cursus.ui.component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.Transient;
import javax.swing.JComboBox;

import uk.uuid.cursus.i18n.LocaleChangeEvent;
import uk.uuid.cursus.i18n.Messages;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;

import uk.uuid.cursus.db.dao.EntityDAO;
import uk.uuid.cursus.db.data.Entity;
import uk.uuid.cursus.i18n.TranslatedEnum;

public abstract class EnumDatabaseColumn<T extends Entity, V extends Enum<?>> extends DatabaseColumn<T, Object> {
	private final MutableListComboBoxModel<Object> values;
	private final Class<V> type;
	private final boolean nullable;

	public EnumDatabaseColumn(String name, Class<V> type, boolean nullable) {
		super(name);
		this.type = type;
		this.nullable = nullable;
		values = new MutableListComboBoxModel<Object>(generateValues());
		cellRenderer = new StringDatabaseTableCellRenderer<T, Object>(this);
	}

	public EnumDatabaseColumn(String name, DatabaseWindow win, EntityDAO<T> dao, Class<V> type, boolean nullable) {
		super(name, win, dao);
		this.type = type;
		this.nullable = nullable;
		values = new MutableListComboBoxModel<Object>(generateValues());
		cellRenderer = new StringDatabaseTableCellRenderer<T, Object>(this);
		cellEditor = new DatabaseTableCellEditor<T, Object>(this, new JComboBox(values));
	}

	// Uses DatabaseTableModel's EventBus
	@Subscribe
	public final void updateEnumValues(LocaleChangeEvent lce) {
		if (TranslatedEnum.class.isAssignableFrom(type)) {
			values.replaceAll(generateValues());
		}
	}

	private List<Object> generateValues() {
		Iterable<Object> constants = Iterables.transform(Iterables.filter(Arrays.asList(type.getEnumConstants()), new Predicate<V>() {
			@Override
			public boolean apply(V input) {
				return !isTransient(input);
			}
		}), new Function<V, Object>() {
			@Override
			public Object apply(V input) {
				return new WrappedEnum(input);
			}
		});

		if (nullable) {
			return Lists.<Object>newArrayList(Iterables.concat(Collections.singletonList(""), constants)); //$NON-NLS-1$
		} else {
			return Lists.<Object>newArrayList(constants);
		}
	}

	public static boolean isTransient(Enum<?> value) {
		try {
			return value.getClass().getField(value.name()).isAnnotationPresent(Transient.class);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected final Object getValue(T row, boolean editing) {
		V value = getEnumValue(row);
		if (value == null) {
			assert (nullable);
			return ""; //$NON-NLS-1$
		} else {
			return new WrappedEnum(value);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected final boolean setValue(T row, Object value) {
		if (value.equals("")) { //$NON-NLS-1$
			assert (nullable);
			value = null;
		} else {
			value = ((WrappedEnum)value).getValue();
		}
		return setEnumValue(row, (V)value);
	}

	protected abstract V getEnumValue(T row);

	protected abstract boolean setEnumValue(T row, V value);

	class WrappedEnum {
		private V value;

		public WrappedEnum(V value) {
			this.value = value;
		}

		public V getValue() {
			return value;
		}

		@Override
		public String toString() {
			if (value instanceof TranslatedEnum) {
				return Messages.getString(((TranslatedEnum)value).getMessagesKey());
			} else {
				return value.toString();
			}
		}
	}
}