package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.Ulimit;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonDeserialize(builder = Ulimit129.Ulimit129Builder.class)
public class Ulimit129 implements Ulimit {

    private String name;
    private int soft;
    private int hard;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Ulimit129Builder {

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Soft")
        private int soft;

        @JsonProperty("Hard")
        private int hard;

    }

}
