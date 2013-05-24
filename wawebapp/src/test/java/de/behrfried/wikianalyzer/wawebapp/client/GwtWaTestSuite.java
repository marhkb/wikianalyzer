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

package de.behrfried.wikianalyzer.wawebapp.client;

import junit.framework.Test;
import junit.framework.TestSuite;
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