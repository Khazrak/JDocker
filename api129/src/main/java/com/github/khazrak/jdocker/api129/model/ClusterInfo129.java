package com.github.khazrak.jdocker.api129.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.ClusterInfo;
import com.github.khazrak.jdocker.abstraction.ClusterSpec;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = ClusterInfo129.ClusterInfo129Builder.class)
public class ClusterInfo129 implements ClusterInfo {

    private String id;
    private Map<String, String> version;
    private String createdAt;
    private String updatedAt;
    private ClusterSpec spec;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ClusterInfo129Builder {

        @JsonProperty("ID")
        private String id;

        @JsonProperty("Version")
        private Map<String, String> version;

        @JsonProperty("CreatedAt")
        private String createdAt;

        @JsonProperty("UpdatedAt")
        private String updatedAt;

        @JsonDeserialize(as = ClusterSpec129.class)
        @JsonProperty("Spec")
        private ClusterSpec spec;
    }

}
