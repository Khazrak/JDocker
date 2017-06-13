package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.HealthCheck;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = HealthCheck129.HealthCheck129Builder.class)
public class HealthCheck129 implements HealthCheck {

    @JsonProperty("Test")
    private String[] test;

    @JsonProperty("Interval")
    private long interval;

    @JsonProperty("Timeout")
    private int timeout;

    @JsonProperty("Retries")
    private int retries;

    @JsonPOJOBuilder(withPrefix = "")
    public static class HealthCheck129Builder {


    }

}
