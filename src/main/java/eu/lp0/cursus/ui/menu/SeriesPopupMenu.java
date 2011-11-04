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
package eu.lp0.cursus.ui.menu;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import eu.lp0.cursus.db.dao.SeriesDAO;
import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.component.Displayable;
import eu.lp0.cursus.ui.event.EventDetailDialog;
import eu.lp0.cursus.ui.series.SeriesDetailDialog;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.util.Messages;

public class SeriesPopupMenu<O extends Frame & DatabaseWindow> extends AbstractNamedEntityPopupMenu<O, Series> implements ActionListener {
	private JMenuItem mnuNewEvent;
	private JMenuItem mnuEditSeries;
	private JMenuItem mnuDeleteSeries;
	private JSeparator mnuSeparator1;
	private JMenuItem mnuNewSeries;

	private static final SeriesDAO seriesDAO = new SeriesDAO();

	private enum Commands {
		NEW_SERIES, NEW_EVENT, EDIT_SERIES, DELETE_SERIES;
	}

	public SeriesPopupMenu(O owner, Series series) {
		super(owner, series, seriesDAO);

		mnuNewEvent = new JMenuItem(Messages.getString("menu.event.new")); //$NON-NLS-1$
		mnuNewEvent.setActionCommand(Commands.NEW_EVENT.toString());
		mnuNewEvent.addActionListener(this);
		add(mnuNewEvent);

		mnuEditSeries = new JMenuItem(Messages.getString("menu.series.edit")); //$NON-NLS-1$
		mnuEditSeries.setActionCommand(Commands.EDIT_SERIES.toString());
		mnuEditSeries.addActionListener(this);
		add(mnuEditSeries);

		mnuDeleteSeries = new JMenuItem(Messages.getString("menu.series.delete")); //$NON-NLS-1$
		mnuDeleteSeries.setActionCommand(Commands.DELETE_SERIES.toString());
		mnuDeleteSeries.addActionListener(this);
		add(mnuDeleteSeries);

		mnuSeparator1 = new JSeparator();
		add(mnuSeparator1);

		mnuNewSeries = new JMenuItem(Messages.getString("menu.series.new")); //$NON-NLS-1$
		mnuNewSeries.setActionCommand(Commands.NEW_SERIES.toString());
		mnuNewSeries.addActionListener(this);
		add(mnuNewSeries);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Displayable win = null;

		switch (Commands.valueOf(ae.getActionCommand())) {
		case NEW_EVENT:
			win = new EventDetailDialog<O>(owner, item.getName() + Constants.EN_DASH + Messages.getString("menu.event.new"), new Event(item)); //$NON-NLS-1$
			break;
		case EDIT_SERIES:
			win = new SeriesDetailDialog<O>(owner, Messages.getString("menu.series.edit") + Constants.EN_DASH + item.getName(), item); //$NON-NLS-1$
			break;
		case DELETE_SERIES:
			confirmDelete("menu.series.delete"); //$NON-NLS-1$
			break;
		case NEW_SERIES:
			win = new SeriesDetailDialog<O>(owner, Messages.getString("menu.series.new"), new Series()); //$NON-NLS-1$
			break;
		}

		if (win != null) {
			win.display();
		}
	}
}