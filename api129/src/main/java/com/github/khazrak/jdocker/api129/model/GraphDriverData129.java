package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.GraphDriverData;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = GraphDriverData129.GraphDriverData129Builder.class)
public class GraphDriverData129 implements GraphDriverData {

    private String deviceId;
    private String deviceName;
    private String deviceSize;

    @JsonPOJOBuilder(withPrefix = "")
    public static class GraphDriverData129Builder {
        @JsonProperty("DeviceId")
        private String deviceId;

        @JsonProperty("DeviceName")
        private String deviceName;

        @JsonProperty("DeviceSize")
        private String deviceSize;
    }

}
