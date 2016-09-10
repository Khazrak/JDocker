package se.codeslasher.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by karl on 9/7/16.
 */
public class LogConfig {

    public LogConfig (String type, Map<String,String> config) {
        this.type = type;
        this.config = config;
    }

    @JsonProperty("Type")
    private String type;

    //TODO: replace with something better
    @JsonProperty("Config")
    private Map<String,String> config;

}
