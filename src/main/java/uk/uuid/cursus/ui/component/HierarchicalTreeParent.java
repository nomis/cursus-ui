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
package uk.uuid.cursus.ui.component;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

/**
 * Node that has HierarchicalTreeRoots as its children
 */
public abstract class HierarchicalTreeParent<T, P extends Comparable<P>, C extends Comparable<C>, N extends HierarchicalTreeNode<P>> extends
		HierarchicalTreeRoot<T, P, N> {
	public HierarchicalTreeParent(T userObject) {
		super(userObject);
	}

	@Override
	protected void updateNode(JTree tree, TreePath path, N node, P item) {
		if (node instanceof HierarchicalTreeBranch) {
			@SuppressWarnings("unchecked")
			HierarchicalTreeBranch<P, C> branch = ((HierarchicalTreeBranch<P, C>)node);
			branch.updateTree(tree, appendedTreePath(path, branch), branch.getChildItems(item));
		}

		super.updateNode(tree, path, node, item);
	}
}