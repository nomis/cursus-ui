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
package uk.uuid.cursus.ui.tree;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import uk.uuid.cursus.ui.actions.DeleteClassAction;
import uk.uuid.cursus.ui.actions.NewClassAction;
import uk.uuid.cursus.ui.component.AbstractTree;
import uk.uuid.cursus.ui.component.DatabaseWindow;
import uk.uuid.cursus.ui.menu.ClassPopupMenu;
import uk.uuid.cursus.ui.menu.ClassesPopupMenu;
import uk.uuid.cursus.ui.series.SeriesClassesTab;
import uk.uuid.cursus.db.data.Class;
import uk.uuid.cursus.db.data.Series;

public class ClassTree extends AbstractTree<ClassListTreeNode, Class> {
	private final SeriesClassesTab tab;
	private Series currentSeries = null;

	public ClassTree(DatabaseWindow win, SeriesClassesTab tab) {
		super(win, new ClassListTreeNode());
		this.tab = tab;

		DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();
		render.setLeafIcon(null);
		setCellRenderer(render);
	}

	@Override
	protected Class userObjectFromPathComponent(Object component) {
		if (component instanceof ClassTreeNode) {
			return ((ClassTreeNode)component).getUserObject();
		} else {
			return null;
		}
	}

	@Override
	protected JPopupMenu menuFromUserObject(Class item) {
		if (item instanceof Class) {
			return new ClassPopupMenu(win, tab, item);
		} else if (item == null) {
			return new ClassesPopupMenu(win, tab, currentSeries);
		} else {
			return null;
		}
	}

	@Override
	protected void insertFromUserObject(Class item, ActionEvent ae) {
		if (item instanceof Class) {
			new NewClassAction(win, tab, item.getSeries()).actionPerformed(ae);
		} else if (item == null) {
			new NewClassAction(win, tab, currentSeries).actionPerformed(ae);
		}
	}

	@Override
	protected void deleteFromUserObject(Class item, ActionEvent ae) {
		if (item instanceof Class) {
			new DeleteClassAction(win, tab, item).actionPerformed(ae);
		}
	}

	public void updateModel(Series newSeries, List<Class> list) {
		assert (SwingUtilities.isEventDispatchThread());

		currentSeries = newSeries;
		root.updateTree(this, new TreePath(root), list);
	}
}