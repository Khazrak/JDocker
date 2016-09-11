package se.codeslasher.docker;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Created by karl on 9/11/16.
 */
@Getter
@ToString
public class Container {

    @JsonProperty("Id")
    private String id;

    @JsonProperty("Names")
    private List<String> names;

    @JsonProperty("Image")
    private DockerImageName image;

    @JsonProperty("ImageID")
    private String imageId;

    @JsonProperty("Command")
    private String command;

    @JsonProperty("Created")
    private long created;

    @Singular
    @JsonProperty("Ports")
    private List<ContainerPort> ports;

    @Singular
    @JsonProperty("Labels")
    private Map<String,String> labels;

    @JsonProperty("State")
    private String state;

    @JsonProperty("Status")
    private String status;

    @JsonProperty("HostConfig")
    private HostConfig hostConfig;

    @JsonProperty("NetworkSettings")
    private NetworkSettings networkSettings;

    @JsonProperty("Mounts")
    private List<Mount> mounts;


}
