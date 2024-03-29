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

package uk.uuid.cursus.util.com.google.common.eventbus;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

/**
 * A {@link HandlerFindingStrategy} for collecting all event handler methods
 * that are marked with the {@link Subscribe} annotation.
 * 
 * @author Cliff Biffle
 */
class AnnotatedHandlerFinder implements HandlerFindingStrategy {

	/**
	 * {@inheritDoc}
	 * 
	 * This implementation finds all methods marked with a {@link Subscribe} annotation.
	 */
	@Override
	public Multimap<Class<?>, EventHandler> findAllHandlers(WeakReference<Object> listenerRef) {
		Object listener = listenerRef.get();
		Preconditions.checkNotNull(listener);
		Multimap<Class<?>, EventHandler> methodsInListener = HashMultimap.create();
		Class<?> clazz = listener.getClass();
		while (clazz != null) {
			for (Method method : clazz.getMethods()) {
				Subscribe annotation = method.getAnnotation(Subscribe.class);

				if (annotation != null) {
					Class<?>[] parameterTypes = method.getParameterTypes();
					if (parameterTypes.length != 1) {
						throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation, but requires " + parameterTypes.length //$NON-NLS-1$ //$NON-NLS-2$
								+ " arguments.  Event handler methods " + "must require a single argument."); //$NON-NLS-1$ //$NON-NLS-2$
					}
					Class<?> eventType = parameterTypes[0];
					EventHandler handler = makeHandler(listenerRef, method);

					methodsInListener.put(eventType, handler);
				}
			}
			clazz = clazz.getSuperclass();
		}
		return methodsInListener;
	}

	/**
	 * Creates an {@code EventHandler} for subsequently calling {@code method} on {@code listener}.
	 * Selects an EventHandler implementation based on the annotations on {@code method}.
	 * 
	 * @param listener
	 *            object bearing the event handler method.
	 * @param method
	 *            the event handler method to wrap in an EventHandler.
	 * @return an EventHandler that will call {@code method} on {@code listener} when invoked.
	 */
	private static EventHandler makeHandler(WeakReference<Object> listener, Method method) {
		EventHandler wrapper;
		if (methodIsDeclaredThreadSafe(method)) {
			wrapper = new EventHandler(listener, method);
		} else {
			wrapper = new SynchronizedEventHandler(listener, method);
		}
		return wrapper;
	}

	/**
	 * Checks whether {@code method} is thread-safe, as indicated by the {@link AllowConcurrentEvents} annotation.
	 * 
	 * @param method
	 *            handler method to check.
	 * @return {@code true} if {@code handler} is marked as thread-safe, {@code false} otherwise.
	 */
	private static boolean methodIsDeclaredThreadSafe(Method method) {
		return method.getAnnotation(AllowConcurrentEvents.class) != null;
	}
}
