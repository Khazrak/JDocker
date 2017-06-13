package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.CpuUsage;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = CpuUsage129.CpuUsage129Builder.class)
public class CpuUsage129 implements CpuUsage {

    @JsonProperty("percpu_usage")
    private List<Long> perCpuUsage;

    @JsonProperty("usage_in_usermode")
    private Long usageInUsermode;

    @JsonProperty("total_usage")
    private long totalUsage;

    @JsonProperty("usage_in_kernelmode")
    private long usageInKernelMode;

    @JsonPOJOBuilder(withPrefix = "")
    public static class CpuUsage129Builder {

        @JsonProperty("percpu_usage")
        private List<Long> perCpuUsage;

        @JsonProperty("usage_in_usermode")
        private Long usageInUsermode;

        @JsonProperty("total_usage")
        private long totalUsage;

        @JsonProperty("usage_in_kernelmode")
        private long usageInKernelMode;

    }

}
