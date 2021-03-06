package com.github.khazrak.jdocker.api126.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.ImageHistoryInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonDeserialize(builder = ImageHistoryInfo126.ImageHistoryInfo126Builder.class)
public class ImageHistoryInfo126 implements ImageHistoryInfo {

    private String id;
    private long created;
    private String createdBy;
    private List<String> tags;
    private long size;
    private String comment;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ImageHistoryInfo126Builder {

        @JsonProperty("Id")
        private String id;

        @JsonProperty("Created")
        private long created;

        @JsonProperty("CreatedBy")
        private String createdBy;

        @JsonProperty("Tags")
        private List<String> tags;

        @JsonProperty("Size")
        private long size;

        @JsonProperty("Comment")
        private String comment;

    }

}
