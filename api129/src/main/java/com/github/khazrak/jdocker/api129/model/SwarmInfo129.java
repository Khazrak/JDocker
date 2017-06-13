package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.ClusterInfo;
import com.github.khazrak.jdocker.abstraction.SwarmInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = SwarmInfo129.SwarmInfo129Builder.class)
public class SwarmInfo129 implements SwarmInfo {

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
    public static class SwarmInfo129Builder {

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

        @JsonDeserialize(as = ClusterInfo129.class)
        @JsonProperty("Cluster")
        private ClusterInfo cluster;

    }

}
