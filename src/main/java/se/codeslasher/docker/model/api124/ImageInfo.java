package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * Created by karl on 9/22/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = ImageInfo.ImageInfoBuilder.class)
public class ImageInfo {

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

    @JsonPOJOBuilder(withPrefix = "")
    public static class ImageInfoBuilder {

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
