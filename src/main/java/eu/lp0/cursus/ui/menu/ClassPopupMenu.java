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
import javax.swing.JSeparator;

import eu.lp0.cursus.db.dao.ClassDAO;
import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.component.Displayable;
import eu.lp0.cursus.ui.series.ClassDetailDialog;
import eu.lp0.cursus.ui.series.SeriesClassesTab;
import eu.lp0.cursus.util.Constants;
import eu.lp0.cursus.util.Messages;

public class ClassPopupMenu extends AbstractNamedEntityPopupMenu<Class> implements ActionListener {
	private final SeriesClassesTab tab;

	private JMenuItem mnuEditClass;
	private JMenuItem mnuDeleteClass;
	private JSeparator mnuSeparator1;
	private JMenuItem mnuNewClass;

	private static final ClassDAO classDAO = new ClassDAO();

	public enum Command {
		NEW_CLASS, EDIT_CLASS, DELETE_CLASS;
	}

	public ClassPopupMenu(DatabaseWindow win, SeriesClassesTab tab, Class clazz) {
		super(win, clazz);
		this.tab = tab;

		mnuEditClass = new JMenuItem(Messages.getString("menu.class.edit")); //$NON-NLS-1$
		mnuEditClass.setActionCommand(Command.EDIT_CLASS.toString());
		mnuEditClass.addActionListener(this);
		add(mnuEditClass);

		mnuDeleteClass = new JMenuItem(Messages.getString("menu.class.delete")); //$NON-NLS-1$
		mnuDeleteClass.setActionCommand(Command.DELETE_CLASS.toString());
		mnuDeleteClass.addActionListener(this);
		add(mnuDeleteClass);

		mnuSeparator1 = new JSeparator();
		add(mnuSeparator1);

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
		case EDIT_CLASS:
			dialog = new ClassDetailDialog(win, tab, Messages.getString("menu.class.edit") + Constants.EN_DASH + item.getName(), item, true); //$NON-NLS-1$
			break;
		case DELETE_CLASS:
			confirmDelete("menu.class.delete"); //$NON-NLS-1$
			break;
		case NEW_CLASS:
			dialog = new ClassDetailDialog(win, tab, Messages.getString("menu.class.new"), new Class(item.getSeries()), false); //$NON-NLS-1$
			break;
		}

		if (dialog != null) {
			dialog.display();
		}
	}

	@Override
	protected void doDelete() {
		Class clazz = classDAO.get(item);
		clazz.getSeries().getClasses().remove(clazz);
	}

	@Override
	protected void doRefresh() {
		win.refreshTab(tab);
	}
}