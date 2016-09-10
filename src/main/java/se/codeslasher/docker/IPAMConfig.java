package se.codeslasher.docker;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

/**
 * Created by karl on 9/7/16.
 */
@Builder
public class IPAMConfig {

    @JsonProperty("IPv4Address")
    private String ipv4Address;

    @JsonProperty("IPv6Address")
    private String ipv6Address;

    @Singular
    @JsonProperty("LinkLocalIPs")
    private List<String> linkLocalIps;

}
