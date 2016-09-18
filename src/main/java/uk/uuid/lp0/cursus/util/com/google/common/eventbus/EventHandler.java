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

/*
 * Copyright (C) 2007 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.uuid.lp0.cursus.util.com.google.common.eventbus;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.google.common.base.Preconditions;

/**
 * Wraps a single-argument 'handler' method on a specific object.
 * 
 * <p>
 * This class only verifies the suitability of the method and event type if something fails. Callers are expected to verify their uses of this class.
 * 
 * <p>
 * Two EventHandlers are equivalent when they refer to the same method on the same object (not class). This property is used to ensure that no handler method is
 * registered more than once.
 * 
 * @author Cliff Biffle
 */
class EventHandler {
	/** Object sporting the handler method. */
	private final WeakReference<Object> targetRef;
	private final int hashCode;
	/** Handler method. */
	private final Method method;

	/**
	 * Creates a new EventHandler to wrap {@code method} on @{code target}.
	 * 
	 * @param targetRef
	 *            object to which the method applies.
	 * @param method
	 *            handler method.
	 */
	EventHandler(WeakReference<Object> targetRef, Method method) {
		Object target = targetRef.get();
		Preconditions.checkNotNull(target, "EventHandler target cannot be null."); //$NON-NLS-1$
		Preconditions.checkNotNull(method, "EventHandler method cannot be null."); //$NON-NLS-1$

		this.targetRef = targetRef;
		hashCode = System.identityHashCode(target);
		this.method = method;
		method.setAccessible(true);
	}

	/**
	 * Invokes the wrapped handler method to handle {@code event}.
	 * 
	 * @param event
	 *            event to handle
	 * @throws InvocationTargetException
	 *             if the wrapped method throws any {@link Throwable} that is not an {@link Error} ({@code Error}s are
	 *             propagated as-is).
	 */
	public void handleEvent(Object event) throws InvocationTargetException {
		try {
			Object target = targetRef.get();
			if (target != null) {
				method.invoke(target, new Object[] { event });
			}
		} catch (IllegalArgumentException e) {
			throw new Error("Method rejected target/argument: " + event, e); //$NON-NLS-1$
		} catch (IllegalAccessException e) {
			throw new Error("Method became inaccessible: " + event, e); //$NON-NLS-1$
		} catch (InvocationTargetException e) {
			if (e.getCause() instanceof Error) {
				throw (Error)e.getCause();
			}
			throw e;
		}
	}

	@Override
	public String toString() {
		return "[wrapper " + method + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		return (PRIME + method.hashCode()) * PRIME + hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final EventHandler other = (EventHandler)obj;

		return method.equals(other.method) && targetRef.get() == other.targetRef.get();
	}

}
