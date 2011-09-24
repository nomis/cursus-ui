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
package eu.lp0.cursus.ui.tree;

import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class RaceTree extends JTree implements MouseListener {
	private final Frame owner;

	public RaceTree(Frame owner) {
		super(new DefaultTreeModel(new DatabaseTreeNode()));

		this.owner = owner;

		setRootVisible(false);
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent me) {
		ensureSelection(me);
		if (me.isPopupTrigger()) {
			showMenu(me);
		}
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		ensureSelection(me);
		if (me.isPopupTrigger()) {
			showMenu(me);
		}
	}

	private JPopupMenu menuFromSelection(Object component) {
		if (component instanceof SeriesTreeNode) {
			return new SeriesTreePopupMenu(owner, ((SeriesTreeNode)component).getUserObject());
		} else if (component instanceof EventTreeNode) {
			return new EventTreePopupMenu(owner, ((EventTreeNode)component).getUserObject());
		} else if (component instanceof RaceTreeNode) {
			return new RaceTreePopupMenu(owner, ((RaceTreeNode)component).getUserObject());
		} else {
			return null;
		}
	}

	private void ensureSelection(MouseEvent me) {
		TreePath path = getPathForLocation(me.getX(), me.getY());

		if (getSelectionPath() != path) {
			if (path == null || me.isPopupTrigger()) {
				setSelectionPath(path);
			}
		}
	}

	private void showMenu(MouseEvent me) {
		TreePath path = getSelectionPath();

		if (path != null) {
			menuFromSelection(path.getLastPathComponent()).show(me.getComponent(), me.getX(), me.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}