/*
	cursus - Race series management program
	Copyright 2013  Simon Arlott

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

public class InvalidFilenameException extends InvalidDatabaseException {
	private String name;

	public InvalidFilenameException(String name) {
		super("Invalid filename: " + name); //$NON-NLS-1$
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static class Semicolon extends InvalidFilenameException {
		public Semicolon(String name) {
			super(name);
		}
	}

	public static class Suffix extends InvalidFilenameException {
		private String suffix;

		public Suffix(String name, String suffix) {
			super(name);
			this.suffix = suffix;
		}

		public String getSuffix() {
			return suffix;
		}
	}
}