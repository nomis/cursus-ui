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

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.util.Messages;

public class EventTreePopupMenu extends JPopupMenu implements ActionListener {
	private final Component main;
	private final Event event;

	private JMenuItem mnuNewRace;
	private JMenuItem mnuEditEvent;
	private JMenuItem mnuDeleteEvent;

	private enum Commands {
		NEW_RACE, EDIT_EVENT, DELETE_EVENT;
	}

	public EventTreePopupMenu(Component main, Event event) {
		this.main = main;
		this.event = event;

		mnuNewRace = new JMenuItem(Messages.getString("menu.race.new")); //$NON-NLS-1$
		mnuNewRace.setMnemonic(KeyEvent.VK_INSERT);
		add(mnuNewRace);

		mnuEditEvent = new JMenuItem(Messages.getString("menu.event.edit")); //$NON-NLS-1$
		mnuEditEvent.setMnemonic(KeyEvent.VK_F2);
		add(mnuEditEvent);

		mnuDeleteEvent = new JMenuItem(Messages.getString("menu.event.delete")); //$NON-NLS-1$
		mnuDeleteEvent.setMnemonic(KeyEvent.VK_DELETE);
		add(mnuDeleteEvent);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch (Commands.valueOf(ae.getActionCommand())) {
		case NEW_RACE:
			break;
		case EDIT_EVENT:
			break;
		case DELETE_EVENT:
			break;
		}
	}
}