package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by karl on 9/20/16.
 */
@Builder
@Getter
public class Ulimit {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Soft")
    private int soft;

    @JsonProperty("Hard")
    private int hard;

}
