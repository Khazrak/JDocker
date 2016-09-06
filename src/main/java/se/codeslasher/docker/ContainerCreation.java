package se.codeslasher.docker;

import com.google.gson.annotations.SerializedName;
import com.sun.org.apache.xpath.internal.operations.String;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Singular;

/**
 * Created by karl on 9/6/16.
 */
@Builder
public class ContainerCreation {

    private String name;

    @SerializedName(value = "Hostname")
    private String hostname;

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
    private List<String> env;

    @Singular
    @SerializedName(value = "Cmd")
    private List<String> cmd;

    @SerializedName(value = "Entrypoint")
    private String entrypoint;

    @SerializedName(value = "Image")
    private String image;

    @Singular
    @SerializedName(value = "Labels")
    private Map<String,String> labels;


    

}
