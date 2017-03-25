package com.github.khazrak.jdocker.abstraction;

import java.util.Map;

public interface ClusterInfo {

    String getId();
    Map<String, String> getVersion();
    String getCreatedAt();
    String getUpdatedAt();
    ClusterSpec getSpec();

}
