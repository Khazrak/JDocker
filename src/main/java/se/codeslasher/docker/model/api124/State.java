package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by karl on 9/20/16.
 */
@Builder
@Getter
@JsonDeserialize(builder = State.StateBuilder.class)
public class State {

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

    @JsonPOJOBuilder(withPrefix = "")
    public static class StateBuilder {

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
