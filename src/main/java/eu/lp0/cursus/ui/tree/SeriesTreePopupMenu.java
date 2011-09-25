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
package eu.lp0.cursus.ui.tree;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.component.Displayable;
import eu.lp0.cursus.ui.event.EventDetailDialog;
import eu.lp0.cursus.ui.series.SeriesDetailDialog;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.util.Messages;

public class SeriesTreePopupMenu<O extends Frame & DatabaseWindow> extends JPopupMenu implements ActionListener {
	private final O owner;
	private final Series series;

	private JMenuItem mnuNewEvent;
	private JMenuItem mnuEditSeries;
	private JMenuItem mnuDeleteSeries;

	private enum Commands {
		NEW_EVENT, EDIT_SERIES, DELETE_SERIES;
	}

	public SeriesTreePopupMenu(O owner, Series series) {
		this.owner = owner;
		this.series = series;

		mnuNewEvent = new JMenuItem(Messages.getString("menu.event.new")); //$NON-NLS-1$
		mnuNewEvent.setMnemonic(KeyEvent.VK_INSERT);
		mnuNewEvent.setActionCommand(Commands.NEW_EVENT.toString());
		mnuNewEvent.addActionListener(this);
		add(mnuNewEvent);

		mnuEditSeries = new JMenuItem(Messages.getString("menu.series.edit")); //$NON-NLS-1$
		mnuEditSeries.setMnemonic(KeyEvent.VK_F2);
		mnuEditSeries.setActionCommand(Commands.EDIT_SERIES.toString());
		mnuEditSeries.addActionListener(this);
		add(mnuEditSeries);

		mnuDeleteSeries = new JMenuItem(Messages.getString("menu.series.delete")); //$NON-NLS-1$
		mnuDeleteSeries.setMnemonic(KeyEvent.VK_DELETE);
		mnuDeleteSeries.setActionCommand(Commands.DELETE_SERIES.toString());
		mnuDeleteSeries.addActionListener(this);
		add(mnuDeleteSeries);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Displayable win = null;

		switch (Commands.valueOf(ae.getActionCommand())) {
		case NEW_EVENT:
			win = new EventDetailDialog<O>(owner, series.getName() + Constants.EN_DASH + Messages.getString("menu.event.new"), new Event(series)); //$NON-NLS-1$
			break;
		case EDIT_SERIES:
			win = new SeriesDetailDialog<O>(owner, Messages.getString("menu.series.edit") + Constants.EN_DASH + series.getName(), series); //$NON-NLS-1$
			break;
		case DELETE_SERIES:
			break;
		}

		if (win != null) {
			win.display();
		}
	}
}