package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.GraphDriver;
import com.github.khazrak.jdocker.abstraction.GraphDriverData;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = GraphDriver126.GraphDriver126Builder.class)
public class GraphDriver126 implements GraphDriver {

    private String name;
    private GraphDriverData data;

    @JsonPOJOBuilder(withPrefix = "")
    public static class GraphDriver126Builder {

        @JsonProperty("Name")
        private String name;

        @JsonDeserialize(as = GraphDriverData126.class)
        @JsonProperty("Data")
        private GraphDriverData data;
    }

}
