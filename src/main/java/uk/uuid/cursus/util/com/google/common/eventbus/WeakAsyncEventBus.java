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

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

import com.google.common.annotations.Beta;

/**
 * An {@link WeakEventBus} that takes the Executor of your choice and uses it to
 * dispatch events, allowing dispatch to occur asynchronously.
 * 
 * @author Cliff Biffle
 * @since 10.0
 */
@Beta
public class WeakAsyncEventBus extends WeakEventBus {
	private final Executor executor;

	/** the queue of events is shared across all threads */
	private final ConcurrentLinkedQueue<EventWithHandler> eventsToDispatch = new ConcurrentLinkedQueue<EventWithHandler>();

	/**
	 * Creates a new AsyncEventBus that will use {@code executor} to dispatch
	 * events. Assigns {@code identifier} as the bus's name for logging purposes.
	 * 
	 * @param identifier
	 *            short name for the bus, for logging purposes.
	 * @param executor
	 *            Executor to use to dispatch events. It is the caller's
	 *            responsibility to shut down the executor after the last event has
	 *            been posted to this event bus.
	 */
	public WeakAsyncEventBus(String identifier, Executor executor) {
		super(identifier);
		this.executor = executor;
	}

	/**
	 * Creates a new AsyncEventBus that will use {@code executor} to dispatch
	 * events.
	 * 
	 * @param executor
	 *            Executor to use to dispatch events. It is the caller's
	 *            responsibility to shut down the executor after the last event has
	 *            been posted to this event bus.
	 */
	public WeakAsyncEventBus(Executor executor) {
		this.executor = executor;
	}

	@Override
	protected void enqueueEvent(Object event, EventHandler handler) {
		eventsToDispatch.offer(new EventWithHandler(event, handler));
	}

	/**
	 * Dispatch {@code events} in the order they were posted, regardless of
	 * the posting thread.
	 */
	@Override
	protected void dispatchQueuedEvents() {
		while (true) {
			EventWithHandler eventWithHandler = eventsToDispatch.poll();
			if (eventWithHandler == null) {
				break;
			}

			dispatch(eventWithHandler.event, eventWithHandler.handler);
		}
	}

	/**
	 * Calls the {@link #executor} to dispatch {@code event} to {@code handler}.
	 */
	@Override
	protected void dispatch(final Object event, final EventHandler handler) {
		executor.execute(new Runnable() {
			@Override
			@SuppressWarnings("synthetic-access")
			public void run() {
				WeakAsyncEventBus.super.dispatch(event, handler);
			}
		});
	}

}
