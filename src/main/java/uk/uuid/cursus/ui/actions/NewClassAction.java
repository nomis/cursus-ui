/*
	cursus - Race series management program
	Copyright 2011, 2014  Simon Arlott

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU Affero General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU Affero General Public License for more details.

	You should have received a copy of the GNU Affero General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uk.uuid.cursus.ui.actions;

import java.awt.event.ActionEvent;

import uk.uuid.cursus.ui.component.AbstractDatabaseTab;
import uk.uuid.cursus.ui.component.DatabaseWindow;
import uk.uuid.cursus.ui.series.ClassDetailDialog;
import uk.uuid.cursus.db.data.Class;
import uk.uuid.cursus.db.data.Series;

public class NewClassAction extends AbstractTranslatedAction {
	private final DatabaseWindow win;
	private final AbstractDatabaseTab<Series> tab;
	private final Series series;

	public NewClassAction(DatabaseWindow win, AbstractDatabaseTab<Series> tab, Series series) {
		super("menu.class.new", false); //$NON-NLS-1$
		this.win = win;
		this.tab = tab;
		this.series = series;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		new ClassDetailDialog(win, tab, (String)getValue(NAME), new Class(series), false).display();
	}
}