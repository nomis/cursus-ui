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

import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import eu.lp0.cursus.util.Messages;

public class SeriesTreePopupMenu extends JPopupMenu {
	private JMenuItem mnuNewEvent;
	private JMenuItem mnuEditSeries;

	public SeriesTreePopupMenu() {
		mnuNewEvent = new JMenuItem(Messages.getString("menu.event.new")); //$NON-NLS-1$
		mnuNewEvent.setMnemonic(KeyEvent.VK_INSERT);
		add(mnuNewEvent);

		mnuEditSeries = new JMenuItem(Messages.getString("menu.series.edit")); //$NON-NLS-1$
		mnuEditSeries.setMnemonic(KeyEvent.VK_F2);
		add(mnuEditSeries);
	}
}