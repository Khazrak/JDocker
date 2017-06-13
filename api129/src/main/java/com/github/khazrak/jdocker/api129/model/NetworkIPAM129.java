package com.github.khazrak.jdocker.api129.model;

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
@JsonDeserialize(builder = NetworkIPAM129.NetworkIPAM129Builder.class)
public class NetworkIPAM129 implements NetworkIPAM {

    private String driver;
    private List<NetworkIPAMConfig> config;
    private Map<String, String> options;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkIPAM129Builder {

        @JsonProperty("Driver")
        private String driver;

        @JsonProperty("Config")
        private List<NetworkIPAMConfig> config;

        @JsonProperty("Options")
        private Map<String, String> options;

    }

}
