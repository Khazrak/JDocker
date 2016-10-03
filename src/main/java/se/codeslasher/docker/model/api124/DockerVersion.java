package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = DockerVersion.DockerVersionBuilder.class)
public class DockerVersion {

    private String version;
    private String os;
    private String kernelVersion;
    private String goVersion;
    private String gitCommit;
    private String arch;
    private String apiVersion;
    private String buildTime;
    private boolean experimental;

    @JsonPOJOBuilder(withPrefix = "")
    public static class DockerVersionBuilder {

        @JsonProperty("Version")
        private String version;

        @JsonProperty("Os")
        private String os;

        @JsonProperty("KernelVersion")
        private String kernelVersion;

        @JsonProperty("GoVersion")
        private String goVersion;

        @JsonProperty("GitCommit")
        private String gitCommit;

        @JsonProperty("Arch")
        private String arch;

        @JsonProperty("ApiVersion")
        private String apiVersion;

        @JsonProperty("BuildTime")
        private String buildTime;

        @JsonProperty("Experimental")
        private boolean experimental;

    }

}
