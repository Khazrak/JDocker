package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@JsonDeserialize(builder = Image.ImageBuilder.class)
public class Image {

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
    private Map<String,String> labels;

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

    @JsonProperty("ContainerConfig")
    private ImageContainerConfig containerConfig;

    @JsonProperty("DockerVersion")
    private String dockerVersion;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ImageBuilder {

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
        private Map<String,String> labels;

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

        @JsonProperty("ContainerConfig")
        private ImageContainerConfig containerConfig;

        @JsonProperty("DockerVersion")
        private String dockerVersion;



        @JsonProperty("Author")
        private String author;

        @JsonProperty("Config")
        private ImageContainerConfig config;

        @JsonProperty("GraphDriver")
        private GraphDriver graphDriver;

        @JsonProperty("RootFS")
        private RootFS rootFs;
    }

}
