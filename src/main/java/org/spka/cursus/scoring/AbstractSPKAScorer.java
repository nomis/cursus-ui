/*
	cursus - Race series management program
	Copyright 2012  Simon Arlott

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

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.scoring.data.Scores;
import eu.lp0.cursus.scoring.scorer.AbstractScorer;

public abstract class AbstractSPKAScorer extends AbstractScorer {
	/**
	 * Race scores include all pilots at the event
	 */
	@Override
	public Scores scoreRace(Race race, Predicate<Pilot> fleetFilter) {
		return super.scoreRace(race, Sets.filter(race.getEvent().getAllPilots(), fleetFilter), fleetFilter);
	}
}