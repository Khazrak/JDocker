package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.NetworkIPAM;
import com.github.khazrak.jdocker.abstraction.NetworkIPAMConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = NetworkIPAM126.NetworkIPAM126Builder.class)
public class NetworkIPAM126 implements NetworkIPAM {

    private String driver;
    private List<NetworkIPAMConfig> config;
    private Map<String, String> options;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkIPAM126Builder {

        @JsonProperty("Driver")
        private String driver;

        @JsonProperty("Config")
        private List<NetworkIPAMConfig> config;

        @JsonProperty("Options")
        private Map<String, String> options;

    }

}
