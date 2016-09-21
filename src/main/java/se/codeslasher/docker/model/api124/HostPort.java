package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;

/**
 * Created by karl on 9/7/16.
 */
@Builder
@JsonDeserialize(builder = HostPort.HostPortBuilder.class)
public class HostPort {

    @JsonProperty("HostIp")
    private String hostIp;

    @JsonProperty("HostPort")
    private String hostPort;

    @JsonPOJOBuilder(withPrefix = "")
    public static class HostPortBuilder {

        @JsonProperty("HostIp")
        private String hostIp;

        @JsonProperty("HostPort")
        private String hostPort;

    }

}
