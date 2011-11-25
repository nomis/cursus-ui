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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.component.Displayable;
import eu.lp0.cursus.ui.series.SeriesDetailDialog;
import eu.lp0.cursus.util.Messages;

public class DatabasePopupMenu extends JPopupMenu implements ActionListener {
	private final DatabaseWindow win;

	private JMenuItem mnuNewSeries;

	public enum Command {
		NEW_SERIES;
	}

	public DatabasePopupMenu(DatabaseWindow win) {
		this.win = win;

		mnuNewSeries = new JMenuItem(Messages.getString("menu.series.new")); //$NON-NLS-1$
		mnuNewSeries.setActionCommand(Command.NEW_SERIES.toString());
		mnuNewSeries.addActionListener(this);
		add(mnuNewSeries);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		doCommand(Command.valueOf(ae.getActionCommand()));
	}

	public void doCommand(Command cmd) {
		Displayable dialog = null;

		switch (cmd) {
		case NEW_SERIES:
			dialog = new SeriesDetailDialog(win, Messages.getString("menu.series.new"), new Series(), false); //$NON-NLS-1$
			break;
		}

		if (dialog != null) {
			dialog.display();
		}
	}
}