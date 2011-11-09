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
package eu.lp0.cursus.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntegerSequence implements Iterable<Integer> {
	private int from;
	private int to;
	private int step;

	public IntegerSequence(int from, int to) {
		this(from, to, 1);
	}

	public IntegerSequence(int from, int to, int step) {
		this.from = from;
		this.to = to;
		this.step = step;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			private int cur = from;

			@Override
			public boolean hasNext() {
				if (step > 0) {
					return cur < to && cur + step <= to;
				} else if (step < 0) {
					return cur > from && cur + step >= to;
				} else {
					return false;
				}
			}

			@Override
			public Integer next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}

				try {
					return cur;
				} finally {
					cur += step;
				}
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}