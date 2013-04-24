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

import com.google.inject.Inject;

import de.behrfried.wikianalyzer.wawebapp.client.service.MainServiceAsync;
import de.behrfried.wikianalyzer.wawebapp.client.view.StartPageView;

/**
 * 
 * @author marcus
 *
 */
public class DefaultStartPagePresenter implements StartPagePresenter {

    private final StartPageView view;
    private final MainServiceAsync mainService;
    
    @Inject
    public DefaultStartPagePresenter(final StartPageView view, MainServiceAsync mainService) {
	this.view = view;
	this.mainService = mainService;
    }

    public void init() {
	this.view.init();
	
	
	
	//TODO add handlers
    }

    public void dispose() {
	this.view.dispose();
	
	//TODO remove handlers
    }
}
