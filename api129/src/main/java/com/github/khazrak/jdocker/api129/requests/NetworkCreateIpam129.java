package com.github.khazrak.jdocker.api129.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.NetworkCreateIpam;
import com.github.khazrak.jdocker.abstraction.NetworkCreateIpamConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Builder
@JsonDeserialize(builder = NetworkCreateIpam129.NetworkCreateIpam129Builder.class)
public class NetworkCreateIpam129 implements NetworkCreateIpam {

    @JsonProperty("Driver")
    private String driver;

    @JsonProperty("Config")
    private List<NetworkCreateIpamConfig> config;

    @JsonProperty("Options")
    private Map<String, String> options;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkCreateIpamBuilder {

        @JsonProperty("Driver")
        private String driver = "default";

        @JsonProperty("Config")
        private List<NetworkCreateIpamConfig> config = new ArrayList<>();

        @JsonProperty("Options")
        private Map<String, String> options = new TreeMap<>();

    }
}
