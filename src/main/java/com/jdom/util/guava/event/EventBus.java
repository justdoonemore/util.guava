/** 
 *  Copyright (C) 2012  Just Do One More
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jdom.util.guava.event;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

import com.jdom.logging.api.LogFactory;
import com.jdom.logging.api.Logger;

/**
 * @author djohnson
 * 
 */
public final class EventBus {

	private static final Logger log = LogFactory.getLogger(EventBus.class);

	private final com.google.common.eventbus.EventBus wrappedBus = new com.google.common.eventbus.EventBus();

	private final Set<Object> subscribers = Collections
			.synchronizedSet(Collections
					.newSetFromMap(new IdentityHashMap<Object, Boolean>()));

	private EventBus() {
	}

	/**
	 * @param subscriber
	 * @return true if the subscriber was registered, false if the subscriber
	 *         was already registered
	 * @throws IllegalArgumentException
	 *             when passed a null subscriber
	 */
	public boolean register(Object subscriber) {
		if (subscriber == null) {
			throw new IllegalArgumentException(
					"Cannot register a null subscriber!",
					new NullPointerException("subscriber"));
		}

		final boolean registered = subscribers.add(subscriber);
		if (registered) {
			wrappedBus.register(subscriber);
		}

		if (log.isDebugEnabled()) {
			log.debug(String.format(
					"Subscriber [%s] of type [%s] registered [%s]", System
							.identityHashCode(subscriber), subscriber
							.getClass().getName(), registered));
		}
		return registered;
	}

	/**
	 * @param subscriber
	 * @return true if the subscriber was unregistered, false if the subscriber
	 *         was never registered
	 */
	public boolean unregister(Object subscriber) {
		if (subscriber == null) {
			throw new IllegalArgumentException(
					"Cannot unregister a null subscriber!",
					new NullPointerException("subscriber"));
		}

		final boolean unregistered = subscribers.remove(subscriber);
		if (unregistered) {
			wrappedBus.unregister(subscriber);
		}
		if (log.isDebugEnabled()) {
			log.debug(String.format(
					"Subscriber [%s] of type [%s] unregistered [%s]", System
							.identityHashCode(subscriber), subscriber
							.getClass().getName(), unregistered));
		}
		return unregistered;
	}

	/**
	 * @param event
	 *            the object to publish
	 * @throws IllegalArgumentException
	 *             when passed a null event
	 */
	public void publish(Object event) {
		if (event == null) {
			throw new IllegalArgumentException("Cannot publish a null event!",
					new NullPointerException("event"));
		}

		if (log.isDebugEnabled()) {
			log.debug(String
					.format("Publishing event [%s] of type [%s] string representation [%s]",
							System.identityHashCode(event), event.getClass()
									.getName(), event.toString()));
		}

		wrappedBus.post(event);
	}

	/**
	 * Create a new {@link EventBus} instance.
	 * 
	 * @return the {@link EventBus}
	 */
	public static EventBus newInstance() {
		return new EventBus();
	}

}
