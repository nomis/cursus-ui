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

import uk.uuid.lp0.cursus.ui.component.DatabaseWindow;
import uk.uuid.cursus.db.dao.SeriesDAO;
import uk.uuid.cursus.db.data.Series;

public class DeleteSeriesAction extends AbstractDeleteAction<Series> {
	private static final SeriesDAO seriesDAO = new SeriesDAO();

	public DeleteSeriesAction(DatabaseWindow win, Series series) {
		super("menu.series.delete", false, win, series); //$NON-NLS-1$
	}

	@Override
	protected void doDelete(Series item) {
		Series series = seriesDAO.get(item);
		seriesDAO.remove(series);
	}

	@Override
	protected void doRefresh(DatabaseWindow win) {
		win.refreshRaceList();
	}
}