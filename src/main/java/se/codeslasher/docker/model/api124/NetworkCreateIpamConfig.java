package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by karl on 9/23/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = NetworkCreateIpamConfig.NetworkCreateIpamConfigBuilder.class)
public class NetworkCreateIpamConfig {

    @JsonProperty("Subnet")
    private String subnet;

    @JsonProperty("IPRange")
    private String ipRange;

    @JsonProperty("Gateway")
    private String gateway;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkCreateIpamConfigBuilder {

        @JsonProperty("Subnet")
        private String subnet;

        @JsonProperty("IPRange")
        private String ipRange;

        @JsonProperty("Gateway")
        private String gateway;

    }
}
