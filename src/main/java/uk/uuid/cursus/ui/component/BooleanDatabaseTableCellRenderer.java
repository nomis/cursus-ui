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
package uk.uuid.cursus.ui.component;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import uk.uuid.cursus.db.data.Entity;

public class BooleanDatabaseTableCellRenderer<T extends Entity> extends DatabaseTableCellRenderer<T, Boolean> {
	private final Border EMPTY_BORDER = new EmptyBorder(1, 1, 1, 1);
	private final JCheckBox checkBox = new JCheckBox();

	public BooleanDatabaseTableCellRenderer(Column<T, Boolean> column) {
		super(column);
		checkBox.setHorizontalAlignment(SwingConstants.CENTER);
		checkBox.setBorderPainted(true);
		checkBox.setOpaque(true);
	}

	@Override
	protected Component getTableCellRendererComponent_(JTable table, Boolean value, boolean isSelected, boolean hasFocus, int vRow, int vCol) {
		if (value == null) {
			value = false;
		}
		checkBox.setSelected(value);
		checkBox.setEnabled(table.isEnabled());
		if (isSelected) {
			checkBox.setForeground(table.getSelectionForeground());
			checkBox.setBackground(table.getSelectionBackground());
		} else {
			checkBox.setForeground(table.getForeground());
			checkBox.setBackground(table.getBackground());
		}
		if (hasFocus) {
			checkBox.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder")); //$NON-NLS-1$
		} else {
			checkBox.setBorder(EMPTY_BORDER);
		}
		return checkBox;
	}
}