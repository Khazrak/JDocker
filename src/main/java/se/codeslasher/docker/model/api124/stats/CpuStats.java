package se.codeslasher.docker.model.api124.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * Created by karl on 9/22/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = CpuStats.CpuStatsBuilder.class)
public class CpuStats {

    @JsonProperty("cpu_usage")
    private CpuUsage cpuUsage;

    @JsonProperty("system_cpu_usage")
    private long systemCpuUsage;

    @JsonProperty("throttling_data")
    private Map<String, Long> throttlingData;

    @JsonPOJOBuilder(withPrefix = "")
    public static class CpuStatsBuilder {

        @JsonProperty("cpu_usage")
        private CpuUsage cpuUsage;

        @JsonProperty("system_cpu_usage")
        private long systemCpuUsage;

        @JsonProperty("throttling_data")
        private Map<String, Long> throttlingData;

    }
}
