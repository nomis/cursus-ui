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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ObjectArrays;

/**
 * Node that has HierarchicalTreeNodes as its children
 * 
 * Manages the mess that is JTree
 * 
 * JTree makes it very difficult to get at all of the expansion state which makes things difficult when we have to reorder nodes without affecting the currently
 * selected node (which the user has selected... so it shouldn't move around or change state)
 */
public abstract class HierarchicalTreeRoot<P, C extends Comparable<C>, N extends HierarchicalTreeNode<C>> extends HierarchicalTreeNode<P> {
	private final Logger log = LoggerFactory.getLogger(getClass());

	public HierarchicalTreeRoot(P userObject) {
		super(userObject);
	}

	/**
	 * Update tree with changes
	 * 
	 * @param tree
	 *            The tree
	 * @param path
	 *            Path to this node (inclusive)
	 * @param items
	 *            Items for this node
	 */
	public void updateTree(JTree tree, TreePath path, List<C> items) {
		assert (SwingUtilities.isEventDispatchThread());

		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();

		if (items == null || items.isEmpty()) {
			if (log.isTraceEnabled() && getChildCount() > 0) {
				log.trace("Removing all nodes"); //$NON-NLS-1$
			}
			removeAllChildren();
			model.nodeStructureChanged(this);
			return;
		}

		// Store a map of each node so that they can be moved around and re-expanded
		Map<C, N> existing = new HashMap<C, N>();
		Map<C, Map<TreePath, Boolean>> expanded = new HashMap<C, Map<TreePath, Boolean>>();
		C fixed = null;

		boolean selected = isPathSelected(tree, path) && !path.equals(tree.getSelectionPath());
		for (int i = 0; i < getChildCount(); i++) {
			@SuppressWarnings("unchecked")
			N node = (N)getChildAt(i);
			C user = node.getUserObject();
			int index = items.indexOf(user);

			if (index != -1) {
				C item = items.get(index);
				log.trace("Updating node {}", user); //$NON-NLS-1$
				// Update all nodes first otherwise the sorting
				// comparisons will be using both old and new data
				updateNode(tree, path, node, item);

				if (selected && isPathSelected(tree, appendedTreePath(path, node))) {
					// Identify currently selected node first so that it can be left alone
					fixed = item;
				} else {
					existing.put(user, node);
					expanded.put(user, getPathStates(tree, appendedTreePath(path, node), node));
				}
			} else {
				log.trace("Removing node {}", user); //$NON-NLS-1$
				// Remove nodes that no longer exist
				model.removeNodeFromParent(node);
				i--;
			}
		}

		// Re-order nodes to match new sorted order, insert new nodes
		Set<C> removed = new HashSet<C>();
		Iterator<C> iter = items.iterator();
		C next = iter.hasNext() ? iter.next() : null;
		int i = 0;

		while (next != null || i < getChildCount()) {
			boolean add = false;

			if (i < getChildCount()) {
				boolean remove = false;
				@SuppressWarnings("unchecked")
				N node = (N)getChildAt(i);
				C user = node.getUserObject();

				if (next == null || user.compareTo(next) < 0) {
					remove = true;
				} else if (user.compareTo(next) == 0) {
					// Do nothing
				} else if (next == fixed) {
					remove = true;
				} else {
					add = true;
				}

				if (remove) {
					log.trace("Removing node {}", user); //$NON-NLS-1$
					removed.add(user);
					model.removeNodeFromParent(node);
					continue;
				}
			} else {
				add = true;
			}

			if (add) {
				boolean firstChild = (getChildCount() == 0);
				N node = existing.get(next);
				if (node != null) {
					if (!removed.contains(next)) {
						log.trace("Removing node {}", next); //$NON-NLS-1$
						removed.add(next);
						model.removeNodeFromParent(node);
					}

					log.trace("Restoring node {}", next); //$NON-NLS-1$
					model.insertNodeInto(node, this, i);
					restorePaths(tree, expanded.get(next));

					log.trace("Updating node {}", next); //$NON-NLS-1$
					updateNode(tree, path, node, next);
				} else {
					log.trace("Adding node {}", next); //$NON-NLS-1$
					node = constructChildNode(next);
					model.insertNodeInto(node, this, i);
					expandAll(tree, appendedTreePath(path, node), node);
				}
				if (firstChild) {
					tree.expandPath(path);
				}
			}

			i++;
			next = iter.hasNext() ? iter.next() : null;
		}
	}

