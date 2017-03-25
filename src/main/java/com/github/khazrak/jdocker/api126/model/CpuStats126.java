package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.CpuStats;
import com.github.khazrak.jdocker.abstraction.CpuUsage;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = CpuStats126.CpuStats126Builder.class)
public class CpuStats126 implements CpuStats {

    @JsonProperty("cpu_usage")
    private CpuUsage cpuUsage;

    @JsonProperty("system_cpu_usage")
    private long systemCpuUsage;

    @JsonProperty("throttling_data")
    private Map<String, Long> throttlingData;

    @JsonPOJOBuilder(withPrefix = "")
    public static class CpuStats126Builder {

        @JsonDeserialize(as = CpuUsage126.class)
        @JsonProperty("cpu_usage")
        private CpuUsage cpuUsage;

        @JsonProperty("system_cpu_usage")
        private long systemCpuUsage;

        @JsonProperty("throttling_data")
        private Map<String, Long> throttlingData;

    }

}
