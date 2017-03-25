package com.github.khazrak.jdocker.abstraction;

import java.util.List;
import java.util.Map;

public interface NetworkSettings {

    String getBridge();
    String getSandboxId();
    boolean isHairPindMode();
    String getLinkLocalIpv6Address();
    int getLinkLocalIpv6PrefixLen();
    Map<String, List<HostPort>> getPorts();
    String getSandboxKey();
    Object getSecondaryIpAddresses();
    Object getSecondaryIpv6Addresses();
    String getEndpointId();
    String getGateway();
    String getGlobalIpv6Address();
    int getGlobalIpv6PrefixLen();
    String getIpAddress();
    int getIpPrefixLen();
    String getIpv6Gateway();
    String getMacAddress();
    Map<String, NetworkInterface> getNetworks();

}
