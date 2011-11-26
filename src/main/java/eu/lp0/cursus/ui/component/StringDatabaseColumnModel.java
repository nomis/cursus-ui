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

import javax.swing.table.TableCellEditor;

import eu.lp0.cursus.db.dao.AbstractEntityDAO;
import eu.lp0.cursus.db.data.AbstractEntity;

public abstract class StringDatabaseColumnModel<T extends AbstractEntity> extends DatabaseColumnModel<T, String> {
	private final int maxLength;

	public StringDatabaseColumnModel(String name) {
		super(name);
		this.maxLength = 0;
	}

	public StringDatabaseColumnModel(String name, DatabaseWindow win, AbstractEntityDAO<T> dao, int maxLength) {
		super(name, win, dao);
		this.maxLength = maxLength;
	}

	@Override
	protected TableCellEditor createCellEditor() {
		return new DatabaseTableCellEditor<T, String>(this, new DatabaseTextField(maxLength));
	}
}