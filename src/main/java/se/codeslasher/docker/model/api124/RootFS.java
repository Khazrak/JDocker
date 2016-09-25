package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created by karl on 9/25/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = RootFS.RootFSBuilder.class)
public class RootFS {

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Layers")
    private List<String> layers;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RootFSBuilder {

        @JsonProperty("Type")
        private String type;

        @JsonProperty("Layers")
        private List<String> layers;

    }
}
