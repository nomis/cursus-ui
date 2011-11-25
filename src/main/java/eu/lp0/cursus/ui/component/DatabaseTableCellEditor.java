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

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import eu.lp0.cursus.db.data.AbstractEntity;

public class DatabaseTableCellEditor<T extends AbstractEntity, V> extends DefaultCellEditor {
	private final Column<T, V> column;
	protected Integer mRow = null;
	protected T mVal = null;

	public static interface Column<T extends AbstractEntity, V> extends DatabaseTableCellRenderer.Column<T, V> {
		public boolean saveEditedValue(T row, V newValue);
	}

	public DatabaseTableCellEditor(Column<T, V> column, JCheckBox checkBox) {
		super(checkBox);
		this.column = column;
		checkBox.setBorder(null);
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
		return super.getTableCellEditorComponent(table, column.loadValue(mVal, true), isSelected, vRow, vCol);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean stopCellEditing() {
		return column.saveEditedValue(mVal, (V)getCellEditorValue()) && super.stopCellEditing();
	}
}