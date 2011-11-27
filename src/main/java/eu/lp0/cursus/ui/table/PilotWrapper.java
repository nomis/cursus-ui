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
package eu.lp0.cursus.ui.table;

import com.google.common.base.Function;

import eu.lp0.cursus.db.data.Pilot;

class PilotWrapper {
	private static final Function<Pilot, PilotWrapper> FUNCTION = new Function<Pilot, PilotWrapper>() {
		@Override
		public PilotWrapper apply(Pilot input) {
			return new PilotWrapper(input);
		}
	};
	private final Pilot pilot;

	public PilotWrapper(Pilot pilot) {
		this.pilot = pilot;
	}

	public Pilot getPilot() {
		return pilot;
	}

	public static Function<Pilot, PilotWrapper> getFunction() {
		return FUNCTION;
	}

	@Override
	public String toString() {
		if (pilot == null) {
			return ""; //$NON-NLS-1$
		} else if (pilot.getRaceNumber() == null) {
			return pilot.getName();
		} else {
			return String.format("%s: %s", pilot.getRaceNumber(), pilot.getName()); //$NON-NLS-1$
		}
	}
}