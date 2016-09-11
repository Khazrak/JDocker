package se.codeslasher.docker;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * Created by karl on 9/11/16.
 */
@Getter
@Builder
@ToString
@JsonDeserialize(builder = NetworkSettings.NetworkSettingsBuilder.class)
public class NetworkSettings {

    @JsonProperty("Networks")
    private Map<String,Network> networks;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NetworkSettingsBuilder {
        @JsonProperty("Networks")
        private Map<String,Network> networks;
    }

}
