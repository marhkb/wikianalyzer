package de.behrfried.wikianalyzer.wawebapp.server;

import java.util.Random;

/**
 * Test class gets removed soon.
 * @author marcus
 *
 */
public class DefRandomGen implements RandomGen {

	private Random r = new Random();
	
	public int getR() {
	    return r.nextInt();
    }

}
