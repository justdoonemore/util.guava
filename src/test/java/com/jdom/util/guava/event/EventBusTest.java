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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.common.eventbus.Subscribe;

/**
 * @author djohnson
 * 
 */
public class EventBusTest {

	private static final String EVENT = "someString";

	private final EventBus eventBus = EventBus.newInstance();

	private String receivedEvent;

	@Test
	public void neverRegisteredSubscriberReturnsTrueWhenRegistering() {
		assertThat(eventBus.register(this), is(true));
	}

	@Test
	public void alreadyRegisteredSubscriberReturnsFalseWhenRegistering() {
		eventBus.register(this);

		assertThat(eventBus.register(this), is(false));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullSubscriberThrowsExceptionOnRegister() {
		eventBus.register(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullSubscriberThrowsExceptionOnUnregister() {
		eventBus.unregister(null);
	}

	@Test
	public void neverRegisteredSubscriberReturnsFalseWhenUnregistering() {
		assertThat(eventBus.unregister(this), is(false));
	}

	@Test
	public void registeredSubscriberReturnsTrueWhenUnregistering() {
		eventBus.register(this);

		assertThat(eventBus.unregister(this), is(true));
	}

	@Test
	public void registeredSubscriberReceivesEvent() {
		eventBus.register(this);
		eventBus.publish(EVENT);

		assertThat(this.receivedEvent, is(equalTo(EVENT)));
	}

	@Test
	public void neverRegisteredSubscriberDoesNotReceiveEvent() {
		eventBus.publish(EVENT);

		assertThat(this.receivedEvent, is(nullValue()));
	}

	@Test
	public void unregisteredSubscriberDoesNotReceiveEvent() {
		eventBus.register(this);
		eventBus.unregister(this);
		eventBus.publish(EVENT);

		assertThat(this.receivedEvent, is(nullValue()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void publishNullEventThrowsException() {
		eventBus.publish(null);

	}

	@Subscribe
	public void processEvent(String event) {
		this.receivedEvent = event;
	}
}
