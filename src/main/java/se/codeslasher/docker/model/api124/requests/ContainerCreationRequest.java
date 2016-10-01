package se.codeslasher.docker.model.api124.requests;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import okhttp3.MediaType;
import se.codeslasher.docker.model.api124.HostConfig;
import se.codeslasher.docker.model.api124.NetworkingConfig;

import java.util.List;
import java.util.Map;

/**
 * Created by karl on 9/6/16.
 */
@JsonPropertyOrder({
        "Hostname",
        "Domainname",
        "User",
        "AttachStdin",
        "AttachStdout",
        "AttachStderr",
        "Tty",
        "OpenStdin",
        "StdinOnce",
        "Env",
        "Cmd",
        "ImageInfo",
        "Volumes",
        "WorkingDir",
        "Entrypoint",
        "OnBuild",
        "Labels",
        "HostConfig",
        "NetworkingConfig"
})
@Getter
@Builder
public class ContainerCreationRequest {

    private transient String name;

    @JsonProperty(value = "Hostname")
    private String hostname;

    @JsonProperty(value = "Domainname")
    private String domainname;

    @JsonProperty("User")
    private String user;

    @JsonProperty("AttachStdin")
    private boolean attachStdin;

    @JsonProperty("AttachStdout")
    private boolean attachStdout;

    @JsonProperty("AttachStderr")
    private boolean attachStderr;

    @JsonProperty("Tty")
    private boolean tty;

    @JsonProperty("OpenStdin")
    private boolean openStdin;

    @JsonProperty("StdinOnce")
    private boolean stdinOnce;

    @Singular
    @JsonProperty("Env")
    private List<String> environmentVariables;

    @JsonProperty("Cmd")
    private List<String> commands;

    @JsonProperty("Entrypoint")
    private String entrypoint;

    @JsonProperty("Image")
    private String image;

    @Singular
    @JsonProperty("Labels")
    private Map<String,String> labels;

    //volumes map
    @Singular()
    @JsonProperty("Volumes")
    private Map<String,Object> volumes;

    @JsonProperty("WorkingDir")
    private String workingDir;

    @JsonProperty("NetworkDisabled")
    private boolean networkDisabled;

    @JsonProperty("MacAddress")
    private String macAddress;

    @Singular
    @JsonProperty("ExposedPorts")
    private Map<String,Object> exposedPorts;

    @JsonProperty("StopSignal")
    private String stopSignal;

    @JsonProperty("HostConfig")
    private HostConfig hostConfig;

    @JsonProperty("NetworkingConfig")
    private NetworkingConfig networkingConfig;

    //Made to give default values to variables, Lombok replaces the "normal" ones
    public static class ContainerCreationRequestBuilder {
        private String hostname = "";
        private String domainname = "";
        private String user = "";
        private String workingDir = "";
        private String stopSignal = "SIGTERM";
        private NetworkingConfig networkingConfig = new NetworkingConfig();
    }

}
