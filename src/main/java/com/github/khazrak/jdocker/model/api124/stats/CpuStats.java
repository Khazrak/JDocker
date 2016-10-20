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
package com.github.khazrak.jdocker.model.api124.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

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
