package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.State;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonDeserialize(builder = State129.State129Builder.class)
public class State129 implements State {

    private String error;
    private int exitCode;
    private String finishedAt;
    private boolean oomKilled;
    private boolean dead;
    private boolean paused;
    private int pid;
    private boolean restarting;
    private boolean running;
    private String startedAt;
    private String status;

    @JsonPOJOBuilder(withPrefix = "")
    public static class State129Builder {

        @JsonProperty("Error")
        private String error;

        @JsonProperty("ExitCode")
        private int exitCode;

        @JsonProperty("FinishedAt")
        private String finishedAt;

        @JsonProperty("OOMKilled")
        private boolean oomKilled;

        @JsonProperty("Dead")
        private boolean dead;

        @JsonProperty("Paused")
        private boolean paused;

        @JsonProperty("Pid")
        private int pid;

        @JsonProperty("Restarting")
        private boolean restarting;

        @JsonProperty("Running")
        private boolean running;

        @JsonProperty("StartedAt")
        private String startedAt;

        @JsonProperty("Status")
        private String status;

    }

}
