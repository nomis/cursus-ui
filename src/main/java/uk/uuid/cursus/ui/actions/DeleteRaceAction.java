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

import uk.uuid.cursus.ui.component.DatabaseWindow;
import uk.uuid.cursus.db.dao.RaceDAO;
import uk.uuid.cursus.db.data.Race;

public class DeleteRaceAction extends AbstractDeleteAction<Race> {
	private static final RaceDAO raceDAO = new RaceDAO();

	public DeleteRaceAction(DatabaseWindow win, Race race) {
		super("menu.race.delete", false, win, race); //$NON-NLS-1$
	}

	@Override
	protected void doDelete(Race item) {
		Race race = raceDAO.get(item);
		race.getEvent().getRaces().remove(race);
		raceDAO.remove(race);
	}

	@Override
	protected void doRefresh(DatabaseWindow win) {
		win.refreshRaceList();
	}
}