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
package se.codeslasher.docker.model.api124.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = NetworkStats.NetworkStatsBuilder.class)
public class NetworkStats {

    @JsonProperty("rx_bytes")
    private int rxBytes;

    @JsonProperty("rx_dropped")
    private int rxDropped;

    @JsonProperty("rx_errors")
    private int rxErrors;

    @JsonProperty("rx_packets")
    private int rxPackets;

    @JsonProperty("tx_bytes")
    private int txBytes;

    @JsonProperty("tx_dropped")
    private int txDropped;

    @JsonProperty("tx_errors")
    private int txErrors;

    @JsonProperty("tx_packets")
    private int txPackets;



    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkStatsBuilder {
        @JsonProperty("rx_bytes")
        private int rxBytes;

        @JsonProperty("rx_dropped")
        private int rxDropped;

        @JsonProperty("rx_errors")
        private int rxErrors;

        @JsonProperty("rx_packets")
        private int rxPackets;

        @JsonProperty("tx_bytes")
        private int txBytes;

        @JsonProperty("tx_dropped")
        private int txDropped;

        @JsonProperty("tx_errors")
        private int txErrors;

        @JsonProperty("tx_packets")
        private int txPackets;
    }
}
