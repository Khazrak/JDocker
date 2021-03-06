package com.github.khazrak.jdocker.abstraction;

import java.util.List;
import java.util.Map;

public interface HostConfig {

    boolean isAutoRemove();

    String getIpcMode();

    String getCgroup();

    String getUtsMode();

    String getUsernsMode();

    String getRuntime();

    List<Integer> getConsoleSize();

    String getIsolation();

    int getDiskQuota();

    int getCpuCount();

    long getNanoCpus();

    String getContainerIdFile();

    List<String> getBinds();

    List<String> getLinks();

    int getMemory();

    int getMemorySwap();

    int getMemoryReservation();

    int getKernelMemory();

    int getCpuPercent();

    int getCpuShares();

    int getCpuPeriod();

    int getCpuQuota();

    String getCpuSetCpus();

    String getCpuSetMems();

    int getIoMaximumIOps();

    int getIoMaximumBandwidth();

    int getBlkioWeight();

    List<Map<String, String>> getBlkioWeightDevice();

    List<Map<String, String>> getBlkioDeviceReadBps();

    List<Map<String, String>> getBlkioDeviceReadIops();

    List<Map<String, String>> getBlkioDeviceWriteBps();

    List<Map<String, String>> getBlkioDeviceWriteIops();

    int getMemorySwappiness();

    boolean isOomKillDisable();

    int getOomScoreAdj();

    String getPidMode();

    int getPidsLimit();

    Map<String, List<HostPort>> getPortBindings();

    boolean isPublishAllPorts();

    boolean isPrivileged();

    boolean isReadOnlyRootfs();

    List<String> getDns();

    List<String> getDnsOptions();

    List<String> getDnsSearch();

    List<String> getExtraHosts();

    List<String> getVolumesFrom();

    List<String> getCapAdd();

    List<String> getCapDrop();

    List<String> getGroupAdd();

    RestartPolicy getRestartPolicy();

    String getNetworkMode();

    List<String> getDevices();

    /**
     * @since Docker api 1.29
     * @return
     */
    List<String> getDeviceCgroupRules();

    Map<String, String> getSysCtls();

    List<Ulimit> getULimits();

    LogConfig getLogConfig();

    List<String> getSecurityOpt();

    List<Map<String, String>> getStorageOpt();

    String getCgroupParent();

    String getVolumeDriver();

    int getShmSize();

    long getCpuRealtimePeriod();

    long getCpuRealtimeRuntime();

}
