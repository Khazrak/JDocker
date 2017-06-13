package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.RootFS;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = RootFS129.RootFS129Builder.class)
public class RootFS129 implements RootFS {

    private String type;
    private List<String> layers;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RootFS129Builder {

        @JsonProperty("Type")
        private String type;

        @JsonProperty("Layers")
        private List<String> layers;

    }

}
