package com.github.khazrak.jdocker.abstraction;

import com.github.khazrak.jdocker.utils.DockerImageName;

import java.util.List;
import java.util.Map;

public interface Container {
    String getId();
    List<String> getNames();
    DockerImageName getImage();
    String getImageId();
    String getCommand();
    long getCreated();
    String getState();
    String getStatus();
    List<ContainerPort> getPorts();
    Map<String,String> getLabels();
    long getSizeRw();
    long getSizeRootFs();
    HostConfig getHostConfig();
    NetworkSettings getNetworkSettings();
    List<Mount> getMounts();

}
