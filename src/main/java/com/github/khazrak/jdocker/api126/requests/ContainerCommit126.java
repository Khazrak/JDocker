package com.github.khazrak.jdocker.api126.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.khazrak.jdocker.abstraction.ContainerCommit;
import com.github.khazrak.jdocker.abstraction.ContainerCommitRequestMount;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class ContainerCommit126 implements ContainerCommit {

    @JsonProperty("Hostname")
    private String hostName;

    @JsonProperty("Domainname")
    private String domainName;

    @JsonProperty("User")
    private String user;

    @JsonProperty("AttachStdin")
    private boolean attachStdin;

    @JsonProperty("AttachStdout")
    private boolean attachStdout;

    @JsonProperty("Tty")
    private boolean tty;

    @JsonProperty("OpenStdin")
    private boolean openStdin;

    @JsonProperty("StdinOnce")
    private boolean stdinOnce;

    @JsonProperty("Env")
    private Map<String, String> environment;

    @JsonProperty("Cmd")
    private List<String> command;

    @JsonProperty("Mounts")
    private List<ContainerCommitRequestMount> mounts;

    @JsonProperty("Labels")
    private Map<String, String> labels;

    @JsonProperty("WorkingDir")
    private String workingDir;

    @JsonProperty("NetworkDisabled")
    private boolean networkDisabled;

    @JsonProperty("ExposedPorts")
    private Map<String, Object> exposedPorts;

}
