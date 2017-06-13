package com.github.khazrak.jdocker.api126.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = NetworkDisconnectRequest126.NetworkDisconnectRequest126Builder.class)
public class NetworkDisconnectRequest126 {

    @JsonProperty("Container")
    private String container;

    @JsonProperty("Force")
    private boolean force;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkDisconnectRequest126Builder {

    }

}
