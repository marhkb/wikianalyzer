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
 
package de.behrfried.wikianalyzer.wawebapp.shared;

import java.io.Serializable;

/**
 * Wrapper around an {@link String} that is used for client server communication
 * @author marcus
 *
 */
public class StringObject implements Serializable {
    
    /**
     * generated UID
     */
    private static final long serialVersionUID = -6407435912570206085L;
    
    /**
     * the String instance
     */
    private String str;
    
    /**
     * Default constructor for {@link Serializable}
     */
    public StringObject() {  }

    /**
     * Creates a {@link StringObject} instance
     * @param str the {@link String} to be held by this {@link StringObject}
     */
    public StringObject(String str) {
	super();
	this.str = str;
    }

    /**
     * Returns the inner {@link String}.
     * @return the inner {@link String}
     */
    public String getStr() {
        return this.str;
    }

    /**
     * Sets the inner {@link String}.
     * @param str the inner {@link String} to be set
     */
    public void setStr(String str) {
        this.str = str;
    }
}
