package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.NetworkStats;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = NetworkStats129.NetworkStats129Builder.class)
public class NetworkStats129 implements NetworkStats {

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
    public static class NetworkStats129Builder {
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
