package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.HostPort;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = HostPort126.HostPort126Builder.class)
public class HostPort126 implements HostPort {

    private String hostIp;
    private String hostPort;

    @Override
    public String toString() {
        return String.format("Hostport = [%s:%s]", hostIp, hostPort);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class HostPort126Builder {

        @JsonProperty("HostIp")
        private String hostIp;

        @JsonProperty("HostPort")
        private String hostPort;

    }

}
