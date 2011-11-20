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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
public class DatabaseTableModel<T extends AbstractEntity, O extends Frame & DatabaseWindow> extends AbstractTableModel {
	private final Logger log = LoggerFactory.getLogger(getClass());
	protected O win;
	private AbstractDAO<T> dao;
	private List<Method> columnGetters = new ArrayList<Method>();
	private List<Method> columnSetters = new ArrayList<Method>();
	private ArrayList<T> rows = new ArrayList<T>();

	public DatabaseTableModel(Class<T> clazz, O win, AbstractDAO<T> dao) {
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

	@Override
	public String getColumnName(int column) {
		return Messages.getString(columnGetters.get(column).getAnnotation(TableModelColumn.class).name());
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnGetters.get(columnIndex).getReturnType();
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public int getColumnCount() {
		return columnGetters.size();
	}

	public T getValueAt(int rowIndex) {
		return rows.get(rowIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			return columnGetters.get(columnIndex).invoke(rows.get(rowIndex));
		} catch (Exception e) {
			log.error(String.format("get row=%d, column=%d", rowIndex, columnIndex), e); //$NON-NLS-1$
			return null;
		}
	}

	public boolean setEditedValueAt(int rowIndex, int columnIndex, Object newValue) {
		assert (SwingUtilities.isEventDispatchThread());

		if (!isCellEditable(rowIndex, columnIndex)) {
			return false;
		}

		Object oldValue = getValueAt(rowIndex, columnIndex);
		if (oldValue == newValue || oldValue != null && oldValue.equals(newValue)) {
			return true;
		}

		boolean revert = false;
		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			T item = dao.get(rows.get(rowIndex));
			try {
				columnSetters.get(columnIndex).invoke(item, newValue);
			} catch (Exception e) {
				revert = true;
				log.error(String.format("Unable to update row=%d, column=%d, oldValue=%s, newValue=%s", rowIndex, columnIndex, oldValue, newValue), e); //$NON-NLS-1$
				return false;
			}
			dao.persist(item);

			DatabaseSession.commit();
		} catch (PersistenceException e) {
			revert = true;
			log.error(String.format("Unable to save changes: row=%d, column=%d, oldValue=%s, newValue=%s", rowIndex, columnIndex, oldValue, newValue), e); //$NON-NLS-1$
			DatabaseError.errorSaving(win, Constants.APP_NAME, e);
			return false;
		} finally {
			if (revert) {
				try {
					columnSetters.get(columnIndex).invoke(rows.get(rowIndex), oldValue);
					fireTableCellUpdated(rowIndex, columnIndex);
				} catch (Exception e) {
					log.error(String.format("Unable to revert row=%d, column=%d, oldValue=%s, newValue=%s", rowIndex, columnIndex, oldValue, newValue), e); //$NON-NLS-1$
				}
			}

			win.getDatabase().endSession();
		}

		try {
			columnSetters.get(columnIndex).invoke(rows.get(rowIndex), newValue);
		} catch (Exception e) {
			log.error(String.format("Unable to set row=%d, column=%d, oldValue=%s, newValue=%s", rowIndex, columnIndex, oldValue, newValue), e); //$NON-NLS-1$
			return false;
		}
		fireTableCellUpdated(rowIndex, columnIndex);
		return true;
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

	public void setupEditableModel(JTable table) {
		table.setModel(this);

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(this);
		sorter.setSortsOnUpdates(true);
		table.setRowSorter(sorter);

		Enumeration<TableColumn> cols = table.getColumnModel().getColumns();
		while (cols.hasMoreElements()) {
			TableColumn col = cols.nextElement();
			col.setCellRenderer(createCellRenderer(col.getModelIndex()));
			if (isCellEditable(col.getModelIndex())) {
				col.setCellEditor(createCellEditor(col.getModelIndex()));
			}
		}
	}

	private TableCellRenderer createCellRenderer(int modelIndex) {
		Class<?> type = getColumnClass(modelIndex);
		if (type == List.class) {
			TableModelColumn col = columnGetters.get(modelIndex).getAnnotation(TableModelColumn.class);
			if (col.type() == RaceNumber.class) {
				return new RaceNumberDatabaseTableCellRenderer();
			}
		}
		return new DefaultTableCellRenderer();
	}

	private boolean isCellEditable(int columnIndex) {
		return columnGetters.get(columnIndex).isAnnotationPresent(TableModelColumn.class);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return isCellEditable(columnIndex);
	}

	private TableCellEditor createCellEditor(int modelIndex) {
		Class<?> type = getColumnClass(modelIndex);
		if (type.isEnum()) {
			Column col = columnGetters.get(modelIndex).getAnnotation(Column.class);
			return new EnumDatabaseTableCellEditor(type, col == null || col.nullable() == true);
		} else if (type == String.class) {
			return new DatabaseTableCellEditor<String>(new JTextField());
		} else if (type == boolean.class) {
			return new DatabaseTableCellEditor<Boolean>(new JCheckBox());
		} else if (type == List.class) {
			TableModelColumn col = columnGetters.get(modelIndex).getAnnotation(TableModelColumn.class);
			if (col.type() == RaceNumber.class) {
				return new RaceNumberDatabaseTableCellEditor();
			}
		}
		return null;
	}

	private class DatabaseTableCellRenderer<R> extends DefaultTableCellRenderer {
		@Override
		@SuppressWarnings("unchecked")
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			return super.getTableCellRendererComponent(table, convertFromDatabase((R)value), isSelected, hasFocus, row, column);
		}

		protected Object convertFromDatabase(R value) {
			return value;
		}
	}

	public class DatabaseTableCellEditor<E> extends DefaultCellEditor {
		protected Integer rowIndex = null;
		protected Integer columnIndex = null;

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
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			rowIndex = table.convertRowIndexToModel(row);
			columnIndex = table.convertColumnIndexToModel(column);
			return super.getTableCellEditorComponent(table, convertFromDatabase(getValueAt(rowIndex), (E)value), isSelected, row, column);
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
			boolean ok = DatabaseTableModel.this.setEditedValueAt(rowIndex, columnIndex, convertToDatabase(getValueAt(rowIndex), getCellEditorValue()));
			if (ok) {
				cancelCellEditing();
			}
			return ok;
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
			return DatabaseTableModel.convertFromDatabase(value);
		}
	}

	private class RaceNumberDatabaseTableCellEditor extends DatabaseTableCellEditor<List<RaceNumber>> {
		public RaceNumberDatabaseTableCellEditor() {
			super(new JTextField());
		}

		@Override
		protected Object convertFromDatabase(T row, List<RaceNumber> value) {
			return DatabaseTableModel.convertFromDatabase(value);
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