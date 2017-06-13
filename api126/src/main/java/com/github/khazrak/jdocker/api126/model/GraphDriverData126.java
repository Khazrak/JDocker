package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.GraphDriverData;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = GraphDriverData126.GraphDriverData126Builder.class)
public class GraphDriverData126 implements GraphDriverData {

    private String deviceId;
    private String deviceName;
    private String deviceSize;

    @JsonPOJOBuilder(withPrefix = "")
    public static class GraphDriverData126Builder {
        @JsonProperty("DeviceId")
        private String deviceId;

        @JsonProperty("DeviceName")
        private String deviceName;

        @JsonProperty("DeviceSize")
        private String deviceSize;
    }

}
