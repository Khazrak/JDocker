package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.NetworkIPAMConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = NetworkIPAMConfig129.NetworkIPAMConfig129Builder.class)
public class NetworkIPAMConfig129 implements NetworkIPAMConfig {

    private String subnet;
    private String gateway;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkIPAMConfig129Builder {

        @JsonProperty("Subnet")
        private String subnet;

        @JsonProperty("Gateway")
        private String gateway;

    }

}
