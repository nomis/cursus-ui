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

import java.awt.Frame;

import javax.persistence.PersistenceException;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.AbstractDAO;
import eu.lp0.cursus.db.data.AbstractEntity;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.util.DatabaseError;

public abstract class DatabaseColumnModel<T extends AbstractEntity, V, O extends Frame & DatabaseWindow> implements DatabaseTableCellEditor.CellSaver<T, V> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final O win;
	private final AbstractDAO<T> dao;
	private final boolean editable;

	public DatabaseColumnModel() {
		this.win = null;
		this.dao = null;
		this.editable = false;
	}

	public DatabaseColumnModel(O win, AbstractDAO<T> dao) {
		this.win = win;
		this.dao = dao;
		this.editable = true;
	}

	public final boolean isCellEditable() {
		return editable;
	}

	public void setupModel(TableColumn col) {
		col.setCellRenderer(createCellRenderer());
		if (isCellEditable()) {
			col.setCellEditor(createCellEditor());
		}
	}

	@Override
	public boolean saveEditedValue(T row, V newValue) {
		assert (SwingUtilities.isEventDispatchThread());

		V oldValue = getValue(row);
		if (Objects.equal(oldValue, newValue)) {
			return true;
		}

		boolean revert = false;
		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			T item = dao.get(row);
			if (!setValue(item, newValue)) {
				revert = true;
				log.error(String.format("Unable to update row=%s#%d, column=%s, oldValue=%s, newValue=%s", row.getClass().getSimpleName(), row.getId(), //$NON-NLS-1$
						getColumnName(), oldValue, newValue));
				return false;
			}
			dao.persist(item);

			DatabaseSession.commit();
		} catch (PersistenceException e) {
			revert = true;
			log.error(String.format("Unable to save changes: row=%s#%d, column=%s, oldValue=%s, newValue=%s", row.getClass().getSimpleName(), row.getId(), //$NON-NLS-1$
					getColumnName(), oldValue, newValue), e);
			DatabaseError.errorSaving(win, Constants.APP_NAME, e);
			return false;
		} finally {
			if (revert && !setValue(row, oldValue)) {
				log.error(String.format("Unable to revert row=%s#%d, column=%s, oldValue=%s, newValue=%s", row.getClass().getSimpleName(), row.getId(), //$NON-NLS-1$
						getColumnName(), oldValue, newValue));
			}

			win.getDatabase().endSession();
		}

		if (!setValue(row, newValue)) {
			log.error(String.format("Unable to set row=%s#%d, column=%s, oldValue=%s, newValue=%s", row.getClass().getSimpleName(), row.getId(), //$NON-NLS-1$
					getColumnName(), oldValue, newValue));
			return false;
		}
		return true;
	}

	public abstract String getColumnName();

	protected abstract V getValue(T row);

	protected abstract TableCellRenderer createCellRenderer();

	protected abstract boolean setValue(T row, V value);

	protected abstract TableCellEditor createCellEditor();
}