package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.IOServiceBytes;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = IOServiceBytes126.IOServiceBytes126Builder.class)
public class IOServiceBytes126 implements IOServiceBytes {

    @JsonProperty("major")
    private int major;

    @JsonProperty("minor")
    private int minor;

    @JsonProperty("op")
    private String op;

    @JsonProperty("value")
    private long value;

    @JsonPOJOBuilder(withPrefix = "")
    public static class IOServiceBytes126Builder {

        @JsonProperty("major")
        private int major;

        @JsonProperty("minor")
        private int minor;

        @JsonProperty("op")
        private String op;

        @JsonProperty("value")
        private long value;

    }

}
