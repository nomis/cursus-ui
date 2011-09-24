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

import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.ui.Displayable;
import eu.lp0.cursus.ui.RaceDetailWindow;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.util.Messages;

public class RaceTreePopupMenu extends JPopupMenu implements ActionListener {
	private final Frame owner;
	private final Race race;

	private JMenuItem mnuEditRace;
	private JMenuItem mnuDeleteRace;

	private enum Commands {
		EDIT_RACE, DELETE_RACE;
	}

	public RaceTreePopupMenu(Frame owner, Race race) {
		this.owner = owner;
		this.race = race;

		mnuEditRace = new JMenuItem(Messages.getString("menu.race.edit")); //$NON-NLS-1$
		mnuEditRace.setMnemonic(KeyEvent.VK_F2);
		mnuEditRace.setActionCommand(Commands.EDIT_RACE.toString());
		mnuEditRace.addActionListener(this);
		add(mnuEditRace);

		mnuDeleteRace = new JMenuItem(Messages.getString("menu.race.delete")); //$NON-NLS-1$
		mnuDeleteRace.setMnemonic(KeyEvent.VK_DELETE);
		mnuDeleteRace.setActionCommand(Commands.DELETE_RACE.toString());
		mnuDeleteRace.addActionListener(this);
		add(mnuDeleteRace);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Displayable win = null;

		switch (Commands.valueOf(ae.getActionCommand())) {
		case EDIT_RACE:
			win = new RaceDetailWindow(owner, Messages.getString("menu.race.edit") + Constants.EN_DASH + race.getName(), race); //$NON-NLS-1$
			break;
		case DELETE_RACE:
			break;
		}

		if (win != null) {
			win.display();
		}
	}
}