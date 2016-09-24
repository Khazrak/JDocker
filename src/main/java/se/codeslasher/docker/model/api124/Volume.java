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
@JsonDeserialize(builder = Volume.VolumeBuilder.class)
public class Volume {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Driver")
    private String driver;

    @JsonProperty("Mountpoint")
    private String mountPoint;

    @JsonProperty("Labels")
    private Map<String, String> labels;

    @JsonProperty("Scope")
    private String scope;

    @JsonPOJOBuilder(withPrefix = "")
    public static class VolumeBuilder {
        @JsonProperty("Name")
        private String name;

        @JsonProperty("Driver")
        private String driver;

        @JsonProperty("Mountpoint")
        private String mountPoint;

        @JsonProperty("Labels")
        private Map<String, String> labels;

        @JsonProperty("Scope")
        private String scope;
    }
}
