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

import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.i18n.Messages;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.component.Displayable;
import eu.lp0.cursus.ui.series.ClassDetailDialog;
import eu.lp0.cursus.ui.series.SeriesClassesTab;

public class ClassesPopupMenu extends JPopupMenu implements ActionListener {
	private final DatabaseWindow win;
	private final SeriesClassesTab tab;
	private final Series series;

	private JMenuItem mnuNewClass;

	public enum Command {
		NEW_CLASS;
	}

	public ClassesPopupMenu(DatabaseWindow win, SeriesClassesTab tab, Series series) {
		this.win = win;
		this.tab = tab;
		this.series = series;

		mnuNewClass = new JMenuItem(Messages.getString("menu.class.new")); //$NON-NLS-1$
		mnuNewClass.setActionCommand(Command.NEW_CLASS.toString());
		mnuNewClass.addActionListener(this);
		add(mnuNewClass);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		doCommand(Command.valueOf(ae.getActionCommand()));
	}

	public void doCommand(Command cmd) {
		Displayable dialog = null;

		switch (cmd) {
		case NEW_CLASS:
			dialog = new ClassDetailDialog(win, tab, Messages.getString("menu.class.new"), new Class(series), false); //$NON-NLS-1$
			break;
		}

		if (dialog != null) {
			dialog.display();
		}
	}
}