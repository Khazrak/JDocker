package com.github.khazrak.jdocker.api126.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.khazrak.jdocker.abstraction.ContainerCreationRequest;
import com.github.khazrak.jdocker.abstraction.HealthCheck;
import com.github.khazrak.jdocker.abstraction.HostConfig;
import com.github.khazrak.jdocker.abstraction.NetworkingConfig;
import com.github.khazrak.jdocker.api126.model.NetworkingConfig126;
import com.github.khazrak.jdocker.utils.DockerImageName;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class ContainerCreationRequest126 implements ContainerCreationRequest {

    private transient String name;

    @JsonProperty(value = "Hostname")
    private String hostname;

    @JsonProperty(value = "Domainname")
    private String domainname;

    @JsonProperty("User")
    private String user;

    @JsonProperty("AttachStdin")
    private boolean attachStdin;

    @JsonProperty("AttachStdout")
    private boolean attachStdout;

    @JsonProperty("AttachStderr")
    private boolean attachStderr;

    @JsonProperty("HealthCheck")
    private HealthCheck healthCheck;

    @JsonProperty("Tty")
    private boolean tty;

    @JsonProperty("OpenStdin")
    private boolean openStdin;

    @JsonProperty("StdinOnce")
    private boolean stdinOnce;

    @Singular
    @JsonProperty("Env")
    private List<String> environmentVariables;

    @JsonProperty("Cmd")
    private List<String> commands;

    @JsonProperty("Entrypoint")
    private String entrypoint;

    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @JsonProperty("Image")
    private DockerImageName image;

    @Singular
    @JsonProperty("Labels")
    private Map<String,String> labels;

    //volumes map
    @Singular()
    @JsonProperty("Volumes")
    private Map<String,Object> volumes;

    @JsonProperty("WorkingDir")
    private String workingDir;

    @JsonProperty("NetworkDisabled")
    private boolean networkDisabled;

    @JsonProperty("MacAddress")
    private String macAddress;

    @Singular
    @JsonProperty("ExposedPorts")
    private Map<String,Object> exposedPorts;

    @JsonProperty("StopSignal")
    private String stopSignal;

    @JsonProperty("HostConfig")
    private HostConfig hostConfig;

    @JsonProperty("NetworkingConfig")
    private NetworkingConfig networkingConfig;

    //Made to give default values to variables, Lombok replaces the "normal" ones
    public static class ContainerCreationRequest126Builder {
        private String hostname = "";
        private String domainname = "";
        private String user = "";
        private String workingDir = "";
        private String stopSignal = "SIGTERM";
        private NetworkingConfig networkingConfig = new NetworkingConfig126();
    }

}
