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
package eu.lp0.cursus.ui.event;

import java.awt.BorderLayout;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.ui.common.AbstractResultsTab;
import eu.lp0.cursus.ui.component.DatabaseWindow;

public class EventResultsTab extends AbstractResultsTab<Event> {
	public EventResultsTab(DatabaseWindow win) {
		super(Event.class, win);
		initialise();
	}

	private void initialise() {
		setLayout(new BorderLayout(0, 0));

	}

	@Override
	public void tabRefresh(Event event) {

	}

	@Override
	public void tabClear() {

	}
}