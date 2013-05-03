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

/*
 * Copyright 2013 Marcus Behrendt & Robert Friedrichs
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.behrfried.wikianalyzer.wawebapp.client;

import junit.framework.Assert;
import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import de.behrfried.wikianalyzer.wawebapp.client.service.MainService;
import de.behrfried.wikianalyzer.wawebapp.client.service.MainServiceAsync;

/**
 * GWT JUnit <b>integration</b> tests must extend GWTTestCase. Using
 * <code>"GwtTest*"</code> naming pattern exclude them from running with
 * surefire during the test phase.
 * 
 * If you run the tests using the Maven command line, you will have to navigate
 * with your browser to a specific url given by Maven. See
 * http://mojo.codehaus.org/gwt-maven-plugin/user-guide/testing.html for
 * details.
 */
public class GwtTestWawebapp extends GWTTestCase {

	/**
	 * Must refer to a valid module that sources this class.
	 */
	@Override
	public String getModuleName() {
		return "de.behrfried.wikianalyzer.wawebapp.WawebappJUnit";
	}

	/**
	 * This test will send a request to the server using the greetServer method
	 * in GreetingService and verify the response.
	 */
	public void testMainService() {
//		// Create the service that we will test.
//		final MainServiceAsync greetingService = GWT.create(MainService.class);
//		final ServiceDefTarget target = (ServiceDefTarget)greetingService;
//		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "Wawebapp/main");
//
//		// Since RPC calls are asynchronous, we will need to wait for a response
//		// after this test method returns. This line tells the test runner to
//		// wait
//		// up to 10 seconds before timing out.
//		this.delayTestFinish(10000);
//
//		// Send a request to the server.
//		final String article = "Hallo";
//		greetingService.getArticle(article, new AsyncCallback<String>() {
//
//			public void onFailure(final Throwable caught) {
//				// The request resulted in an unexpected error.
//				Assert.fail("Request failure: " + caught.getMessage());
//			}
//
//			public void onSuccess(final String result) {
//				// Verify that the response is correct.
//				Assert.assertTrue(article.toUpperCase().equals(result));
//
//				// Now that we have received a response, we need to tell the
//				// test runner
//				// that the test is complete. You must call finishTest() after
//				// an
//				// asynchronous test finishes successfully, or the test will
//				// time out.
//				GwtTestWawebapp.this.finishTest();
//			}
//		});
	}

}
