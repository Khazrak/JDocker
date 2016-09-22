package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import se.codeslasher.docker.model.api124.stats.BlkIOStats;
import se.codeslasher.docker.model.api124.stats.CpuStats;
import se.codeslasher.docker.model.api124.stats.MemoryStats;
import se.codeslasher.docker.model.api124.stats.NetworkStats;

import java.util.Map;

/**
 * Created by karl on 9/22/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = ContainerStats.ContainerStatsBuilder.class)
public class ContainerStats {

    @JsonProperty("read")
    private String read;

    @JsonProperty("pids_stats")
    private Map<String, Integer> pidsStats;

    @JsonProperty("networks")
    private Map<String,NetworkStats> networksStats;

    @JsonProperty("memory_stats")
    private MemoryStats memoryStats;

    @JsonProperty("blkio_stats")
    private BlkIOStats blkioStats;

    @JsonProperty("cpu_stats")
    private CpuStats cpuStats;

    @JsonProperty("precpu_stats")
    private CpuStats preCpuStats;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ContainerStatsBuilder {

        @JsonProperty("read")
        private String read;

        @JsonProperty("pids_stats")
        private Map<String, Integer> pidsStats;

        @JsonProperty("networks")
        private Map<String,NetworkStats> networksStats;

        @JsonProperty("memory_stats")
        private MemoryStats memoryStats;

        @JsonProperty("blkio_stats")
        private BlkIOStats blkioStats;

        @JsonProperty("cpu_stats")
        private CpuStats cpuStats;

        @JsonProperty("precpu_stats")
        private CpuStats preCpuStats;

    }

}
