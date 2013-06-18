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

package de.behrfried.wikianalyzer.wawebapp.client.list;

import de.behrfried.wikianalyzer.wawebapp.client.util.data.DataContainer;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.Handler;
import de.behrfried.wikianalyzer.wawebapp.client.util.list.DefaultObservableList;
import de.behrfried.wikianalyzer.wawebapp.client.util.list.ListChangedEventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.util.list.ObservableArrayList;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Various tests on {@link ObservableArrayList}.
 * 
 * @author marcus
 * 
 */
public class ObservableArrayListTest {

	/**
	 * the {@link ObservableArrayList} on which the tests are driven
	 */
	private ObservableArrayList<Object> observableArrayList;

	/**
	 * assigns a new {@link ObservableArrayList} before each test.
	 */
	@Before
	public void setUp() {
		this.observableArrayList = new ObservableArrayList<Object>();
	}

	/**
	 * Tests whether elements can be added and callbacks are invoked.
	 */
	@Test
	public void testAddE() {

		/* this is the object we want to insert later */
		final Object objectToAdd = new Object();

		/*
		 * inokeCntr is used to count the actual invocations of the callback
		 * method when the object is added
		 */
		final DataContainer<Integer> invokeCntr = new DataContainer<Integer>(0);

		/*
		 * now we register on the list's Event when items changed
		 */
		this.observableArrayList.listChanged().addHandler(new Handler<ListChangedEventArgs<Object>>() {

			public void invoke(final Object sender, final ListChangedEventArgs<Object> e) {

				/* assert ListChangedType is ADD_REMOVE */
				Assert.assertEquals(ListChangedEventArgs.ListChangedAction.ADD_REMOVE, e.getListChangedAction());

				/*
				 * assert getOldItems is null because no elements has been
				 * removed
				 */
				Assert.assertNull(e.getOldItems());

				/*
				 * assert getNewItems is not null because new elements has been
				 * added
				 */
				Assert.assertNotNull(e.getNewItems());

				/* assert the new items list's size is 1 */
				Assert.assertEquals(1, e.getNewItems().size());

				/* assert the new item is the expected one */
				Assert.assertEquals(objectToAdd, e.getNewItems().get(0));

				/*
				 * in the end increment the counter to assert that this method
				 * isn't invoked twice
				 */
				invokeCntr.setValue(invokeCntr.getValue() + 1);
			}
		});

		/*
		 * now add the add the object to the list
		 */
		this.observableArrayList.add(objectToAdd);

		/* assert the callback method was only invoked once */
		Assert.assertEquals(1, invokeCntr.getValue().intValue());

		/* assert the list has the correct size after insertion */
		Assert.assertEquals(1, this.observableArrayList.size());

		/* assert the list's first object is the expected one */
		Assert.assertEquals(objectToAdd, this.observableArrayList.get(0));
	}

	/**
	 * Tests whether elements can be added at a specific position and callbacks
	 * are invoked.
	 */
	@Test
	public void testAddIntE() {

		/* at first add two objects to the list */
		this.observableArrayList.add(new Object());
		this.observableArrayList.add(new Object());

		/* this is the object we want to insert later */
		final Object objectToAdd = new Object();

		/*
		 * invokeCntr is used to count the actual invocations of the callback
		 * method when the object is added
		 */
		final DataContainer<Integer> invokeCntr = new DataContainer<Integer>(0);

		/*
		 * now we register on the list's Event when items changed
		 */
		this.observableArrayList.listChanged().addHandler(new Handler<ListChangedEventArgs<Object>>() {

			/*
			 * This method is invoked after the object has been added
			 */
			public void invoke(final Object sender, final ListChangedEventArgs<Object> e) {

				/* assert ListChangedType is ADD_REMOVE */
				Assert.assertEquals(ListChangedEventArgs.ListChangedAction.ADD_REMOVE, e.getListChangedAction());

				/*
				 * assert getOldItems is null because no elements has been
				 * removed
				 */
				Assert.assertNull(e.getOldItems());

				/*
				 * assert getNewItems is not null because new elements has been
				 * added
				 */
				Assert.assertNotNull(e.getNewItems());

				/* assert the new items list's size is 1 */
				Assert.assertEquals(1, e.getNewItems().size());

				/* assert the new item is the expected one */
				Assert.assertEquals(objectToAdd, e.getNewItems().get(0));

				/*
				 * in the end increment the counter to assert that this method
				 * isn't invoked twice
				 */
				invokeCntr.setValue(invokeCntr.getValue() + 1);
			}
		});

		/*
		 * now add the insert the object in the list at index 0
		 */
		this.observableArrayList.add(0, objectToAdd);

		/* assert the callback method was only invoked once */
		Assert.assertEquals(1, invokeCntr.getValue().intValue());

		/* assert the list has the correct size after insertion */
		Assert.assertEquals(3, this.observableArrayList.size());

		/* assert the list's first object is the expected one */
		Assert.assertEquals(objectToAdd, this.observableArrayList.get(0));

		/* try to insert at a position that is not allowed */

		/*
		 * first create a copy of the current list to check it again after
		 * exception testing
		 */
		final DefaultObservableList<Object> copiedList = new ObservableArrayList<Object>(this.observableArrayList);
		try {
			/* at a negative index */
			this.observableArrayList.add(-1, new Object());
			Assert.fail("Can add items at negative index.");
		} catch(final Exception e) {}
		try {
			/*
			 * at an index greater than the greatest accessible index of the
			 * list
			 */
			this.observableArrayList.add(this.observableArrayList.size() + 1, new Object());
			Assert.fail("Can add items at index greater than the greatest index of the list.");
		} catch(final Exception e) {}

		/* test whether the list still has the same size after exception testing */
		Assert.assertEquals(3, this.observableArrayList.size());

		/* test whether the list's first object is still the expected one */
		Assert.assertEquals(objectToAdd, this.observableArrayList.get(0));

		/* test whether the list still is unchanged after exception testing */
		Assert.assertTrue(this.observableArrayList.containsAll(copiedList));
	}

