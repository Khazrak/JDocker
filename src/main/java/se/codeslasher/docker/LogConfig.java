package se.codeslasher.docker;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.ObjectConstructor;

/**
 * Created by karl on 9/7/16.
 */
public class LogConfig {

    public LogConfig (String type, ObjectConstructor config) {
        this.type = type;
        this.config = config;
    }

    @SerializedName(value = "Type")
    private String type;

    //TODO: replace with something better
    @SerializedName(value = "Config")
    private Object config;

}
