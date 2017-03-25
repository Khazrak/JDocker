package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.ImageInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = ImageInfo126.ImageInfo126Builder.class)
public class ImageInfo126 implements ImageInfo {

    int containers;
    long sharedSize;
    String id;
    String parentId;
    List<String> repoTags;
    List<String> repoDigests;
    long created;
    long size;
    long virtualSize;
    Map<String,String> labels;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ImageInfo126Builder {

        @JsonProperty("Containers")
        private int containers;

        @JsonProperty("SharedSize")
        private long sharedSize;

        @JsonProperty("Id")
        private String id;

        @JsonProperty("ParentId")
        private String parentId;

        @JsonProperty("RepoTags")
        private List<String> repoTags;

        @JsonProperty("RepoDigests")
        private List<String> repoDigests;

        @JsonProperty("Created")
        private long created;

        @JsonProperty("Size")
        private long size;

        @JsonProperty("VirtualSize")
        private long virtualSize;

        @JsonProperty("Labels")
        private Map<String,String> labels;


    }

}
