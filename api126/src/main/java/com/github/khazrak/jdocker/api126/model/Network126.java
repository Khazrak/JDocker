package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.Network;
import com.github.khazrak.jdocker.abstraction.NetworkContainer;
import com.github.khazrak.jdocker.abstraction.NetworkIPAM;
import com.github.khazrak.jdocker.exception.DockerApiException;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = Network126.Network126Builder.class)
public class Network126 implements Network {

    private String name;
    private String id;
    private String driver;
    private String scope;
    private boolean enableIpv6;
    private NetworkIPAM ipam;
    private boolean internal;
    private Map<String, String> options;
    private Map<String, NetworkContainer> containers;
    private Map<String, String> labels;
    private String created;
    private boolean attachable;

    public boolean isIngress() {
        throw new DockerApiException();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Network126Builder {

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Id")
        private String id;

        @JsonProperty("Driver")
        private String driver;

        @JsonProperty("Scope")
        private String scope;

        @JsonProperty("EnableIPv6")
        private boolean enableIpv6;

        @JsonDeserialize(as = NetworkIPAM126.class)
        @JsonProperty("IPAM")
        private NetworkIPAM ipam;

        @JsonProperty("Internal")
        private boolean internal;

        @JsonProperty("Options")
        private Map<String, String> options;

        @JsonProperty("Containers")
        private Map<String, NetworkContainer> containers;

        @JsonProperty("Labels")
        private Map<String, String> labels;

        @JsonProperty("Created")
        private String created;

        @JsonProperty("Attachable")
        private boolean attachable;

    }

}
