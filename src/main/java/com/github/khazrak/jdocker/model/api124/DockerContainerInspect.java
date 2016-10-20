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
