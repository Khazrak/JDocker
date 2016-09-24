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

/**
 * Created by karl on 9/23/16.
 */
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
