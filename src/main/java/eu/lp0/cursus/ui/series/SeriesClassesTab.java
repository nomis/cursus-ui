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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import eu.lp0.cursus.db.data.Series;
import eu.lp0.cursus.ui.component.AbstractDatabaseTab;
import eu.lp0.cursus.ui.component.DatabaseWindow;
import eu.lp0.cursus.ui.tree.ClassTree;

public class SeriesClassesTab<O extends Frame & DatabaseWindow> extends AbstractDatabaseTab<O, Series> {
	private JSplitPane splitPane;
	private JScrollPane leftScrollPane;
	private ClassTree<O> list;
	private JScrollPane rightScrollPane;
	private JTable table;

	public SeriesClassesTab(O win) {
		super(Series.class, win, "tab.classes"); //$NON-NLS-1$
		initialise();
	}

	private void initialise() {
		setLayout(new BorderLayout(0, 0));

		splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);

		leftScrollPane = new JScrollPane();
		leftScrollPane.setPreferredSize(new Dimension(150, 0));
		splitPane.setLeftComponent(leftScrollPane);

		list = new ClassTree<O>(win, this);
		list.setBorder(new EmptyBorder(2, 2, 2, 2));
		leftScrollPane.setViewportView(list);
		leftScrollPane.setBorder(null);

		rightScrollPane = new JScrollPane();
		rightScrollPane.setBorder(new EmptyBorder(0, 2, 0, 0));
		splitPane.setRightComponent(rightScrollPane);

		table = new JTable();
		rightScrollPane.setViewportView(table);
	}

	@Override
	public void tabRefresh(Series series) {
		list.tabRefresh(series);
	}

	@Override
	public void tabClear() {
		list.tabClear();
	}
}