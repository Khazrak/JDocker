package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by karl on 9/21/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = GraphDriver.GraphDriverBuilder.class)
public class GraphDriver {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Data")
    private GraphDriverData data;

    @JsonPOJOBuilder(withPrefix = "")
    public static class GraphDriverBuilder {

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Data")
        private GraphDriverData data;
    }
}