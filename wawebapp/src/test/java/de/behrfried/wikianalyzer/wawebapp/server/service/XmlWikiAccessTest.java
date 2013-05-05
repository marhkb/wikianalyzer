package de.behrfried.wikianalyzer.wawebapp.server.service;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class XmlWikiAccessTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {}
	
	private WikiAccess wikiAccess;

	@Before
	public void setUp() throws Exception {
		this.wikiAccess = new XmlWikiAccess(new HttpWikiApi());
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
		
		expected = 2578216;
		actual = this.wikiAccess.getPageId("Hans Wurst");
		assertEquals(expected, actual);
	}

}
