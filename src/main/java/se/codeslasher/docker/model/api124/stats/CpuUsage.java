package se.codeslasher.docker.model.api124.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created by karl on 9/22/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = CpuUsage.CpuUsageBuilder.class)
public class CpuUsage {

    @JsonProperty("percpu_usage")
    private List<Long> perCpuUsage;

    @JsonProperty("usage_in_usermode")
    private Long usageInUsermode;

    @JsonProperty("total_usage")
    private long totalUsage;

    @JsonProperty("usage_in_kernelmode")
    private long usageInKernelMode;

    @JsonPOJOBuilder(withPrefix = "")
    public static class CpuUsageBuilder {

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
