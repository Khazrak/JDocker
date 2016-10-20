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

import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = BlkIOStats.BlkIOStatsBuilder.class)
public class BlkIOStats {

    @JsonProperty("io_service_bytes_recursive")
    private List<IOServiceBytes> ioServiceBytesRecursive;

    @JsonProperty("io_queue_recursive")
    private List<IOServiceBytes> ioQueueRecursive;

    @JsonProperty("io_service_time_recursive")
    private List<IOServiceBytes> ioServiceTimeRecursive;

    @JsonProperty("io_wait_time_recursive")
    private List<IOServiceBytes> ioWaitTimeRecursive;

    @JsonProperty("io_merged_recursive")
    private List<IOServiceBytes> ioMergedRecursive;

    @JsonProperty("io_time_recursive")
    private List<IOServiceBytes> ioTimeRecursive;

    @JsonProperty("sectors_recursive")
    private List<IOServiceBytes> sectorsRecursive;

    @JsonPOJOBuilder(withPrefix = "")
    public static class BlkIOStatsBuilder {

        @JsonProperty("io_service_bytes_recursive")
        private List<IOServiceBytes> ioServiceBytesRecursive;

        @JsonProperty("io_serviced_recursive")
        private List<IOServiceBytes> ioServicedRecursive;

        @JsonProperty("io_queue_recursive")
        private List<IOServiceBytes> ioQueueRecursive;

        @JsonProperty("io_service_time_recursive")
        private List<IOServiceBytes> ioServiceTimeRecursive;

        @JsonProperty("io_wait_time_recursive")
        private List<IOServiceBytes> ioWaitTimeRecursive;

        @JsonProperty("io_merged_recursive")
        private List<IOServiceBytes> ioMergedRecursive;

        @JsonProperty("io_time_recursive")
        private List<IOServiceBytes> ioTimeRecursive;

        @JsonProperty("sectors_recursive")
        private List<IOServiceBytes> sectorsRecursive;

    }
}
