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
package uk.uuid.lp0.cursus.ui.component;

import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

/**
 * HierarchicalTreeRoots that aren't at the top level
 */
public interface HierarchicalTreeBranch<P extends Comparable<P>, T extends Comparable<T>> {
	public void updateTree(JTree tree, TreePath path, List<T> items);

	public abstract List<T> getChildItems(P item);
}