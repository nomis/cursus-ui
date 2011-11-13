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
package eu.lp0.cursus.db;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public final class IDGenerator {
	private static final Supplier<AtomicLong> value = Suppliers.memoizeWithExpiration(new Supplier<AtomicLong>() {
		@Override
		public AtomicLong get() {
			return new AtomicLong(System.nanoTime());
		}
	}, 5, TimeUnit.MINUTES);

	private IDGenerator() {
	}

	public static final Long next() {
		return value.get().getAndIncrement();
	}
}