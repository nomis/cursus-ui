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

import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

import eu.lp0.cursus.db.data.Class;
import eu.lp0.cursus.db.data.Gender;
import eu.lp0.cursus.db.data.Pilot;

public abstract class FleetFilter implements Predicate<Pilot> {
	public abstract boolean apply(Pilot pilot);

	public final Set<Pilot> apply(Set<Pilot> pilots) {
		return Sets.filter(pilots, this);
	}

	public static FleetFilter any() {
		return new FleetFilter() {
			@Override
			public boolean apply(Pilot pilot) {
				return true;
			}
		};
	}

	public static FleetFilter from(final Set<Class> classes) {
		return new FleetFilter() {
			@Override
			public boolean apply(Pilot pilot) {
				return !Sets.intersection(pilot.getClasses(), classes).isEmpty();
			}
		};
	}

	public static FleetFilter from(final Gender gender) {
		return new FleetFilter() {
			@Override
			public boolean apply(Pilot pilot) {
				return gender == null || pilot.getGender() == gender || pilot.getGender() == null;
			}
		};
	}

	public static FleetFilter from(final Set<Class> classes, final Gender gender) {
		final FleetFilter classFilter = FleetFilter.from(classes);
		final FleetFilter genderFilter = FleetFilter.from(gender);

		return new FleetFilter() {
			@Override
			public boolean apply(Pilot pilot) {
				return genderFilter.apply(pilot) && classFilter.apply(pilot);
			}
		};
	}
}