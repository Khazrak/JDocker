package se.codeslasher.docker;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Singular;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by karl on 9/7/16.
 */
@JsonPropertyOrder({
        "Binds",
        "ContainerIDFile",
        "LogConfig",
        "NetworkMode",
        "PortBindings",
        "RestartPolicy",
        "AutoRemove",
        "VolumeDriver",
        "VolumesFrom",
        "CapAdd",
        "CapDrop",
        "Dns",
        "DnsOptions",
        "DnsSearch",
        "ExtraHosts",
        "GroupAdd",
        "IpcMode",
        "Cgroup",
        "Links",
        "OomScoreAdj",
        "PidMode",
        "Privileged",
        "PublishAllPorts",
        "ReadonlyRootfs",
        "SecurityOpt",
        "UTSMode",
        "UsernsMode",
        "ShmSize",
        "ConsoleSize",
        "Isolation",
        "CpuShares",
        "Memory",
        "CgroupParent",
        "BlkioWeight",
        "BlkioWeightDevice",
        "BlkioDeviceReadBps",
        "BlkioDeviceWriteBps",
        "BlkioDeviceReadIOps",
        "BlkioDeviceWriteIOps",
        "CpuPeriod",
        "CpuQuota",
        "CpusetCpus",
        "CpusetMems",
        "Devices",
        "DiskQuota",
        "KernelMemory",
        "MemoryReservation",
        "MemorySwap",
        "MemorySwappiness",
        "OomKillDisable",
        "PidsLimit",
        "Ulimits",
        "CpuCount",
        "CpuPercent",
        "IOMaximumIOps",
        "IOMaximumBandwidth"
})
@Builder
@JsonDeserialize(builder = HostConfig.HostConfigBuilder.class)
public class HostConfig {

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

    @JsonProperty("CpusetCpus")
    private String cpuSetCpus;

    @JsonProperty("CpusetMems")
    private String cpuSetMems;

    @JsonProperty("IOMaximumIOps")
    private int ioMaximumIOps;

    @JsonProperty("IOMaximumBandwidth")
    private int ioMaximumBandwidth;

    @JsonProperty("BlkioWeight")
    private int blkioWeight;

    //TODO: Replace Object with other
    @JsonProperty("BlkioWeightDevice")
    private List <Object> blkioWeightDevice;

    @JsonProperty("BlkioDeviceReadBps")
    private List <Object> blkioDeviceReadBps;

    @JsonProperty("BlkioDeviceReadIOps")
    private List <Object> blkioDeviceReadIops;

    @JsonProperty("BlkioDeviceWriteBps")
    private List <Object> blkioDeviceWriteBps;

    @JsonProperty("BlkioDeviceWriteIOps")
    private List <Object> blkioDeviceWriteIops;

    @JsonProperty("MemorySwappiness")
    private int memorySwappiness;

    @JsonProperty("OomKillDisable")
    private boolean oomKillDisable;

    @JsonProperty("OomScoreAdj")
    private int oomScoreAdj;

    @JsonProperty("PidMode")
    private String pidMode;

    @JsonProperty("PidsLimit")
    private int pidsLimit;

    @Singular
    @JsonProperty("PortBindings")
    private Map<String, List<HostPort>> portBindings;

    @JsonProperty("PublishAllPorts")
    private boolean publishAllPorts;

    @JsonProperty("Privileged")
    private boolean privileged;

    @JsonProperty("ReadonlyRootfs")
    private boolean readOnlyRootfs;

    @Singular
    @JsonProperty("Dns")
    private List<String> dns;

    @Singular
    @JsonProperty("DnsOptions")
    private List<String> dnsOptions;

    @Singular("DnsSearch")
    @JsonProperty("DnsSearch")
    private List<String> dnsSearch;

    //TODO: replace with something better, list of strings?
    @JsonProperty("ExtraHosts")
    private Object extraHosts;


    @JsonProperty("VolumesFrom")
    private List<String> volumesFrom;

    @JsonProperty("CapAdd")
    private List<String> capAdd;

    @JsonProperty("CapDrop")
    private List<String> capDrop;

    @JsonProperty("GroupAdd")
    private List<String> groupAdd;

    @JsonProperty("RestartPolicy")
    private RestartPolicy restartPolicy;

    @JsonProperty("NetworkMode")
    private String networkMode;

    @Singular
    @JsonProperty("Devices")
    private List<String> devices;

    @JsonProperty("Sysctls")
    private Map<String,String> sysCtls;

    @JsonProperty("Ulimits")
    private List<Object> uLimits;

    @JsonProperty("LogConfig")
    private LogConfig logConfig;

    @JsonProperty("SecurityOpt")
    private List<String> securityOpt;

    @JsonProperty("StorageOpt")
    private Object storageOpt;

    @JsonProperty("CgroupParent")
    private String cgroupParent;

    @JsonProperty("VolumeDriver")
    private String volumeDriver;

    @JsonProperty("ShmSize")
    private int shmSize;

    @JsonPOJOBuilder(withPrefix = "")
    public static class HostConfigBuilder {
        LogConfig logConfig = new LogConfig("",new TreeMap<String,String>());
        @JsonProperty("NetworkMode")
        String networkMode = "default";
        RestartPolicy restartPolicy = new RestartPolicy("no",0);
        String volumeDriver = "";
        String pidMode = "";
        String cgroupParent = "";
        String cpuSetCpus = "";
        String cpuSetMems = "";
        int memorySwappiness = -1;

    }
}