	/**
	 * Tests whether collections can be added and callbacks are invoked.
	 */
	@Test
	public void testAddAllCollectionOfQextendsE() {

		/* this lists is to be added to the observable list */
		final List<Object> objectsToAdd = new ArrayList<Object>();

		/* add elements to the list to be added */
		for(int i = 0; i < 20; i++) {
			objectsToAdd.add(new Object());
		}

		/* insert a few elements into the observable list */
		for(int i = 0; i < 10; i++) {
			this.observableArrayList.add(new Object());
		}

		/*
		 * inokeCntr is used to count the actual invocations of the callback
		 * method when the object is added
		 */
		final DataContainer<Integer> invokeCntr = new DataContainer<Integer>(0);

		/*
		 * now we register on the list's Event when items changed
		 */
		this.observableArrayList.listChanged().addHandler(new Handler<ListChangedEventArgs<Object>>() {

			/*
			 * This method is invoked after the object has been added
			 */
			public void invoke(final Object sender, final ListChangedEventArgs<Object> e) {

				/* assert ListChangedType is ADD_REMOVE */
				Assert.assertEquals(ListChangedEventArgs.ListChangedAction.ADD_REMOVE, e.getListChangedAction());

				/*
				 * assert getOldItems is null because no elements has been
				 * removed
				 */
				Assert.assertNull(e.getOldItems());

				/*
				 * assert getNewItems is not null because new elements has been
				 * added
				 */
				Assert.assertNotNull(e.getNewItems());

				/* assert the new items list's size is correct */
				Assert.assertEquals(objectsToAdd.size(), e.getNewItems().size());

				/* assert the new items are the expected ones */
				Assert.assertThat(e.getNewItems(), CoreMatchers.is(CoreMatchers.equalTo(objectsToAdd)));

				/*
				 * in the end increment the counter to assert that this method
				 * isn't invoked twice
				 */
				invokeCntr.setValue(invokeCntr.getValue() + 1);
			}
		});

		/*
		 * add the list to the observable list
		 */
		this.observableArrayList.addAll(objectsToAdd);

		/* assert the callback method was only invoked once */
		Assert.assertEquals(1, invokeCntr.getValue().intValue());

		/* assert the list has the correct size after insertion */
		Assert.assertEquals(30, this.observableArrayList.size());

		/* assert all newly added elements are in the list */
		Assert.assertTrue(this.observableArrayList.containsAll(objectsToAdd));
	}

