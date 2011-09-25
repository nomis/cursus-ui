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
package eu.lp0.cursus.ui.series;

import java.awt.Frame;

import eu.lp0.cursus.db.dao.ClassDAO;
import eu.lp0.cursus.db.dao.SeriesDAO;
import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.ui.common.CommonDetailDialog;
import eu.lp0.cursus.ui.component.DatabaseWindow;

public class ClassDetailDialog<O extends Frame & DatabaseWindow> extends CommonDetailDialog<O, Class> {
	private final SeriesClassesTab<O> tab;

	private static final SeriesDAO seriesDAO = new SeriesDAO();
	private static final ClassDAO classDAO = new ClassDAO();

	public ClassDetailDialog(O win, SeriesClassesTab<O> tab, String title, Class clazz) {
		super(win, title, classDAO, clazz);
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