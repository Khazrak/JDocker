package com.github.khazrak.jdocker.api126.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.NetworkConnectEndpointConfig;
import com.github.khazrak.jdocker.abstraction.NetworkConnectRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = NetworkConnectRequest126.NetworkConnectRequest126Builder.class)
public class NetworkConnectRequest126 implements NetworkConnectRequest {

    private volatile String networkName;

    @JsonProperty("Container")
    private String container;

    @JsonDeserialize(as = NetworkConnectEndpointConfig126.class)
    @JsonProperty("EndpointConfig")
    private NetworkConnectEndpointConfig endpointConfig;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkConnectRequest126Builder {

    }

}
