package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.*;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = ContainerStats126.ContainerStats126Builder.class)
public class ContainerStats126 implements ContainerStats {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("read")
    private String read;

    @JsonProperty("preread")
    private String preRead;

    @JsonProperty("pids_stats")
    private Map<String, Integer> pidsStats;

    @JsonProperty("networks")
    private Map<String, NetworkStats> networksStats;

    @JsonProperty("memory_stats")
    private MemoryStats memoryStats;

    @JsonDeserialize(as = BlkIOStats126.class)
    @JsonProperty("blkio_stats")
    private BlkIOStats blkioStats;

    @JsonProperty("cpu_stats")
    private CpuStats cpuStats;

    @JsonProperty("precpu_stats")
    private CpuStats preCpuStats;

    @JsonProperty("num_procs")
    private int numProcs;

    @JsonProperty("storage_stats")
    private Map<String, String> storageStats;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ContainerStats126Builder {

        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("read")
        private String read;

        @JsonProperty("preread")
        private String preRead;

        @JsonProperty("pids_stats")
        private Map<String, Integer> pidsStats;

        @JsonProperty("networks")
        private Map<String, NetworkStats> networksStats;

        @JsonDeserialize(as = MemoryStats126.class)
        @JsonProperty("memory_stats")
        private MemoryStats memoryStats;

        @JsonDeserialize(as = BlkIOStats126.class)
        @JsonProperty("blkio_stats")
        private BlkIOStats blkioStats;

        @JsonDeserialize(as = CpuStats126.class)
        @JsonProperty("cpu_stats")
        private CpuStats cpuStats;

        @JsonDeserialize(as = CpuStats126.class)
        @JsonProperty("precpu_stats")
        private CpuStats preCpuStats;

        @JsonProperty("num_procs")
        private int numProcs;

        @JsonProperty("storage_stats")
        private Map<String, String> storageStats;

    }

}
