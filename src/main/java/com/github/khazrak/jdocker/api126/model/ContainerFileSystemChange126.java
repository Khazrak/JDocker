package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.ContainerFileSystemChange;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = ContainerFileSystemChange126.ContainerFileSystemChange126Builder.class)
@EqualsAndHashCode
public class ContainerFileSystemChange126 implements ContainerFileSystemChange {

    @JsonProperty("Path")
    private String path;

    @JsonProperty("Kind")
    private int kind;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ContainerFileSystemChange126Builder {
        @JsonProperty("Path")
        private String path;

        @JsonProperty("Kind")
        private int kind;
    }

}
