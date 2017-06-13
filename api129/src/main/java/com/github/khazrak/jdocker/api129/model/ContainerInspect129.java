package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.*;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = ContainerInspect129.ContainerInspect129Builder.class)
public class ContainerInspect129 implements ContainerInspect {

    private long sizeRw;
    private long sizeRootFs;
    private GraphDriver graphDriver;
    private String appArmorProfile;
    private List<String> args;
    private ContainerInspectConfig config;
    private String created;
    private String driver;
    private List<String> execIds;
    private HostConfig hostConfig;
    private String hostNamePath;
    private String hostsPath;
    private String logPath;
    private String id;
    private String image;
    private String mountLabel;
    private String name;
    private NetworkSettings networkSettings;
    private String path;
    private String processLabel;
    private String resolveConfPath;
    private int restartCount;
    private State state;
    private List<Mount> mounts;


    @JsonPOJOBuilder(withPrefix = "")
    public static class ContainerInspect129Builder {

        @JsonProperty("SizeRw")
        private long sizeRw;

        @JsonProperty("SizeRootFs")
        private long sizeRootFs;

        @JsonDeserialize(as = GraphDriver129.class)
        @JsonProperty("GraphDriver")
        private GraphDriver graphDriver;

        @JsonProperty("AppArmorProfile")
        private String appArmorProfile;

        @JsonProperty("Args")
        private List<String> args;

        @JsonDeserialize(as = ContainerInspectConfig129.class)
        @JsonProperty("Config")
        private ContainerInspectConfig config;

        @JsonProperty("Created")
        private String created;

        @JsonProperty("Driver")
        private String driver;

        @JsonProperty("ExecIDs")
        private List<String> execIds;

        @JsonDeserialize(as = HostConfig129.class)
        @JsonProperty("HostConfig")
        private HostConfig hostConfig;

        @JsonProperty("HostnamePath")
        private String hostNamePath;

        @JsonProperty("HostsPath")
        private String hostsPath;

        @JsonProperty("LogPath")
        private String logPath;

        @JsonProperty("Id")
        private String id;

        @JsonProperty("Image")
        private String image;

        @JsonProperty("MountLabel")
        private String mountLabel;

        @JsonProperty("Name")
        private String name;

        @JsonDeserialize(as = NetworkSettings129.class)
        @JsonProperty("NetworkSettings")
        private NetworkSettings networkSettings;

        @JsonProperty("Path")
        private String path;

        @JsonProperty("ProcessLabel")
        private String processLabel;

        @JsonProperty("ResolvConfPath")
        private String resolveConfPath;

        @JsonProperty("RestartCount")
        private int restartCount;

        @JsonDeserialize(as = State129.class)
        @JsonProperty("State")
        private State state;

        @JsonProperty("Mounts")
        private List<Mount> mounts;

    }

}
