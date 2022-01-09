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

import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleSelection;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import uk.uuid.cursus.ui.util.AccessibleJTreeFix;

/**
 * JTree AccessibleSelection ignores isRootVisible()
 * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=7115912
 */
public class Bug7115912 {
	@Test
	@Ignore
	public void checkBug() {
		// For a JTree with an invisible root
		JTree tree = new JTree(new DefaultTreeModel(new DefaultMutableTreeNode()));
		tree.setRootVisible(false);

		// There are no accessible children
		AccessibleContext context = tree.getAccessibleContext();
		Assert.assertEquals(0, context.getAccessibleChildrenCount());

		// If we select all the accessible children
		AccessibleSelection select = context.getAccessibleSelection();
		select.selectAllAccessibleSelection();

		// Then there should be 0 selected children
		Assert.assertEquals(0, select.getAccessibleSelectionCount());
	}

	@Test
	public void checkWorkaround() {
		// For a JTree with an invisible root
		JTree tree = new AccessibleJTreeFix(new DefaultTreeModel(new DefaultMutableTreeNode()));
		tree.setRootVisible(false);

		// There are no accessible children
		AccessibleContext context = tree.getAccessibleContext();
		Assert.assertEquals(0, context.getAccessibleChildrenCount());

		// If we select all the accessible children
		AccessibleSelection select = context.getAccessibleSelection();
		select.selectAllAccessibleSelection();

		// Then there should be 0 selected children
		Assert.assertEquals(0, select.getAccessibleSelectionCount());
	}
}