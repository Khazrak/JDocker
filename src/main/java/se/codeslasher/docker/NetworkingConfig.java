package se.codeslasher.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by karl on 9/7/16.
 */
public class NetworkingConfig {

    public NetworkingConfig() {
        endpointConfig = new TreeMap<>();
    }

    @JsonProperty("EndpointsConfig")
    private Map<String,NetworkSubConfig> endpointConfig;

    public void addEndpointConfig(String networkName, NetworkSubConfig config) {
        endpointConfig.put(networkName,config);
    }
}
