package com.github.khazrak.jdocker.abstraction;

import java.util.List;
import java.util.Map;

public interface NetworkIPAM {

    String getDriver();
    List<NetworkIPAMConfig> getConfig();
    Map<String, String> getOptions();

}
