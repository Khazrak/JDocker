package se.codeslasher.docker.model.api124.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created by karl on 9/22/16.
 */
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
