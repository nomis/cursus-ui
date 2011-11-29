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

import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import eu.lp0.cursus.i18n.Messages;

public class MemoryDatabase extends Database {
	private static final AtomicLong UNTITLED = new AtomicLong();

	public MemoryDatabase() throws SQLException, InvalidDatabaseException {
		super(Messages.getString("db.untitled", UNTITLED.incrementAndGet()), "jdbc:hsqldb:mem:" + UUID.randomUUID(), "SA", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

	@Override
	public boolean isSaved() {
		return false;
	}

	public synchronized void save() {

	}
}
