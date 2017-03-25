package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.Volume;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = Volume126.Volume126Builder.class)
public class Volume126 implements Volume {

    private String name;
    private String driver;
    private String mountPoint;
    private Map<String, String> labels;
    private String scope;
    private Map<String, String> status;
    private Map<String, String> options;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Volume126Builder {
        @JsonProperty("Name")
        private String name;

        @JsonProperty("Driver")
        private String driver;

        @JsonProperty("Mountpoint")
        private String mountPoint;

        @JsonProperty("Labels")
        private Map<String, String> labels;

        @JsonProperty("Scope")
        private String scope;

        @JsonProperty("Status")
        private Map<String, String> status;

        @JsonProperty("Options")
        private Map<String, String> options;

    }

}
