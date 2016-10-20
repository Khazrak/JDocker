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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Builder
@JsonDeserialize(builder = NetworkCreateIpam.NetworkCreateIpamBuilder.class)
public class NetworkCreateIpam {

    @JsonProperty("Driver")
    private String driver;

    @JsonProperty("Config")
    private List<NetworkCreateIpamConfig> config;

    @JsonProperty("Options")
    private Map<String,String> options;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkCreateIpamBuilder {

        @JsonProperty("Driver")
        private String driver = "default";

        @JsonProperty("Config")
        private List<NetworkCreateIpamConfig> config = new ArrayList<>();

        @JsonProperty("Options")
        private Map<String,String> options = new TreeMap<>();

    }
}
