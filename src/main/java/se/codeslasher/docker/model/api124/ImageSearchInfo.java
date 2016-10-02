package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by karl on 10/2/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = ImageSearchInfo.ImageSearchInfoBuilder.class)
public class ImageSearchInfo {

    private String description;
    private boolean official;
    private boolean automated;
    private String name;
    private int starCount;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ImageSearchInfoBuilder {

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
