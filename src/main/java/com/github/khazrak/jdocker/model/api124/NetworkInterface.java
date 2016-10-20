/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.khazrak.jdocker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

@JsonPropertyOrder({
        "IPAMConfig",
        "Links",
        "Aliases",
        "NetworkID",
        "EndpointID",
        "Gateway",
        "IPAddress",
        "IPPrefixLen",
        "IPv6Gateway",
        "GlobalIPv6Address",
        "GlobalIPv6PrefixLen",
        "MacAddress"
})
@Getter
@Builder
@JsonDeserialize(builder = NetworkInterface.NetworkInterfaceBuilder.class)
public class NetworkInterface {

    @JsonProperty("IPAMConfig")
    private IPAMConfig ipamConfig;

    @JsonProperty("Links")
    private String links;

    @JsonProperty("Aliases")
    private String aliases;

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

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkInterfaceBuilder {

        @JsonProperty("IPAMConfig")
        private IPAMConfig ipamConfig;

        @JsonProperty("Links")
        private String links;

        @JsonProperty("Aliases")
        private String aliases;

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
