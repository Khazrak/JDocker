package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.IPAMConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = IPAMConfig129.IPAMConfig129Builder.class)
public class IPAMConfig129 implements IPAMConfig {

    private String ipv4Address;
    private String ipv6Address;
    private List<String> linkLocalIps;

    @JsonPOJOBuilder(withPrefix = "")
    public static class IPAMConfig129Builder {

        @JsonProperty("IPv4Address")
        private String ipv4Address;

        @JsonProperty("IPv6Address")
        private String ipv6Address;

        @JsonProperty("LinkLocalIPs")
        private List<String> linkLocalIps;

    }

}
