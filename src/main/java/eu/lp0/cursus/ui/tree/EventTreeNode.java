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
package eu.lp0.cursus.ui.tree;

import java.util.List;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.ui.component.HierarchicalTreeBranch;
import eu.lp0.cursus.ui.component.HierarchicalTreeNode;

public class EventTreeNode extends HierarchicalTreeNode<Race, RaceTreeNode> implements HierarchicalTreeBranch<Event, Race> {
	public EventTreeNode(Event event) {
		super(event);
		for (Race race : event.getRaces()) {
			add(new RaceTreeNode(race));
		}
	}

	@Override
	protected RaceTreeNode constructChildNode(Race race) {
		return new RaceTreeNode(race);
	}

	@Override
	public List<Race> getChildItems(Event event) {
		return event.getRaces();
	}

	// @Override
	// public Event getUserObject() {
	// return (Event)super.getUserObject();
	// }
	//
	// @Override
	// public void setUserObject(Object userObject) {
	// assert (userObject instanceof Event);
	// super.setUserObject(userObject);
	// }
	//
	// public void updateTree(Event event) {
	// setUserObject(event);
	// if (event.getRaces() == null || event.getRaces().isEmpty()) {
	// removeAllChildren();
	// return;
	// }
	//
	// Iterator<Race> raceIter = event.getRaces().iterator();
	// Race next = raceIter.hasNext() ? raceIter.next() : null;
	// int i = 0;
	//
	// while (next != null || i < getChildCount()) {
	// if (i < getChildCount()) {
	// RaceTreeNode race = (RaceTreeNode)getChildAt(i);
	//
	// if (next == null || race.getUserObject().compareTo(next) < 0) {
	// remove(i);
	// continue;
	// } else if (race.getUserObject().equals(next)) {
	// race.updateTree(next);
	// } else {
	// insert(new RaceTreeNode(next), i);
	// }
	//
	// i++;
	// } else {
	// insert(new RaceTreeNode(next), i++);
	// }
	//
	// next = raceIter.hasNext() ? raceIter.next() : null;
	// }
	// }
}