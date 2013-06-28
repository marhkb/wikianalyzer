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

import java.util.LinkedHashMap;
import de.behrfried.wikianalyzer.wawebapp.client.util.command.Command;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.Event;
import de.behrfried.wikianalyzer.wawebapp.client.util.event.EventArgs;
import de.behrfried.wikianalyzer.wawebapp.client.view.user.UserComparisonView;
import de.behrfried.wikianalyzer.wawebapp.shared.user.UserComparisonInfo;

/**
 * Default implementation of {@link UserComparisonView.Presenter}
 * 
 * @author marcus
 * 
 */
public class DefaultUserComparisonPresenter implements UserComparisonView.Presenter {

	@Override
    public UserComparisonInfo getUserComparisonInfo() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Event<EventArgs> userComparisonInfoChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public String getUserName1() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public String getUserName2() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public void setUserName1(String userName) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void setUserName2(String userName) {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public Event<EventArgs> userName1Changed() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Event<EventArgs> userName2Changed() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Command getSendCommand() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public boolean getSearchStatus() {
	    // TODO Auto-generated method stub
	    return false;
    }

	@Override
    public Event<EventArgs> searchStatusChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public LinkedHashMap<String, String> getUser1Suggestions() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public LinkedHashMap<String, String> getUser2Suggestions() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Event<EventArgs> user1SuggestionsChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public Event<EventArgs> user2SuggestionsChanged() {
	    // TODO Auto-generated method stub
	    return null;
    }



}
