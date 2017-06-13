package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.HostPort;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = HostPort129.HostPort129Builder.class)
public class HostPort129 implements HostPort {

    private String hostIp;
    private String hostPort;

    @Override
    public String toString() {
        return String.format("Hostport = [%s:%s]", hostIp, hostPort);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class HostPort129Builder {

        @JsonProperty("HostIp")
        private String hostIp;

        @JsonProperty("HostPort")
        private String hostPort;

    }

}
