/*
	cursus - Race series management program
	Copyright 2011-2014  Simon Arlott

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
package uk.uuid.cursus.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import uk.uuid.cursus.app.Main;
import uk.uuid.cursus.i18n.LanguageManager;
import uk.uuid.cursus.i18n.LocaleChangeEvent;
import uk.uuid.cursus.i18n.Messages;
import uk.uuid.cursus.ui.component.AbstractDatabaseTab;
import uk.uuid.cursus.ui.component.DatabaseTabSync;
import uk.uuid.cursus.ui.component.DatabaseWindow;
import uk.uuid.cursus.ui.component.Displayable;
import uk.uuid.cursus.ui.event.EventAttendeesTab;
import uk.uuid.cursus.ui.event.EventResultsTab;
import uk.uuid.cursus.ui.menu.MainMenu;
import uk.uuid.cursus.ui.preferences.JFrameAutoPrefs;
import uk.uuid.cursus.ui.race.RaceAttendeesTab;
import uk.uuid.cursus.ui.race.RaceLapsTab;
import uk.uuid.cursus.ui.race.RacePenaltiesTab;
import uk.uuid.cursus.ui.race.RaceResultsTab;
import uk.uuid.cursus.ui.series.SeriesClassesTab;
import uk.uuid.cursus.ui.series.SeriesPilotsTab;
import uk.uuid.cursus.ui.series.SeriesResultsTab;
import uk.uuid.cursus.ui.tree.RaceTree;
import uk.uuid.cursus.ui.util.AccessibleComponents;
import uk.uuid.cursus.util.Background;

import com.google.common.eventbus.Subscribe;

import uk.uuid.cursus.db.Database;
import uk.uuid.cursus.db.data.Event;
import uk.uuid.cursus.db.data.Race;
import uk.uuid.cursus.db.data.RaceEntity;
import uk.uuid.cursus.db.data.Series;

public final class MainWindow extends JFrame implements Displayable, DatabaseWindow {
	private final Main main;

	private JFrameAutoPrefs prefs = new JFrameAutoPrefs(this);
	private DatabaseManager dbMgr = new DatabaseManager(this);
	@SuppressWarnings("unused")
	private RuntimeManager runMgr;
	private TabbedPaneManager tabMgr;
	private SelectedTabManager selMgr;

	private MainMenu menuBar;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private RaceTree raceList;
	private JTabbedPane tabbedPane;
	private AbstractDatabaseTab<Series> serPilotsTab;
	private AbstractDatabaseTab<Series> serClassesTab;
	private AbstractDatabaseTab<Series> serResultsTab;
	private List<AbstractDatabaseTab<Series>> seriesTabs;
	private AbstractDatabaseTab<Event> evtPilotsTab;
	private AbstractDatabaseTab<Event> evtResultsTab;
	private List<AbstractDatabaseTab<Event>> eventTabs;
	private AbstractDatabaseTab<Race> racAttendeesTab;
	private AbstractDatabaseTab<Race> racLapsTab;
	private AbstractDatabaseTab<Race> racPenaltiesTab;
	private AbstractDatabaseTab<Race> racResultsTab;
	private List<AbstractDatabaseTab<Race>> raceTabs;

	public MainWindow(Main main) {
		super();
		this.main = main;

		initialise();
		bind();
		databaseClosed();
		LanguageManager.register(this, true);
	}

	@Subscribe
	public final void updateLanguage(LocaleChangeEvent lce) {
		Messages.setAccessible(raceList, AccessibleComponents.RACE_TREE);
	}

	public void display() {
		assert (SwingUtilities.isEventDispatchThread());

		prefs.display();
	}

	public Main getMain() {
		return main;
	}

	public Frame getFrame() {
		return this;
	}

	public MainMenu getMenu() {
		return menuBar;
	}

	public boolean isOpen() {
		return main.isOpen();
	}

	public Database getDatabase() {
		return main.getDatabase();
	}

	public RaceEntity getSelected() {
		assert (SwingUtilities.isEventDispatchThread());

		return isOpen() ? raceList.getSelected() : null;
	}

	@SuppressWarnings("unchecked")
	private void initialise() {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setTitle(Constants.APP_NAME);
		setSize(800, 600);

		{ // Menu
			menuBar = new MainMenu(this, dbMgr);
			setJMenuBar(menuBar);
		}

		{ // Main
			splitPane = new JSplitPane();
			splitPane.setBorder(new EmptyBorder(2, 0, 0, 0));
			getContentPane().add(splitPane, BorderLayout.CENTER);

			scrollPane = new JScrollPane();
			scrollPane.setMinimumSize(new Dimension(50, 0));
			scrollPane.setPreferredSize(new Dimension(150, 0));
			splitPane.setLeftComponent(scrollPane);

			raceList = new RaceTree(this);
			raceList.setBorder(new EmptyBorder(2, 2, 2, 2));
			scrollPane.setViewportView(raceList);

			tabbedPane = new JTabbedPane();
			tabbedPane.setBorder(new EmptyBorder(0, 2, 0, 0));
			tabbedPane.setMinimumSize(new Dimension(50, 0));
			splitPane.setRightComponent(tabbedPane);
		}

		{ // Series
			serClassesTab = new SeriesClassesTab(this);
			serPilotsTab = new SeriesPilotsTab(this);
			serResultsTab = new SeriesResultsTab(this);
			seriesTabs = Arrays.asList(serPilotsTab, serClassesTab, serResultsTab);
		}

		{ // Event
			evtPilotsTab = new EventAttendeesTab(this);
			evtResultsTab = new EventResultsTab(this);

			eventTabs = Arrays.asList(evtPilotsTab, evtResultsTab);
		}

		{ // Race
			racAttendeesTab = new RaceAttendeesTab(this);
			racLapsTab = new RaceLapsTab(this);
			racPenaltiesTab = new RacePenaltiesTab(this);
			racResultsTab = new RaceResultsTab(this);

			raceTabs = Arrays.asList(racAttendeesTab, racLapsTab, racPenaltiesTab, racResultsTab);
		}
	}

	private void bind() {
		runMgr = new RuntimeManager(this, dbMgr, main.getArgs());
		tabMgr = new TabbedPaneManager(raceList, tabbedPane, seriesTabs, eventTabs, raceTabs);
		selMgr = new SelectedTabManager(this, tabbedPane);
	}

	private void sync(final boolean open, final boolean saved, final String title) {
		assert (Background.isExecutorThread());

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				splitPane.setVisible(open);
				menuBar.sync(open, saved);
				getRootPane().validate();

				if (!open) {
					tabMgr.showSelected(null);
				}

				setTitle(title);
			}
		});

		if (open) {
			raceList.databaseRefresh();
			selMgr.databaseRefresh();
		} else {
			raceList.databaseClosed();
			selMgr.databaseClosed();
		}
	}

	public void databaseOpened() {
		String prefix = main.getDatabase().isSaved() ? "" : "*";  //$NON-NLS-1$//$NON-NLS-2$
		sync(true, main.getDatabase().isSaved(), Constants.APP_NAME + Constants.EN_DASH + prefix + main.getDatabase().getName());
	}

	public void databaseClosed() {
		sync(false, false, Constants.APP_DESC);
	}

	public void refreshRaceList() {
		Background.execute(new Runnable() {
			@Override
			public void run() {
				raceList.databaseRefresh();
			}
		});
	}

	public void refreshTab(final DatabaseTabSync<?> tab) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				selMgr.tryRefreshTabLater(tab);
			}
		});
	}

	public void reloadCurrentTabs() {
		Background.execute(new Runnable() {
			@Override
			public void run() {
				selMgr.databaseClosed();
				selMgr.databaseRefresh();
			}
		});
	}
}