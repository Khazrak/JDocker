package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.LogConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = LogConfig126.LogConfig126Builder.class)
public class LogConfig126 implements LogConfig {

    private String type;
    //TODO: replace with something better
    private Map<String, String> config;

    @JsonPOJOBuilder(withPrefix = "")
    public static class LogConfig126Builder {

        @JsonProperty("Type")
        private String type;

        //TODO: replace with something better
        @JsonProperty("Config")
        private Map<String, String> config;

    }

}
