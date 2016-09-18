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
package uk.uuid.lp0.cursus.ui.actions;

import uk.uuid.lp0.cursus.ui.component.AbstractDatabaseTab;
import uk.uuid.lp0.cursus.ui.component.DatabaseWindow;
import uk.uuid.cursus.db.dao.ClassDAO;
import uk.uuid.cursus.db.data.Class;
import uk.uuid.cursus.db.data.Series;

public class DeleteClassAction extends AbstractDeleteAction<Class> {
	private final AbstractDatabaseTab<Series> tab;

	private static final ClassDAO classDAO = new ClassDAO();

	public DeleteClassAction(DatabaseWindow win, AbstractDatabaseTab<Series> tab, Class clazz) {
		super("menu.event.delete", false, win, clazz); //$NON-NLS-1$
		this.tab = tab;
	}

	@Override
	protected void doDelete(Class item) {
		Class clazz = classDAO.get(item);
		clazz.getSeries().getClasses().remove(clazz);
		classDAO.remove(clazz);
	}

	@Override
	protected void doRefresh(DatabaseWindow win) {
		win.refreshTab(tab);
	}
}