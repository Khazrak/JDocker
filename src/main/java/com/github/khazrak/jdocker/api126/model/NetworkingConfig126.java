package com.github.khazrak.jdocker.api126.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.khazrak.jdocker.abstraction.NetworkSubConfig;
import com.github.khazrak.jdocker.abstraction.NetworkingConfig;
import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

@Getter
public class NetworkingConfig126 implements NetworkingConfig {

    public NetworkingConfig126() {
        endpointConfig = new TreeMap<>();
    }

    @JsonProperty("EndpointsConfig")
    private Map<String,NetworkSubConfig> endpointConfig;

    public void addEndpointConfig(String networkName, NetworkSubConfig config) {
        endpointConfig.put(networkName,config);
    }

}
