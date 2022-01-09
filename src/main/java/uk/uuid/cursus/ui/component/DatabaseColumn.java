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
package uk.uuid.cursus.ui.component;

import javax.persistence.PersistenceException;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.uuid.cursus.i18n.LocaleChangeEvent;
import uk.uuid.cursus.i18n.Messages;
import uk.uuid.cursus.ui.Constants;
import uk.uuid.cursus.util.DatabaseError;

import com.google.common.base.Objects;
import com.google.common.eventbus.Subscribe;

import uk.uuid.cursus.db.DatabaseSession;
import uk.uuid.cursus.db.dao.EntityDAO;
import uk.uuid.cursus.db.data.Entity;

public abstract class DatabaseColumn<T extends Entity, V> extends TableColumn implements DatabaseTableCellEditor.Column<T, V> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	protected final DatabaseWindow win;
	private final EntityDAO<T> dao;
	private final String messagesKey;
	private final boolean editable;

	public DatabaseColumn(String messagesKey) {
		assert (!Objects.equal(messagesKey, "")); //$NON-NLS-1$
		this.win = null;
		this.dao = null;
		this.messagesKey = messagesKey;
		this.editable = false;

		headerValue = getName();
	}

	public DatabaseColumn(String messagesKey, DatabaseWindow win, EntityDAO<T> dao) {
		assert (!Objects.equal(messagesKey, "")); //$NON-NLS-1$
		this.win = win;
		this.dao = dao;
		this.messagesKey = messagesKey;
		this.editable = true;

		headerValue = getName();
	}

	// Uses DatabaseTableModel's EventBus
	@Subscribe
	public final void updateHeaderValue(LocaleChangeEvent lce) {
		setHeaderValue(getName());
	}

	public final String getName() {
		if (messagesKey == null) {
			return ""; //$NON-NLS-1$
		}
		return Messages.getString(messagesKey);
	}

	public boolean isCellEditable(T row) {
		return editable;
	}

	public void setupModel(JTable table, DatabaseTableModel<T> model, TableRowSorter<? super TableModel> sorter) {

	}

	@Override
	public V loadValue(T row, boolean editing) {
		return getValue(row, editing);
	}

	@Override
	public boolean saveEditedValue(T row, V newValue) {
		assert (SwingUtilities.isEventDispatchThread());

		if (row == null) {
			return false;
		}

		V oldValue = getValue(row, true);
		if (Objects.equal(oldValue, newValue)) {
			return true;
		}

		win.getDatabase().startSession();
		try {
			boolean reload = false;
			DatabaseSession.begin();

			T item = dao.get(row);
			if (!setValue(item, newValue)) {
				log.error(String.format("Unable to update row=%s#%d, column=%s, oldValue=%s, newValue=%s", row.getClass().getSimpleName(), row.getId(), //$NON-NLS-1$
						getName(), oldValue, newValue));
				return false;
			}
			dao.persist(item);
			DatabaseSession.commit();

			dao.detach(item);
			if (!setValue(row, newValue)) {
				log.error(String.format("Unable to set row=%s#%d, column=%s, oldValue=%s, newValue=%s", row.getClass().getSimpleName(), row.getId(), //$NON-NLS-1$
						getName(), oldValue, newValue));
				reload = true;
			}

			if (reload) {
				win.reloadCurrentTabs();
			}
			return true;
		} catch (PersistenceException e) {
			log.error(String.format("Unable to save changes: row=%s#%d, column=%s, oldValue=%s, newValue=%s", row.getClass().getSimpleName(), row.getId(), //$NON-NLS-1$
					getName(), oldValue, newValue), e);
			win.reloadCurrentTabs();
			DatabaseError.errorSaving(win.getFrame(), Constants.APP_NAME, e);
			return false;
		} finally {
			win.getDatabase().endSession();
		}
	}

	protected abstract V getValue(T row, boolean editing);

	protected abstract boolean setValue(T row, V value);
}