package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by karl on 9/24/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = NetworkConnectRequest.NetworkConnectRequestBuilder.class)
public class NetworkConnectRequest {

    private volatile String networkName;

    @JsonProperty("Container")
    private String container;

    @JsonProperty("EndpointConfig")
    private NetworkConnectEndpointConfig endpointConfig;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkConnectRequestBuilder {

    }
}
