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
package eu.lp0.cursus.ui.util;

import java.util.Hashtable;
import java.util.Vector;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleSelection;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

/**
 * Workaround bug #7115912 by wrapping AccessibleSelection
 * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=7115912
 */
public class AccessibleJTreeFix extends JTree {
	private static final Logger log = LoggerFactory.getLogger(AccessibleJTreeFix.class);
	private static final Supplier<Boolean> WORKAROUND_REQUIRED = Suppliers.memoize(new Supplier<Boolean>() {
		@Override
		public Boolean get() {
			// For a JTree with an invisible root
			JTree tree = new JTree(new DefaultTreeModel(new DefaultMutableTreeNode()));
			tree.setRootVisible(false);

			// There are no accessible children
			AccessibleContext context = tree.getAccessibleContext();
			assert (context.getAccessibleChildrenCount() == 0);

			// If we select all the accessible children
			AccessibleSelection select = context.getAccessibleSelection();
			select.selectAllAccessibleSelection();

			// Then there should be 0 selected children
			if (select.getAccessibleSelectionCount() != 0) {
				log.debug("Applying workaround for Sun Java bug #7115912"); //$NON-NLS-1$
				return true;
			} else {
				return false;
			}
		}
	});

	public AccessibleJTreeFix() {
		super();
	}

	public AccessibleJTreeFix(Hashtable<?, ?> value) {
		super(value);
	}

	public AccessibleJTreeFix(Object[] value) {
		super(value);
	}

	public AccessibleJTreeFix(TreeModel newModel) {
		super(newModel);
	}

	public AccessibleJTreeFix(TreeNode root, boolean asksAllowsChildren) {
		super(root, asksAllowsChildren);
	}

	public AccessibleJTreeFix(TreeNode root) {
		super(root);
	}

	public AccessibleJTreeFix(Vector<?> value) {
		super(value);
	}

	@Override
	public AccessibleContext getAccessibleContext() {
		if (!WORKAROUND_REQUIRED.get()) {
			return super.getAccessibleContext();
		}

		final AccessibleContext superAccessibleContext = super.getAccessibleContext();
		return new ForwardingAccessibleContext() {
			@Override
			protected AccessibleContext delegate() {
				return superAccessibleContext;
			}

			@Override
			public AccessibleSelection getAccessibleSelection() {
				final AccessibleSelection superAccessibleSelection = super.getAccessibleSelection();
				return new AccessibleSelection() {
					@Override
					public int getAccessibleSelectionCount() {
						if (isRootVisible()) {
							return superAccessibleSelection.getAccessibleSelectionCount();
						} else {
							TreeModel model = getModel();
							if (model == null) {
								return 0;
							}

							Object root = model.getRoot();
							int count = 0;
							int childCount = model.getChildCount(root);
							for (int i = 0; i < childCount; i++) {
								if (isPathSelected(new TreePath(new Object[] { root, model.getChild(root, i) }))) {
									count++;
								}
							}
							return count;
						}
					}

					@Override
					public Accessible getAccessibleSelection(int i) {
						if (isRootVisible()) {
							return superAccessibleSelection.getAccessibleSelection(i);
						} else {
							TreeModel model = getModel();
							if (model == null) {
								return null;
							}

							Object root = model.getRoot();
							int count = 0;
							int childCount = model.getChildCount(root);
							for (int j = 0; j < childCount; j++) {
								if (isPathSelected(new TreePath(new Object[] { root, model.getChild(root, j) }))) {
									if (count == i) {
										return superAccessibleContext.getAccessibleChild(j);
									} else {
										count++;
									}
								}
							}
							return null;
						}
					}

					@Override
					public boolean isAccessibleChildSelected(int i) {
						if (isRootVisible()) {
							return superAccessibleSelection.isAccessibleChildSelected(i);
						} else {
							TreeModel model = getModel();
							if (model == null) {
								return false;
							}

							Object root = model.getRoot();
							if (i >= 0 && i < model.getChildCount(root)) {
								return isPathSelected(new TreePath(new Object[] { root, model.getChild(root, i) }));
							} else {
								return false;
							}
						}
					}

					@Override
					public void addAccessibleSelection(int i) {
						if (isRootVisible()) {
							superAccessibleSelection.addAccessibleSelection(i);
						} else {
							TreeModel model = getModel();
							if (model == null) {
								return;
							}

							Object root = model.getRoot();
							if (i >= 0 && i < model.getChildCount(root)) {
								addSelectionPath(new TreePath(new Object[] { root, model.getChild(root, i) }));
							}
						}
					}

					@Override
					public void removeAccessibleSelection(int i) {
						if (isRootVisible()) {
							superAccessibleSelection.removeAccessibleSelection(i);
						} else {
							TreeModel model = getModel();
							if (model == null) {
								return;
							}

							Object root = model.getRoot();
							if (i >= 0 && i < model.getChildCount(root)) {
								removeSelectionPath(new TreePath(new Object[] { root, model.getChild(root, i) }));
							}
						}
					}

					@Override
					public void clearAccessibleSelection() {
						if (isRootVisible()) {
							superAccessibleSelection.clearAccessibleSelection();
						} else {
							TreeModel model = getModel();
							if (model == null) {
								return;
							}

							Object root = model.getRoot();
							int childCount = model.getChildCount(root);
							for (int i = 0; i < childCount; i++) {
								removeSelectionPath(new TreePath(new Object[] { root, model.getChild(root, i) }));
							}
						}
					}

					@Override
					public void selectAllAccessibleSelection() {
						if (isRootVisible()) {
							superAccessibleSelection.selectAllAccessibleSelection();
						} else {
							TreeModel model = getModel();
							if (model == null) {
								return;
							}

							Object root = model.getRoot();
							int childCount = model.getChildCount(root);
							for (int i = 0; i < childCount; i++) {
								addSelectionPath(new TreePath(new Object[] { root, model.getChild(root, i) }));
							}
						}
					}
				};
			}
		};
	}
}