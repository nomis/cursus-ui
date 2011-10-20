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
package eu.lp0.cursus.ui.series;

import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import eu.lp0.cursus.db.dao.PilotDAO;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.RaceNumber;
import eu.lp0.cursus.ui.component.DatabaseTableModel;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.util.Messages;

public class PilotTableModel<O extends Frame & DatabaseWindow> extends DatabaseTableModel<Pilot, O> {
	public PilotTableModel(O win) {
		super(Pilot.class, win, new PilotDAO());
	}

	@Override
	public void setupEditableModel(JTable table) {
		super.setupEditableModel(table);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) {
					tryEditPilot((JTable)me.getSource());
				}
			}
		});

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_F2) {
					tryEditPilot((JTable)ke.getSource());
				}
			}
		});
	}

	private void tryEditPilot(JTable table) {
		int rowIndex = table.getSelectedRow();
		int columnIndex = table.getSelectedColumn();

		if (columnIndex != -1 && getColumnClass(columnIndex) == RaceNumber.class) {
			editPilot(getValueAt(rowIndex));
		}
	}

	private void editPilot(Pilot pilot) {
		// TODO edit pilot
		JOptionPane.showMessageDialog(win, Messages.getString("err.feat-not-impl"), //$NON-NLS-1$
				Constants.APP_NAME, JOptionPane.ERROR_MESSAGE);
	}
}