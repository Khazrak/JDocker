package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.HostPort;
import com.github.khazrak.jdocker.abstraction.NetworkInterface;
import com.github.khazrak.jdocker.abstraction.NetworkSettings;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@ToString
@JsonDeserialize(builder = NetworkSettings126.NetworkSettings126Builder.class)
public class NetworkSettings126 implements NetworkSettings {

    private String bridge;
    private String sandboxId;
    private boolean hairPindMode;
    private String linkLocalIpv6Address;
    private int linkLocalIpv6PrefixLen;
    private Map<String, List<HostPort>> ports;
    private String sandboxKey;
    private Object secondaryIpAddresses;
    private Object secondaryIpv6Addresses;
    private String endpointId;
    private String gateway;
    private String globalIpv6Address;
    private int globalIpv6PrefixLen;
    private String ipAddress;
    private int ipPrefixLen;
    private String ipv6Gateway;
    private String macAddress;
    private Map<String, NetworkInterface> networks;


    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkSettings126Builder {
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
        private Map<String, List<HostPort>> ports;

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
        private Map<String, NetworkInterface> networks;
    }

}
