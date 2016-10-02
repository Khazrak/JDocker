package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Created by karl on 10/2/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = ImageHistoryInfo.ImageHistoryInfoBuilder.class)
public class ImageHistoryInfo {

    private String id;
    private long created;
    private String createdBy;
    private List<String> tags;
    private long size;
    private String comment;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ImageHistoryInfoBuilder {

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
