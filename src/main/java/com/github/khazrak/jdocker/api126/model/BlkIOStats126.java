package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.BlkIOStats;
import com.github.khazrak.jdocker.abstraction.IOServiceBytes;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = BlkIOStats126.BlkIOStats126Builder.class)
public class BlkIOStats126 implements BlkIOStats {

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
    public static class BlkIOStats126Builder {

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
