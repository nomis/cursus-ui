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
import java.util.Collections;
import java.util.List;

import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.EventDAO;
import eu.lp0.cursus.db.dao.RaceDAO;
import eu.lp0.cursus.db.dao.SeriesDAO;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceEntity;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.ui.component.AbstractTree;
import eu.lp0.cursus.ui.component.DatabaseSync;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.menu.DatabasePopupMenu;
import eu.lp0.cursus.ui.menu.EventPopupMenu;
import eu.lp0.cursus.ui.menu.RacePopupMenu;
import eu.lp0.cursus.ui.menu.SeriesPopupMenu;
import eu.lp0.cursus.util.Background;

public class RaceTree<O extends Frame & DatabaseWindow> extends AbstractTree<O, DatabaseTreeNode, RaceEntity> implements DatabaseSync {
	private static final SeriesDAO seriesDAO = new SeriesDAO();
	private static final EventDAO eventDAO = new EventDAO();
	private static final RaceDAO raceDAO = new RaceDAO();

	public RaceTree(O win) {
		super(win, new DatabaseTreeNode());
	}

	@Override
	protected RaceEntity userObjectFromPathComponent(Object component) {
		return raceEntityFromPathComponent(component);
	}

	public static RaceEntity raceEntityFromPathComponent(Object component) {
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

	@Override
	protected JPopupMenu menuFromUserObject(RaceEntity item) {
		if (item instanceof Series) {
			return new SeriesPopupMenu<O>(win, (Series)item);
		} else if (item instanceof Event) {
			return new EventPopupMenu<O>(win, (Event)item);
		} else if (item instanceof Race) {
			return new RacePopupMenu<O>(win, (Race)item);
		} else if (item == null) {
			return new DatabasePopupMenu<O>(win);
		} else {
			return null;
		}
	}

	@Override
	public void databaseRefresh() {
		assert (Background.isExecutorThread());

		final List<Series> seriesList;

		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			seriesList = seriesDAO.findAll();
			for (Series series : seriesList) {
				for (Event event : series.getEvents()) {
					event.getRaces();
				}
			}

			DatabaseSession.commit();

			for (Series series : seriesList) {
				for (Event event : series.getEvents()) {
					for (Race race : event.getRaces()) {
						raceDAO.detach(race);
					}
					eventDAO.detach(event);
				}
				seriesDAO.detach(series);
			}
		} finally {
			win.getDatabase().endSession();
		}

		Collections.sort(seriesList);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				updateModel(seriesList);
			}
		});
	}

	@Override
	public void databaseClosed() {
		assert (Background.isExecutorThread());

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				updateModel(null);
			}
		});
	}

	private void updateModel(List<Series> list) {
		assert (SwingUtilities.isEventDispatchThread());

		root.updateTree(this, new TreePath(root), list);
	}
}