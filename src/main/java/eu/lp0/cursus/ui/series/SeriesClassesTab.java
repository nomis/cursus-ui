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
import java.util.Collections;
import java.util.List;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.ClassDAO;
import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.ui.component.AbstractDatabaseTab;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.component.EntityComboBoxModel;

public class SeriesClassesTab extends AbstractDatabaseTab<Series> {
	private JSplitPane splitPane;
	private JList list;
	private JScrollPane scrollPane;
	private JTable table;

	private static final ClassDAO classDAO = new ClassDAO();

	public SeriesClassesTab(DatabaseWindow win) {
		super(Series.class, win, "tab.classes"); //$NON-NLS-1$
		initialise();
	}

	private void initialise() {
		setLayout(new BorderLayout(0, 0));

		splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);

		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setMinimumSize(new Dimension(150, 0));
		list.setModel(new EntityComboBoxModel<Class>());
		splitPane.setLeftComponent(list);

		scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

	@Override
	public void tabRefresh(Series series) {
		final List<Class> classes;

		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			classes = classDAO.findAll(series);
			for (Class cls : classes) {
				classDAO.detach(cls);
			}

			DatabaseSession.commit();
		} finally {
			win.getDatabase().endSession();
		}

		Collections.sort(classes);

		SwingUtilities.invokeLater(new Runnable() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				((EntityComboBoxModel<Class>)list.getModel()).updateModel(classes);
			}
		});
	}

	@Override
	public void tabClear() {
		list.setModel(new EntityComboBoxModel<Class>());
	}
}