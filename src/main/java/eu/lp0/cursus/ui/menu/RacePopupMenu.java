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

import eu.lp0.cursus.db.dao.RaceDAO;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.component.Displayable;
import eu.lp0.cursus.ui.race.RaceDetailDialog;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.util.Messages;

public class RacePopupMenu<O extends Frame & DatabaseWindow> extends AbstractNamedEntityPopupMenu<O, Race> implements ActionListener {
	private JMenuItem mnuEditRace;
	private JMenuItem mnuDeleteRace;
	private JSeparator mnuSeparator1;
	private JMenuItem mnuNewRace;

	private static final RaceDAO raceDAO = new RaceDAO();

	private enum Commands {
		EDIT_RACE, DELETE_RACE, NEW_RACE;
	}

	public RacePopupMenu(O owner, Race race) {
		super(owner, race);

		mnuEditRace = new JMenuItem(Messages.getString("menu.race.edit")); //$NON-NLS-1$
		mnuEditRace.setActionCommand(Commands.EDIT_RACE.toString());
		mnuEditRace.addActionListener(this);
		add(mnuEditRace);

		mnuDeleteRace = new JMenuItem(Messages.getString("menu.race.delete")); //$NON-NLS-1$
		mnuDeleteRace.setActionCommand(Commands.DELETE_RACE.toString());
		mnuDeleteRace.addActionListener(this);
		add(mnuDeleteRace);

		mnuSeparator1 = new JSeparator();
		add(mnuSeparator1);

		mnuNewRace = new JMenuItem(Messages.getString("menu.race.new")); //$NON-NLS-1$
		mnuNewRace.setActionCommand(Commands.NEW_RACE.toString());
		mnuNewRace.addActionListener(this);
		add(mnuNewRace);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Displayable win = null;

		switch (Commands.valueOf(ae.getActionCommand())) {
		case EDIT_RACE:
			win = new RaceDetailDialog<O>(owner, Messages.getString("menu.race.edit") + Constants.EN_DASH + item.getName(), item, true); //$NON-NLS-1$
			break;
		case DELETE_RACE:
			confirmDelete("menu.race.delete"); //$NON-NLS-1$
			break;
		case NEW_RACE:
			win = new RaceDetailDialog<O>(owner, item.getName() + Constants.EN_DASH + Messages.getString("menu.race.new"), new Race(item.getEvent()), false); //$NON-NLS-1$
			break;
		}

		if (win != null) {
			win.display();
		}
	}

	@Override
	protected void doDelete() {
		Race race = raceDAO.get(item);
		race.getEvent().getRaces().remove(race);
	}
}