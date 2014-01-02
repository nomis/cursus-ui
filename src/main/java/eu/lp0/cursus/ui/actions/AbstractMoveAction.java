/*
	cursus - Race series management program
	Copyright 2011, 2013-2014  Simon Arlott

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
package eu.lp0.cursus.ui.actions;

import java.awt.event.ActionEvent;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.data.NamedEntity;
import eu.lp0.cursus.ui.Constants;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.util.Background;
import eu.lp0.cursus.util.DatabaseError;

public abstract class AbstractMoveAction<T extends NamedEntity> extends AbstractTranslatedAction {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private final DatabaseWindow win;
	private final T item;

	public AbstractMoveAction(String messagesKey, boolean hasMnemonic, DatabaseWindow win, T item) {
		super(messagesKey, hasMnemonic);
		this.win = win;
		this.item = item;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		doMove();
	}

	private void doMove() {
		Background.execute(new Runnable() {
			@Override
			public void run() {
				boolean ok = false;

				win.getDatabase().startSession();
				try {
					DatabaseSession.begin();
					ok = doMove(item);
					DatabaseSession.commit();
				} catch (PersistenceException e) {
					log.error("Unable to move", e); //$NON-NLS-1$
					DatabaseError.errorSaving(win.getFrame(), getValue(NAME) + Constants.EN_DASH + item.getName(), e);
					return;
				} finally {
					win.getDatabase().endSession();
				}

				if (ok) {
					doRefresh(win);
				}
			}
		});
	}

	@SuppressWarnings("hiding")
	protected abstract boolean doMove(T item);

	@SuppressWarnings("hiding")
	protected abstract void doRefresh(DatabaseWindow win);
}