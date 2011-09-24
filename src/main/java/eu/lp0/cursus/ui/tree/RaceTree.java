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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class RaceTree extends JTree {
	private JPopupMenu series = new SeriesTreePopupMenu();
	private JPopupMenu event = new EventTreePopupMenu();
	private JPopupMenu race = new RaceTreePopupMenu();

	public RaceTree() {
		super(new DefaultTreeModel(new DatabaseTreeNode()));
		setRootVisible(false);
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				if (me.isPopupTrigger()) {
					showMenu(me);
				}
			}

			@Override
			public void mouseReleased(MouseEvent me) {
				if (me.isPopupTrigger()) {
					showMenu(me);
				}
			}

			private JPopupMenu menuFromSelection(Object component) {
				if (component instanceof SeriesTreeNode) {
					return series;
				} else if (component instanceof EventTreeNode) {
					return event;
				} else if (component instanceof RaceTreeNode) {
					return race;
				} else {
					return null;
				}
			}

			private TreePath ensureSelection(MouseEvent me) {
				TreePath path = getPathForLocation(me.getX(), me.getY());

				if (getSelectionPath() != path) {
					setSelectionPath(path);
				}

				return path;
			}

			private void showMenu(MouseEvent me) {
				TreePath path = ensureSelection(me);

				if (path != null) {
					menuFromSelection(path.getLastPathComponent()).show(me.getComponent(), me.getX(), me.getY());
				}
			}
		});
	}
}