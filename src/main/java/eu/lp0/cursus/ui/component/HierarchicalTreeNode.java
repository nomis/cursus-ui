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

import java.util.Iterator;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public abstract class HierarchicalTreeNode<T extends Comparable<T>, N extends DefaultMutableTreeNode> extends DefaultMutableTreeNode {
	public HierarchicalTreeNode() {
	}

	public HierarchicalTreeNode(Object userObject) {
		super(userObject);
	}

	public void updateTree(DefaultTreeModel model, List<T> items) {
		if (items == null || items.isEmpty()) {
			removeAllChildren();
			return;
		}

		Iterator<T> iter = items.iterator();
		T next = iter.hasNext() ? iter.next() : null;
		int i = 0;

		while (next != null || i < getChildCount()) {
			if (i < getChildCount()) {
				@SuppressWarnings("unchecked")
				N node = (N)getChildAt(i);
				@SuppressWarnings("unchecked")
				T user = (T)node.getUserObject();

				if (next == null || user.compareTo(next) < 0) {
					remove(i);
					model.nodesWereRemoved(this, new int[] { i }, new Object[] { node });
					continue;
				} else if (user.equals(next)) {
					updateNode(model, node, user);
					model.nodesChanged(this, new int[] { i });
				} else {
					insert(constructChildNode(next), i);
					model.nodesWereInserted(this, new int[] { i });
				}
			} else {
				insert(constructChildNode(next), i);
				model.nodesWereInserted(this, new int[] { i });
				model.nodeStructureChanged(this);
			}

			i++;
			next = iter.hasNext() ? iter.next() : null;
		}
	}

	protected void updateNode(DefaultTreeModel model, N node, T item) {
		node.setUserObject(item);
	}

	protected abstract N constructChildNode(T item);
}