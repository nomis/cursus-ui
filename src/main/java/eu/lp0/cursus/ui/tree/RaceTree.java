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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.EventDAO;
import eu.lp0.cursus.db.dao.RaceDAO;
import eu.lp0.cursus.db.dao.SeriesDAO;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceHierarchy;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.ui.component.DatabaseSync;
import eu.lp0.cursus.ui.component.DatabaseWindow;

public class RaceTree<O extends Frame & DatabaseWindow> extends JTree implements MouseListener, DatabaseSync {
	private final O win;
	private final DatabaseTreeNode root;

	private static final SeriesDAO seriesDAO = new SeriesDAO();
	private static final EventDAO eventDAO = new EventDAO();
	private static final RaceDAO raceDAO = new RaceDAO();

	public RaceTree(O win) {
		super();
		this.win = win;

		root = new DatabaseTreeNode();
		setModel(new DefaultTreeModel(root));

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

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public static RaceHierarchy userObjectFromSelection(Object component) {
		if (component instanceof SeriesTreeNode) {
			return ((SeriesTreeNode)component).getUserObject();
		} else if (component instanceof EventTreeNode) {
			return ((EventTreeNode)component).getUserObject();
		} else if (component instanceof RaceTreeNode) {
			return ((RaceTreeNode)component).getUserObject();
		} else {
			return null;
		}
	}

	private JPopupMenu menuFromUserObject(RaceHierarchy item) {
		if (item instanceof Series) {
			return new SeriesTreePopupMenu(win, (Series)item);
		} else if (item instanceof Event) {
			return new EventTreePopupMenu(win, (Event)item);
		} else if (item instanceof Race) {
			return new RaceTreePopupMenu(win, (Race)item);
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
			menuFromUserObject(userObjectFromSelection(path.getLastPathComponent())).show(me.getComponent(), me.getX(), me.getY());
		}
	}

	@Override
	public void databaseRefresh() {
		final List<Series> seriesList = new ArrayList<Series>();

		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			Series series = seriesDAO.findSingleton();
			for (Event event : series.getEvents()) {
				for (Race race : event.getRaces()) {
					raceDAO.detach(race);
				}
				eventDAO.detach(event);
			}
			seriesDAO.detach(series);
			seriesList.add(series);

			DatabaseSession.commit();
		} finally {
			win.getDatabase().endSession();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				updateModel(seriesList);
			}
		});
	}

	@Override
	public void databaseClosed() {
		updateModel(null);
	}

	private void updateModel(List<Series> seriesList) {
		root.updateTree(this, new TreePath(root), seriesList);
	}
}