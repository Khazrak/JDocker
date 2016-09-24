package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created by karl on 9/11/16.
 */
@Getter
@Builder
@ToString
@JsonDeserialize(builder = NetworkSettings.NetworkSettingsBuilder.class)
public class NetworkSettings {

    @JsonProperty("Bridge")
    private String bridge;

    @JsonProperty("SandboxID")
    private String sandboxId;

    @JsonProperty("HairpinMode")
    private boolean hairPindMode;

    @JsonProperty("LinkLocalIPv6Address")
    private String linkLocalIpv6Address;

    @JsonProperty("LinkLocalIPv6PrefixLen")
    private int linkLocalIpv6PrefixLen;

    @JsonProperty("Ports")
    private List<ContainerPort> ports;

    @JsonProperty("SandboxKey")
    private String sandboxKey;

    @JsonProperty("SecondaryIPAddresses")
    private Object secondaryIpAddresses;

    @JsonProperty("SecondaryIPv6Addresses")
    private Object secondaryIpv6Addresses;

    @JsonProperty("EndpointID")
    private String endpointId;

    @JsonProperty("Gateway")
    private String gateway;

    @JsonProperty("GlobalIPv6Address")
    private String globalIpv6Address;

    @JsonProperty("GlobalIPv6PrefixLen")
    private int globalIpv6PrefixLen;

    @JsonProperty("IPAddress")
    private String ipAddress;

    @JsonProperty("IPPrefixLen")
    private int ipPrefixLen;

    @JsonProperty("IPv6Gateway")
    private String ipv6Gateway;

    @JsonProperty("MacAddress")
    private String macAddress;

    @JsonProperty("Networks")
    private Map<String,NetworkInterface> networks;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkSettingsBuilder {
        @JsonProperty("Bridge")
        private String bridge;

        @JsonProperty("SandboxID")
        private String sandboxId;

        @JsonProperty("HairpinMode")
        private boolean hairPindMode;

        @JsonProperty("LinkLocalIPv6Address")
        private String linkLocalIpv6Address;

        @JsonProperty("LinkLocalIPv6PrefixLen")
        private int linkLocalIpv6PrefixLen;

        @JsonProperty("Ports")
        private List<ContainerPort> ports;

        @JsonProperty("SandboxKey")
        private String sandboxKey;

        @JsonProperty("SecondaryIPAddresses")
        private Object secondaryIpAddresses;

        @JsonProperty("SecondaryIPv6Addresses")
        private Object secondaryIpv6Addresses;

        @JsonProperty("EndpointID")
        private String endpointId;

        @JsonProperty("Gateway")
        private String gateway;

        @JsonProperty("GlobalIPv6Address")
        private String globalIpv6Address;

        @JsonProperty("GlobalIPv6PrefixLen")
        private int globalIpv6PrefixLen;

        @JsonProperty("IPAddress")
        private String ipAddress;

        @JsonProperty("IPPrefixLen")
        private int ipPrefixLen;

        @JsonProperty("IPv6Gateway")
        private String ipv6Gateway;

        @JsonProperty("MacAddress")
        private String macAddress;

        @JsonProperty("Networks")
        private Map<String,NetworkInterface> networks;
    }

}
