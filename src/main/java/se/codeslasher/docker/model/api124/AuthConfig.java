package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

/**
 * Created by karl on 9/18/16.
 */
@Builder
public class AuthConfig {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String emal;
}
