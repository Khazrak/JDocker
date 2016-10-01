package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created by karl on 9/29/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = ProcessConfig.ProcessConfigBuilder.class)
public class ProcessConfig {

    private List<String> arguments;
    private String entryPoint;
    private boolean privileged;
    private boolean tty;
    private String user;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ProcessConfigBuilder {

        @JsonProperty("arguments")
        private List<String> arguments;

        @JsonProperty("entrypoint")
        private String entryPoint;

        @JsonProperty("privileged")
        private boolean privileged;

        @JsonProperty("tty")
        private boolean tty;

        @JsonProperty("user")
        private String user;

    }

}
