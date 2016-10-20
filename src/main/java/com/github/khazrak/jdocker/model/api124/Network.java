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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = Network.NetworkBuilder.class)
public class Network {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Id")
    private String id;

    @JsonProperty("Driver")
    private String driver;

    @JsonProperty("Scope")
    private String scope;

    @JsonProperty("EnableIPv6")
    private boolean enableIpv6;

    @JsonProperty("IPAM")
    private NetworkIPAM ipam;

    @JsonProperty("Internal")
    private boolean internal;

    @JsonProperty("Options")
    private Map<String,String> options;

    @JsonProperty("Containers")
    private Map<String, NetworkContainer> containers;

    @JsonProperty("Labels")
    private Map<String, String> labels;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkBuilder {

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Id")
        private String id;

        @JsonProperty("Driver")
        private String driver;

        @JsonProperty("Scope")
        private String scope;

        @JsonProperty("EnableIPv6")
        private boolean enableIpv6;

        @JsonProperty("IPAM")
        private NetworkIPAM ipam;

        @JsonProperty("Internal")
        private boolean internal;

        @JsonProperty("Options")
        private Map<String,String> options;

        @JsonProperty("Containers")
        private Map<String, NetworkContainer> containers;

        @JsonProperty("Labels")
        private Map<String, String> labels;

    }
}
