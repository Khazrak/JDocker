package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.RestartPolicy;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = RestartPolicy129.RestartPolicy129Builder.class)
public class RestartPolicy129 implements RestartPolicy {

    private String name;
    private int maximumRetryCount;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RestartPolicy129Builder {
        @JsonProperty("Name")
        private String name = "";

        @JsonProperty("MaximumRetryCount")
        private int maximumRetryCount;
    }

}
