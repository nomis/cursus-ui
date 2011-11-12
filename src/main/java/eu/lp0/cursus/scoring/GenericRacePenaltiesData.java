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

import eu.lp0.cursus.db.data.Penalty;
import eu.lp0.cursus.db.data.Pilot;
import eu.lp0.cursus.db.data.Race;
import eu.lp0.cursus.db.data.RaceAttendee;

public class GenericRacePenaltiesData<T extends ScoredData> extends AbstractRacePenaltiesData<T> {
	public GenericRacePenaltiesData(T scores) {
		super(scores);
	}

	@Override
	public int getRacePenalties(Pilot pilot, Race race) {
		RaceAttendee attendee = race.getAttendees().get(pilot);
		if (attendee == null) {
			return 0;
		}

		// Count previous automatic penalties
		int autoPenalties = 0;
		for (Race previousRace : race.getEvent().getRaces()) {
			if (previousRace == race) {
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

		// Calculate race penalties
		int penalties = 0;
		for (Penalty penalty : attendee.getPenalties()) {
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
}