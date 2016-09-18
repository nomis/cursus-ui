/*
	cursus - Race series management program
	Copyright 2011, 2014  Simon Arlott

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
package uk.uuid.lp0.cursus.ui.component;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import uk.uuid.cursus.db.data.Entity;

public class DatabaseTableCellEditor<T extends Entity, V> extends DefaultCellEditor {
	private final Column<T, V> column;
	protected Integer mRow = null;
	protected T mVal = null;

	public static interface Column<T, V> extends DatabaseTableCellRenderer.Column<T, V> {
		public boolean saveEditedValue(T row, V newValue);
	}

	public DatabaseTableCellEditor(Column<T, V> column, JCheckBox checkBox) {
		super(checkBox);
		this.column = column;
		checkBox.setHorizontalAlignment(SwingConstants.CENTER);
		checkBox.setBorderPainted(true);
	}

	public DatabaseTableCellEditor(Column<T, V> cellSaver, JComboBox comboBox) {
		super(comboBox);
		this.column = cellSaver;
		comboBox.setBorder(null);
	}

	public DatabaseTableCellEditor(Column<T, V> cellSaver, JTextField textField) {
		super(textField);
		this.column = cellSaver;
		textField.setBorder(null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int vRow, int vCol) {
		mRow = table.convertRowIndexToModel(vRow);
		mVal = (T)value;
		if (value != null) {
			value = column.loadValue((T)value, true);
		}
		return super.getTableCellEditorComponent(table, value, isSelected, vRow, vCol);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean stopCellEditing() {
		return column.saveEditedValue(mVal, (V)getCellEditorValue()) && super.stopCellEditing();
	}
}