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
package org.spka.cursus.scoring;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.ScoringSystem;

@ScoringSystem(uuid = Scoring2011.UUID, name = "SPKA 2011/12")
public class Scoring2011 extends Scoring2010 {
	@SuppressWarnings("hiding")
	public static final String UUID = "9f5cd14f-5eac-b854-03c3-1011a2bed527"; //$NON-NLS-1$

	@Override
	public int calculateFleetSize(Race race) {
		return calculateFleetSize(race.getEvent());
	}

	@Override
	public int calculateFleetSize(Event event) {
		return calculateFleetSize(event.getSeries());
	}
}