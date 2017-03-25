package com.github.khazrak.jdocker.abstraction;

import java.util.List;

public interface NetworkInterface {

    IPAMConfig getIpamConfig();
    List<String> getLinks();
    List<String> getAliases();
    String getNetworkId();
    String getEndpointId();
    String getGateway();
    String getIpAddress();
    int getIpPrefixLen();
    String getIpv6GateWay();
    String getGlobalIpv6Address();
    int getGlobalIpv6PrefixLen();
    String getMacAddress();

}
