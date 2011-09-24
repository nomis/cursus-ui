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
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lp0.cursus.db.data.RaceHierarchy;
import eu.lp0.cursus.ui.component.DatabaseSync;
import eu.lp0.cursus.ui.component.DatabaseTabSync;
import eu.lp0.cursus.ui.component.DatabaseWindow;

public class SelectedTabManager implements DatabaseSync, ContainerListener, ChangeListener {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private final DatabaseWindow win;
	private final JTabbedPane tabs;
	private Component previous = null;

	public SelectedTabManager(DatabaseWindow win, JTabbedPane tabs) {
		this.win = win;
		this.tabs = tabs;

		tabs.addContainerListener(this);
		tabs.addChangeListener(this);
	}

	@Override
	public void componentAdded(ContainerEvent ce) {
	}

	@Override
	public void componentRemoved(ContainerEvent ce) {
		@SuppressWarnings("unchecked")
		DatabaseTabSync<? extends RaceHierarchy> tab = (DatabaseTabSync<? extends RaceHierarchy>)ce.getChild();
		invokeCloseTabLater(tab);
	}

	@Override
	public void stateChanged(final ChangeEvent ce) {
		try {
			if (previous == tabs.getSelectedComponent()) {
				return;
			}
			tryRefreshTabLater();
		} finally {
			previous = tabs.getSelectedComponent();
		}
	}

	private void tryRefreshTabLater() {
		@SuppressWarnings("unchecked")
		final DatabaseTabSync<? extends RaceHierarchy> tab = (DatabaseTabSync<? extends RaceHierarchy>)tabs.getSelectedComponent();

		if (tab != null) {
			log.trace("Selected tab: " + tab.getClass().getSimpleName()); //$NON-NLS-1$

			RaceHierarchy selected = win.getSelected();
			if (isValidFor(tab, selected)) {
				win.execute(new Runnable() {
					@Override
					public void run() {
						databaseRefresh(tab);
					}
				});
			}
		}
	}

	private void invokeCloseTabLater(final DatabaseTabSync<? extends RaceHierarchy> tab) {
		win.execute(new Runnable() {
			@Override
			public void run() {
				databaseClosed(tab);
			}
		});
	}

	private boolean isValidFor(DatabaseTabSync<? extends RaceHierarchy> tab, RaceHierarchy selected) {
		return selected != null && tab.getType().isAssignableFrom(selected.getClass());
	}

	public <T extends RaceHierarchy> void databaseRefresh(DatabaseTabSync<T> tab) {
		RaceHierarchy selected = win.getSelected();
		if (isValidFor(tab, selected)) {
			tab.tabRefresh(tab.getType().cast(selected));
		}
	}

	public <T extends RaceHierarchy> void databaseClosed(DatabaseTabSync<T> tab) {
		tab.tabClear();
	}

	@Override
	public void databaseRefresh() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				tryRefreshTabLater();
			}
		});
	}

	@Override
	public void databaseClosed() {
		SwingUtilities.invokeLater(new Runnable() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				for (int i = 0; i < tabs.getTabCount(); i++) {
					invokeCloseTabLater((DatabaseTabSync<? extends RaceHierarchy>)tabs.getComponentAt(i));
				}
			}
		});
	}
}