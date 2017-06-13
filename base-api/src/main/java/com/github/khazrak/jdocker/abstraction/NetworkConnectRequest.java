package com.github.khazrak.jdocker.abstraction;

public interface NetworkConnectRequest {

    String getNetworkName();

    String getContainer();

    NetworkConnectEndpointConfig getEndpointConfig();

}
