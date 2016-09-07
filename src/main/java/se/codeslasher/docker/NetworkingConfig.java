package se.codeslasher.docker;

import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by karl on 9/7/16.
 */
public class NetworkingConfig {

    public NetworkingConfig() {
        endpointConfig = new TreeMap<>();
    }

    @SerializedName(value = "EndpointsConfig")
    private Map<String,NetworkSubConfig> endpointConfig;

    public void addEndpointConfig(String networkName, NetworkSubConfig config) {
        endpointConfig.put(networkName,config);
    }
}
