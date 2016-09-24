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
@JsonDeserialize(builder = NetworkIPAMConfig.NetworkIPAMConfigBuilder.class)
public class NetworkIPAMConfig {

    @JsonProperty("Subnet")
    private String subnet;

    @JsonProperty("Gateway")
    private String gateway;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkIPAMConfigBuilder {

        @JsonProperty("Subnet")
        private String subnet;

        @JsonProperty("Gateway")
        private String gateway;

    }
}
