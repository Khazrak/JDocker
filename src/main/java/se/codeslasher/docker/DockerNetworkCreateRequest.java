package se.codeslasher.docker;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import se.codeslasher.docker.model.api124.NetworkCreateIpam;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by karl on 9/23/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = DockerNetworkCreateRequest.DockerNetworkCreateRequestBuilder.class)
public class DockerNetworkCreateRequest {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("CheckDuplicate")
    private boolean checkDuplicate;

    @JsonProperty("Driver")
    private String driver;

    @JsonProperty("EnableIPv6")
    private boolean enableIpv6;

    @JsonProperty("IPAM")
    private NetworkCreateIpam ipam;

    @JsonProperty("Internal")
    private boolean internal;

    @JsonProperty("Options")
    private Map<String,String> options;

    @JsonProperty("Labels")
    private Map<String,String> labels;

    @JsonPOJOBuilder(withPrefix = "")
    public static class DockerNetworkCreateRequestBuilder {

        @JsonProperty("Name")
        private String name;

        @JsonProperty("CheckDuplicate")
        private boolean checkDuplicate = true;

        @JsonProperty("Driver")
        private String driver = "bridge";

        @JsonProperty("EnableIPv6")
        private boolean enableIpv6 = false;

        @JsonProperty("IPAM")
        private NetworkCreateIpam ipam = NetworkCreateIpam.builder().build();

        @JsonProperty("Internal")
        private boolean internal = false;

        @JsonProperty("Options")
        private Map<String,String> options = new TreeMap<>();

        @JsonProperty("Labels")
        private Map<String,String> labels = new TreeMap<>();

    }

}
