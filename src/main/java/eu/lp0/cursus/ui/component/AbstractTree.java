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

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public abstract class AbstractTree<R extends TreeNode, T> extends JTree {
	protected final DatabaseWindow win;
	protected final R root;

	public AbstractTree(DatabaseWindow win, R root) {
		super();
		this.win = win;
		this.root = root;

		setModel(new DefaultTreeModel(root));

		setRootVisible(false);
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ke) {
				if (ke.isConsumed()) {
					return;
				}
				if (ke.getKeyCode() == KeyEvent.VK_CONTEXT_MENU) {
					showMenu(ke);
				}
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				if (me.isConsumed()) {
					return;
				}
				ensureSelection(me);
				if (me.isPopupTrigger()) {
					showMenu(me);
				}
			}

			@Override
			public void mouseReleased(MouseEvent me) {
				if (me.isConsumed()) {
					return;
				}
				ensureSelection(me);
				if (me.isPopupTrigger()) {
					showMenu(me);
				}
			}
		});
	}

	protected abstract T userObjectFromPathComponent(Object component);

	protected abstract JPopupMenu menuFromUserObject(T item);

	private void ensureSelection(MouseEvent me) {
		TreePath path = getPathForLocation(me.getX(), me.getY());

		if (getSelectionPath() != path) {
			if (path == null || me.isPopupTrigger()) {
				setSelectionPath(path);
			}
		}
	}

	private void showMenu(MouseEvent me) {
		showMenu(me.getX(), me.getY());
	}

	private void showMenu(KeyEvent ke) {
		Point mouse = getMousePosition();
		if (mouse != null) {
			showMenu((int)getMousePosition().getX(), (int)getMousePosition().getY());
		} else {
			showMenu(0, 0);
		}
	}

	private void showMenu(int x, int y) {
		TreePath path = getSelectionPath();
		JPopupMenu menu = null;

		if (path != null) {
			menu = menuFromUserObject(userObjectFromPathComponent(path.getLastPathComponent()));
		} else {
			menu = menuFromUserObject(null);
		}
		menu.show(this, x, y);
	}
}