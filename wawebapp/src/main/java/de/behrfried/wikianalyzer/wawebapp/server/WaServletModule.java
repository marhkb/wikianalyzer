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

import com.google.inject.servlet.ServletModule;
import de.behrfried.wikianalyzer.wawebapp.server.servlets.ArticleInfoServiceImpl;
import de.behrfried.wikianalyzer.wawebapp.server.servlets.MainServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Server side module for for configuring servlets.
 * 
 * @author marcus
 * 
 */
public class WaServletModule extends ServletModule {

	/**
	 * logger linked to any logging framework
	 */
	private final Logger logger = LoggerFactory.getLogger(WaServletModule.class);

	/**
	 * Binds all Servlets.
	 */
	@Override
	protected void configureServlets() {
		this.logger.info("configuring servlets");
		
		this.logger.debug("configuring MainService");
		this.serve("/Wawebapp/main").with(MainServiceImpl.class);
		
		this.logger.debug("configuring ArticleInfoService");
		this.serve("/Wawebapp/articleInfo").with(ArticleInfoServiceImpl.class);
		
		this.logger.info("all servlets have been configured");
	}
}
