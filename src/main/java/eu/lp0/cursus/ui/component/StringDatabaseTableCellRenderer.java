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

import javax.swing.JTable;

import eu.lp0.cursus.db.data.Entity;

public class StringDatabaseTableCellRenderer<T extends Entity, V> extends DatabaseDefaultTableCellRenderer<T, V> {
	public StringDatabaseTableCellRenderer(DatabaseTableCellRenderer.Column<T, V> column) {
		super(column);
	}

	@Override
	protected Component getTableCellRendererComponent_(JTable table, V value, boolean isSelected, boolean hasFocus, int vRow, int vCol) {
		setEnabled(table.isEnabled());
		return super.getTableCellRendererComponent_(table, value, isSelected, hasFocus, vRow, vCol);
	}
}