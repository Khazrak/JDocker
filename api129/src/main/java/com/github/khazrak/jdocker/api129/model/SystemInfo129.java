package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.Plugins;
import com.github.khazrak.jdocker.abstraction.RegistryConfig;
import com.github.khazrak.jdocker.abstraction.SwarmInfo;
import com.github.khazrak.jdocker.abstraction.SystemInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = SystemInfo129.SystemInfo129Builder.class)
public class SystemInfo129 implements SystemInfo {

    private Map<String, String> initCommit;
    private Map<String, String> containerdCommit;
    private Map<String, String> runcCommit;
    private String initBinary;
    private String isolation;
    private String architecture;
    private String clusterStore;
    private String cgroupDriver;
    private int containers;
    private int containersRunning;
    private int containersStopped;
    private int containersPaused;
    private boolean cpuCfsPeriod;
    private boolean cpuCfsQuota;
    private boolean debug;
    private String dockerRootDir;
    private String driver;
    private List<List<String>> driverStatus;
    private boolean experimentalBuild;
    private String httpProxy;
    private String httpsProxy;
    private String id;
    private boolean ipv4Forwarding;
    private int images;
    private String indexServerAddress;
    private String initPath;
    private String initSha1;
    private String kernelVersion;
    private boolean kernelMemory;
    private List<String> labels;
    private long memTotal;
    private boolean memoryLimit;
    private boolean bridgeNfIptables;
    private boolean bridgeNfIp6tables;
    private boolean cpuShares;
    private boolean cpuSet;
    private int ncpu;
    private int enventsListener;
    private int nfd;
    private int ngoRoutines;
    private String name;
    private String noProxy;
    private boolean oomKillDisable;
    private String osType;
    private String operatingSystem;
    private Plugins plugins;
    private RegistryConfig registryConfig;
    private List<String> securityOptions;
    private String serverVersion;
    private boolean swapLimit;
    private List<List<String>> systemStatus;
    private String executionDriver;
    private String systemTime;
    private String loggingDriver;
    private String clusterAdvertise;
    private Map<String, Map<String, String>> runtimes;
    private String defaultRuntime;
    private SwarmInfo swarm;
    private boolean liveRestoreEnabled;


    @JsonPOJOBuilder(withPrefix = "")
    public static class SystemInfo129Builder {

        @JsonProperty("ContainerdCommit")
        private Map<String, String> containerdCommit;

        @JsonProperty("RuncCommit")
        private Map<String, String> runcCommit;

        @JsonProperty("InitCommit")
        private Map<String, String> initCommit;

        @JsonProperty("InitBinary")
        private String initBinary;

        @JsonProperty("Isolation")
        private String isolation;

        @JsonProperty("Architecture")
        private String architecture;

        @JsonProperty("ClusterStore")
        private String clusterStore;

        @JsonProperty("CgroupDriver")
        private String cgroupDriver;

        @JsonProperty("Containers")
        private int containers;

        @JsonProperty("ContainersRunning")
        private int containersRunning;

        @JsonProperty("ContainersStopped")
        private int containersStopped;

        @JsonProperty("ContainersPaused")
        private int containersPaused;

        @JsonProperty("CpuCfsPeriod")
        private boolean cpuCfsPeriod;

        @JsonProperty("CpuCfsQuota")
        private boolean cpuCfsQuota;

        @JsonProperty("Debug")
        private boolean debug;

        @JsonProperty("DockerRootDir")
        private String dockerRootDir;

        @JsonProperty("Driver")
        private String driver;

        @JsonProperty("DriverStatus")
        private List<List<String>> driverStatus;

        @JsonProperty("ExperimentalBuild")
        private boolean experimentalBuild;

        @JsonProperty("HttpProxy")
        private String httpProxy;

        @JsonProperty("HttpsProxy")
        private String httpsProxy;

        @JsonProperty("ID")
        private String id;

        @JsonProperty("IPv4Forwarding")
        private boolean ipv4Forwarding;

        @JsonProperty("Images")
        private int images;

        @JsonProperty("IndexServerAddress")
        private String indexServerAddress;

        @JsonProperty("InitPath")
        private String initPath;

        @JsonProperty("InitSha1")
        private String initSha1;

        @JsonProperty("KernelVersion")
        private String kernelVersion;

        @JsonProperty("KernelMemory")
        private boolean kernelMemory;

        @JsonProperty("Labels")
        private List<String> labels;

        @JsonProperty("MemTotal")
        private long memTotal;

        @JsonProperty("MemoryLimit")
        private boolean memoryLimit;

        @JsonProperty("BridgeNfIptables")
        private boolean bridgeNfIptables;

        @JsonProperty("BridgeNfIp6tables")
        private boolean bridgeNfIp6tables;

        @JsonProperty("CPUShares")
        private boolean cpuShares;

        @JsonProperty("CPUSet")
        private boolean cpuSet;

        @JsonProperty("NCPU")
        private int ncpu;

        @JsonProperty("NEventsListener")
        private int enventsListener;

        @JsonProperty("NFd")
        private int nfd;

        @JsonProperty("NGoroutines")
        private int ngoRoutines;

        @JsonProperty("Name")
        private String name;

        @JsonProperty("NoProxy")
        private String noProxy;

        @JsonProperty("OomKillDisable")
        private boolean oomKillDisable;

        @JsonProperty("OSType")
        private String osType;

        @JsonProperty("OperatingSystem")
        private String operatingSystem;

        @JsonDeserialize(as = Plugins129.class)
        @JsonProperty("Plugins")
        private Plugins plugins;

        @JsonDeserialize(as = RegistryConfig129.class)
        @JsonProperty("RegistryConfig")
        private RegistryConfig registryConfig;

        @JsonProperty("SecurityOptions")
        private List<String> securityOptions;

        @JsonProperty("ServerVersion")
        private String serverVersion;

        @JsonProperty("SwapLimit")
        private boolean swapLimit;

        @JsonProperty("SystemStatus")
        private List<List<String>> systemStatus;

        @JsonProperty("ExecutionDriver")
        private String executionDriver;

        @JsonProperty("SystemTime")
        private String systemTime;

        @JsonProperty("LoggingDriver")
        private String loggingDriver;

        @JsonProperty("ClusterAdvertise")
        private String clusterAdvertise;

        @JsonProperty("Runtimes")
        private Map<String, Map<String, String>> runtimes;

        @JsonProperty("DefaultRuntime")
        private String defaultRuntime;

        @JsonDeserialize(as = SwarmInfo129.class)
        @JsonProperty("Swarm")
        private SwarmInfo swarm;

        @JsonProperty("LiveRestoreEnabled")
        private boolean liveRestoreEnabled;

    }

}
