package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.IPAMConfig;
import com.github.khazrak.jdocker.abstraction.NetworkInterface;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = NetworkInterface129.NetworkInterface129Builder.class)
public class NetworkInterface129 implements NetworkInterface {

    private IPAMConfig ipamConfig;
    private List<String> links;
    private List<String> aliases;
    private String networkId;
    private String endpointId;
    private String gateway;
    private String ipAddress;
    private int ipPrefixLen;
    private String ipv6GateWay;
    private String globalIpv6Address;
    private int globalIpv6PrefixLen;
    private String macAddress;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkInterface129Builder {

        @JsonDeserialize(as = IPAMConfig129.class)
        @JsonProperty("IPAMConfig")
        private IPAMConfig ipamConfig;

        @JsonProperty("Links")
        private List<String> links;

        @JsonProperty("Aliases")
        private List<String> aliases;

        @JsonProperty("NetworkID")
        private String networkId;

        @JsonProperty("EndpointID")
        private String endpointId;

        @JsonProperty("Gateway")
        private String gateway;

        @JsonProperty("IPAddress")
        private String ipAddress;

        @JsonProperty("IPPrefixLen")
        private int ipPrefixLen;

        @JsonProperty("IPv6Gateway")
        private String ipv6GateWay;

        @JsonProperty("GlobalIPv6Address")
        private String globalIpv6Address;

        @JsonProperty("GlobalIPv6PrefixLen")
        private int globalIpv6PrefixLen;

        @JsonProperty("MacAddress")
        private String macAddress;

    }

}
