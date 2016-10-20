/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.khazrak.jdocker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.model.api124.stats.CpuStats;
import lombok.Builder;
import lombok.Getter;
import com.github.khazrak.jdocker.model.api124.stats.BlkIOStats;
import com.github.khazrak.jdocker.model.api124.stats.MemoryStats;
import com.github.khazrak.jdocker.model.api124.stats.NetworkStats;

import java.util.Map;

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
