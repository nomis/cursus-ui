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
package uk.uuid.cursus.test.ui;

import javax.accessibility.Accessible;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import uk.uuid.cursus.ui.util.AccessibleJTreeFix;

public class JTreeTests {
	private JTree tree;
	private DefaultMutableTreeNode root;
	private DefaultMutableTreeNode parent;
	private DefaultMutableTreeNode child;

	private enum NodeName {
		ROOT, PARENT, CHILD
	};

	@Before
	public void createTree() {
		root = new DefaultMutableTreeNode(NodeName.ROOT);
		parent = new DefaultMutableTreeNode(NodeName.PARENT);
		child = new DefaultMutableTreeNode(NodeName.CHILD);
		root.add(parent);
		parent.add(child);

		tree = new AccessibleJTreeFix(new DefaultTreeModel(root));
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.expandPath(new TreePath(new Object[] { root, parent }));
		Assert.assertTrue(tree.isVisible(new TreePath(new Object[] { root, parent, child })));
		Assert.assertNull(tree.getSelectionPath());
	}

	private Accessible getOnlyChild(Accessible aParent, NodeName name) {
		Assert.assertEquals(1, aParent.getAccessibleContext().getAccessibleChildrenCount());
		Accessible aChild = aParent.getAccessibleContext().getAccessibleChild(0);
		Assert.assertEquals(name.toString(), aChild.getAccessibleContext().getAccessibleName());
		return aChild;
	}

	private void selectOnlyChild(Accessible aParent, NodeName name) {
		getOnlyChild(aParent, name);

		Assert.assertEquals(1, aParent.getAccessibleContext().getAccessibleChildrenCount());
		aParent.getAccessibleContext().getAccessibleSelection().addAccessibleSelection(0);
	}

	@Test
	public void accessibleSelectParentVisibleRoot() {
		tree.setRootVisible(true);

		Accessible aRoot = getOnlyChild(tree, NodeName.ROOT);
		selectOnlyChild(aRoot, NodeName.PARENT);

		Assert.assertArrayEquals(new Object[] { root, parent }, tree.getSelectionPath().getPath());
	}

	@Test
	public void accessibleSelectParentHiddenRoot() {
		tree.setRootVisible(false);

		selectOnlyChild(tree, NodeName.PARENT);

		Assert.assertArrayEquals(new Object[] { root, parent }, tree.getSelectionPath().getPath());
	}

	@Test
	public void accessibleSelectChildVisibleRoot() {
		tree.setRootVisible(true);

		Accessible aRoot = getOnlyChild(tree, NodeName.ROOT);
		Accessible aParent = getOnlyChild(aRoot, NodeName.PARENT);
		selectOnlyChild(aParent, NodeName.CHILD);

		Assert.assertArrayEquals(new Object[] { root, parent, child }, tree.getSelectionPath().getPath());
	}

	@Test
	public void accessibleSelectChildHiddenRoot() {
		tree.setRootVisible(false);

		Accessible aParent = getOnlyChild(tree, NodeName.PARENT);
		selectOnlyChild(aParent, NodeName.CHILD);

		Assert.assertArrayEquals(new Object[] { root, parent, child }, tree.getSelectionPath().getPath());
	}
}