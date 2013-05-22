/*
	cursus - Race series management program
	Copyright 2011-2013  Simon Arlott

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

import java.sql.SQLException;
import java.util.UUID;

public class MemoryDatabase extends Database {
	public MemoryDatabase(String name) throws SQLException, InvalidDatabaseException {
		super(name, "jdbc:h2:mem:" + UUID.randomUUID(), "SA", "", Mode.FORCE_OPEN); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
	}

	@Override
	public boolean isSaved() {
		return false;
	}
}
