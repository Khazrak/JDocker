package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.*;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Builder
@JsonDeserialize(builder = HostConfig126.HostConfig126Builder.class)
public class HostConfig126 implements HostConfig {

    private boolean autoRemove;
    private String ipcMode;
    private String cgroup;
    private String utsMode;
    private String usernsMode;
    private String runtime;
    private List<Integer> consoleSize;
    private String isolation;
    private int diskQuota;
    private int cpuCount;
    private long nanoCpus;
    private String containerIdFile;
    private List<String> binds;
    private List<String> links;
    private int memory;
    private int memorySwap;
    private int memoryReservation;
    private int kernelMemory;
    private int cpuPercent;
    private int cpuShares;
    private int cpuPeriod;
    private int cpuQuota;
    private String cpuSetCpus;
    private String cpuSetMems;
    private int ioMaximumIOps;
    private int ioMaximumBandwidth;
    private int blkioWeight;
    private List <Map<String,String>> blkioWeightDevice;
    private List <Map<String,String>> blkioDeviceReadBps;
    private List <Map<String,String>> blkioDeviceReadIops;
    private List <Map<String,String>> blkioDeviceWriteBps;
    private List <Map<String,String>> blkioDeviceWriteIops;
    private int memorySwappiness;
    private boolean oomKillDisable;
    private int oomScoreAdj;
    private String pidMode;
    private int pidsLimit;
    private Map<String, List<HostPort>> portBindings;
    private boolean publishAllPorts;
    private boolean privileged;
    private boolean readOnlyRootfs;
    private List<String> dns;
    private List<String> dnsOptions;
    private List<String> dnsSearch;
    private List<String> extraHosts;
    private List<String> volumesFrom;
    private List<String> capAdd;
    private List<String> capDrop;
    private List<String> groupAdd;
    private RestartPolicy restartPolicy;
    private String networkMode;
    private List<String> devices;
    private Map<String,String> sysCtls;
    private List<Ulimit> uLimits;
    private LogConfig logConfig;
    private List<String> securityOpt;
    private List<Map<String, String>> storageOpt;
    private String cgroupParent;
    private String volumeDriver;
    private int shmSize;
    private long cpuRealtimePeriod;
    private long cpuRealtimeRuntime;

    @JsonPOJOBuilder(withPrefix = "")
    public static class HostConfig126Builder {

        @JsonProperty("AutoRemove")
        private boolean autoRemove;

        @JsonProperty("IpcMode")
        private String ipcMode;

        @JsonProperty("Cgroup")
        private String cgroup;

        @JsonProperty("UsernsMode")
        private String usernsMode;

        @JsonProperty("UTSMode")
        private String utsMode;

        @JsonProperty("Runtime")
        private String runtime;

        @JsonProperty("ConsoleSize")
        private List<Integer> consoleSize;

        @JsonProperty("Isolation")
        private String isolation;

        @JsonProperty("DiskQuota")
        private int diskQuota;

        @JsonProperty("CpuCount")
        private int cpuCount;

        @JsonProperty("NanoCpus")
        private long nanoCpus;

        @JsonProperty("ContainerIDFile")
        private String containerIdFile;

        @JsonProperty("Binds")
        private List<String> binds;

        @JsonProperty("Links")
        private List<String> links;

        @JsonProperty("Memory")
        private int memory;

        @JsonProperty("MemorySwap")
        private int memorySwap;

        @JsonProperty("MemoryReservation")
        private int memoryReservation;

        @JsonProperty("KernelMemory")
        private int kernelMemory;

        @JsonProperty("CpuPercent")
        private int cpuPercent;

        @JsonProperty("CpuShares")
        private int cpuShares;

        @JsonProperty("CpuPeriod")
        private int cpuPeriod;

        @JsonProperty("CpuQuota")
        private int cpuQuota;

        @JsonProperty("IOMaximumIOps")
        private int ioMaximumIOps;

        @JsonProperty("IOMaximumBandwidth")
        private int ioMaximumBandwidth;

        @JsonProperty("BlkioWeight")
        private int blkioWeight;

        @JsonProperty("BlkioWeightDevice")
        private List <Map<String,String>> blkioWeightDevice;

        @JsonProperty("BlkioDeviceReadBps")
        private List <Map<String,String>> blkioDeviceReadBps;

        @JsonProperty("BlkioDeviceReadIOps")
        private List <Map<String,String>> blkioDeviceReadIops;

        @JsonProperty("BlkioDeviceWriteBps")
        private List <Map<String,String>> blkioDeviceWriteBps;

        @JsonProperty("BlkioDeviceWriteIOps")
        private List <Map<String,String>> blkioDeviceWriteIops;

        @JsonProperty("OomKillDisable")
        private boolean oomKillDisable;

        @JsonProperty("OomScoreAdj")
        private int oomScoreAdj;

        @JsonProperty("PidsLimit")
        private int pidsLimit;

        @JsonProperty("PortBindings")
        private Map<String, List<HostPort>> portBindings;

        @JsonProperty("PublishAllPorts")
        private boolean publishAllPorts;

        @JsonProperty("Privileged")
        private boolean privileged;

        @JsonProperty("ReadonlyRootfs")
        private boolean readOnlyRootfs;

        @JsonProperty("Dns")
        private List<String> dns;

        @JsonProperty("DnsOptions")
        private List<String> dnsOptions;

        @JsonProperty("DnsSearch")
        private List<String> dnsSearch;

        @JsonProperty("ExtraHosts")
        private List<String> extraHosts;

        @JsonProperty("VolumesFrom")
        private List<String> volumesFrom;

        @JsonProperty("CapAdd")
        private List<String> capAdd;

        @JsonProperty("CapDrop")
        private List<String> capDrop;

        @JsonProperty("GroupAdd")
        private List<String> groupAdd;


        @JsonProperty("Devices")
        private List<String> devices;

        @JsonProperty("Sysctls")
        private Map<String,String> sysCtls;

        @JsonProperty("Ulimits")
        private List<Ulimit> uLimits;

        @JsonProperty("SecurityOpt")
        private List<String> securityOpt;

        @JsonProperty("StorageOpt")
        private List<Map<String, String>> storageOpt;

        @JsonProperty("ShmSize")
        private int shmSize;

        @JsonDeserialize(as = LogConfig126.class)
        @JsonProperty("LogConfig")
        private LogConfig logConfig = new LogConfig126("", new TreeMap<String,String>());

        @JsonProperty("NetworkMode")
        private String networkMode = "default";

        @JsonDeserialize(as = RestartPolicy126.class)
        @JsonProperty("RestartPolicy")
        private RestartPolicy restartPolicy = new RestartPolicy126("no",0);

        @JsonProperty("VolumeDriver")
        private String volumeDriver = "";

        @JsonProperty("PidMode")
        private String pidMode = "";

        @JsonProperty("CgroupParent")
        private String cgroupParent = "";

        @JsonProperty("CpusetCpus")
        private String cpuSetCpus = "";

        @JsonProperty("CpusetMems")
        private String cpuSetMems = "";

        @JsonProperty("MemorySwappiness")
        private int memorySwappiness = -1;

        @JsonProperty("CpuRealtimePeriod")
        private long cpuRealtimePeriod;

        @JsonProperty("CpuRealtimeRuntime")
        private long cpuRealtimeRuntime;
    }

}