	/**
	 * Tests whether collections can be added at specific position and callbacks
	 * are invoked.
	 */
	@Test
	public void testAddAllIntCollectionOfQextendsE() {

		/* this lists is to be added to the observable list */
		final List<Object> objectsToAdd = new ArrayList<Object>();

		/* add elements to the list to be added */
		for(int i = 0; i < 20; i++) {
			objectsToAdd.add(new Object());
		}

		/* insert a few elements into the observable list */
		for(int i = 0; i < 10; i++) {
			this.observableArrayList.add(new Object());
		}

		/*
		 * inokeCntr is used to count the actual invocations of the callback
		 * method when the object is added
		 */
		final DataContainer<Integer> invokeCntr = new DataContainer<Integer>(0);

		/*
		 * now we register on the list's Event when items changed
		 */
		this.observableArrayList.listChanged().addHandler(new Handler<ListChangedEventArgs<Object>>() {

			/*
			 * This method is invoked after the object has been added
			 */
			public void invoke(final Object sender, final ListChangedEventArgs<Object> e) {

				/* assert ListChangedType is ADD_REMOVE */
				Assert.assertEquals(ListChangedEventArgs.ListChangedAction.ADD_REMOVE, e.getListChangedAction());

				/*
				 * assert getOldItems is null because no elements has been
				 * removed
				 */
				Assert.assertNull(e.getOldItems());

				/*
				 * assert getNewItems is not null because new elements has been
				 * added
				 */
				Assert.assertNotNull(e.getNewItems());

				/* assert the new items list's size is correct */
				Assert.assertEquals(objectsToAdd.size(), e.getNewItems().size());

				/* assert the new items are the expected ones */
				Assert.assertThat(e.getNewItems(), CoreMatchers.is(CoreMatchers.equalTo(objectsToAdd)));

				/*
				 * in the end increment the counter to assert that this method
				 * isn't invoked twice
				 */
				invokeCntr.setValue(invokeCntr.getValue() + 1);
			}
		});

		/*
		 * add the list to the observable list
		 */
		this.observableArrayList.addAll(5, objectsToAdd);

		/* assert the callback method was only invoked once */
		Assert.assertEquals(1, invokeCntr.getValue().intValue());

		/* assert the list has the correct size after insertion */
		Assert.assertEquals(30, this.observableArrayList.size());

		/* assert all newly added elements are in the list */
		Assert.assertTrue(this.observableArrayList.containsAll(objectsToAdd));

		/* try to insert at a position that is not allowed */
		/*
		 * first create a copy of the current list to check it again after
		 * exception testing
		 */
		final DefaultObservableList<Object> copiedList = new ObservableArrayList<Object>(this.observableArrayList);
		try {
			/* at a negative index */
			this.observableArrayList.addAll(-1, objectsToAdd);
			Assert.fail("Can add items at negative index.");
		} catch(final Exception e) {}
		try {
			/*
			 * at an index greater than the greatest accessible index of the
			 * list
			 */
			this.observableArrayList.addAll(this.observableArrayList.size() + 1, objectsToAdd);
			Assert.fail("Can add items at index greater than the greatest index of the list.");
		} catch(final Exception e) {}

		/* test whether the list still has the same size after exception testing */
		Assert.assertEquals(30, this.observableArrayList.size());

		/* assert all newly added elements are still in the list */
		Assert.assertTrue(this.observableArrayList.containsAll(objectsToAdd));

		/* test whether the list still is unchanged after exception testing */
		Assert.assertTrue(this.observableArrayList.containsAll(copiedList));

	}

	/**
	 * Tests whether list can be cleared and callback are invoked.
	 */
	@Test
	public void testClear() {

		/* first add a few elements to the list */
		for(int i = 0; i < 20; i++) {
			this.observableArrayList.add(new Object());
		}

		/* assert the list's size is 20 */
		Assert.assertEquals(20, this.observableArrayList.size());

		/*
		 * inokeCntr is used to count the actual invocations of the callback
		 * method when the object is added
		 */
		final DataContainer<Integer> invokeCntr = new DataContainer<Integer>(0);

		/*
		 * now we register on the list's Event when items changed
		 */
		this.observableArrayList.listChanged().addHandler(new Handler<ListChangedEventArgs<Object>>() {

			/*
			 * This method is invoked after the object has been added
			 */
			public void invoke(final Object sender, final ListChangedEventArgs<Object> e) {

				/* assert ListChangedType is ADD_REMOVE */
				Assert.assertEquals(ListChangedEventArgs.ListChangedAction.CLEAR, e.getListChangedAction());

				/*
				 * assert getOldItems is null because the list has been cleared
				 */
				Assert.assertNull(e.getOldItems());

				/*
				 * assert getNewItems is not null because the list has been
				 * cleared
				 */
				Assert.assertNull(e.getNewItems());

				/*
				 * in the end increment the counter to assert that this method
				 * isn't invoked twice
				 */
				invokeCntr.setValue(invokeCntr.getValue() + 1);
			}
		});

		/*
		 * now clear the list
		 */
		this.observableArrayList.clear();

		/* assert this method is only invoked once */
		Assert.assertEquals(1, invokeCntr.getValue().intValue());

		/* assert the lists size is now 0 */
		Assert.assertEquals(0, this.observableArrayList.size());

	}

