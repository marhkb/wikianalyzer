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
 
package de.behrfried.wikianalyzer.wawebapp.client.presenter;

import de.behrfried.wikianalyzer.wawebapp.client.view.UserView;

/**
 * Default implementation of {@link UserPresenter}.
 * @author marcus
 *
 */
public class DefaultUserPresenter implements UserPresenter {

    /**
     * the {@link DefaultUserPresenter}'s {@link UserView}
     */
    private final UserView view;
    
    /**
     * Creates a {@link DefaultUserPresenter}. All arguments are injected by Gin.
     * @param view the {@link DefaultUserPresenter}'s {@link UserView}
     * @throws IllegalArgumentException if view == null
     */
    public DefaultUserPresenter(final UserView view) throws IllegalArgumentException {
	if(view == null) {
	    throw new IllegalArgumentException("view == null");
	}
	this.view = view;
    }
    
    /**
     * @see Presenter
     */
    public void init() {
	// TODO Auto-generated method stub
	
    }

    /**
     * @see Presenter
     */
    public void dispose() {
	// TODO Auto-generated method stub
	
    }
    
}
