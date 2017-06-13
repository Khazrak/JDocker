package com.github.khazrak.jdocker.api129.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.khazrak.jdocker.abstraction.NetworkCreateIpam;
import com.github.khazrak.jdocker.abstraction.NetworkCreateRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Getter
@Builder
@JsonDeserialize(builder = NetworkCreateRequest129.NetworkCreateRequest129Builder.class)
public class NetworkCreateRequest129 implements NetworkCreateRequest {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("CheckDuplicate")
    private boolean checkDuplicate;

    @JsonProperty("Driver")
    private String driver;

    @JsonProperty("EnableIPv6")
    private boolean enableIpv6;

    @JsonSerialize(as = NetworkCreateIpam129.class)
    @JsonProperty("IPAM")
    private NetworkCreateIpam ipam;

    @JsonProperty("Internal")
    private boolean internal;

    @JsonProperty("Attachable")
    private boolean attachable;

    @JsonProperty(" Ingress ")
    private boolean ingress;

    @JsonProperty("Options")
    private Map<String, String> options;

    @JsonProperty("Labels")
    private Map<String, String> labels;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkCreateRequest129Builder {

        @JsonProperty("Name")
        private String name;

        @JsonProperty("CheckDuplicate")
        private boolean checkDuplicate = true;

        @JsonProperty("Driver")
        private String driver = "bridge";

        @JsonProperty("EnableIPv6")
        private boolean enableIpv6 = false;

        @JsonDeserialize(as = NetworkCreateIpam129.class)
        @JsonProperty("IPAM")
        private NetworkCreateIpam ipam = NetworkCreateIpam129.builder().build();

        @JsonProperty("Internal")
        private boolean internal = false;

        @JsonProperty("Attachable")
        private boolean attachable = true;

        @JsonProperty(" Ingress ")
        private boolean ingress = false;

        @JsonProperty("Options")
        private Map<String, String> options = new TreeMap<>();

        @JsonProperty("Labels")
        private Map<String, String> labels = new TreeMap<>();

    }


}
