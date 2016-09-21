package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by karl on 9/21/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = GraphDriverData.GraphDriverDataBuilder.class)
public class GraphDriverData {

    @JsonProperty("DeviceId")
    private String deviceId;

    @JsonProperty("DeviceName")
    private String deviceName;

    @JsonProperty("DeviceSize")
    private String DeviceSize;

    @JsonPOJOBuilder(withPrefix = "")
    public static class GraphDriverDataBuilder {
        @JsonProperty("DeviceId")
        private String deviceId;

        @JsonProperty("DeviceName")
        private String deviceName;

        @JsonProperty("DeviceSize")
        private String DeviceSize;
    }

}
