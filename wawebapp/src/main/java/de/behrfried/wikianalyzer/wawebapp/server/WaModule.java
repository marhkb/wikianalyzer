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