package se.codeslasher.docker;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Singular;

import java.util.List;
import java.util.Map;

/**
 * Created by karl on 9/7/16.
 */
@Builder
public class HostConfig {

    @Singular
    @SerializedName(value = "Binds")
    private List<String> binds;

    @Singular
    @SerializedName(value = "Links")
    private List<String> links;

    @SerializedName(value = "Memory")
    private int memory;

    @SerializedName(value = "MemorySwap")
    private int memorySwap;

    @SerializedName(value = "MemoryReservation")
    private int memoryReservation;

    @SerializedName(value = "KernelMemory")
    private int kernelMemory;

    @SerializedName(value = "CpuPercent")
    private int cpuPercent;

    @SerializedName(value = "CpuShares")
    private int cpuShares;

    @SerializedName(value = "CpuPeriod")
    private int cpuPeriod;

    @SerializedName(value = "CpuQuota")
    private int cpuQuota;

    @SerializedName(value = "CpusetCpus")
    private String cpuSetCpus;

    @SerializedName(value = "CpusetMems")
    private String cpuSetMems;

    @SerializedName(value = "MaximumIOps")
    private int maximumIops;

    @SerializedName(value = "MaximumIOBps")
    private int maximumIobps;

    @SerializedName(value = "BlkioWeight")
    private int blkioWeight;

    //TODO: Replace Object with other
    @Singular("BlkioWeightDevice")
    @SerializedName(value = "BlkioWeightDevice")
    private List <Object> blkioWeightDevice;

    @Singular
    @SerializedName(value = "BlkioDeviceReadBps")
    private List <Object> blkioDeviceReadBps;

    @Singular
    @SerializedName(value = "BlkioDeviceReadIOps")
    private List <Object> blkioDeviceReadIops;

    @Singular
    @SerializedName(value = "BlkioDeviceWriteBps")
    private List <Object> blkioDeviceWriteBps;

    @Singular
    @SerializedName(value = "BlkioDeviceWriteIOps")
    private List <Object> blkioDeviceWriteIops;


    @SerializedName(value = "MemorySwappiness")
    private int memorySwappiness;

    @SerializedName(value = "OomKillDisable")
    private boolean oomKillDisable;

    @SerializedName(value = "OomScoreAdj")
    private int oomScoreAdj;

    @SerializedName(value = "PidMode")
    private String pidMode;

    @SerializedName(value = "PidsLimit")
    private int pidsLimit;

    @Singular
    @SerializedName(value = "PortBindings")
    private Map<String, List<HostPort>> portBindings;

    @SerializedName(value = "PublishAllPorts")
    private boolean publishAllPorts;

    @SerializedName(value = "Privileged")
    private boolean privileged;

    @SerializedName(value = "ReadonlyRootfs")
    private boolean readOnlyRootfs;

    @Singular("Dns")
    @SerializedName(value = "Dns")
    private List<String> dns;

    @Singular("DnsOptions")
    @SerializedName(value = "DnsOptions")
    private List<String> dnsOptions;

    @Singular("DnsSearch")
    @SerializedName(value = "DnsSearch")
    private List<String> dnsSearch;

    //TODO: replace with something better, list of strings?
    @SerializedName(value = "ExtraHosts")
    private Object extraHosts;

    @Singular("VolumesFrom")
    @SerializedName(value = "VolumesFrom")
    private List<String> volumesFrom;

    @Singular("CapAdd")
    @SerializedName(value = "CapAdd")
    private List<String> capAdd;

    @Singular("CapDrop")
    @SerializedName(value = "CapDrop")
    private List<String> capDrop;

    @Singular("GroupAdd")
    @SerializedName(value = "GroupAdd")
    private List<String> groupAdd;

    @SerializedName(value = "RestartPolicy")
    private RestartPolicy restartPolicy;

    @SerializedName(value = "NetworkMode")
    private String networkMode;

    @SerializedName(value = "Devices")
    private List<String> devices;

    @Singular
    @SerializedName(value = "Sysctls")
    private Map<String,String> sysCtls;

    @SerializedName(value = "Ulimits")
    private List<Object> uLimits;

    @SerializedName(value = "LogConfig")
    private LogConfig logConfig;

    @Singular("SecurityOpt")
    @SerializedName(value = "SecurityOpt")
    private List<String> securityOpt;

    @SerializedName(value = "StorageOpt")
    private Object storageOpt;

    @SerializedName(value = "CgroupParent")
    private String cgroupParent;

    @SerializedName(value = "VolumeDriver")
    private String volumeDriver;

    @SerializedName(value = "ShmSize")
    private int shmSize;


}
