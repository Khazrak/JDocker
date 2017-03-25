package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.Mount;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = Mount126.Mount126Builder.class)
public class Mount126 implements Mount {

    private String name;
    private String source;
    private String destination;
    private String driver;
    private String mode;
    private String readwrite;
    private String propagation;
    private String type;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Mount126Builder {

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Source")
        private String source;

        @JsonProperty("Destination")
        private String destination;

        @JsonProperty("Driver")
        private String driver;

        @JsonProperty("Mode")
        private String mode;

        @JsonProperty("RW")
        private String readwrite;

        @JsonProperty("Propagation")
        private String propagation;

        @JsonProperty("Type")
        private String type;

    }

}
