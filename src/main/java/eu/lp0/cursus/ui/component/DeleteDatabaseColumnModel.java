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

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.persistence.PersistenceException;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.AbstractDAO;
import eu.lp0.cursus.db.data.AbstractEntity;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.util.DatabaseError;

public abstract class DeleteDatabaseColumnModel<T extends AbstractEntity> extends DatabaseColumnModel<T, String> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final AbstractDAO<T> dao;

	public DeleteDatabaseColumnModel(DatabaseWindow win, AbstractDAO<T> dao) {
		super("", win, dao); //$NON-NLS-1$
		this.dao = dao;
	}

	@Override
	public void setupModel(TableRowSorter<TableModel> sorter, TableColumn col) {
		super.setupModel(sorter, col);
		col.setMinWidth(25);
		col.setPreferredWidth(25);
		col.setMaxWidth(25);

		col.setHeaderRenderer(new HeaderRenderer());

		sorter.setSortable(col.getModelIndex(), false);
	}

	protected abstract T newRow();

	protected void addRow(DatabaseTableModel<T> model) {
		assert (SwingUtilities.isEventDispatchThread());
		T row = null;

		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			row = newRow();
			dao.persist(row);

			DatabaseSession.commit();
		} catch (PersistenceException e) {
			if (row != null) {
				log.error(String.format("Unable to save row: %s#%d", row.getClass().getSimpleName(), row.getId()), e); //$NON-NLS-1$
			}
			DatabaseError.errorSaving(win.getFrame(), Constants.APP_NAME, e);
		} finally {
			win.getDatabase().endSession();
		}

		model.addRow(row);
	}

	protected boolean deleteRow(T row) {
		assert (SwingUtilities.isEventDispatchThread());

		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			T item = dao.get(row);
			dao.remove(item);

			DatabaseSession.commit();
			return true;
		} catch (PersistenceException e) {
			log.error(String.format("Unable to delete row: row=%s#%d", row.getClass().getSimpleName(), row.getId()), e); //$NON-NLS-1$
			DatabaseError.errorSaving(win.getFrame(), Constants.APP_NAME, e);
			return false;
		} finally {
			win.getDatabase().endSession();
		}
	}

	private class HeaderRenderer extends DefaultTableCellRenderer implements ActionListener, MouseListener {
		private final CellJButton button = new CellJButton("+"); //$NON-NLS-1$
		private DatabaseTableModel<T> model;

		public HeaderRenderer() {
			// button.addMouseListener(new MouseAdapter() {
			// @Override
			// public void mouseExited(MouseEvent e) {
			// button.setEnabled(false);
			// button.setEnabled(true);
			// }
			// });
			button.addActionListener(this);
		}

		@Override
		@SuppressWarnings("unchecked")
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int vRow, int vCol) {
			if (model == null) {
				table.getTableHeader().addMouseListener(this);
				model = (DatabaseTableModel<T>)table.getModel();
			}
			button.setSelected(isSelected);
			button.setFocus(hasFocus);
			return button;
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			addRow(model);
		}

		@Override
		public void mouseEntered(MouseEvent me) {
			button.getModel().setArmed(true);
		}

		@Override
		public void mousePressed(MouseEvent me) {
			button.getModel().setPressed(true);
		}

		@Override
		public void mouseReleased(MouseEvent me) {
			button.getModel().setPressed(false);
		}

		@Override
		public void mouseClicked(MouseEvent me) {
		}

		@Override
		public void mouseExited(MouseEvent me) {
			button.getModel().setArmed(false);
		}
	}

	@Override
	protected TableCellRenderer createCellRenderer() {
		return new CellRenderer();
	}

	private class CellJButton extends JButton {
		private final Color fg = getForeground();
		private final Color bg = getBackground();
		private boolean focus;
		private boolean painting;

		public CellJButton(String text) {
			super(text);
		}

		public void setFocus(boolean focus) {
			this.focus = focus;
		}

		@Override
		public void paint(Graphics g) {
			painting = true;
			super.paint(g);
			painting = false;
		}

		@Override
		public boolean isFocusOwner() {
			return painting && focus || (isFocusable() && super.isFocusOwner());
		}

		public void setSelected(JTable table, boolean selected) {
			if (selected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(fg);
				setBackground(bg);
			}
		}

		@Override
		public void repaint(long tm, int x, int y, int width, int height) {
			if (focus) {
				super.repaint(tm, x, y, width, height);
			}
		}

		@Override
		public void repaint(Rectangle r) {
			if (focus) {
				super.repaint(r);
			}
		}

		@Override
		public void revalidate() {
		}

		@Override
		public void validate() {
		}
	}

	private class CellRenderer extends DefaultTableCellRenderer {
		private final CellJButton button = new CellJButton("-"); //$NON-NLS-1$

		public CellRenderer() {
			setOpaque(true);
			button.setFocusable(false);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int vRow, int vCol) {
			button.setSelected(table, isSelected);
			button.setFocus(hasFocus);
			return button;
		}
	}

	@Override
	protected boolean setValue(T row, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected TableCellEditor createCellEditor() {
		return new CellEditor();
	}

	private class CellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
		private final CellJButton button = new CellJButton("-"); //$NON-NLS-1$
		private DatabaseTableModel<T> model;
		private Integer mRow;
		private T mVal;

		public CellEditor() {
			button.setFocus(true);
			button.addActionListener(this);
		}

		@Override
		@SuppressWarnings("unchecked")
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int vRow, int vCol) {
			model = (DatabaseTableModel<T>)table.getModel();
			mRow = table.convertRowIndexToModel(vRow);
			mVal = (T)value;
			return button;
		}

		@Override
		public Object getCellEditorValue() {
			return mVal;
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (deleteRow(mVal)) {
				super.stopCellEditing();
				model.deleteRow(mRow);
			}
		}

		@Override
		public void cancelCellEditing() {
			super.cancelCellEditing();
			model = null;
			mRow = null;
			mVal = null;
		}

		@Override
		public boolean stopCellEditing() {
			try {
				return super.stopCellEditing();
			} finally {
				model = null;
				mRow = null;
				mVal = null;
			}
		}
	}
}