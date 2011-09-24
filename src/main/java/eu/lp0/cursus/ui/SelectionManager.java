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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	private final List<Component> ordering = new ArrayList<Component>();
	private Map<Class<? extends RaceHierarchy>, AbstractDatabaseTab<? extends RaceHierarchy>> previousTabs = new HashMap<Class<? extends RaceHierarchy>, AbstractDatabaseTab<? extends RaceHierarchy>>();

	public SelectionManager(JTree tree, JTabbedPane tabbedPane, List<AbstractDatabaseTab<Series>> seriesTabs, List<AbstractDatabaseTab<Event>> eventTabs,
			List<AbstractDatabaseTab<Race>> raceTabs) {
		this.tree = tree;
		this.tabbedPane = tabbedPane;
		this.seriesTabs = seriesTabs;
		this.eventTabs = eventTabs;
		this.raceTabs = raceTabs;

		ordering.addAll(seriesTabs);
		assert (Collections.disjoint(ordering, eventTabs));
		ordering.addAll(eventTabs);
		assert (Collections.disjoint(ordering, raceTabs));
		ordering.addAll(raceTabs);

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

	public void showSelected(final RaceHierarchy item) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (log.isTraceEnabled()) {
					if (item != null) {
						log.trace("Selecting " + item.getClass().getSimpleName() + ": " + item.getName()); //$NON-NLS-1$ //$NON-NLS-2$
					} else {
						log.trace("Deselected current item"); //$NON-NLS-1$
					}
				}

				updateTabs(item);
			}
		});
	}

	private Collection<?> getVisibleTabsFor(RaceHierarchy item) {
		if (item instanceof Series) {
			return seriesTabs;
		} else if (item instanceof Event) {
			return eventTabs;
		} else if (item instanceof Race) {
			return raceTabs;
		} else {
			return Collections.emptyList();
		}
	}

	/*
	 * Update the visible tabs, taking care not to cause the tabbed pane to select a different tab while removing/adding tabs
	 */
	private void updateTabs(RaceHierarchy item) {
		Class<? extends RaceHierarchy> clazz = (item != null ? item.getClass() : null);
		@SuppressWarnings("unchecked")
		AbstractDatabaseTab<? extends RaceHierarchy> selectedTab = (AbstractDatabaseTab<? extends RaceHierarchy>)tabbedPane.getSelectedComponent();
		AbstractDatabaseTab<? extends RaceHierarchy> previousTab = previousTabs.get(clazz);
		@SuppressWarnings("unchecked")
		Collection<AbstractDatabaseTab<? extends RaceHierarchy>> targetTabs = (Collection<AbstractDatabaseTab<? extends RaceHierarchy>>)getVisibleTabsFor(item);
		Set<Component> currentTabs = new LinkedHashSet<Component>();

		// Get the current tabs
		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			Component c = tabbedPane.getComponentAt(i);
			currentTabs.add(c);
		}

		if (currentTabs.equals(targetTabs)) {
			// Nothing to do
			return;
		}

		// Save current tab
		if (selectedTab != null) {
			if (log.isTraceEnabled()) {
				log.trace("Saving current tab: " + selectedTab.getClass().getSimpleName()); //$NON-NLS-1$
			}

			previousTabs.put(selectedTab.getType(), selectedTab);
		}

		// Remove all the tabs that aren't selected
		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			Component c = tabbedPane.getComponentAt(i);
			if (selectedTab != c) {
				tabbedPane.remove(i--);
			}
		}

		// Remove the selected tab
		if (selectedTab != null) {
			tabbedPane.remove(selectedTab);
		}

		// At this point there should be no tabs
		assert (tabbedPane.getTabCount() == 0);

		// Add the previously selected tab
		if (previousTab != null && targetTabs.contains(previousTab)) {
			if (log.isTraceEnabled()) {
				log.trace("Restoring previous tab: " + previousTab.getClass().getSimpleName()); //$NON-NLS-1$
			}

			previousTab.addToTabbedPane(tabbedPane, 0);
			// This tab will now be selected
		}

		// Add all the tabs that should be there
		Iterator<AbstractDatabaseTab<? extends RaceHierarchy>> iter = targetTabs.iterator();
		for (int i = 0; iter.hasNext(); i++) {
			AbstractDatabaseTab<? extends RaceHierarchy> tab = iter.next();
			if (tab != previousTab) {
				tab.addToTabbedPane(tabbedPane, i);
			}
		}
	}
}