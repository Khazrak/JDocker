package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * Created by karl on 9/25/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = VolumeCreateRequest.VolumeCreateRequestBuilder.class)
public class VolumeCreateRequest {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Driver")
    private String driver;

    @JsonProperty("Labels")
    private Map<String, String> labels;

    @JsonProperty("DriverOpts")
    private Map<String, String> driverOpts;

    @JsonPOJOBuilder(withPrefix = "")
    public static class VolumeCreateRequestBuilder {

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Driver")
        private String driver;

        @JsonProperty("Labels")
        private Map<String, String> labels;

        @JsonProperty("DriverOpts")
        private Map<String, String> driverOpts;
    }
}
