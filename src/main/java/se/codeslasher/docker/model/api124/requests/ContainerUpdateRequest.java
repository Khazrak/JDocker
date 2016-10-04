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
package se.codeslasher.docker.model.api124.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import se.codeslasher.docker.model.api124.RestartPolicy;

@Getter
@Builder
public class ContainerUpdateRequest {

    @JsonProperty("BlkioWeight")
    private long blkioWeight;

    @JsonProperty("CpuShares")
    private int cpuShares;

    @JsonProperty("CpuPeriod")
    private long cpuPeriod;

    @JsonProperty("CpuQuota")
    private long cpuQuota;

    @JsonProperty("CpusetCpus")
    private String cpuSetCpus;

    @JsonProperty("CpusetMems")
    private String cpuSetMems;

    @JsonProperty("Memory")
    private long memory;

    @JsonProperty("MemorySwap")
    private long memorySwap;
    @JsonProperty("MemoryReservation")
    private long memoryReservation;

    @JsonProperty("KernelMemory")
    private long kernelMemory;

    @JsonProperty("RestartPolicy")
    private RestartPolicy restartPolicy;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ContainerUpdateRequestBuilder {

        @JsonProperty("RestartPolicy")
        private RestartPolicy restartPolicy = RestartPolicy.builder().build();

    }

}
