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

package de.behrfried.wikianalyzer.wawebapp.client.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import com.google.gwt.user.client.Window;

public class JQueryWikiAccess implements WikiAccess {

	public Collection<String> getArticleTitles(String startsWith, int maxResults) {
		final List<String> result = new LinkedList<String>();
		this.jGetArticleTitles(result, startsWith, maxResults);
		for(Object s : result) {
			Window.alert(s.toString());
		}
		return result;
	}

	public native void jGetArticleTitles(List list, String word, int maxResults) /*-{
		 $wnd.$.getJSON("http://en.wikipedia.org/w/api.php?action=query&format=json&generator=allpages&gaplimit=3&gapfrom=Ba&callback=?",
			function(data) {
				for ( var d in data["query"]["pages"]) {
					//$wnd.alert(data["query"]["pages"][d].title);
					list.@java.util.List::add(Ljava/lang/Object;)('Hallo');
				}
			});

	}-*/;

}
