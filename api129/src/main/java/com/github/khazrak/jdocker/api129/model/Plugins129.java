package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.Plugins;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = Plugins129.Plugins129Builder.class)
public class Plugins129 implements Plugins {

    private List<String> volume;
    private List<String> network;
    private List<String> authorization;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Plugins129Builder {

        @JsonProperty("Volume")
        private List<String> volume;

        @JsonProperty("Network")
        private List<String> network;

        @JsonProperty("Authorization")
        private List<String> authorization;

    }


}
