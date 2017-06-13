package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.ContainerFileSystemChange;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = ContainerFileSystemChange129.ContainerFileSystemChange129Builder.class)
@EqualsAndHashCode
public class ContainerFileSystemChange129 implements ContainerFileSystemChange {

    @JsonProperty("Path")
    private String path;

    @JsonProperty("Kind")
    private int kind;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ContainerFileSystemChange129Builder {
        @JsonProperty("Path")
        private String path;

        @JsonProperty("Kind")
        private int kind;
    }

}
