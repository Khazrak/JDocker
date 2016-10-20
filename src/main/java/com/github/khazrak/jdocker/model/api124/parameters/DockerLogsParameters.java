/*
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
package com.github.khazrak.jdocker.model.api124.parameters;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Builder
@Getter
public class DockerLogsParameters {

    private boolean details;
    private boolean follow;
    private boolean stdout;
    private boolean stderr;

    /**
     * Unix Timestamp, retrieves logs since this timestamp
     */
    private long since;
    private boolean timestamps;

    /**
     * Number of logs to tail from, (default is all)
     */
    private int tail;

    public Map<String, String> getQueryMap() {
        Map <String, String> queries = new TreeMap<>();

        queries.put("stdout", Boolean.toString(stdout));
        if(stderr) {
            queries.put("stderr", Boolean.toString(true));
        }
        if(details) {
            queries.put("details", Boolean.toString(true));
        }
        if(timestamps) {
            queries.put("timestamps", Boolean.toString(true));
        }
        if(since != 0) {
            queries.put("since", Long.toString(since));
        }
        if(tail > 0) {
            queries.put("tail", Integer.toString(tail));
        }

        return queries;
    }

    public String toString() {
        String params = "?stdout="+stdout;
        if(stderr) {
            params += "&stderr=true";
        }
        if(details) {
            params += "&details=true";
        }
        if(timestamps) {
            params += "%timestamps=true";
        }
        if(since != 0) {
            params += "&since=" + since;
        }
        if(tail > 0) {
            params += "&tail="+tail;
        }

        if(params.length() == 1) {
            params = "";
        }
        return params;
    }


}
