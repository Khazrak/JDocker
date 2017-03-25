package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.ImageInspectContainerInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
@Getter
@Builder
@JsonDeserialize(builder = ImageInspectContainerInfo126.ImageInspectContainerInfo126Builder.class)
public class ImageInspectContainerInfo126 implements ImageInspectContainerInfo {

    private boolean attachStderr;
    private boolean attachStdin;
    private boolean attachStdout;
    private List<String> cmd;
    private String domainName;
    private List<String> entrypoint;
    private List<String> envs;
    private Map<String, Object> exposedPorts;
    private String hostName;
    private boolean tty;
    private boolean openStdin;
    private boolean stdinOnce;
    private boolean argsEscaped;
    private String image;
    private Map<String,String> labels;
    private boolean networkDisabled;
    private List<String> onBuild;
    private String publishService;
    private String user;
    private Map<String,Map<String,String>> volumes;
    private String workingDir;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ImageInspectContainerInfo126Builder {

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