	/**
	 * Tests whether {@code contains()} works properly.
	 */
	@Test
	public void testContains() {

		/* store the objects to add in an array */
		final Object[] objectsToAdd = new Object[20];

		/* add objects to the list */
		for(int i = 0; i < objectsToAdd.length; i++) {
			objectsToAdd[i] = new Object();
			this.observableArrayList.add(objectsToAdd[i]);
		}

		/* now test whether each added object is in the list */
		for(final Object element : objectsToAdd) {
			Assert.assertTrue(this.observableArrayList.contains(element));
		}

		/* assert that not added objects aren't in the list */
		for(int i = 0; i < 10; i++) {
			Assert.assertFalse(this.observableArrayList.contains(new Object()));
		}
	}

	/**
	 * Tests whether {@code containsAll()} works properly.
	 */
	@Test
	public void testContainsAll() {

		/* store the objects to add in an array */
		final ArrayList<Object> objectsToAdd = new ArrayList<Object>();

		/* add objects to the list */
		for(int i = 0; i < 20; i++) {
			final Object objectToAdd = new Object();
			objectsToAdd.add(objectToAdd);
			Assert.assertTrue(this.observableArrayList.add(objectToAdd));
		}

		/* now test whether each added object is in the list */
		Assert.assertTrue(this.observableArrayList.containsAll(objectsToAdd));

		/* assert that not added objects aren't in the list */
		for(int i = 0; i < 10; i++) {
			Assert.assertFalse(this.observableArrayList.contains(new Object()));
		}
	}

	/**
	 * Tests whether elements can be retrieved from the list.
	 */
	@Test
	public void testGet() {

		/* store the objects to add in an array */
		final Object[] objectsToAdd = new Object[20];

		/* add objects to the list */
		for(int i = 0; i < objectsToAdd.length; i++) {
			objectsToAdd[i] = new Object();
			this.observableArrayList.add(objectsToAdd[i]);
		}

		/* now test whether each added object can be retrieved */
		for(int i = 0; i < objectsToAdd.length; i++) {
			Assert.assertEquals(objectsToAdd[i], this.observableArrayList.get(i));
		}

		/* try to retrieve elements with an index out of bounds */
		try {
			/* at a negative index */
			this.observableArrayList.get(-1);
			Assert.fail("Can get items at negative index.");
		} catch(final Exception e) {}
		try {
			/*
			 * at an index greater than the greatest accessible index of the
			 * list
			 */
			this.observableArrayList.get(this.observableArrayList.size());
			Assert.fail("Can get items at index greater than the greatest index of the list.");
		} catch(final Exception e) {}
	}

	/**
	 * Tests whether positions of elements can be retrieved.
	 */
	@Test
	public void testIndexOf() {

		/* store the objects to add in an array */
		final Object[] objectsToAdd = new Object[20];

		/* add objects to the list */
		for(int i = 0; i < objectsToAdd.length; i++) {
			objectsToAdd[i] = new Object();
			this.observableArrayList.add(objectsToAdd[i]);
		}

		/* again add the same objects to the list */
		for(final Object element : objectsToAdd) {
			this.observableArrayList.add(element);
		}

		/* Test whether the first index of an object is returned properly */
		for(int i = 0; i < objectsToAdd.length; i++) {
			Assert.assertEquals(i, this.observableArrayList.indexOf(objectsToAdd[i]));
		}

		/* try to retrieve the index of an element that isn't in the list */
		Assert.assertEquals(-1, this.observableArrayList.indexOf(new Object()));
	}

	/**
	 * Tests whether {@code isEmpty()} works properly.
	 */
	@Test
	public void testIsEmpty() {

		/* assert the list is empty at initial state */
		Assert.assertTrue(this.observableArrayList.isEmpty());

		/* add objects to the list */
		for(int i = 0; i < 20; i++) {
			this.observableArrayList.add(new Object());

			/* assert the list is not empty */
			Assert.assertFalse(this.observableArrayList.isEmpty());
		}

		/* clear the list */
		this.observableArrayList.clear();

		/* assert the list is empty again */
		Assert.assertTrue(this.observableArrayList.isEmpty());

		/* again add objects to the list */
		for(int i = 0; i < 20; i++) {
			this.observableArrayList.add(new Object());

			/* assert list is not empty */
			Assert.assertFalse(this.observableArrayList.isEmpty());
		}

		/* remove all elements separately */
		for(int i = 0; i < 20; i++) {
			/* assert the list is not empty */
			Assert.assertFalse(this.observableArrayList.isEmpty());

			this.observableArrayList.remove(0);
		}

		/* assert the list is empty again */
		Assert.assertTrue(this.observableArrayList.isEmpty());
	}

