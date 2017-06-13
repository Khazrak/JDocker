package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.LogConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = LogConfig129.LogConfig129Builder.class)
public class LogConfig129 implements LogConfig {

    private String type;
    private Map<String, String> config;

    @JsonPOJOBuilder(withPrefix = "")
    public static class LogConfig129Builder {

        @JsonProperty("Type")
        private String type;

        @JsonProperty("Config")
        private Map<String, String> config;

    }

}
