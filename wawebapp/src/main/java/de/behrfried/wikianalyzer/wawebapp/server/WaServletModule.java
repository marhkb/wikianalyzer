package de.behrfried.wikianalyzer.wawebapp.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.servlet.ServletModule;
import de.behrfried.wikianalyzer.wawebapp.server.servlets.MainServiceImpl;


/**
 * Server side module for for configuring servlets.
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
		this.serve("/Wawebapp/main").with(MainServiceImpl.class);
		this.logger.info("servlets configured");
	}

}
