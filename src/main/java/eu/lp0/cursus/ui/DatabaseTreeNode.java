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
package eu.lp0.cursus.ui;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Series;

public class DatabaseTreeNode extends HierarchicalTreeRoot<Series, Event, SeriesTreeNode> {
	@Override
	protected SeriesTreeNode constructChildNode(Series series) {
		return new SeriesTreeNode(series);
	}

	// public void updateTree(DefaultTreeModel model, List<Series> seriesList) {
	// if (seriesList == null || seriesList.isEmpty()) {
	// removeAllChildren();
	// return;
	// }
	//
	// Iterator<Series> seriesIter = seriesList.iterator();
	// Series next = seriesIter.hasNext() ? seriesIter.next() : null;
	// int i = 0;
	//
	// while (next != null || i < getChildCount()) {
	// if (i < getChildCount()) {
	// SeriesTreeNode series = (SeriesTreeNode)getChildAt(i);
	//
	// if (next == null || series.getUserObject().compareTo(next) < 0) {
	// remove(i);
	// model.nodesWereRemoved(this, new int[] { i }, new Object[] { series });
	// continue;
	// } else if (series.getUserObject().equals(next)) {
	// series.updateTree(next);
	// model.nodesChanged(this, new int[] { i });
	// } else {
	// insert(new SeriesTreeNode(next), i);
	// model.nodesWereInserted(this, new int[] { i });
	// }
	//
	// i++;
	// } else {
	// insert(new SeriesTreeNode(next), i);
	// model.nodesWereInserted(this, new int[] { i });
	//
	// i++;
	// }
	//
	// next = seriesIter.hasNext() ? seriesIter.next() : null;
	// }
	// }
}