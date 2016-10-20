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
package com.github.khazrak.jdocker.model.api124.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import com.github.khazrak.jdocker.model.api124.NetworkCreateIpam;

import java.util.Map;
import java.util.TreeMap;

@Getter
@Builder
@JsonDeserialize(builder = NetworkCreateRequest.NetworkCreateRequestBuilder.class)
public class NetworkCreateRequest {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("CheckDuplicate")
    private boolean checkDuplicate;

    @JsonProperty("Driver")
    private String driver;

    @JsonProperty("EnableIPv6")
    private boolean enableIpv6;

    @JsonProperty("IPAM")
    private NetworkCreateIpam ipam;

    @JsonProperty("Internal")
    private boolean internal;

    @JsonProperty("Options")
    private Map<String,String> options;

    @JsonProperty("Labels")
    private Map<String,String> labels;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkCreateRequestBuilder {

        @JsonProperty("Name")
        private String name;

        @JsonProperty("CheckDuplicate")
        private boolean checkDuplicate = true;

        @JsonProperty("Driver")
        private String driver = "bridge";

        @JsonProperty("EnableIPv6")
        private boolean enableIpv6 = false;

        @JsonProperty("IPAM")
        private NetworkCreateIpam ipam = NetworkCreateIpam.builder().build();

        @JsonProperty("Internal")
        private boolean internal = false;

        @JsonProperty("Options")
        private Map<String,String> options = new TreeMap<>();

        @JsonProperty("Labels")
        private Map<String,String> labels = new TreeMap<>();

    }

}
