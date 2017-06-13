package com.github.khazrak.jdocker.abstraction;

public interface NetworkContainer {

    String getEndpointId();

    String getMacAddress();

    String getIpv44Address();

    String getIpv64Address();

}
