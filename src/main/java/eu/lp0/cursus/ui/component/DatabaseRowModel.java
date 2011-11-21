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

import java.awt.Component;
import java.awt.Frame;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.PersistenceException;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.AbstractDAO;
import eu.lp0.cursus.db.data.AbstractEntity;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.RaceNumber;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.util.DatabaseError;
import eu.lp0.cursus.util.Messages;

// TODO refactor this as the race number editing is a mess
public class DatabaseRowModel<T extends AbstractEntity, O extends Frame & DatabaseWindow> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private List<Method> columnGetters = new ArrayList<Method>();
	private List<Method> columnSetters = new ArrayList<Method>();
	private O win;
	private AbstractDAO<T> dao;

	public DatabaseRowModel(O win, Class<T> clazz, AbstractDAO<T> dao) {
		this.win = win;
		this.dao = dao;

		SortedMap<Integer, Method> getters = new TreeMap<Integer, Method>();
		SortedMap<Integer, Method> setters = new TreeMap<Integer, Method>();
		for (Method m : clazz.getMethods()) {
			TableModelColumn a = m.getAnnotation(TableModelColumn.class);
			if (a != null) {
				assert (m.getReturnType() != Void.class);
				assert (m.getParameterTypes().length == 0);
				getters.put(a.index(), m);

				try {
					Method setter = clazz.getMethod(m.getName().replaceFirst("^(is|get)", "set"), m.getReturnType()); //$NON-NLS-1$ //$NON-NLS-2$
					setters.put(a.index(), setter);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		columnGetters.addAll(getters.values());
		columnSetters.addAll(setters.values());
	}

	public void setupEditableModel(JTable table) {
		Enumeration<TableColumn> cols = table.getColumnModel().getColumns();
		while (cols.hasMoreElements()) {
			TableColumn col = cols.nextElement();
			col.setCellRenderer(createCellRenderer(col.getModelIndex()));
			if (isCellEditable(col.getModelIndex())) {
				col.setCellEditor(createCellEditor(col.getModelIndex()));
			}
		}
	}

	public String getColumnName(int mCol) {
		return Messages.getString(columnGetters.get(mCol).getAnnotation(TableModelColumn.class).name());
	}

	public int getColumnCount() {
		return columnGetters.size();
	}

	private Object getValueFrom(T row, int mCol) {
		try {
			return columnGetters.get(mCol).invoke(row);
		} catch (Exception e) {
			log.error(String.format("get row=%s#%d, column=%d", row.getClass().getSimpleName(), row.getId(), mCol), e); //$NON-NLS-1$
			return null;
		}
	}

	private Class<?> getColumnClass(int mCol) {
		return columnGetters.get(mCol).getReturnType();
	}

	public boolean setEditedValueAt(T row, int mCol, Object newValue) {
		assert (SwingUtilities.isEventDispatchThread());

		Object oldValue = getValueFrom(row, mCol);
		if (oldValue == newValue || oldValue != null && oldValue.equals(newValue)) {
			return true;
		}

		boolean revert = false;
		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			T item = dao.get(row);
			try {
				columnSetters.get(mCol).invoke(item, newValue);
			} catch (Exception e) {
				revert = true;
				log.error(
						String.format(
								"Unable to update row=%s#%d, column=%d, oldValue=%s, newValue=%s", row.getClass().getSimpleName(), row.getId(), mCol, oldValue, newValue), e); //$NON-NLS-1$
				return false;
			}
			dao.persist(item);

			DatabaseSession.commit();
		} catch (PersistenceException e) {
			revert = true;
			log.error(
					String.format(
							"Unable to save changes: row=%s#%d, column=%d, oldValue=%s, newValue=%s", row.getClass().getSimpleName(), row.getId(), mCol, oldValue, newValue), e); //$NON-NLS-1$
			DatabaseError.errorSaving(win, Constants.APP_NAME, e);
			return false;
		} finally {
			if (revert) {
				try {
					columnSetters.get(mCol).invoke(row, oldValue);
				} catch (Exception e) {
					log.error(
							String.format(
									"Unable to revert row=%s#%d, column=%d, oldValue=%s, newValue=%s", row.getClass().getSimpleName(), row.getId(), mCol, oldValue, newValue), e); //$NON-NLS-1$
				}
			}

			win.getDatabase().endSession();
		}

		try {
			columnSetters.get(mCol).invoke(row, newValue);
		} catch (Exception e) {
			log.error(String.format(
					"Unable to set row=%s#%d, column=%d, oldValue=%s, newValue=%s", row.getClass().getSimpleName(), row.getId(), mCol, oldValue, newValue), e); //$NON-NLS-1$
			return false;
		}
		return true;
	}

	private TableCellRenderer createCellRenderer(int modelIndex) {
		Class<?> type = getColumnClass(modelIndex);
		if (type == List.class) {
			TableModelColumn col = columnGetters.get(modelIndex).getAnnotation(TableModelColumn.class);
			if (col.type() == RaceNumber.class) {
				return new RaceNumberDatabaseTableCellRenderer();
			}
		}
		return new DatabaseTableCellRenderer<Object>();
	}

	public boolean isCellEditable(int columnIndex) {
		return columnGetters.get(columnIndex).isAnnotationPresent(TableModelColumn.class);
	}

	private TableCellEditor createCellEditor(int mCol) {
		Class<?> type = getColumnClass(mCol);
		if (type.isEnum()) {
			Column col = columnGetters.get(mCol).getAnnotation(Column.class);
			return new EnumDatabaseTableCellEditor(type, col == null || col.nullable() == true);
		} else if (type == String.class) {
			return new DatabaseTableCellEditor<String>(new JTextField());
		} else if (type == boolean.class) {
			return new DatabaseTableCellEditor<Boolean>(new JCheckBox());
		} else if (type == List.class) {
			TableModelColumn col = columnGetters.get(mCol).getAnnotation(TableModelColumn.class);
			if (col.type() == RaceNumber.class) {
				return new RaceNumberDatabaseTableCellEditor();
			}
		}
		return null;
	}

	private class DatabaseTableCellRenderer<R> extends DefaultTableCellRenderer {
		@Override
		@SuppressWarnings("unchecked")
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int vRow, int vCol) {
			int mRow = table.convertRowIndexToModel(vRow);
			int mCol = table.convertColumnIndexToModel(vCol);
			return super.getTableCellRendererComponent(table, convertFromDatabase((R)getValueFrom((T)value, mCol)), isSelected, hasFocus, mRow, mCol);
		}

		protected Object convertFromDatabase(R value) {
			return value;
		}
	}

	public class DatabaseTableCellEditor<E> extends DefaultCellEditor {
		protected T mVal = null;
		protected Integer mRow = null;
		protected Integer mCol = null;

		public DatabaseTableCellEditor(JCheckBox checkBox) {
			super(checkBox);
			checkBox.setBorder(null);
		}

		public DatabaseTableCellEditor(JComboBox comboBox) {
			super(comboBox);
			comboBox.setBorder(null);
		}

		public DatabaseTableCellEditor(JTextField textField) {
			super(textField);
			textField.setBorder(null);
		}

		@Override
		@SuppressWarnings("unchecked")
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int vRow, int vCol) {
			mVal = (T)value;
			mRow = table.convertRowIndexToModel(vRow);
			mCol = table.convertColumnIndexToModel(vCol);
			return super.getTableCellEditorComponent(table, convertFromDatabase(mVal, (E)getValueFrom(mVal, mCol)), isSelected, vRow, vCol);
		}

		protected Object convertFromDatabase(T row, E value) {
			return value;
		}

		@SuppressWarnings("unchecked")
		protected E convertToDatabase(T row, Object value) {
			return (E)value;
		}

		@Override
		public boolean stopCellEditing() {
			return DatabaseRowModel.this.setEditedValueAt(mVal, mCol, convertToDatabase(mVal, getCellEditorValue())) && super.stopCellEditing();
		}
	}

	private static Vector<Object> generateEnumValues(Class<?> type, boolean nullable) {
		Vector<Object> values = new Vector<Object>();
		if (nullable) {
			values.add(""); //$NON-NLS-1$
		}
		values.addAll(Arrays.asList((Object[])type.getEnumConstants()));
		return values;
	}

	private class EnumDatabaseTableCellEditor extends DatabaseTableCellEditor<Enum<?>> {
		public EnumDatabaseTableCellEditor(Class<?> type, boolean nullable) {
			super(new JComboBox(generateEnumValues(type, nullable)));
		}

		@Override
		protected Object convertFromDatabase(T row, Enum<?> value) {
			if (value == null) {
				return ""; //$NON-NLS-1$
			}
			return value;
		}

		@Override
		protected Enum<?> convertToDatabase(T row, Object value) {
			if (value.equals("")) { //$NON-NLS-1$
				value = null;
			}
			return (Enum<?>)value;
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

	private class RaceNumberDatabaseTableCellRenderer extends DatabaseTableCellRenderer<List<RaceNumber>> {
		@Override
		protected Object convertFromDatabase(List<RaceNumber> value) {
			return DatabaseRowModel.convertFromDatabase(value);
		}
	}

	private class RaceNumberDatabaseTableCellEditor extends DatabaseTableCellEditor<List<RaceNumber>> {
		public RaceNumberDatabaseTableCellEditor() {
			super(new JTextField());
		}

		@Override
		protected Object convertFromDatabase(T row, List<RaceNumber> value) {
			return DatabaseRowModel.convertFromDatabase(value);
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