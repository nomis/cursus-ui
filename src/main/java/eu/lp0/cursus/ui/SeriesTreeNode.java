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

import java.util.List;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;

public class SeriesTreeNode extends HierarchicalTreeRoot<Event, Race, EventTreeNode> implements HierarchicalTreeBranch<Series, Event> {
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

	// @Override
	// public Series getUserObject() {
	// return (Series)super.getUserObject();
	// }
	//
	// @Override
	// public void setUserObject(Object userObject) {
	// assert (userObject instanceof Series);
	// super.setUserObject(userObject);
	// }
	//
	// public void updateTree(Series series) {
	// setUserObject(series);
	// if (series.getEvents().isEmpty()) {
	// removeAllChildren();
	// return;
	// }
	//
	// Iterator<Event> eventIter = series.getEvents().iterator();
	// Event next = eventIter.hasNext() ? eventIter.next() : null;
	// int i = 0;
	//
	// while (next != null || i < getChildCount()) {
	// if (i < getChildCount()) {
	// EventTreeNode event = (EventTreeNode)getChildAt(i);
	//
	// if (next == null || event.getUserObject().compareTo(next) < 0) {
	// remove(i);
	// continue;
	// } else if (event.getUserObject().equals(next)) {
	// event.updateTree(next);
	// } else {
	// insert(new EventTreeNode(next), i);
	// }
	//
	// i++;
	// } else {
	// insert(new EventTreeNode(next), i++);
	// }
	//
	// next = eventIter.hasNext() ? eventIter.next() : null;
	// }
	// }
}