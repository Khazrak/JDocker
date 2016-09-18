package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by karl on 9/11/16.
 */
@Builder
@Getter
@JsonPropertyOrder({
        "Name",
        "Source",
        "Destination",
        "Driver",
        "Mode",
        "RW",
        "Propagation"
})
@JsonDeserialize(builder = Mount.MountBuilder.class)
public class Mount {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Source")
    private String source;

    @JsonProperty("Destination")
    private String destination;

    @JsonProperty("Driver")
    private String driver;

    @JsonProperty("Mode")
    private String mdoe;

    @JsonProperty("RW")
    private String readwrite;

    @JsonProperty("Propagation")
    private String propagation;

    @JsonPOJOBuilder(withPrefix = "")
    public static class MountBuilder {
        @JsonProperty("Name")
        private String name;

        @JsonProperty("Source")
        private String source;

        @JsonProperty("Destination")
        private String destination;

        @JsonProperty("Driver")
        private String driver;

        @JsonProperty("Mode")
        private String mdoe;

        @JsonProperty("RW")
        private String readwrite;

        @JsonProperty("Propagation")
        private String propagation;
    }

}
