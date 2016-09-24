package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * Created by karl on 9/24/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = Network.NetworkBuilder.class)
public class Network {

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

    @JsonProperty("IPAM")
    private NetworkIPAM ipam;

    @JsonProperty("Internal")
    private boolean internal;

    @JsonProperty("Options")
    private Map<String,String> options;

    @JsonProperty("Containers")
    private Map<String, NetworkContainer> containers;

    @JsonProperty("Labels")
    private Map<String, String> labels;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkBuilder {

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

        @JsonProperty("IPAM")
        private NetworkIPAM ipam;

        @JsonProperty("Internal")
        private boolean internal;

        @JsonProperty("Options")
        private Map<String,String> options;

        @JsonProperty("Containers")
        private Map<String, NetworkContainer> containers;

        @JsonProperty("Labels")
        private Map<String, String> labels;

    }
}
