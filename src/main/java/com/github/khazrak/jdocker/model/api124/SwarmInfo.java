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

import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = SwarmInfo.SwarmInfoBuilder.class)
public class SwarmInfo {

    private String nodeId;
    private String nodeAddr;
    private String localNodeState;
    private boolean controlAvailable;
    private String error;
    private List<String> remoteManagers;
    private int nodes;
    private int managers;
    private ClusterInfo cluster;

    @JsonPOJOBuilder(withPrefix = "")
    public static class SwarmInfoBuilder {

        @JsonProperty("NodeID")
        private String nodeId;

        @JsonProperty("NodeAddr")
        private String nodeAddr;

        @JsonProperty("LocalNodeState")
        private String localNodeState;

        @JsonProperty("ControlAvailable")
        private boolean controlAvailable;

        @JsonProperty("Error")
        private String error;

        @JsonProperty("RemoteManagers")
        private List<String> remoteManagers;

        @JsonProperty("Nodes")
        private int nodes;

        @JsonProperty("Managers")
        private int managers;

        @JsonProperty("Cluster")
        private ClusterInfo cluster;

    }
}
