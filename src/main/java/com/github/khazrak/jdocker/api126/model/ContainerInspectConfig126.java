package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.ContainerInspectConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Builder
@Getter
@JsonDeserialize(builder = ContainerInspectConfig126.ContainerInspectConfig126Builder.class)
public class ContainerInspectConfig126 implements ContainerInspectConfig {

       private boolean attachStderr;
       private boolean attachStdin;
       private boolean attachStdout;
       private List<String> cmd;
       private String domainName;
       private List<String> entrypoint;
       private List<String> envs;
       private Map<String, Object> exposedPorts;
       private String hostName;
       private String image;
       private Map<String,String> labels;
       private String macAddress;
       private boolean networkDisabled;
       private List<String> onBuild;
       private boolean openStdin;
       private boolean stdinOnce;
       private boolean tty;
       private String user;
       private Map<String,Map<String,String>> volumes;
       private String workingDir;
       private String stopSignal;
       private boolean argsEscaped;


    @JsonPOJOBuilder(withPrefix = "")
    public static class ContainerInspectConfig126Builder {

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

        @JsonProperty("Image")
        private String image;

        @JsonProperty("Labels")
        private Map<String,String> labels;

        @JsonProperty("MacAddress")
        private String macAddress;

        @JsonProperty("NetworkDisabled")
        private boolean networkDisabled;

        @JsonProperty("OnBuild")
        private List<String> onBuild;

        @JsonProperty("OpenStdin")
        private boolean openStdin;

        @JsonProperty("StdinOnce")
        private boolean stdinOnce;

        @JsonProperty("Tty")
        private boolean tty;

        @JsonProperty("User")
        private String user;

        @JsonProperty("Volumes")
        private Map<String,Map<String,String>> volumes;

        @JsonProperty("WorkingDir")
        private String workingDir;

        @JsonProperty("StopSignal")
        private String stopSignal = "SIGTERM";

        @JsonProperty("ArgsEscaped")
        private boolean argsEscaped;
    }

}
