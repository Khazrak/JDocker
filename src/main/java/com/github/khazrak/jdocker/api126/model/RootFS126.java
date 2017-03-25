package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.RootFS;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = RootFS126.RootFS126Builder.class)
public class RootFS126 implements RootFS {

    private String type;
    private List<String> layers;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RootFS126Builder {

        @JsonProperty("Type")
        private String type;

        @JsonProperty("Layers")
        private List<String> layers;

    }

}
