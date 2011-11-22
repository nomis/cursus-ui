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
import java.util.List;

import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import com.google.common.collect.Ordering;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.ClassDAO;
import eu.lp0.cursus.db.dao.SeriesDAO;
import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.ui.component.AbstractTree;
import eu.lp0.cursus.ui.component.DatabaseTabSync;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.menu.ClassPopupMenu;
import eu.lp0.cursus.ui.menu.ClassesPopupMenu;
import eu.lp0.cursus.ui.series.SeriesClassesTab;
import eu.lp0.cursus.util.Background;

public class ClassTree<O extends Frame & DatabaseWindow> extends AbstractTree<O, ClassListTreeNode, Class> implements DatabaseTabSync<Series> {
	private final SeriesClassesTab<O> tab;
	private Series currentSeries = null;

	private static final SeriesDAO seriesDAO = new SeriesDAO();
	private static final ClassDAO classDAO = new ClassDAO();

	public ClassTree(O win, SeriesClassesTab<O> tab) {
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
			return new ClassPopupMenu<O>(win, tab, item);
		} else if (item == null) {
			return new ClassesPopupMenu<O>(win, tab, currentSeries);
		} else {
			return null;
		}
	}

	@Override
	public void tabRefresh(Series series) {
		assert (Background.isExecutorThread());

		final Series newSeries;
		final List<Class> newClasses;

		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			newSeries = seriesDAO.get(series);
			newClasses = Ordering.natural().sortedCopy(newSeries.getClasses());
			DatabaseSession.commit();

			for (Class cls : newClasses) {
				classDAO.detach(cls);
			}
			seriesDAO.detach(newSeries);
		} finally {
			win.getDatabase().endSession();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				currentSeries = newSeries;
				updateModel(newClasses);
			}
		});
	}

	@Override
	public void tabClear() {
		assert (Background.isExecutorThread());

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				currentSeries = null;
				updateModel(null);
			}
		});
	}

	private void updateModel(List<Class> list) {
		assert (SwingUtilities.isEventDispatchThread());

		root.updateTree(this, new TreePath(root), list);
	}

	@Override
	public java.lang.Class<Series> getType() {
		return Series.class;
	}
}