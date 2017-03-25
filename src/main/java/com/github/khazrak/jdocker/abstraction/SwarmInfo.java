package com.github.khazrak.jdocker.abstraction;

import java.util.List;

public interface SwarmInfo {

    String getNodeId();
    String getNodeAddr();
    String getLocalNodeState();
    boolean isControlAvailable();
    String getError();
    List<String> getRemoteManagers();
    int getNodes();
    int getManagers();
    ClusterInfo getCluster();

}
