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
package eu.lp0.cursus.ui.tab;

import java.awt.Component;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lp0.cursus.db.data.RaceHierarchy;
import eu.lp0.cursus.ui.component.DatabaseSync;
import eu.lp0.cursus.ui.component.DatabaseWindow;

public class TabManager<T extends RaceHierarchy> implements DatabaseSync, HierarchyListener, ChangeListener {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private final DatabaseWindow win;
	private final DatabaseTabSync<T> tab;
	private final Class<T> clazz;
	private Component previous = null;

	public TabManager(DatabaseWindow win, DatabaseTabSync<T> tab, Class<T> clazz) {
		this.win = win;
		this.tab = tab;
		this.clazz = clazz;

		tab.addHierarchyListener(this);
	}

	@Override
	public void hierarchyChanged(HierarchyEvent he) {
		if ((he.getChangeFlags() & HierarchyEvent.PARENT_CHANGED) != 0) {
			JTabbedPane tabs = (JTabbedPane)he.getChangedParent();
			if (tab.getParent() != null) {
				tabs.addChangeListener(this);
			} else {
				tabs.removeChangeListener(this);
				invokeCloseTabLater();
			}
		}
	}

	@Override
	public void stateChanged(final ChangeEvent ce) {
		JTabbedPane tabs = (JTabbedPane)ce.getSource();
		try {
			if (previous == tabs.getSelectedComponent()) {
				return;
			}
			tryRefreshTabLater(tabs);
		} finally {
			previous = tabs.getSelectedComponent();
		}
	}

	private void tryRefreshTabLater(JTabbedPane tabs) {
		if (tabs.getSelectedComponent() == tab) {
			log.trace("Selected tab: " + tab.getClass().getSimpleName()); //$NON-NLS-1$

			RaceHierarchy selected = win.getSelected();
			if (isValid(selected)) {
				win.execute(new Runnable() {
					@Override
					public void run() {
						databaseRefresh();
					}
				});
			}
		}
	}

	private void invokeCloseTabLater() {
		win.execute(new Runnable() {
			@Override
			public void run() {
				databaseClosed();
			}
		});
	}

	private boolean isValid(RaceHierarchy selected) {
		return selected != null && clazz.isAssignableFrom(selected.getClass());
	}

	public void databaseRefresh() {
		JTabbedPane tabs = (JTabbedPane)tab.getParent();
		if (tabs != null && tabs.getSelectedComponent() == tab) {
			RaceHierarchy selected = win.getSelected();
			if (isValid(selected)) {
				tab.tabRefresh(clazz.cast(selected));
			}
		}
	}

	public void databaseClosed() {
		tab.tabClear();
	}
}