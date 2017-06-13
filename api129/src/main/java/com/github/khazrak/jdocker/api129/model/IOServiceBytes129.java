package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.IOServiceBytes;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = IOServiceBytes129.IOServiceBytes129Builder.class)
public class IOServiceBytes129 implements IOServiceBytes {

    @JsonProperty("major")
    private int major;

    @JsonProperty("minor")
    private int minor;

    @JsonProperty("op")
    private String op;

    @JsonProperty("value")
    private long value;

    @JsonPOJOBuilder(withPrefix = "")
    public static class IOServiceBytes129Builder {

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
