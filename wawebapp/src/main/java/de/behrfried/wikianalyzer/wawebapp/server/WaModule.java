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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import de.behrfried.wikianalyzer.wawebapp.server.service.HttpWikiApi;
import de.behrfried.wikianalyzer.wawebapp.server.service.JsonWikiAccess;
import de.behrfried.wikianalyzer.wawebapp.server.service.WikiAccess;
import de.behrfried.wikianalyzer.wawebapp.server.service.WikiApi;

/**
 * Server side module for configuring regular services.
 * @author marcus
 *
 */
public class WaModule extends AbstractModule{

	/**
	 * logger linked to any logging framework
	 */
	private final Logger logger = LoggerFactory.getLogger(WaModule.class);
	
	/**
	 * Binds all regular interfaces to a certain implementation.
	 */
	@Override
    protected void configure() {
		this.logger.info("configuring dependencies");
		this.bind(WikiApi.class).to(HttpWikiApi.class).in(Singleton.class);
		this.bind(WikiAccess.class).to(JsonWikiAccess.class).in(Singleton.class);
		this.logger.info("dependencies configured");
    }

}
