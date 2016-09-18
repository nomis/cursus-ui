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

import uk.uuid.cursus.db.dao.EntityDAO;
import uk.uuid.cursus.db.data.Entity;

public abstract class StringDatabaseColumn<T extends Entity> extends DatabaseColumn<T, String> {
	public StringDatabaseColumn(String name) {
		super(name);
		cellRenderer = new StringDatabaseTableCellRenderer<T, String>(this);
	}

	public StringDatabaseColumn(String name, DatabaseWindow win, EntityDAO<T> dao, int maxLength) {
		super(name, win, dao);
		cellRenderer = new StringDatabaseTableCellRenderer<T, String>(this);
		cellEditor = new DatabaseTableCellEditor<T, String>(this, new DatabaseTextField(maxLength));
	}
}