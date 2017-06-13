package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.GraphDriver;
import com.github.khazrak.jdocker.abstraction.ImageInspect;
import com.github.khazrak.jdocker.abstraction.ImageInspectContainerInfo;
import com.github.khazrak.jdocker.abstraction.RootFS;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = ImageInspect129.ImageInspect129Builder.class)
public class ImageInspect129 implements ImageInspect {

    private String id;
    private String parentId;
    private List<String> repoTags;
    private List<String> repoDigests;
    private String created;
    private long size;
    private long virtualSize;
    private Map<String, String> labels;
    private String parent;
    private String comment;
    private String os;
    private String architecture;
    private String container;
    private ImageInspectContainerInfo containerConfig;
    private String dockerVersion;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ImageInspect129Builder {

        @JsonProperty("Id")
        private String id;

        @JsonProperty("ParentId")
        private String parentId;

        @JsonProperty("RepoTags")
        private List<String> repoTags;

        @JsonProperty("RepoDigests")
        private List<String> repoDigests;

        @JsonProperty("Created")
        private String created;

        @JsonProperty("Size")
        private long size;

        @JsonProperty("VirtualSize")
        private long virtualSize;

        @JsonProperty("Labels")
        private Map<String, String> labels;

        @JsonProperty("Parent")
        private String parent;

        @JsonProperty("Comment")
        private String comment;

        @JsonProperty("Os")
        private String os;

        @JsonProperty("Architecture")
        private String architecture;

        @JsonProperty("Container")
        private String container;

        @JsonDeserialize(as = ImageInspectContainerInfo129.class)
        @JsonProperty("ContainerConfig")
        private ImageInspectContainerInfo containerConfig;

        @JsonProperty("DockerVersion")
        private String dockerVersion;


        @JsonProperty("Author")
        private String author;

        @JsonDeserialize(as = ImageInspectContainerInfo129.class)
        @JsonProperty("Config")
        private ImageInspectContainerInfo config;

        @JsonDeserialize(as = GraphDriver129.class)
        @JsonProperty("GraphDriver")
        private GraphDriver graphDriver;

        @JsonDeserialize(as = RootFS129.class)
        @JsonProperty("RootFS")
        private RootFS rootFs;
    }

}
