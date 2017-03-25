package com.github.khazrak.jdocker.abstraction;

import java.util.List;

public interface IPAMConfig {

    String getIpv4Address();
    String getIpv6Address();
    List<String> getLinkLocalIps();

}
