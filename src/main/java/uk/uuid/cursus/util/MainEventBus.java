/*
	cursus - Race series management program
	Copyright 2011, 2014  Simon Arlott

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU Affero General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU Affero General Public License for more details.

	You should have received a copy of the GNU Affero General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uk.uuid.cursus.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.uuid.cursus.util.com.google.common.eventbus.WeakEventBus;

public class MainEventBus {
	private static final Logger log = LoggerFactory.getLogger(MainEventBus.class);
	private static final WeakEventBus MAIN = new WeakEventBus();

	public static void register(Object o) {
		log.trace("Register {}", o); //$NON-NLS-1$
		MAIN.register(o);
	}

	public static void register(Object o, Object... events) {
		log.trace("Register {}", o); //$NON-NLS-1$
		MAIN.register(o, events);
	}

	public static void unregister(Object o) {
		log.trace("Unregister {}", o); //$NON-NLS-1$
		MAIN.unregister(o);
	}

	public static void post(Object event) {
		MAIN.post(event);
	}
}