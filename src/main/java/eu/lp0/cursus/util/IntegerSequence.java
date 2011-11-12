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

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;

public class IntegerSequence implements Iterable<Integer> {
	private final int from;
	private final int to;
	private final int step;

	public IntegerSequence(int from, int to) {
		this(from, to, 1);
	}

	public IntegerSequence(int from, int to, int step) {
		Preconditions.checkArgument(step != 0);
		this.from = from;
		this.to = to;
		this.step = step;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new AbstractIterator<Integer>() {
			private int cur = from;

			@Override
			protected Integer computeNext() {
				if (step > 0) {
					if (from > to || cur > to) {
						return endOfData();
					}
				} else {
					if (from < to || cur < to) {
						return endOfData();
					}
				}
				try {
					return cur;
				} finally {
					cur += step;
				}
			}
		};
	}
}