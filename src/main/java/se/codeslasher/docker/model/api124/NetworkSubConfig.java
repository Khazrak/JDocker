package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

/**
 * Created by karl on 9/7/16.
 */
@Builder
public class NetworkSubConfig {

    @JsonProperty("IPAMConfig")
    private IPAMConfig ipamConfig;

    @Singular
    @JsonProperty("Links")
    private List<String> links;

    @Singular
    @JsonProperty("Aliases")
    private List<String> aliases;

}
