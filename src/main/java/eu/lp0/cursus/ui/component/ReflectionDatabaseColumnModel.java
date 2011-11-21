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
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lp0.cursus.db.dao.AbstractDAO;
import eu.lp0.cursus.db.data.AbstractEntity;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.RaceNumber;
import eu.lp0.cursus.util.Messages;

// TODO refactor this as the race number editing is a mess
public class ReflectionDatabaseColumnModel<T extends AbstractEntity, O extends Frame & DatabaseWindow> extends EditableDatabaseColumnModel<T, Object, O> {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private final TableModelColumn annotation;
	private final Method getter;
	private final Method setter;

	public ReflectionDatabaseColumnModel(O win, Class<T> clazz, AbstractDAO<T> dao, Method m, TableModelColumn annotation) {
		super(win, dao);

		assert (m.getReturnType() != Void.class);
		assert (m.getParameterTypes().length == 0);
		this.annotation = annotation;
		getter = m;

		try {
			setter = clazz.getMethod(m.getName().replaceFirst("^(is|get)", "set"), m.getReturnType()); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getColumnName() {
		return Messages.getString(annotation.name());
	}

	@Override
	protected Object getValue(T row) {
		try {
			return getter.invoke(row);
		} catch (Exception e) {
			log.error(String.format("invoke error row=%s#%d, method=%s", row.getClass().getSimpleName(), row.getId(), getter.getName()), e); //$NON-NLS-1$
			return null;
		}
	}

	@Override
	protected boolean setValue(T row, Object value) {
		try {
			setter.invoke(row, value);
			return true;
		} catch (Exception e) {
			log.error(String.format("invoke error row=%s#%d, method=%s, value=%s", row.getClass().getSimpleName(), row.getId(), setter.getName(), value), e); //$NON-NLS-1$
			return false;
		}
	}

	@Override
	protected boolean isCellEditable() {
		return true;
	}

	private Class<?> getColumnClass() {
		return getter.getReturnType();
	}

	@Override
	protected TableCellRenderer createCellRenderer() {
		Class<?> type = getColumnClass();
		if (type == List.class) {
			if (annotation.type() == RaceNumber.class) {
				return new RaceNumberDatabaseTableCellRenderer();
			}
		}
		return new ReflectionDatabaseTableCellRenderer();
	}

	@Override
	@SuppressWarnings("unchecked")
	protected TableCellEditor createCellEditor() {
		Class<?> type = getColumnClass();
		if (type.isEnum()) {
			Column col = getter.getAnnotation(Column.class);
			return new EnumDatabaseTableCellEditor(this, (Class<Enum<?>>)type, col == null || col.nullable() == true);
		} else if (type == String.class) {
			return new ReflectionDatabaseTableCellEditor<String>(this, new JTextField());
		} else if (type == boolean.class) {
			return new ReflectionDatabaseTableCellEditor<Boolean>(this, new JCheckBox());
		} else if (type == List.class) {
			if (annotation.type() == RaceNumber.class) {
				return new RaceNumberDatabaseTableCellEditor(this);
			}
		}
		return null;
	}

	private static Vector<Object> generateEnumValues(Class<Enum<?>> type, boolean nullable) {
		Vector<Object> values = new Vector<Object>();
		if (nullable) {
			values.add(""); //$NON-NLS-1$
		}

		values.addAll(Arrays.asList(type.getEnumConstants()));
		return values;
	}

	private class ReflectionDatabaseTableCellEditor<V> extends DatabaseTableCellEditor<T, Object> {
		public ReflectionDatabaseTableCellEditor(CellSaver<T, Object> cellSaver, JCheckBox checkBox) {
			super(cellSaver, checkBox);
		}

		public ReflectionDatabaseTableCellEditor(CellSaver<T, Object> cellSaver, JComboBox comboBox) {
			super(cellSaver, comboBox);
		}

		public ReflectionDatabaseTableCellEditor(CellSaver<T, Object> cellSaver, JTextField textField) {
			super(cellSaver, textField);
		}

		@Override
		protected Object convertFromDatabase(T row) {
			return getValue(row);
		}

		@Override
		@SuppressWarnings("unchecked")
		protected V convertToDatabase(T row, Object value) {
			return (V)value;
		}
	}

	private class EnumDatabaseTableCellEditor extends ReflectionDatabaseTableCellEditor<Object> {
		public EnumDatabaseTableCellEditor(CellSaver<T, Object> cellSaver, Class<Enum<?>> type, boolean nullable) {
			super(cellSaver, new JComboBox(generateEnumValues(type, nullable)));
		}

		@Override
		protected Object convertFromDatabase(T row) {
			Object value = super.convertFromDatabase(row);
			if (value == null) {
				return ""; //$NON-NLS-1$
			}
			return value;
		}

		@Override
		protected Object convertToDatabase(T row, Object value) {
			if (value.equals("")) { //$NON-NLS-1$
				value = null;
			}
			return super.convertToDatabase(row, value);
		}
	}

	private class ReflectionDatabaseTableCellRenderer extends DatabaseTableCellRenderer<T> {
		@Override
		protected Object convertFromDatabase(T row) {
			return getValue(row);
		}
	}

	private static String convertFromDatabase(List<RaceNumber> raceNos) {
		StringBuilder sb = new StringBuilder();
		for (RaceNumber raceNo : raceNos) {
			if (sb.length() > 0) {
				sb.append(", "); //$NON-NLS-1$
			}
			sb.append(raceNo);
		}
		return sb.toString();
	}

	private class RaceNumberDatabaseTableCellRenderer extends ReflectionDatabaseTableCellRenderer {
		@Override
		protected Object convertFromDatabase(T row) {
			@SuppressWarnings("unchecked")
			List<RaceNumber> value = (List<RaceNumber>)super.convertFromDatabase(row);
			return ReflectionDatabaseColumnModel.convertFromDatabase(value);
		}
	}

	private class RaceNumberDatabaseTableCellEditor extends ReflectionDatabaseTableCellEditor<List<RaceNumber>> {
		public RaceNumberDatabaseTableCellEditor(CellSaver<T, Object> cellSaver) {
			super(cellSaver, new JTextField());
		}

		@Override
		protected Object convertFromDatabase(T row) {
			@SuppressWarnings("unchecked")
			List<RaceNumber> value = (List<RaceNumber>)super.convertFromDatabase(row);
			return ReflectionDatabaseColumnModel.convertFromDatabase(value);
		}

		@Override
		protected List<RaceNumber> convertToDatabase(T row, Object values) {
			Pilot pilot = (Pilot)row;
			List<RaceNumber> raceNos = new ArrayList<RaceNumber>();
			for (String value : values.toString().split("[ ,;/]+")) { //$NON-NLS-1$
				if (value.length() > 0) {
					raceNos.add(RaceNumber.valueOfFor(value, pilot));
				}
			}
			return raceNos;
		}
	}
}