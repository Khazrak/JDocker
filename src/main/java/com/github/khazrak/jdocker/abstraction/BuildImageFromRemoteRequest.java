package com.github.khazrak.jdocker.abstraction;

import com.github.khazrak.jdocker.utils.DockerImageName;

import java.util.Map;

public interface BuildImageFromRemoteRequest {

    Map<String, AuthConfig> getAuthConfigs();
    String getDockerFilePath();
    String getRemoteUrl();
    DockerImageName getTag();
    boolean isQuiet();
    boolean isNocache();
    boolean isPull();
    boolean isRemoveIntermediateContainers();
    boolean isForceRm();
    long getMemory();
    long getMemswap();
    int getCpushares();
    String getCpusetcpus();
    long getCpuperiod();
    long getCpuquota();
    long getShmsize();
    Map<String, String> getBuildargs();
    Map<String, String> getLabels();
    Map<String, String> getQueries();

}
