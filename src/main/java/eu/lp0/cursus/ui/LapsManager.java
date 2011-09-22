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

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultTreeModel;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.EventDAO;
import eu.lp0.cursus.db.dao.RaceDAO;
import eu.lp0.cursus.db.dao.SeriesDAO;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.Series;

class LapsManager {
	private final MainWindow win;

	private final JTabbedPane mainTabs;
	private final JPanel lapsTab;
	private final JTree lapsRaceList;

	private static final SeriesDAO seriesDAO = new SeriesDAO();
	private static final EventDAO eventDAO = new EventDAO();
	private static final RaceDAO raceDAO = new RaceDAO();

	LapsManager(MainWindow win, JTabbedPane mainTabs, JPanel lapsTab, JTree lapsRaceList) {
		this.win = win;

		this.mainTabs = mainTabs;
		this.lapsTab = lapsTab;
		this.lapsRaceList = lapsRaceList;

		mainTabs.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				LapsManager.this.win.execute(new Runnable() {
					@Override
					public void run() {
						if (LapsManager.this.mainTabs.getSelectedComponent() == LapsManager.this.lapsTab) {
							load();
						}
					}
				});
			}
		});
	}

	private void load() {
		if (win.getMain().isOpen()) {
			final List<Series> seriesList = new ArrayList<Series>();

			win.getDatabase().startSession();
			try {
				DatabaseSession.begin();

				Series series = seriesDAO.findSingleton();
				for (Event event : series.getEvents()) {
					for (Race race : event.getRaces()) {
						raceDAO.detach(race);
					}
					eventDAO.detach(event);
				}
				seriesDAO.detach(series);
				seriesList.add(series);

				DatabaseSession.commit();
			} finally {
				win.getDatabase().endSession();
			}

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					updateModel(lapsRaceList, seriesList);
				}
			});
		}
	}

	public void syncGUI(boolean open) {
		if (open) {
			if (mainTabs.getSelectedComponent() == lapsTab) {
				load();
			}
		} else {
			updateModel(lapsRaceList, null);
		}
	}

	private static void updateModel(JTree tree, List<Series> seriesList) {
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		DatabaseTreeNode root = (DatabaseTreeNode)tree.getModel().getRoot();
		root.updateTree(model, seriesList);
	}
}