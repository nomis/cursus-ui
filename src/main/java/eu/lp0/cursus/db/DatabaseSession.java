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

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.google.common.base.Preconditions;

public class DatabaseSession {
	private static final ThreadLocal<EntityManager> threads = new ThreadLocal<EntityManager>();
	private final Database database;

	DatabaseSession(Database database) {
		this.database = database;
	}

	void startSession() {
		Preconditions.checkState(threads.get() == null, "Session already open"); //$NON-NLS-1$
		threads.set(database.createEntityManager());
	}

	public static EntityManager getEntityManager() {
		EntityManager em = threads.get();
		Preconditions.checkState(em != null, "Session not open"); //$NON-NLS-1$
		return em;
	}

	public static EntityTransaction getTransaction() {
		return getEntityManager().getTransaction();
	}

	public static void begin() {
		getTransaction().begin();
	}

	public static boolean isActive() {
		return getTransaction().isActive();
	}

	public static void commit() {
		getTransaction().commit();
	}

	public static void rollback() {
		getTransaction().rollback();
	}

	void endSession() {
		getEntityManager().close();
		threads.set(null);
	}
}