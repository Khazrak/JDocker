package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.IPAMConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = IPAMConfig126.IPAMConfig126Builder.class)
public class IPAMConfig126 implements IPAMConfig {

    private String ipv4Address;
    private String ipv6Address;
    private List<String> linkLocalIps;

    @JsonPOJOBuilder(withPrefix = "")
    public static class IPAMConfig126Builder {

        @JsonProperty("IPv4Address")
        private String ipv4Address;

        @JsonProperty("IPv6Address")
        private String ipv6Address;

        @JsonProperty("LinkLocalIPs")
        private List<String> linkLocalIps;

    }

}
