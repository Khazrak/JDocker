package com.github.khazrak.jdocker.abstraction;

import java.util.Map;

public interface Volume {

    String getName();

    String getDriver();

    String getMountPoint();

    Map<String, String> getLabels();

    String getScope();

    Map<String, String> getStatus();

    Map<String, String> getOptions();

}
