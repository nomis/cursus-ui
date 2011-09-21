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
package eu.lp0.cursus.ui;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.ClassDAO;
import eu.lp0.cursus.db.dao.SeriesDAO;
import eu.lp0.cursus.db.data.Class;

class ClassManager {
	private final MainWindow win;

	private final JTabbedPane mainTabs;
	private final JPanel classesTab;
	private final JList classesList;

	private boolean loaded;

	private static final SeriesDAO seriesDAO = new SeriesDAO();
	private static final ClassDAO classDAO = new ClassDAO();

	ClassManager(MainWindow win, JTabbedPane mainTabs, JPanel classesTab, JList classesList) {
		this.win = win;

		this.mainTabs = mainTabs;
		this.classesTab = classesTab;
		this.classesList = classesList;

		mainTabs.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ClassManager.this.win.execute(new Runnable() {
					@Override
					public void run() {
						load();
					}
				});
			}
		});
	}

	private void load() {
		if (!loaded && win.getMain().isOpen() && mainTabs.getSelectedComponent() == classesTab) {
			Vector<String> classNames = new Vector<String>();

			win.getDatabase().startSession();
			try {
				DatabaseSession.begin();

				List<Class> classes = classDAO.findAll(seriesDAO.findSingleton());
				Collections.sort(classes);
				for (Class cls : classes) {
					classNames.add(cls.getName());
				}

				DatabaseSession.commit();
			} finally {
				win.getDatabase().endSession();
			}

			classesList.setModel(new DefaultComboBoxModel(classNames));

			loaded = true;
		}
	}

	public void syncGUI(boolean open) {
		loaded = false;
		if (open) {
			load();
		} else {
			classesList.setModel(new DefaultListModel());
		}
	}
}