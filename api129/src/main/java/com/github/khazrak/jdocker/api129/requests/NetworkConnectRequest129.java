package com.github.khazrak.jdocker.api129.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.NetworkConnectEndpointConfig;
import com.github.khazrak.jdocker.abstraction.NetworkConnectRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = NetworkConnectRequest129.NetworkConnectRequest129Builder.class)
public class NetworkConnectRequest129 implements NetworkConnectRequest {

    private volatile String networkName;

    @JsonProperty("Container")
    private String container;

    @JsonDeserialize(as = NetworkConnectEndpointConfig129.class)
    @JsonProperty("EndpointConfig")
    private NetworkConnectEndpointConfig endpointConfig;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkConnectRequest129Builder {

    }

}
