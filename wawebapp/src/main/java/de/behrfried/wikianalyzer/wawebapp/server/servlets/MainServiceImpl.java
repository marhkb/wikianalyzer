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

package de.behrfried.wikianalyzer.wawebapp.server.servlets;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.behrfried.wikianalyzer.wawebapp.client.exception.ArticleNotExistException;
import de.behrfried.wikianalyzer.wawebapp.client.exception.CriterionNotFoundException;
import de.behrfried.wikianalyzer.wawebapp.client.exception.UserForComparisonNotExistException;
import de.behrfried.wikianalyzer.wawebapp.client.exception.UserNotExistException;
import de.behrfried.wikianalyzer.wawebapp.client.service.MainService;
import de.behrfried.wikianalyzer.wawebapp.server.service.WikiAccess;
import de.behrfried.wikianalyzer.wawebapp.shared.article.ArticleInfo;
import de.behrfried.wikianalyzer.wawebapp.shared.user.CriterionInfo;
import de.behrfried.wikianalyzer.wawebapp.shared.user.UserComparisonInfo;
import de.behrfried.wikianalyzer.wawebapp.shared.user.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Server side implementation of {@link MainService}
 * 
 * @author marcus
 * 
 */
@Singleton
public class MainServiceImpl extends RemoteServiceServlet implements MainService {

	/**
     * 
     */
	private static final long serialVersionUID = 3575183933435770570L;

	private final Logger logger = LoggerFactory.getLogger(MainServiceImpl.class);

	private final WikiAccess wikiAccess;

	@Inject
	public MainServiceImpl(final WikiAccess wikiAccess) {
		this.wikiAccess = wikiAccess;
	}

	public ArticleInfo sendArticleName(final String articleName) throws ArticleNotExistException {
		return this.wikiAccess.getArticleInfo(articleName);
	}

	@Override
    public UserInfo sendUserName(final String userName) throws UserNotExistException {
	    return this.wikiAccess.getUserInfo(userName);
    }

	@Override
    public UserComparisonInfo sendUserForComparison(final String userName1, final String userName2) throws UserForComparisonNotExistException {
	    return this.wikiAccess.getUserComparisonInfo(userName1, userName2);
    }

	@Override
    public CriterionInfo sendCriterionInfo() throws CriterionNotFoundException {
	    return this.wikiAccess.getCriterionInfo();
    }
	
	
}
