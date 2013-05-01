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

package de.behrfried.wikianalyzer.util.list;

import java.util.Collections;
import java.util.List;
import de.behrfried.wikianalyzer.util.event.EventArgs;


public class ListChangedEventArgs<E> extends EventArgs {

	public enum ListChangedType {
		ADD_REMOVE,
		CLEAR
	}
	
	private final ListChangedType listChangedType;
	
	private final List<E> oldItems;
	private final List<E> newItems;
	
	public ListChangedEventArgs(ListChangedType listChangedType, final List<E> oldItems, List<E> newItems) {
		this.listChangedType = listChangedType;
		this.oldItems = oldItems;
		this.newItems = newItems;
	}
	
    public ListChangedType getListChangedType() {
    	return listChangedType;
    }
	
    public List<E> getOldItems() {
    	if(this.oldItems == null) {
    		return null;
    	}
    	return Collections.unmodifiableList(this.oldItems);
    }
	
    public List<E> getNewItems() {
    	if(this.newItems == null) {
    		return null;
    	}
    	return Collections.unmodifiableList(this.newItems);
    }
}
