package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;

import java.util.Map;

/**
 * Created by karl on 9/7/16.
 */
@JsonDeserialize(builder = LogConfig.LogConfigBuilder.class)
@Builder
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

    @JsonPOJOBuilder(withPrefix = "")
    public static class LogConfigBuilder {


        @JsonProperty("Type")
        private String type;

        //TODO: replace with something better
        @JsonProperty("Config")
        private Map<String,String> config;

    }

}
