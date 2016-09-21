package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created by karl on 9/21/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = ContainerProcesses.ContainerProcessesBuilder.class)
public class ContainerProcesses {

    @JsonProperty("Titles")
    private List<String> titles;

    @JsonProperty("Processes")
    private List<List<String>> processes;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ContainerProcessesBuilder {
        @JsonProperty("Titles")
        private List<String> titles;

        @JsonProperty("Processes")
        private List<List<String>> processes;
    }

}
