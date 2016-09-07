package se.codeslasher.docker;

import com.google.gson.annotations.SerializedName;

/**
 * Created by karl on 9/7/16.
 */
public class HostPort {

    public HostPort(String hostPort) {
        this.hostPort = hostPort;
    }

    @SerializedName(value = "HostPort")
    private String hostPort;

}