	/**
	 * Tests whether the last index of an elements is correct.
	 */
	@Test
	public void testLastIndexOf() {

		/* store the objects to add in an array */
		final Object[] objectsToAdd = new Object[20];

		/* add objects to the list */
		for(int i = 0; i < objectsToAdd.length; i++) {
			objectsToAdd[i] = new Object();
			this.observableArrayList.add(objectsToAdd[i]);
		}

		/* again add the same objects to the list */
		for(final Object element : objectsToAdd) {
			this.observableArrayList.add(element);
		}

		/* Test whether the last index of an object is returned properly */
		for(int i = 0; i < objectsToAdd.length; i++) {
			Assert.assertEquals(i + objectsToAdd.length, this.observableArrayList.lastIndexOf(objectsToAdd[i]));
		}

		/* try to retrieve the index of an element that isn't in the list */
		Assert.assertEquals(-1, this.observableArrayList.indexOf(new Object()));
	}

	/**
	 * Tests whether elements can be removed and callbacks are invoked.
	 */
	@Test
	public void testRemoveObject() {
		/* this is the object we want to remove later */
		final Object objectToRemove = new Object();

		/* insert the object that is to be removed later */
		this.observableArrayList.add(objectToRemove);

		/*
		 * inokeCntr is used to count the actual invocations of the callback
		 * method when the object is added
		 */
		final DataContainer<Integer> invokeCntr = new DataContainer<Integer>(0);

		/*
		 * now we register on the list's Event when items changed
		 */
		this.observableArrayList.listChanged().addHandler(new Handler<ListChangedEventArgs<Object>>() {

			public void invoke(final Object sender, final ListChangedEventArgs<Object> e) {

				/* assert ListChangedType is ADD_REMOVE */
				Assert.assertEquals(ListChangedEventArgs.ListChangedAction.ADD_REMOVE, e.getListChangedAction());

				/*
				 * assert getOldItems is not null because an element has been
				 * removed
				 */
				Assert.assertNotNull(e.getOldItems());

				/* assert the old items list's size is 1 */
				Assert.assertEquals(1, e.getOldItems().size());

				/*
				 * assert getNewItems is null because no new elements has been
				 * added
				 */
				Assert.assertNull(e.getNewItems());

				/* assert the old item is the expected one */
				Assert.assertEquals(objectToRemove, e.getOldItems().get(0));

				/*
				 * in the end increment the counter to assert that this method
				 * isn't invoked twice
				 */
				invokeCntr.setValue(invokeCntr.getValue() + 1);
			}
		});

		/*
		 * now remove the object to the list
		 */
		this.observableArrayList.remove(objectToRemove);

		/* assert the callback method was only invoked once */
		Assert.assertEquals(1, invokeCntr.getValue().intValue());

		/* assert the list has the correct size after insertion */
		Assert.assertEquals(0, this.observableArrayList.size());
	}

