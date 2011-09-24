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
package eu.lp0.cursus.ui.component;

import java.util.Arrays;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public abstract class ExpandingTreeNode<T> extends DefaultMutableTreeNode {
	public ExpandingTreeNode(T userObject) {
		super(userObject);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getUserObject() {
		return (T)super.getUserObject();
	}

	/**
	 * Expand everything when new nodes are added
	 * 
	 * @param tree
	 *            The tree
	 * @param path
	 *            Path to us (inclusive)
	 */
	protected void expandAll(JTree tree, TreePath path) {
		assert (SwingUtilities.isEventDispatchThread());

		if (getChildCount() == 0) {
			return;
		}

		tree.expandPath(path);

		for (int i = 0; i < getChildCount(); i++) {
			ExpandingTreeNode<?> child = (ExpandingTreeNode<?>)getChildAt(i);
			child.expandAll(tree, appendedTreePath(path, child));
		}
	}

	/**
	 * Append a child to the TreePath of its parent
	 * 
	 * @param parent
	 *            Parent node path
	 * @param child
	 *            Child node
	 * @return Combined path
	 */
	protected static TreePath appendedTreePath(TreePath parent, Object child) {
		Object[] path = Arrays.copyOf(parent.getPath(), parent.getPathCount() + 1);
		path[path.length - 1] = child;
		return new TreePath(path);
	}
}