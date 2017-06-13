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
package com.github.khazrak.jdocker.utils;

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
    private Map<String, String> filtersMap;

    public Filters() {
        mapper = new ObjectMapper();
        filtersMap = new TreeMap<>();
    }

    public Filters(ObjectMapper mapper) {
        this.mapper = mapper;
        filtersMap = new TreeMap<>();
    }

    public void add(String key, String value) {
        filtersMap.put(key, value);
    }

    public void add(Map<String, String> filters) {
        if (!filters.keySet().isEmpty()) {
            for (Map.Entry<String, String> s : filters.entrySet()) {
                add(s.getKey(), s.getValue());
            }
        }
    }

    public int size() {
        return this.filtersMap.size();
    }

    public String toString() {
        ObjectNode objectNode = mapper.createObjectNode();
        if (!filtersMap.keySet().isEmpty()) {
            for (Map.Entry<String, String> s : filtersMap.entrySet()) {
                objectNode.putObject(s.getKey()).put(s.getValue(), true);
            }
        }

        return objectNode.toString();
    }

    public static String encodeFilters(Map<String, String> filters) {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        ObjectNode objectNode = mapper.createObjectNode();
        if (!filters.keySet().isEmpty()) {
            for (Map.Entry<String, String> s : filters.entrySet()) {
                objectNode.putObject(s.getKey()).put(s.getValue(), true);
            }
        }

        try {
            result = URLEncoder.encode(objectNode.toString(), StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            logger.error("Error encoding filtersMap", e);
        }
        return result;
    }

}
