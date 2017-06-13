package com.github.khazrak.jdocker.api129.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.ContainerUpdateRequest;
import com.github.khazrak.jdocker.abstraction.RestartPolicy;
import com.github.khazrak.jdocker.api129.model.RestartPolicy129;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContainerUpdateRequest129 implements ContainerUpdateRequest {

    @JsonProperty("BlkioWeight")
    private long blkioWeight;

    @JsonProperty("CpuShares")
    private int cpuShares;

    @JsonProperty("CpuPeriod")
    private long cpuPeriod;

    @JsonProperty("CpuQuota")
    private long cpuQuota;

    @JsonProperty("CpusetCpus")
    private String cpuSetCpus;

    @JsonProperty("CpusetMems")
    private String cpuSetMems;

    @JsonProperty("Memory")
    private long memory;

    @JsonProperty("MemorySwap")
    private long memorySwap;
    @JsonProperty("MemoryReservation")
    private long memoryReservation;

    @JsonProperty("KernelMemory")
    private long kernelMemory;

    @JsonProperty("RestartPolicy")
    private RestartPolicy restartPolicy;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ContainerUpdateRequest129Builder {

        @JsonProperty("RestartPolicy")
        private RestartPolicy restartPolicy = RestartPolicy129.builder().build();

    }

}
