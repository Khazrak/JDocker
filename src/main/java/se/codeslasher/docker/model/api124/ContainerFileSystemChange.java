package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by karl on 9/21/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = ContainerFileSystemChange.ContainerFileSystemChangeBuilder.class)
@EqualsAndHashCode
public class ContainerFileSystemChange {

    @JsonProperty("Path")
    private String path;

    @JsonProperty("Kind")
    private int kind;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ContainerFileSystemChangeBuilder {
        @JsonProperty("Path")
        private String path;

        @JsonProperty("Kind")
        private int kind;
    }

}