	/**
	 * Tests whether elements can be removed at an specific position and
	 * callbacks are invoked.
	 */
	@Test
	public void testRemoveInt() {
		/* at first add two objects to the list */
		this.observableArrayList.add(new Object());
		this.observableArrayList.add(new Object());

		/* this is the object we want to remove later */
		final Object objectToBeRemoved = this.observableArrayList.get(0);

		/*
		 * inokeCntr is used to count the actual invocations of the callback
		 * method when the object is added
		 */
		final DataContainer<Integer> invokeCntr = new DataContainer<Integer>(0);

		/*
		 * now we register on the list's Event when items changed
		 */
		this.observableArrayList.listChanged().addHandler(new Handler<ListChangedEventArgs<Object>>() {

			/*
			 * This method is invoked after the object has been added
			 */
			public void invoke(final Object sender, final ListChangedEventArgs<Object> e) {

				/* assert ListChangedType is ADD_REMOVE */
				Assert.assertEquals(ListChangedEventArgs.ListChangedAction.ADD_REMOVE, e.getListChangedAction());

				/*
				 * assert getOldItems is not null because an element has been
				 * removed
				 */
				Assert.assertNotNull(e.getOldItems());

				/*
				 * assert getNewItems is null because no new elements has been
				 * added
				 */
				Assert.assertNull(e.getNewItems());

				/* assert the old items list's size is 1 */
				Assert.assertEquals(1, e.getOldItems().size());

				/* assert the old item is the expected one */
				Assert.assertEquals(objectToBeRemoved, e.getOldItems().get(0));

				/*
				 * in the end increment the counter to assert that this method
				 * isn't invoked twice
				 */
				invokeCntr.setValue(invokeCntr.getValue() + 1);
			}
		});

		/*
		 * now add the remove the object in the list at index 0
		 */
		this.observableArrayList.remove(0);

		/* assert the callback method was only invoked once */
		Assert.assertEquals(1, invokeCntr.getValue().intValue());

		/* assert the list has the correct size after removing */
		Assert.assertEquals(1, this.observableArrayList.size());

		/* assert the list's doesn't contain the removed object any more */
		Assert.assertFalse(this.observableArrayList.contains(objectToBeRemoved));

		/* try to remove at a position that is not allowed */

		/*
		 * first create a copy of the current list to check it again after
		 * exception testing
		 */
		final DefaultObservableList<Object> copiedList = new ObservableArrayList<Object>(this.observableArrayList);
		try {
			/* at a negative index */
			this.observableArrayList.remove(-1);
			Assert.fail("Can add items at negative index.");
		} catch(final Exception e) {}
		try {
			/*
			 * at an index greater than the greatest accessible index of the
			 * list
			 */
			this.observableArrayList.remove(this.observableArrayList.size());
			Assert.fail("Can add items at index greater than the greatest index of the list.");
		} catch(final Exception e) {}

		/* test whether the list still has the same size after exception testing */
		Assert.assertEquals(1, this.observableArrayList.size());

		/* test whether the list still is unchanged after exception testing */
		Assert.assertTrue(this.observableArrayList.containsAll(copiedList));
	}

	/**
	 * Tests whether a collection of elements can be removed and callbacks are
	 * invoked.
	 */
	@Test
	public void testRemoveAll() {
		/* this lists is to be removed from the observable list */
		final List<Object> objectsToBeRemoved = new ArrayList<Object>();

		/* add elements to the list and to the observable list to be removed */
		for(int i = 0; i < 20; i++) {
			final Object objectToAdd = new Object();
			objectsToBeRemoved.add(objectToAdd);
			this.observableArrayList.add(objectToAdd);
		}

		/* insert a few elements into the observable list */
		for(int i = 0; i < 10; i++) {
			this.observableArrayList.add(new Object());
		}

		/*
		 * inokeCntr is used to count the actual invocations of the callback
		 * method when the object is added
		 */
		final DataContainer<Integer> invokeCntr = new DataContainer<Integer>(0);

		/*
		 * now we register on the list's Event when items changed
		 */
		this.observableArrayList.listChanged().addHandler(new Handler<ListChangedEventArgs<Object>>() {

			/*
			 * This method is invoked after the object has been added
			 */
			public void invoke(final Object sender, final ListChangedEventArgs<Object> e) {

				/* assert ListChangedType is ADD_REMOVE */
				Assert.assertEquals(ListChangedEventArgs.ListChangedAction.ADD_REMOVE, e.getListChangedAction());

				/*
				 * assert getOldItems is not null because elements has been
				 * removed
				 */
				Assert.assertNotNull(e.getOldItems());

				/*
				 * assert getNewItems is null because no new elements has been
				 * added
				 */
				Assert.assertNull(e.getNewItems());

				/* assert the old items list's size is correct */
				Assert.assertEquals(objectsToBeRemoved.size(), e.getOldItems().size());

				/* assert the old items are the expected ones */
				Assert.assertThat(e.getOldItems(), CoreMatchers.is(CoreMatchers.equalTo(objectsToBeRemoved)));

				/*
				 * in the end increment the counter to assert that this method
				 * isn't invoked twice
				 */
				invokeCntr.setValue(invokeCntr.getValue() + 1);
			}
		});

		/*
		 * add the list to the observable list
		 */
		this.observableArrayList.removeAll(objectsToBeRemoved);

		/* assert the callback method was only invoked once */
		Assert.assertEquals(1, invokeCntr.getValue().intValue());

		/* assert the list has the correct size after removing */
		Assert.assertEquals(10, this.observableArrayList.size());

		/* assert all removed elements aren't in the list any more */
		for(final Object removedObject : objectsToBeRemoved) {
			Assert.assertFalse(this.observableArrayList.contains(removedObject));
		}
	}

