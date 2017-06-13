package com.github.khazrak.jdocker.api126.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.khazrak.jdocker.abstraction.NetworkCreateIpam;
import com.github.khazrak.jdocker.abstraction.NetworkCreateRequest;
import com.github.khazrak.jdocker.exception.DockerApiException;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Getter
@Builder
@JsonDeserialize(builder = NetworkCreateRequest126.NetworkCreateRequest126Builder.class)
public class NetworkCreateRequest126 implements NetworkCreateRequest {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("CheckDuplicate")
    private boolean checkDuplicate;

    @JsonProperty("Driver")
    private String driver;

    @JsonProperty("EnableIPv6")
    private boolean enableIpv6;

    @JsonSerialize(as = NetworkCreateIpam126.class)
    @JsonProperty("IPAM")
    private NetworkCreateIpam ipam;

    @JsonProperty("Internal")
    private boolean internal;

    @JsonProperty("Options")
    private Map<String, String> options;

    @JsonProperty("Labels")
    private Map<String, String> labels;

    @JsonIgnore
    @Override
    public boolean isAttachable() {
        throw new DockerApiException();
    }

    @JsonIgnore
    @Override
    public boolean isIngress() {
        throw new DockerApiException();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkCreateRequest126Builder {

        @JsonProperty("Name")
        private String name;

        @JsonProperty("CheckDuplicate")
        private boolean checkDuplicate = true;

        @JsonProperty("Driver")
        private String driver = "bridge";

        @JsonProperty("EnableIPv6")
        private boolean enableIpv6 = false;

        @JsonDeserialize(as = NetworkCreateIpam126.class)
        @JsonProperty("IPAM")
        private NetworkCreateIpam ipam = NetworkCreateIpam126.builder().build();

        @JsonProperty("Internal")
        private boolean internal = false;

        @JsonProperty("Options")
        private Map<String, String> options = new TreeMap<>();

        @JsonProperty("Labels")
        private Map<String, String> labels = new TreeMap<>();

    }


}
