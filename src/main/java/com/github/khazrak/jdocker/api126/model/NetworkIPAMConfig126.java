package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.NetworkIPAMConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = NetworkIPAMConfig126.NetworkIPAMConfig126Builder.class)
public class NetworkIPAMConfig126 implements NetworkIPAMConfig {

    private String subnet;
    private String gateway;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkIPAMConfig126Builder {

        @JsonProperty("Subnet")
        private String subnet;

        @JsonProperty("Gateway")
        private String gateway;

    }

}
