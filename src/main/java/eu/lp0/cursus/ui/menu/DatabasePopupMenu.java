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
import javax.swing.JPopupMenu;

import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.component.Displayable;
import eu.lp0.cursus.ui.series.SeriesDetailDialog;
import eu.lp0.cursus.util.Messages;

public class DatabasePopupMenu<O extends Frame & DatabaseWindow> extends JPopupMenu implements ActionListener {
	private final O owner;

	private JMenuItem mnuNewSeries;

	private enum Commands {
		NEW_SERIES;
	}

	public DatabasePopupMenu(O owner) {
		this.owner = owner;

		mnuNewSeries = new JMenuItem(Messages.getString("menu.series.new")); //$NON-NLS-1$
		mnuNewSeries.setActionCommand(Commands.NEW_SERIES.toString());
		mnuNewSeries.addActionListener(this);
		add(mnuNewSeries);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Displayable win = null;

		switch (Commands.valueOf(ae.getActionCommand())) {
		case NEW_SERIES:
			win = new SeriesDetailDialog<O>(owner, Messages.getString("menu.series.new"), new Series()); //$NON-NLS-1$
			break;
		}

		if (win != null) {
			win.display();
		}
	}
}