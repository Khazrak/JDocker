package se.codeslasher.docker.model.api124;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;

/**
 * Created by karl on 9/7/16.
 */
@Builder
@JsonDeserialize(builder = RestartPolicy.RestartPolicyBuilder.class)
public class RestartPolicy {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("MaximumRetryCount")
    private int maximumRetryCount;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RestartPolicyBuilder {
        @JsonProperty("Name")
        private String name = "";

        @JsonProperty("MaximumRetryCount")
        private int maximumRetryCount;
    }
}
