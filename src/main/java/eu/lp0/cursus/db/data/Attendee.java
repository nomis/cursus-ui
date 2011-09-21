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
package eu.lp0.cursus.db.data;

import eu.lp0.cursus.util.Messages;

/**
 * Race attendees who are pilots
 * 
 * The fleet consists only of the PILOTs
 * 
 * Mandatory/voluntary participation in other activities may affect the scores of pilots who didn't compete
 */
public enum Attendee {
	/** Pilot racing */
	PILOT ("attendee.pilot"), //$NON-NLS-1$

	/** Mandatory race master */
	M_RACE_MASTER ("attendee.race-master.mandatory"), //$NON-NLS-1$

	/** Mandatory scorer */
	M_SCORER ("attendee.scorer.mandatory"), //$NON-NLS-1$

	/** Mandatory marshal */
	M_MARSHAL ("attendee.marshal.mandatory"), //$NON-NLS-1$

	/** Voluntary race master */
	V_RACE_MASTER ("attendee.race-master.voluntary"), //$NON-NLS-1$

	/** Voluntary scorer */
	V_SCORER ("attendee.scorer.voluntary"), //$NON-NLS-1$

	/** Voluntary marshal */
	V_MARSHAL ("attendee.marshal.voluntary"), //$NON-NLS-1$

	/** Other participant */
	OTHER ("attendee.other"); //$NON-NLS-1$ 

	private final String key;

	private Attendee(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return Messages.getString(key);
	}
}