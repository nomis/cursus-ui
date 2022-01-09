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
package uk.uuid.cursus.ui.tree;

import java.util.List;

import uk.uuid.cursus.ui.component.HierarchicalTreeBranch;
import uk.uuid.cursus.ui.component.HierarchicalTreeRoot;
import uk.uuid.cursus.db.data.Event;
import uk.uuid.cursus.db.data.Race;

public class EventTreeNode extends HierarchicalTreeRoot<Event, Race, RaceTreeNode> implements HierarchicalTreeBranch<Event, Race> {
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
}