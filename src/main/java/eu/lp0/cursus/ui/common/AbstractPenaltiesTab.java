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
package eu.lp0.cursus.ui.common;

import java.awt.Frame;

import eu.lp0.cursus.db.data.RaceEntity;
import eu.lp0.cursus.ui.component.AbstractDatabaseTab;
import eu.lp0.cursus.ui.component.DatabaseWindow;

public abstract class AbstractPenaltiesTab<O extends Frame & DatabaseWindow, T extends RaceEntity> extends AbstractDatabaseTab<O, T> {
	public AbstractPenaltiesTab(Class<T> clazz, O win) {
		super(clazz, win, "tab.penalties"); //$NON-NLS-1$
		initialise();
	}

	private void initialise() {

	}
}