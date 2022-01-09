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
package uk.uuid.cursus.ui.series;

import uk.uuid.cursus.ui.common.CommonDetailDialog;
import uk.uuid.cursus.ui.component.AbstractDatabaseTab;
import uk.uuid.cursus.ui.component.DatabaseWindow;
import uk.uuid.cursus.db.dao.ClassDAO;
import uk.uuid.cursus.db.dao.SeriesDAO;
import uk.uuid.cursus.db.data.Class;
import uk.uuid.cursus.db.data.Series;

public class ClassDetailDialog extends CommonDetailDialog<Class> {
	private final AbstractDatabaseTab<Series> tab;

	private static final SeriesDAO seriesDAO = new SeriesDAO();
	private static final ClassDAO classDAO = new ClassDAO();

	public ClassDetailDialog(DatabaseWindow win, AbstractDatabaseTab<Series> tab, String title, Class clazz, boolean isUpdate) {
		super(win, title, classDAO, clazz, isUpdate);
		this.tab = tab;
	}

	@Override
	protected void prePersist(Class clazz) {
		clazz.setSeries(seriesDAO.get(clazz.getSeries()));
	}

	@Override
	protected void postSave() {
		win.refreshTab(tab);
	}
}