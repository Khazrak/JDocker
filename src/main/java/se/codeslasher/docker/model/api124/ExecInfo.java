package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by karl on 9/29/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = ExecInfo.ExecInfoBuilder.class)
public class ExecInfo {

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

    @JsonPOJOBuilder(withPrefix = "")
    public static class ExecInfoBuilder {

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

        @JsonProperty("ProcessConfig")
        private ProcessConfig processConfig;

        @JsonProperty("Running")
        private boolean running;

    }

}
