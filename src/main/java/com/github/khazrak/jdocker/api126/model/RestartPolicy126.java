package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.RestartPolicy;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = RestartPolicy126.RestartPolicy126Builder.class)
public class RestartPolicy126 implements RestartPolicy {

    private String name;
    private int maximumRetryCount;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RestartPolicy126Builder {
        @JsonProperty("Name")
        private String name = "";

        @JsonProperty("MaximumRetryCount")
        private int maximumRetryCount;
    }

}
