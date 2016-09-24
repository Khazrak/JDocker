package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * Created by karl on 9/24/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = NetworkIPAM.NetworkIPAMBuilder.class)
public class NetworkIPAM {

    @JsonProperty("Driver")
    private String driver;

    @JsonProperty("Config")
    private List<NetworkIPAMConfig> config;

    @JsonProperty("Options")
    private Map<String, String> options;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkIPAMBuilder {

        @JsonProperty("Driver")
        private String driver;

        @JsonProperty("Config")
        private List<NetworkIPAMConfig> config;

        @JsonProperty("Options")
        private Map<String, String> options;

    }

}
