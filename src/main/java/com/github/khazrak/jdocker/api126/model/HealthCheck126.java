package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.HealthCheck;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = HealthCheck126.HealthCheck126Builder.class)
public class HealthCheck126 implements HealthCheck {

    @JsonProperty("Test")
    private String [] test;

    @JsonProperty("Interval")
    private int interval;

    @JsonProperty("Timeout")
    private int timeout;

    @JsonProperty("Retries")
    private int retries;

    @JsonPOJOBuilder(withPrefix = "")
    public static class HealthCheck126Builder {


    }

}