	/**
	 * Tests whether a collection of elements can be retained and callbacks are
	 * invoked.
	 */
	@Test
	public void testRetainAll() {

		/* this lists is to be retained in the observable list */
		final List<Object> objectsToBeRetained = new ArrayList<Object>();

		/* add elements to the list and to the observable list to be retained */
		for(int i = 0; i < 20; i++) {
			final Object objectToAdd = new Object();
			objectsToBeRetained.add(objectToAdd);
			this.observableArrayList.add(objectToAdd);
		}

		/* insert a few elements into the observable list */
		for(int i = 0; i < 10; i++) {
			this.observableArrayList.add(new Object());
		}

		/*
		 * inokeCntr is used to count the actual invocations of the callback
		 * method when the object is added
		 */
		final DataContainer<Integer> invokeCntr = new DataContainer<Integer>(0);

		/*
		 * now we register on the list's Event when items changed
		 */
		this.observableArrayList.listChanged().addHandler(new Handler<ListChangedEventArgs<Object>>() {

			/*
			 * This method is invoked after the object has been added
			 */
			public void invoke(final Object sender, final ListChangedEventArgs<Object> e) {

				/* assert ListChangedType is ADD_REMOVE */
				Assert.assertEquals(ListChangedEventArgs.ListChangedAction.ADD_REMOVE, e.getListChangedAction());

				/*
				 * assert getOldItems is not null because elements has been
				 * removed
				 */
				Assert.assertNotNull(e.getOldItems());

				/*
				 * assert getNewItems is null because no new elements has been
				 * added
				 */
				Assert.assertNull(e.getNewItems());

				/* assert the old items list's size is correct */
				Assert.assertEquals(10, e.getOldItems().size());

				/* assert the old items are the expected ones */
				for(final Object object : e.getOldItems()) {
					Assert.assertFalse(objectsToBeRetained.contains(object));
				}

				/*
				 * in the end increment the counter to assert that this method
				 * isn't invoked twice
				 */
				invokeCntr.setValue(invokeCntr.getValue() + 1);
			}
		});

		/*
		 * add the list to the observable list
		 */
		this.observableArrayList.retainAll(objectsToBeRetained);

		/* assert the callback method was only invoked once */
		Assert.assertEquals(1, invokeCntr.getValue().intValue());

		/* assert the list has the correct size after retaining */
		Assert.assertEquals(20, this.observableArrayList.size());

		/* assert all retained elements are still in the list */
		for(final Object removedObject : objectsToBeRetained) {
			Assert.assertTrue(this.observableArrayList.contains(removedObject));
		}
	}

