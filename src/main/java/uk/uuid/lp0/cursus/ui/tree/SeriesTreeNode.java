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
package uk.uuid.lp0.cursus.ui.tree;

import java.util.List;

import uk.uuid.lp0.cursus.ui.component.HierarchicalTreeBranch;
import uk.uuid.lp0.cursus.ui.component.HierarchicalTreeParent;
import uk.uuid.cursus.db.data.Event;
import uk.uuid.cursus.db.data.Race;
import uk.uuid.cursus.db.data.Series;

public class SeriesTreeNode extends HierarchicalTreeParent<Series, Event, Race, EventTreeNode> implements HierarchicalTreeBranch<Series, Event> {
	public SeriesTreeNode(Series series) {
		super(series);
		for (Event event : series.getEvents()) {
			add(new EventTreeNode(event));
		}
	}

	@Override
	protected EventTreeNode constructChildNode(Event event) {
		return new EventTreeNode(event);
	}

	@Override
	public List<Event> getChildItems(Series series) {
		return series.getEvents();
	}
}