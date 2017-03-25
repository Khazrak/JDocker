package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.ContainerProcesses;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = ContainerProcesses126.ContainerProcesses126Builder.class)
public class ContainerProcesses126 implements ContainerProcesses {

    @JsonProperty("Titles")
    private List<String> titles;

    @JsonProperty("Processes")
    private List<List<String>> processes;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ContainerProcesses126Builder {
        @JsonProperty("Titles")
        private List<String> titles;

        @JsonProperty("Processes")
        private List<List<String>> processes;
    }

}
