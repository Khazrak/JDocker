package com.github.khazrak.jdocker.abstraction;

import java.util.Map;

public interface LogConfig {

    String getType();

    //TODO: replace with something better
    Map<String, String> getConfig();

}
