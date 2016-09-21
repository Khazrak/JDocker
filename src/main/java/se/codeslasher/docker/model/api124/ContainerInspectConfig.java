package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * Created by karl on 9/19/16.
 */
@Builder
@Getter
@JsonDeserialize(builder = ContainerInspectConfig.ContainerInspectConfigBuilder.class)
public class ContainerInspectConfig {

    @JsonProperty("AttachStderr")
    private boolean attachStderr;

    @JsonProperty("AttachStdin")
    private boolean attachStdin;

    @JsonProperty("AttachStdout")
    private boolean attachStdout;

    @JsonProperty("Cmd")
    private List<String> cmd;

    @JsonProperty("Domainname")
    private String domainName;

    @JsonProperty("Entrypoint")
    private List<String> entrypoint;

    @JsonProperty("Env")
    private List<String> envs;

    @JsonProperty("ExposedPorts")
    private Map<String, Object> exposedPorts;

    @JsonProperty("Hostname")
    private String hostName;

    @JsonProperty("Image")
    private String image;

    @JsonProperty("Labels")
    private Map<String,String> labels;

    @JsonProperty("MacAddress")
    private String macAddress;

    @JsonProperty("NetworkDisabled")
    private boolean networkDisabled;

    @JsonProperty("OnBuild")
    private List<String> onBuild;

    @JsonProperty("OpenStdin")
    private boolean openStdin;

    @JsonProperty("StdinOnce")
    private boolean stdinOnce;

    @JsonProperty("Tty")
    private boolean tty;

    @JsonProperty("User")
    private String user;

    @JsonProperty("Volumes")
    private Map<String,Map<String,String>> volumes;

    @JsonProperty("WorkingDir")
    private String workingDir;

    @JsonProperty("StopSignal")
    private String stopSignal;


    @JsonPOJOBuilder(withPrefix = "")
    public static class ContainerInspectConfigBuilder {


        @JsonProperty("AttachStderr")
        private boolean attachStderr;

        @JsonProperty("AttachStdin")
        private boolean attachStdin;

        @JsonProperty("AttachStdout")
        private boolean attachStdout;

        @JsonProperty("Cmd")
        private List<String> cmd;

        @JsonProperty("Domainname")
        private String domainName;

        @JsonProperty("Entrypoint")
        private List<String> entrypoint;

        @JsonProperty("Env")
        private List<String> envs;

        @JsonProperty("ExposedPorts")
        private Map<String, Object> exposedPorts;

        @JsonProperty("Hostname")
        private String hostName;

        @JsonProperty("Image")
        private String image;

        @JsonProperty("Labels")
        private Map<String,String> labels;

        @JsonProperty("MacAddress")
        private String macAddress;

        @JsonProperty("NetworkDisabled")
        private boolean networkDisabled;

        @JsonProperty("OnBuild")
        private List<String> onBuild;

        @JsonProperty("OpenStdin")
        private boolean openStdin;

        @JsonProperty("StdinOnce")
        private boolean stdinOnce;

        @JsonProperty("Tty")
        private boolean tty;

        @JsonProperty("User")
        private String user;

        @JsonProperty("Volumes")
        private Map<String,Map<String,String>> volumes;

        @JsonProperty("WorkingDir")
        private String workingDir;

        @JsonProperty("StopSignal")
        private String stopSignal = "SIGTERM";
    }

}
