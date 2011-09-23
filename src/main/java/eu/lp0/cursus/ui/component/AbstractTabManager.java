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
package eu.lp0.cursus.ui.component;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import eu.lp0.cursus.ui.MainWindow;

public abstract class AbstractTabManager {
	protected final MainWindow win;
	protected final JTabbedPane tabs;
	protected final JPanel tab;

	protected AbstractTabManager(MainWindow win, JTabbedPane tabs, JPanel tab) {
		this.win = win;
		this.tabs = tabs;
		this.tab = tab;

		tabs.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				AbstractTabManager.this.win.execute(new Runnable() {
					@Override
					public void run() {
						if (AbstractTabManager.this.tabs.getSelectedComponent() == AbstractTabManager.this.tab) {
							if (AbstractTabManager.this.win.getMain().isOpen()) {
								tabSelected();
							}
						}
					}
				});
			}
		});
	}

	protected abstract void tabSelected();

	protected abstract void databaseClosed();

	public void syncGUI(boolean open) {
		if (open) {
			if (tabs.getSelectedComponent() == tab) {
				tabSelected();
			}
		} else {
			databaseClosed();
		}
	}
}