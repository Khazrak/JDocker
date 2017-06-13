package com.github.khazrak.jdocker.api126.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.IPAMConfig;
import com.github.khazrak.jdocker.abstraction.NetworkConnectEndpointConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = NetworkConnectEndpointConfig126.NetworkConnectEndpointConfig126Builder.class)
public class NetworkConnectEndpointConfig126 implements NetworkConnectEndpointConfig {

    @JsonProperty("IPAMConfig")
    private IPAMConfig config;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkConnectEndpointConfig126Builder {

        @JsonProperty("IPAMConfig")
        private IPAMConfig config;

    }

}
