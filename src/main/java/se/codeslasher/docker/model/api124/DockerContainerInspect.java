package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created by karl on 9/19/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = DockerContainerInspect.DockerContainerInspectBuilder.class)
public class DockerContainerInspect {

    @JsonProperty("SizeRw")
    private long sizeRw;

    @JsonProperty("SizeRootFs")
    private long sizeRootFs;

    @JsonProperty("GraphDriver")
    private GraphDriver graphDriver;

    @JsonProperty("AppArmorProfile")
    private String appArmorProfile;

    @JsonProperty("Args")
    private List<String> args;

    @JsonProperty("Config")
    private ContainerInspectConfig config;

    @JsonProperty("Created")
    private String created;

    @JsonProperty("Driver")
    private String driver;

    @JsonProperty("ExecIDs")
    private List<String> execIds;

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

    @JsonProperty("State")
    private State state;

    @JsonProperty("Mounts")
    private List<Mount> mounts;


    @JsonPOJOBuilder(withPrefix = "")
    public static class DockerContainerInspectBuilder {

        @JsonProperty("SizeRw")
        private long sizeRw;

        @JsonProperty("SizeRootFs")
        private long sizeRootFs;

        @JsonProperty("GraphDriver")
        private GraphDriver graphDriver;

        @JsonProperty("AppArmorProfile")
        private String appArmorProfile;

        @JsonProperty("Args")
        private List<String> args;

        @JsonProperty("Config")
        private ContainerInspectConfig config;

        @JsonProperty("Created")
        private String created;

        @JsonProperty("Driver")
        private String driver;

        @JsonProperty("ExecIDs")
        private List<String> execIds;

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

        @JsonProperty("State")
        private State state;

        @JsonProperty("Mounts")
        private List<Mount> mounts;

    }


}
