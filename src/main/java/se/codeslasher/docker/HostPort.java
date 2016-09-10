package se.codeslasher.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by karl on 9/7/16.
 */
public class HostPort {

    public HostPort(String hostPort) {
        this.hostPort = hostPort;
    }

    @JsonProperty("HostPort")
    private String hostPort;

}
