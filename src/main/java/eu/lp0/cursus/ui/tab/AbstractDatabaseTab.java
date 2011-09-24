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

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import eu.lp0.cursus.db.data.RaceHierarchy;
import eu.lp0.cursus.ui.component.DatabaseSync;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.util.Messages;

public abstract class AbstractDatabaseTab<T extends RaceHierarchy> extends JPanel implements DatabaseSync, DatabaseTabSync<T> {
	protected final DatabaseWindow win;
	private final TabManager<T> manager;

	private final String title;
	private final int mnemonic;

	public AbstractDatabaseTab(Class<T> clazz, DatabaseWindow win, String messagesKey) {
		this.win = win;
		this.manager = new TabManager<T>(win, this, clazz);

		this.title = Messages.getString(messagesKey);
		this.mnemonic = Messages.getKeyEvent(messagesKey);
	}

	public void addToTabbedPane(JTabbedPane tabbedPane) {
		tabbedPane.addTab(title, null, this, null);
		tabbedPane.setMnemonicAt(tabbedPane.getTabCount() - 1, mnemonic);
	}

	@Override
	public void databaseRefresh() {
		manager.databaseRefresh();
	}

	@Override
	public void databaseClosed() {
		manager.databaseClosed();
	}
}