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
package eu.lp0.cursus.ui.series;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.ClassDAO;
import eu.lp0.cursus.db.dao.SeriesDAO;
import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.ui.component.AbstractDatabaseTab;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.menu.ClassPopupMenu;
import eu.lp0.cursus.ui.menu.ClassesPopupMenu;

public class SeriesClassesTab<O extends Frame & DatabaseWindow> extends AbstractDatabaseTab<O, Series> {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private JSplitPane splitPane;
	private JScrollPane leftScrollPane;
	private JList list;
	private JScrollPane rightScrollPane;
	private JTable table;
	private Series currentSeries = null;

	private static final SeriesDAO seriesDAO = new SeriesDAO();
	private static final ClassDAO classDAO = new ClassDAO();

	public SeriesClassesTab(O win) {
		super(Series.class, win, "tab.classes"); //$NON-NLS-1$
		initialise();
	}

	private void initialise() {
		setLayout(new BorderLayout(0, 0));

		splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);

		leftScrollPane = new JScrollPane();
		leftScrollPane.setBorder(new EmptyBorder(0, 0, 0, 2));
		leftScrollPane.setPreferredSize(new Dimension(150, 0));
		splitPane.setLeftComponent(leftScrollPane);

		list = new JList(new DefaultListModel());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				if (me.isPopupTrigger()) {
					showMenu(me);
				}
			}

			@Override
			public void mouseReleased(MouseEvent me) {
				if (me.isPopupTrigger()) {
					showMenu(me);
				}
			}
		});
		leftScrollPane.setViewportView(list);

		rightScrollPane = new JScrollPane();
		rightScrollPane.setBorder(new EmptyBorder(0, 2, 0, 0));
		splitPane.setRightComponent(rightScrollPane);

		table = new JTable();
		rightScrollPane.setViewportView(table);
	}

	private void showMenu(MouseEvent me) {
		Class clazz = (Class)list.getSelectedValue();
		if (clazz != null) {
			new ClassPopupMenu<O>(win, this, clazz).show(me.getComponent(), me.getX(), me.getY());
		} else if (currentSeries != null) {
			new ClassesPopupMenu<O>(win, this, currentSeries).show(me.getComponent(), me.getX(), me.getY());
		}
	}

	@Override
	public void tabRefresh(Series series) {
		final List<Class> classes;
		final Series newSeries;

		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			newSeries = seriesDAO.get(series);
			classes = new ArrayList<Class>(newSeries.getClasses());
			DatabaseSession.commit();

			for (Class cls : classes) {
				classDAO.detach(cls);
			}
			seriesDAO.detach(newSeries);
		} finally {
			win.getDatabase().endSession();
		}

		Collections.sort(classes);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				updateModel(newSeries, classes);
			}
		});
	}

	@Override
	public void tabClear() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				updateModel(null, null);
			}
		});
	}

	private void updateModel(Series series, List<Class> updatedItems) {
		assert (SwingUtilities.isEventDispatchThread());

		currentSeries = series;

		final boolean trace = log.isTraceEnabled();
		DefaultListModel model = (DefaultListModel)list.getModel();

		if (updatedItems == null || updatedItems.isEmpty()) {
			if (trace && model.getSize() > 0) {
				log.trace("Removing all items"); //$NON-NLS-1$
			}
			model.removeAllElements();
			return;
		}

		Class selected = (Class)list.getSelectedValue();
		int index = updatedItems.indexOf(selected);
		Class preUpdated = null;
		if (selected != null) {
			if (index == -1) {
				if (trace) {
					log.trace("Deselecting current item"); //$NON-NLS-1$
				}
				selected = null;
				list.setSelectedValue(null, false);
			} else {
				Class item = updatedItems.get(index);
				if (trace) {
					log.trace("Updating " + selected + " > " + item); //$NON-NLS-1$ //$NON-NLS-2$
				}
				model.setElementAt(item, list.getSelectedIndex());
				preUpdated = item;
			}
		}

		Iterator<Class> iter = updatedItems.iterator();
		Class next = iter.hasNext() ? iter.next() : null;
		int i = 0;

		while (next != null || i < model.getSize()) {
			boolean add = false;

			if (i < model.getSize()) {
				boolean remove = false;
				Class item = (Class)model.getElementAt(i);

				if (next == null || item.compareTo(next) < 0) {
					remove = true;
				} else if (item.compareTo(next) == 0) {
					if (next != preUpdated) {
						if (trace) {
							log.trace("Updating " + item + " > " + next); //$NON-NLS-1$ //$NON-NLS-2$
						}
						model.setElementAt(next, i);
					}
				} else if (next == preUpdated) {
					remove = true;
				} else {
					add = true;
				}

				if (remove) {
					if (trace) {
						log.trace("Removing " + item); //$NON-NLS-1$
					}
					model.removeElementAt(i);
					continue;
				}
			} else {
				add = true;
			}

			if (add) {
				if (trace) {
					log.trace("Adding " + next); //$NON-NLS-1$
				}
				model.insertElementAt(next, i);
			}

			i++;
			next = iter.hasNext() ? iter.next() : null;
		}
	}
}