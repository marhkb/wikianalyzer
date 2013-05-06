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

package de.behrfried.wikianalyzer.wawebapp.server.service;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class JsonAccessTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {}
	
	private WikiAccess wikiAccess;

	@Before
	public void setUp() throws Exception {
		this.wikiAccess = new JsonWikiAccess(new HttpWikiApi());
	}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testGetPageId() {
		int expected = 88112;
		int actual = this.wikiAccess.getPageId("Hamster");
		assertEquals(expected, actual);
		
		expected = 105066;
		actual = this.wikiAccess.getPageId("Ratte");
		assertEquals(expected, actual);
	}

}
