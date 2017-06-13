package com.github.khazrak.jdocker.api129.model;

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
@JsonDeserialize(builder = CpuStats129.CpuStats129Builder.class)
public class CpuStats129 implements CpuStats {

    @JsonProperty("cpu_usage")
    private CpuUsage cpuUsage;

    @JsonProperty("system_cpu_usage")
    private long systemCpuUsage;

    @JsonProperty("throttling_data")
    private Map<String, Long> throttlingData;

    @JsonProperty("online_cpus")
    private int onlineCpus;

    @JsonPOJOBuilder(withPrefix = "")
    public static class CpuStats129Builder {

        @JsonDeserialize(as = CpuUsage129.class)
        @JsonProperty("cpu_usage")
        private CpuUsage cpuUsage;

        @JsonProperty("system_cpu_usage")
        private long systemCpuUsage;

        @JsonProperty("throttling_data")
        private Map<String, Long> throttlingData;

        @JsonProperty("online_cpus")
        private int onlineCpus;

    }

}
