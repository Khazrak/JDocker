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
package se.codeslasher.docker.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

public class Filters {

    private static final Logger logger = LoggerFactory.getLogger(Filters.class);

    private ObjectMapper mapper;
    private Map<String,String> filters;

    public Filters() {
        mapper = new ObjectMapper();
        filters = new TreeMap<>();
    }

    public Filters(ObjectMapper mapper) {
        this.mapper = mapper;
        filters = new TreeMap<>();
    }

    public void add(String key, String value) {
        filters.put(key,value);
    }

    public void add(Map<String,String> filters) {
        if(filters.keySet().size() > 0) {
            for(String s : filters.keySet()) {
                add(s,filters.get(s));
            }
        }
    }

    public int size() {
        return this.filters.size();
    }

    public String toString() {
        ObjectNode objectNode = mapper.createObjectNode();
        if(filters.keySet().size() > 0) {
            for(String s : filters.keySet()) {
                objectNode.putObject(s).put(filters.get(s),true);
            }
        }

        return objectNode.toString();
    }

    public static String encodeFilters(Map<String,String> filters) {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        ObjectNode objectNode = mapper.createObjectNode();
        if(filters.keySet().size() > 0) {
            for(String s : filters.keySet()) {
                objectNode.putObject(s).put(filters.get(s),true);
            }
        }

        try {
            result = URLEncoder.encode(objectNode.toString(), StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            logger.error("Error encoding filters",e);
        }
        return result;
    }

}
