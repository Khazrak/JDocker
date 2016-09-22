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
@JsonDeserialize(builder = MemoryStats.MemoryStatsBuilder.class)
public class MemoryStats {

    @JsonProperty("stats")
    private Map<String,Long> stats;

    @JsonProperty("max_usage")
    private long maxUsage;

    @JsonProperty("usage")
    private long usage;

    @JsonProperty("failcnt")
    private int failCount;

    @JsonProperty("limit")
    private long limit;

    @JsonPOJOBuilder(withPrefix = "")
    public static class MemoryStatsBuilder {

        @JsonProperty("stats")
        private Map<String,Long> stats;

        @JsonProperty("max_usage")
        private long maxUsage;

        @JsonProperty("usage")
        private long usage;

        @JsonProperty("failcnt")
        private int failCount;

        @JsonProperty("limit")
        private long limit;

    }

}
