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

package eu.lp0.cursus.util.com.google.common.eventbus;

import java.lang.ref.WeakReference;

import com.google.common.collect.Multimap;

/**
 * A method for finding event handler methods in objects, for use by {@link WeakEventBus}.
 * 
 * @author Cliff Biffle
 */
interface HandlerFindingStrategy {

	/**
	 * Finds all suitable event handler methods in {@code source}, organizes them
	 * by the type of event they handle, and wraps them in {@link EventHandler}s.
	 * 
	 * @param source
	 *            object whose handlers are desired.
	 * @return EventHandler objects for each handler method, organized by event
	 *         type.
	 * 
	 * @throws IllegalArgumentException
	 *             if {@code source} is not appropriate for
	 *             this strategy (in ways that this interface does not define).
	 */
	Multimap<Class<?>, EventHandler> findAllHandlers(WeakReference<Object> source);

}
