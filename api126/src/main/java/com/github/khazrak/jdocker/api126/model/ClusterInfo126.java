package com.github.khazrak.jdocker.api126.model;


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
@JsonDeserialize(builder = ClusterInfo126.ClusterInfo126Builder.class)
public class ClusterInfo126 implements ClusterInfo {

    private String id;
    private Map<String, String> version;
    private String createdAt;
    private String updatedAt;
    private ClusterSpec spec;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ClusterInfo126Builder {

        @JsonProperty("ID")
        private String id;

        @JsonProperty("Version")
        private Map<String, String> version;

        @JsonProperty("CreatedAt")
        private String createdAt;

        @JsonProperty("UpdatedAt")
        private String updatedAt;

        @JsonDeserialize(as = ClusterSpec126.class)
        @JsonProperty("Spec")
        private ClusterSpec spec;
    }

}
