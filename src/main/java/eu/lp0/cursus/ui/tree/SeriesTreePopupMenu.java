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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.util.Messages;

public class SeriesTreePopupMenu extends JPopupMenu implements ActionListener {
	private final Component main;
	private final Series series;

	private JMenuItem mnuNewEvent;
	private JMenuItem mnuEditSeries;

	private enum Commands {
		NEW_EVENT, EDIT_SERIES;
	}

	public SeriesTreePopupMenu(Component main, Series series) {
		this.main = main;
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
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch (Commands.valueOf(ae.getActionCommand())) {
		case NEW_EVENT:
			break;
		case EDIT_SERIES:
			break;
		}
	}
}