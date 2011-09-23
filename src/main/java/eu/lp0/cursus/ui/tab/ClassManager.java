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

import java.util.Collections;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.ClassDAO;
import eu.lp0.cursus.db.dao.SeriesDAO;
import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.ui.MainWindow;
import eu.lp0.cursus.ui.component.AbstractTabManager;
import eu.lp0.cursus.ui.component.EntityComboBoxModel;

public class ClassManager extends AbstractTabManager {
	private final JList classesList;

	private static final SeriesDAO seriesDAO = new SeriesDAO();
	private static final ClassDAO classDAO = new ClassDAO();

	public ClassManager(MainWindow win, JTabbedPane mainTabs, JPanel classesTab, JList classesList) {
		super(win, mainTabs, classesTab);
		this.classesList = classesList;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void tabSelected() {
		final List<Class> classes;

		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			classes = classDAO.findAll(seriesDAO.findSingleton());
			for (Class cls : classes) {
				classDAO.detach(cls);
			}

			DatabaseSession.commit();
		} finally {
			win.getDatabase().endSession();
		}

		Collections.sort(classes);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				((EntityComboBoxModel<Class>)classesList.getModel()).updateModel(classes);
			}
		});
	}

	@Override
	protected void databaseClosed() {
		classesList.setModel(new EntityComboBoxModel<Class>());
	}
}