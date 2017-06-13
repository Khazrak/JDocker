package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.Mount;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = Mount129.Mount129Builder.class)
public class Mount129 implements Mount {

    private String name;
    private String source;
    private String destination;
    private String driver;
    private String mode;
    private String readwrite;
    private String propagation;
    private String type;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Mount129Builder {

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
