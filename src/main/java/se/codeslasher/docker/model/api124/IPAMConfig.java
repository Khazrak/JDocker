package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;
import java.util.Map;

/**
 * Created by karl on 9/7/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = IPAMConfig.IPAMConfigBuilder.class)
public class IPAMConfig {

    @JsonProperty("IPv4Address")
    private String ipv4Address;

    @JsonProperty("IPv6Address")
    private String ipv6Address;

    @JsonProperty("LinkLocalIPs")
    private List<String> linkLocalIps;

    @JsonPOJOBuilder(withPrefix = "")
    public static class IPAMConfigBuilder {
        @JsonProperty("IPv4Address")
        private String ipv4Address;

        @JsonProperty("IPv6Address")
        private String ipv6Address;

        @JsonProperty("LinkLocalIPs")
        private List<String> linkLocalIps;

    }

}
