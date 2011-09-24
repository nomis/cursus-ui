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
package eu.lp0.cursus.ui.race;

import java.awt.Frame;

import eu.lp0.cursus.db.dao.RaceDAO;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.ui.common.CommonDetailWindow;
import eu.lp0.cursus.ui.component.DatabaseWindow;

public class RaceDetailWindow<O extends Frame & DatabaseWindow> extends CommonDetailWindow<O, Race> {
	public RaceDetailWindow(O win, String title, Race race) {
		super(win, title, new RaceDAO(), race);
	}
}