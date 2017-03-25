package com.github.khazrak.jdocker.abstraction;

import java.util.Map;

public interface NetworkingConfig {
    Map<String,NetworkSubConfig> getEndpointConfig();
}
