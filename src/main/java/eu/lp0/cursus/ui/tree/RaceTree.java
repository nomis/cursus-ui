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
package eu.lp0.cursus.ui.tree;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Ordering;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.EventDAO;
import eu.lp0.cursus.db.dao.RaceDAO;
import eu.lp0.cursus.db.dao.SeriesDAO;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceEntity;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.ui.actions.DeleteEventAction;
import eu.lp0.cursus.ui.actions.DeleteRaceAction;
import eu.lp0.cursus.ui.actions.DeleteSeriesAction;
import eu.lp0.cursus.ui.actions.MoveEventDownAction;
import eu.lp0.cursus.ui.actions.MoveEventUpAction;
import eu.lp0.cursus.ui.actions.MoveRaceDownAction;
import eu.lp0.cursus.ui.actions.MoveRaceUpAction;
import eu.lp0.cursus.ui.actions.NewEventAction;
import eu.lp0.cursus.ui.actions.NewRaceAction;
import eu.lp0.cursus.ui.actions.NewSeriesAction;
import eu.lp0.cursus.ui.component.AbstractTree;
import eu.lp0.cursus.ui.component.DatabaseSync;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.component.HierarchicalTreeNode;
import eu.lp0.cursus.ui.menu.DatabasePopupMenu;
import eu.lp0.cursus.ui.menu.EventPopupMenu;
import eu.lp0.cursus.ui.menu.RacePopupMenu;
import eu.lp0.cursus.ui.menu.SeriesPopupMenu;
import eu.lp0.cursus.util.Background;

public class RaceTree extends AbstractTree<DatabaseTreeNode, RaceEntity> implements DatabaseSync {
	public static final String COMMAND_MOVE_UP = "move entity up"; //$NON-NLS-1$
	public static final String COMMAND_MOVE_DOWN = "move entity down"; //$NON-NLS-1$

	private final Logger log = LoggerFactory.getLogger(getClass());

	private static final SeriesDAO seriesDAO = new SeriesDAO();
	private static final EventDAO eventDAO = new EventDAO();
	private static final RaceDAO raceDAO = new RaceDAO();

	public RaceTree(DatabaseWindow win) {
		super(win, new DatabaseTreeNode());

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ke) {
				if (ke.isConsumed()) {
					return;
				}
				if (ke.isAltDown() && ke.getKeyCode() == KeyEvent.VK_UP) {
					doMoveUp(getSelected());
					ke.consume();
				} else if (ke.isAltDown() && ke.getKeyCode() == KeyEvent.VK_DOWN) {
					doMoveDown(getSelected());
					ke.consume();
				}
			}
		});
	}

	@Override
	protected RaceEntity userObjectFromPathComponent(Object component) {
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
			return new SeriesPopupMenu(win, (Series)item);
		} else if (item instanceof Event) {
			return new EventPopupMenu(win, (Event)item);
		} else if (item instanceof Race) {
			return new RacePopupMenu(win, (Race)item);
		} else if (item == null) {
			return new DatabasePopupMenu(win);
		} else {
			return null;
		}
	}

	@Override
	protected void insertFromUserObject(RaceEntity item, ActionEvent ae) {
		if (item instanceof Series) {
			new NewEventAction(win, (Series)item).actionPerformed(ae);
		} else if (item instanceof Event) {
			new NewRaceAction(win, (Event)item).actionPerformed(ae);
		} else if (item instanceof Race) {
			new NewRaceAction(win, ((Race)item).getEvent()).actionPerformed(ae);
		} else if (item == null) {
			new NewSeriesAction(win).actionPerformed(ae);
		}
	}

	@Override
	protected void deleteFromUserObject(RaceEntity item, ActionEvent ae) {
		if (item instanceof Series) {
			new DeleteSeriesAction(win, (Series)item).actionPerformed(ae);
		} else if (item instanceof Event) {
			new DeleteEventAction(win, (Event)item).actionPerformed(ae);
		} else if (item instanceof Race) {
			new DeleteRaceAction(win, (Race)item).actionPerformed(ae);
		}
	}

	protected void doMoveUp(RaceEntity item) {
		ActionEvent ae = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, COMMAND_MOVE_UP);
		if (item instanceof Event) {
			new MoveEventUpAction(win, (Event)item).actionPerformed(ae);
		} else if (item instanceof Race) {
			new MoveRaceUpAction(win, (Race)item).actionPerformed(ae);
		}
	}

	protected void doMoveDown(RaceEntity item) {
		ActionEvent ae = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, COMMAND_MOVE_DOWN);
		if (item instanceof Event) {
			new MoveEventDownAction(win, (Event)item).actionPerformed(ae);
		} else if (item instanceof Race) {
			new MoveRaceDownAction(win, (Race)item).actionPerformed(ae);
		}
	}

	@Override
	public void databaseRefresh() {
		assert (Background.isExecutorThread());

		final List<Series> seriesList;

		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			seriesList = Ordering.natural().sortedCopy(seriesDAO.findAll());
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

		RaceEntity selected = getSelected();

		root.updateTree(this, new TreePath(root), list);

		if (selected != null && !selected.equals(getSelected())) {
			// Restore lost selection
			log.trace("Restoring selection to " + selected); //$NON-NLS-1$
			TreePath path = findPathTo(selected);
			if (path != null) {
				expandPath(path);
				addSelectionPath(path);
			} else {
				setSelectionPaths(new TreePath[0]);
			}
		}
	}

	private TreePath findPathTo(RaceEntity selected) {
		Series series = null;

		if (selected instanceof Series) {
			series = (Series)selected;
		} else if (selected instanceof Event) {
			series = ((Event)selected).getSeries();
		} else if (selected instanceof Race) {
			series = ((Race)selected).getEvent().getSeries();
		}
		assert (series != null);

		@SuppressWarnings("unchecked")
		Enumeration<HierarchicalTreeNode<Series>> en = root.children();
		while (en.hasMoreElements()) {
			HierarchicalTreeNode<Series> node = en.nextElement();
			if (node.getUserObject().equals(series)) {
				if (selected instanceof Series) {
					return new TreePath(new Object[] { root, node });
				} else {
					TreePath found = findPathFromSeriesTo(node, selected);
					if (found != null) {
						return new TreePath(ObjectArrays.concat(new Object[] { root, node }, found.getPath(), Object.class));
					}
				}
				break;
			}
		}

		return null;
	}

	private TreePath findPathFromSeriesTo(HierarchicalTreeNode<Series> series, RaceEntity selected) {
		@SuppressWarnings("unchecked")
		Enumeration<HierarchicalTreeNode<Event>> en = series.children();
		while (en.hasMoreElements()) {
			HierarchicalTreeNode<Event> node = en.nextElement();
			if (selected instanceof Event) {
				if (node.getUserObject().equals(selected)) {
					return new TreePath(node);
				}
			} else {
				// Race may have moved to another event so we need to check all of them
				TreeNode found = findNodeFromEventTo(node, selected);
				if (found != null) {
					return new TreePath(new Object[] { node, found });
				}
			}
		}

		return null;
	}

	private TreeNode findNodeFromEventTo(HierarchicalTreeNode<Event> event, RaceEntity selected) {
		@SuppressWarnings("unchecked")
		Enumeration<HierarchicalTreeNode<Race>> en = event.children();
		while (en.hasMoreElements()) {
			HierarchicalTreeNode<Race> node = en.nextElement();
			if (node.getUserObject().equals(selected)) {
				return node;
			}
		}

		return null;
	}
}