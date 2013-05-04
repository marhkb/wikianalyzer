package de.behrfried.wikianalyzer.wawebapp.client;

import junit.framework.Test;
import junit.framework.TestSuite;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.junit.tools.GWTTestSuite;
import de.behrfried.wikianalyzer.wawebapp.client.presenter.GwtTestMockArticlePresenter;


public class GwtWaTestSuite extends GWTTestSuite {
	public static Test suite() {
	    final TestSuite suite = new TestSuite("Test for a Maps Application");
	    suite.addTestSuite(GwtTestWawebapp.class);
	    suite.addTestSuite(GwtTestMockArticlePresenter.class);
	    return suite;
	}
}
