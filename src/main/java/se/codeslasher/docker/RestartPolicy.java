package se.codeslasher.docker;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by karl on 9/7/16.
 */

public class RestartPolicy {

    public RestartPolicy(String name, int maximumRetryCount)
    {
        this.name = name;
        this.maximumRetryCount = maximumRetryCount;
    }

    @JsonProperty("Name")
    private String name;

    @JsonProperty("MaximumRetryCount")
    private int maximumRetryCount;

}
