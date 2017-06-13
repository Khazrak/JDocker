package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.ExecInfo;
import com.github.khazrak.jdocker.abstraction.ProcessConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = ExecInfo126.ExecInfo126Builder.class)
public class ExecInfo126 implements ExecInfo {

    private boolean canRemove;
    private String containerId;
    private String detachKeys;
    private int exitCode;
    private String id;
    private boolean openStdErr;
    private boolean openStdIn;
    private boolean openStdOut;
    private ProcessConfig processConfig;
    private boolean running;
    private int pid;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ExecInfo126Builder {

        @JsonProperty("CanRemove")
        private boolean canRemove;

        @JsonProperty("ContainerID")
        private String containerId;

        @JsonProperty("DetachKeys")
        private String detachKeys;

        @JsonProperty("ExitCode")
        private int exitCode;

        @JsonProperty("ID")
        private String id;

        @JsonProperty("OpenStderr")
        private boolean openStdErr;

        @JsonProperty("OpenStdin")
        private boolean openStdIn;

        @JsonProperty("OpenStdout")
        private boolean openStdOut;

        @JsonDeserialize(as = ProcessConfig126.class)
        @JsonProperty("ProcessConfig")
        private ProcessConfig processConfig;

        @JsonProperty("Running")
        private boolean running;

        @JsonProperty("Pid")
        private int pid;

    }

}