	/**
	 * Tests whether elements can be set and callbacks are invoked.
	 */
	@Test
	public void testSet() {
		/* at first add two objects to the list */
		this.observableArrayList.add(new Object());
		this.observableArrayList.add(new Object());

		/* this is the object we want to set later */
		final Object objectToAdd = new Object();
		final Object objectToBeRemoved = this.observableArrayList.get(1);

		/*
		 * invokeCntr is used to count the actual invocations of the callback
		 * method when the object is added
		 */
		final DataContainer<Integer> invokeCntr = new DataContainer<Integer>(0);

		/*
		 * now we register on the list's Event when items changed
		 */
		this.observableArrayList.listChanged().addHandler(new Handler<ListChangedEventArgs<Object>>() {

			/*
			 * This method is invoked after the object has been added
			 */
			public void invoke(final Object sender, final ListChangedEventArgs<Object> e) {

				/* assert ListChangedType is ADD_REMOVE */
				Assert.assertEquals(ListChangedEventArgs.ListChangedAction.ADD_REMOVE, e.getListChangedAction());

				/*
				 * assert getOldItems is not null because an element has been
				 * removed by replacing it eith another element
				 */
				Assert.assertNotNull(e.getOldItems());

				/* assert the old items list's size is 1 */
				Assert.assertEquals(1, e.getOldItems().size());

				/*
				 * assert getNewItems is not null because new elements has been
				 * added by replacing another element
				 */
				Assert.assertNotNull(e.getNewItems());

				/* assert the new items list's size is 1 */
				Assert.assertEquals(1, e.getNewItems().size());

				/* assert the removed item is the expected one */
				Assert.assertEquals(objectToBeRemoved, e.getOldItems().get(0));

				/* assert the new item is the expected one */
				Assert.assertEquals(objectToAdd, e.getNewItems().get(0));

				/*
				 * in the end increment the counter to assert that this method
				 * isn't invoked twice
				 */
				invokeCntr.setValue(invokeCntr.getValue() + 1);
			}
		});

		/*
		 * now add the insert the object in the list at index 0
		 */
		this.observableArrayList.set(1, objectToAdd);

		/* assert the callback method was only invoked once */
		Assert.assertEquals(1, invokeCntr.getValue().intValue());

		/* assert the list has the correct size after insertion */
		Assert.assertEquals(2, this.observableArrayList.size());

		/* assert the list's first object is the expected one */
		Assert.assertEquals(objectToAdd, this.observableArrayList.get(1));

		/* assert the replaced element isn't in the list any more */
		Assert.assertFalse(this.observableArrayList.contains(objectToBeRemoved));

		/* try to replace at a position that is not allowed */

		/*
		 * first create a copy of the current list to check it again after
		 * exception testing
		 */
		final DefaultObservableList<Object> copiedList = new ObservableArrayList<Object>(this.observableArrayList);
		try {
			/* at a negative index */
			this.observableArrayList.set(-1, new Object());
			Assert.fail("Can add items at negative index.");
		} catch(final Exception e) {}
		try {
			/*
			 * at an index greater than the greatest accessible index of the
			 * list
			 */
			this.observableArrayList.set(this.observableArrayList.size(), new Object());
			Assert.fail("Can add items at index greater than the greatest index of the list.");
		} catch(final Exception e) {}

		/* test whether the list still has the same size after exception testing */
		Assert.assertEquals(2, this.observableArrayList.size());

		/* test whether the list's first object is still the expected one */
		Assert.assertEquals(objectToAdd, this.observableArrayList.get(1));

		/* test whether the list still is unchanged after exception testing */
		Assert.assertTrue(this.observableArrayList.containsAll(copiedList));
	}

	/**
	 * Tests whether the size is correct.
	 */
	@Test
	public void testSize() {
		/* assert the list's size is 0 at initial state */
		Assert.assertEquals(0, this.observableArrayList.size());

		/* add objects to the list */
		for(int i = 0; i < 20; i++) {

			this.observableArrayList.add(new Object());

			/* assert the list's size is correct */
			Assert.assertEquals(i + 1, this.observableArrayList.size());
		}

		/* clear the list */
		this.observableArrayList.clear();

		/* assert the list's size is 0 again */
		Assert.assertTrue(this.observableArrayList.isEmpty());

		/* again add objects to the list */
		for(int i = 0; i < 20; i++) {
			this.observableArrayList.add(new Object());

			/* assert the list's size is correct */
			Assert.assertEquals(i + 1, this.observableArrayList.size());
		}

		/* remove all elements separately */
		for(int i = 0; i < 20; i++) {

			/* assert the list's is correct */
			Assert.assertEquals(20 - i, this.observableArrayList.size());;

			this.observableArrayList.remove(0);
		}

		/* assert the list is empty again */
		Assert.assertTrue(this.observableArrayList.isEmpty());
	}

	/**
	 * Tests whether correct sublists are returned.
	 */
	@Test
	public void testSubList() {

		/* store the objects to add in an array */
		final Object[] objectsToAdd = new Object[20];

		/* add objects to the list */
		for(int i = 0; i < objectsToAdd.length; i++) {
			objectsToAdd[i] = new Object();
			this.observableArrayList.add(objectsToAdd[i]);
		}

		/* get a sublist */
		final List<Object> sublist = this.observableArrayList.subList(6, 14);

		/* assert the sublist is a DefaultObservableList */
		Assert.assertTrue(sublist.getClass() == ObservableArrayList.class);

		/* assert the sublist has the correct size */
		Assert.assertEquals(14 - 6, sublist.size());

		/* assert the sublist has the correct elements */
		for(int i = 6; i < 14; i++) {
			Assert.assertTrue(sublist.contains(objectsToAdd[i]));
		}

		/* assert the sublist hasn't false elements */
		for(int i = 0; i < 6; i++) {
			Assert.assertFalse(sublist.contains(objectsToAdd[i]));
		}

		/* assert the sublist hasn't false elements */
		for(int i = 14; i < objectsToAdd.length; i++) {
			Assert.assertFalse(sublist.contains(objectsToAdd[i]));
		}
	}

}
