package se.codeslasher.docker.model.api124.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import se.codeslasher.docker.model.api124.RestartPolicy;

/**
 * Created by karl on 9/22/16.
 */
@Getter
@Builder
public class ContainerUpdateRequest {

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
    public static class ContainerUpdateRequestBuilder {

        @JsonProperty("RestartPolicy")
        private RestartPolicy restartPolicy = RestartPolicy.builder().build();

    }

}
