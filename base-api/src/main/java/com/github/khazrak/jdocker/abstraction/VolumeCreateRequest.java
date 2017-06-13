package com.github.khazrak.jdocker.abstraction;

import java.util.Map;

public interface VolumeCreateRequest {

    String getName();

    String getDriver();

    Map<String, String> getLabels();

    Map<String, String> getDriverOpts();

}
