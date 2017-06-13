package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.ImageSearchInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonDeserialize(builder = ImageSearchInfo129.ImageSearchInfo129Builder.class)
public class ImageSearchInfo129 implements ImageSearchInfo {

    private String description;
    private boolean official;
    private boolean automated;
    private String name;
    private int starCount;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ImageSearchInfo129Builder {

        @JsonProperty("description")
        private String description;

        @JsonProperty("is_official")
        private boolean official;

        @JsonProperty("is_automated")
        private boolean automated;

        @JsonProperty("name")
        private String name;

        @JsonProperty("star_count")
        private int starCount;

    }

}
