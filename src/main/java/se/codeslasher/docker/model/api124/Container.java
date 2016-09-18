package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;
import se.codeslasher.docker.model.api124.*;

import java.util.List;
import java.util.Map;

/**
 * Created by karl on 9/11/16.
 */
@JsonPropertyOrder({
        "Id",
        "Names",
        "Image",
        "ImageID",
        "Command",
        "Created",
        "State",
        "Status",
        "Ports",
        "Labels",
        "SizeRw",
        "SizeRootFs",
        "HostConfig",
        "NetworkSettings",
        "Mounts"
})
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

    @JsonProperty("State")
    private String state;

    @JsonProperty("Status")
    private String status;

    @Singular
    @JsonProperty("Ports")
    private List<ContainerPort> ports;

    @Singular
    @JsonProperty("Labels")
    private Map<String,String> labels;

    @JsonProperty("SizeRw")
    private long sizeRw;

    @JsonProperty("SizeRootFs")
    private long sizeRootFs;

    @JsonProperty("HostConfig")
    private HostConfig hostConfig;

    @JsonProperty("NetworkSettings")
    private NetworkSettings networkSettings;

    @JsonProperty("Mounts")
    private List<Mount> mounts;


}
