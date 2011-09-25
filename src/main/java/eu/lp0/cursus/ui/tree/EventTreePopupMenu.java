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
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.component.Displayable;
import eu.lp0.cursus.ui.event.EventDetailDialog;
import eu.lp0.cursus.ui.race.RaceDetailDialog;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.util.Messages;

public class EventTreePopupMenu<O extends Frame & DatabaseWindow> extends JPopupMenu implements ActionListener {
	private final O owner;
	private final Event event;

	private JMenuItem mnuNewRace;
	private JMenuItem mnuEditEvent;
	private JMenuItem mnuDeleteEvent;

	private enum Commands {
		NEW_RACE, EDIT_EVENT, DELETE_EVENT;
	}

	public EventTreePopupMenu(O owner, Event event) {
		this.owner = owner;
		this.event = event;

		mnuNewRace = new JMenuItem(Messages.getString("menu.race.new")); //$NON-NLS-1$
		mnuNewRace.setMnemonic(KeyEvent.VK_INSERT);
		mnuNewRace.setActionCommand(Commands.NEW_RACE.toString());
		mnuNewRace.addActionListener(this);
		add(mnuNewRace);

		mnuEditEvent = new JMenuItem(Messages.getString("menu.event.edit")); //$NON-NLS-1$
		mnuEditEvent.setMnemonic(KeyEvent.VK_F2);
		mnuEditEvent.setActionCommand(Commands.EDIT_EVENT.toString());
		mnuEditEvent.addActionListener(this);
		add(mnuEditEvent);

		mnuDeleteEvent = new JMenuItem(Messages.getString("menu.event.delete")); //$NON-NLS-1$
		mnuDeleteEvent.setMnemonic(KeyEvent.VK_DELETE);
		mnuDeleteEvent.setActionCommand(Commands.DELETE_EVENT.toString());
		mnuDeleteEvent.addActionListener(this);
		add(mnuDeleteEvent);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Displayable win = null;

		switch (Commands.valueOf(ae.getActionCommand())) {
		case NEW_RACE:
			win = new RaceDetailDialog<O>(owner, event.getName() + Constants.EN_DASH + Messages.getString("menu.race.new"), new Race(event)); //$NON-NLS-1$
			break;
		case EDIT_EVENT:
			win = new EventDetailDialog<O>(owner, Messages.getString("menu.event.edit") + Constants.EN_DASH + event.getName(), event); //$NON-NLS-1$
			break;
		case DELETE_EVENT:
			break;
		}

		if (win != null) {
			win.display();
		}
	}
}