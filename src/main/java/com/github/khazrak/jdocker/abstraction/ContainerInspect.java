package com.github.khazrak.jdocker.abstraction;

import java.util.List;

public interface ContainerInspect {

    long getSizeRw();

    long getSizeRootFs();

    GraphDriver getGraphDriver();

    String getAppArmorProfile();

    List<String> getArgs();

    ContainerInspectConfig getConfig();

    String getCreated();

    String getDriver();

    List<String> getExecIds();

    HostConfig getHostConfig();

    String getHostNamePath();

    String getHostsPath();

    String getLogPath();

    String getId();

    String getImage();

    String getMountLabel();

    String getName();

    NetworkSettings getNetworkSettings();

    String getPath();

    String getProcessLabel();

    String getResolveConfPath();

    int getRestartCount();

    State getState();

    List<Mount> getMounts();

}
