package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by karl on 9/23/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = NetworkCreateIpam.NetworkCreateIpamBuilder.class)
public class NetworkCreateIpam {

    @JsonProperty("Driver")
    private String driver;

    @JsonProperty("Config")
    private List<NetworkCreateIpamConfig> config;

    @JsonProperty("Options")
    private Map<String,String> options;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkCreateIpamBuilder {

        @JsonProperty("Driver")
        private String driver = "default";

        @JsonProperty("Config")
        private List<NetworkCreateIpamConfig> config = new ArrayList<>();

        @JsonProperty("Options")
        private Map<String,String> options = new TreeMap<>();

    }
}
