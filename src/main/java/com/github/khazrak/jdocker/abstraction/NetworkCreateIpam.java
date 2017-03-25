package com.github.khazrak.jdocker.abstraction;

import java.util.List;
import java.util.Map;

public interface NetworkCreateIpam {

    String getDriver();
    List<NetworkCreateIpamConfig> getConfig();
    Map<String,String> getOptions();

}
