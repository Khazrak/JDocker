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
import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = ImageContainerConfig.ImageContainerConfigBuilder.class)
public class ImageContainerConfig {

    @JsonProperty("AttachStderr")
    private boolean attachStderr;

    @JsonProperty("AttachStdin")
    private boolean attachStdin;

    @JsonProperty("AttachStdout")
    private boolean attachStdout;

    @JsonProperty("Cmd")
    private List<String> cmd;

    @JsonProperty("Domainname")
    private String domainName;

    @JsonProperty("Entrypoint")
    private List<String> entrypoint;

    @JsonProperty("Env")
    private List<String> envs;

    @JsonProperty("ExposedPorts")
    private Map<String, Object> exposedPorts;

    @JsonProperty("Hostname")
    private String hostName;

    @JsonProperty("User")
    private String user;

    @JsonProperty("WorkingDir")
    private String workingDir;

    @JsonProperty("Volumes")
    private Map<String,Map<String,String>> volumes;

    @JsonProperty("Tty")
    private boolean tty;

    @JsonProperty("OpenStdin")
    private boolean openStdin;

    @JsonProperty("StdinOnce")
    private boolean stdinOnce;

    @JsonProperty("ArgsEscaped")
    private boolean argsEscaped;

    @JsonProperty("Image")
    private String image;

    @JsonProperty("OnBuild")
    private List<String> onBuild;

    @JsonProperty("Labels")
    private Map<String,String> labels;

    @JsonProperty("NetworkDisabled")
    private boolean networkDisabled;

    @JsonProperty("PublishService")
    private String publishService;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ImageContainerConfigBuilder {

        @JsonProperty("AttachStderr")
        private boolean attachStderr;

        @JsonProperty("AttachStdin")
        private boolean attachStdin;

        @JsonProperty("AttachStdout")
        private boolean attachStdout;

        @JsonProperty("Cmd")
        private List<String> cmd;

        @JsonProperty("Domainname")
        private String domainName;

        @JsonProperty("Entrypoint")
        private List<String> entrypoint;

        @JsonProperty("Env")
        private List<String> envs;

        @JsonProperty("ExposedPorts")
        private Map<String, Object> exposedPorts;

        @JsonProperty("Hostname")
        private String hostName;

        @JsonProperty("User")
        private String user;

        @JsonProperty("WorkingDir")
        private String workingDir;

        @JsonProperty("Volumes")
        private Map<String,Map<String,String>> volumes;

        @JsonProperty("Tty")
        private boolean tty;

        @JsonProperty("OpenStdin")
        private boolean openStdin;

        @JsonProperty("StdinOnce")
        private boolean stdinOnce;

        @JsonProperty("ArgsEscaped")
        private boolean argsEscaped;

        @JsonProperty("Image")
        private String image;

        @JsonProperty("OnBuild")
        private List<String> onBuild;

        @JsonProperty("Labels")
        private Map<String,String> labels;

        @JsonProperty("NetworkDisabled")
        private boolean networkDisabled;

        @JsonProperty("PublishService")
        private String publishService;
    }
}
