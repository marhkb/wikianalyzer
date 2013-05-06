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

package de.behrfried.wikianalyzer.wawebapp.server.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpWikiApi implements WikiApi {

	private final Logger logger = LoggerFactory.getLogger(HttpWikiApi.class);
	
	public String getResult(String query) {
		InputStream inputStream = null;
		try {
			inputStream = new URL(query).openStream();
			int r = 0;
			StringBuilder builder = new StringBuilder();
			while((r = inputStream.read()) != -1) {
				builder.append((char)r);
			}
			return builder.toString();
		} catch(MalformedURLException e1) {
			this.logger.error(e1.getMessage(), e1);
		} catch(IOException e2) {
			this.logger.error(e2.getMessage(), e2);
        } finally {
			if(inputStream != null) {
				try {
	                inputStream.close();
                } catch(IOException e) {
                	this.logger.error(e.getMessage(), e);
                }
			}
		}
		return null;
    }

}
