/*
 * Copyright 2013 Marcus Behrendt & Robert Friedrichs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package de.behrfried.wikianalyzer.util.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import de.behrfried.wikianalyzer.util.data.DataContainer;

/**
 * Various tests on {@link Event}
 * 
 * @author marcus
 * 
 */
public class EventTest {

	/**
	 * Tests whether {@link Handler}s can be added to an {@link Event} properly.
	 */
	@Test
	public void testAddHandler() {

		/*
		 * at the end of this test this number of Handlers are expected in the
		 * Event
		 */
		final int expected = 10;

		/* Object that contains the Event */
		final Dummy dummy = new Dummy();

		/* add as many Handlers to the Event as expected */
		for (int i = 0; i < expected; i++) {
			dummy.getSimpleEvent().addHandler(new Handler<EventArgs>() {
				public void invoke(Object sender, EventArgs e) {
				}
			});
		}

		/*
		 * test the result
		 */
		assertEquals(expected, dummy.getSimpleEvent().size());
	}

	/**
	 * Tests whether {@link Handler}s can be removed from an {@link Event}
	 * properly after they have been added to it.
	 */
	@Test
	public void removeHandler() {

		/* at first a number Handlers will be added to the Event */
		final int add1 = 15;

		/*
		 * at second a number of randomly chosen Handlers will be removed from
		 * the Event
		 */
		final int rem = 6;

		/* at third a number of Handlers again will be added to the Event */
		final int add2 = 10;

		/* Object that contains the Event */
		final Dummy dummy = new Dummy();

		/* List containing HandlerRegistrations */
		final List<Event<EventArgs>.EventHandlerRegistration> regList = new ArrayList<Event<EventArgs>.EventHandlerRegistration>();

		/* add the first Handlers to the Event */
		for (int i = 0; i < add1; i++) {
			/*
			 * 'add' returns a EventHandlerRegistration that is stored in the
			 * list
			 */
			regList.add(dummy.getSimpleEvent().addHandler(
					new Handler<EventArgs>() {
						public void invoke(Object sender, EventArgs e) {
						}
					}));
		}

		/*
		 * Pseudo Random Number Generator for helping to remove Handlers from
		 * the Event
		 */
		final Random r = new Random();

		/* remove Handlers from the Event */
		for (int i = 0; i < rem; i++) {
			/* */
			regList.remove(r.nextInt(regList.size())).removeHandler();
		}

		/* add Handlers again to the event */
		for (int i = 0; i < add2; i++) {
			/*
			 * EventHandlerRegistration is not needed here since test is almost
			 * finished
			 */
			dummy.getSimpleEvent().addHandler(new Handler<EventArgs>() {
				public void invoke(Object sender, EventArgs e) {
				}
			});
		}

		/*
		 * test the result
		 */
		assertEquals(add1 + add2 - rem, dummy.getSimpleEvent().size());
	}

	/**
	 * Tests whether an {@link Event} fires properly under various
	 * circumstances.
	 */
	@Test
	public void testFire() {

		/*
		 * at first a number of Handlers are to be added to the Event each
		 * Handler increments the variable 'actual' and at the end expected' and
		 * 'actual' should be equal
		 */
		final int expected = 10;
		final DataContainer<Integer> actual = new DataContainer<Integer>(0);

		/* Object that contains the Event */
		final Dummy dummy = new Dummy();

		/* add the first Handlers to the Event */
		for (int i = 0; i < expected; i++) {
			/*
			 * EventHandlerRegistration is not needed here since Handlers aren't
			 * to be removed
			 */
			dummy.getSimpleEvent().addHandler(new Handler<EventArgs>() {

				/**
				 * Callback method to be invoked when Handler fires.
				 * 
				 * @param sender
				 *            the Object which fires the Event
				 * @param e
				 *            additional argument
				 */
				public void invoke(Object sender, EventArgs e) {
					/* when the Event is fired actuasl's value is to be changed */
					actual.setValue(actual.getValue() + 1);
				}
			});
		}

		/*
		 * let the dummy raise the Event
		 */
		dummy.raiseSimpleEvent();

		/*
		 * test the result
		 */
		assertEquals(expected, actual.getValue().intValue());

		/* Test fire without permission */
		try {
			/* try to pass a null value */
			dummy.getSimpleEvent().fire(null, this, EventArgs.EMPTY);
			fail("Can fire without having the right initContext!");
		} catch (Exception e) {
		}
		try {
			/* try to pass an Object */
			dummy.getSimpleEvent().fire(new Object(), this, EventArgs.EMPTY);
			fail("Can fire without having the right initContext!");
		} catch (Exception e) {
		}
	}
}

class Dummy {

	private final Object initContext = new Object();
	private final Event<EventArgs> simpleEvent = new Event<EventArgs>(
			this.initContext);

	public Event<EventArgs> getSimpleEvent() {
		return this.simpleEvent;
	}

	public void raiseSimpleEvent() {
		this.simpleEvent.fire(this.initContext, this, EventArgs.EMPTY);
	}

}