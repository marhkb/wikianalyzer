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

package de.behrfried.wikianalyzer.wawebapp.server;

import info.bliki.api.Connector;
import info.bliki.api.User;
import info.bliki.api.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.behrfried.wikianalyzer.wawebapp.client.service.MainService;

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
	
	private final RandomGen randomGen;
	
	@Inject
	public MainServiceImpl(RandomGen randomGen) {
		this.randomGen = randomGen;
	}

	public int sendArticleName(String articleName) {
		
		logger.info(articleName);		
		User user = new User("", "", "http://de.wikipedia.org/w/api.php");
		user.login();
		Connector connector = new Connector();
		
		final Query query = new Query();
		query.action("query");
		query.format("json");
		query.generator("allpages");
		//final List<Page> pages = connector.query(user, query);
		
//		for(Page p : pages) {
//			this.logger.info(p.getTitle());
//		}
		return this.randomGen.getR();
	}

}
