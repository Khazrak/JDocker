package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.NetworkContainer;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = NetworkContainer126.NetworkContainer126Builder.class)
public class NetworkContainer126 implements NetworkContainer {

    private String endpointId;
    private String macAddress;
    private String ipv44Address;
    private String ipv64Address;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkContainer126Builder {

        @JsonProperty("EndpointID")
        private String endpointId;

        @JsonProperty("MacAddress")
        private String macAddress;

        @JsonProperty("IPv4Address")
        private String ipv44Address;

        @JsonProperty("IPv6Address")
        private String ipv64Address;

    }


}
