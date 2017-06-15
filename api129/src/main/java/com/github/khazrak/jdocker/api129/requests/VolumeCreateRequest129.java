package com.github.khazrak.jdocker.api129.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.VolumeCreateRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = VolumeCreateRequest129.VolumeCreateRequest129Builder.class)
public class VolumeCreateRequest129 implements VolumeCreateRequest {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Driver")
    private String driver;

    @JsonProperty("Labels")
    private Map<String, String> labels;

    @JsonProperty("DriverOpts")
    private Map<String, String> driverOpts;

    @JsonPOJOBuilder(withPrefix = "")
    public static class VolumeCreateRequest129Builder {

    }


}
