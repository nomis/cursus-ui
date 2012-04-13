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
package eu.lp0.cursus.scoring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;

import eu.lp0.cursus.db.data.Event;
import eu.lp0.cursus.db.data.Penalty;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceAttendee;

public class GenericRacePenaltiesData<T extends ScoredData> extends AbstractRacePenaltiesData<T> {
	private final CumulativeMethod method;

	public enum CumulativeMethod {
		RACE, EVENT, SERIES;
	}

	public GenericRacePenaltiesData(T scores, CumulativeMethod method) {
		super(scores);

		this.method = method;
	}

	@Override
	protected int calculateRacePenalties(Pilot pilot, Race race) {
		RaceAttendee attendee = race.getAttendees().get(pilot);
		if (attendee == null) {
			return 0;
		}

		int autoPenalties = countPreviousAutomaticPenalties(pilot, race);
		int penalties = 0;
		for (Penalty penalty : Iterables.concat(Ordering.natural().sortedCopy(attendee.getPenalties()), getSimulatedRacePenalties(pilot, race))) {
			switch (penalty.getType()) {
			case AUTOMATIC:
				if (penalty.getValue() >= 0) {
					// Add multiple automatic penalties
					for (int i = 0; i < penalty.getValue(); i++) {
						penalties += ++autoPenalties;
					}
				} else {
					// Remove multiple automatic penalties
					for (int i = penalty.getValue(); i > 0; i--) {
						if (autoPenalties > 0) {
							penalties -= autoPenalties--;
						}
					}
				}
				break;

			case FIXED:
				// Fixed penalty added/removed
				penalties += penalty.getValue();
				break;

			case LAPS:
				// This is applied in RaceLapsData
				break;

			case EVENT_NON_ATTENDANCE:
				// This should only be applied in OverallPenaltiesData
				Preconditions.checkArgument(false, "Invalid race penalty type: " + penalty.getType()); //$NON-NLS-1$
				break;
			}
		}

		// Can't have negative race penalties because we need
		// to be able to remove them from the race points later
		// and negative race points doesn't make sense
		if (penalties < 0) {
			penalties = 0;
		}

		return penalties;
	}

	protected int countPreviousAutomaticPenalties(Pilot pilot, Race race) {
		int autoPenalties = 0;
		for (Race previousRace : findPreviousRaces(race)) {
			if (previousRace.equals(race)) {
				break;
			} else {
				RaceAttendee previousAttendee = previousRace.getAttendees().get(pilot);
				if (previousAttendee != null) {
					for (Penalty penalty : previousAttendee.getPenalties()) {
						if (penalty.getType() == Penalty.Type.AUTOMATIC) {
							autoPenalties += penalty.getValue();
							// Don't allow negative automatic penalties, that just gets silly
							if (autoPenalties < 0) {
								autoPenalties = 0;
							}
						}
					}
				}
			}
		}
		return autoPenalties;
	}

	protected final List<Race> findPreviousRaces(Race race) {
		List<Race> previousRaces = new ArrayList<Race>(scores.getRaces().size() * 2);
		switch (method) {
		case RACE:
			break;

		case EVENT:
			previousRaces.addAll(race.getEvent().getRaces());
			break;

		case SERIES:
			for (Event event : scores.getSeries().getEvents()) {
				previousRaces.addAll(event.getRaces());
			}
			break;
		}
		return previousRaces;
	}

	@Override
	protected List<Penalty> calculateSimulatedRacePenalties(Pilot pilot, Race race) {
		return Collections.emptyList();
	}
}