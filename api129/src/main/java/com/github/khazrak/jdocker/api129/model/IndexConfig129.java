package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.IndexConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = IndexConfig129.IndexConfig129Builder.class)
public class IndexConfig129 implements IndexConfig {

    private List<String> mirrors;
    private String name;
    private boolean official;
    private boolean secure;

    @JsonPOJOBuilder(withPrefix = "")
    public static class IndexConfig129Builder {


        @JsonProperty("Mirrors")
        private List<String> mirrors;

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Official")
        private boolean official;

        @JsonProperty("Secure")
        private boolean secure;

    }

}
