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

package de.behrfried.wikianalyzer.wawebapp.client.view.dflt.usercomparison;

import com.google.inject.Inject;
import de.behrfried.wikianalyzer.wawebapp.client.view.usercomparison.UserComparisonView;

/**
 * Default implementation for {@link UserComparisonView}.
 * 
 * @author marcus
 * 
 */
public class DefUserComparisonView extends UserComparisonView {

	/**
	 * parent view of this {@link DefUserComparisonView}
	 */
	private final Presenter presenter;

	/**
	 * Creates an instance of {@link DefUserComparisonView}. All arguments
	 * are injected by Gin
	 * 
	 * @param parentView
	 * @throws IllegalArgumentException
	 */
	@Inject
	public DefUserComparisonView(final Presenter presenter) throws IllegalArgumentException {
		if(presenter == null) {
			throw new IllegalArgumentException("presenter == null");
		}
		this.presenter = presenter;
	}

	@Override
	public String getName() {
		return "User Comparison";
	}
}
