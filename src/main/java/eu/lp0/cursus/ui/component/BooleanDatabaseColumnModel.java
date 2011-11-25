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

import javax.swing.JCheckBox;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import eu.lp0.cursus.db.dao.AbstractDAO;
import eu.lp0.cursus.db.data.AbstractEntity;

public abstract class BooleanDatabaseColumnModel<T extends AbstractEntity> extends DatabaseColumnModel<T, Boolean> {
	public BooleanDatabaseColumnModel(String name) {
		super(name);
	}

	public BooleanDatabaseColumnModel(String name, DatabaseWindow win, AbstractDAO<T> dao) {
		super(name, win, dao);
	}

	@Override
	protected TableCellRenderer createCellRenderer() {
		return new BooleanDatabaseTableCellRenderer<T>(this);
	}

	@Override
	protected TableCellEditor createCellEditor() {
		return new DatabaseTableCellEditor<T, Boolean>(this, new JCheckBox());
	}
}