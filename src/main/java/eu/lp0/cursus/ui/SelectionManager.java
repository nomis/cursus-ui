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
package eu.lp0.cursus.ui;

import java.awt.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceHierarchy;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.ui.tab.AbstractDatabaseTab;
import eu.lp0.cursus.ui.tree.RaceTree;

public class SelectionManager implements TreeSelectionListener {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final JTree tree;
	private final JTabbedPane tabbedPane;
	private final List<AbstractDatabaseTab<Series>> seriesTabs;
	private final List<AbstractDatabaseTab<Event>> eventTabs;
	private final List<AbstractDatabaseTab<Race>> raceTabs;
	private Map<Class<? extends RaceHierarchy>, AbstractDatabaseTab<? extends RaceHierarchy>> currentTabs = new HashMap<Class<? extends RaceHierarchy>, AbstractDatabaseTab<? extends RaceHierarchy>>();

	public SelectionManager(JTree tree, JTabbedPane tabbedPane, List<AbstractDatabaseTab<Series>> seriesTabs, List<AbstractDatabaseTab<Event>> eventTabs,
			List<AbstractDatabaseTab<Race>> raceTabs) {
		this.tree = tree;
		this.tabbedPane = tabbedPane;
		this.seriesTabs = seriesTabs;
		this.eventTabs = eventTabs;
		this.raceTabs = raceTabs;

		tree.addTreeSelectionListener(this);
	}

	@Override
	public void valueChanged(final TreeSelectionEvent tse) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (tse.isAddedPath()) {
					showSelected(RaceTree.userObjectFromSelection(tse.getPath().getLastPathComponent()));
				} else {
					showSelected(null);
				}
			}
		});
	}

	public RaceHierarchy getSelected() {
		TreePath path = tree.getSelectionPath();
		return path != null ? RaceTree.userObjectFromSelection(path.getLastPathComponent()) : null;
	}

	public void showSelected(RaceHierarchy item) {
		if (log.isTraceEnabled()) {
			if (item != null) {
				log.trace("Selecting " + item.getClass().getSimpleName() + ": " + item.getName()); //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				log.trace("Deselected current item"); //$NON-NLS-1$
			}
		}

		saveCurrentTab();
		showTabs(item);
		restorePreviousTab(item);
	}

	@SuppressWarnings("unchecked")
	private void saveCurrentTab() {
		Component selectedTab = tabbedPane.getSelectedComponent();
		if (selectedTab == null) {
			return;
		}

		if (log.isTraceEnabled()) {
			log.trace("Saving current tab: " + selectedTab.getClass().getSimpleName()); //$NON-NLS-1$
		}

		if (seriesTabs.contains(selectedTab)) {
			currentTabs.put(Series.class, (AbstractDatabaseTab<Series>)selectedTab);
		} else if (eventTabs.contains(selectedTab)) {
			currentTabs.put(Event.class, (AbstractDatabaseTab<Event>)selectedTab);
		} else if (raceTabs.contains(selectedTab)) {
			currentTabs.put(Race.class, (AbstractDatabaseTab<Race>)selectedTab);
		}
	}

	private void showTabs(RaceHierarchy item) {
		updateVisibility(seriesTabs, item instanceof Series);
		updateVisibility(eventTabs, item instanceof Event);
		updateVisibility(raceTabs, item instanceof Race);
	}

	private <T extends RaceHierarchy> void updateVisibility(final List<AbstractDatabaseTab<T>> tabs, final boolean visibility) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (AbstractDatabaseTab<T> tab : tabs) {
					if (visibility) {
						tab.addToTabbedPane(tabbedPane);
					} else {
						tabbedPane.remove(tab);
					}
				}
			}
		});
	}

	private void restorePreviousTab(RaceHierarchy item) {
		Class<? extends RaceHierarchy> clazz = item != null ? item.getClass() : null;
		final AbstractDatabaseTab<? extends RaceHierarchy> previousTab = currentTabs.get(clazz);
		if (previousTab == null) {
			return;
		}

		if (log.isTraceEnabled()) {
			log.trace("Restoring previous tab: " + previousTab.getClass().getSimpleName()); //$NON-NLS-1$
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				int index = tabbedPane.indexOfComponent(previousTab);
				if (index != -1) {
					tabbedPane.setSelectedIndex(index);
				}
			}
		});
	}
}