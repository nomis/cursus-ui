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
package uk.uuid.lp0.cursus.ui;

import java.awt.Component;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.uuid.lp0.cursus.ui.component.DatabaseSync;
import uk.uuid.lp0.cursus.ui.component.DatabaseTabSync;
import uk.uuid.lp0.cursus.ui.component.DatabaseWindow;
import uk.uuid.lp0.cursus.util.Background;
import uk.uuid.cursus.db.data.RaceEntity;

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
		DatabaseTabSync<? extends RaceEntity> tab = (DatabaseTabSync<? extends RaceEntity>)ce.getChild();
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
		assert (SwingUtilities.isEventDispatchThread());

		@SuppressWarnings("unchecked")
		final DatabaseTabSync<? extends RaceEntity> tab = (DatabaseTabSync<? extends RaceEntity>)tabs.getSelectedComponent();

		if (tab != null) {
			log.trace("Selected tab: " + tab.getClass().getSimpleName()); //$NON-NLS-1$

			final RaceEntity selected = win.getSelected();
			if (isValidFor(tab, selected)) {
				Background.execute(new Runnable() {
					@Override
					public void run() {
						databaseRefresh(tab, selected);
					}
				});
			}
		}
	}

	private void invokeCloseTabLater(final DatabaseTabSync<? extends RaceEntity> tab) {
		assert (SwingUtilities.isEventDispatchThread());

		Background.execute(new Runnable() {
			@Override
			public void run() {
				databaseClosed(tab);
			}
		});
	}

	private boolean isValidFor(DatabaseTabSync<? extends RaceEntity> tab, RaceEntity selected) {
		return selected != null && tab.getType().isAssignableFrom(selected.getClass());
	}

	private <T extends RaceEntity> void databaseRefresh(DatabaseTabSync<T> tab, RaceEntity selected) {
		assert (Background.isExecutorThread());

		if (isValidFor(tab, selected)) {
			tab.tabRefresh(tab.getType().cast(selected));
		}
	}

	private <T extends RaceEntity> void databaseClosed(DatabaseTabSync<T> tab) {
		assert (Background.isExecutorThread());

		tab.tabClear();
	}

	public void tryRefreshTabLater(DatabaseTabSync<? extends RaceEntity> expectedTab) {
		assert (SwingUtilities.isEventDispatchThread());

		@SuppressWarnings("unchecked")
		final DatabaseTabSync<? extends RaceEntity> tab = (DatabaseTabSync<? extends RaceEntity>)tabs.getSelectedComponent();

		if (tab == expectedTab) {
			final RaceEntity selected = win.getSelected();
			if (isValidFor(tab, selected)) {
				Background.execute(new Runnable() {
					@Override
					public void run() {
						databaseRefresh(tab, selected);
					}
				});
			}
		}
	}

	@Override
	public void databaseRefresh() {
		assert (Background.isExecutorThread());

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				tryRefreshTabLater();
			}
		});
	}

	@Override
	public void databaseClosed() {
		assert (Background.isExecutorThread());

		SwingUtilities.invokeLater(new Runnable() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				for (int i = 0; i < tabs.getTabCount(); i++) {
					invokeCloseTabLater((DatabaseTabSync<? extends RaceEntity>)tabs.getComponentAt(i));
				}
			}
		});
	}
}