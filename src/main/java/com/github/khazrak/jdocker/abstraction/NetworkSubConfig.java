package com.github.khazrak.jdocker.abstraction;

import java.util.List;

public interface NetworkSubConfig {

    IPAMConfig getIpamConfig();
    List<String> getLinks();
    List<String> getAliases();

}