	/**
	 * Get a map of expanded paths under a node (including itself)
	 * 
	 * Works around the JTree "feature" that a child node is considered collapsed if its parent is currently collapsed
	 * 
	 * @param tree
	 *            Tree
	 * @param path
	 *            Path to node (inclusive)
	 * @param node
	 *            Node to examine
	 * @return a map of expanded and collapsed paths
	 */
	private static Map<TreePath, Boolean> getPathStates(JTree tree, TreePath path, TreeNode node) {
		boolean expanded = tree.isExpanded(path);
		Map<TreePath, Boolean> paths = new LinkedHashMap<TreePath, Boolean>();

		paths.put(path, expanded);
		if (!expanded) {
			// Temporarily expand to find the state of child nodes
			tree.expandPath(path);
		}

		for (int i = 0; i < node.getChildCount(); i++) {
			TreeNode child = node.getChildAt(i);
			paths.putAll(getPathStates(tree, appendedTreePath(path, child), child));
		}

		if (!expanded) {
			// Reverse temporary expansion
			tree.collapsePath(path);
		}

		return paths;
	}

	/**
	 * Restore path states in reverse order
	 * 
	 * Works around the JTree "feature" that collapsing a node always expands its parent first
	 * 
	 * @param tree
	 *            Tree
	 * @param paths
	 *            Paths to be expanded/collapsed
	 */
	private static void restorePaths(JTree tree, Map<TreePath, Boolean> paths) {
		List<Map.Entry<TreePath, Boolean>> reversed = new ArrayList<Map.Entry<TreePath, Boolean>>(paths.entrySet());
		ListIterator<Map.Entry<TreePath, Boolean>> iter = reversed.listIterator(reversed.size() - 1);

		while (iter.hasPrevious()) {
			Map.Entry<TreePath, Boolean> path = iter.previous();
			if (path.getValue()) {
				tree.expandPath(path.getKey());
			} else {
				tree.collapsePath(path.getKey());
			}
		}
	}

	/**
	 * Expand everything when new nodes are added
	 * 
	 * @param tree
	 *            The tree
	 * @param path
	 *            Path to node (inclusive)
	 * @param node
	 *            Node to expand
	 */
	private static void expandAll(JTree tree, TreePath path, TreeNode node) {
		if (node.getChildCount() == 0) {
			return;
		}

		// We could optimise by not expanding this node if it has grandchildren, but that would take longer than just expanding anyway
		tree.expandPath(path);

		for (int i = 0; i < node.getChildCount(); i++) {
			HierarchicalTreeNode<?> child = (HierarchicalTreeNode<?>)node.getChildAt(i);
			expandAll(tree, appendedTreePath(path, child), child);
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
		return new TreePath(ObjectArrays.concat(parent.getPath(), child));
	}

	/**
	 * Removes nodes from the end of a tree path
	 * 
	 * @param path
	 *            Tree path
	 * @return Truncated path
	 */
	protected static TreePath truncatedTreePath(TreePath path, int count) {
		assert (count > 0);
		return new TreePath(Arrays.copyOf(path.getPath(), path.getPathCount() - count));
	}

	/**
	 * Check if the specified path is currently selected
	 * 
	 * @param tree
	 * @param path
	 * @return
	 */
	private static boolean isPathSelected(JTree tree, TreePath path) {
		TreePath selected = tree.getSelectionPath();

		while (selected != null) {
			if (selected.equals(path)) {
				return true;
			}

			if (selected.getPathCount() > 1) {
				selected = truncatedTreePath(selected, 1);
			} else {
				selected = null;
			}
		}

		return false;
	}

	protected void updateNode(JTree tree, TreePath path, N node, C item) {
		assert (SwingUtilities.isEventDispatchThread());

		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		model.valueForPathChanged(appendedTreePath(path, node), item);
	}

	protected abstract N constructChildNode(C item);
}