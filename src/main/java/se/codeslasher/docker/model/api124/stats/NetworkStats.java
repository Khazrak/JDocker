package se.codeslasher.docker.model.api124.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by karl on 9/22/16.
 */
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
