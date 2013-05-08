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
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Class whose {@code getInjector()} is invoked immediately after application
 * has deployed.
 * 
 * @author marcus
 * 
 */
public class WaServletContextListener extends GuiceServletContextListener {

	/**
	 * logger linked to any logging framework
	 */
	private final Logger logger = LoggerFactory.getLogger(WaServletContextListener.class);

	/**
	 * This method creates all needed {@link Injector}s for server side
	 * application.
	 */
	@Override
	protected Injector getInjector() {
		this.logger.info("Creating Injectors");
		return Guice.createInjector(new WaModule(), new WaServletModule());
	}
}
