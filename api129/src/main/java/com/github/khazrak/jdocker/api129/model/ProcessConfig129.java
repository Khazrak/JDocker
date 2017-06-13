package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.ProcessConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = ProcessConfig129.ProcessConfig129Builder.class)
public class ProcessConfig129 implements ProcessConfig {

    private List<String> arguments;
    private String entryPoint;
    private boolean privileged;
    private boolean tty;
    private String user;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ProcessConfig129Builder {

        @JsonProperty("arguments")
        private List<String> arguments;

        @JsonProperty("entrypoint")
        private String entryPoint;

        @JsonProperty("privileged")
        private boolean privileged;

        @JsonProperty("tty")
        private boolean tty;

        @JsonProperty("user")
        private String user;

    }
}
