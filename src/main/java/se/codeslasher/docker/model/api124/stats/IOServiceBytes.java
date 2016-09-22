package se.codeslasher.docker.model.api124.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by karl on 9/22/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = IOServiceBytes.IOServiceBytesBuilder.class)
public class IOServiceBytes {

    @JsonProperty("major")
    private int major;

    @JsonProperty("minor")
    private int minor;

    @JsonProperty("op")
    private String op;

    @JsonProperty("value")
    private long value;

    @JsonPOJOBuilder(withPrefix = "")
    public static class IOServiceBytesBuilder {

        @JsonProperty("major")
        private int major;

        @JsonProperty("minor")
        private int minor;

        @JsonProperty("op")
        private String op;

        @JsonProperty("value")
        private long value;

    }
}
