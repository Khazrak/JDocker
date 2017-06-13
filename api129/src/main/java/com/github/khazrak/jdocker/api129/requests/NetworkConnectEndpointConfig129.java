package com.github.khazrak.jdocker.api129.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.IPAMConfig;
import com.github.khazrak.jdocker.abstraction.NetworkConnectEndpointConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = NetworkConnectEndpointConfig129.NetworkConnectEndpointConfig129Builder.class)
public class NetworkConnectEndpointConfig129 implements NetworkConnectEndpointConfig {

    @JsonProperty("IPAMConfig")
    private IPAMConfig config;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkConnectEndpointConfig129Builder {

        @JsonProperty("IPAMConfig")
        private IPAMConfig config;

    }

}
