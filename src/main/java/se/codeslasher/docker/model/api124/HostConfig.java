/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    @JsonProperty("AutoRemove")
    private boolean autoRemove;

    @JsonProperty("IpcMode")
    private String ipcMode;

    @JsonProperty("Cgroup")
    private String cgroup;

    @JsonProperty("UTSMode")
    private String utsMode;

    @JsonProperty("UsernsMode")
    private String usernsMode;

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

    @JsonProperty("RestartPolicy")
    private RestartPolicy restartPolicy;

    @JsonProperty("NetworkMode")
    private String networkMode;

    @JsonProperty("Devices")
    private List<String> devices;

    @JsonProperty("Sysctls")
    private Map<String,String> sysCtls;

    @JsonProperty("Ulimits")
    private List<Ulimit> uLimits;

    @JsonProperty("LogConfig")
    private LogConfig logConfig;

    @JsonProperty("SecurityOpt")
    private List<String> securityOpt;

    @JsonProperty("StorageOpt")
    private List<Map<String, String>> storageOpt;

    @JsonProperty("CgroupParent")
    private String cgroupParent;

    @JsonProperty("VolumeDriver")
    private String volumeDriver;

    @JsonProperty("ShmSize")
    private int shmSize;

    @JsonPOJOBuilder(withPrefix = "")
    public static class HostConfigBuilder {

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

        @JsonProperty("LogConfig")
        private LogConfig logConfig = new LogConfig("",new TreeMap<String,String>());

        @JsonProperty("NetworkMode")
        private String networkMode = "default";

        @JsonProperty("RestartPolicy")
        private RestartPolicy restartPolicy = new RestartPolicy("no",0);

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

    }
}
