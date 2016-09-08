package se.codeslasher.docker;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Singular;
import okhttp3.MediaType;

/**
 * Created by karl on 9/6/16.
 */
@Builder
public class ContainerCreation {

    public transient static final String PATH="/v1.24/containers/create";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private transient String name;

    @SerializedName(value = "Hostname")
    private String hostname = new String("");

    @SerializedName(value = "Domainname")
    private String Domainname;

    @SerializedName(value = "User")
    private String user;

    @SerializedName(value = "AttachStdin")
    private boolean attachStdin;

    @SerializedName(value = "AttachStdout")
    private boolean attachStdout;

    @SerializedName(value = "AttachStderr")
    private boolean attachStderr;

    @SerializedName(value = "Tty")
    private boolean tty;

    @SerializedName(value = "OpenStdin")
    private boolean openStdin;

    @SerializedName(value = "StdinOnce")
    private boolean stdinOnce;

    @Singular
    @SerializedName(value = "Env")
    private List<String> environmentVariables;

    @Singular
    @SerializedName(value = "Cmd")
    private List<String> commands;

    @SerializedName(value = "Entrypoint")
    private String entrypoint;

    @SerializedName(value = "Image")
    private String image;

    @Singular
    @SerializedName(value = "Labels")
    private Map<String,String> labels;

    //volumes map

    @SerializedName(value = "WorkingDir")
    private String workingDir;

    @SerializedName(value = "NetworkDisabled")
    private boolean networkDisabled;

    @SerializedName(value = "MacAddress")
    private String macAddress;

    @Singular
    @SerializedName(value = "ExposedPorts")
    private Map<String,Object> exposedPorts;

    @SerializedName(value = "StopSignal")
    private String stopSignal;

    @SerializedName(value = "HostConfig")
    private HostConfig hostConfig;

    @SerializedName(value = "NetworkingConfig")
    private NetworkingConfig networkingConfig;

}
