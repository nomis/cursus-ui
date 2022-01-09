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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Wraps a single-argument 'handler' method on a specific object, and ensures
 * that only one thread may enter the method at a time.
 * 
 * <p>
 * Beyond synchronization, this class behaves identically to {@link EventHandler}.
 * 
 * @author Cliff Biffle
 */
class SynchronizedEventHandler extends EventHandler {
	/**
	 * Creates a new SynchronizedEventHandler to wrap {@code method} on {@code target}.
	 * 
	 * @param target
	 *            object to which the method applies.
	 * @param method
	 *            handler method.
	 */
	public SynchronizedEventHandler(WeakReference<Object> target, Method method) {
		super(target, method);
	}

	@Override
	public synchronized void handleEvent(Object event) throws InvocationTargetException {
		super.handleEvent(event);
	}

}
