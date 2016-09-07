package se.codeslasher.docker;

import com.google.gson.annotations.SerializedName;

/**
 * Created by karl on 9/7/16.
 */

public class RestartPolicy {

    public RestartPolicy(String name, int maximumRetryCount)
    {
        this.name = name;
        this.maximumRetryCount = maximumRetryCount;
    }

    @SerializedName(value = "Name")
    private String name;

    @SerializedName(value = "MaximumRetryCount")
    private int maximumRetryCount;

}
