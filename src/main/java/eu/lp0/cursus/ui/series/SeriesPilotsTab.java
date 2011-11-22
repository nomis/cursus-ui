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
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import eu.lp0.cursus.db.DatabaseSession;
import eu.lp0.cursus.db.dao.PilotDAO;
import eu.lp0.cursus.db.dao.SeriesDAO;
import eu.lp0.cursus.db.data.Gender;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.RaceNumber;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.ui.component.AbstractDatabaseTab;
import eu.lp0.cursus.ui.component.DatabaseColumnModel;
import eu.lp0.cursus.ui.component.DatabaseRowModel;
import eu.lp0.cursus.ui.component.DatabaseTableModel;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.component.EnumDatabaseColumnModel;
import eu.lp0.cursus.ui.component.RaceNumbersDatabaseColumnModel;
import eu.lp0.cursus.ui.component.StringDatabaseColumnModel;
import eu.lp0.cursus.util.Background;
import eu.lp0.cursus.util.Constants;

public class SeriesPilotsTab<O extends Frame & DatabaseWindow> extends AbstractDatabaseTab<O, Series> {
	private JScrollPane scrollPane;
	private JTable table;
	private DatabaseTableModel<Pilot> model;
	@SuppressWarnings("unused")
	private Series currentSeries = null;

	private static final SeriesDAO seriesDAO = new SeriesDAO();
	private static final PilotDAO pilotDAO = new PilotDAO();

	public SeriesPilotsTab(O win) {
		super(Series.class, win, "tab.pilots"); //$NON-NLS-1$
		initialise();
	}

	@SuppressWarnings("unchecked")
	private void initialise() {
		setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		model = new DatabaseTableModel<Pilot>(new DatabaseRowModel<Pilot>(Arrays.<DatabaseColumnModel<Pilot, ?, ?>>asList(
				new RaceNumbersDatabaseColumnModel<O>("pilot.race-number", win), //$NON-NLS-1$
				new StringDatabaseColumnModel<Pilot, O>("pilot.name", win, pilotDAO, Constants.MAX_STRING_LEN) { //$NON-NLS-1$
					@Override
					protected String getValue(Pilot row) {
						return row.getName();
					}

					@Override
					protected boolean setValue(Pilot row, String value) {
						row.setName(value);
						return true;
					}
				}, new EnumDatabaseColumnModel<Pilot, Gender, O>("pilot.gender", win, pilotDAO, Gender.class, true) { //$NON-NLS-1$
					@Override
					protected Gender getEnumValue(Pilot row) {
						return row.getGender();
					}

					@Override
					protected boolean setEnumValue(Pilot row, Gender value) {
						row.setGender(value);
						return true;
					}
				}, new StringDatabaseColumnModel<Pilot, O>("pilot.country", win, pilotDAO, Constants.MAX_STRING_LEN) { //$NON-NLS-1$
					@Override
					protected String getValue(Pilot row) {
						return row.getCountry();
					}

					@Override
					protected boolean setValue(Pilot row, String value) {
						row.setCountry(value);
						return true;
					}
				})));
		model.setupModel(table);
	}

	@Override
	public void tabRefresh(Series series) {
		assert (Background.isExecutorThread());

		final Series newSeries;
		final List<Pilot> newPilots;

		win.getDatabase().startSession();
		try {
			DatabaseSession.begin();

			newSeries = seriesDAO.get(series);
			newPilots = new ArrayList<Pilot>(newSeries.getPilots());
			for (Pilot pilot : newPilots) {
				for (@SuppressWarnings("unused")
				RaceNumber raceNumber : pilot.getRaceNumbers()) {
					;
				}
			}
			DatabaseSession.commit();

			seriesDAO.detach(newSeries);
		} finally {
			win.getDatabase().endSession();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				currentSeries = newSeries;
				model.updateModel(newPilots);
			}
		});
	}

	@Override
	public void tabClear() {
		assert (Background.isExecutorThread());

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				currentSeries = null;
				model.updateModel(Collections.<Pilot>emptyList());
			}
		});
	}
}