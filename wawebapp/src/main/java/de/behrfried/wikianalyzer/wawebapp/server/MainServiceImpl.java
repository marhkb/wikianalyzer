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
 
package de.behrfried.wikianalyzer.wawebapp.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.behrfried.wikianalyzer.wawebapp.client.service.MainService;
import de.behrfried.wikianalyzer.wawebapp.shared.StringObject;

/**
 * Server side implementation of {@link MainService}
 * @author marcus
 *
 */
public class MainServiceImpl extends RemoteServiceServlet implements MainService {

    /**
     * generated UID
     */
    private static final long serialVersionUID = 200675849907147502L;

    /**
     * Look at {@link MainService}
     */
    public StringObject getStringObject(StringObject o) {
    	return o;
    }

	public String getArticle(String article) {
		return "Hallo";
	}
    
}
