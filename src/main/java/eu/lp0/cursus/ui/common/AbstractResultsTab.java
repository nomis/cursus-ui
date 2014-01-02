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
package eu.lp0.cursus.ui.common;

import eu.lp0.cursus.db.data.RaceEntity;
import eu.lp0.cursus.ui.component.AbstractDatabaseTab;
import eu.lp0.cursus.ui.component.DatabaseWindow;

public abstract class AbstractResultsTab<T extends RaceEntity> extends AbstractDatabaseTab<T> {
	public AbstractResultsTab(Class<T> clazz, DatabaseWindow win) {
		super(clazz, win, "tab.results"); //$NON-NLS-1$
		initialise();
	}

	private void initialise() {

	}
}