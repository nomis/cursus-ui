/*
	cursus - Race series management program
	Copyright 2011, 2013-2014  Simon Arlott

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
package eu.lp0.cursus.ui.tree;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.ui.Constants;
import eu.lp0.cursus.ui.component.HierarchicalTreeParent;

public class DatabaseTreeNode extends HierarchicalTreeParent<String, Series, Event, SeriesTreeNode> {
	public DatabaseTreeNode() {
		super(Constants.APP_NAME);
	}

	@Override
	protected SeriesTreeNode constructChildNode(Series series) {
		return new SeriesTreeNode(series);
	}
}