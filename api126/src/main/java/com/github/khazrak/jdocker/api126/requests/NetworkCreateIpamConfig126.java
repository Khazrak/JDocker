package com.github.khazrak.jdocker.api126.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.NetworkCreateIpamConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = NetworkCreateIpamConfig126.NetworkCreateIpamConfig126Builder.class)
public class NetworkCreateIpamConfig126 implements NetworkCreateIpamConfig {

    @JsonProperty("Subnet")
    private String subnet;

    @JsonProperty("IPRange")
    private String ipRange;

    @JsonProperty("Gateway")
    private String gateway;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkCreateIpamConfig126Builder {

        @JsonProperty("Subnet")
        private String subnet;

        @JsonProperty("IPRange")
        private String ipRange;

        @JsonProperty("Gateway")
        private String gateway;

    }
}
