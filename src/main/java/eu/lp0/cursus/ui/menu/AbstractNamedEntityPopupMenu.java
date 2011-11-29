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
package eu.lp0.cursus.ui.menu;

import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.data.AbstractEntity;
import eu.lp0.cursus.db.data.NamedEntity;
import eu.lp0.cursus.i18n.Messages;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.util.Background;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.util.DatabaseError;

public abstract class AbstractNamedEntityPopupMenu<T extends AbstractEntity & NamedEntity> extends JPopupMenu {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	protected final DatabaseWindow win;
	protected final T item;

	public AbstractNamedEntityPopupMenu(DatabaseWindow win, T item) {
		this.win = win;
		this.item = item;
	}

	protected void confirmDelete(String action) {
		switch (JOptionPane.showConfirmDialog(win.getFrame(), Messages.getString(action + ".confirm", item.getName()), //$NON-NLS-1$
				Messages.getString(action) + Constants.EN_DASH + item.getName(), JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
		case JOptionPane.YES_OPTION:
			doDelete(action);
			break;
		case JOptionPane.NO_OPTION:
		case JOptionPane.CLOSED_OPTION:
		default:
			break;
		}
	}

	private void doDelete(final String action) {
		Background.execute(new Runnable() {
			@Override
			public void run() {
				win.getDatabase().startSession();
				try {
					DatabaseSession.begin();
					doDelete();
					DatabaseSession.commit();
				} catch (PersistenceException e) {
					log.error("Unable to delete", e); //$NON-NLS-1$
					DatabaseError.errorSaving(win.getFrame(), Messages.getString(action) + Constants.EN_DASH + item.getName(), e);
					return;
				} finally {
					win.getDatabase().endSession();
				}

				doRefresh();
			}
		});
	}

	protected abstract void doDelete();

	protected abstract void doRefresh();
}