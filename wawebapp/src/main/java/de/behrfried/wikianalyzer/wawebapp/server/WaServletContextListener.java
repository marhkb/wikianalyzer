package de.behrfried.wikianalyzer.wawebapp.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Class whose {@code getInjector()} is invoked immediately after application has deployed.
 * @author marcus
 *
 */
public class WaServletContextListener extends GuiceServletContextListener {

	/**
	 * logger linked to any logging framework
	 */
	private final Logger logger = LoggerFactory.getLogger(WaServletContextListener.class);
	
	/**
	 * This method creates all needed {@link Injector}s for server side application.
	 */
	@Override
    protected Injector getInjector() {
		this.logger.info("Creating Injectors");
	    return Guice.createInjector(new WaModule(), new WaServletModule());
    }
}
