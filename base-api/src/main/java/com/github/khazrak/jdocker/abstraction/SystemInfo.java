package com.github.khazrak.jdocker.abstraction;

import java.util.List;
import java.util.Map;

public interface SystemInfo {

    Map<String, String> getInitCommit();

    Map<String, String> getContainerdCommit();

    Map<String, String> getRuncCommit();

    String getInitBinary();

    String getIsolation();

    String getArchitecture();

    String getClusterStore();

    String getCgroupDriver();

    int getContainers();

    int getContainersRunning();

    int getContainersStopped();

    int getContainersPaused();

    boolean isCpuCfsPeriod();

    boolean isCpuCfsQuota();

    boolean isDebug();

    String getDockerRootDir();

    String getDriver();

    List<List<String>> getDriverStatus();

    boolean isExperimentalBuild();

    String getHttpProxy();

    String getHttpsProxy();

    String getId();

    boolean isIpv4Forwarding();

    int getImages();

    String getIndexServerAddress();

    String getInitPath();

    String getInitSha1();

    String getKernelVersion();

    boolean isKernelMemory();

    List<String> getLabels();

    long getMemTotal();

    boolean isMemoryLimit();

    boolean isBridgeNfIptables();

    boolean isBridgeNfIp6tables();

    boolean isCpuShares();

    boolean isCpuSet();

    int getNcpu();

    int getEnventsListener();

    int getNfd();

    int getNgoRoutines();

    String getName();

    String getNoProxy();

    boolean isOomKillDisable();

    String getOsType();

    String getOperatingSystem();

    Plugins getPlugins();

    RegistryConfig getRegistryConfig();

    List<String> getSecurityOptions();

    String getServerVersion();

    boolean isSwapLimit();

    List<List<String>> getSystemStatus();

    String getExecutionDriver();

    String getSystemTime();

    String getLoggingDriver();

    String getClusterAdvertise();

    Map<String, Map<String, String>> getRuntimes();

    String getDefaultRuntime();

    SwarmInfo getSwarm();

    boolean isLiveRestoreEnabled();

}
