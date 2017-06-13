package com.github.khazrak.jdocker.abstraction;

import java.util.Map;

public interface LogConfig {

    String getType();
    Map<String, String> getConfig();

}
