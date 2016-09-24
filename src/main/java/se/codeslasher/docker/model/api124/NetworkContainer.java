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
@JsonDeserialize(builder = NetworkContainer.NetworkContainerBuilder.class)
public class NetworkContainer {

    @JsonProperty("EndpointID")
    private String endpointId;

    @JsonProperty("MacAddress")
    private String macAddress;

    @JsonProperty("IPv4Address")
    private String ipv44Address;

    @JsonProperty("IPv6Address")
    private String ipv64Address;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkContainerBuilder {

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
