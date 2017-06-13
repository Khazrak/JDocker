package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.MemoryStats;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = MemoryStats129.MemoryStats129Builder.class)
public class MemoryStats129 implements MemoryStats {

    @JsonProperty("stats")
    private Map<String, Long> stats;

    @JsonProperty("max_usage")
    private long maxUsage;

    @JsonProperty("usage")
    private long usage;

    @JsonProperty("failcnt")
    private int failCount;

    @JsonProperty("limit")
    private long limit;

    @JsonPOJOBuilder(withPrefix = "")
    public static class MemoryStats129Builder {

        @JsonProperty("stats")
        private Map<String, Long> stats;

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
