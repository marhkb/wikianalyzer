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

import de.behrfried.wikianalyzer.wawebapp.client.exception.ArticleNotExistException;
import de.behrfried.wikianalyzer.wawebapp.client.exception.CriterionNotFoundException;
import de.behrfried.wikianalyzer.wawebapp.client.exception.UserForComparisonNotExistException;
import de.behrfried.wikianalyzer.wawebapp.client.exception.UserNotExistException;
import de.behrfried.wikianalyzer.wawebapp.shared.article.ArticleInfo;
import de.behrfried.wikianalyzer.wawebapp.shared.user.CriterionInfo;
import de.behrfried.wikianalyzer.wawebapp.shared.user.TitleOrCategory;
import de.behrfried.wikianalyzer.wawebapp.shared.user.UserComparisonInfo;
import de.behrfried.wikianalyzer.wawebapp.shared.user.UserInfo;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

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
	public void testGetShortArticleInfo() throws ArticleNotExistException {
		final ArticleInfo articleInfo = this.wikiAccess.getArticleInfo("Maus");
		Assert.assertNotNull(articleInfo);
	}

	@Test
	public void testGetPageId() {
		int expected = 88112;
		int actual = this.wikiAccess.getPageId("Hamster");
		Assert.assertEquals(expected, actual);

		expected = 105066;
		actual = this.wikiAccess.getPageId("Ratte");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetUserInfo() throws UserNotExistException {
		final UserInfo ui = wikiAccess.getUserInfo("APPER");
		System.out.println(ui);
	}

	@Test
	public void testGetUserComparisonInfo() throws UserForComparisonNotExistException {
		final UserComparisonInfo uci = this.wikiAccess.getUserComparisonInfo("Hans", "Wurst");

	}


	@Test
	public void testGetCriterion() throws CriterionNotFoundException {
		final List<TitleOrCategory> titleOrCategories1 = new ArrayList<TitleOrCategory>(2);
		titleOrCategories1.add(new TitleOrCategory("Datenschutz", false));
		titleOrCategories1.add(new TitleOrCategory("Deutschland", false));
		final CriterionInfo ci1 = this.wikiAccess.getCriterionInfo(titleOrCategories1);
		System.out.println(ci1);
	}
}
