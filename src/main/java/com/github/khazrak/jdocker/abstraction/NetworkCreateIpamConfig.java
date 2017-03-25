package com.github.khazrak.jdocker.abstraction;

public interface NetworkCreateIpamConfig {

    String getSubnet();
    String getIpRange();
    String getGateway();

}
